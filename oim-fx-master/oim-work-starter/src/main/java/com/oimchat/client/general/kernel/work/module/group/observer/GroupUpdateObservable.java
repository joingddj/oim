
package com.oimchat.client.general.kernel.work.module.group.observer;

import com.oimchat.client.general.kernel.work.common.observer.BaseUpdateObservable;
import com.oimchat.client.general.kernel.work.module.group.entity.Group;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-31 10:50:51<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class GroupUpdateObservable extends BaseUpdateObservable<Group> {

	public GroupUpdateObservable(AppContext appContext) {
		super(appContext);
	}

}
