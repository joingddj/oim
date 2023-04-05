
package com.oimchat.client.general.kernel.work.module.contact.observer.listener;

import java.util.List;

/**
 * Description <br>
 * Date 2021-03-30 18:41:19<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public interface ContactRelationEventListener {

	/**
	 * 推送修改联系人备注<br>
	 * Date 2020-04-10 22:14:02<br>
	 * 
	 * @param contactUserId
	 * @param remark
	 * @since 1.0.0
	 */
	public void updateRemark(String contactUserId, String remark);

	/**
	 * 
	 * 推送联系人移动分组<br>
	 * Date 2020-04-10 22:49:33<br>
	 * 
	 * @param key
	 * @param contactUserIds
	 * @param categoryId
	 * @since 1.0.0
	 */
	public void moveCategory(List<String> contactUserIds, String categoryId);

	/**
	 * 
	 * 推送联系人拉入黑名单<br>
	 * Date 2020-04-10 22:50:25<br>
	 * 
	 * @param contactUserId
	 * @param isBlocked
	 * @since 1.0.0
	 */
	public void updateBlocked(String contactUserId, String isBlocked);

}
