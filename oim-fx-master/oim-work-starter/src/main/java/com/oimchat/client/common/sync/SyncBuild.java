
package com.oimchat.client.common.sync;

/**
 * Description <br>
 * Date 2020-12-28 19:40:46<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public interface SyncBuild<T> {

	void build(SyncPut<T> sp);
}
