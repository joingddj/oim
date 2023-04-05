package com.oimchat.client.general.kernel.work.module.group.handler;

import com.oimchat.client.common.asyn.InfoBack;
import com.oimchat.client.common.asyn.ValueBack;
import com.oimchat.client.common.net.back.InfoBackActionImpl;
import com.oimchat.client.common.net.back.ValueBackActionImpl;
import com.oimchat.client.general.kernel.work.module.group.entity.Group;
import com.oimchat.client.general.kernel.work.module.group.entity.GroupHead;
import com.oimchat.client.general.kernel.work.module.group.sender.GroupBusinessSender;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * 群业务接口<br>
 * Date 2019-01-20 20:49:52<br>
 * 
 * @author XiaHui<br>
 * @since 1.0.0
 * @docModuleSuperKey 1.3.0
 */

public class GroupBusinessHandler extends AbstractMaterial {

	public GroupBusinessHandler(AppContext appContext) {
		super(appContext);
	}

	/**
	 * 创建群<br>
	 * Date 2019-01-26 14:42:17<br>
	 * 
	 * @author XiaHui<br>
	 * @since 1.0.0
	 */

	public void add(
			Group group, ValueBack<Group> back) {
		GroupBusinessSender sender = this.appContext.getObject(GroupBusinessSender.class);
		sender.add(group, new ValueBackActionImpl<Group>(back, Group.class));
	}

	/**
	 * 修改群<br>
	 * Date 2019-01-26 14:42:29<br>
	 * 
	 * @author XiaHui<br>
	 * @since 1.0.0
	 */

	public void update(
			Group group, InfoBack back) {
		GroupBusinessSender sender = this.appContext.getObject(GroupBusinessSender.class);
		sender.update(group, new InfoBackActionImpl(back));
	}

	/**
	 * 修改头像<br>
	 * Date 2019-01-26 14:42:46<br>
	 * 
	 * @author XiaHui<br>
	 * @since 1.0.0
	 */

	public void uploadHead(GroupHead groupHead, ValueBack<Group> back) {
		GroupBusinessSender sender = this.appContext.getObject(GroupBusinessSender.class);
		sender.uploadHead(groupHead, new ValueBackActionImpl<Group>(back, Group.class));
	}

	/**
	 * 转让群<br>
	 * Date 2019-01-26 14:54:27<br>
	 * 
	 * @author XiaHui<br>
	 * @since 1.0.0
	 */

	public void changeGroupOwner(
			String groupId,
			String newOwnerUserId, InfoBack back) {
		GroupBusinessSender sender = this.appContext.getObject(GroupBusinessSender.class);
		sender.changeGroupOwner(groupId, newOwnerUserId, new InfoBackActionImpl(back));
	}

	/**
	 * 解散群<br>
	 * Date 2019-01-26 15:04:37<br>
	 * 
	 * @author XiaHui<br>
	 * @since 1.0.0
	 */

	public void disband(
			String groupId, InfoBack back) {
		GroupBusinessSender sender = this.appContext.getObject(GroupBusinessSender.class);
		sender.disband(groupId, new InfoBackActionImpl(back));
	}

	/**
	 * 退出群<br>
	 * Date 2019-01-27 12:54:59<br>
	 * 
	 * @author XiaHui<br>
	 * @since 1.0.0
	 */

	public void quit(
			String groupId, InfoBack back) {
		GroupBusinessSender sender = this.appContext.getObject(GroupBusinessSender.class);
		sender.quit(groupId, new InfoBackActionImpl(back));
	}
}
