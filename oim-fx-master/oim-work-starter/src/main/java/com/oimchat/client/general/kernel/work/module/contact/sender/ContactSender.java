
package com.oimchat.client.general.kernel.work.module.contact.sender;

import java.util.List;

import com.oimchat.client.general.kernel.work.module.contact.data.dto.ContactAddApplyData;
import com.oimchat.client.general.kernel.work.module.contact.data.dto.ContactAddApplyQuery;
import com.oimchat.client.general.kernel.work.module.contact.data.dto.ContactAddHandleData;
import com.oimchat.client.general.kernel.work.module.contact.data.dto.VerifyAnswer;
import com.onlyxiahui.common.annotation.parameter.Define;
import com.onlyxiahui.common.data.common.data.Page;
import com.onlyxiahui.framework.action.dispatcher.annotation.ActionMapping;
import com.onlyxiahui.framework.net.handler.data.action.DataBackAction;
import com.onlyxiahui.wofa.client.net.send.annotation.Sender;

/**
 * Description <br>
 * Date 2021-03-15 15:02:12<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */
@Sender
@ActionMapping(value = "1.2.001")
public interface ContactSender {

	@ActionMapping(value = "1.1.0006")
	public void getContactAddVerifySetting(
			@Define("body.targetUserId") String targetUserId,
			DataBackAction back);

	@ActionMapping(value = "1.1.0007")
	public void sendAddApply(
			@Define("body.apply") ContactAddApplyData apply,
			@Define("body.answers") List<VerifyAnswer> answers,
			DataBackAction back);

	@ActionMapping(value = "1.1.0008")
	public void getApplyCount(
			@Define("body.query") ContactAddApplyQuery query,
			DataBackAction back);

	@ActionMapping(value = "1.1.0009")
	public void queryApplyReceiveList(
			@Define("body.query") ContactAddApplyQuery query,
			@Define("body.page") Page page,
			DataBackAction back);

	@ActionMapping(value = "1.1.0010")
	public void queryApplyDataReceiveList(
			@Define("body.query") ContactAddApplyQuery query,
			@Define("body.page") Page page,
			DataBackAction back);

	public void getApplyCaseById(
			@Define("body.applyId") String applyId, DataBackAction back);

	@ActionMapping(value = "1.1.0012")
	public void applyHandle(
			@Define("body.handle") ContactAddHandleData handle,
			DataBackAction back);

}
