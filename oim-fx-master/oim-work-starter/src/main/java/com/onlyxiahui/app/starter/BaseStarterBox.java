
package com.onlyxiahui.app.starter;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-11 10:09:03<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class BaseStarterBox extends AbstractMaterial {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final List<BaseStarter> list = new ArrayList<>();

	public BaseStarterBox(AppContext appContext) {
		super(appContext);
	}

	public void add(BaseStarter enter) {
		if (null != enter && !list.contains(enter)) {
			list.add(enter);
		}
	}

	public void start() {
		List<Class<?>> completeList = new ArrayList<>();
		start(list, completeList);
	}

	private void start(List<BaseStarter> readyList, List<Class<?>> completeList) {
		if (!readyList.isEmpty()) {
			List<BaseStarter> tempList = new ArrayList<>();
			for (BaseStarter enter : readyList) {
				List<Class<?>> cs = enter.onAfter();

				if (null != cs) {
					boolean can = true;
					for (Class<?> tc : cs) {
						if (!completeList.contains(tc)) {
							can = false;
							break;
						}
					}

					if (can) {
						enter.start(appContext);
						completeList.add(enter.getClass());
					} else {
						if (hasAllAfter(cs)) {
							tempList.add(enter);
						}
					}
				} else {
					enter.start(appContext);
					completeList.add(enter.getClass());
				}
			}
			start(tempList, completeList);
		}
	}

	private boolean hasAllAfter(List<Class<?>> cs) {
		boolean has = true;
		if (null != cs) {
			for (Class<?> tc : cs) {
				boolean ex = false;
				for (BaseStarter enter : list) {
					if (tc == enter.getClass()) {
						ex = true;
						break;
					}
				}
				if (!ex) {
					has = false;
					break;
				}
			}
		}
		return has;
	}
}
