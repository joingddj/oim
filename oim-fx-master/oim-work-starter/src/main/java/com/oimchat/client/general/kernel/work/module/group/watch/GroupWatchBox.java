
package com.oimchat.client.general.kernel.work.module.group.watch;

import com.oimchat.client.general.kernel.work.module.group.entity.Group;

/**
 * Description <br>
 * Date 2021-03-17 11:48:27<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class GroupWatchBox extends AbstractWatchBox<GroupWatch> implements GroupWatch {

	@Override
	public void update(Group group) {
		super.watches().forEach((w) -> {
			w.update(group);
		});
	}
}
