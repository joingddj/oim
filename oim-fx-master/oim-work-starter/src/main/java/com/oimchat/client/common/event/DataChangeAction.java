
package com.oimchat.client.common.event;

/**
 * Description <br>
 * Date 2021-03-10 15:39:49<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public interface DataChangeAction<T> {

	void add(T data);

	void update(T data);

	void delete(T data);
}
