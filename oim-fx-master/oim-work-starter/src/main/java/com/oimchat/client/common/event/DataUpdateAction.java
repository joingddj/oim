
package com.oimchat.client.common.event;

/**
 * Description <br>
 * Date 2021-03-15 14:34:21<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public interface DataUpdateAction<T> {

	void value(T value);
}
