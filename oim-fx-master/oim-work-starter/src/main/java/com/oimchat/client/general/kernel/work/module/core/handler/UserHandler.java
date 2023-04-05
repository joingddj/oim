
package com.oimchat.client.general.kernel.work.module.core.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oimchat.client.basic.util.ListConvertUtil;
import com.oimchat.client.common.asyn.ValueBack;
import com.oimchat.client.common.net.back.ValueBackActionAdapter;
import com.oimchat.client.general.kernel.work.module.core.data.query.UserQuery;
import com.oimchat.client.general.kernel.work.module.core.entity.User;
import com.oimchat.client.general.kernel.work.module.core.sender.UserSender;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.annotation.parameter.Define;
import com.onlyxiahui.common.data.common.data.Page;
import com.onlyxiahui.common.data.common.data.PageResult;
import com.onlyxiahui.common.message.bean.Info;
import com.onlyxiahui.wofa.client.net.core.back.annotation.Back;

/**
 * Description <br>
 * Date 2021-03-15 14:22:23<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class UserHandler extends AbstractMaterial {

	public UserHandler(AppContext appContext) {
		super(appContext);
	}

	public void list(UserQuery query, Page page, ValueBack<PageResult<List<User>>> back) {
		UserSender us = this.appContext.getObject(UserSender.class);
		us.list(query, page, new ValueBackActionAdapter<PageResult<List<User>>>(back) {

			@Back
			public void back(@Define("info") Info info, @Define("body") PageResult<List<User>> pr) {
				back.back(info, pr);
			}
		});
	}

	public void getById(String id, ValueBack<User> back) {
		UserSender us = this.appContext.getObject(UserSender.class);
		us.getById(id, new ValueBackActionAdapter<User>(back) {

			@Back
			public void back(@Define("info") Info info, @Define("body") User u) {
				back.back(info, u);
			}
		});
	}

	public void getListByIds(List<String> ids, ValueBack<List<User>> back) {
		UserSender us = this.appContext.getObject(UserSender.class);
		us.getListByIds(ids, new ValueBackActionAdapter<List<User>>(back) {

			@Back
			public void back(@Define("info") Info info, @Define("body.items") List<User> items) {
				back.back(info, items);
			}
		});
	}

	public void getMapByIds(
			List<String> ids,
			ValueBack<Map<String, User>> back) {
		this.getListByIds(ids, (info, items) -> {
			Map<String, User> map = new HashMap<>();
			if (null != items) {
				for (User u : items) {
					map.put(u.getId(), u);
				}
			}
			back.back(info, map);
		});
	}

	public <T> void getListByIds(
			List<T> list,
			ListConvertUtil.Convert<String, T> c,
			ValueBack<List<User>> back) {
		List<String> ids = ListConvertUtil.convert(list, c);
		this.getListByIds(ids, back);
	}

	public <T> void getMapByIds(
			List<T> list,
			ListConvertUtil.Convert<String, T> c,
			ValueBack<Map<String, User>> back) {
		this.getListByIds(list, c, (info, items) -> {
			Map<String, User> map = new HashMap<>();
			if (null != items) {
				for (User u : items) {
					map.put(u.getId(), u);
				}
			}
			back.back(info, map);
		});
	}
}
