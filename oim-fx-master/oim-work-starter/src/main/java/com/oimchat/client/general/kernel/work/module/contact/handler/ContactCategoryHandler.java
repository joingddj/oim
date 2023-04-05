
package com.oimchat.client.general.kernel.work.module.contact.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.oimchat.client.common.asyn.InfoBack;
import com.oimchat.client.common.asyn.ValueBack;
import com.oimchat.client.common.net.back.InfoBackActionImpl;
import com.oimchat.client.common.net.back.ValueBackActionImpl;
import com.oimchat.client.common.reflect.TypeClass;
import com.oimchat.client.general.kernel.work.module.contact.entity.ContactCategory;
import com.oimchat.client.general.kernel.work.module.contact.sender.ContactCategorySender;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.data.common.bean.CountInfo;
import com.onlyxiahui.common.data.common.data.Page;
import com.onlyxiahui.common.data.common.data.PageResult;

/**
 * Description <br>
 * Date 2021-03-15 15:41:36<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ContactCategoryHandler extends AbstractMaterial {

	public ContactCategoryHandler(AppContext appContext) {
		super(appContext);
	}

	public void count(
			ValueBack<CountInfo> back) {
		ContactCategorySender sender = this.appContext.getObject(ContactCategorySender.class);
		sender.count(new ValueBackActionImpl<CountInfo>(back, CountInfo.class));
	}

	public void list(
			Page page,
			ValueBack<PageResult<List<ContactCategory>>> back) {
		ContactCategorySender sender = this.appContext.getObject(ContactCategorySender.class);
		sender.list(page, new ValueBackActionImpl<PageResult<List<ContactCategory>>>(back, new TypeClass<PageResult<List<ContactCategory>>>() {
		}));
	}

	public void getById(
			String id,
			ValueBack<ContactCategory> back) {
		ContactCategorySender sender = this.appContext.getObject(ContactCategorySender.class);
		sender.getById(id, new ValueBackActionImpl<ContactCategory>(back, ContactCategory.class));
	}

	public void add(
			ContactCategory data,
			ValueBack<ContactCategory> back) {
		ContactCategorySender sender = this.appContext.getObject(ContactCategorySender.class);
		sender.add(data, new ValueBackActionImpl<ContactCategory>(back, ContactCategory.class));
	}

	public void updateName(
			String id,
			String name,
			InfoBack back) {
		ContactCategorySender sender = this.appContext.getObject(ContactCategorySender.class);
		sender.updateName(id, name, new InfoBackActionImpl(back));
	}

	public void updateSort(
			String id,
			Integer sort,
			InfoBack back) {
		ContactCategorySender sender = this.appContext.getObject(ContactCategorySender.class);
		sender.updateSort(id, sort, new InfoBackActionImpl(back));
	}

	public void deleteById(
			String id,
			InfoBack back) {
		ContactCategorySender sender = this.appContext.getObject(ContactCategorySender.class);
		sender.deleteById(id, new InfoBackActionImpl(back));
	}

	public PageResult<List<ContactCategory>> list(
			Page page) {
		CompletableFuture<PageResult<List<ContactCategory>>> future = new CompletableFuture<>();
		list(page, (info, pr) -> {

			if (info.isSuccess() && null != pr) {
				future.complete(pr);
			} else {
				future.complete(new PageResult<>(page, new ArrayList<>()));
			}
		});
		try {
			return future.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return new PageResult<>(page, new ArrayList<>());
	}
}
