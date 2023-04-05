package com.onlyxiahui.app.view.fx.common.data;

/**
 * 
 * 数据转换 <br>
 * Date 2020-03-12 17:24:40<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @param <T>
 * @param <E>
 * @since 1.0.0
 */
public interface DataConverter<T, E> {

	E converter(T data);
}
