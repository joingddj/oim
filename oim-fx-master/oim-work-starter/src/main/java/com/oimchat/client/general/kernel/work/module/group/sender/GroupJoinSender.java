package com.oimchat.client.general.kernel.work.module.group.sender;

import java.util.List;

import com.oimchat.client.general.kernel.work.module.group.data.dto.GroupJoinApplyData;
import com.oimchat.client.general.kernel.work.module.group.data.dto.GroupJoinHandleData;
import com.oimchat.client.general.kernel.work.module.group.data.dto.JoinVerifyAnswer;
import com.oimchat.client.general.kernel.work.module.group.data.query.GroupJoinApplyQuery;
import com.onlyxiahui.common.annotation.parameter.Define;
import com.onlyxiahui.common.data.common.data.Page;
import com.onlyxiahui.framework.action.dispatcher.annotation.ActionMapping;
import com.onlyxiahui.framework.net.handler.data.action.DataBackAction;
import com.onlyxiahui.wofa.client.net.send.annotation.Sender;

/**
 * 加入群业务接口<br>
 * Date 2019-01-20 20:49:52<br>
 * 
 * @author XiaHui<br>
 * @since 1.0.0
 * @docModuleSuperKey 1.3.0
 */
@Sender
@ActionMapping(value = "1.3.007")
public interface GroupJoinSender {

	/**
	 * 获取申请加入数量(群主/管理员)<br>
	 * Date 2019-01-27 10:39:08<br>
	 * 
	 * @author XiaHui<br>
	 * @since 1.0.0
	 */

	@ActionMapping(value = "1.1.0001")
	public void queryJoinApplyCount(
			@Define("body.query") GroupJoinApplyQuery query,
			DataBackAction back);

	/**
	 * 获取申请加入列表(群主/管理员)<br>
	 * Date 2019-01-27 10:39:08<br>
	 * 
	 * @author XiaHui<br>
	 * @since 1.0.0
	 */
	@ActionMapping(value = "1.1.0002")
	public void queryJoinApplyList(
			@Define("body.query") GroupJoinApplyQuery query,
			@Define("body.page") Page page,
			DataBackAction back);

	/**
	 * 
	 * 获取申请加入详情列表(群主/管理员)<br>
	 * Date 2020-04-11 20:05:25<br>
	 * 
	 * @since 1.0.0
	 */
	@ActionMapping(value = "1.1.0003")
	public void queryJoinApplyDataList(
			@Define("body.query") GroupJoinApplyQuery query,
			@Define("body.page") Page page,
			DataBackAction back);

	/**
	 * 处理加入申请<br>
	 * Date 2019-01-26 14:44:23<br>
	 * 
	 * @since 1.0.0
	 */
	@ActionMapping(value = "1.1.0005")
	public void joinHandle(
			@Define("body.handle") GroupJoinHandleData handle,
			DataBackAction back);

	/**
	 * 申请加入<br>
	 * Date 2019-01-26 14:43:22<br>
	 * 
	 * @author XiaHui<br>
	 * @since 1.0.0
	 */
	@ActionMapping(value = "1.1.0006")
	public void joinApply(
			@Define("body.apply") GroupJoinApplyData apply,
			@Define("body.answers") List<JoinVerifyAnswer> answers,
			DataBackAction back);
}
