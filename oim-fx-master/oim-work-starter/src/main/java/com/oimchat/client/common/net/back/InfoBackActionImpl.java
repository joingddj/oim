
package com.oimchat.client.common.net.back;

import com.oimchat.client.common.asyn.InfoBack;
import com.oimchat.client.common.asyn.InfoBackInvoker;
import com.onlyxiahui.common.annotation.parameter.Define;
import com.onlyxiahui.common.message.bean.Info;
import com.onlyxiahui.framework.net.handler.data.action.DataBackAction;
import com.onlyxiahui.wofa.client.net.core.back.annotation.Back;

/**
 * Description <br>
 * Date 2021-03-11 16:39:46<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class InfoBackActionImpl implements DataBackAction {

	InfoBack ib;

	public InfoBackActionImpl(InfoBack ib) {
		this.ib = ib;
	}

	@Override
	public void lost() {
		InfoBackInvoker.warning(ib, "4.001", "请求失败");
	}

	@Override
	public void timeOut() {
		InfoBackInvoker.warning(ib, "4.001", "请求超时");
	}

	@Back
	public void back(@Define("info") Info info) {
		InfoBackInvoker.back(ib, info);
	}
}
