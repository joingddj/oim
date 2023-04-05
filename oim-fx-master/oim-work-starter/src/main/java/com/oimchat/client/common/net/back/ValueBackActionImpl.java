
package com.oimchat.client.common.net.back;

import java.lang.reflect.Type;

import org.jsoup.internal.StringUtil;

import com.oimchat.client.common.asyn.InfoBuilder;
import com.oimchat.client.common.asyn.ValueBack;
import com.oimchat.client.common.asyn.ValueBackInvoker;
import com.oimchat.client.common.reflect.TypeClass;
import com.onlyxiahui.common.annotation.parameter.Define;
import com.onlyxiahui.common.lib.util.json.JsonUtil;
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

public class ValueBackActionImpl<T> implements DataBackAction {

	ValueBack<T> ib;
	protected final Type type;

	public ValueBackActionImpl(ValueBack<T> ib, Class<T> type) {
		this.ib = ib;
		this.type = type;
	}

	public ValueBackActionImpl(ValueBack<T> ib, TypeClass<T> tr) {
		this.ib = ib;
		this.type = tr.getType();
	}

	@Override
	public void lost() {
		ValueBackInvoker.back(ib, InfoBuilder.warning("4.001", "请求失败"), null);
	}

	@Override
	public void timeOut() {
		ValueBackInvoker.back(ib, InfoBuilder.warning("4.001", "请求超时"), null);
	}

	@Back
	public void back(@Define("info") Info info, @Define("body") String body) {
		T data = (StringUtil.isBlank(body)) ? null : JsonUtil.toObject(body, type);
		ValueBackInvoker.back(ib, info, data);
	}
}
