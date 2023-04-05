
package com.oimchat.client.platform.fx.work.inform.converge;

import com.oimchat.client.general.kernel.work.module.contact.data.dto.ContactAddApplyQuery;
import com.oimchat.client.general.kernel.work.module.contact.entity.ContactAddApply;
import com.oimchat.client.general.kernel.work.module.contact.handler.ContactHandler;
import com.oimchat.client.general.kernel.work.module.group.data.query.GroupInviteVerifyQuery;
import com.oimchat.client.general.kernel.work.module.group.data.query.GroupInviteeApplyQuery;
import com.oimchat.client.general.kernel.work.module.group.data.query.GroupJoinApplyQuery;
import com.oimchat.client.general.kernel.work.module.group.entity.GroupInviteApply;
import com.oimchat.client.general.kernel.work.module.group.entity.GroupJoinApply;
import com.oimchat.client.general.kernel.work.module.group.handler.GroupInviteHandler;
import com.oimchat.client.general.kernel.work.module.group.handler.GroupJoinHandler;
import com.oimchat.client.platform.fx.module.list.store.type.GeneralApplyType;
import com.oimchat.client.platform.fx.work.inform.service.GeneralApplyInformationService;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-04-09 09:45:13<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class GeneralApplyInformationConverge extends AbstractMaterial {

	public GeneralApplyInformationConverge(AppContext appContext) {
		super(appContext);
	}

	public void refreshContactApplyNotice() {
		GeneralApplyInformationService service = this.appContext.getObject(GeneralApplyInformationService.class);

		ContactAddApplyQuery query = new ContactAddApplyQuery();
		query.setHandleType(ContactAddApply.handle_type_untreated);
		ContactHandler handler = this.appContext.getObject(ContactHandler.class);
		handler.getApplyCount(query, (info, c) -> {
			long count = (null == c) ? 0 : c.getCount();
			if (count > 0) {
				service.inform(GeneralApplyType.ContactAddApply, count, false);
			}
		});
	}

	public void refreshGroupJoinNotice() {
		GeneralApplyInformationService service = this.appContext.getObject(GeneralApplyInformationService.class);

		GroupJoinApplyQuery query = new GroupJoinApplyQuery();
		query.setHandleType(GroupJoinApply.handle_type_untreated);
		GroupJoinHandler handler = this.appContext.getObject(GroupJoinHandler.class);
		handler.queryJoinApplyCount(query, (info, c) -> {
			long count = (null == c) ? 0 : c.getCount();
			if (count > 0) {
				service.inform(GeneralApplyType.GroupJoinApply, count, false);
			}
		});
	}

	public void refreshGroupInviteNotice() {
		GeneralApplyInformationService service = this.appContext.getObject(GeneralApplyInformationService.class);

		GroupInviteVerifyQuery query = new GroupInviteVerifyQuery();
		query.setVerifyHandleType(GroupInviteApply.verify_handle_type_untreated);
		GroupInviteHandler handler = this.appContext.getObject(GroupInviteHandler.class);
		handler.queryInviteApplyCount(query, (info, c) -> {
			long count = (null == c) ? 0 : c.getCount();
			if (count > 0) {
				service.inform(GeneralApplyType.GroupInviteApply, count, false);
			}
		});
	}

	public void refreshGroupInviteeNotice() {
		GeneralApplyInformationService service = this.appContext.getObject(GeneralApplyInformationService.class);

		GroupInviteeApplyQuery query = new GroupInviteeApplyQuery();
		query.setInviteeHandleType(GroupInviteApply.invitee_handle_type_untreated);
		GroupInviteHandler handler = this.appContext.getObject(GroupInviteHandler.class);
		handler.queryInviteeCount(query, (info, c) -> {
			long count = (null == c) ? 0 : c.getCount();
			if (count > 0) {
				service.inform(GeneralApplyType.GroupInviteeApply, count, false);
			}
		});
	}
}
