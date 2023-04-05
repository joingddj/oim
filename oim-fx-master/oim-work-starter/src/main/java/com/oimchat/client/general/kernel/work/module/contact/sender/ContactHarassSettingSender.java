
package com.oimchat.client.general.kernel.work.module.contact.sender;

import java.util.List;

import com.oimchat.client.general.kernel.work.module.contact.entity.ContactAddVerifyQuestion;
import com.oimchat.client.general.kernel.work.module.contact.entity.ContactHarassSetting;
import com.onlyxiahui.common.annotation.parameter.Define;
import com.onlyxiahui.framework.action.dispatcher.annotation.ActionMapping;
import com.onlyxiahui.framework.net.handler.data.action.DataBackAction;
import com.onlyxiahui.wofa.client.net.send.annotation.Sender;

/**
 * Description <br>
 * Date 2021-03-15 14:50:51<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */
@Sender
@ActionMapping(value = "1.2.004")
public interface ContactHarassSettingSender {

	@ActionMapping(value = "1.1.0001")
	public void get(
			DataBackAction back);

	@ActionMapping(value = "1.1.0002")
	public void update(
			@Define("body.setting") ContactHarassSetting setting,
			@Define("body.questions") List<ContactAddVerifyQuestion> questions,
			DataBackAction back);
}
