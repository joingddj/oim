package com.oimchat.client.platform.fx.view.impl.classics;

import java.util.ArrayList;
import java.util.List;

import com.oimchat.app.fx.base.component.loading.WaitingPane;
import com.oimchat.app.fx.view.ui.classics.module.find.FindGroupItem;
import com.oimchat.app.fx.view.ui.classics.module.find.FindGroupPane;
import com.oimchat.app.fx.view.ui.classics.module.find.FindPane;
import com.oimchat.app.fx.view.ui.classics.module.find.FindUserItem;
import com.oimchat.app.fx.view.ui.classics.module.find.FindUserPane;
import com.oimchat.app.fx.view.ui.classics.module.stage.ClassicsStage;
import com.oimchat.client.general.common.util.OimDateUtil;
import com.oimchat.client.general.kernel.work.module.contact.handler.ContactRelationHandler;
import com.oimchat.client.general.kernel.work.module.core.data.query.UserQuery;
import com.oimchat.client.general.kernel.work.module.core.entity.User;
import com.oimchat.client.general.kernel.work.module.core.handler.UserHandler;
import com.oimchat.client.general.kernel.work.module.group.data.query.GroupQuery;
import com.oimchat.client.general.kernel.work.module.group.entity.Group;
import com.oimchat.client.general.kernel.work.module.group.handler.GroupHandler;
import com.oimchat.client.general.kernel.work.module.group.handler.GroupRelationHandler;
import com.oimchat.client.platform.common.view.ContactUserApplyView;
import com.oimchat.client.platform.common.view.FindView;
import com.oimchat.client.platform.common.view.GroupJoinApplyView;
import com.oimchat.client.platform.fx.work.common.handler.GroupHeadImageHandler;
import com.oimchat.client.platform.fx.work.common.handler.UserHeadImageHandler;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.app.view.fx.common.util.FxUtil;
import com.onlyxiahui.app.view.fx.common.util.ImageLoadUtil;
import com.onlyxiahui.common.data.common.data.Page;
import com.onlyxiahui.common.utils.base.lang.string.StringUtil;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.util.Callback;

/**
 * 描述：
 * 
 * @author XiaHui
 * @date 2015年3月16日 下午10:42:19
 * @version 0.0.1
 */
public class FindViewImpl extends BaseStageView<ClassicsStage> implements FindView {

	Page userPage;
	Page groupPage;
	UserQuery userQuery;
	GroupQuery groupQuery;

	FindPane pane = new FindPane();

	FindUserPane fup = pane.getFindUserPane();
	FindGroupPane fgp = pane.getFindGroupPane();

	public FindViewImpl(AppContext appContext) {
		super(appContext);
		FxUtil.invoke(() -> {
			initStage(getStage());
			initComponent();
			initEvent();
		});
	}

	public void initData() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				fup.initData();
				fgp.initData();
			}
		});
	}

	private void userPageChange(int number) {
		if (null == userPage) {
			userPage = new Page();
			userPage.setSize(16);
		}
		userPage.setNumber(number);
		queryUserDataList(userQuery, userPage);
	}

	private void groupPageChange(int number) {
		if (null == groupPage) {
			groupPage = new Page();
			groupPage.setSize(16);
		}
		groupPage.setNumber(number);
		queryGroupList(groupQuery, userPage);
	}

	public void queryUserDataList(UserQuery userQuery) {
		this.userQuery = userQuery;
		if (null == userPage) {
			userPage = new Page();
			userPage.setSize(16);
		}
		userPage.setNumber(1);
		queryUserDataList(userQuery, userPage);
	}

	public void queryGroupList(GroupQuery groupQuery) {
		this.groupQuery = groupQuery;
		if (null == groupPage) {
			groupPage = new Page();
			groupPage.setSize(16);
		}
		groupPage.setNumber(1);
		queryGroupList(groupQuery, groupPage);
	}

	public void queryUserDataList(UserQuery userQuery, Page page) {
		fup.getQueryListPane().showWaiting(true, WaitingPane.show_waiting);

		UserHandler uh = this.appContext.getObject(UserHandler.class);
		uh.list(userQuery, page, (info, pr) -> {
			if (info.isSuccess()) {
				fup.getQueryListPane().showWaiting(false, WaitingPane.show_waiting);
			} else {
				fup.getQueryListPane().showWaiting(true, WaitingPane.show_result);
			}

			setUserDataList(pr.getItems(), pr.getPage());
		});
	}

	public void queryGroupList(GroupQuery groupQuery, Page page) {
		fgp.getQueryListPane().showWaiting(true, WaitingPane.show_waiting);

		GroupHandler gh = this.appContext.getObject(GroupHandler.class);
		gh.list(groupQuery, page, (info, pr) -> {
			if (info.isSuccess()) {
				fgp.getQueryListPane().showWaiting(false, WaitingPane.show_waiting);
			} else {
				fgp.getQueryListPane().showWaiting(true, WaitingPane.show_result);
			}
			setGroupList(pr.getItems(), pr.getPage());
		});
	}

	public void setUserDataList(List<User> userDataList, Page page) {
		List<FindUserItem> list = new ArrayList<FindUserItem>();
		if (null != userDataList) {

			for (User userData : userDataList) {

				String nickname = userData.getNickname();
				// String account = userData.getAccount();
				String gender = userData.getGender();
				StringBuilder sb = new StringBuilder();

				String genderText = "保密";
				if ("1".equals(gender)) {
					genderText = "男";
				}
				if ("2".equals(gender)) {
					genderText = "女";
				}
				sb.append("性别：");
				sb.append(genderText);
				if (null != userData.getBirthDate()) {
					sb.append("|");
					int y = OimDateUtil.beforePresentYearCount(userData.getBirthDate());
					sb.append("年龄：");
					sb.append(y);
					sb.append("岁");
				}
				// sb.append("\n");
				// sb.append("11111111111");

				// if(null!=userData.getSignature()&&!"".equals(userData.getSignature())){
				// sb.append("|");
				// sb.append(userData.getSignature());
				// }else{
				// sb.append("\n");
				// }
				String avatar = userData.getAvatar();
				String headId = userData.getHead();

				FindUserItem head = new FindUserItem();

				head.setNickname(nickname);
				head.setShowText(sb.toString());

				head.setPrefSize(200, 100);
				head.setMaxWidth(200);
				head.setMaxHeight(100);

				head.addAttribute("userData", userData);
				head.setAddAction(a -> {
					openAddView(userData);
				});
				list.add(head);

				UserHeadImageHandler him = appContext.getObject(UserHeadImageHandler.class);
				if (StringUtil.isNotBlank(avatar)) {
					Image image = ImageLoadUtil.getImageByUrl(avatar);
					FxUtil.invoke(() -> {
						head.setHeadImage(image);
					});
				} else {
					him.loadHeadImage(headId, (img) -> {
						FxUtil.invoke(() -> {
							head.setHeadImage(img);
						});
					});
				}
			}
		}
		int totalPage = getTotalPage(page);
		FxUtil.invoke(() -> {
			fup.getQueryListPane().setTotalPage(totalPage);
			fup.getQueryListPane().setNodeList(list);
		});

	}

	public int getTotalPage(Page page) {
		int totalPage = 1;
		if (null != page && page.getTotalPage() > 0) {
			totalPage = page.getTotalPage();
		}
		return totalPage;
	}

	public void setGroupList(List<Group> groupList, Page page) {

		List<FindGroupItem> list = new ArrayList<FindGroupItem>();
		if (null != groupList) {
			GroupHeadImageHandler him = appContext.getObject(GroupHeadImageHandler.class);

			for (Group group : groupList) {

				String name = group.getName();
				String number = group.getNumber() + "";

				StringBuilder showText = new StringBuilder();
				// showText.append("(");
				showText.append(number);
				// showText.append(")|");
				// showText.append(group.getIntroduce());

				StringBuilder infoText = new StringBuilder();
				infoText.append("简介：");
				infoText.append(group.getIntroduce());

				FindGroupItem head = new FindGroupItem();

				head.setName(name);
				head.setShowText(showText.toString());
				head.setInfoText(infoText.toString());

				head.setPrefSize(275, 160);
				head.setMaxWidth(275);
				head.setMaxHeight(160);

				head.setAddAction(a -> {
					openJoinGroupView(group);
				});
				list.add(head);

				String avatar = group.getAvatar();
				String headId = group.getHead();

				him.loadAvatarImage(group.getHead(), group.getAvatar(), (img) -> {
					head.setHeadImage(img);
				});

				if (StringUtil.isNotBlank(avatar)) {
					Image image = ImageLoadUtil.getImageByUrl(avatar);
					FxUtil.invoke(() -> {
						head.setHeadImage(image);
					});
				} else {
					him.loadHeadImage(headId, (img) -> {
						FxUtil.invoke(() -> {
							head.setHeadImage(img);
						});
					});
				}
			}
		}

		int totalPage = getTotalPage(page);
		FxUtil.invoke(() -> {
			fgp.getQueryListPane().setTotalPage(totalPage);
			fgp.getQueryListPane().setNodeList(list);
		});
	}

	public void openAddView(User u) {
		List<String> ids = new ArrayList<>();
		ids.add(u.getId());
		ContactRelationHandler crh = this.appContext.getObject(ContactRelationHandler.class);
		crh.getByContactUserIds(ids, (info, pr) -> {
			if (null != pr && null != pr.getItems() && !pr.getItems().isEmpty()) {
				showPrompt(u.getNickname() + "已经是你的好友！");
			} else {
				ContactUserApplyView addView = appContext.getObject(ContactUserApplyView.class);
				addView.setUserId(u.getId());
				addView.setVisible(true);
			}
		});

	}

	public void openJoinGroupView(Group g) {
		GroupRelationHandler grh = this.appContext.getObject(GroupRelationHandler.class);
		List<String> ids = new ArrayList<>();
		ids.add(g.getId());
		grh.getListByGroupIds(ids, (info, pr) -> {
			if (null != pr.getItems() && !pr.getItems().isEmpty()) {
				showPrompt("你已经加入" + g.getName());
			} else {
				GroupJoinApplyView addView = appContext.getObject(GroupJoinApplyView.class);
				addView.setGroupId(g.getId());
				addView.setVisible(true);
			}
		});
	}

	@Override
	public void selectedTab(int index) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				pane.selectedTab(index);
			}
		});
	}

	@Override
	protected ClassicsStage createStage() {
		return new ClassicsStage();
	}

	@Override
	protected void initStage(ClassicsStage stage) {
		stage.setWidth(930);
		stage.setHeight(590);
		stage.setCenter(pane);
	}

	@Override
	protected void initComponent() {

	}

	@Override
	protected void initEvent() {
		fup.setSearchAction(a -> {

			UserQuery user = new UserQuery();
			String queryText = fup.getText();
			String age = (String) fup.getAge();
			String sex = (String) fup.getGender();
			// String homeAddress = (String) fup.getHomeAddress();
			// String locationAddress = (String) fup.getLocationAddress();

			user.setQueryText(queryText);

			if (null == sex || "".equals(sex)) {
				user.setGender(null);
			} else if (!"不限".equals(sex) && !"性别".equals(sex)) {
				sex = ("男".equals(sex)) ? "1" : "2";
				user.setGender(sex);
			} else {
				user.setGender(null);
			}
			// user.setHomeAddress(homeAddress);
			// user.setLocationAddress(locationAddress);

			// "不限",
			// "18岁以下",
			// "18-22岁",
			// "23-26岁",
			// "27-35岁",
			// "35岁以上"
			if ("18岁以下".equals(age)) {
				// long now = System.currentTimeMillis();
				// String start = DateUtil.dateToDate(new Date((now - (18L * 31536000000l))));
				// String end = DateUtil.dateToDate(new Date((now - (18l *
				// 31536000000l))));
				// user.setStartBirthdate(start);
				// user.setEndBirthdate("");
			} else if ("18-22岁".equals(age)) {
				// long now = System.currentTimeMillis();
				// String start = DateUtil.dateToDate(new Date((now - (22L * 31536000000l))));
				// String end = DateUtil.dateToDate(new Date((now - (18L * 31536000000l))));
				// user.setStartBirthdate(start);
				// user.setEndBirthdate(end);
			} else if ("23-26岁".equals(age)) {
				// long now = System.currentTimeMillis();
				// String start = DateUtil.dateToDate(new Date((now - (26L * 31536000000l))));
				// String end = DateUtil.dateToDate(new Date((now - (23L * 31536000000l))));
				// user.setStartBirthdate(start);
				// user.setEndBirthdate(end);
			} else if ("27-35岁".equals(age)) {
				// long now = System.currentTimeMillis();
				// String start = DateUtil.dateToDate(new Date((now - (35L * 31536000000l))));
				// String end = DateUtil.dateToDate(new Date((now - (27L * 31536000000l))));
				// user.setStartBirthdate(start);
				// user.setEndBirthdate(end);
			} else if ("35岁以上".equals(age)) {
				// long now = System.currentTimeMillis();
				// String end = DateUtil.dateToDate(new Date((now - (35L * 31536000000l))));
				// user.setStartBirthdate("");
				// user.setEndBirthdate(end);
			} else {
				// user.setStartBirthdate("");
				// user.setEndBirthdate("");
			}
			queryUserDataList(user);
		});

		fgp.setSearchAction(a -> {
			GroupQuery group = new GroupQuery();
			String queryText = fgp.getText();
			group.setQueryText(queryText);
			queryGroupList(group);
		});

		fup.getQueryListPane().setPage(0, 10);
		fgp.getQueryListPane().setPage(0, 10);

		fup.getQueryListPane().setPageFactory(new Callback<Integer, Node>() {

			@Override
			public Node call(Integer index) {
				userPageChange(index + 1);
				return new Label("第" + (index + 1) + "页");
			}
		});
		fgp.getQueryListPane().setPageFactory(new Callback<Integer, Node>() {

			@Override
			public Node call(Integer index) {
				groupPageChange(index + 1);
				return new Label("第" + (index + 1) + "页");
			}
		});
	}
}
