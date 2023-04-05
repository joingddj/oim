package com.oimchat.client.platform.fx.view.impl.classics;

import java.util.ArrayList;
import java.util.List;

import com.oimchat.app.fx.view.ui.classics.module.add.AcceptApplyPane;
import com.oimchat.app.fx.view.ui.classics.module.stage.ClassicsTitleStage;
import com.oimchat.client.general.kernel.work.module.contact.data.dto.ContactAddHandleData;
import com.oimchat.client.general.kernel.work.module.contact.entity.ContactAddApply;
import com.oimchat.client.general.kernel.work.module.contact.entity.ContactCategory;
import com.oimchat.client.general.kernel.work.module.contact.handler.ContactCategoryHandler;
import com.oimchat.client.general.kernel.work.module.contact.handler.ContactHandler;
import com.oimchat.client.general.kernel.work.module.contact.manager.ContactCategoryManager;
import com.oimchat.client.platform.common.view.ContactUserAcceptView;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.app.view.fx.common.event.ExecuteEvent;
import com.onlyxiahui.app.view.fx.common.util.FxUtil;
import com.onlyxiahui.common.message.util.OnlyMessageUtil;

/**
 * 描述： 同意
 * 
 * @author XiaHui
 * @date 2015年3月16日 下午10:42:19
 * @version 0.0.1
 */
public class ContactUserAcceptViewImpl extends BaseStageView<ClassicsTitleStage> implements ContactUserAcceptView {

	AcceptApplyPane pane = new AcceptApplyPane();

	private String applyId;
	private String userId;
	private ExecuteEvent back;

	public ContactUserAcceptViewImpl(AppContext appContext) {
		super(appContext);
		FxUtil.invoke(() -> {
			initStage(getStage());
			initComponent();
			initEvent();
		});
	}

	@Override
	protected ClassicsTitleStage createStage() {
		return new ClassicsTitleStage();
	}

	@Override
	protected void initStage(ClassicsTitleStage stage) {
		stage.setWidth(500);
		stage.setHeight(365);

		stage.setMaxWidth(500);
		stage.setMaxHeight(365);

		stage.setResizable(false);

		stage.setTitle("添加好友");
		
		stage.setCenter(pane);
	}

	@Override
	protected void initComponent() {
		pane.setMaxSize(500, 340);
	}

	@Override
	protected void initEvent() {
		stage.setDoneAction(a -> {
			doneAction();
		});

		pane.setNewCategoryAction(name -> {
			if (null != name && !"".equals(name)) {
				addCategory(name);
			}
		});
	}

	public void setCategoryList(List<ContactCategory> list) {
		FxUtil.invoke(() -> {
			for (ContactCategory data : list) {
				pane.addCategory(data.getId(), data.getName());
			}
		});
	}

	public void clearCategory() {
		FxUtil.invoke(() -> {
			pane.clearCategory();
		});
	}

	public void selectCategory() {
		FxUtil.invoke(() -> {
			pane.selectCategory(0);
		});
	}

	private void doneAction() {
		send();
	}

	public void addCategory(String name) {
		if (null != name && !"".equals(name)) {
			ContactCategory data = new ContactCategory();
			data.setName(name);

			ContactCategoryHandler uch = this.appContext.getObject(ContactCategoryHandler.class);
			uch.add(data, (info, v) -> {
				if (!info.isSuccess()) {
					showPrompt("添加失败！");
				} else {
					pane.addCategory(v.getId(), v.getName());
				}
			});
		}
	}

	private void loadCategoryList() {
		clearCategory();
		ContactCategoryManager uch = this.appContext.getObject(ContactCategoryManager.class);
		uch.asynGetAllList((info, list) -> {
			setCategoryList(list);
			selectCategory();
		});
	}

	private void send() {

		String categoryId = this.pane.getCategoryId();

		if (null == categoryId || "".equals(categoryId)) {
			showPrompt("请选择或者新建分组！");
			return;
		}

		List<String> applyIds = new ArrayList<>();
		applyIds.add(applyId);

		String userId = this.userId;
		String remark = pane.getRemark();

//		PersonalBox pb = this.appContext.getObject(PersonalBox.class);
//		String ownerUserId = pb.getOwnerUserId();

		ContactHandler ch = this.appContext.getObject(ContactHandler.class);
		ContactAddHandleData handle = new ContactAddHandleData();

		handle.setApplyIds(applyIds);
		handle.setApplyUserId(userId);
		handle.setCategoryId(categoryId);
		handle.setHandleType(ContactAddApply.handle_type_accept);
		handle.setRemark(remark);

		ch.applyHandle(handle, (info) -> {
			if (info.isSuccess()) {
				setVisible(false);
				if (null != back) {
					back.execute();
				}
			} else {
				String text = OnlyMessageUtil.getDefaultErrorText(info);
				showPrompt(text);
			}
		});
	}

	@Override
	public void setData(String userId, String applyId, ExecuteEvent back) {
		this.userId = userId;
		this.applyId = applyId;
		this.back = back;
		loadCategoryList();
	}
}
