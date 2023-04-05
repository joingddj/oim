
package com.oimchat.client.platform.fx.module.list.function;

import com.oimchat.app.fx.base.component.list.ListNodePane;
import com.oimchat.client.general.kernel.work.module.contact.entity.ContactCategory;
import com.oimchat.client.platform.fx.module.list.wrap.ContactCategoryMenuWrap;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;

/**
 * Description <br>
 * Date 2021-03-25 14:31:25<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ContactCategoryNodeFunction extends AbstractMaterial {

	public ContactCategoryNodeFunction(AppContext appContext) {
		super(appContext);
	}

	public void setEvent(ListNodePane node) {
		node.setOnContextMenuRequested(e -> {
			e.consume();
			ContactCategory data = node.getAttribute(ContactCategory.class);
			ContactCategoryMenuWrap menu = appContext.getObject(ContactCategoryMenuWrap.class);
			menu.setCategory(data);
			menu.show(node, e.getScreenX(), e.getScreenY());
		});
	}
}
