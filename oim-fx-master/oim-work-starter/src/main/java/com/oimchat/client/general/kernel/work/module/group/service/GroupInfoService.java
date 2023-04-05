
package com.oimchat.client.general.kernel.work.module.group.service;

import com.oimchat.client.general.kernel.work.module.group.manager.GroupManager;
import com.oimchat.client.general.kernel.work.module.group.observer.GroupUpdateObservable;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-31 10:51:54<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class GroupInfoService extends AbstractMaterial {

	public GroupInfoService(AppContext appContext) {
		super(appContext);
	}

	public void update(String id) {
		GroupUpdateObservable o = this.appContext.getObject(GroupUpdateObservable.class);
		GroupManager manager = this.appContext.getObject(GroupManager.class);
		manager.loadById(id, (info, data) -> {
			if (info.isSuccess() && null != data) {
				o.execute(data);
			}
		});
	}

	public void updateHead(String id) {
		GroupUpdateObservable o = this.appContext.getObject(GroupUpdateObservable.class);
		GroupManager manager = this.appContext.getObject(GroupManager.class);
		manager.loadById(id, (info, data) -> {
			if (info.isSuccess() && null != data) {
				o.execute(data);
			}
		});
	}
}
