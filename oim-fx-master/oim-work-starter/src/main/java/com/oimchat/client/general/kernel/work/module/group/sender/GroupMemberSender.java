package com.oimchat.client.general.kernel.work.module.group.sender;

import java.util.List;

import com.oimchat.client.general.kernel.work.module.group.data.query.GroupMemberQuery;
import com.onlyxiahui.common.annotation.parameter.Define;
import com.onlyxiahui.common.data.common.data.Page;
import com.onlyxiahui.framework.action.dispatcher.annotation.ActionMapping;
import com.onlyxiahui.framework.net.handler.data.action.DataBackAction;
import com.onlyxiahui.wofa.client.net.send.annotation.Sender;

/**
 * 群成员业务接口<br>
 * Date 2019-01-20 21:59:18<br>
 * 
 * @author XiaHui<br>
 * @since 1.0.0
 * @docModuleSuperKey 1.3.0
 */
@Sender
@ActionMapping(value = "1.3.004")
public interface GroupMemberSender {

	/**
	 * 获取个人已经加入群的数量<br>
	 * Date 2019-01-23 21:49:05<br>
	 * 
	 * @author XiaHui<br>
	 * @since 1.0.0
	 */
	@ActionMapping(value = "1.1.0001")
	public void getOwnerGroupMemberCount(
			@Define("body.query.userId") String userId,
			DataBackAction back);

	/**
	 * 获取个人在已经加入群的关系信息（权限）<br>
	 * Date 2019-01-23 21:49:19<br>
	 * 
	 * @author XiaHui<br>
	 * @since 1.0.0
	 */
	@ActionMapping(value = "1.1.0002")
	public void getOwnerGroupMemberList(
			@Define("body.query.userId") String userId,
			@Define("body.page") Page page,
			DataBackAction back);

	/**
	 * 获取成员数量<br>
	 * Date 2019-01-23 21:49:35<br>
	 * 
	 * @author XiaHui<br>
	 * @since 1.0.0
	 */
	@ActionMapping(value = "1.1.0003")
	public void queryGroupMemberCount(
			@Define("body.query") GroupMemberQuery query,
			DataBackAction back);

	/**
	 * 获取成员列表<br>
	 * Date 2019-01-23 21:49:45<br>
	 * 
	 * @author XiaHui<br>
	 * @since 1.0.0
	 */
	@ActionMapping(value = "1.1.0004")
	public void queryGroupMemberList(
			@Define("body.query") GroupMemberQuery query,
			@Define("body.page") Page page,
			DataBackAction back);

	/**
	 * 
	 * 获取详情<br>
	 * Date 2020-04-12 20:58:25<br>
	 * 
	 * @since 1.0.0
	 */
	@ActionMapping(value = "1.1.0005")
	public void getById(
			@Define("body.id") String id,
			DataBackAction back);

	/**
	 * 
	 * 批量获取详情<br>
	 * Date 2020-04-15 12:16:33<br>
	 * 
	 * @param ids
	 * @docIgnore
	 */
	@ActionMapping(value = "1.1.0006")
	public void getByIds(
			@Define("body.ids") List<String> ids,
			DataBackAction back);

	/**
	 * 获取成员详情<br>
	 * Date 2019-01-23 21:49:58<br>
	 * 
	 * @author XiaHui<br>
	 * @since 1.0.0
	 */
	@ActionMapping(value = "1.1.0007")
	public void getGroupMember(
			@Define("body.groupId") String groupId,
			@Define("body.userId") String userId,
			DataBackAction back);

	/**
	 * 
	 * 批量获取详情<br>
	 * Date 2020-04-15 12:16:33<br>
	 */
	@ActionMapping(value = "1.1.0008")
	public void getByUserIds(
			@Define("body.groupId") String groupId,
			@Define("body.userIds") List<String> userIds,
			DataBackAction back);

	/**
	 * 
	 * 批量获取详情<br>
	 * Date 2020-04-15 12:16:33<br>
	 */
	@ActionMapping(value = "1.1.0009")
	public void getByGroupIds(
			@Define("body.userId") String userId,
			@Define("body.groupIds") List<String> groupIds,
			DataBackAction back);

	/**
	 * 
	 * 修改群成员昵称<br>
	 * Date 2020-04-11 19:39:28<br>
	 * 
	 */
	@ActionMapping(value = "1.1.0010")
	public void updateRemark(
			@Define("body.groupId") String groupId,
			@Define("body.userId") String userId,
			@Define("body.nickname") String nickname,
			DataBackAction back);

	/**
	 * 设置管理员<br>
	 * Date 2019-01-23 21:57:44<br>
	 * 
	 */
	@ActionMapping(value = "1.1.0011")
	public void updatePosition(
			@Define("body.groupId") String groupId,
			@Define("body.userId") String userId,
			@Define("body.position") String position,
			DataBackAction back);

	/**
	 * 踢人<br>
	 * Date 2019-01-23 21:58:11<br>
	 * 
	 */
	@ActionMapping(value = "1.1.0012")
	public void delete(
			@Define("body.groupId") String groupId,
			@Define("body.userId") String userId,
			DataBackAction back);
}
