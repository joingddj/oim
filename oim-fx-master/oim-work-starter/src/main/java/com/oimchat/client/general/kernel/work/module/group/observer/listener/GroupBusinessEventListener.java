
package com.oimchat.client.general.kernel.work.module.group.observer.listener;

/**
 * Description <br>
 * Date 2021-03-30 16:48:28<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public interface GroupBusinessEventListener {

	public void disband(String groupId, String ownerUserId);

	/**
	 * 
	 * 推送退出群(通知管理员和群主)<br>
	 * Date 2020-04-11 13:43:17<br>
	 * 
	 * @param groupId
	 * @param userId
	 * @since 1.0.0
	 */
	public void quit(String groupId, String userId);

}
