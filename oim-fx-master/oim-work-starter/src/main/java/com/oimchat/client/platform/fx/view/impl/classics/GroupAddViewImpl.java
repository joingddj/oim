package com.oimchat.client.platform.fx.view.impl.classics;

import java.util.Random;

import com.oimchat.app.fx.view.ui.classics.module.group.GroupEditStage;
import com.oimchat.client.common.sync.SyncGet;
import com.oimchat.client.general.kernel.work.module.group.entity.Group;
import com.oimchat.client.general.kernel.work.module.group.handler.GroupBusinessHandler;
import com.oimchat.client.platform.common.view.GroupAddView;
import com.oimchat.client.platform.fx.work.common.handler.GroupHeadImageHandler;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.app.view.fx.common.util.FxUtil;

/**
 * 描述：
 * 
 * @author XiaHui
 * @date 2015年3月16日 下午10:42:19
 * @version 0.0.1
 */
public class GroupAddViewImpl extends BaseStageView<GroupEditStage> implements GroupAddView {

	Group group;
	String head;

	public GroupAddViewImpl(AppContext appContext) {
		super(appContext);
		FxUtil.invoke(() -> {
			initStage(getStage());
			initComponent();
			initEvent();
		});
	}

	@Override
	public void setVisible(boolean visible) {
		if (visible) {
			initData();
		}
		super.setVisible(visible);
	}

	@Override
	public boolean isShowing() {
		return getStage().isShowing();
	}

	public GroupEditStage getStage() {
		if (null == stage) {
			stage = SyncGet.<GroupEditStage>syncGet((p) -> {
				FxUtil.invoke(() -> {
					p.put(new GroupEditStage());
				});
			});
		}
		return stage;
	}

	public void add() {

		if (group == null) {
			group = new Group();
			group.setHead(head);
		}

		String name = stage.getName();
		String introduce = stage.getIntroduce();
		if (null == name || "".equals(name)) {
			showPrompt("名称不能空！");
			return;
		}

		group.setName(name);
		group.setIntroduce(introduce);

		GroupBusinessHandler gh = this.appContext.getObject(GroupBusinessHandler.class);
		gh.add(group, (info, g) -> {
			if (info.isSuccess()) {
				setVisible(false);
			} else {
				showPrompt("添加失败！");
			}
		});
	}

	public void initData() {
		int i = new Random().nextInt(3);
		i = i + 1;
		this.head = i + "";
		String head = this.head;
		String avatar = null;
		FxUtil.invoke(() -> {
			stage.setName("");
			stage.setIntroduce("");
			stage.setTitleText("新建群");
		});
		GroupHeadImageHandler uhim = this.appContext.getObject(GroupHeadImageHandler.class);
		uhim.loadAvatarImage(head, avatar, (img) -> {
			FxUtil.invoke(() -> {
				stage.setHeadImage(img);
			});
		});
	}

	@Override
	protected GroupEditStage createStage() {
		return new GroupEditStage();
	}

	@Override
	protected void initStage(GroupEditStage stage) {
		stage.setTitleText("");
	}

	@Override
	protected void initComponent() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initEvent() {
		stage.setDoneAction(a -> {
			add();
		});
	}
}
