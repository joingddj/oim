package com.oimchat.client.general.kernel.work.module.group.handler;

import java.util.List;

import com.oimchat.client.common.asyn.InfoBack;
import com.oimchat.client.common.asyn.ValueBack;
import com.oimchat.client.common.net.back.InfoBackActionImpl;
import com.oimchat.client.common.net.back.ValueBackActionImpl;
import com.oimchat.client.common.reflect.TypeClass;
import com.oimchat.client.general.kernel.work.module.group.data.query.GroupMemberQuery;
import com.oimchat.client.general.kernel.work.module.group.entity.GroupMember;
import com.oimchat.client.general.kernel.work.module.group.sender.GroupMemberSender;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.data.common.bean.CountInfo;
import com.onlyxiahui.common.data.common.data.Page;
import com.onlyxiahui.common.data.common.data.PageResult;

/**
 * 群成员业务接口<br>
 * Date 2019-01-20 21:59:18<br>
 * 
 * @author XiaHui<br>
 * @since 1.0.0
 * @docModuleSuperKey 1.3.0
 */

public class GroupMemberHandler extends AbstractMaterial {

	public GroupMemberHandler(AppContext appContext) {
		super(appContext);
	}

	/**
	 * 获取个人已经加入群的数量<br>
	 * Date 2019-01-23 21:49:05<br>
	 * 
	 * @author XiaHui<br>
	 * @since 1.0.0
	 */

	public void getOwnerGroupMemberCount(
			String userId,
			ValueBack<CountInfo> back) {
		GroupMemberSender sender = this.appContext.getObject(GroupMemberSender.class);
		sender.getOwnerGroupMemberCount(userId, new ValueBackActionImpl<CountInfo>(back, CountInfo.class));
	}

	/**
	 * 获取个人在已经加入群的关系信息（权限）<br>
	 * Date 2019-01-23 21:49:19<br>
	 * 
	 * @author XiaHui<br>
	 * @since 1.0.0
	 */

	public void getOwnerGroupMemberList(
			String userId,
			Page page,
			ValueBack<PageResult<List<GroupMember>>> back) {
		GroupMemberSender sender = this.appContext.getObject(GroupMemberSender.class);
		sender.getOwnerGroupMemberList(userId, page, new ValueBackActionImpl<PageResult<List<GroupMember>>>(back,
				new TypeClass<PageResult<List<GroupMember>>>() {
				}));
	}

	/**
	 * 获取成员数量<br>
	 * Date 2019-01-23 21:49:35<br>
	 * 
	 * @author XiaHui<br>
	 * @since 1.0.0
	 */

	public void queryGroupMemberCount(
			GroupMemberQuery query,
			ValueBack<CountInfo> back) {
		GroupMemberSender sender = this.appContext.getObject(GroupMemberSender.class);
		sender.queryGroupMemberCount(query, new ValueBackActionImpl<CountInfo>(back, CountInfo.class));
	}

	/**
	 * 获取成员列表<br>
	 * Date 2019-01-23 21:49:45<br>
	 * 
	 * @author XiaHui<br>
	 * @since 1.0.0
	 */

	public void queryGroupMemberList(
			GroupMemberQuery query,
			Page page,
			ValueBack<PageResult<List<GroupMember>>> back) {
		GroupMemberSender sender = this.appContext.getObject(GroupMemberSender.class);
		sender.queryGroupMemberList(query, page, new ValueBackActionImpl<PageResult<List<GroupMember>>>(back, new TypeClass<PageResult<List<GroupMember>>>() {
		}));
	}

	/**
	 * 
	 * 获取详情<br>
	 * Date 2020-04-12 20:58:25<br>
	 * 
	 * @since 1.0.0
	 */

	public void getById(
			String id,
			ValueBack<GroupMember> back) {
		GroupMemberSender sender = this.appContext.getObject(GroupMemberSender.class);
		sender.getById(id, null);
	}

	/**
	 * 
	 * 批量获取详情<br>
	 * Date 2020-04-15 12:16:33<br>
	 * 
	 */

	public void getByIds(
			List<String> ids,
			ValueBack<List<GroupMember>> back) {
		GroupMemberSender sender = this.appContext.getObject(GroupMemberSender.class);
		sender.getByIds(ids, new ValueBackActionImpl<List<GroupMember>>(back, new TypeClass<List<GroupMember>>() {
		}));
	}

	/**
	 * 获取成员详情<br>
	 * Date 2019-01-23 21:49:58<br>
	 * 
	 * @author XiaHui<br>
	 * @since 1.0.0
	 */

	public void getGroupMember(
			String groupId,
			String userId,
			ValueBack<GroupMember> back) {
		GroupMemberSender sender = this.appContext.getObject(GroupMemberSender.class);
		sender.getGroupMember(groupId, userId, new ValueBackActionImpl<GroupMember>(back, GroupMember.class));
	}

	/**
	 * 
	 * 批量获取详情<br>
	 * Date 2020-04-15 12:16:33<br>
	 * 
	 * @docIgnore
	 */

	public void getByUserIds(
			String groupId,
			List<String> userIds,
			ValueBack<List<GroupMember>> back) {
		GroupMemberSender sender = this.appContext.getObject(GroupMemberSender.class);
		sender.getByUserIds(groupId, userIds, new ValueBackActionImpl<List<GroupMember>>(back, new TypeClass<List<GroupMember>>() {
		}));
	}

	/**
	 * 
	 * 批量获取详情<br>
	 * Date 2020-04-15 12:16:33<br>
	 * 
	 */

	public void getByGroupIds(
			String userId,
			List<String> groupIds,
			ValueBack<List<GroupMember>> back) {
		GroupMemberSender sender = this.appContext.getObject(GroupMemberSender.class);
		sender.getByGroupIds(userId, groupIds, new ValueBackActionImpl<List<GroupMember>>(back, new TypeClass<List<GroupMember>>() {
		}));
	}

	/**
	 * 
	 * 修改群成员昵称<br>
	 * Date 2020-04-11 19:39:28<br>
	 * 
	 * @since 1.0.0
	 */

	public void updateRemark(
			String groupId,
			String userId,
			String nickname,
			InfoBack back) {
		GroupMemberSender sender = this.appContext.getObject(GroupMemberSender.class);
		sender.updateRemark(groupId, userId, nickname, new InfoBackActionImpl(back));
	}

	/**
	 * 设置管理员<br>
	 * Date 2019-01-23 21:57:44<br>
	 * 
	 * @author XiaHui<br>
	 * @since 1.0.0
	 */

	public void updatePosition(
			String groupId,
			String userId,
			String position,
			InfoBack back) {
		GroupMemberSender sender = this.appContext.getObject(GroupMemberSender.class);
		sender.updatePosition(groupId, userId, position, new InfoBackActionImpl(back));
	}

	/**
	 * 踢人<br>
	 * Date 2019-01-23 21:58:11<br>
	 * 
	 * @author XiaHui<br>
	 * @since 1.0.0
	 */

	public void delete(
			String groupId,
			String userId,
			InfoBack back) {
		GroupMemberSender sender = this.appContext.getObject(GroupMemberSender.class);
		sender.delete(groupId, userId, new InfoBackActionImpl(back));
	}
}
