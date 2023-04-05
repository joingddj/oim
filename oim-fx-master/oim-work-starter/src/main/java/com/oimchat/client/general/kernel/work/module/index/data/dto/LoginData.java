
package com.oimchat.client.general.kernel.work.module.index.data.dto;

import com.oimchat.client.general.basic.message.data.Client;

/**
 * Description <br>
 * Date 2021-03-12 09:57:41<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class LoginData {

	private LoginUser user;

	private Client client;

	public LoginData() {

	}

	public LoginData(LoginUser user, Client client) {
		super();
		this.user = user;
		this.client = client;
	}

	public LoginUser getUser() {
		return user;
	}

	public void setUser(LoginUser user) {
		this.user = user;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}
