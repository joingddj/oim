package com.oimchat.client.common.event;

/**
 * 描述：
 * 
 * @author XiaHui
 * @date 2016年1月17日 下午12:23:03
 * @version 0.0.1
 */
public interface CallBackAction<T> {

	public void execute(T t);
}
