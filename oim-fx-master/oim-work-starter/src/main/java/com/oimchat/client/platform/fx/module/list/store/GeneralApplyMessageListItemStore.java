
package com.oimchat.client.platform.fx.module.list.store;

import com.oimchat.app.fx.base.component.head.HeadItem;
import com.oimchat.client.platform.fx.module.list.interaction.GeneralApplyMessageInteraction;
import com.oimchat.client.platform.fx.module.list.store.type.GeneralApplyType;
import com.oimchat.client.platform.kernel.work.common.box.SystemInformationHeadBox;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.app.view.fx.common.util.ImageLoadUtil;
import com.onlyxiahui.common.message.bean.Info;

import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

/**
 * Description <br>
 * Date 2021-04-02 11:14:26<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class GeneralApplyMessageListItemStore extends BaseMessageListItemStore<Integer> {

	public GeneralApplyMessageListItemStore(AppContext appContext) {
		super(appContext);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public String type() {
		return "general_apply_item";
	}

	@Override
	public String getKeyByData(Integer data) {
		return getKeyById(data + "");
	}

	@Override
	protected Info checkRemove(Integer data) {
		return new Info();
	}

	@Override
	protected void onRemove(Integer data) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onSelect(Integer data) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initialize(HeadItem item, Integer data) {

		SystemInformationHeadBox headBox = this.appContext.getObject(SystemInformationHeadBox.class);
		Image image = ImageLoadUtil.getImageByUrl(headBox.getHeadUrl().toExternalForm());
		item.setHeadImage(image);

		if (GeneralApplyType.ContactAddApply == data) {
			item.setRemark("添加好友请求");
		}
		if (GeneralApplyType.GroupJoinApply == data) {
			item.setRemark("加入群申请");
		}
		if (GeneralApplyType.GroupInviteApply == data) {
			item.setRemark("邀请加入群申请");
		}
		if (GeneralApplyType.GroupInviteeApply == data) {
			item.setRemark("被邀请加入群");
		}

		item.setOnMouseClicked((MouseEvent me) -> {
			if (me.getClickCount() == 2) {
				GeneralApplyMessageInteraction apply = appContext.getObject(GeneralApplyMessageInteraction.class);
				apply.show(data);
			}
			me.consume();
		});
	}

	@Override
	protected void setInfo(HeadItem item, Integer data) {
		// TODO Auto-generated method stub
	}
}
