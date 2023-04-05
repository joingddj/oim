
package com.oimchat.client.general.kernel.work.module.group.manager;

import java.util.ArrayList;
import java.util.List;

import com.oimchat.client.basic.util.PageUtil;
import com.oimchat.client.common.asyn.ValueBack;
import com.oimchat.client.general.kernel.work.module.group.box.GroupCategoryBox;
import com.oimchat.client.general.kernel.work.module.group.entity.GroupCategory;
import com.oimchat.client.general.kernel.work.module.group.handler.GroupCategoryHandler;
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

public class GroupCategoryManager extends AbstractMaterial {

	public GroupCategoryManager(AppContext appContext) {
		super(appContext);
	}

	public void asynGetAllList(ValueBack<List<GroupCategory>> back) {
		GroupCategoryHandler handler = this.appContext.getObject(GroupCategoryHandler.class);
		handler.count((info, c) -> {
			Long l = (null == c) ? 0 : c.getCount();
			int size = l.intValue();
			if (size > 0) {
				List<GroupCategory> allList = new ArrayList<>(size);
				PageUtil.stream(size, 100, null, (n, p, q) -> {
					handler.list(p, (i, pr) -> {
						List<GroupCategory> list = (null != pr && null != pr.getItems()) ? pr.getItems() : new ArrayList<>();
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

	public void asynLoadAllList(ValueBack<List<GroupCategory>> back) {
		GroupCategoryBox box = this.appContext.getObject(GroupCategoryBox.class);
		asynGetAllList((info, list) -> {
			list.forEach((item) -> {
				box.put(item.getId(), item);
			});
			back.back(info, list);
		});
	}

	public void loadById(
			String categoryId,
			ValueBack<GroupCategory> back) {
		GroupCategoryBox box = this.appContext.getObject(GroupCategoryBox.class);
		GroupCategoryHandler handler = this.appContext.getObject(GroupCategoryHandler.class);
		handler.getById(categoryId, (info, data) -> {
			if (info.isSuccess() && null != data) {
				box.put(categoryId, data);
			}
			back.back(info, data);
		});
	}

	public void getOrLoadById(
			String categoryId,
			ValueBack<GroupCategory> back) {
		GroupCategoryBox box = this.appContext.getObject(GroupCategoryBox.class);
		GroupCategory data = box.get(categoryId);
		if (null == data) {
			loadById(categoryId, back);
		} else {
			back.back(new Info(), data);
		}
	}
}
