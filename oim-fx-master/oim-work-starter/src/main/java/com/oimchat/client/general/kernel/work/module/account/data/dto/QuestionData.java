
package com.oimchat.client.general.kernel.work.module.account.data.dto;

import java.util.List;

/**
 * Description <br>
 * Date 2021-03-12 17:10:23<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class QuestionData {

	private String userId;
	private List<SecurityQuestion> items;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<SecurityQuestion> getItems() {
		return items;
	}

	public void setItems(List<SecurityQuestion> items) {
		this.items = items;
	}
}
