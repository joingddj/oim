
package com.oimchat.client.common.event;

/**
 * Description <br>
 * Date 2021-03-14 16:48:33<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public interface KeyValueAction<K, T> {

	void value(K key, T value);
}
