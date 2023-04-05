package com.oimchat.client.platform.swing.view.impl;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import com.oimchat.app.awt.image.util.AwtImageUtil;
import com.oimchat.app.fx.view.ui.classics.module.main.menu.TrayPopupMenu;
import com.oimchat.app.swing.util.ImageIconLoadUtil;
import com.oimchat.client.general.kernel.work.module.common.store.AuthStore;
import com.oimchat.client.general.unit.SystemUnit;
import com.oimchat.client.platform.common.inform.box.PromptDataBox;
import com.oimchat.client.platform.common.inform.data.PromptData;
import com.oimchat.client.platform.common.view.LoginView;
import com.oimchat.client.platform.common.view.MainView;
import com.oimchat.client.platform.common.view.TrayView;
import com.onlyxiahui.app.basic.view.AbstractMaterialView;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.app.fx.common.component.OnlyTrayIcon;

import javafx.application.Platform;

/**
 * 描述：
 * 
 * @author XiaHui
 * @date 2015年3月16日 下午9:16:16
 * @version 0.0.1
 */
public class TrayViewImpl extends AbstractMaterialView implements TrayView {

	private TrayPopupMenu trayPopupMenu = new TrayPopupMenu();

	private Image logoImage;
	private Image emptyImage;

	private OnlyTrayIcon trayIcon;

	private boolean isShowTempImage = false;
	private boolean isShowTrayImage = true;

	private String key;

	public TrayViewImpl(AppContext appContext) {
		super(appContext);
		initTrayIcon();
		initTray();
		initEvent();
	}

	private void initTrayIcon() {
		logoImage = logoImage();
		emptyImage = emptyImage();
		trayIcon = new OnlyTrayIcon(logoImage(), "OIM");
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				trayPopupMenu = new TrayPopupMenu();
				initPopupMenuEvent();
				trayIcon.setContextMenu(trayPopupMenu);
			}
		});
		trayIcon.addStylesheet(TrayViewImpl.class.getResource("/general/view/theme/classics/css/menu.css").toExternalForm());
		this.showAllMenu(false);
	}

	private void initPopupMenuEvent() {

		trayPopupMenu.setOnExitAction(a -> {
			exit();
		});
		trayPopupMenu.setOnOpenMainAction(a -> {
			showMainFrame();
		});
	}

	private void initEvent() {
		Timer timer = new Timer();
		timer.schedule(new PromptTask(), 1000, 600);
		trayIcon.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				trayMouseClicked(evt);
			}

			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				trayMouseEntered(evt);
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				trayMouseExited(evt);
			}

			@Override
			public void mousePressed(java.awt.event.MouseEvent evt) {
				trayMousePressed(evt);
			}
		});
	}

	private void initTray() {
		try {
			if (SystemTray.isSupported()) {
				SystemTray tray = SystemTray.getSystemTray();
				tray.add(trayIcon);
			}
		} catch (AWTException ex) {
			ex.printStackTrace();
		}
	}

	private void trayMouseClicked(java.awt.event.MouseEvent evt) {
		if (evt.getClickCount() == 2 && evt.getButton() == MouseEvent.BUTTON1) {
			doAction();
		}
	}

	private void trayMouseEntered(java.awt.event.MouseEvent evt) {
		// TODO add your handling code here:
	}

	private void trayMouseExited(java.awt.event.MouseEvent evt) {
		// TODO add your handling code here:
	}

	private void trayMousePressed(java.awt.event.MouseEvent evt) {
		// TODO add your handling code here:
	}

	public void setImage(Image image) {
		trayIcon.setImage(image);
	}

	public void showAllMenu(boolean show) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				trayPopupMenu.showAll(show);
			}
		});
	}

	public void exit() {
		SystemUnit sm = appContext.getObject(SystemUnit.class);
		sm.exit();
	}

	public void changeStatus(String status) {

	}

	public void doAction() {
		if (null != key) {
			PromptDataBox pdb = appContext.getObject(PromptDataBox.class);
			pdb.execute(key);
			key = null;
		} else {
			showMainFrame();
		}
	}

	public void showMainFrame() {
		AuthStore sm = appContext.getObject(AuthStore.class);
		if (sm.isAuth()) {
			MainView mainView = appContext.getObject(MainView.class);
			mainView.setVisible(true);
		} else {
			LoginView loginView = appContext.getObject(LoginView.class);
			loginView.setVisible(true);
		}
	}

	class PromptTask extends TimerTask {
		Image showImage = null;

		@Override
		public void run() {
			try {
				if (key == null) {
					PromptDataBox pdb = appContext.getObject(PromptDataBox.class);
					PromptData pd = pdb.getPromptData();
					if (null != pd) {
						key = pd.getKey();
						URL url = pd.getIconUrl();
						if (null == url) {
							showImage = logoImage;
						} else {
							try {
								showImage = AwtImageUtil.getRoundedCornerImageByUrl(url, 16, 16, 16, 16);
							} catch (Exception e) {
								e.printStackTrace();
								// TODO: handle exception
								showImage = logoImage;
							}
							if (null == showImage) {
								showImage = logoImage;
							}
						}
					}
				}
				if (key != null) {
					PromptDataBox pdb = appContext.getObject(PromptDataBox.class);
					if (null == pdb.getPromptData(key)) {
						showImage = null;
						key = null;
					}
				} else {
					showImage = null;
				}

				if (showImage != null) {
					if (isShowTempImage) {
						isShowTempImage = false;
						trayIcon.setImage(showImage);
					} else {
						isShowTempImage = true;
						trayIcon.setImage(emptyImage);
					}
					isShowTrayImage = false;
				} else {
					if (!isShowTrayImage) {
						isShowTrayImage = true;
						trayIcon.setImage(logoImage);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void setVisible(boolean visible) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean isShowing() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void showPrompt(String text) {
		// TODO Auto-generated method stub
	}

	public Image logoImage() {
		return ImageIconLoadUtil.getByClassPath("/general/view/common/images/tray/logo_16.png").getImage();
	}

	public Image emptyImage() {
		return ImageIconLoadUtil.getByClassPath("/general/view/common/images/tray/empty.png").getImage();
	}

	class PromptDataWrap {
		Image showImage;
		PromptData PromptData;
	}

	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				System.out.println(1111);
			}

		}, 1000, 800);
	}
}
