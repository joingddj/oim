
package com.oimchat.client.general.kernel.work.module.group.manager;

import java.util.ArrayList;
import java.util.List;

import com.oimchat.client.basic.util.PageUtil;
import com.oimchat.client.common.asyn.ValueBack;
import com.oimchat.client.general.kernel.work.module.group.box.GroupRelationBox;
import com.oimchat.client.general.kernel.work.module.group.entity.GroupRelation;
import com.oimchat.client.general.kernel.work.module.group.handler.GroupRelationHandler;
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

public class GroupRelationManager extends AbstractMaterial {

	public GroupRelationManager(AppContext appContext) {
		super(appContext);
	}

	public void asynGetAllList(ValueBack<List<GroupRelation>> back) {
		GroupRelationHandler handler = this.appContext.getObject(GroupRelationHandler.class);
		handler.count((info, c) -> {
			Long l = (null == c) ? 0 : c.getCount();
			int size = l.intValue();
			if (size > 0) {
				List<GroupRelation> allList = new ArrayList<>(size);
				PageUtil.stream(size, 100, null, (n, p, q) -> {
					handler.list(p, (i, pr) -> {
						List<GroupRelation> list = (null != pr && null != pr.getItems()) ? pr.getItems() : new ArrayList<>();
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

	public void asynLoadAllList(ValueBack<List<GroupRelation>> back) {
		GroupRelationBox box = this.appContext.getObject(GroupRelationBox.class);
		asynGetAllList((info, list) -> {
			list.forEach((item) -> {
				box.put(item.getCategoryId(), item.getGroupId(), item);
			});
			back.back(info, list);
		});
	}

	public void loadByGroupId(String groupId, ValueBack<GroupRelation> back) {
		GroupRelationBox box = this.appContext.getObject(GroupRelationBox.class);
		GroupRelationHandler handler = this.appContext.getObject(GroupRelationHandler.class);
		handler.getByGroupId(groupId, (info, data) -> {
			if (info.isSuccess() && null != data) {
				box.put(data.getCategoryId(), data.getGroupId(), data);
			}
			back.back(info, data);
		});
	}

	public void getOrLoadByGroupId(String groupId, ValueBack<GroupRelation> back) {
		GroupRelationBox box = this.appContext.getObject(GroupRelationBox.class);
		List<GroupRelation> list = box.getListByItemKey(groupId);
		if (list.isEmpty()) {
			loadByGroupId(groupId, back);
		} else {
			back.back(new Info(), list.get(0));
		}
	}
}
