
package com.oimchat.client.common.net.back;

import com.oimchat.client.common.asyn.InfoBuilder;
import com.oimchat.client.common.asyn.ValueBack;
import com.oimchat.client.common.asyn.ValueBackInvoker;
import com.onlyxiahui.framework.net.handler.data.action.DataBackAction;

/**
 * Description <br>
 * Date 2021-03-11 16:39:46<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public abstract class ValueBackActionAdapter<T> implements DataBackAction {

	ValueBack<T> ib;

	public ValueBackActionAdapter(ValueBack<T> ib) {
		this.ib = ib;
	}

	@Override
	public void lost() {
		ValueBackInvoker.back(ib, InfoBuilder.warning("4.001", "请求失败"), null);
	}

	@Override
	public void timeOut() {
		ValueBackInvoker.back(ib, InfoBuilder.warning("4.001", "请求超时"), null);
	}
}
