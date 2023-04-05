
package com.oimchat.client.platform.fx.module.contact.wrap;

import java.util.ArrayList;
import java.util.List;

import com.oimchat.app.fx.base.component.list.ListRootPane;
import com.oimchat.app.fx.view.ui.classics.element.page.PageListPane;
import com.oimchat.app.fx.view.ui.classics.module.apply.ApplyNoticeItem;
import com.oimchat.client.general.kernel.work.module.common.util.UserInfoUtil;
import com.oimchat.client.general.kernel.work.module.contact.controller.ContactAddApplyController;
import com.oimchat.client.general.kernel.work.module.contact.data.dto.ContactAddApplyQuery;
import com.oimchat.client.general.kernel.work.module.contact.data.dto.ContactAddHandleData;
import com.oimchat.client.general.kernel.work.module.contact.data.vo.ContactAddApplyEntityCaseData;
import com.oimchat.client.general.kernel.work.module.contact.entity.ContactAddApply;
import com.oimchat.client.general.kernel.work.module.contact.entity.ContactAddVerifyAnswer;
import com.oimchat.client.general.kernel.work.module.contact.handler.ContactHandler;
import com.oimchat.client.general.kernel.work.module.contact.type.ContactVerifyTypeEnum;
import com.oimchat.client.general.kernel.work.module.core.entity.User;
import com.oimchat.client.platform.common.view.ContactUserAcceptView;
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

public class ContactApplyNoticeListViewWrap extends AbstractMaterial {

	private final BorderPane rootPane = new BorderPane();
	private final PageListPane pagePane = new PageListPane();
	private final ListRootPane listPane = new ListRootPane();

	private final Button refreshButton = new Button("刷新");

	public ContactApplyNoticeListViewWrap(AppContext appContext) {
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

	public void reloadList() {
		int index = pagePane.getCurrentPage();
		int pageNumber = (index + 1);
		loadList(pageNumber);
	}

	private void loadList(int number) {
		Page page = new Page();
		page.setNumber(number);

		ContactAddApplyQuery query = new ContactAddApplyQuery();
		query.setHandleType(ContactAddApply.handle_type_untreated);

		ContactAddApplyController controller = appContext.getObject(ContactAddApplyController.class);
		controller.queryApplyDataReceiveList(query, page, (info, pr) -> {
			setList(pr.getPage(), pr.getItems());
		});
	}

	private void setList(Page page, List<ContactAddApplyEntityCaseData> list) {
		FxUtil.invoke(() -> {
			pagePane.setTotalPage((page.getTotalPage() <= 0 ? 1 : page.getTotalPage()));
		});
		FxUtil.invoke(() -> {
			listPane.clearNode();
		});
		for (ContactAddApplyEntityCaseData cd : list) {
			ContactAddApply apply = cd.getApply();
			User user = cd.getUser();

			String handleType = apply.getHandleType();
			String verifyType = apply.getVerifyType();
			String applyMessage = apply.getApplyMessage();

			String time = apply.getCreatedDateTime();
			List<ContactAddVerifyAnswer> answers = cd.getAnswers();

			StringBuilder title = new StringBuilder();
			title.append(UserInfoUtil.getShowName(user));
			title.append("(");
			title.append(user.getNumber());
			title.append(")");
			title.append("    请求添加为好友");

			StringBuilder content = new StringBuilder();
			if (StringUtil.isNotBlank(applyMessage)) {
				content.append("附加消息：").append(applyMessage);
				content.append("\n");
				content.append("\n");
			}

			if (ContactVerifyTypeEnum.confirm.getCode().equals(verifyType)) {
				if (null != answers) {
					for (ContactAddVerifyAnswer a : answers) {
						content.append("问题:");
						content.append(a.getQuestion());
						content.append("\n");
						content.append("答案:");
						content.append(a.getAnswer());
						content.append("\n");
						content.append("\n");
					}
				}
			}

			ApplyNoticeItem item = new ApplyNoticeItem();

			item.setTime(time);
			item.setTitle(title.toString());
			item.setContent(content.toString());

			if (ContactAddApply.handle_type_untreated.equals(handleType)) {
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

	private void reject(ContactAddApply apply) {
		this.handle(ContactAddApply.handle_type_reject, apply);
	}

	private void accept(ContactAddApply apply) {
		ContactUserAcceptView view = this.appContext.getObject(ContactUserAcceptView.class);
		view.setData(apply.getApplyUserId(), apply.getId(), () -> {
			reloadList();
		});
		view.setVisible(true);
	}

	private void handle(String handleType, ContactAddApply apply) {

		List<String> applyIds = new ArrayList<>();
		applyIds.add(apply.getId());

		ContactHandler ch = this.appContext.getObject(ContactHandler.class);
		ContactAddHandleData handle = new ContactAddHandleData();
		handle.setHandleType(handleType);
		handle.setApplyIds(applyIds);

		ch.applyHandle(handle, (info) -> {
			int index = pagePane.getCurrentPage();
			int pageNumber = (index + 1);
			loadList(pageNumber);
		});
	}
}
