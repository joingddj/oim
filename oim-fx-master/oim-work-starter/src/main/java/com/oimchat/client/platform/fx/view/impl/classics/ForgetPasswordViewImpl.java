package com.oimchat.client.platform.fx.view.impl.classics;

import java.util.ArrayList;
import java.util.List;

import com.oimchat.app.fx.view.ui.classics.element.question.QuestionItem;
import com.oimchat.app.fx.view.ui.classics.module.forget.AccountPane;
import com.oimchat.app.fx.view.ui.classics.module.forget.ForgetPasswordStage;
import com.oimchat.app.fx.view.ui.classics.module.forget.PasswordResetPane;
import com.oimchat.client.general.kernel.work.module.account.call.AccountCall;
import com.oimchat.client.general.kernel.work.module.account.data.dto.SecurityQuestion;
import com.oimchat.client.general.kernel.work.module.server.handler.ServerHandler;
import com.oimchat.client.platform.common.view.ForgetPasswordView;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.app.view.fx.common.util.FxUtil;
import com.onlyxiahui.common.message.bean.Info;
import com.onlyxiahui.common.message.util.OnlyMessageUtil;
import com.onlyxiahui.common.utils.base.security.Md5Util;

import javafx.application.Platform;

/**
 * @author: XiaHui
 * @date: 2017年4月11日 下午12:07:41
 */
public class ForgetPasswordViewImpl extends BaseStageView<ForgetPasswordStage> implements ForgetPasswordView {

	AccountPane ap;// = new AccountPanel();
	PasswordResetPane qp;// = new QuestionPanel();
	List<QuestionItem> itemList = new ArrayList<QuestionItem>();
	String userId;

	public ForgetPasswordViewImpl(AppContext appContext) {
		super(appContext);
		FxUtil.invoke(() -> {
			initStage(getStage());
			initComponent();
			initEvent();
		});
	}

	@Override
	public void setIndex(int index) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				stage.setCenterNode(ap);
			}
		});
	}

	private void get() {
		boolean verify = ap.verify();
		if (verify) {
			String account = ap.getAccount();
			AccountCall call = this.appContext.getObject(AccountCall.class);
			this.execute(() -> {
				ServerHandler sh = appContext.getObject(ServerHandler.class);
				sh.load((i) -> {
					if (i.isSuccess()) {

						call.getSecurityQuestionList(account, (info, qd) -> {
							handle(info, qd.getUserId(), qd.getItems());
						});
					} else {
						String text = OnlyMessageUtil.getDefaultErrorText(i);
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								showPrompt(text);
							}
						});
					}
				});
			});
		}
	}

	protected void handle(Info info, String userId, List<SecurityQuestion> list) {
		if (info.isSuccess()) {
			this.userId = userId;
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					itemList.clear();
					for (SecurityQuestion sq : list) {
						QuestionItem qi = new QuestionItem();
						qi.setQuestion(sq.getQuestion());
						qi.setQuestionId(sq.getId());
						itemList.add(qi);
					}
					qp.setQuestionItemList(itemList);
					qp.setDoneButtonVisible(true);
					stage.setCenterNode(qp);
				}
			});
		} else {
			String text = OnlyMessageUtil.getDefaultErrorText(info);
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					showPrompt(text);
					qp.setDoneButtonVisible(false);
				}
			});
		}
	}

	private void updatePassword() {
		boolean verify = qp.verify();
		if (verify) {
			if (null != userId) {
				String password = qp.getPassword();

				List<SecurityQuestion> list = new ArrayList<SecurityQuestion>();
				for (QuestionItem qi : itemList) {
					if (!qi.verify()) {
						return;
					} else {
						SecurityQuestion sq = new SecurityQuestion();
						sq.setId(qi.getQuestionId());
						sq.setAnswer(qi.getAnswer());
						list.add(sq);
					}
				}
				qp.setDoneButtonVisible(false);
				AccountCall call = this.appContext.getObject(AccountCall.class);
				this.execute(() -> {
					ServerHandler sh = appContext.getObject(ServerHandler.class);
					sh.load((i) -> {
						if (i.isSuccess()) {
							String p = Md5Util.lower32(password);
							call.updatePassword(userId, p, list, (info) -> {
								handle(info);
							});
						} else {
							qp.setDoneButtonVisible(true);
							String text = OnlyMessageUtil.getDefaultErrorText(i);
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									showPrompt(text);
								}
							});
						}
					});
				});
			} else {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						showPrompt("获取账号信息失败！");
					}
				});
			}
		}
	}

	protected void handle(Info info) {
		if (info.isSuccess()) {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					showPrompt("修改成功。");
					qp.setDoneButtonVisible(false);
				}
			});
		} else {
			String text = OnlyMessageUtil.getDefaultErrorText(info);
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					showPrompt(text);
					qp.setDoneButtonVisible(true);
				}
			});
		}
	}

	public void showPrompt(String text) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				stage.showWaitPrompt(text);
			}
		});
	}

	@Override
	protected ForgetPasswordStage createStage() {
		return new ForgetPasswordStage();
	}

	@Override
	protected void initStage(ForgetPasswordStage stage) {
		ap = new AccountPane();
		qp = new PasswordResetPane();
	}

	@Override
	protected void initComponent() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initEvent() {
		ap.setOnNextAction(a -> {
			qp.setDoneButtonVisible(false);
			get();
		});

		qp.setOnUpAction(a -> {
			stage.setCenterNode(ap);
		});

		qp.setOnDoneAction(a -> {
			updatePassword();
		});
	}
}
