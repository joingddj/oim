
package com.oimchat.client.general.kernel.work.module.contact.manager;

import java.util.ArrayList;
import java.util.List;

import com.oimchat.client.basic.util.PageUtil;
import com.oimchat.client.common.asyn.ValueBack;
import com.oimchat.client.general.kernel.work.module.contact.box.ContactRelationBox;
import com.oimchat.client.general.kernel.work.module.contact.entity.ContactRelation;
import com.oimchat.client.general.kernel.work.module.contact.handler.ContactRelationHandler;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.message.bean.Info;

/**
 * Description <br>
 * Date 2021-03-16 15:59:34<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ContactRelationManager extends AbstractMaterial {

	public ContactRelationManager(AppContext appContext) {
		super(appContext);
	}

	public void asynGetAllList(ValueBack<List<ContactRelation>> back) {
		ContactRelationHandler handler = this.appContext.getObject(ContactRelationHandler.class);
		handler.count((info, c) -> {
			Long l = (null == c) ? 0 : c.getCount();
			int size = l.intValue();
			if (size > 0) {
				List<ContactRelation> allList = new ArrayList<>(size);
				PageUtil.stream(size, 100, null, (n, p, q) -> {
					handler.list(p, (i, pr) -> {
						List<ContactRelation> list = (null != pr && null != pr.getItems()) ? pr.getItems() : new ArrayList<>();
						allList.addAll(list);
						if (p.getNumber() >= p.getTotalPage()) {
							back.back(info, allList);
						} else {
							n.next();
						}
					});
				});
			} else {
				back.back(info, new ArrayList<>());
			}
		});
	}

	public void asynLoadAllList(ValueBack<List<ContactRelation>> back) {
		ContactRelationBox box = this.appContext.getObject(ContactRelationBox.class);
		asynGetAllList((info, list) -> {
			list.forEach((item) -> {
				box.put(item.getCategoryId(), item.getContactUserId(), item);
			});
			back.back(info, list);
		});
	}

	public void loadByContactUserId(String contactUserId,
			ValueBack<ContactRelation> back) {
		ContactRelationBox box = this.appContext.getObject(ContactRelationBox.class);
		ContactRelationHandler handler = this.appContext.getObject(ContactRelationHandler.class);
		handler.getByContactUserId(contactUserId, (info, data) -> {
			// TODO
			if (info.isSuccess() && null != data && null != data.getId()) {
				box.put(data.getCategoryId(), data.getContactUserId(), data);
			}
			back.back(info, data);
		});
	}

	public void getOrLoadByContactUserId(String contactUserId,
			ValueBack<ContactRelation> back) {
		ContactRelationBox box = this.appContext.getObject(ContactRelationBox.class);
		List<ContactRelation> list = box.getListByItemKey(contactUserId);
		if (list.isEmpty()) {
			loadByContactUserId(contactUserId, back);
		} else {
			back.back(new Info(), list.get(0));
		}
	}
}
