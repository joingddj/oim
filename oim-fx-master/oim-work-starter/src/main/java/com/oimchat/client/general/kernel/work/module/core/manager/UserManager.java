
package com.oimchat.client.general.kernel.work.module.core.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oimchat.client.basic.util.ListConvertUtil;
import com.oimchat.client.common.asyn.ValueBack;
import com.oimchat.client.general.kernel.work.module.core.box.UserBox;
import com.oimchat.client.general.kernel.work.module.core.entity.User;
import com.oimchat.client.general.kernel.work.module.core.handler.UserHandler;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.message.bean.Info;

/**
 * Description <br>
 * Date 2021-03-29 21:38:57<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class UserManager extends AbstractMaterial {

	public UserManager(AppContext appContext) {
		super(appContext);
	}

	public void loadById(String id, ValueBack<User> back) {
		UserBox box = this.appContext.getObject(UserBox.class);
		UserHandler uh = this.appContext.getObject(UserHandler.class);
		uh.getById(id, (info, user) -> {
			if (info.isSuccess() && null != user) {
				box.put(id, user);
			}
			back.back(info, user);
		});
	}

	public void getOrLoadById(String id, ValueBack<User> back) {
		UserBox box = this.appContext.getObject(UserBox.class);
		User user = box.get(id);
		if (null == user) {
			loadById(id, back);
		} else {
			back.back(new Info(), user);
		}
	}

	public void loadListByIds(List<String> ids, ValueBack<List<User>> back) {
		UserBox box = this.appContext.getObject(UserBox.class);
		UserHandler uh = this.appContext.getObject(UserHandler.class);
		uh.getListByIds(ids, (info, list) -> {
			if (null != list) {
				list.forEach((user) -> {
					box.put(user.getId(), user);
				});
			}
			back.back(info, list);
		});
	}

	public void loadMapByIds(
			List<String> ids,
			ValueBack<Map<String, User>> back) {
		this.loadListByIds(ids, (info, items) -> {
			Map<String, User> map = new HashMap<>();
			if (null != items) {
				for (User u : items) {
					map.put(u.getId(), u);
				}
			}
			back.back(info, map);
		});
	}

	public <T> void loadListByIds(
			List<T> list,
			ListConvertUtil.Convert<String, T> c,
			ValueBack<List<User>> back) {
		List<String> ids = ListConvertUtil.convert(list, c);
		this.loadListByIds(ids, back);
	}

	public <T> void loadMapByIds(
			List<T> list,
			ListConvertUtil.Convert<String, T> c,
			ValueBack<Map<String, User>> back) {
		this.loadListByIds(list, c, (info, items) -> {
			Map<String, User> map = new HashMap<>();
			if (null != items) {
				for (User u : items) {
					map.put(u.getId(), u);
				}
			}
			back.back(info, map);
		});
	}

	/*************************/

	public void getOrLoadListByIds(List<String> ids, ValueBack<List<User>> back) {
		UserBox box = this.appContext.getObject(UserBox.class);
		List<User> allList = new ArrayList<>();
		if (null != ids) {
			List<String> userIds = new ArrayList<>();
			for (String id : ids) {
				User u = box.get(id);
				if (null != u) {
					allList.add(u);
				} else {
					userIds.add(id);
				}
			}

			if (userIds.isEmpty()) {
				back.back(new Info(), allList);
			} else {
				loadListByIds(userIds, (info, list) -> {
					if (null != list) {
						allList.addAll(list);
					}
					back.back(info, allList);
				});
			}
		} else {
			back.back(new Info(), allList);
		}
	}

	public void getOrLoadMapByIds(
			List<String> ids,
			ValueBack<Map<String, User>> back) {
		this.getOrLoadListByIds(ids, (info, items) -> {
			Map<String, User> map = new HashMap<>();
			if (null != items) {
				for (User u : items) {
					map.put(u.getId(), u);
				}
			}
			back.back(info, map);
		});
	}

	public <T> void getOrLoadListByIds(
			List<T> list,
			ListConvertUtil.Convert<String, T> c,
			ValueBack<List<User>> back) {
		List<String> ids = ListConvertUtil.convert(list, c);
		this.getOrLoadListByIds(ids, back);
	}

	public <T> void getOrLoadMapByIds(
			List<T> list,
			ListConvertUtil.Convert<String, T> c,
			ValueBack<Map<String, User>> back) {
		this.getOrLoadListByIds(list, c, (info, items) -> {
			Map<String, User> map = new HashMap<>();
			if (null != items) {
				for (User u : items) {
					map.put(u.getId(), u);
				}
			}
			back.back(info, map);
		});
	}

	public void getLocalOrRemoteById(
			String id,
			ValueBack<User> back) {
		UserBox box = this.appContext.getObject(UserBox.class);
		User u = box.get(id);
		if (null == u) {
			this.loadById(id, back);
		} else {
			back.back(new Info(), u);
		}
	}
}
