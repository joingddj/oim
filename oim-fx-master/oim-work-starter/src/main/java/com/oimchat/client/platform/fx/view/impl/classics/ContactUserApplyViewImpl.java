package com.oimchat.client.platform.fx.view.impl.classics;

import java.util.ArrayList;
import java.util.List;

import com.oimchat.app.fx.view.ui.classics.element.info.BaseInfoPane;
import com.oimchat.app.fx.view.ui.classics.element.question.QuestionItem;
import com.oimchat.app.fx.view.ui.classics.element.question.QuestionPane;
import com.oimchat.app.fx.view.ui.classics.module.add.AddApplyPane;
import com.oimchat.app.fx.view.ui.classics.module.stage.ClassicsTitleStage;
import com.oimchat.client.general.common.util.HereStringUtil;
import com.oimchat.client.general.common.util.OimDateUtil;
import com.oimchat.client.general.kernel.work.module.contact.data.dto.ContactAddApplyData;
import com.oimchat.client.general.kernel.work.module.contact.data.dto.ContactVerifySettingData;
import com.oimchat.client.general.kernel.work.module.contact.data.dto.ContactVerifySettingDataCase;
import com.oimchat.client.general.kernel.work.module.contact.data.dto.VerifyAnswer;
import com.oimchat.client.general.kernel.work.module.contact.data.dto.VerifyQuestion;
import com.oimchat.client.general.kernel.work.module.contact.entity.ContactCategory;
import com.oimchat.client.general.kernel.work.module.contact.handler.ContactCategoryHandler;
import com.oimchat.client.general.kernel.work.module.contact.handler.ContactHandler;
import com.oimchat.client.general.kernel.work.module.contact.manager.ContactCategoryManager;
import com.oimchat.client.general.kernel.work.module.contact.type.ContactVerifyTypeEnum;
import com.oimchat.client.general.kernel.work.module.core.entity.User;
import com.oimchat.client.general.kernel.work.module.core.handler.UserHandler;
import com.oimchat.client.general.kernel.work.module.personal.box.PersonalBox;
import com.oimchat.client.platform.common.view.ContactUserApplyView;
import com.oimchat.client.platform.fx.work.common.handler.UserHeadImageHandler;
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
public class ContactUserApplyViewImpl extends BaseStageView<ClassicsTitleStage> implements ContactUserApplyView {

	AddApplyPane pane = new AddApplyPane();

	BorderPane blockedPane = new BorderPane();
	Label blockedLabel = new Label();

	BorderPane errorPane = new BorderPane();
	Label errorLabel = new Label();

	QuestionPane questionMultiplePane = new QuestionPane();
	List<QuestionItem> questionItems = new ArrayList<>();

	QuestionPane questionOnePane = new QuestionPane();
	QuestionItem questionOneItem = new QuestionItem();

	private String verifyType;

	private String userId;

	public ContactUserApplyViewImpl(AppContext appContext) {
		super(appContext);
		FxUtil.invoke(() -> {
			initStage(getStage());
			initComponent();
			initEvent();
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

		stage.setTitle("添加好友");
	}

	@Override
	protected void initComponent() {
		blockedPane.setCenter(blockedLabel);
		blockedLabel.setText("对方拒绝添加好友");

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

	public void setCategoryList(List<ContactCategory> list) {
		FxUtil.invoke(() -> {
			for (ContactCategory data : list) {
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

	public void setUserData(User user) {
		StringBuilder showText = new StringBuilder();
		if (null != user) {
			// String nickname = userData.getNickname();
			// String account = userData.getAccount();
			String signature = user.getSignature();
			// StringBuilder sb = new StringBuilder();
			String gender = user.getGender();

			showText.append("昵称：");
			showText.append(HereStringUtil.value(user.getNickname()));
			showText.append("\n");

			showText.append("账号：");
			showText.append(HereStringUtil.value(user.getAccount()) + "");
			showText.append("\n");

			String genderText = "保密";
			if ("1".equals(gender)) {
				genderText = "男";
			}
			if ("2".equals(gender)) {
				genderText = "女";
			}

			showText.append("性别：");
			showText.append(genderText);
			showText.append("\n");

			showText.append("年龄：");
			if (null != user.getBirthDate()) {
				int y = OimDateUtil.beforePresentYearCount(user.getBirthDate());
				showText.append(y);
				showText.append("岁");
			}
			showText.append("\n");

			showText.append("故乡：");
			showText.append(user.getHomeAddress());
			showText.append("\n");

			showText.append(signature);
			showText.append("\n");

		}

		BaseInfoPane ip = pane.getInfoPanel();

//		ip.setName(nickname);
//		ip.setNumber(userData.getNumber() + "");
		FxUtil.invoke(() -> {
			pane.clearData();
			ip.setInfoText(showText.toString());
		});
		if (null != user) {
			UserHeadImageHandler him = appContext.getObject(UserHeadImageHandler.class);
			him.loadAvatarImage(user.getHead(), user.getAvatar(), (img) -> {
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
			ContactCategory data = new ContactCategory();
			data.setName(name);

			ContactCategoryHandler uch = this.appContext.getObject(ContactCategoryHandler.class);
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
	public void setUserId(String userId) {
		this.userId = userId;
		FxUtil.invoke(() -> {
			pane.setBottomNode(null);
			stage.setCenter(null);
		});
		loadInfo();
		loadSetting(userId);
		loadCategoryList();
	}

	private void loadInfo() {
		UserHandler uh = this.appContext.getObject(UserHandler.class);
		uh.getById(userId, (info, u) -> {
			setUserData(u);
		});
	}

	private void loadSetting(String userId) {
		FxUtil.invoke(() -> {
			stage.setCenter(null);
			pane.setBottomNode(null);
			questionItems.clear();
			questionMultiplePane.setQuestionItemList(questionItems);
		});
		ContactHandler ch = this.appContext.getObject(ContactHandler.class);
		ch.getContactAddVerifySetting(userId, (info, data) -> {
			setVerifySetting(info, data);
		});
	}

	private void loadCategoryList() {
		clearCategory();
		ContactCategoryManager uch = this.appContext.getObject(ContactCategoryManager.class);
		uch.asynGetAllList((info, list) -> {
			setCategoryList(list);
			selectCategory();
		});
	}

	private void setVerifySetting(Info info, ContactVerifySettingDataCase dc) {
		ClassicsTitleStage stage = getStage();
		if (!info.isSuccess() || null == dc || null == dc.getSetting()) {
			FxUtil.invoke(() -> {
				stage.setCenter(errorPane);
			});
		} else {
			// 验证设置
			ContactVerifySettingData setting = dc.getSetting();
			// 问题列表
			List<VerifyQuestion> questions = dc.getQuestions();

			this.verifyType = setting.getVerifyType();
			if (ContactVerifyTypeEnum.any.isEquals(verifyType)
					|| ContactVerifyTypeEnum.auth.isEquals(verifyType)) {
				FxUtil.invoke(() -> {
					pane.setBottomNode(null);
					stage.setCenter(pane);
				});
			} else if (ContactVerifyTypeEnum.answer.isEquals(verifyType)) {
				questionOneItem.setQuestion(setting.getQuestion());
				questionOneItem.setQuestionId(setting.getUserId());
				FxUtil.invoke(() -> {
					pane.setBottomNode(questionOnePane);
					stage.setCenter(pane);
				});
			} else if (ContactVerifyTypeEnum.confirm.isEquals(verifyType)) {
				if (null != questions) {
					for (VerifyQuestion sq : questions) {
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
			}
		}
	}

	private void send() {

		String categoryId = this.pane.getCategoryId();

		if (null == categoryId || "".equals(categoryId)) {
			showPrompt("请选择或者新建分组！");
			return;
		}
		String userId = this.userId;
		String remark = pane.getRemark();

		String answer = questionOneItem.getAnswer();
		String question = questionOneItem.getQuestion();

		ContactAddApplyData apply = new ContactAddApplyData();
		List<VerifyAnswer> answers = new ArrayList<>();
		if (ContactVerifyTypeEnum.confirm.isEquals(verifyType)) {
			for (QuestionItem qi : questionItems) {
				if (!qi.verify()) {
					return;
				} else {
					VerifyAnswer sq = new VerifyAnswer();
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
		apply.setTargetUserId(userId);
		apply.setCategoryId(categoryId);
		apply.setAnswer(answer);
		apply.setQuestion(question);
		apply.setRemark(remark);

		ContactHandler ch = this.appContext.getObject(ContactHandler.class);
		ch.sendAddApply(apply, answers, (info) -> {
			if (info.isSuccess()) {
				setVisible(false);
			} else {
				String text = OnlyMessageUtil.getDefaultErrorText(info);
				showPrompt(text);
			}
		});
	}
}
