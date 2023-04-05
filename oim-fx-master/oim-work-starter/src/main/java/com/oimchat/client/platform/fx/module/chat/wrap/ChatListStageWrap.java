
package com.oimchat.client.platform.fx.module.chat.wrap;

import com.oimchat.app.fx.base.component.head.HeadCloseItem;
import com.oimchat.app.fx.view.ui.classics.module.chat.ChatListStage;
import com.oimchat.client.common.sync.SyncGet;
import com.oimchat.client.platform.fx.view.common.box.StageBox;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.app.view.fx.common.util.FxUtil;

import javafx.concurrent.Task;
import javafx.scene.Node;

/**
 * Description <br>
 * Date 2021-03-22 15:02:24<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ChatListStageWrap extends AbstractMaterial {

	private ChatListStage stage;
	// 记录收到或者发送抖动信息的时间，为了不过于频繁抖动。
	private long shakeTime = 0;

	public ChatListStageWrap(AppContext appContext) {
		super(appContext);
		initStage();
	}

	private void initStage() {
		ChatListStage stage = getStage();
		stage.getRootPane().getStylesheets().add(this.getClass().getResource("/general/view/theme/classics/css/chat.css").toString());
		StageBox sb = this.appContext.getObject(StageBox.class);
		sb.add(stage);
	}

	public synchronized ChatListStage getStage() {
		if (null == stage) {
			stage = SyncGet.<ChatListStage>syncGet((p) -> {
				FxUtil.invoke(() -> {
					p.put(new ChatListStage());
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
			ChatListStage stage = getStage();
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

	public void addItem(HeadCloseItem item) {
		ChatListStage stage = getStage();
		FxUtil.invoke(() -> {
			stage.addItem(item);
		});
	}

	public boolean hasKey(String key) {
		ChatListStage stage = getStage();
		return stage.has(key);
	}

	public void removeKey(String key) {
		ChatListStage stage = getStage();
		FxUtil.invoke(() -> {
			stage.remove(key);
		});
	}

	public HeadCloseItem getItem(String key) {
		ChatListStage stage = getStage();
		return stage.getItem(key);
	}

	public void selected(String key) {
		ChatListStage stage = getStage();
		FxUtil.invoke(() -> {
			stage.selected(key);
		});
	}

	public boolean isSelected(String key) {
		ChatListStage stage = getStage();
		return stage.isSelected(key);
	}

	public void setMainPnae(Node ndoe) {
		ChatListStage stage = getStage();
		FxUtil.invoke(() -> {
			stage.setChatPane(ndoe);
		});
	}

	public void shake() {
		if (System.currentTimeMillis() - shakeTime < 3000) {
			return;
		}
		Task<Integer> task = new Task<Integer>() {
			@Override
			protected Integer call() throws Exception {
				int iterations = 0;
				for (int i = 0; i < 3; i++) {
					FxUtil.invoke(() -> {
						stage.setX(stage.getX() + 4);
						stage.setY(stage.getY() - 4);
					});

					try {
						Thread.sleep(40);
						FxUtil.invoke(() -> {
							stage.setX(stage.getX() - 8);
							stage.setY(stage.getY());
						});
						Thread.sleep(40);
						FxUtil.invoke(() -> {
							stage.setX(stage.getX());
							stage.setY(stage.getY() + 4);
						});
						Thread.sleep(40);
						FxUtil.invoke(() -> {
							stage.setX(stage.getX() + 4);
							stage.setY(stage.getY());
						});
						Thread.sleep(40);
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
				}
				return iterations;
			}
		};

		Thread th = new Thread(task);
		th.setDaemon(true);
		th.start();
		shakeTime = System.currentTimeMillis();
	}
}
