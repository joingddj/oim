
package com.oimchat.client.platform.fx.module.chat.function;

import java.io.File;

import com.oimchat.app.fx.base.component.chat.MessageWriteMapper;
import com.oimchat.app.fx.view.ui.classics.module.chat.ChatPane;
import com.oimchat.client.basic.common.data.im.message.content.Content;
import com.oimchat.client.general.common.task.RunExecutor;
import com.oimchat.client.general.kernel.work.module.core.entity.User;
import com.oimchat.client.platform.common.view.FileUploadView;
import com.oimchat.client.platform.common.view.UserChatHistoryView;
import com.oimchat.client.platform.common.view.operator.UserChatOperator;
import com.oimchat.client.platform.fx.view.common.build.IconButtonBuilder;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.app.view.fx.common.util.FxUtil;
import com.onlyxiahui.app.view.fx.component.icon.font.FontIconButton;
import com.onlyxiahui.common.lib.util.json.JsonUtil;
import com.onlyxiahui.common.message.util.OnlyMessageUtil;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.FileChooser;
import javafx.stage.Window;

/**
 * Description <br>
 * Date 2021-03-27 09:11:07<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ChatPaneUserFunction extends AbstractMaterial {

	protected FileChooser fileChooser;

	public ChatPaneUserFunction(AppContext appContext) {
		super(appContext);
		FxUtil.invoke(() -> {
			initStore();
		});
	}

	private void initStore() {
		fileChooser = new FileChooser();
	}

	public void sendChat(ChatPane cp, User user) {
		MessageWriteMapper writeMapper = cp.getAreaPane().getWritePane().getMapper();
		RunExecutor run = appContext.getObject(RunExecutor.class);
		String messageContent = writeMapper.getMessageContent();
		run.execute(() -> {
			try {
				if (null != messageContent && !messageContent.isEmpty()) {
					FxUtil.invoke(() -> {
						cp.setSendButtonDisable(true);
					});
					String receiveUserId = user.getId();
					Content content = JsonUtil.toObject(messageContent, Content.class);
					UserChatOperator uco = appContext.getObject(UserChatOperator.class);
					uco.send(receiveUserId, content, (info) -> {
						FxUtil.invoke(() -> {
							cp.setSendButtonDisable(false);
						});
						if (!info.isSuccess()) {
							String text = OnlyMessageUtil.getDefaultWarningText(info);
							FxUtil.invoke(() -> {
								cp.getAreaPane().getWritePane().showPrompt(text);
							});
						} else {
							FxUtil.invoke(() -> {
								writeMapper.clear();
							});
						}
					});
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public void sendFile(File f, User user) {
		if (null != f && null != user) {
			FileUploadView fv = this.appContext.getObject(FileUploadView.class);
			fv.upload(f, (fd) -> {
				String receiveUserId = user.getId();
				UserChatOperator uco = appContext.getObject(UserChatOperator.class);
				uco.send(receiveUserId, fd, (info) -> {
				});
			});
		}
	}

	public void sendShake(User user) {
		if (null != user) {
			String receiveUserId = user.getId();
			UserChatOperator uco = appContext.getObject(UserChatOperator.class);
			uco.shake(receiveUserId);
		}
	}

	private void onFile(ChatPane cp, User user) {
		Scene scene = cp.getScene();
		Window w = (null == scene) ? null : scene.getWindow();
		File file = fileChooser.showOpenDialog(w);
		if (null != file) {
			sendFile(file, user);
		}
	}

	public void setToolIcon(ChatPane cp, User user) {

		FontIconButton fileButton = IconButtonBuilder.awesomeSolidIconButton("\uf07b", "发送文件");
		FontIconButton vibrateButton = IconButtonBuilder.awesomeSolidIconButton("\uf0f3", "提醒");
		FontIconButton historyButton = IconButtonBuilder.awesomeSolidIconButton("\uf1da", "历史记录");
		FxUtil.invoke(() -> {
			cp.addMiddleLeftTool(fileButton);
			cp.addMiddleLeftTool(vibrateButton);
			cp.addMiddleRightTool(historyButton);
		});
		FxUtil.invoke(() -> {
			fileButton.setOnAction(a -> {
				onFile(cp, user);
			});
			vibrateButton.setOnAction(a -> {
				sendShake(user);
			});
			historyButton.setOnAction(a -> {
				UserChatHistoryView view = this.appContext.getObject(UserChatHistoryView.class);
				view.setUserId(user.getId());
				view.setVisible(true);
			});
		});
	}

	public void setFileSend(ChatPane cp, User user) {
		cp.setOnFileEventAction((list) -> {
			if (null != list) {
				FileUploadView fv = this.appContext.getObject(FileUploadView.class);
				for (File f : list) {
					fv.upload(f, (fd) -> {
						String receiveUserId = user.getId();
						UserChatOperator uco = appContext.getObject(UserChatOperator.class);
						uco.send(receiveUserId, fd, (info) -> {
						});
					});
				}
			}
		});
	}

	public void setChatSend(ChatPane cp, User user) {
		FxUtil.invoke(() -> {
			cp.setOnSendAction(a -> {
				sendChat(cp, user);
			});
			// 回车发送
			cp.setOnWriteKeyReleased(e -> {
				if (!e.isShiftDown() && e.getCode() == KeyCode.ENTER) {
					e.consume();
					sendChat(cp, user);
				}
			});
		});
	}
}
