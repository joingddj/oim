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
import com.oimchat.client.general.kernel.work.module.group.entity.GroupCategory;
import com.oimchat.client.general.kernel.work.module.group.sender.GroupCategorySender;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.data.common.bean.CountInfo;
import com.onlyxiahui.common.data.common.data.Page;
import com.onlyxiahui.common.data.common.data.PageResult;

/**
 * 群分组业务接口<br>
 * Date 2019-01-20 21:12:18<br>
 * 
 * @author XiaHui<br>
 * @since 1.0.0
 * @docModuleSuperKey 1.3.0
 */
@Component

public class GroupCategoryHandler extends AbstractMaterial {

	public GroupCategoryHandler(AppContext appContext) {
		super(appContext);
	}

	/**
	 * 获取分组数量<br>
	 * Date 2019-01-22 19:36:14<br>
	 * 
	 * @author XiaHui<br>
	 * @since 1.0.0
	 */

	public void count(
			ValueBack<CountInfo> back) {
		GroupCategorySender sender = this.appContext.getObject(GroupCategorySender.class);
		sender.count(new ValueBackActionImpl<CountInfo>(back, CountInfo.class));
	}

	/**
	 * 获取分组列表<br>
	 * Date 2019-01-20 14:28:43<br>
	 * 
	 * @author XiaHui<br>
	 * @since 1.0.0
	 */

	public void list(
			Page page,
			ValueBack<PageResult<List<GroupCategory>>> back) {
		GroupCategorySender sender = this.appContext.getObject(GroupCategorySender.class);
		sender.list(page, new ValueBackActionImpl<PageResult<List<GroupCategory>>>(back, new TypeClass<PageResult<List<GroupCategory>>>() {
		}));
	}

	/**
	 * 获取分组详情<br>
	 * Date 2020-04-12 20:00:54<br>
	 * 
	 * @since 1.0.0
	 */

	public void getById(
			String categoryId,
			ValueBack<GroupCategory> back) {
		GroupCategorySender sender = this.appContext.getObject(GroupCategorySender.class);
		sender.getById(categoryId, new ValueBackActionImpl<GroupCategory>(back, GroupCategory.class));

	}

	/**
	 * 新增分组<br>
	 * Date 2019-01-20 14:22:21<br>
	 * 
	 * @since 1.0.0
	 */

	public void add(
			GroupCategory groupCategory,
			ValueBack<GroupCategory> back) {
		GroupCategorySender sender = this.appContext.getObject(GroupCategorySender.class);
		sender.add(groupCategory, new ValueBackActionImpl<GroupCategory>(back, GroupCategory.class));
	}

	/**
	 * 修改名称<br>
	 * Date 2020-04-12 20:01:35<br>
	 * 
	 * @since 1.0.0
	 */

	public void updateName(
			String id,
			String name,
			String userId,
			InfoBack back) {
		GroupCategorySender sender = this.appContext.getObject(GroupCategorySender.class);
		sender.updateName(id, name, userId, new InfoBackActionImpl(back));
	}

	/**
	 * 
	 * 修改排序<br>
	 * Date 2020-04-12 20:03:33<br>
	 * 
	 * @since 1.0.0
	 */

	public void updateSort(
			String id,
			int sort,
			String userId,
			InfoBack back) {
		GroupCategorySender sender = this.appContext.getObject(GroupCategorySender.class);
		sender.updateSort(id, sort, userId, new InfoBackActionImpl(back));
	}

	/**
	 * 
	 * 删除分组<br>
	 * Date 2020-04-12 20:03:39<br>
	 * 
	 * @since 1.0.0
	 */

	public void delete(
			String id,
			String userId,
			InfoBack back) {
		GroupCategorySender sender = this.appContext.getObject(GroupCategorySender.class);
		sender.delete(id, userId, new InfoBackActionImpl(back));
	}

	public PageResult<List<GroupCategory>> list(
			Page page) {
		CompletableFuture<PageResult<List<GroupCategory>>> future = new CompletableFuture<>();
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
