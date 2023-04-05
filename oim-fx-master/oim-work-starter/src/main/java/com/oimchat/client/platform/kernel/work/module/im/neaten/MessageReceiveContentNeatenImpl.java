
package com.oimchat.client.platform.kernel.work.module.im.neaten;

import com.oimchat.client.basic.common.data.im.message.content.Content;
import com.oimchat.client.basic.common.data.im.message.content.Item;
import com.oimchat.client.basic.common.data.im.util.CoreContentUtil;
import com.oimchat.client.common.asyn.ValueBack;
import com.oimchat.client.general.kernel.work.module.im.neaten.MessageReceiveContentNeaten;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.message.bean.Info;

/**
 * Description <br>
 * Date 2021-03-22 17:25:57<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class MessageReceiveContentNeatenImpl extends AbstractMaterial implements MessageReceiveContentNeaten {

	public MessageReceiveContentNeatenImpl(AppContext appContext) {
		super(appContext);
	}

	@Override
	public void neaten(Content content, ValueBack<Content> back) {
		MessageReceiveItemConverter ic = this.appContext.getObject(MessageReceiveItemConverter.class);
		ic.convert(content);
		ic.handleFaceItem(CoreContentUtil.getFaceItemList(content));
		ic.handleFileItem(CoreContentUtil.getFileItemList(content));
		ic.handleTextItem(CoreContentUtil.getItemList(content, Item.type_text));
		back.back(new Info(), content);
	}
}
