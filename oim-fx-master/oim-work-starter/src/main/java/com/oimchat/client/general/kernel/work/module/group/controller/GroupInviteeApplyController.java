
package com.oimchat.client.general.kernel.work.module.group.controller;

import java.util.ArrayList;
import java.util.List;

import com.oimchat.client.common.asyn.ValueBack;
import com.oimchat.client.common.step.task.TaskNext;
import com.oimchat.client.general.kernel.work.module.core.handler.UserHandler;
import com.oimchat.client.general.kernel.work.module.group.data.query.GroupInviteeApplyQuery;
import com.oimchat.client.general.kernel.work.module.group.data.vo.GroupInviteApplyEntityCaseData;
import com.oimchat.client.general.kernel.work.module.group.entity.GroupInviteApply;
import com.oimchat.client.general.kernel.work.module.group.handler.GroupHandler;
import com.oimchat.client.general.kernel.work.module.group.handler.GroupInviteHandler;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.data.common.data.Page;
import com.onlyxiahui.common.data.common.data.PageResult;

/**
 * Description <br>
 * Date 2021-04-06 20:22:29<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class GroupInviteeApplyController extends AbstractMaterial {

	public GroupInviteeApplyController(AppContext appContext) {
		super(appContext);
	}

	public void queryApplyDataReceiveList(
			GroupInviteeApplyQuery query,
			Page page,
			ValueBack<PageResult<List<GroupInviteApplyEntityCaseData>>> back) {
		UserHandler uh = this.appContext.getObject(UserHandler.class);
		GroupHandler gh = this.appContext.getObject(GroupHandler.class);
		GroupInviteHandler hanlder = this.appContext.getObject(GroupInviteHandler.class);

		hanlder.queryInviteeList(query, page, (info, pr) -> {
			List<GroupInviteApplyEntityCaseData> items = new ArrayList<>();
			List<GroupInviteApply> list = (null != pr && null != pr.getItems()) ? pr.getItems() : new ArrayList<>();
			list.forEach((b) -> {
				GroupInviteApplyEntityCaseData data = new GroupInviteApplyEntityCaseData();
				data.setApply(b);
				items.add(data);
			});
			if (!items.isEmpty()) {

				// 异步串行执行
				TaskNext tn = new TaskNext();
				tn.set((n) -> {

					List<String> userIds = new ArrayList<>();
					for (GroupInviteApply a : list) {
						userIds.add(a.getInviteeUserId());
						userIds.add(a.getInviterUserId());
					}
					uh.getMapByIds(userIds, (ui, um) -> {
						for (GroupInviteApplyEntityCaseData g : items) {
							String inviteeUserId = (null != g && null != g.getApply()) ? g.getApply().getInviteeUserId() : "";
							String inviterUserId = (null != g && null != g.getApply()) ? g.getApply().getInviterUserId() : "";
							g.setInviteeUser(um.get(inviteeUserId));
							g.setInviterUser(um.get(inviterUserId));
						}
						n.next();
					});
				}).set((n) -> {
					gh.getMapByIds(items, (g) -> {
						String groupId = (null != g && null != g.getApply()) ? g.getApply().getGroupId() : "";
						return groupId;
					}, (ui, gm) -> {
						for (GroupInviteApplyEntityCaseData g : items) {
							String groupId = (null != g && null != g.getApply()) ? g.getApply().getGroupId() : "";
							g.setGroup(gm.get(groupId));
						}
						back.back(info, new PageResult<>(page, items));
					});
				});
				tn.next();
			} else {
				back.back(info, new PageResult<>(page, items));
			}
		});
	}
}
