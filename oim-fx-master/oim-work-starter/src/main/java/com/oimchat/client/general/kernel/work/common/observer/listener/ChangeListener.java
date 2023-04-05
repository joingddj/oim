
package com.oimchat.client.general.kernel.work.common.observer.listener;

/**
 * Description <br>
 * Date 2021-03-10 15:38:19<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public interface ChangeListener<T> {

	void change(T newValue, T oldValue);
}
