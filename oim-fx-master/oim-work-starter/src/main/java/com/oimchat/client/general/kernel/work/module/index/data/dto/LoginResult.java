package com.oimchat.client.general.kernel.work.module.index.data.dto;

import com.oimchat.client.general.kernel.work.module.core.entity.User;

/**
 * 登录结果<br>
 * Date 2019-01-06 12:08:30<br>
 * 
 * @author XiaHui<br>
 * @since 1.0.0
 */
public class LoginResult {

	/**
	 * 授权令牌
	 */
	private String token;

	/**
	 * 用户
	 */
	private User user;

	public LoginResult() {
		super();
	}

	public LoginResult(String token, User user) {
		super();
		this.token = token;
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
