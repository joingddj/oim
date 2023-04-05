
package com.oimchat.client.general.kernel.work.module.contact.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.oimchat.client.common.asyn.ValueBack;
import com.oimchat.client.general.kernel.work.module.contact.data.dto.ContactAddApplyEntityCase;
import com.oimchat.client.general.kernel.work.module.contact.data.dto.ContactAddApplyQuery;
import com.oimchat.client.general.kernel.work.module.contact.data.vo.ContactAddApplyEntityCaseData;
import com.oimchat.client.general.kernel.work.module.contact.handler.ContactHandler;
import com.oimchat.client.general.kernel.work.module.core.entity.User;
import com.oimchat.client.general.kernel.work.module.core.handler.UserHandler;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.data.common.data.Page;
import com.onlyxiahui.common.data.common.data.PageResult;

/**
 * Description <br>
 * Date 2021-04-02 14:48:13<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ContactAddApplyController extends AbstractMaterial {

	public ContactAddApplyController(AppContext appContext) {
		super(appContext);
	}

	public void queryApplyDataReceiveList(
			ContactAddApplyQuery query,
			Page page,
			ValueBack<PageResult<List<ContactAddApplyEntityCaseData>>> back) {
		UserHandler uh = this.appContext.getObject(UserHandler.class);
		ContactHandler ch = this.appContext.getObject(ContactHandler.class);
		List<ContactAddApplyEntityCaseData> items = new ArrayList<>();
		ch.queryApplyDataReceiveList(query, page, (info, pr) -> {
			if (null != pr) {
				Page p = (null == pr.getPage()) ? page : pr.getPage();
				List<ContactAddApplyEntityCase> list = (null == pr.getItems()) ? new ArrayList<>() : pr.getItems();
				uh.getMapByIds(list, (d) -> {
					String id = (null == d.getApply()) ? "" : d.getApply().getApplyUserId();
					return id;
				}, (ui, map) -> {
					list.forEach((d) -> {
						String userId = (null == d.getApply()) ? "" : d.getApply().getApplyUserId();
						User u = map.get(userId);
						if (null != u) {
							ContactAddApplyEntityCaseData cd = new ContactAddApplyEntityCaseData();
							BeanUtils.copyProperties(d, cd);
							cd.setUser(u);

							items.add(cd);
						}
					});
					back.back(info, new PageResult<>(p, items));
				});
			} else {
				back.back(info, new PageResult<>(page, new ArrayList<>()));
			}
		});
	}
}
