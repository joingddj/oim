
package com.oimchat.client.platform.fx.module.main.wrap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oimchat.app.fx.base.component.choose.ChooseGroup;
import com.oimchat.app.fx.base.component.head.HeadItem;
import com.oimchat.app.fx.base.component.icon.IconPane;
import com.oimchat.app.fx.view.ui.classics.element.icon.ToolIconButton;
import com.oimchat.app.fx.view.ui.classics.element.list.ListTitleNodePane;
import com.oimchat.app.fx.view.ui.classics.module.main.MainPane;
import com.oimchat.app.fx.view.ui.classics.module.main.menu.MainPopupMenu;
import com.oimchat.app.fx.view.ui.classics.module.main.menu.StatusPopupMenu;
import com.oimchat.client.basic.util.ListConvertUtil;
import com.oimchat.client.general.common.task.RunExecutor;
import com.oimchat.client.general.kernel.work.module.common.util.GroupInfoUtil;
import com.oimchat.client.general.kernel.work.module.common.util.UserInfoUtil;
import com.oimchat.client.general.kernel.work.module.contact.entity.ContactRelation;
import com.oimchat.client.general.kernel.work.module.contact.handler.ContactRelationHandler;
import com.oimchat.client.general.kernel.work.module.core.data.query.UserQuery;
import com.oimchat.client.general.kernel.work.module.core.entity.User;
import com.oimchat.client.general.kernel.work.module.core.handler.UserHandler;
import com.oimchat.client.general.kernel.work.module.core.type.UserStatusTypeEnum;
import com.oimchat.client.general.kernel.work.module.group.data.query.GroupQuery;
import com.oimchat.client.general.kernel.work.module.group.entity.Group;
import com.oimchat.client.general.kernel.work.module.group.entity.GroupRelation;
import com.oimchat.client.general.kernel.work.module.group.handler.GroupHandler;
import com.oimchat.client.general.kernel.work.module.group.handler.GroupRelationHandler;
import com.oimchat.client.general.kernel.work.module.personal.box.PersonalBox;
import com.oimchat.client.general.kernel.work.module.personal.controller.PersonalController;
import com.oimchat.client.general.kernel.work.module.personal.handler.PersonalHandler;
import com.oimchat.client.general.kernel.work.module.personal.observer.PersonalObservable;
import com.oimchat.client.general.kernel.work.module.personal.observer.PersonalStatusObservable;
import com.oimchat.client.general.kernel.work.module.personal.util.PersonalUtil;
import com.oimchat.client.general.unit.SystemUnit;
import com.oimchat.client.platform.common.view.ContactUserApplyView;
import com.oimchat.client.platform.common.view.FindView;
import com.oimchat.client.platform.common.view.GroupDataView;
import com.oimchat.client.platform.common.view.GroupJoinApplyView;
import com.oimchat.client.platform.common.view.MainSettingView;
import com.oimchat.client.platform.common.view.PersonalEditView;
import com.oimchat.client.platform.common.view.UpdatePasswordView;
import com.oimchat.client.platform.common.view.UserDataView;
import com.oimchat.client.platform.fx.module.chat.interaction.UserChatInteraction;
import com.oimchat.client.platform.fx.module.list.interaction.GeneralApplyMessageInteraction;
import com.oimchat.client.platform.fx.module.list.store.type.GeneralApplyType;
import com.oimchat.client.platform.fx.view.common.box.UserStatusImageBox;
import com.oimchat.client.platform.fx.work.common.handler.GroupHeadImageHandler;
import com.oimchat.client.platform.fx.work.common.handler.UserHeadImageHandler;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.app.view.fx.common.util.FxUtil;
import com.onlyxiahui.app.view.fx.common.util.ImageLoadUtil;
import com.onlyxiahui.common.data.common.data.Page;
import com.onlyxiahui.common.utils.base.lang.string.StringUtil;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * Description <br>
 * Date 2021-03-17 15:12:57<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class MainPaneViewWrap extends AbstractMaterial {

	protected final MainPane mainPane = new MainPane();
	protected ListTitleNodePane findUserListPane = new ListTitleNodePane();
	protected ListTitleNodePane findContactListPane = new ListTitleNodePane();
	protected ListTitleNodePane findGroupListPane = new ListTitleNodePane();
	protected ListTitleNodePane findJoinGroupListPane = new ListTitleNodePane();
	protected final ChooseGroup chooseGroup = new ChooseGroup();
	protected StatusPopupMenu statusPopupMenu = new StatusPopupMenu();
	protected MainPopupMenu mainPopupMenu = new MainPopupMenu();

	public MainPaneViewWrap(AppContext appContext) {
		super(appContext);
		initConfig();
		FxUtil.invoke(() -> {
			initUi();
		});
		initEvent();
	}

	private void initConfig() {
		PersonalObservable pl = this.appContext.getObject(PersonalObservable.class);
		pl.addListener((user) -> {
			setPersonal(user);
		});
		PersonalStatusObservable psl = this.appContext.getObject(PersonalStatusObservable.class);
		psl.addListener((status) -> {
			updateStatus(status);
		});

		findContactListPane.setText("查到的联系人");
		findJoinGroupListPane.setText("查到已加入的群");

		findUserListPane.setText("查到的用户");
		findGroupListPane.setText("查到的群");
		mainPane.getFindListPane().addNode(findContactListPane);
		mainPane.getFindListPane().addNode(findJoinGroupListPane);
		mainPane.getFindListPane().addNode(findUserListPane);
		mainPane.getFindListPane().addNode(findGroupListPane);

		Image normalImage = ImageLoadUtil.getImageByClassPath("/general/resources/classics/images/main/bottom/mainmenu.png");
		Image hoverImage = ImageLoadUtil.getImageByClassPath("/general/resources/classics/images/main/bottom/mainmenu.png");
		Image pressedImage = ImageLoadUtil.getImageByClassPath("/general/resources/classics/images/main/bottom/mainmenu.png");

		IconPane menuIconButton = new IconPane(normalImage, hoverImage, pressedImage);

		normalImage = ImageLoadUtil.getImageByClassPath("/general/resources/classics/images/main/bottom/find.png");
		hoverImage = ImageLoadUtil.getImageByClassPath("/general/resources/classics/images/main/bottom/find_hover.png");
		pressedImage = ImageLoadUtil.getImageByClassPath("/general/resources/classics/images/main/bottom/find_down.png");

		IconPane findIconButton = new IconPane("查找", normalImage, hoverImage, pressedImage);

		normalImage = ImageLoadUtil.getImageByClassPath("/general/resources/classics/images/main/bottom/tools.png");
		hoverImage = ImageLoadUtil.getImageByClassPath("/general/resources/classics/images/main/bottom/tools_hover.png");
		pressedImage = ImageLoadUtil.getImageByClassPath("/general/resources/classics/images/main/bottom/tools_down.png");

		IconPane settingIconButton = new IconPane(normalImage, hoverImage, pressedImage);

		normalImage = ImageLoadUtil.getImageByClassPath("/general/resources/classics/images/main/bottom/message_new.png");
		hoverImage = ImageLoadUtil.getImageByClassPath("/general/resources/classics/images/main/bottom/message_new_highlight.png");
		pressedImage = ImageLoadUtil.getImageByClassPath("/general/resources/classics/images/main/bottom/message_new_down.png");

		IconPane infoIconButton = new IconPane(normalImage, hoverImage, pressedImage);

		menuIconButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				Object source = event.getSource();
				if (source instanceof Node) {
					Node node = (Node) source;
					mainPopupMenu.show(node, Side.TOP, node.getLayoutX(), node.getLayoutY());
				}
			}
		});

		findIconButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				FindView findView = appContext.getObject(FindView.class);
				findView.setVisible(true);
			}
		});

		settingIconButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				MainSettingView view = appContext.getObject(MainSettingView.class);
				view.initializeData();
				view.setVisible(true);
			}
		});

		infoIconButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				GeneralApplyMessageInteraction apply = appContext.getObject(GeneralApplyMessageInteraction.class);
				apply.show(GeneralApplyType.ContactAddApply);
			}
		});

		mainPane.addFunctionIcon(menuIconButton);
		mainPane.addFunctionIcon(findIconButton);
		mainPane.addFunctionIcon(settingIconButton);
		mainPane.addFunctionIcon(infoIconButton);
	}

	private void initUi() {
		for (UserStatusTypeEnum t : UserStatusTypeEnum.values()) {
			statusPopupMenu.addItem(
					t.getCode(),
					t.getText(),
					UserStatusImageBox.getStatusImageIcon(t.getCode()));
		}
	}

	private void initEvent() {
		TextField textField = mainPane.getFindTextField();
		textField.textProperty().addListener((observable, oldValue, newValue) -> {
			String text = textField.getText();
			find(text);
		});

		mainPane.setOnStatusMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				Object source = event.getSource();
				if (source instanceof Node) {
					Node node = (Node) source;
					statusPopupMenu.show(node, Side.BOTTOM, node.getLayoutX(), node.getLayoutY());
				}
			}
		});
		mainPane.setOnHeadMouseClicked(m -> {
			PersonalBox pb = appContext.getObject(PersonalBox.class);
			User user = pb.getUser();
			UserDataView v = appContext.getObject(UserDataView.class);
			v.showUserId(user.getId());
			v.setVisible(true);
		});
		statusPopupMenu.setStatusAction((status) -> {
			sendStatus(status);
		});

		mainPopupMenu.setQuitAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				SystemUnit sm = appContext.getObject(SystemUnit.class);
				sm.exit();
			}
		});
		mainPopupMenu.setUpdatePasswordAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				UpdatePasswordView upv = appContext.getObject(UpdatePasswordView.class);
				upv.setVisible(true);
			}
		});

		mainPopupMenu.setUpdateAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				PersonalEditView view = appContext.getObject(PersonalEditView.class);
				view.initializeData();
				view.setVisible(true);
			}
		});
	}

	public MainPane getMainPane() {
		return mainPane;
	}

	public void initializeData() {
		PersonalHandler ph = this.appContext.getObject(PersonalHandler.class);
		ph.get((info, user) -> {
			setPersonal(user);
		});
	}

	public void setPersonal(User user) {
		if (null != user) {
			UserHeadImageHandler uhim = this.appContext.getObject(UserHeadImageHandler.class);

			String name = PersonalUtil.getShowName(user);
			Image statusImage = UserStatusImageBox.getStatusImageIcon(user.getStatus());

			FxUtil.invoke(() -> {
				mainPane.setStatusImage(statusImage);
				mainPane.setNickname(name);
				mainPane.setText(user.getSignature());
			});
			uhim.loadAvatarImage(user.getHead(), user.getAvatar(), (img) -> {

				FxUtil.invoke(() -> {
					mainPane.setHeadImage(img);
				});
			});
		}
	}

	public void sendStatus(String status) {
		PersonalController pc = appContext.getObject(PersonalController.class);
		pc.updateStatus(status, (info) -> {
		});
	}

	public void updateSignature(String signature) {
		FxUtil.invoke(() -> {
			mainPane.setText(signature);
		});
	}

	public void updateStatus(String status) {
		Image statusImage = UserStatusImageBox.getStatusImageIcon(status);
		FxUtil.invoke(() -> {
			mainPane.setStatusImage(statusImage);
		});
	}

	private void find(String text) {
		if (null == text || text.trim().isEmpty()) {
			return;
		}
		UserHandler uh = this.appContext.getObject(UserHandler.class);
		GroupHandler gh = this.appContext.getObject(GroupHandler.class);
		ContactRelationHandler crh = this.appContext.getObject(ContactRelationHandler.class);
		GroupRelationHandler grh = this.appContext.getObject(GroupRelationHandler.class);

		RunExecutor run = appContext.getObject(RunExecutor.class);
		run.execute(() -> {
			FxUtil.invoke(() -> {
				findContactListPane.clearItem();
				findJoinGroupListPane.clearItem();

				findUserListPane.clearItem();
				findGroupListPane.clearItem();
			});
			UserQuery uq = new UserQuery();
			uq.setQueryText(text);
			uh.list(uq, new Page(), (ui, pr) -> {

				if (null != pr && null != pr.getItems()) {
					List<User> items = pr.getItems();
					List<String> ids = ListConvertUtil.convert(items, i -> i.getId());
					crh.getByContactUserIds(ids, (info, lb) -> {
						Map<String, ContactRelation> map = new HashMap<>();
						if (null != lb && null != lb.getItems()) {
							lb.getItems().forEach((item) -> {
								map.put(item.getContactUserId(), item);
							});
						}

						for (User user : items) {
							ContactRelation data = map.get(user.getId());
							if (null == data) {
								addFindUser(user);
							} else {
								addFindContact(data, user);
							}
						}
					});
				}
			});

			GroupQuery gq = new GroupQuery();
			gq.setQueryText(text);

			gh.list(gq, new Page(), (gi, pr) -> {
				if (null != pr && null != pr.getItems()) {
					List<Group> items = pr.getItems();
					List<String> ids = ListConvertUtil.convert(items, i -> i.getId());
					grh.getListByGroupIds(ids, (info, lb) -> {
						Map<String, GroupRelation> map = new HashMap<>();
						if (null != lb && null != lb.getItems()) {
							lb.getItems().forEach((item) -> {
								map.put(item.getGroupId(), item);
							});
//							Map<String, GroupRelation> vm = lb
//									.getItems()
//									.stream()
//									.collect(Collectors.toMap(GroupRelation::getGroupId, v -> v));
						}

						for (Group group : items) {
							GroupRelation data = map.get(group.getId());
							if (null == data) {
								addFindGroup(group);
							} else {
								addFindJoinGroup(data, group);
							}
						}
					});
				}
			});
		});
	}

	public void addFindUser(User user) {
		if (null != user) {
			UserHeadImageHandler uhim = this.appContext.getObject(UserHeadImageHandler.class);

			String remark = null;

			boolean hasRemark = StringUtil.isNotBlank(remark);

			String status = user.getStatus();
			boolean gray = UserInfoUtil.isOffline(status);
			String signature = user.getSignature();

			HeadItem item = new HeadItem();
			FxUtil.invoke(() -> {

				String showRemark = "";
				String showName = "";

				if (hasRemark) {
					String name = UserInfoUtil.getShowName(user);
					showRemark = remark;
					showName = "(" + name + ")";
				} else {
					String name = UserInfoUtil.getShowName(user);
					showRemark = name;
					showName = "";
				}

				item.setRemark(showRemark);
				item.setNickname(showName);
				// item.setStatus(status);
				item.setShowText(signature);

				item.setGray(gray);

				findUserListPane.addItem(item);
			});
			item.setChooseGroup(chooseGroup);

			uhim.loadAvatarImage(user.getHead(), user.getAvatar(), (img) -> {
				FxUtil.invoke(() -> {
					item.setHeadImage(img);
				});
			});

			ToolIconButton infoButton = new ToolIconButton();
			infoButton.getStyleClass().add("tool-icon-button");
			infoButton.setFontIcon("\uf2bb");
			infoButton.setOnAction(a -> {
				openContactInfoView(user);
			});

			ToolIconButton applyButton = new ToolIconButton();
			applyButton.getStyleClass().add("tool-icon-button");
			applyButton.setFontIcon("\uf055");

			applyButton.setOnAction(a -> {
				openContactUserApplyView(user);
			});

			VBox box = new VBox();
			box.setPadding(new Insets(0, 20, 0, 0));
			box.getChildren().add(infoButton);
			box.getChildren().add(applyButton);
			FxUtil.invoke(() -> {
				item.setRight(box);
			});
		}
	}

	public void addFindContact(ContactRelation data, User user) {

		if (null != data && null != user) {
			UserHeadImageHandler uhim = this.appContext.getObject(UserHeadImageHandler.class);

			String remark = data.getRemark();

			boolean hasRemark = StringUtil.isNotBlank(remark);

			String status = user.getStatus();
			boolean gray = UserInfoUtil.isOffline(status);
			String signature = user.getSignature();

			HeadItem item = new HeadItem();
			FxUtil.invoke(() -> {

				String showRemark = "";
				String showName = "";

				if (hasRemark) {
					String name = UserInfoUtil.getShowName(user);
					showRemark = remark;
					showName = "(" + name + ")";
				} else {
					String name = UserInfoUtil.getShowName(user);
					showRemark = name;
					showName = "";
				}

				item.setRemark(showRemark);
				item.setNickname(showName);
				// item.setStatus(status);
				item.setShowText(signature);

				item.setGray(gray);

				findContactListPane.addItem(item);
			});
			item.setChooseGroup(chooseGroup);

			uhim.loadAvatarImage(user.getHead(), user.getAvatar(), (img) -> {
				FxUtil.invoke(() -> {
					item.setHeadImage(img);
				});
			});

			ToolIconButton infoButton = new ToolIconButton();
			infoButton.getStyleClass().add("tool-icon-button");
			infoButton.setFontIcon("\uf2bb");
			infoButton.setOnAction(a -> {
				openContactInfoView(user);
			});

			ToolIconButton chatButton = new ToolIconButton();
			chatButton.setFontIcon("\uf086");
			chatButton.getStyleClass().add("tool-icon-button");
			chatButton.setOnAction(a -> {
				openContactChatView(user);
			});

			VBox box = new VBox();
			box.setPadding(new Insets(0, 20, 0, 0));
			box.getChildren().add(infoButton);
			box.getChildren().add(chatButton);
			FxUtil.invoke(() -> {
				item.setRight(box);
			});
		}
	}

	public void addFindGroup(Group group) {

		if (null != group) {
			GroupHeadImageHandler uhim = this.appContext.getObject(GroupHeadImageHandler.class);

			String remark = null;

			boolean hasRemark = StringUtil.isNotBlank(remark);

			HeadItem item = new HeadItem();
			FxUtil.invoke(() -> {

				String showRemark = "";
				String showName = "";

				if (hasRemark) {
					String name = GroupInfoUtil.getShowName(group);
					showRemark = remark;
					showName = "(" + name + ")";
				} else {
					String name = GroupInfoUtil.getShowName(group);
					showRemark = name;
					showName = "";
				}
				String showTex = group.getIntroduce();

				item.setRemark(showRemark);
				item.setNickname(showName);
				item.setShowText(showTex);

				findGroupListPane.addItem(item);

			});
			item.setChooseGroup(chooseGroup);

			uhim.loadAvatarImage(group.getHead(), group.getAvatar(), (img) -> {
				FxUtil.invoke(() -> {
					item.setHeadImage(img);
				});
			});

			ToolIconButton infoButton = new ToolIconButton();
			infoButton.getStyleClass().add("tool-icon-button");
			infoButton.setFontIcon("\uf2bb");
			infoButton.setOnAction(a -> {
				openGroupInfoView(group);
			});

			ToolIconButton applyButton = new ToolIconButton();
			applyButton.getStyleClass().add("tool-icon-button");
			applyButton.setFontIcon("\uf055");
			applyButton.setOnAction(a -> {
				openGroupJoinApplyView(group);
			});

			VBox box = new VBox();
			box.setPadding(new Insets(0, 20, 0, 0));
			box.getChildren().add(infoButton);
			box.getChildren().add(applyButton);
			FxUtil.invoke(() -> {
				item.setRight(box);
			});
		}
	}

	public void addFindJoinGroup(GroupRelation data, Group group) {

		if (null != data && null != group) {
			GroupHeadImageHandler uhim = this.appContext.getObject(GroupHeadImageHandler.class);

			String remark = data.getRemark();
			boolean hasRemark = StringUtil.isNotBlank(remark);

			HeadItem item = new HeadItem();
			FxUtil.invoke(() -> {

				String showRemark = "";
				String showName = "";

				if (hasRemark) {
					String name = GroupInfoUtil.getShowName(group);
					showRemark = remark;
					showName = "(" + name + ")";
				} else {
					String name = GroupInfoUtil.getShowName(group);
					showRemark = name;
					showName = "";
				}
				String showTex = group.getIntroduce();

				item.setRemark(showRemark);
				item.setNickname(showName);
				item.setShowText(showTex);

				findJoinGroupListPane.addItem(item);

			});
			item.setChooseGroup(chooseGroup);

			uhim.loadAvatarImage(group.getHead(), group.getAvatar(), (img) -> {
				FxUtil.invoke(() -> {
					item.setHeadImage(img);
				});
			});

			ToolIconButton infoButton = new ToolIconButton();
			infoButton.getStyleClass().add("tool-icon-button");
			infoButton.setFontIcon("\uf2bb");
			infoButton.setOnAction(a -> {
				openGroupInfoView(group);
			});

			ToolIconButton chatButton = new ToolIconButton();
			chatButton.setFontIcon("\uf086");
			chatButton.getStyleClass().add("tool-icon-button");
			chatButton.setOnAction(a -> {
				openGroupChatView(group);
			});
			VBox box = new VBox();
			box.setPadding(new Insets(0, 20, 0, 0));
			box.getChildren().add(infoButton);
			box.getChildren().add(chatButton);
			FxUtil.invoke(() -> {
				item.setRight(box);
			});
		}
	}

	private void openGroupChatView(Group group) {
		// TODO Auto-generated method stub

	}

	private void openContactChatView(User user) {
		UserChatInteraction ci = this.appContext.getObject(UserChatInteraction.class);
		ci.showChat(user);
	}

	public void openContactUserApplyView(User u) {
		ContactUserApplyView addView = appContext.getObject(ContactUserApplyView.class);
		addView.setUserId(u.getId());
		addView.setVisible(true);
	}

	public void openGroupJoinApplyView(Group g) {
		GroupJoinApplyView addView = appContext.getObject(GroupJoinApplyView.class);
		addView.setGroupId(g.getId());
		addView.setVisible(true);
	}

	public void openContactInfoView(User u) {
		UserDataView view = appContext.getObject(UserDataView.class);
		view.showUserId(u.getId());
		view.setVisible(true);
	}

	public void openGroupInfoView(Group g) {
		GroupDataView view = appContext.getObject(GroupDataView.class);
		view.setGroupId(g.getId());
		view.setVisible(true);
	}
}
