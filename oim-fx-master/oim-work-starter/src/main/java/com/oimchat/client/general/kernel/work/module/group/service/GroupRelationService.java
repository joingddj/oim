
package com.oimchat.client.general.kernel.work.module.group.service;

import java.util.List;

import com.oimchat.client.general.kernel.work.module.group.box.GroupRelationBox;
import com.oimchat.client.general.kernel.work.module.group.entity.GroupRelation;
import com.oimchat.client.general.kernel.work.module.group.manager.GroupRelationManager;
import com.oimchat.client.general.kernel.work.module.group.observer.GroupRelationObservable;
import com.oimchat.client.general.kernel.work.module.group.observer.listener.GroupRelationEventListener;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-16 11:33:30<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class GroupRelationService extends AbstractMaterial {

	public GroupRelationService(AppContext appContext) {
		super(appContext);
	}

	public void add(String groupId) {
		GroupRelationObservable o = this.appContext.getObject(GroupRelationObservable.class);
		GroupRelationManager manager = this.appContext.getObject(GroupRelationManager.class);
		manager.loadByGroupId(groupId, (info, data) -> {
			if (info.isSuccess() && null != data) {
				o.executeAdd(data);
			}
		});
	}

	public void updateRemark(String groupId, String remark) {
		GroupRelationObservable o = this.appContext.getObject(GroupRelationObservable.class);
		GroupRelationManager manager = this.appContext.getObject(GroupRelationManager.class);
		manager.loadByGroupId(groupId, (info, data) -> {
			if (info.isSuccess() && null != data) {
				o.executeUpdate(data);
			}
		});
	}

	public void moveCategory(List<String> groupIds, String categoryId) {
		GroupRelationManager manager = this.appContext.getObject(GroupRelationManager.class);
		manager.asynLoadAllList((info, list) -> {

		});
		GroupRelationEventListener o = this.appContext.getObject(GroupRelationEventListener.class);
		o.moveCategory(groupIds, categoryId);
	}

	public void delete(String groupId) {
		GroupRelationObservable o = this.appContext.getObject(GroupRelationObservable.class);
		GroupRelationBox box = this.appContext.getObject(GroupRelationBox.class);
		List<GroupRelation> list = box.getListByItemKey(groupId);
		box.removeItem(groupId);
		if (list.isEmpty()) {
			for (GroupRelation data : list) {
				o.executeDelete(data);
			}
		} else {
			GroupRelation data = new GroupRelation();
			data.setGroupId(groupId);
			o.executeDelete(data);
		}
	}
}
