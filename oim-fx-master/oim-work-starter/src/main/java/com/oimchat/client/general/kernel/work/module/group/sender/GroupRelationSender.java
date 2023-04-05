package com.oimchat.client.general.kernel.work.module.group.sender;

import java.util.List;

import com.onlyxiahui.common.annotation.parameter.Define;
import com.onlyxiahui.common.data.common.data.Page;
import com.onlyxiahui.framework.action.dispatcher.annotation.ActionMapping;
import com.onlyxiahui.framework.net.handler.data.action.DataBackAction;
import com.onlyxiahui.wofa.client.net.send.annotation.Sender;

/**
 * 群分组关系业务接口<br>
 * Date 2019-01-20 21:46:46<br>
 * 
 * @author XiaHui<br>
 * @since 1.0.0
 * @docModuleSuperKey 1.3.0
 */
@Sender
@ActionMapping(value = "1.3.003")
public interface GroupRelationSender {

	/**
	 * 
	 * 获取数量<br>
	 * 
	 * Date 2020-04-11 16:09:54<br>
	 * 
	 * @since 1.0.0
	 */
	@ActionMapping(value = "1.1.0001")
	public void count(
			DataBackAction back);

	/**
	 * 
	 * 获取列表<br>
	 * Date 2020-04-11 16:10:12<br>
	 * 
	 * @since 1.0.0
	 */
	@ActionMapping(value = "1.1.0002")
	public void list(
			@Define("body.page") Page page,
			DataBackAction back);

	/**
	 * 获取详情<br>
	 * Date 2020-04-11 16:10:23<br>
	 * 
	 * @since 1.0.0
	 */
	@ActionMapping(value = "1.1.0004")
	public void getByGroupId(
			@Define("body.groupId") String groupId,
			DataBackAction back);

	
	@ActionMapping(value = "1.1.0007")
	public void getListByGroupIds(
			@Define("body.groupIds") List<String> groupIds,
			DataBackAction back);

	/**
	 * 修改群备注<br>
	 * Date 2019-01-20 17:37:06<br>
	 * 
	 * @author XiaHui<br>
	 * @since 1.0.0
	 */
	@ActionMapping(value = "1.1.0005")
	public void updateRemark(
			@Define("body.groupId") String groupId,
			@Define("body.remark") String remark,
			DataBackAction back);

	/**
	 * 移动群<br>
	 * Date 2019-01-20 17:37:33<br>
	 * 
	 * @author XiaHui<br>
	 * @since 1.0.0
	 */
	@ActionMapping(value = "1.1.0006")
	public void moveCategory(
			@Define("body.groupIds") List<String> groupIds,
			@Define("body.categoryId") String categoryId,
			DataBackAction back);
}
