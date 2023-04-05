
package com.oimchat.client.general.kernel.work.module.group.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oimchat.client.basic.util.ListConvertUtil;
import com.oimchat.client.common.asyn.ValueBack;
import com.oimchat.client.general.kernel.work.module.group.box.GroupBox;
import com.oimchat.client.general.kernel.work.module.group.entity.Group;
import com.oimchat.client.general.kernel.work.module.group.handler.GroupHandler;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.message.bean.Info;

/**
 * Description <br>
 * Date 2021-03-16 11:33:30<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class GroupManager extends AbstractMaterial {

	public GroupManager(AppContext appContext) {
		super(appContext);
	}

	public void loadById(
			String groupId,
			ValueBack<Group> back) {
		GroupBox box = this.appContext.getObject(GroupBox.class);
		GroupHandler handler = this.appContext.getObject(GroupHandler.class);
		handler.getById(groupId, (info, data) -> {
			if (info.isSuccess() && null != data) {
				box.put(groupId, data);
			}
			back.back(info, data);
		});
	}

	public void getOrLoadById(
			String groupId,
			ValueBack<Group> back) {
		GroupBox box = this.appContext.getObject(GroupBox.class);
		Group data = box.get(groupId);
		if (null == data) {
			loadById(groupId, back);
		} else {
			back.back(new Info(), data);
		}
	}

	public void loadListByIds(List<String> ids, ValueBack<List<Group>> back) {
		GroupBox box = this.appContext.getObject(GroupBox.class);
		GroupHandler uh = this.appContext.getObject(GroupHandler.class);
		uh.getListByIds(ids, (info, list) -> {
			if (null != list) {
				list.forEach((group) -> {
					box.put(group.getId(), group);
				});
			}
			back.back(info, list);
		});
	}

	public void loadMapByIds(
			List<String> ids,
			ValueBack<Map<String, Group>> back) {
		this.loadListByIds(ids, (info, items) -> {
			Map<String, Group> map = new HashMap<>();
			if (null != items) {
				for (Group u : items) {
					map.put(u.getId(), u);
				}
			}
			back.back(info, map);
		});
	}

	public <T> void loadListByIds(
			List<T> list,
			ListConvertUtil.Convert<String, T> c,
			ValueBack<List<Group>> back) {
		List<String> ids = ListConvertUtil.convert(list, c);
		this.loadListByIds(ids, back);
	}

	public <T> void loadMapByIds(
			List<T> list,
			ListConvertUtil.Convert<String, T> c,
			ValueBack<Map<String, Group>> back) {
		this.loadListByIds(list, c, (info, items) -> {
			Map<String, Group> map = new HashMap<>();
			if (null != items) {
				for (Group u : items) {
					map.put(u.getId(), u);
				}
			}
			back.back(info, map);
		});
	}

	/*************************/

	public void getOrLoadListByIds(List<String> ids, ValueBack<List<Group>> back) {
		GroupBox box = this.appContext.getObject(GroupBox.class);
		List<Group> allList = new ArrayList<>();
		if (null != ids) {
			List<String> groupIds = new ArrayList<>();
			for (String id : ids) {
				Group u = box.get(id);
				if (null != u) {
					allList.add(u);
				} else {
					groupIds.add(id);
				}
			}

			if (groupIds.isEmpty()) {
				back.back(new Info(), allList);
			} else {
				loadListByIds(groupIds, (info, list) -> {
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
			ValueBack<Map<String, Group>> back) {
		this.getOrLoadListByIds(ids, (info, items) -> {
			Map<String, Group> map = new HashMap<>();
			if (null != items) {
				for (Group u : items) {
					map.put(u.getId(), u);
				}
			}
			back.back(info, map);
		});
	}

	public <T> void getOrLoadListByIds(
			List<T> list,
			ListConvertUtil.Convert<String, T> c,
			ValueBack<List<Group>> back) {
		List<String> ids = ListConvertUtil.convert(list, c);
		this.getOrLoadListByIds(ids, back);
	}

	public <T> void getOrLoadMapByIds(
			List<T> list,
			ListConvertUtil.Convert<String, T> c,
			ValueBack<Map<String, Group>> back) {
		this.getOrLoadListByIds(list, c, (info, items) -> {
			Map<String, Group> map = new HashMap<>();
			if (null != items) {
				for (Group u : items) {
					map.put(u.getId(), u);
				}
			}
			back.back(info, map);
		});
	}

	public void getLocalOrRemoteById(
			String id,
			ValueBack<Group> back) {
		GroupBox box = this.appContext.getObject(GroupBox.class);
		Group d = box.get(id);
		if (null == d) {
			this.loadById(id, back);
		} else {
			back.back(new Info(), d);
		}
	}
}
