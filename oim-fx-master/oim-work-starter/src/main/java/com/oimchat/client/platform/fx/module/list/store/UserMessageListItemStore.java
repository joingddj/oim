
package com.oimchat.client.platform.fx.module.list.store;

import com.oimchat.app.fx.base.component.head.HeadItem;
import com.oimchat.client.general.kernel.work.module.contact.manager.ContactRelationManager;
import com.oimchat.client.general.kernel.work.module.core.entity.User;
import com.oimchat.client.general.kernel.work.module.core.event.UserStatusObservable;
import com.oimchat.client.platform.fx.module.list.function.UserMessageItemFunction;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.message.bean.Info;

/**
 * Description <br>
 * Date 2021-04-01 16:43:13<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class UserMessageListItemStore extends BaseMessageListItemStore<User> {

	public UserMessageListItemStore(AppContext appContext) {
		super(appContext);
		init();
	}

	public void init() {
		UserStatusObservable ul = this.appContext.getObject(UserStatusObservable.class);
		ul.addListener((id, status) -> {
			updateStatus(id, status);
		});
	}

	private void updateStatus(String id, String status) {
		// TODO Auto-generated method stub

	}

	@Override
	public String type() {
		return "user_message_item";
	}

	@Override
	public String getKeyByData(User data) {
		return getKeyById(data.getId());
	}

	@Override
	protected Info checkRemove(User data) {
		return new Info();
	}

	@Override
	protected void onRemove(User data) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onSelect(User data) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initialize(HeadItem item, User data) {
		UserMessageItemFunction function = this.appContext.getObject(UserMessageItemFunction.class);
		function.setEvent(item);
	}

	@Override
	protected void setInfo(HeadItem item, User data) {
		UserMessageItemFunction function = this.appContext.getObject(UserMessageItemFunction.class);

		String userId = data.getId();
		item.addAttribute(User.class, data);
		ContactRelationManager cch = this.appContext.getObject(ContactRelationManager.class);
		cch.getOrLoadByContactUserId(userId, (info, relation) -> {
			String remark = (null == relation) ? null : relation.getRemark();
			function.setInfo(item, data, remark);
		});
	}
}
