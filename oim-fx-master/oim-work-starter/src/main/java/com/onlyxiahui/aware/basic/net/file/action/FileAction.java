package com.onlyxiahui.aware.basic.net.file.action;

/**
 * 
 * 
 * <br>
 * Date 2019-09-10 17:01:54<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @param <T>
 * @since 1.0.0
 */
public interface FileAction<T> {

	/**
	 * 
	 * 
	 * 进度<br>
	 * Date 2019-09-04 08:51:43<br>
	 * 
	 * @param speed
	 * @param size
	 * @param finishSize
	 * @param progress
	 * @since 1.0.0
	 */
	void progress(long speed, long size, long finishSize, double progress);

	/**
	 * 
	 * 
	 * 成功<br>
	 * Date 2019-09-04 08:51:51<br>
	 * 
	 * @param t
	 * @since 1.0.0
	 */
	void success(T t);

	/**
	 * 
	 * 
	 * 失败<br>
	 * Date 2019-09-04 08:52:05<br>
	 * 
	 * @param t
	 * @since 1.0.0
	 */
	void lost(T t);
}
