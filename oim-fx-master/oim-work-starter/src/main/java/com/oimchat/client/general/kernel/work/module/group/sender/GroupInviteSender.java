package com.oimchat.client.general.kernel.work.module.group.sender;

import java.util.List;

import com.oimchat.client.general.kernel.work.module.group.data.dto.GroupInviteVerifyHandleData;
import com.oimchat.client.general.kernel.work.module.group.data.dto.GroupInviteeHandleData;
import com.oimchat.client.general.kernel.work.module.group.data.query.GroupInviteVerifyQuery;
import com.oimchat.client.general.kernel.work.module.group.data.query.GroupInviteeApplyQuery;
import com.onlyxiahui.common.annotation.parameter.Define;
import com.onlyxiahui.common.data.common.data.Page;
import com.onlyxiahui.framework.action.dispatcher.annotation.ActionMapping;
import com.onlyxiahui.framework.net.handler.data.action.DataBackAction;
import com.onlyxiahui.wofa.client.net.send.annotation.Sender;

/**
 * 群邀请业务接口<br>
 * Date 2019-01-20 20:49:52<br>
 * 
 * @author XiaHui<br>
 * @since 1.0.0
 * @docModuleSuperKey 1.3.0
 */
@Sender
@ActionMapping(value = "1.3.008")
public interface GroupInviteSender {

	/**
	 * 获取申请邀请数量(群主/管理员)<br>
	 * Date 2019-01-27 10:51:05<br>
	 * 
	 * @author XiaHui<br>
	 * @since 1.0.0
	 */

	@ActionMapping(value = "1.1.0001")
	public void queryInviteApplyCount(
			@Define("body.query") GroupInviteVerifyQuery query,
			DataBackAction back);

	/**
	 * 获取申请邀请列表(群主/管理员)<br>
	 * Date 2019-01-27 10:51:54<br>
	 * 
	 * @author XiaHui<br>
	 * @since 1.0.0
	 */

	@ActionMapping(value = "1.1.0002")
	public void queryInviteApplyList(
			@Define("body.query") GroupInviteVerifyQuery query,
			@Define("body.page") Page page,
			DataBackAction back);

	/**
	 * 
	 * 1.1.0003<br>
	 * Date 2020-04-16 11:25:34<br>
	 * 
	 * @since 1.0.0
	 */
	public void queryInviteApplyDataList(
			@Define("body.query") GroupInviteVerifyQuery query,
			@Define("body.page") Page page,
			DataBackAction back);

	/**
	 * 
	 * 1.1.0004 <br>
	 * Date 2020-04-16 11:25:05<br>
	 * 
	 * @since 1.0.0
	 */
	public void getById();

	/**
	 * 审批处理邀请(群主/管理员)<br>
	 * Date 2019-01-27 10:56:14<br>
	 * 
	 * @author XiaHui<br>
	 * @since 1.0.0
	 */
	@ActionMapping(value = "1.1.0005")
	public void inviteVerifyHandle(
			@Define("body.handle") GroupInviteVerifyHandleData handle,
			DataBackAction back);

	/**
	 * 邀请加入<br>
	 * Date 2019-01-27 10:23:38<br>
	 * 
	 * @author XiaHui<br>
	 * @since 1.0.0
	 */
	@ActionMapping(value = "1.1.0006")
	public void invite(
			@Define("body.groupId") String groupId,
			@Define("body.userIds") List<String> userIds,
			DataBackAction back);

	/**
	 * 获取被邀请数量<br>
	 * Date 2019-01-27 10:28:43<br>
	 * 
	 * @since 1.0.0
	 */
	@ActionMapping(value = "1.1.0007")
	public void queryInviteeCount(
			@Define("body.query") GroupInviteeApplyQuery query,
			DataBackAction back);

	/**
	 * 获取被邀请列表<br>
	 * Date 2019-01-27 10:29:03<br>
	 * 
	 * @since 1.0.0
	 */
	@ActionMapping(value = "1.1.0008")
	public void queryInviteeList(
			@Define("body.query") GroupInviteeApplyQuery query,
			@Define("body.page") Page page,
			DataBackAction back);

	/**
	 * 
	 * 处理被邀请(同意/拒绝)<br>
	 * Date 2020-04-12 19:59:22<br>
	 * 
	 * @since 1.0.0
	 */
	@ActionMapping(value = "1.1.0010")
	public void inviteeHandle(
			@Define("body.handle") GroupInviteeHandleData handle,
			DataBackAction back);
}
