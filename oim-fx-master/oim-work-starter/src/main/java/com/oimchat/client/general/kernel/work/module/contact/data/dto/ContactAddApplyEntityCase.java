
package com.oimchat.client.general.kernel.work.module.contact.data.dto;

import java.util.List;

import com.oimchat.client.general.kernel.work.module.contact.entity.ContactAddApply;
import com.oimchat.client.general.kernel.work.module.contact.entity.ContactAddVerifyAnswer;

/**
 * 联系人添加请求信息<br>
 * Date 2020-04-10 19:44:04<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ContactAddApplyEntityCase {

	/**
	 * 请求信息
	 */
	private ContactAddApply apply;
	/**
	 * 验证问题回答
	 */
	private List<ContactAddVerifyAnswer> answers;

	public ContactAddApplyEntityCase() {
		super();
	}

	public ContactAddApplyEntityCase(ContactAddApply apply, List<ContactAddVerifyAnswer> answers) {
		super();
		this.apply = apply;
		this.answers = answers;
	}

	public ContactAddApply getApply() {
		return apply;
	}

	public void setApply(ContactAddApply apply) {
		this.apply = apply;
	}

	public List<ContactAddVerifyAnswer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<ContactAddVerifyAnswer> answers) {
		this.answers = answers;
	}
}
