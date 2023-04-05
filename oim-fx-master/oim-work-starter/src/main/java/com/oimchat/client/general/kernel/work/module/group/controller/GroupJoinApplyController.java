
package com.oimchat.client.general.kernel.work.module.group.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.oimchat.client.common.asyn.ValueBack;
import com.oimchat.client.common.step.task.TaskNext;
import com.oimchat.client.general.kernel.work.module.core.handler.UserHandler;
import com.oimchat.client.general.kernel.work.module.group.data.dto.GroupJoinApplyEntityCase;
import com.oimchat.client.general.kernel.work.module.group.data.query.GroupJoinApplyQuery;
import com.oimchat.client.general.kernel.work.module.group.data.vo.GroupJoinApplyEntityCaseData;
import com.oimchat.client.general.kernel.work.module.group.handler.GroupHandler;
import com.oimchat.client.general.kernel.work.module.group.handler.GroupJoinHandler;
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

public class GroupJoinApplyController extends AbstractMaterial {

	public GroupJoinApplyController(AppContext appContext) {
		super(appContext);
	}

	public void queryApplyDataReceiveList(
			GroupJoinApplyQuery query,
			Page page,
			ValueBack<PageResult<List<GroupJoinApplyEntityCaseData>>> back) {
		UserHandler uh = this.appContext.getObject(UserHandler.class);
		GroupHandler gh = this.appContext.getObject(GroupHandler.class);
		GroupJoinHandler hanlder = this.appContext.getObject(GroupJoinHandler.class);

		hanlder.queryJoinApplyDataList(query, page, (info, pr) -> {
			List<GroupJoinApplyEntityCaseData> items = new ArrayList<>();
			List<GroupJoinApplyEntityCase> list = (null != pr && null != pr.getItems()) ? pr.getItems() : new ArrayList<>();
			list.forEach((b) -> {
				GroupJoinApplyEntityCaseData data = new GroupJoinApplyEntityCaseData();
				BeanUtils.copyProperties(b, data);
				items.add(data);
			});
			if (!items.isEmpty()) {

				// 异步串行执行
				TaskNext tn = new TaskNext();
				tn.set((n) -> {
					uh.getMapByIds(items, (g) -> {
						String userId = (null != g && null != g.getApply()) ? g.getApply().getApplyUserId() : "";
						return userId;
					}, (ui, um) -> {
						for (GroupJoinApplyEntityCaseData g : items) {
							String userId = (null != g && null != g.getApply()) ? g.getApply().getApplyUserId() : "";
							g.setUser(um.get(userId));
						}
						n.next();
					});
				}).set((n) -> {
					gh.getMapByIds(items, (g) -> {
						String groupId = (null != g && null != g.getApply()) ? g.getApply().getGroupId() : "";
						return groupId;
					}, (ui, gm) -> {
						for (GroupJoinApplyEntityCaseData g : items) {
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
