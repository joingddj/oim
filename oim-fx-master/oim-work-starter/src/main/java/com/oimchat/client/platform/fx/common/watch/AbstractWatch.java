
package com.oimchat.client.platform.fx.common.watch;

import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-23 10:11:33<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public abstract class AbstractWatch extends AbstractMaterial {

	public AbstractWatch(AppContext appContext) {
		super(appContext);
		initialize();
	}

	public abstract void initialize();
}
