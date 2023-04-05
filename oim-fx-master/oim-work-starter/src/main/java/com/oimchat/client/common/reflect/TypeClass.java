
package com.oimchat.client.common.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Description <br>
 * Date 2021-03-16 18:05:14<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class TypeClass<T> {

	protected final Type type;

	/**
	 * Constructs a new type literal. Derives represented class from type parameter.
	 *
	 * <p>
	 * Clients create an empty anonymous subclass. Doing so embeds the type
	 * parameter in the anonymous class's type hierarchy so we can reconstitute it
	 * at runtime despite erasure.
	 */
	protected TypeClass() {
		Type superClass = getClass().getGenericSuperclass();
		Type type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
		this.type = type;
	}

	public Type getType() {
		return type;
	}
}
