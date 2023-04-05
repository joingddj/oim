
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
import com.oimchat.client.general.kernel.work.module.contact.entity.ContactRelation;
import com.oimchat.client.general.kernel.work.module.contact.sender.ContactRelationSender;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.data.common.bean.CountInfo;
import com.onlyxiahui.common.data.common.data.ListBody;
import com.onlyxiahui.common.data.common.data.Page;
import com.onlyxiahui.common.data.common.data.PageResult;

/**
 * Description <br>
 * Date 2021-03-15 15:41:36<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ContactRelationHandler extends AbstractMaterial {

	public ContactRelationHandler(AppContext appContext) {
		super(appContext);
	}

	public void count(
			ValueBack<CountInfo> back) {
		ContactRelationSender sender = this.appContext.getObject(ContactRelationSender.class);
		sender.count(new ValueBackActionImpl<CountInfo>(back, CountInfo.class));
	}

	public void list(
			Page page,
			ValueBack<PageResult<List<ContactRelation>>> back) {
		ContactRelationSender sender = this.appContext.getObject(ContactRelationSender.class);
		sender.list(page, new ValueBackActionImpl<PageResult<List<ContactRelation>>>(back,
				new TypeClass<PageResult<List<ContactRelation>>>() {
				}));
	}

	public void getByContactUserId(
			String contactUserId,
			ValueBack<ContactRelation> back) {
		ContactRelationSender sender = this.appContext.getObject(ContactRelationSender.class);
		sender.getByContactUserId(contactUserId, new ValueBackActionImpl<ContactRelation>(back, ContactRelation.class));
	}

	public void getByContactUserIds(
			List<String> contactUserIds,
			ValueBack<ListBody<List<ContactRelation>>> back) {
		ContactRelationSender sender = this.appContext.getObject(ContactRelationSender.class);
		sender.getByContactUserIds(contactUserIds, new ValueBackActionImpl<>(back,
				new TypeClass<ListBody<List<ContactRelation>>>() {
				}));
	}

	public void updateRemark(
			String contactUserId,
			String remark,
			InfoBack back) {
		ContactRelationSender sender = this.appContext.getObject(ContactRelationSender.class);
		sender.updateRemark(contactUserId, remark, new InfoBackActionImpl(back));
	}

	public void moveCategory(
			List<String> contactUserIds,
			String categoryId,
			InfoBack back) {
		ContactRelationSender sender = this.appContext.getObject(ContactRelationSender.class);
		sender.moveCategory(contactUserIds, categoryId, new InfoBackActionImpl(back));
	}

	public void deleteByContactUserId(
			String contactUserId,
			InfoBack back) {
		ContactRelationSender sender = this.appContext.getObject(ContactRelationSender.class);
		sender.deleteByContactUserId(contactUserId, new InfoBackActionImpl(back));
	}

	public void updateBlocked(
			String contactUserId,
			String isBlocked,
			InfoBack back) {
		ContactRelationSender sender = this.appContext.getObject(ContactRelationSender.class);
		sender.updateBlocked(contactUserId, isBlocked, new InfoBackActionImpl(back));
	}
	
	public PageResult<List<ContactRelation>> list(
			Page page) {
		CompletableFuture<PageResult<List<ContactRelation>>> future = new CompletableFuture<>();
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
