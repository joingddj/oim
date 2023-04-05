package com.oimchat.server.general.kernel.work.module.group.base.manager;

import org.springframework.stereotype.Service;

import com.oimchat.server.general.kernel.work.module.group.base.entity.Group;
import com.onlyxiahui.aware.basic.manager.BaseManager;
import com.onlyxiahui.common.utils.base.util.time.DateUtil;

/**
 * 
 * <br>
 * Date 2020-11-08 18:55:40<br>
 * 
 * @author XiaHui
 * @since 1.0.0
 */
@Service
public class GroupManager extends BaseManager<Group> {

	/**
	 * 根据id更新不为空的字段
	 * 
	 * @param data
	 * @since 1.0.0
	 */
	@Override
	public int updateSelective(Group data) {
		if (null != data) {
			data.setUpdatedTimestamp(System.currentTimeMillis());
			data.setUpdatedDateTime(DateUtil.getCurrentDateTime());
		}
		return super.updateSelective(data);
	}

	/**
	 * 根据id删除<br>
	 * 
	 * @param id
	 * @param updatedUserId
	 * @since 1.0.0
	 */
	public int deleteById(String id, String updatedUserId) {
		Group data = new Group();
		data.setId(id);
		data.setIsDeleted(1);
		data.setUpdatedTimestamp(System.currentTimeMillis());
		return this.updateSelective(data);
	}
}