
package com.oimchat.client.general.kernel.work.common.cleaner;

import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-27 21:49:03<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public abstract class AbstractWorkCleaner extends AbstractMaterial implements WorCleaner {

	public AbstractWorkCleaner(AppContext appContext) {
		super(appContext);
		put();
	}

	private void put() {
		WorCleanerBox box = this.appContext.getObject(WorCleanerBox.class);
		box.add(this);
	}
}
