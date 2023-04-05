package com.oimchat.client.general.kernel.work.module.group.sender;

import java.util.List;

import com.oimchat.client.general.kernel.work.module.group.data.query.GroupQuery;
import com.onlyxiahui.common.annotation.parameter.Define;
import com.onlyxiahui.common.data.common.data.Page;
import com.onlyxiahui.framework.action.dispatcher.annotation.ActionMapping;
import com.onlyxiahui.framework.net.handler.data.action.DataBackAction;
import com.onlyxiahui.wofa.client.net.send.annotation.Sender;

/**
 * 群信息业务接口<br>
 * Date 2019-01-20 20:49:52<br>
 * 
 * @author XiaHui<br>
 * @since 1.0.0
 * @docModuleSuperKey 1.3.0
 */
@Sender
@ActionMapping(value = "1.3.001")
public interface GroupSender {

	/**
	 * 条件分页搜索群<br>
	 * Date 2019-01-26 14:36:56<br>
	 * 
	 * @author XiaHui<br>
	 * @since 1.0.0
	 */
	@ActionMapping(value = "1.1.0001")
	public void list(
			@Define("body.query") GroupQuery query,
			@Define("body.page") Page page,
			DataBackAction back);

	/**
	 * 获取群信息<br>
	 * Date 2019-01-26 14:37:13<br>
	 * 
	 * @since 1.0.0
	 */
	@ActionMapping(value = "1.1.0002")
	public void getById(
			@Define("body.id") String id,
			DataBackAction back);

	/**
	 * 
	 * 批量获取群信息<br>
	 * Date 2019-05-05 08:39:53<br>
	 * 
	 * @since 1.0.0
	 */
	@ActionMapping(value = "1.1.0003")
	public void getListByIds(
			@Define("body.ids") List<String> ids,
			DataBackAction back);
}
