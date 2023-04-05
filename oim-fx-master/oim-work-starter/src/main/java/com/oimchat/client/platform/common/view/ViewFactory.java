package com.oimchat.client.platform.common.view;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.oimchat.client.platform.fx.view.impl.classics.BaseStageView;
import com.onlyxiahui.app.basic.view.View;
import com.onlyxiahui.app.context.AbstractFactory;
import com.onlyxiahui.app.context.AppContext;

/**
 * 
 * @author XiaHui
 * @date 2015年3月16日 下午2:24:31
 */
public class ViewFactory extends AbstractFactory {

	private Map<Class<?>, Class<?>> map = new ConcurrentHashMap<Class<?>, Class<?>>();
	private Map<Class<?>, Object> objectMap = new ConcurrentHashMap<Class<?>, Object>();

	public ViewFactory(AppContext appContext) {
		super(appContext);
	}

	public void register(Class<? extends View> classType, Class<? extends BaseStageView<?>> view) {
		map.put(classType, view);
	}

	@SuppressWarnings("unchecked")
	public <T> T getSingleView(Class<? extends View> classType) {
		Object o = null;
		Class<?> view = map.get(classType);
		if (null != view) {
			o = objectMap.get(view);
			if (null == o) {
				o = super.getObject(view);
				objectMap.put(view, o);
			}
		}
		return (T) o;
	}

	@SuppressWarnings("unchecked")
	public <T> T getNewView(Class<? extends View> classType) {
		Object o = null;
		Class<?> view = map.get(classType);
		if (null != view) {
			o = super.getObject(view);
		}
		return (T) o;
	}

	@Override
	public <T> boolean isSupport(Class<T> clazz) {
		return true;
	}
}
