package com.onlyxiahui.app.basic.storage.manager;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Description <br>
 * Date 2020-05-29 15:51:06<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public abstract class AbstractEntityType<T> {

	@SuppressWarnings("unchecked")
	public Class<T> getEntityClass() {
		Type type = this.getClass().getGenericSuperclass();
		ParameterizedType parameterizedType = (ParameterizedType) type;
		Type[] types = parameterizedType.getActualTypeArguments();
		Class<T> entityClass = null;
		if (types.length > 0) {
			Type valueType = types[0];
			if (valueType instanceof Class<?>) {
				entityClass = (Class<T>) valueType;
			}
		}
		return entityClass;
	}
}
