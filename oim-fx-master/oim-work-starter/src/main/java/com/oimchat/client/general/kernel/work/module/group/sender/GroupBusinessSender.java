package com.oimchat.client.general.kernel.work.module.group.sender;

import com.oimchat.client.general.kernel.work.module.group.entity.Group;
import com.oimchat.client.general.kernel.work.module.group.entity.GroupHead;
import com.onlyxiahui.common.annotation.parameter.Define;
import com.onlyxiahui.framework.action.dispatcher.annotation.ActionMapping;
import com.onlyxiahui.framework.net.handler.data.action.DataBackAction;
import com.onlyxiahui.wofa.client.net.send.annotation.Sender;

/**
 * 群业务接口<br>
 * Date 2019-01-20 20:49:52<br>
 * 
 * @author XiaHui<br>
 * @since 1.0.0
 * @docModuleSuperKey 1.3.0
 */
@Sender
@ActionMapping(value = "1.3.005")
public interface GroupBusinessSender {

	/**
	 * 创建群<br>
	 * Date 2019-01-26 14:42:17<br>
	 * 
	 * @author XiaHui<br>
	 * @since 1.0.0
	 */
	@ActionMapping(value = "1.1.0005")
	public void add(
			@Define("body") Group group,
			DataBackAction back);

	/**
	 * 修改群<br>
	 * Date 2019-01-26 14:42:29<br>
	 * 
	 * @author XiaHui<br>
	 * @since 1.0.0
	 */
	@ActionMapping(value = "1.1.0006")
	public void update(
			@Define("body") Group group,
			DataBackAction back);

	/**
	 * 修改头像<br>
	 * Date 2019-01-26 14:42:46<br>
	 * 
	 * @author XiaHui<br>
	 * @since 1.0.0
	 */
	@ActionMapping(value = "1.1.0007")
	public void uploadHead(@Define("body") GroupHead groupHead,
			DataBackAction back);

	/**
	 * 转让群<br>
	 * Date 2019-01-26 14:54:27<br>
	 * 
	 * @author XiaHui<br>
	 * @since 1.0.0
	 */
	@ActionMapping(value = "1.1.0008")
	public void changeGroupOwner(
			@Define("body.groupId") String groupId,
			@Define("body.newOwnerUserId") String newOwnerUserId,
			DataBackAction back);

	/**
	 * 解散群<br>
	 * Date 2019-01-26 15:04:37<br>
	 * 
	 * @author XiaHui<br>
	 * @since 1.0.0
	 */
	@ActionMapping(value = "1.1.0009")
	public void disband(
			@Define("body.groupId") String groupId,
			DataBackAction back);

	/**
	 * 退出群<br>
	 * Date 2019-01-27 12:54:59<br>
	 * 
	 * @author XiaHui<br>
	 * @since 1.0.0
	 */
	@ActionMapping(value = "1.1.0010")
	public void quit(
			@Define("body.groupId") String groupId,
			DataBackAction back);

}
