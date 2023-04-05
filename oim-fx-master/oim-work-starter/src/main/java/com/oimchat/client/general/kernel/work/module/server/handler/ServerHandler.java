
package com.oimchat.client.general.kernel.work.module.server.handler;

import java.util.List;

import com.oimchat.client.common.asyn.InfoBack;
import com.oimchat.client.common.asyn.InfoBackInvoker;
import com.oimchat.client.common.net.back.ValueBackActionImpl;
import com.oimchat.client.common.reflect.TypeClass;
import com.oimchat.client.general.kernel.work.module.server.box.ServerBox;
import com.oimchat.client.general.kernel.work.module.server.call.ServerCall;
import com.oimchat.client.general.kernel.work.module.server.data.dto.ServerData;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.data.common.data.ListBody;

/**
 * Description <br>
 * Date 2021-03-11 16:08:26<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ServerHandler extends AbstractMaterial {

	public ServerHandler(AppContext appContext) {
		super(appContext);
	}

	public void load(InfoBack ib) {
		ServerBox box = this.appContext.getObject(ServerBox.class);
		ServerCall call = this.appContext.getObject(ServerCall.class);

		call.getAddressList(new ValueBackActionImpl<ListBody<List<ServerData>>>((info, bl) -> {
			box.clear();
			if (null != bl && null != bl.getItems()) {
				box.addList(bl.getItems());
			}

			InfoBackInvoker.back(ib, info);
		}, new TypeClass<ListBody<List<ServerData>>>() {
		}));
	}
}
