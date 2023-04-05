
package com.oimchat.client.general.kernel.work.module.contact.handler;

import java.util.List;

import com.oimchat.client.common.asyn.InfoBack;
import com.oimchat.client.common.asyn.ValueBack;
import com.oimchat.client.common.net.back.InfoBackActionImpl;
import com.oimchat.client.common.net.back.ValueBackActionImpl;
import com.oimchat.client.general.kernel.work.module.contact.data.dto.ContactHarassSettingEntityCase;
import com.oimchat.client.general.kernel.work.module.contact.entity.ContactAddVerifyQuestion;
import com.oimchat.client.general.kernel.work.module.contact.entity.ContactHarassSetting;
import com.oimchat.client.general.kernel.work.module.contact.sender.ContactHarassSettingSender;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-15 15:49:07<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ContactHarassSettingHandler extends AbstractMaterial {

	public ContactHarassSettingHandler(AppContext appContext) {
		super(appContext);
	}

	public void get(
			ValueBack<ContactHarassSettingEntityCase> back) {
		ContactHarassSettingSender sender = this.appContext.getObject(ContactHarassSettingSender.class);
		sender.get(new ValueBackActionImpl<ContactHarassSettingEntityCase>(back, ContactHarassSettingEntityCase.class));
	}

	public void update(
			ContactHarassSetting setting,
			List<ContactAddVerifyQuestion> questions,
			InfoBack back) {
		ContactHarassSettingSender sender = this.appContext.getObject(ContactHarassSettingSender.class);
		sender.update(setting, questions, new InfoBackActionImpl(back));
	}
}
