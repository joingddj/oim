
package com.oimchat.client.general.common.watch;

/**
 * Description <br>
 * Date 2021-03-17 14:02:52<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public interface DataWatch<T> {

	void add(T data);

	void update(T data);

	void delete(T data);
}
