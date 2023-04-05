
package com.oimchat.client.general.kernel.work.module.contact.data.dto;

import java.util.List;

import com.oimchat.client.general.kernel.work.module.contact.entity.ContactAddVerifyQuestion;
import com.oimchat.client.general.kernel.work.module.contact.entity.ContactHarassSetting;

/**
 * 隐私设置<br>
 * Date 2020-04-10 20:11:31<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */
public class ContactHarassSettingEntityCase {
	/**
	 * 设置
	 */
	private ContactHarassSetting setting;
	/**
	 * 问题列表
	 */
	private List<ContactAddVerifyQuestion> questions;

	public ContactHarassSettingEntityCase() {
		super();
	}

	public ContactHarassSettingEntityCase(ContactHarassSetting setting, List<ContactAddVerifyQuestion> questions) {
		super();
		this.setting = setting;
		this.questions = questions;
	}

	public ContactHarassSetting getSetting() {
		return setting;
	}

	public void setSetting(ContactHarassSetting setting) {
		this.setting = setting;
	}

	public List<ContactAddVerifyQuestion> getQuestions() {
		return questions;
	}

	public void setQuestions(List<ContactAddVerifyQuestion> questions) {
		this.questions = questions;
	}
}
