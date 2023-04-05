
package com.oimchat.client.general.common.sender;

import com.onlyxiahui.app.context.AbstractFactory;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.wofa.client.net.send.annotation.Sender;
import com.onlyxiahui.wofa.client.work.core.impl.SenderFactory;

/**
 * Description <br>
 * Date 2021-03-09 18:34:34<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class SenderBuildFactory extends AbstractFactory {

	public SenderBuildFactory(AppContext appContext) {
		super(appContext);
	}

	@Override
	public <T> boolean isSupport(Class<T> clazz) {
		boolean isSupport = false;
		if (null != clazz) {
			boolean isInterface = clazz.isInterface();
			boolean hasAction = null != clazz.getAnnotation(Sender.class);
			isSupport = isInterface && hasAction;
		}
		return isSupport;
	}

	public <T> T createObject(Class<T> classType) {
		SenderFactory sf = this.appContext.createObject(SenderFactory.class);
		return sf.getObject(classType);
	}
}
