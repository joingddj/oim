
package com.oimchat.client.general.kernel.work.common.call;

import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.message.request.AbstractRequestMessage;
import com.onlyxiahui.framework.net.handler.data.action.DataBackAction;

/**
 * Description <br>
 * Date 2021-03-10 16:11:13<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class BaseCall extends AbstractMaterial {

	public BaseCall(AppContext appContext) {
		super(appContext);
	}

	public void execute(String url, AbstractRequestMessage<?> data, DataBackAction dataBackAction) {
		HttpWrap hh = appContext.getObject(HttpWrap.class);
		hh.execute(url, data, dataBackAction);
	}
}
