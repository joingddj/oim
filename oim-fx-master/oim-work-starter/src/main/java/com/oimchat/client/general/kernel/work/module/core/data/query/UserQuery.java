
package com.oimchat.client.general.kernel.work.module.core.data.query;

/**
 * Description <br>
 * Date 2021-03-15 14:12:30<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class UserQuery {

	/**
	 * 查询文本(名称/账号/昵称)
	 */
	private String queryText;
	/**
	 * 性别 1:男 2：女 3：保密
	 */
	private String gender;
	/**
	 * 星座
	 */
	private String constellationCode;
	/**
	 * 血型
	 */
	private String blood;
	/**
	 * 婚姻状况: 1:未婚 2:已婚
	 */
	private String maritalStatus;

	private String startBirthDate;
	private String endBirthDate;

	public String getQueryText() {
		return queryText;
	}

	public void setQueryText(String queryText) {
		this.queryText = queryText;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getConstellationCode() {
		return constellationCode;
	}

	public void setConstellationCode(String constellationCode) {
		this.constellationCode = constellationCode;
	}

	public String getBlood() {
		return blood;
	}

	public void setBlood(String blood) {
		this.blood = blood;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getStartBirthDate() {
		return startBirthDate;
	}

	public void setStartBirthDate(String startBirthDate) {
		this.startBirthDate = startBirthDate;
	}

	public String getEndBirthDate() {
		return endBirthDate;
	}

	public void setEndBirthDate(String endBirthDate) {
		this.endBirthDate = endBirthDate;
	}
}
