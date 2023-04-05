package com.oimchat.client.platform.fx.view.impl.classics;

import java.util.ArrayList;
import java.util.List;

import com.oimchat.app.fx.view.ui.classics.element.question.QuestionItem;
import com.oimchat.app.fx.view.ui.classics.element.question.QuestionPane;
import com.oimchat.app.fx.view.ui.classics.module.setting.ContactHarassSettingPane;
import com.oimchat.app.fx.view.ui.classics.module.setting.LoginSettingPane;
import com.oimchat.app.fx.view.ui.classics.module.setting.MainSettingPane;
import com.oimchat.app.fx.view.ui.classics.module.stage.ClassicsTitleStage;
import com.oimchat.client.general.kernel.work.module.contact.entity.ContactAddVerifyQuestion;
import com.oimchat.client.general.kernel.work.module.contact.entity.ContactHarassSetting;
import com.oimchat.client.general.kernel.work.module.contact.handler.ContactHarassSettingHandler;
import com.oimchat.client.general.kernel.work.module.contact.type.ContactVerifyTypeEnum;
import com.oimchat.client.general.kernel.work.module.core.entity.User;
import com.oimchat.client.general.kernel.work.module.personal.box.PersonalBox;
import com.oimchat.client.platform.common.config.login.LoginConfigBox;
import com.oimchat.client.platform.common.config.login.data.LoginSaveData;
import com.oimchat.client.platform.common.config.login.data.LoginSettingData;
import com.oimchat.client.platform.common.view.MainSettingView;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.app.view.fx.common.util.FxUtil;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

/**
 * @author XiaHui
 * @date 2017-11-24 21:46:45
 */
public class MainSettingViewImpl extends BaseStageView<ClassicsTitleStage> implements MainSettingView {
	MainSettingPane settingPane = new MainSettingPane();

	LoginSettingPane loginSettingPane = new LoginSettingPane();

	ContactHarassSettingPane contactHarassSettingPane = new ContactHarassSettingPane();

	QuestionPane contactHarassQuestionMultiplePane = new QuestionPane();
	List<QuestionItem> contactHarassQuestionItems = new ArrayList<>();

	QuestionPane contactHarassQuestionOnePane = new QuestionPane();
	QuestionItem contactHarassQuestionOneItem = new QuestionItem();

	public MainSettingViewImpl(AppContext appContext) {
		super(appContext);
		FxUtil.invoke(() -> {
			initStage(getStage());
			initComponent();
			initEvent();
		});
	}

	protected void initLoginConfig() {
		PersonalBox pb = appContext.getObject(PersonalBox.class);
		User user = pb.getUser();
		String account = user.getAccount();

		LoginConfigBox lcb = this.appContext.getObject(LoginConfigBox.class);
		LoginSaveData lsd = lcb.get(account);

		if (null == lsd) {
			lsd = new LoginSaveData();
			lsd.getUser().setAccount(account);
		}
		LoginSettingData ls = lsd.getSetting();
		if (ls == null) {
			ls = new LoginSettingData();
			lsd.setSetting(ls);
		}
		boolean autoLogin = ls.isAutoLogin();
		FxUtil.invoke(() -> {
			loginSettingPane.setAutoLogin(autoLogin);
		});
	}

	void loadContactHarassSetting() {

		contactHarassQuestionItems.clear();
		FxUtil.invoke(() -> {
			contactHarassQuestionMultiplePane.setQuestionItemList(contactHarassQuestionItems);
		});

		ContactHarassSettingHandler chsh = this.appContext.getObject(ContactHarassSettingHandler.class);
		chsh.get((info, v) -> {
			if (null != v) {
				ContactHarassSetting hs = v.getSetting();
				String verifyType = hs.getVerifyType();
				String question = hs.getQuestion();
				String answer = hs.getAnswer();

				List<ContactAddVerifyQuestion> questions = v.getQuestions();
				if (null != questions) {
					for (ContactAddVerifyQuestion sq : questions) {
						QuestionItem qi = new QuestionItem();
						qi.setVerifyQuestion(true);
						qi.setVerifyAnswer(false);
						qi.setAnswerVisible(false);
						qi.setQuestionEditable(true);
						qi.setQuestion(sq.getQuestion());
						qi.setQuestionId(sq.getId());
						contactHarassQuestionItems.add(qi);

						Button db = new Button("-删除");
						qi.setRight(db);

						db.setOnAction(da -> {
							contactHarassQuestionItems.remove(qi);
							contactHarassQuestionMultiplePane.setQuestionItemList(contactHarassQuestionItems);
						});
					}
				}
				FxUtil.invoke(() -> {
					contactHarassQuestionOneItem.setQuestion(question);
					contactHarassQuestionOneItem.setAnswer(answer);

					contactHarassSettingPane.setSelected(verifyType);
					contactHarassQuestionMultiplePane.setQuestionItemList(contactHarassQuestionItems);
				});
			}
		});
	}

	public void save() {

		String verifyType = contactHarassSettingPane.getSelected();
		String answer = contactHarassQuestionOneItem.getAnswer();
		String question = contactHarassQuestionOneItem.getQuestion();

		ContactHarassSetting setting = new ContactHarassSetting();

		setting.setVerifyType(verifyType);
		setting.setQuestion(question);
		setting.setAnswer(answer);

		List<ContactAddVerifyQuestion> questions = new ArrayList<>();
		if (ContactVerifyTypeEnum.confirm.isEquals(verifyType)) {
			for (QuestionItem qi : contactHarassQuestionItems) {
				if (!qi.verify()) {
					return;
				} else {
					ContactAddVerifyQuestion sq = new ContactAddVerifyQuestion();
					sq.setQuestion(qi.getQuestion());
					sq.setId(qi.getQuestionId());
					questions.add(sq);
				}
			}
		}

		ContactHarassSettingHandler chsh = this.appContext.getObject(ContactHarassSettingHandler.class);
		chsh.update(setting, questions, (info) -> {
		});

		this.setVisible(false);
	}

	@Override
	public void initializeData() {
		this.execute(() -> {
			initLoginConfig();
			loadContactHarassSetting();
		});
	}

	@Override
	protected ClassicsTitleStage createStage() {
		return new ClassicsTitleStage();
	}

	@Override
	protected void initStage(ClassicsTitleStage stage) {
		stage.setResizable(false);
		stage.setTitlePaneStyle(2);
		stage.setWidth(560);
		stage.setHeight(420);
		stage.setTitle("系统设置");
		stage.setCenter(settingPane);
	}

	@Override
	protected void initComponent() {

		settingPane.addTab("登录设置", loginSettingPane);
		settingPane.addTab("防骚扰设置", contactHarassSettingPane);

		contactHarassQuestionOneItem.setQuestionEditable(true);
		List<QuestionItem> items = new ArrayList<>();
		items.add(contactHarassQuestionOneItem);
		contactHarassQuestionOnePane.setQuestionItemList(items);

		Button button = new Button("添加问题");
		BorderPane contactHarassQuestionPane = new BorderPane();
		contactHarassQuestionPane.setTop(button);
		contactHarassQuestionPane.setCenter(contactHarassQuestionMultiplePane);

		button.setOnAction(a -> {
			if (contactHarassQuestionItems.size() < 3) {

				QuestionItem qi = new QuestionItem();
				qi.setVerifyQuestion(true);
				qi.setVerifyAnswer(false);
				qi.setAnswerVisible(false);
				qi.setQuestionEditable(true);
				contactHarassQuestionItems.add(qi);

				Button db = new Button("-删除");
				qi.setRight(db);

				db.setOnAction(da -> {
					contactHarassQuestionItems.remove(qi);
					contactHarassQuestionMultiplePane.setQuestionItemList(contactHarassQuestionItems);
				});
				contactHarassQuestionMultiplePane.setQuestionItemList(contactHarassQuestionItems);
			}
		});

		contactHarassSettingPane.addTab(ContactVerifyTypeEnum.any.getCode(), ContactVerifyTypeEnum.any.getText(), null);
		contactHarassSettingPane.addTab(ContactVerifyTypeEnum.auth.getCode(), ContactVerifyTypeEnum.auth.getText(), null);
		contactHarassSettingPane.addTab(ContactVerifyTypeEnum.answer.getCode(), ContactVerifyTypeEnum.answer.getText(), contactHarassQuestionOnePane);
		contactHarassSettingPane.addTab(ContactVerifyTypeEnum.confirm.getCode(), ContactVerifyTypeEnum.confirm.getText(), contactHarassQuestionPane);
	}

	@Override
	protected void initEvent() {
		loginSettingPane.setOnAutoLoginAction(a -> {
			boolean autoLogin = loginSettingPane.isAutoLogin();

//			LoginUserStore lus = appContext.getObject(LoginUserStore.class);
//			lus.getLoginUser();

			PersonalBox pb = appContext.getObject(PersonalBox.class);
			User user = pb.getUser();
			String account = user.getAccount();

			LoginConfigBox lcb = this.appContext.getObject(LoginConfigBox.class);
			LoginSaveData lsd = lcb.get(account);

			if (null == lsd) {
				lsd = new LoginSaveData();
				lsd.getUser().setAccount(account);
			}
			LoginSettingData ls = lsd.getSetting();
			if (ls == null) {
				ls = new LoginSettingData();
				lsd.setSetting(ls);
			}
			ls.setAutoLogin(autoLogin);
			lcb.put(account, lsd);
		});

		ClassicsTitleStage stage = getStage();
		stage.setDoneAction(a -> {
			save();
		});
	}
}
