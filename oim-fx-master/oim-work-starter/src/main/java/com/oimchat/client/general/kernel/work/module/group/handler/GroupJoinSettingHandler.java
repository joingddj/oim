package com.oimchat.client.general.kernel.work.module.group.handler;

import java.util.List;

import com.oimchat.client.common.asyn.InfoBack;
import com.oimchat.client.common.asyn.ValueBack;
import com.oimchat.client.common.net.back.InfoBackActionImpl;
import com.oimchat.client.common.net.back.ValueBackActionImpl;
import com.oimchat.client.general.kernel.work.module.group.data.dto.GroupJoinSettingEntityCase;
import com.oimchat.client.general.kernel.work.module.group.entity.GroupJoinSetting;
import com.oimchat.client.general.kernel.work.module.group.entity.GroupJoinVerifyQuestion;
import com.oimchat.client.general.kernel.work.module.group.sender.GroupJoinSettingSender;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * 群加入验证设置业务接口<br>
 * Date 2019-01-20 11:55:08<br>
 * 
 * @author XiaHui<br>
 * @since 1.0.0
 * @docModuleSuperKey 1.3.0
 */
public class GroupJoinSettingHandler extends AbstractMaterial {

	public GroupJoinSettingHandler(AppContext appContext) {
		super(appContext);
	}

	/**
	 * 
	 * 获取群设置信息<br>
	 * Date 2020-04-11 16:04:55<br>
	 * 
	 * @since 1.0.0
	 */

	public void getByGroupId(
			String groupId,
			ValueBack<GroupJoinSettingEntityCase> back) {
		GroupJoinSettingSender sender = this.appContext.getObject(GroupJoinSettingSender.class);
		sender.getByGroupId(groupId, new ValueBackActionImpl<GroupJoinSettingEntityCase>(back, GroupJoinSettingEntityCase.class));
	}

	/**
	 * 
	 * 修改群设置信息<br>
	 * Date 2020-04-11 16:05:13<br>
	 * 
	 * @since 1.0.0
	 */

	public void update(
			GroupJoinSetting data,
			List<GroupJoinVerifyQuestion> list,
			InfoBack back) {
		GroupJoinSettingSender sender = this.appContext.getObject(GroupJoinSettingSender.class);
		sender.update(data, list, new InfoBackActionImpl(back));
	}
}
