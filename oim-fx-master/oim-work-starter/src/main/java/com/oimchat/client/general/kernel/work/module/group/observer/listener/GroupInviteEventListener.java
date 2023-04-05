
package com.oimchat.client.general.kernel.work.module.group.observer.listener;

/**
 * Description <br>
 * Date 2021-03-31 10:58:21<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public interface GroupInviteEventListener {
	/**
	 * 推送邀请申请(群主/管理员)<br>
	 * Date 2020-04-11 15:28:24<br>
	 * 
	 * @param groupId
	 * @param applyId
	 * @since 1.0.0
	 */
	public void onReceiveInviteApply(
			String groupId,
			String applyId);

	/**
	 * 
	 * 推送邀请(被邀请者)<br>
	 * Date 2020-04-12 21:24:03<br>
	 * 
	 * @param groupId
	 * @param applyId
	 * @since 1.0.0
	 */
	public void onReceiveInviteInfo(
			String groupId,
			String applyId);
}
