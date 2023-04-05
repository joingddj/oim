
package com.oimchat.client.general.kernel.work.module.group.observer.listener;

import java.util.List;

/**
 * Description <br>
 * Date 2021-03-30 18:41:19<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public interface GroupRelationEventListener {

	/**
	 * 修改备注<br>
	 * Date 2020-04-10 22:14:02<br>
	 * 
	 * @param groupId
	 * @param remark
	 * @since 1.0.0
	 */
	public void updateRemark(String groupId, String remark);

	/**
	 * 
	 * 移动分组<br>
	 * Date 2020-04-10 22:49:33<br>
	 * 
	 * @param key
	 * @param groupIds
	 * @param categoryId
	 * @since 1.0.0
	 */
	public void moveCategory(List<String> groupIds, String categoryId);

}
