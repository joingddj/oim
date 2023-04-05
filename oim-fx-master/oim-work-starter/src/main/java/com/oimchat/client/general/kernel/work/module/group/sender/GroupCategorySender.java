package com.oimchat.client.general.kernel.work.module.group.sender;

import com.oimchat.client.general.kernel.work.module.group.entity.GroupCategory;
import com.onlyxiahui.common.annotation.parameter.Define;
import com.onlyxiahui.common.data.common.data.Page;
import com.onlyxiahui.framework.action.dispatcher.annotation.ActionMapping;
import com.onlyxiahui.framework.net.handler.data.action.DataBackAction;
import com.onlyxiahui.wofa.client.net.send.annotation.Sender;

/**
 * 群分组业务接口<br>
 * Date 2019-01-20 21:12:18<br>
 * 
 * @author XiaHui<br>
 * @since 1.0.0
 * @docModuleSuperKey 1.3.0
 */
@Sender
@ActionMapping(value = "1.3.002")
public interface GroupCategorySender {

	/**
	 * 获取分组数量<br>
	 * Date 2019-01-22 19:36:14<br>
	 * 
	 * @author XiaHui<br>
	 * @since 1.0.0
	 */
	@ActionMapping(value = "1.1.0001")
	public void count(
			DataBackAction back);

	/**
	 * 获取分组列表<br>
	 * Date 2019-01-20 14:28:43<br>
	 * 
	 * @author XiaHui<br>
	 * @since 1.0.0
	 */
	@ActionMapping(value = "1.1.0002")
	public void list(
			@Define("body.page") Page page,
			DataBackAction back);

	/**
	 * 获取分组详情<br>
	 * Date 2020-04-12 20:00:54<br>
	 * 
	 * @since 1.0.0
	 */
	@ActionMapping(value = "1.1.0003")
	public void getById(
			@Define("body.id") String categoryId,
			DataBackAction back);

	/**
	 * 新增分组<br>
	 * Date 2019-01-20 14:22:21<br>
	 * 
	 * @since 1.0.0
	 */
	@ActionMapping(value = "1.1.0004")
	public void add(
			@Define("body") GroupCategory groupCategory,
			DataBackAction back);

	/**
	 * 修改名称<br>
	 * Date 2020-04-12 20:01:35<br>
	 * 
	 * @since 1.0.0
	 */
	@ActionMapping(value = "1.1.0006")
	public void updateName(
			@Define("body.id") String id,
			@Define("body.name") String name,
			@Define("body.userId") String userId,
			DataBackAction back);

	/**
	 * 
	 * 修改排序<br>
	 * Date 2020-04-12 20:03:33<br>
	 * 
	 * @since 1.0.0
	 */
	@ActionMapping(value = "1.1.0007")
	public void updateSort(
			@Define("body.id") String id,
			@Define("body.sort") int sort,
			@Define("body.userId") String userId,
			DataBackAction back);

	/**
	 * 
	 * 删除分组<br>
	 * Date 2020-04-12 20:03:39<br>
	 * 
	 * @since 1.0.0
	 */
	@ActionMapping(value = "1.1.0008")
	public void delete(
			@Define("body.id") String id,
			@Define("body.userId") String userId,
			DataBackAction back);
}
