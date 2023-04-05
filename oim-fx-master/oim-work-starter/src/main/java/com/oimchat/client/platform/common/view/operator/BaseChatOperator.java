
package com.oimchat.client.platform.common.view.operator;

import com.oimchat.client.basic.common.data.im.message.content.Content;
import com.oimchat.client.basic.common.data.im.message.content.item.FileValue;
import com.oimchat.client.basic.common.data.im.util.CoreContentUtil;
import com.oimchat.client.basic.util.FileNameUtil;
import com.oimchat.client.common.net.file.bean.FileData;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-24 14:50:01<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class BaseChatOperator extends AbstractMaterial {

	public BaseChatOperator(AppContext appContext) {
		super(appContext);
	}

	public Content getFileContent(FileData fd) {

		String id = fd.getId();
		String name = fd.getName();
		long size = fd.getSize();
		String url = fd.getUrl();
		// String path = fd.getPath();

		String suffixName = FileNameUtil.getSuffixName(name);

		FileValue fv = new FileValue();
		fv.setExtension(suffixName);
		fv.setId(id);
		fv.setName(name);
		fv.setSize(size);
		fv.setUrl(url);
		fv.setType("1");

		Content content = CoreContentUtil.createContent(id, fv);
		return content;
	}
}
