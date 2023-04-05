
package com.oimchat.client.general.kernel.work.module.index.data.dto;

/**
 * Description <br>
 * Date 2021-03-12 09:36:01<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class LoginUser {
	/**
	 * 帐号
	 */
	public String account;

	/**
	 * 密码
	 */
	public String password;

	/**
	 * 验证码
	 */
	public String verifyCode;

	/**
	 * 在线状态
	 */
	public String status;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
