
package com.oimchat.client.platform.fx.module.list.store;

import com.oimchat.app.fx.base.component.head.HeadItem;
import com.oimchat.client.general.kernel.work.module.group.entity.Group;
import com.oimchat.client.general.kernel.work.module.group.manager.GroupRelationManager;
import com.oimchat.client.platform.fx.module.list.function.GroupMessageItemFunction;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.message.bean.Info;

/**
 * Description <br>
 * Date 2021-04-01 16:43:13<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class GroupMessageListItemStore extends BaseMessageListItemStore<Group> {

	public GroupMessageListItemStore(AppContext appContext) {
		super(appContext);
		init();
	}

	public void init() {
	}

	@Override
	public String type() {
		return "group_message_item";
	}

	@Override
	public String getKeyByData(Group data) {
		return getKeyById(data.getId());
	}

	@Override
	protected Info checkRemove(Group data) {
		return new Info();
	}

	@Override
	protected void onRemove(Group data) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onSelect(Group data) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initialize(HeadItem item, Group data) {
		GroupMessageItemFunction function = this.appContext.getObject(GroupMessageItemFunction.class);
		function.setEvent(item);
	}

	@Override
	protected void setInfo(HeadItem item, Group data) {
		GroupMessageItemFunction function = this.appContext.getObject(GroupMessageItemFunction.class);

		String groupId = data.getId();
		item.addAttribute(Group.class, data);
		GroupRelationManager cch = this.appContext.getObject(GroupRelationManager.class);
		cch.getOrLoadByGroupId(groupId, (info, relation) -> {
			String remark = (null == relation) ? null : relation.getRemark();
			function.setInfo(item, data, remark);
		});
	}
}
