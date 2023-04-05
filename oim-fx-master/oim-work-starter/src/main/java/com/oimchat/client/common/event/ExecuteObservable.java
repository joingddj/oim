
package com.oimchat.client.common.event;

import java.util.ArrayList;
import java.util.List;

/**
 * Description <br>
 * Date 2021-03-10 15:42:46<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ExecuteObservable {

	List<ExecuteAction> list = new ArrayList<>();

	public void add(ExecuteAction ea) {
		if (null != ea && !list.contains(ea)) {
			list.add(ea);
		}
	}

	public void execute() {
		for (ExecuteAction ea : list) {
			ea.execute();
		}
	}
}
