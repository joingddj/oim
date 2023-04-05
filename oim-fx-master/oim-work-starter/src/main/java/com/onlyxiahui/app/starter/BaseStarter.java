
package com.onlyxiahui.app.starter;

import java.util.List;

import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-11 10:07:14<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public interface BaseStarter {

	List<Class<?>> onAfter();

	void start(AppContext appContext);
}
