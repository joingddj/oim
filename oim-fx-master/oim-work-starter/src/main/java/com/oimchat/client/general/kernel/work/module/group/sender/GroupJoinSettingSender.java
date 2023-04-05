package com.oimchat.client.general.kernel.work.module.group.sender;

import java.util.List;

import com.oimchat.client.general.kernel.work.module.group.entity.GroupJoinSetting;
import com.oimchat.client.general.kernel.work.module.group.entity.GroupJoinVerifyQuestion;
import com.onlyxiahui.common.annotation.parameter.Define;
import com.onlyxiahui.framework.action.dispatcher.annotation.ActionMapping;
import com.onlyxiahui.framework.net.handler.data.action.DataBackAction;
import com.onlyxiahui.wofa.client.net.send.annotation.Sender;

/**
 * 群加入验证设置业务接口<br>
 * Date 2019-01-20 11:55:08<br>
 * 
 * @author XiaHui<br>
 * @since 1.0.0
 * @docModuleSuperKey 1.3.0
 */
@Sender
@ActionMapping(value = "1.3.006")
public interface GroupJoinSettingSender {

	/**
	 * 
	 * 获取群设置信息<br>
	 * Date 2020-04-11 16:04:55<br>
	 * 
	 * @since 1.0.0
	 */
	@ActionMapping(value = "1.1.0001")
	public void getByGroupId(
			@Define("body.groupId") String groupId,
			DataBackAction back);

	/**
	 * 
	 * 修改群设置信息<br>
	 * Date 2020-04-11 16:05:13<br>
	 * 
	 * @since 1.0.0
	 */
	@ActionMapping(value = "1.1.0002")
	public void update(
			@Define("body.setting") GroupJoinSetting data,
			@Define("body.questions") List<GroupJoinVerifyQuestion> list,
			DataBackAction back);
}
