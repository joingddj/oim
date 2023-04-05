
package com.oimchat.client.platform.common.config.base;

import com.oimchat.client.general.basic.config.JsonConfigAccess;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Description <br>
 * Date 2021-03-10 17:29:48<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public abstract class BaseConfigAccess<T> {

	private T data;

	public abstract String getFilePath();

	public abstract boolean isCache();

	public T get() {
		if (null != data) {
			return data;
		}
		String path = getFilePath();
		T v = JsonConfigAccess.getByFilePath(path, getEntityClass());
		if (isCache()) {
			this.data = v;
		}
		return v;
	}

	public void save(T data) {
		String path = getFilePath();
		JsonConfigAccess.addOrUpdate(path, data);
		if (isCache()) {
			this.data = data;
		}
	}

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
