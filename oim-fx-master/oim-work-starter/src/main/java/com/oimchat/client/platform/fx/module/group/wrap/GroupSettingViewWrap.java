
package com.oimchat.client.platform.fx.module.group.wrap;

import java.util.ArrayList;
import java.util.List;

import com.oimchat.app.fx.view.ui.classics.element.question.QuestionItem;
import com.oimchat.app.fx.view.ui.classics.element.question.QuestionPane;
import com.oimchat.app.fx.view.ui.classics.module.group.GroupJoinVerifySettingPane;
import com.oimchat.app.fx.view.ui.classics.module.stage.ClassicsTitleStage;
import com.oimchat.client.common.sync.SyncGet;
import com.oimchat.client.general.kernel.work.module.contact.type.ContactVerifyTypeEnum;
import com.oimchat.client.general.kernel.work.module.group.entity.GroupJoinSetting;
import com.oimchat.client.general.kernel.work.module.group.entity.GroupJoinVerifyQuestion;
import com.oimchat.client.general.kernel.work.module.group.handler.GroupJoinSettingHandler;
import com.oimchat.client.general.kernel.work.module.group.type.GroupInviteTypeEnum;
import com.oimchat.client.general.kernel.work.module.group.type.GroupJoinTypeEnum;
import com.oimchat.client.platform.fx.view.common.box.StageBox;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.app.view.fx.common.util.FxUtil;
import com.onlyxiahui.common.message.util.OnlyMessageUtil;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

/**
 * Description <br>
 * Date 2021-04-02 11:34:32<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class GroupSettingViewWrap extends AbstractMaterial {
	ClassicsTitleStage stage;
	GroupJoinVerifySettingPane groupJoinVerifySettingPane = new GroupJoinVerifySettingPane();

	QuestionPane groupJoinVerifyQuestionMultiplePane = new QuestionPane();
	List<QuestionItem> groupJoinVerifyQuestionItems = new ArrayList<>();

	QuestionPane groupJoinVerifyQuestionOnePane = new QuestionPane();
	QuestionItem groupJoinVerifyQuestionOneItem = new QuestionItem();

	GroupJoinVerifySettingPane inviteTypePane = new GroupJoinVerifySettingPane();

	String groupId;

	public GroupSettingViewWrap(AppContext appContext) {
		super(appContext);
		initStage();
		initUI();
		initEvent();
	}

	private void initStage() {
		ClassicsTitleStage stage = getStage();
		StageBox sb = this.appContext.getObject(StageBox.class);
		sb.add(stage);

		stage.setResizable(false);
		stage.setTitlePaneStyle(2);
		stage.setWidth(560);
		stage.setHeight(420);
		stage.setTitle("系统设置");
		stage.setCenter(groupJoinVerifySettingPane);

		groupJoinVerifySettingPane.setPadding(new Insets(20, 0, 0, 30));
	}

	public synchronized ClassicsTitleStage getStage() {
		if (null == stage) {
			stage = SyncGet.<ClassicsTitleStage>syncGet((p) -> {
				FxUtil.invoke(() -> {
					p.put(new ClassicsTitleStage());
				});
			});
		}
		return stage;
	}

	public void showPrompt(String text) {
		FxUtil.invoke(() -> {
			getStage().showPopupPrompt(text);
		});
	}

	public void setVisible(boolean visible) {
		FxUtil.invoke(() -> {
			ClassicsTitleStage stage = getStage();
			if (visible) {
				if (stage.isIconified()) {
					stage.setIconified(false);
				} else {
					stage.show();
				}
				stage.toFront();
			} else {
				stage.hide();
			}
		});
	}

	public boolean isShowing() {
		return getStage().isShowing();
	}

	private void initUI() {

		inviteTypePane.setPadding(new Insets(5, 0, 5, 20));
		// TODO Auto-generated method stub
		groupJoinVerifyQuestionOneItem.setQuestionEditable(true);
		List<QuestionItem> items = new ArrayList<>();
		items.add(groupJoinVerifyQuestionOneItem);
		groupJoinVerifyQuestionOnePane.setQuestionItemList(items);

		Button button = new Button("添加问题");
		BorderPane groupJoinVerifyQuestionPane = new BorderPane();
		groupJoinVerifyQuestionPane.setTop(button);
		groupJoinVerifyQuestionPane.setCenter(groupJoinVerifyQuestionMultiplePane);

		button.setOnAction(a -> {
			if (groupJoinVerifyQuestionItems.size() < 3) {

				QuestionItem qi = new QuestionItem();
				qi.setVerifyQuestion(true);
				qi.setVerifyAnswer(false);
				qi.setAnswerVisible(false);
				qi.setQuestionEditable(true);
				groupJoinVerifyQuestionItems.add(qi);

				Button db = new Button("-删除");
				qi.setRight(db);

				db.setOnAction(da -> {
					groupJoinVerifyQuestionItems.remove(qi);
					groupJoinVerifyQuestionMultiplePane.setQuestionItemList(groupJoinVerifyQuestionItems);
				});
				groupJoinVerifyQuestionMultiplePane.setQuestionItemList(groupJoinVerifyQuestionItems);
			}
		});

//				/** 1：允许任何人加入 **/
//				any("1", "允许任何人加入"),
//				/** 2：需要验证消息 **/
//				auth("2", "需要验证消息"),
//				/** 3：需要回答正确问题 **/
//				answer("3", "需要回答正确问题"),
//				/** 4：需要回答问题并由管理员审核 **/
//				confirm("4", "需要回答问题并由管理员审核"),
//				/** 5：只允许邀请加入 **/
//				invite("5", "只允许邀请加入"),
//				/** 4：不允许任何人加入 **/
//				never("6", "不允许任何人加入");
		groupJoinVerifySettingPane.addTab(GroupJoinTypeEnum.any.getCode(), GroupJoinTypeEnum.any.getText(), null);
		groupJoinVerifySettingPane.addTab(GroupJoinTypeEnum.auth.getCode(), GroupJoinTypeEnum.auth.getText(), null);
		groupJoinVerifySettingPane.addTab(GroupJoinTypeEnum.answer.getCode(), GroupJoinTypeEnum.answer.getText(), groupJoinVerifyQuestionOnePane);
		groupJoinVerifySettingPane.addTab(GroupJoinTypeEnum.confirm.getCode(), GroupJoinTypeEnum.confirm.getText(), groupJoinVerifyQuestionPane);
		groupJoinVerifySettingPane.addTab(GroupJoinTypeEnum.invite.getCode(), GroupJoinTypeEnum.invite.getText(), inviteTypePane);
		groupJoinVerifySettingPane.addTab(GroupJoinTypeEnum.never.getCode(), GroupJoinTypeEnum.never.getText(), null);

//		/** 1：不允许邀请 **/
//		never("1", "不允许邀请"),
//		/** 2：管理员邀请加入 **/
//		admin("2", "管理员邀请加入"),
//		/** 3：允许任何人邀请加入 **/
//		any("3", "允许任何人邀请加入"),
//		/** 4：需要管理员验证 **/
//		auth("4", "需要管理员验证");
		inviteTypePane.addTab(GroupInviteTypeEnum.never.getCode(), GroupInviteTypeEnum.never.getText(), null);
		inviteTypePane.addTab(GroupInviteTypeEnum.admin.getCode(), GroupInviteTypeEnum.admin.getText(), null);
		inviteTypePane.addTab(GroupInviteTypeEnum.any.getCode(), GroupInviteTypeEnum.any.getText(), null);
		inviteTypePane.addTab(GroupInviteTypeEnum.auth.getCode(), GroupInviteTypeEnum.auth.getText(), null);
	}

	private void initEvent() {
		ClassicsTitleStage stage = getStage();
		stage.setDoneAction(a -> {
			save();
		});
	}

	void loadContactHarassSetting() {

		groupJoinVerifyQuestionItems.clear();
		FxUtil.invoke(() -> {
			groupJoinVerifyQuestionMultiplePane.setQuestionItemList(groupJoinVerifyQuestionItems);
		});

		GroupJoinSettingHandler chsh = this.appContext.getObject(GroupJoinSettingHandler.class);
		chsh.getByGroupId(groupId, (info, v) -> {
			if (null != v) {

				// GroupJoinSettingEntityCase
				GroupJoinSetting hs = v.getSetting();

				String joinType = hs.getJoinType();
				String question = hs.getQuestion();
				String answer = hs.getAnswer();

				String inviteType = hs.getInviteType();

				List<GroupJoinVerifyQuestion> questions = v.getQuestions();
				if (null != questions) {
					for (GroupJoinVerifyQuestion sq : questions) {
						QuestionItem qi = new QuestionItem();
						qi.setVerifyQuestion(true);
						qi.setVerifyAnswer(false);
						qi.setAnswerVisible(false);
						qi.setQuestionEditable(true);
						qi.setQuestion(sq.getQuestion());
						qi.setQuestionId(sq.getId());
						groupJoinVerifyQuestionItems.add(qi);

						Button db = new Button("-删除");
						qi.setRight(db);

						db.setOnAction(da -> {
							groupJoinVerifyQuestionItems.remove(qi);
							groupJoinVerifyQuestionMultiplePane.setQuestionItemList(groupJoinVerifyQuestionItems);
						});
					}
				}
				FxUtil.invoke(() -> {
					groupJoinVerifyQuestionOneItem.setQuestion(question);
					groupJoinVerifyQuestionOneItem.setAnswer(answer);

					groupJoinVerifySettingPane.setSelected(joinType);
					groupJoinVerifyQuestionMultiplePane.setQuestionItemList(groupJoinVerifyQuestionItems);

					inviteTypePane.setSelected(inviteType);
				});
			}
		});
	}

	public void save() {

		String joinType = groupJoinVerifySettingPane.getSelected();
		String answer = groupJoinVerifyQuestionOneItem.getAnswer();
		String question = groupJoinVerifyQuestionOneItem.getQuestion();

		String inviteType = inviteTypePane.getSelected();

		GroupJoinSetting setting = new GroupJoinSetting();

		setting.setGroupId(groupId);

		setting.setJoinType(joinType);
		setting.setQuestion(question);
		setting.setAnswer(answer);

		setting.setInviteType(inviteType);

		List<GroupJoinVerifyQuestion> questions = new ArrayList<>();
		if (ContactVerifyTypeEnum.confirm.isEquals(joinType)) {
			for (QuestionItem qi : groupJoinVerifyQuestionItems) {
				if (!qi.verify()) {
					return;
				} else {
					GroupJoinVerifyQuestion sq = new GroupJoinVerifyQuestion();
					sq.setQuestion(qi.getQuestion());
					sq.setId(qi.getQuestionId());
					questions.add(sq);
				}
			}
		}

		GroupJoinSettingHandler chsh = this.appContext.getObject(GroupJoinSettingHandler.class);
		chsh.update(setting, questions, (info) -> {
			if (info.isSuccess()) {
				this.setVisible(false);
			} else {
				showPrompt(OnlyMessageUtil.getDefaultWarningText(info));
			}
		});
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
		loadContactHarassSetting();
	}
}
