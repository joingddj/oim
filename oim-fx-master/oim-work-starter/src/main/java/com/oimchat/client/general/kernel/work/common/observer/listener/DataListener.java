
package com.oimchat.client.general.kernel.work.common.observer.listener;

/**
 * Description <br>
 * Date 2021-03-10 15:39:49<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public interface DataListener<A, U, D> {

	void add(A data);

	void update(U data);

	void delete(D data);
}
