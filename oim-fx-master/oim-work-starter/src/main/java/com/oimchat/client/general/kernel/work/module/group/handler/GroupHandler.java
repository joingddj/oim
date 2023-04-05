package com.oimchat.client.general.kernel.work.module.group.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.oimchat.client.basic.util.ListConvertUtil;
import com.oimchat.client.common.asyn.ValueBack;
import com.oimchat.client.common.net.back.ValueBackActionImpl;
import com.oimchat.client.common.reflect.TypeClass;
import com.oimchat.client.general.kernel.work.module.group.data.query.GroupQuery;
import com.oimchat.client.general.kernel.work.module.group.entity.Group;
import com.oimchat.client.general.kernel.work.module.group.sender.GroupSender;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.data.common.data.ListBody;
import com.onlyxiahui.common.data.common.data.Page;
import com.onlyxiahui.common.data.common.data.PageResult;

/**
 * 群信息业务接口<br>
 * Date 2019-01-20 20:49:52<br>
 * 
 * @author XiaHui<br>
 * @since 1.0.0
 * @docModuleSuperKey 1.3.0
 */
@Component

public class GroupHandler extends AbstractMaterial {

	public GroupHandler(AppContext appContext) {
		super(appContext);
	}

	/**
	 * 条件分页搜索群<br>
	 * Date 2019-01-26 14:36:56<br>
	 * 
	 * @author XiaHui<br>
	 * @since 1.0.0
	 */

	public void list(
			GroupQuery query,
			Page page, ValueBack<PageResult<List<Group>>> back) {
		GroupSender sender = this.appContext.getObject(GroupSender.class);
		sender.list(query, page, new ValueBackActionImpl<PageResult<List<Group>>>(back, new TypeClass<PageResult<List<Group>>>() {
		}));
	}

	/**
	 * 获取群信息<br>
	 * Date 2019-01-26 14:37:13<br>
	 * 
	 * @since 1.0.0
	 */

	public void getById(
			String id, ValueBack<Group> back) {
		GroupSender sender = this.appContext.getObject(GroupSender.class);
		sender.getById(id, new ValueBackActionImpl<Group>(back, Group.class));
	}

	/**
	 * 
	 * 批量获取群信息<br>
	 * Date 2019-05-05 08:39:53<br>
	 * 
	 * @since 1.0.0
	 */

	public void getListByIds(
			List<String> ids, ValueBack<List<Group>> back) {
		GroupSender sender = this.appContext.getObject(GroupSender.class);
		ValueBack<ListBody<List<Group>>> vb = (info, body) -> {
			back.back(info, null == body ? new ArrayList<>() : body.getItems());
		};
		sender.getListByIds(ids, new ValueBackActionImpl<ListBody<List<Group>>>(vb, new TypeClass<ListBody<List<Group>>>() {
		}));
	}

	public void getMapByIds(
			List<String> ids,
			ValueBack<Map<String, Group>> back) {
		this.getListByIds(ids, (info, items) -> {
			Map<String, Group> map = new HashMap<>();
			if (null != items) {
				for (Group u : items) {
					map.put(u.getId(), u);
				}
			}
			back.back(info, map);
		});
	}

	public <T> void getListByIds(
			List<T> list,
			ListConvertUtil.Convert<String, T> c,
			ValueBack<List<Group>> back) {
		List<String> ids = ListConvertUtil.convert(list, c);
		this.getListByIds(ids, back);
	}

	public <T> void getMapByIds(
			List<T> list,
			ListConvertUtil.Convert<String, T> c,
			ValueBack<Map<String, Group>> back) {
		this.getListByIds(list, c, (info, items) -> {
			Map<String, Group> map = new HashMap<>();
			if (null != items) {
				for (Group u : items) {
					map.put(u.getId(), u);
				}
			}
			back.back(info, map);
		});
	}
}
