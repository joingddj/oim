package com.oimchat.client.platform.fx.view.impl.classics;

import java.util.ArrayList;
import java.util.List;

import com.oimchat.app.fx.view.ui.classics.element.info.BaseInfoPane;
import com.oimchat.app.fx.view.ui.classics.element.question.QuestionItem;
import com.oimchat.app.fx.view.ui.classics.element.question.QuestionPane;
import com.oimchat.app.fx.view.ui.classics.module.add.AddApplyPane;
import com.oimchat.app.fx.view.ui.classics.module.stage.ClassicsTitleStage;
import com.oimchat.client.general.common.util.HereStringUtil;
import com.oimchat.client.general.kernel.work.module.group.data.dto.GroupJoinApplyData;
import com.oimchat.client.general.kernel.work.module.group.data.dto.GroupJoinSettingEntityCase;
import com.oimchat.client.general.kernel.work.module.group.data.dto.JoinVerifyAnswer;
import com.oimchat.client.general.kernel.work.module.group.entity.Group;
import com.oimchat.client.general.kernel.work.module.group.entity.GroupCategory;
import com.oimchat.client.general.kernel.work.module.group.entity.GroupJoinSetting;
import com.oimchat.client.general.kernel.work.module.group.entity.GroupJoinVerifyQuestion;
import com.oimchat.client.general.kernel.work.module.group.handler.GroupCategoryHandler;
import com.oimchat.client.general.kernel.work.module.group.handler.GroupHandler;
import com.oimchat.client.general.kernel.work.module.group.handler.GroupJoinHandler;
import com.oimchat.client.general.kernel.work.module.group.handler.GroupJoinSettingHandler;
import com.oimchat.client.general.kernel.work.module.group.manager.GroupCategoryManager;
import com.oimchat.client.general.kernel.work.module.group.type.GroupJoinTypeEnum;
import com.oimchat.client.general.kernel.work.module.personal.box.PersonalBox;
import com.oimchat.client.platform.common.view.GroupJoinApplyView;
import com.oimchat.client.platform.fx.work.common.handler.GroupHeadImageHandler;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.app.view.fx.common.util.FxUtil;
import com.onlyxiahui.common.message.bean.Info;
import com.onlyxiahui.common.message.util.OnlyMessageUtil;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 * 描述： 添加好友或者加入群显示窗口
 * 
 * @author XiaHui
 * @date 2015年3月16日 下午10:42:19
 * @version 0.0.1
 */
public class GroupJoinApplyViewImpl extends BaseStageView<ClassicsTitleStage> implements GroupJoinApplyView {

	AddApplyPane pane = new AddApplyPane();

	BorderPane blockedPane = new BorderPane();
	Label blockedLabel = new Label();

	BorderPane errorPane = new BorderPane();
	Label errorLabel = new Label();

	QuestionPane questionMultiplePane = new QuestionPane();
	List<QuestionItem> questionItems = new ArrayList<>();

	QuestionPane questionOnePane = new QuestionPane();
	QuestionItem questionOneItem = new QuestionItem();

	private String joinType;

	private String groupId;

	public GroupJoinApplyViewImpl(AppContext appContext) {
		super(appContext);
		FxUtil.invoke(() -> {
			initStage(getStage());
			initComponent();
			initEvent();
		});
	}

	public void setCategoryList(List<GroupCategory> list) {
		FxUtil.invoke(() -> {
			for (GroupCategory data : list) {
				pane.addCategory(data.getId(), data.getName());
			}
		});
	}

	public void clearCategory() {
		FxUtil.invoke(() -> {
			pane.clearCategory();
		});
	}

	public void selectCategory() {
		FxUtil.invoke(() -> {
			pane.selectCategory(0);
		});
	}

	public void setGroupData(Group group) {

		StringBuilder sb = new StringBuilder();
		if (null != group) {
			sb.append("名称：");
			sb.append(HereStringUtil.value(group.getName()));
			sb.append("\n");

			sb.append("群号：");
			sb.append(HereStringUtil.value(group.getNumber() + ""));
			sb.append("\n");

			sb.append("简介：");
			sb.append(HereStringUtil.value(group.getIntroduce()));
			sb.append("\n");
		}

		BaseInfoPane ip = pane.getInfoPanel();
		FxUtil.invoke(() -> {
			pane.clearData();
			ip.setInfoText(sb.toString());
		});
		if (null != group) {
			GroupHeadImageHandler him = appContext.getObject(GroupHeadImageHandler.class);
			him.loadAvatarImage(group.getHead(), group.getAvatar(), (img) -> {
				FxUtil.invoke(() -> {
					ip.setHeadImage(img);
				});
			});
		} else {
			FxUtil.invoke(() -> {
				ip.setHeadImage(null);
			});
		}
	}

	private void doneAction() {
		send();
	}

	public void addCategory(String name) {
		if (null != name && !"".equals(name)) {
			GroupCategory data = new GroupCategory();
			data.setName(name);

			GroupCategoryHandler uch = this.appContext.getObject(GroupCategoryHandler.class);
			uch.add(data, (info, v) -> {
				if (!info.isSuccess()) {
					showPrompt("添加失败！");
				} else {
					FxUtil.invoke(() -> {
						pane.addCategory(v.getId(), v.getName());
					});
				}
			});
		}
	}

	public void showWaiting(boolean show) {

	}

	@Override
	public void setGroupId(String groupId) {
		this.groupId = groupId;
		FxUtil.invoke(() -> {
			pane.setBottomNode(null);
			stage.setCenter(null);
		});
		loadInfo();
		loadSetting(groupId);
		loadCategoryList();
	}

	private void loadInfo() {
		GroupHandler gh = this.appContext.getObject(GroupHandler.class);
		gh.getById(groupId, (info, g) -> {
			setGroupData(g);
		});
	}

	private void loadSetting(String groupId) {
		FxUtil.invoke(() -> {
			stage.setCenter(null);
			pane.setBottomNode(null);
			questionItems.clear();
			questionMultiplePane.setQuestionItemList(questionItems);
		});
		GroupJoinSettingHandler ch = this.appContext.getObject(GroupJoinSettingHandler.class);
		ch.getByGroupId(groupId, (info, data) -> {
			setVerifySetting(info, data);
		});
	}

	private void loadCategoryList() {
		clearCategory();
		GroupCategoryManager uch = this.appContext.getObject(GroupCategoryManager.class);
		uch.asynGetAllList((info, list) -> {
			setCategoryList(list);
			selectCategory();
		});
	}

	private void setVerifySetting(Info info, GroupJoinSettingEntityCase dc) {
		ClassicsTitleStage stage = getStage();
		if (!info.isSuccess() || null == dc || null == dc.getSetting()) {
			FxUtil.invoke(() -> {
				stage.setCenter(errorPane);
			});
		} else {
			// 验证设置
			GroupJoinSetting setting = dc.getSetting();
			// 问题列表
			List<GroupJoinVerifyQuestion> questions = dc.getQuestions();

			this.joinType = setting.getJoinType();
			if (GroupJoinTypeEnum.any.isEquals(joinType)
					|| GroupJoinTypeEnum.auth.isEquals(joinType)) {
				FxUtil.invoke(() -> {
					pane.setBottomNode(null);
					stage.setCenter(pane);
				});
			} else if (GroupJoinTypeEnum.answer.isEquals(joinType)) {
				questionOneItem.setQuestion(setting.getQuestion());
				questionOneItem.setQuestionId(setting.getGroupId());
				FxUtil.invoke(() -> {
					pane.setBottomNode(questionOnePane);
					stage.setCenter(pane);
				});
			} else if (GroupJoinTypeEnum.confirm.isEquals(joinType)) {
				if (null != questions) {
					for (GroupJoinVerifyQuestion sq : questions) {
						QuestionItem qi = new QuestionItem();
						qi.setQuestion(sq.getQuestion());
						qi.setQuestionId(sq.getId());
						questionItems.add(qi);
					}
				}
				FxUtil.invoke(() -> {
					questionMultiplePane.setQuestionItemList(questionItems);
					pane.setBottomNode(questionMultiplePane);
					stage.setCenter(pane);
				});
			} else if (GroupJoinTypeEnum.invite.isEquals(joinType)
					|| GroupJoinTypeEnum.never.isEquals(joinType)) {
				stage.setCenter(blockedPane);
			}
		}
	}

	private void send() {

		String categoryId = this.pane.getCategoryId();

		if (null == categoryId || "".equals(categoryId)) {
			showPrompt("请选择或者新建分组！");
			return;
		}
		String groupId = this.groupId;
		String remark = pane.getRemark();

		String answer = questionOneItem.getAnswer();
		String question = questionOneItem.getQuestion();

		GroupJoinApplyData apply = new GroupJoinApplyData();
		List<JoinVerifyAnswer> answers = new ArrayList<>();
		if (GroupJoinTypeEnum.confirm.isEquals(joinType)) {
			for (QuestionItem qi : questionItems) {
				if (!qi.verify()) {
					return;
				} else {
					JoinVerifyAnswer sq = new JoinVerifyAnswer();
					sq.setQuestionId(qi.getQuestionId());
					sq.setQuestion(qi.getQuestion());
					sq.setAnswer(qi.getAnswer());
					answers.add(sq);
				}
			}
		}
		PersonalBox pb = this.appContext.getObject(PersonalBox.class);
		String ownerUserId = pb.getOwnerUserId();

		apply.setApplyUserId(ownerUserId);
		apply.setGroupId(groupId);
		apply.setCategoryId(categoryId);
		apply.setAnswer(answer);
		apply.setQuestion(question);
		apply.setRemark(remark);

		GroupJoinHandler ch = this.appContext.getObject(GroupJoinHandler.class);
		ch.joinApply(apply, answers, (info) -> {
			if (info.isSuccess()) {
				setVisible(false);
			} else {
				String text = OnlyMessageUtil.getDefaultErrorText(info);
				showPrompt(text);
			}
		});
	}

	@Override
	protected ClassicsTitleStage createStage() {
		return new ClassicsTitleStage();
	}

	@Override
	protected void initStage(ClassicsTitleStage stage) {
		stage.setWidth(500);
		stage.setHeight(365);

		stage.setMaxWidth(500);
		stage.setMaxHeight(365);

		stage.setResizable(false);

		stage.setTitle("申请加入");
	}

	@Override
	protected void initComponent() {
		blockedPane.setCenter(blockedLabel);
		blockedLabel.setText("不允许加入");

		errorPane.setCenter(errorLabel);
		errorLabel.setText("信息加载失败");

		List<QuestionItem> items = new ArrayList<>();
		items.add(questionOneItem);
		questionOnePane.setQuestionItemList(items);

		questionMultiplePane.setMaxSize(335, 200);
		pane.setMaxSize(500, 340);
	}

	@Override
	protected void initEvent() {
		stage.setDoneAction(a -> {
			doneAction();
		});

		pane.setNewCategoryAction(name -> {
			if (null != name && !"".equals(name)) {
				addCategory(name);
			}
		});
	}
}
