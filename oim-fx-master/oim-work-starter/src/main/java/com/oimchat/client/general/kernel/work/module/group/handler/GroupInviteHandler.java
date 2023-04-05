package com.oimchat.client.general.kernel.work.module.group.handler;

import java.util.List;

import org.springframework.stereotype.Component;

import com.oimchat.client.common.asyn.InfoBack;
import com.oimchat.client.common.asyn.ValueBack;
import com.oimchat.client.common.net.back.InfoBackActionImpl;
import com.oimchat.client.common.net.back.ValueBackActionImpl;
import com.oimchat.client.common.reflect.TypeClass;
import com.oimchat.client.general.kernel.work.module.group.data.dto.GroupInviteVerifyHandleData;
import com.oimchat.client.general.kernel.work.module.group.data.dto.GroupInviteeHandleData;
import com.oimchat.client.general.kernel.work.module.group.data.query.GroupInviteVerifyQuery;
import com.oimchat.client.general.kernel.work.module.group.data.query.GroupInviteeApplyQuery;
import com.oimchat.client.general.kernel.work.module.group.entity.GroupInviteApply;
import com.oimchat.client.general.kernel.work.module.group.sender.GroupInviteSender;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.data.common.bean.CountInfo;
import com.onlyxiahui.common.data.common.data.Page;
import com.onlyxiahui.common.data.common.data.PageResult;

/**
 * 群邀请业务接口<br>
 * Date 2019-01-20 20:49:52<br>
 * 
 * @author XiaHui<br>
 * @since 1.0.0
 * @docModuleSuperKey 1.3.0
 */
@Component

public class GroupInviteHandler extends AbstractMaterial {

	public GroupInviteHandler(AppContext appContext) {
		super(appContext);
	}

	/**
	 * 获取申请邀请数量(群主/管理员)<br>
	 * Date 2019-01-27 10:51:05<br>
	 * 
	 * @since 1.0.0
	 */

	public void queryInviteApplyCount(
			GroupInviteVerifyQuery query,
			ValueBack<CountInfo> back) {
		GroupInviteSender sender = this.appContext.getObject(GroupInviteSender.class);
		sender.queryInviteApplyCount(query, new ValueBackActionImpl<CountInfo>(back, CountInfo.class));
	}

	/**
	 * 获取申请邀请列表(群主/管理员)<br>
	 * Date 2019-01-27 10:51:54<br>
	 * 
	 * @author XiaHui<br>
	 * @since 1.0.0
	 */

	public void queryInviteApplyList(
			GroupInviteVerifyQuery query,
			Page page,
			ValueBack<PageResult<List<GroupInviteApply>>> back) {
		GroupInviteSender sender = this.appContext.getObject(GroupInviteSender.class);
		sender.queryInviteApplyDataList(query, page, new ValueBackActionImpl<PageResult<List<GroupInviteApply>>>(back, new TypeClass<PageResult<List<GroupInviteApply>>>() {
		}));
	}

	/**
	 * 
	 * 1.1.0003<br>
	 * Date 2020-04-16 11:25:34<br>
	 * 
	 * @since 1.0.0
	 */
	public void queryInviteApplyDataList(
			GroupInviteVerifyQuery query,
			Page page,
			ValueBack<PageResult<List<GroupInviteApply>>> back) {
		GroupInviteSender sender = this.appContext.getObject(GroupInviteSender.class);
		sender.queryInviteApplyDataList(query, page, new ValueBackActionImpl<PageResult<List<GroupInviteApply>>>(back, new TypeClass<PageResult<List<GroupInviteApply>>>() {
		}));
	}

	/**
	 * 
	 * 1.1.0004 <br>
	 * Date 2020-04-16 11:25:05<br>
	 * 
	 * @return
	 * @since 1.0.0
	 */

	/**
	 * 审批处理邀请(群主/管理员)<br>
	 * Date 2019-01-27 10:56:14<br>
	 * 
	 * @author XiaHui<br>
	 * @since 1.0.0
	 */

	public void inviteVerifyHandle(
			GroupInviteVerifyHandleData handle,
			InfoBack back) {
		GroupInviteSender sender = this.appContext.getObject(GroupInviteSender.class);
		sender.inviteVerifyHandle(handle, new InfoBackActionImpl(back));
	}

	/**
	 * 邀请加入<br>
	 * Date 2019-01-27 10:23:38<br>
	 * 
	 * @author XiaHui<br>
	 * @since 1.0.0
	 */

	public void invite(
			String groupId,
			List<String> userIds,
			InfoBack back) {
		GroupInviteSender sender = this.appContext.getObject(GroupInviteSender.class);
		sender.invite(groupId, userIds, new InfoBackActionImpl(back));
	}

	/**
	 * 获取被邀请数量<br>
	 * Date 2019-01-27 10:28:43<br>
	 * 
	 * @author XiaHui<br>
	 * @since 1.0.0
	 */

	public void queryInviteeCount(
			GroupInviteeApplyQuery query,
			ValueBack<CountInfo> back) {
		GroupInviteSender sender = this.appContext.getObject(GroupInviteSender.class);
		sender.queryInviteeCount(query, new ValueBackActionImpl<CountInfo>(back, CountInfo.class));
	}

	/**
	 * 获取被邀请列表<br>
	 * Date 2019-01-27 10:29:03<br>
	 * 
	 * @author XiaHui<br>
	 * @since 1.0.0
	 */

	public void queryInviteeList(
			GroupInviteeApplyQuery query,
			Page page,
			ValueBack<PageResult<List<GroupInviteApply>>> back) {
		GroupInviteSender sender = this.appContext.getObject(GroupInviteSender.class);
		sender.queryInviteeList(query, page, new ValueBackActionImpl<PageResult<List<GroupInviteApply>>>(back, new TypeClass<PageResult<List<GroupInviteApply>>>() {
		}));
	}

	/**
	 * 
	 * 处理被邀请(同意/拒绝)<br>
	 * Date 2020-04-12 19:59:22<br>
	 * 
	 * @since 1.0.0
	 */

	public void inviteeHandle(
			GroupInviteeHandleData handle,
			InfoBack back) {
		GroupInviteSender sender = this.appContext.getObject(GroupInviteSender.class);
		sender.inviteeHandle(handle, new InfoBackActionImpl(back));
	}
}
