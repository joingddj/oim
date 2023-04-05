package com.oimchat.client.general.kernel.work.module.group.handler;

import java.util.List;

import org.springframework.stereotype.Component;

import com.oimchat.client.common.asyn.InfoBack;
import com.oimchat.client.common.asyn.ValueBack;
import com.oimchat.client.common.net.back.InfoBackActionImpl;
import com.oimchat.client.common.net.back.ValueBackActionImpl;
import com.oimchat.client.common.reflect.TypeClass;
import com.oimchat.client.general.kernel.work.module.group.data.dto.GroupJoinApplyData;
import com.oimchat.client.general.kernel.work.module.group.data.dto.GroupJoinApplyEntityCase;
import com.oimchat.client.general.kernel.work.module.group.data.dto.GroupJoinHandleData;
import com.oimchat.client.general.kernel.work.module.group.data.dto.JoinVerifyAnswer;
import com.oimchat.client.general.kernel.work.module.group.data.query.GroupJoinApplyQuery;
import com.oimchat.client.general.kernel.work.module.group.entity.GroupJoinApply;
import com.oimchat.client.general.kernel.work.module.group.sender.GroupJoinSender;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.data.common.bean.CountInfo;
import com.onlyxiahui.common.data.common.data.Page;
import com.onlyxiahui.common.data.common.data.PageResult;

/**
 * 加入群业务接口<br>
 * Date 2019-01-20 20:49:52<br>
 * 
 * @author XiaHui<br>
 * @since 1.0.0
 * @docModuleSuperKey 1.3.0
 */
@Component

public class GroupJoinHandler extends AbstractMaterial {

	public GroupJoinHandler(AppContext appContext) {
		super(appContext);
	}

	/**
	 * 获取申请加入数量(群主/管理员)<br>
	 * Date 2019-01-27 10:39:08<br>
	 * 
	 * @author XiaHui<br>
	 * @since 1.0.0
	 */

	public void queryJoinApplyCount(
			GroupJoinApplyQuery query,
			ValueBack<CountInfo> back) {
		GroupJoinSender sender = this.appContext.getObject(GroupJoinSender.class);
		sender.queryJoinApplyCount(query, new ValueBackActionImpl<CountInfo>(back, CountInfo.class));
	}

	/**
	 * 获取申请加入列表(群主/管理员)<br>
	 * Date 2019-01-27 10:39:08<br>
	 * 
	 * @author XiaHui<br>
	 * @since 1.0.0
	 */

	public void queryJoinApplyList(
			GroupJoinApplyQuery query,
			Page page,
			ValueBack<PageResult<List<GroupJoinApply>>> back) {
		GroupJoinSender sender = this.appContext.getObject(GroupJoinSender.class);
		sender.queryJoinApplyList(query, page, new ValueBackActionImpl<PageResult<List<GroupJoinApply>>>(back, new TypeClass<PageResult<List<GroupJoinApply>>>() {
		}));
	}

	/**
	 * 
	 * 获取申请加入详情列表(群主/管理员)<br>
	 * Date 2020-04-11 20:05:25<br>
	 * 
	 * @since 1.0.0
	 */

	public void queryJoinApplyDataList(
			GroupJoinApplyQuery query,
			Page page,
			ValueBack<PageResult<List<GroupJoinApplyEntityCase>>> back) {
		GroupJoinSender sender = this.appContext.getObject(GroupJoinSender.class);
		sender.queryJoinApplyDataList(query, page, new ValueBackActionImpl<PageResult<List<GroupJoinApplyEntityCase>>>(back, new TypeClass<PageResult<List<GroupJoinApplyEntityCase>>>() {
		}));
	}

	/**
	 * 处理加入申请<br>
	 * Date 2019-01-26 14:44:23<br>
	 * 
	 * @author XiaHui<br>
	 * @since 1.0.0
	 */

	public void joinHandle(
			GroupJoinHandleData handle,
			InfoBack back) {
		GroupJoinSender sender = this.appContext.getObject(GroupJoinSender.class);
		sender.joinHandle(handle, new InfoBackActionImpl(back));
	}

	/**
	 * 申请加入<br>
	 * Date 2019-01-26 14:43:22<br>
	 * 
	 * @author XiaHui<br>
	 * @since 1.0.0
	 */

	public void joinApply(
			GroupJoinApplyData apply,
			List<JoinVerifyAnswer> answers,
			InfoBack back) {
		GroupJoinSender sender = this.appContext.getObject(GroupJoinSender.class);
		sender.joinApply(apply, answers, new InfoBackActionImpl(back));
	}
}
