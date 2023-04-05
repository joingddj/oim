
package com.oimchat.client.general.kernel.work.common.cleaner;

import java.util.ArrayList;
import java.util.List;

import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-27 21:49:33<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class WorCleanerBox extends AbstractMaterial {

	public WorCleanerBox(AppContext appContext) {
		super(appContext);
	}

	List<WorCleaner> list = new ArrayList<>();

	public void add(WorCleaner e) {
		if (!list.contains(e)) {
			list.add(e);
		}
	}

	public void initialize() {
		for (WorCleaner e : list) {
			e.clear();
		}
	}
}
