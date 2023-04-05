package com.oimchat.client.general.kernel.work.module.group.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Component;

import com.oimchat.client.common.asyn.InfoBack;
import com.oimchat.client.common.asyn.ValueBack;
import com.oimchat.client.common.net.back.InfoBackActionImpl;
import com.oimchat.client.common.net.back.ValueBackActionImpl;
import com.oimchat.client.common.reflect.TypeClass;
import com.oimchat.client.general.kernel.work.module.group.entity.GroupRelation;
import com.oimchat.client.general.kernel.work.module.group.sender.GroupRelationSender;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.data.common.bean.CountInfo;
import com.onlyxiahui.common.data.common.data.ListBody;
import com.onlyxiahui.common.data.common.data.Page;
import com.onlyxiahui.common.data.common.data.PageResult;

/**
 * 群分组关系业务接口<br>
 * Date 2019-01-20 21:46:46<br>
 * 
 * @author XiaHui<br>
 * @since 1.0.0
 * @docModuleSuperKey 1.3.0
 */
@Component

public class GroupRelationHandler extends AbstractMaterial {

	public GroupRelationHandler(AppContext appContext) {
		super(appContext);
	}

	/**
	 * 
	 * 获取数量<br>
	 * 
	 * Date 2020-04-11 16:09:54<br>
	 * 
	 * @since 1.0.0
	 */

	public void count(ValueBack<CountInfo> back) {
		GroupRelationSender sender = this.appContext.getObject(GroupRelationSender.class);
		sender.count(new ValueBackActionImpl<CountInfo>(back, CountInfo.class));
	}

	/**
	 * 
	 * 获取列表<br>
	 * Date 2020-04-11 16:10:12<br>
	 * 
	 * @since 1.0.0
	 */

	public void list(
			Page page,
			ValueBack<PageResult<List<GroupRelation>>> back) {
		GroupRelationSender sender = this.appContext.getObject(GroupRelationSender.class);
		sender.list(page, new ValueBackActionImpl<PageResult<List<GroupRelation>>>(back, new TypeClass<PageResult<List<GroupRelation>>>() {
		}));
	}

	/**
	 * 获取详情<br>
	 * Date 2020-04-11 16:10:23<br>
	 * 
	 * @since 1.0.0
	 */

	public void getByGroupId(
			String groupId,
			ValueBack<GroupRelation> back) {
		GroupRelationSender sender = this.appContext.getObject(GroupRelationSender.class);
		sender.getByGroupId(groupId, new ValueBackActionImpl<GroupRelation>(back, GroupRelation.class));
	}

	public void getListByGroupIds(List<String> groupIds,
			ValueBack<ListBody<List<GroupRelation>>> back) {
		GroupRelationSender sender = this.appContext.getObject(GroupRelationSender.class);
		sender.getListByGroupIds(groupIds, new ValueBackActionImpl<>(back, new TypeClass<ListBody<List<GroupRelation>>>() {
		}));
	}

	/**
	 * 修改群备注<br>
	 * Date 2019-01-20 17:37:06<br>
	 * 
	 * @author XiaHui<br>
	 * @since 1.0.0
	 */

	public void updateRemark(
			String groupId,
			String remark,
			InfoBack back) {
		GroupRelationSender sender = this.appContext.getObject(GroupRelationSender.class);
		sender.updateRemark(groupId, remark, new InfoBackActionImpl(back));
	}

	/**
	 * 移动群<br>
	 * Date 2019-01-20 17:37:33<br>
	 * 
	 * @author XiaHui<br>
	 * @since 1.0.0
	 */

	public void moveCategory(
			List<String> groupIds,
			String categoryId,
			InfoBack back) {
		GroupRelationSender sender = this.appContext.getObject(GroupRelationSender.class);
		sender.moveCategory(groupIds, categoryId, new InfoBackActionImpl(back));
	}
	
	public PageResult<List<GroupRelation>> list(
			Page page) {
		CompletableFuture<PageResult<List<GroupRelation>>> future = new CompletableFuture<>();
		list(page, (info, pr) -> {

			if (info.isSuccess() && null != pr) {
				future.complete(pr);
			} else {
				future.complete(new PageResult<>(page, new ArrayList<>()));
			}
		});
		try {
			return future.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return new PageResult<>(page, new ArrayList<>());
	}
}
