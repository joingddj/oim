
package com.oimchat.client.platform.fx.module.group.wrap;

import java.util.ArrayList;
import java.util.List;

import com.oimchat.app.fx.base.component.list.ListRootPane;
import com.oimchat.app.fx.view.ui.classics.element.page.PageListPane;
import com.oimchat.app.fx.view.ui.classics.module.apply.ApplyNoticeItem;
import com.oimchat.client.general.kernel.work.module.common.util.UserInfoUtil;
import com.oimchat.client.general.kernel.work.module.core.entity.User;
import com.oimchat.client.general.kernel.work.module.group.controller.GroupInviteeApplyController;
import com.oimchat.client.general.kernel.work.module.group.data.dto.GroupInviteeHandleData;
import com.oimchat.client.general.kernel.work.module.group.data.query.GroupInviteeApplyQuery;
import com.oimchat.client.general.kernel.work.module.group.data.vo.GroupInviteApplyEntityCaseData;
import com.oimchat.client.general.kernel.work.module.group.entity.Group;
import com.oimchat.client.general.kernel.work.module.group.entity.GroupInviteApply;
import com.oimchat.client.general.kernel.work.module.group.handler.GroupInviteHandler;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.app.view.fx.common.util.FxUtil;
import com.onlyxiahui.common.data.common.data.Page;
import com.onlyxiahui.common.utils.base.lang.string.StringUtil;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

/**
 * Description <br>
 * Date 2021-03-15 11:19:49<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class GroupInviteeNoticeListViewWrap extends AbstractMaterial {

	private final BorderPane rootPane = new BorderPane();
	private final PageListPane pagePane = new PageListPane();
	private final ListRootPane listPane = new ListRootPane();

	private final Button refreshButton = new Button("刷新");

	public GroupInviteeNoticeListViewWrap(AppContext appContext) {
		super(appContext);
		initConfig();
	}

	private void initConfig() {

		HBox box = new HBox();
		box.getChildren().add(refreshButton);
		box.setAlignment(Pos.CENTER_RIGHT);
		box.setPadding(new Insets(5, 30, 5, 5));
		box.setStyle("-fx-background-color:rgba(245, 245, 245, 0.9)");

		pagePane.setContentPane(listPane);

		rootPane.setTop(box);
		rootPane.setCenter(pagePane);

		pagePane.setPageFactory(new Callback<Integer, Node>() {

			@Override
			public Node call(Integer index) {
				int pageNumber = (index + 1);
				loadList(pageNumber);
				pagePane.showLoding();
				return new Label("第" + pageNumber + "页");
			}
		});

		refreshButton.setOnAction(a -> {
			loadList(1);
		});
	}

	public BorderPane getRootPane() {
		return rootPane;
	}

	public void initPage() {
		FxUtil.invoke(() -> {
			pagePane.setPage(0, 1);
		});
	}

	private void loadList(int number) {
		Page page = new Page();
		page.setNumber(number);

		GroupInviteeApplyQuery query = new GroupInviteeApplyQuery();
		query.setInviteeHandleType(GroupInviteApply.invitee_handle_type_untreated);

		GroupInviteeApplyController controller = appContext.getObject(GroupInviteeApplyController.class);
		controller.queryApplyDataReceiveList(query, page, (info, pr) -> {
			setList(pr.getPage(), pr.getItems());
		});
	}

	private void setList(Page page, List<GroupInviteApplyEntityCaseData> list) {
		FxUtil.invoke(() -> {
			pagePane.setTotalPage((page.getTotalPage() <= 0 ? 1 : page.getTotalPage()));
		});
		FxUtil.invoke(() -> {
			listPane.clearNode();
		});
		for (GroupInviteApplyEntityCaseData cd : list) {
			GroupInviteApply apply = cd.getApply();
			User inviterUser = cd.getInviterUser();
			User inviteeUser = cd.getInviteeUser();
			Group group = cd.getGroup();

			// String inviteeHandleType = apply.getInviteeHandleType();
			String verifyType = apply.getVerifyType();
			String inviterMessage = apply.getInviterMessage();

			String time = apply.getCreatedDateTime();

			StringBuilder title = new StringBuilder();
			title.append(UserInfoUtil.getShowName(inviterUser));
			title.append("(");
			title.append(inviterUser.getNumber());
			title.append(")");
			title.append("    邀请 ");
			title.append(UserInfoUtil.getShowName(inviterUser));
			title.append("(");
			title.append(inviteeUser.getNumber());
			title.append(")");

			title.append("    加入 ");
			title.append(group.getName());

			StringBuilder content = new StringBuilder();
			if (StringUtil.isNotBlank(inviterMessage)) {
				content.append("附加消息：").append(inviterMessage);
				content.append("\n");
				content.append("\n");
			}

			ApplyNoticeItem item = new ApplyNoticeItem();

			item.setTime(time);
			item.setTitle(title.toString());
			item.setContent(content.toString());

			if (GroupInviteApply.verify_handle_type_untreated.equals(verifyType)) {
				item.setAcceptButton(true);
				item.setRefuseButton(true);
			} else {
				item.setAcceptButton(false);
				item.setRefuseButton(false);
			}

			FxUtil.invoke(() -> {
				item.setOnAcceptAction(a -> {
					accept(apply);
				});

				item.setOnRefuseAction(a -> {
					reject(apply);
				});
				listPane.addNode(item);
			});
		}
		FxUtil.invoke(() -> {
			pagePane.showNode();
		});
	}

	private void reject(GroupInviteApply apply) {
		this.handle(GroupInviteApply.invitee_handle_type_reject, apply);
	}

	private void accept(GroupInviteApply apply) {
		this.handle(GroupInviteApply.invitee_handle_type_accept, apply);
	}

	private void handle(String inviteeHandleType, GroupInviteApply apply) {

		List<String> applyIds = new ArrayList<>();
		applyIds.add(apply.getId());

		GroupInviteeHandleData handle = new GroupInviteeHandleData();
		GroupInviteHandler contactController = appContext.getObject(GroupInviteHandler.class);
		handle.setInviteeHandleType(inviteeHandleType);
		handle.setApplyIds(applyIds);
		contactController.inviteeHandle(handle, (info) -> {
			int index = pagePane.getCurrentPage();
			int pageNumber = (index + 1);
			loadList(pageNumber);
		});
	}
}
