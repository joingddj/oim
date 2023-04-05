
package com.oimchat.client.general.kernel.work.module.contact.handler;

import java.util.List;

import com.oimchat.client.common.asyn.InfoBack;
import com.oimchat.client.common.asyn.ValueBack;
import com.oimchat.client.common.net.back.InfoBackActionImpl;
import com.oimchat.client.common.net.back.ValueBackActionImpl;
import com.oimchat.client.common.reflect.TypeClass;
import com.oimchat.client.general.kernel.work.module.contact.data.dto.ContactAddApplyData;
import com.oimchat.client.general.kernel.work.module.contact.data.dto.ContactAddApplyEntityCase;
import com.oimchat.client.general.kernel.work.module.contact.data.dto.ContactAddApplyQuery;
import com.oimchat.client.general.kernel.work.module.contact.data.dto.ContactAddHandleData;
import com.oimchat.client.general.kernel.work.module.contact.data.dto.ContactVerifySettingDataCase;
import com.oimchat.client.general.kernel.work.module.contact.data.dto.VerifyAnswer;
import com.oimchat.client.general.kernel.work.module.contact.entity.ContactAddApply;
import com.oimchat.client.general.kernel.work.module.contact.sender.ContactSender;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.data.common.bean.CountInfo;
import com.onlyxiahui.common.data.common.data.Page;
import com.onlyxiahui.common.data.common.data.PageResult;

/**
 * Description <br>
 * Date 2021-03-15 15:16:57<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ContactHandler extends AbstractMaterial {

	public ContactHandler(AppContext appContext) {
		super(appContext);
	}

	public void getContactAddVerifySetting(
			String targetUserId,
			ValueBack<ContactVerifySettingDataCase> back) {
		ContactSender sender = this.appContext.getObject(ContactSender.class);
		sender.getContactAddVerifySetting(targetUserId, new ValueBackActionImpl<ContactVerifySettingDataCase>(back, ContactVerifySettingDataCase.class));
	}

	public void sendAddApply(
			ContactAddApplyData apply,
			List<VerifyAnswer> answers,
			InfoBack back) {
		ContactSender sender = this.appContext.getObject(ContactSender.class);
		sender.sendAddApply(apply, answers, new InfoBackActionImpl(back));
	}

	public void getApplyCount(
			ContactAddApplyQuery query,
			ValueBack<CountInfo> back) {
		ContactSender sender = this.appContext.getObject(ContactSender.class);
		sender.getApplyCount(query, new ValueBackActionImpl<CountInfo>(back, CountInfo.class));
	}

	public void queryApplyReceiveList(
			ContactAddApplyQuery query,
			Page page,
			ValueBack<PageResult<List<ContactAddApply>>> back) {
		ContactSender sender = this.appContext.getObject(ContactSender.class);
		sender.queryApplyReceiveList(
				query, page,
				new ValueBackActionImpl<PageResult<List<ContactAddApply>>>(back, new TypeClass<PageResult<List<ContactAddApply>>>() {
				}));
	}

	public void queryApplyDataReceiveList(
			ContactAddApplyQuery query,
			Page page,
			ValueBack<PageResult<List<ContactAddApplyEntityCase>>> back) {
		ContactSender sender = this.appContext.getObject(ContactSender.class);
		sender.queryApplyDataReceiveList(
				query, page,
				new ValueBackActionImpl<PageResult<List<ContactAddApplyEntityCase>>>(back,
						new TypeClass<PageResult<List<ContactAddApplyEntityCase>>>() {
						}));
	}

	public void getApplyCaseById(String applyId, ValueBack<ContactAddApplyEntityCase> back) {
		ContactSender sender = this.appContext.getObject(ContactSender.class);
		sender.getApplyCaseById(applyId, new ValueBackActionImpl<ContactAddApplyEntityCase>(back, ContactAddApplyEntityCase.class));
	}

	public void applyHandle(
			ContactAddHandleData handle,
			InfoBack back) {
		ContactSender sender = this.appContext.getObject(ContactSender.class);
		sender.applyHandle(handle, new InfoBackActionImpl(back));
	}
}
