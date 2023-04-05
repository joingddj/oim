package com.oimchat.client.platform.fx.view.impl.classics;

import com.oimchat.app.fx.view.ui.classics.element.info.BaseInfoPane;
import com.oimchat.app.fx.view.ui.classics.module.stage.ClassicsStage;
import com.oimchat.client.general.common.util.HereStringUtil;
import com.oimchat.client.general.kernel.work.module.group.entity.Group;
import com.oimchat.client.general.kernel.work.module.group.handler.GroupHandler;
import com.oimchat.client.platform.common.view.GroupDataView;
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
public class GroupDataViewImpl extends BaseStageView<ClassicsStage> implements GroupDataView {

	BaseInfoPane frame = new BaseInfoPane();;

	public GroupDataViewImpl(AppContext appContext) {
		super(appContext);
		FxUtil.invoke(() -> {
			initStage(getStage());
			initComponent();
			initEvent();
		});
	}

	@Override
	public void setGroupId(String groupId) {
		GroupHandler uh = this.appContext.getObject(GroupHandler.class);
		uh.getById(groupId, (info, group) -> {
			showGroup(group);
		});
	}

	public void showGroup(Group group) {
		String info = getInfo(group);

		FxUtil.invoke(() -> {
			if (null != group) {
				frame.setInfoText(info);
			} else {
				frame.setHeadImage(null);
				frame.setName("");
				frame.setNumber("");
				frame.setInfoText("");
			}
		});
		if (null != group) {
			GroupHeadImageHandler uhim = this.appContext.getObject(GroupHeadImageHandler.class);
			uhim.loadAvatarImage(group.getHead(), group.getAvatar(), (img) -> {
				FxUtil.invoke(() -> {
					frame.setHeadImage(img);
				});
			});
		}
	}

	private String getInfo(Group group) {

		StringBuilder sb = new StringBuilder();
		sb.append("名称：");
		sb.append(HereStringUtil.value(group.getName()));
		sb.append("\n");
		sb.append("\n");

		sb.append("群号：");
		sb.append(HereStringUtil.value(group.getNumber() + ""));
		sb.append("\n");
		sb.append("\n");

		sb.append("简介：");
		sb.append(HereStringUtil.value(group.getIntroduce()));
		sb.append("\n");
		sb.append("\n");

		return (sb.toString());
	}

	@Override
	protected ClassicsStage createStage() {
		return new ClassicsStage();
	}

	@Override
	protected void initStage(ClassicsStage stage) {
		stage.setWidth(380);
		stage.setHeight(520);
		stage.setResizable(false);
		stage.setTitlePaneStyle(2);
		stage.setCenter(frame);
	}

	@Override
	protected void initComponent() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void initEvent() {
		// TODO Auto-generated method stub
	}
}
