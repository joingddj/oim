
package com.oimchat.client.general.kernel.work.module.group.observer.listener;

import com.oimchat.client.general.kernel.work.module.group.data.dto.GroupJoinResultData;

/**
 * Description <br>
 * Date 2021-03-31 10:58:21<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public interface GroupJoinEventListener {
	/**
	 * 
	 * 推送加入申请(推送给群主/管理员)<br>
	 * Date 2020-04-11 15:30:42<br>
	 * 
	 * @param groupId
	 * @param applyId
	 * @since 1.0.0
	 */
	public void onReceiveJoinApply(String groupId, String applyId);

	/**
	 * 
	 * 推送申请处理结果(推送给申请者) <br>
	 * Date 2020-04-11 15:31:58<br>
	 * 
	 * @param result
	 * @since 1.0.0
	 */
	public void onReceiveJoinResult(GroupJoinResultData result);
}
