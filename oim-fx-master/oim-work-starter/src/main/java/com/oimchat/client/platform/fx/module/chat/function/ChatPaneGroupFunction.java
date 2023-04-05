
package com.oimchat.client.platform.fx.module.chat.function;

import java.io.File;

import com.oimchat.app.fx.base.component.chat.MessageWriteMapper;
import com.oimchat.app.fx.view.ui.classics.module.chat.ChatPane;
import com.oimchat.client.basic.common.data.im.message.content.Content;
import com.oimchat.client.general.common.task.RunExecutor;
import com.oimchat.client.general.kernel.work.module.group.entity.Group;
import com.oimchat.client.platform.common.view.FileUploadView;
import com.oimchat.client.platform.common.view.UserChatHistoryView;
import com.oimchat.client.platform.common.view.operator.GroupChatOperator;
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

public class ChatPaneGroupFunction extends AbstractMaterial {

	protected FileChooser fileChooser;

	public ChatPaneGroupFunction(AppContext appContext) {
		super(appContext);
		FxUtil.invoke(() -> {
			initStore();
		});
	}

	private void initStore() {
		fileChooser = new FileChooser();
	}

	public void sendChat(ChatPane cp, Group group) {
		MessageWriteMapper writeMapper = cp.getAreaPane().getWritePane().getMapper();
		RunExecutor run = appContext.getObject(RunExecutor.class);
		String messageContent = writeMapper.getMessageContent();
		run.execute(() -> {
			try {
				if (null != messageContent && !messageContent.isEmpty()) {
					FxUtil.invoke(() -> {
						cp.setSendButtonDisable(true);
					});
					String groupId = group.getId();
					Content content = JsonUtil.toObject(messageContent, Content.class);
					GroupChatOperator uco = appContext.getObject(GroupChatOperator.class);
					uco.send(groupId, content, (info) -> {
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

	public void sendFile(File f, Group group) {
		if (null != f && null != group) {
			FileUploadView fv = this.appContext.getObject(FileUploadView.class);
			fv.upload(f, (fd) -> {
				String groupId = group.getId();
				GroupChatOperator uco = appContext.getObject(GroupChatOperator.class);
				uco.send(groupId, fd, (info) -> {
				});
			});
		}
	}

	private void onFile(ChatPane cp, Group group) {
		Scene scene = cp.getScene();
		Window w = (null == scene) ? null : scene.getWindow();
		File file = fileChooser.showOpenDialog(w);
		if (null != file) {
			sendFile(file, group);
		}
	}

	public void setToolIcon(ChatPane cp, Group group) {

		FontIconButton fileButton = IconButtonBuilder.awesomeSolidIconButton("\uf07b", "发送文件");
		FontIconButton historyButton = IconButtonBuilder.awesomeSolidIconButton("\uf1da", "历史记录");
		FxUtil.invoke(() -> {
			cp.addMiddleLeftTool(fileButton);
			cp.addMiddleRightTool(historyButton);
		});
		FxUtil.invoke(() -> {
			fileButton.setOnAction(a -> {
				onFile(cp, group);
			});
			historyButton.setOnAction(a -> {
				UserChatHistoryView view = this.appContext.getObject(UserChatHistoryView.class);
				view.setUserId(group.getId());
				view.setVisible(true);
			});
		});
	}

	public void setFileSend(ChatPane cp, Group group) {
		cp.setOnFileEventAction((list) -> {
			if (null != list) {
				FileUploadView fv = this.appContext.getObject(FileUploadView.class);
				for (File f : list) {
					fv.upload(f, (fd) -> {
						String groupId = group.getId();
						GroupChatOperator uco = appContext.getObject(GroupChatOperator.class);
						uco.send(groupId, fd, (info) -> {
						});
					});
				}
			}
		});
	}

	public void setChatSend(ChatPane cp, Group group) {
		FxUtil.invoke(() -> {
			cp.setOnSendAction(a -> {
				sendChat(cp, group);
			});
			// 回车发送
			cp.setOnWriteKeyReleased(e -> {
				if (!e.isShiftDown() && e.getCode() == KeyCode.ENTER) {
					e.consume();
					sendChat(cp, group);
				}
			});
		});
	}
}
