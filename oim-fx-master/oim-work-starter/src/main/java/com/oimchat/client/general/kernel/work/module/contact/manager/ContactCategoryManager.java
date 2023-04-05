
package com.oimchat.client.general.kernel.work.module.contact.manager;

import java.util.ArrayList;
import java.util.List;

import com.oimchat.client.basic.util.PageUtil;
import com.oimchat.client.common.asyn.ValueBack;
import com.oimchat.client.general.kernel.work.module.contact.box.ContactCategoryBox;
import com.oimchat.client.general.kernel.work.module.contact.entity.ContactCategory;
import com.oimchat.client.general.kernel.work.module.contact.handler.ContactCategoryHandler;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.message.bean.Info;

/**
 * Description <br>
 * Date 2021-03-16 11:33:30<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ContactCategoryManager extends AbstractMaterial {

	public ContactCategoryManager(AppContext appContext) {
		super(appContext);
	}

	public void asynGetAllList(ValueBack<List<ContactCategory>> back) {
		ContactCategoryHandler handler = this.appContext.getObject(ContactCategoryHandler.class);
		handler.count((info, c) -> {
			Long l = (null == c) ? 0 : c.getCount();
			int size = l.intValue();
			if (size > 0) {
				List<ContactCategory> allList = new ArrayList<>(size);
				PageUtil.stream(size, 100, null, (n, p, q) -> {
					handler.list(p, (i, pr) -> {
						List<ContactCategory> list = (null != pr && null != pr.getItems()) ? pr.getItems() : new ArrayList<>();
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

	public void asynLoadAllList(ValueBack<List<ContactCategory>> back) {
		ContactCategoryBox box = this.appContext.getObject(ContactCategoryBox.class);
		asynGetAllList((info, list) -> {
			list.forEach((item) -> {
				box.put(item.getId(), item);
			});
			back.back(info, list);
		});
	}

	public void loadById(
			String categoryId,
			ValueBack<ContactCategory> back) {
		ContactCategoryBox box = this.appContext.getObject(ContactCategoryBox.class);
		ContactCategoryHandler handler = this.appContext.getObject(ContactCategoryHandler.class);
		handler.getById(categoryId, (info, data) -> {
			if (info.isSuccess() && null != data) {
				box.put(categoryId, data);
			}
			back.back(info, data);
		});
	}

	public void getOrLoadById(
			String categoryId,
			ValueBack<ContactCategory> back) {
		ContactCategoryBox box = this.appContext.getObject(ContactCategoryBox.class);
		ContactCategory data = box.get(categoryId);
		if (null == data) {
			loadById(categoryId, back);
		} else {
			back.back(new Info(), data);
		}
	}
}
