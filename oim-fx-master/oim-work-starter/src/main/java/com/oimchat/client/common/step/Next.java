
package com.oimchat.client.common.step;

/**
 * Description <br>
 * Date 2021-03-10 09:08:24<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public interface Next<R, V> {

	NextResult<R> execute(V v);
}
