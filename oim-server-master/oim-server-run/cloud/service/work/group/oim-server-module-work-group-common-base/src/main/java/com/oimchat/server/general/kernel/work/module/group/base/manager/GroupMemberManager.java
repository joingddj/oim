package com.oimchat.server.general.kernel.work.module.group.base.manager;

import org.springframework.stereotype.Service;

import com.oimchat.server.general.kernel.work.module.group.base.entity.GroupMember;
import com.onlyxiahui.aware.basic.manager.BaseManager;

/**
 * 
 * <br>
 * Date 2020-11-08 18:55:40<br>
 * 
 * @author XiaHui
 * @since 1.0.0
 */
@Service
public class GroupMemberManager extends BaseManager<GroupMember> {

	/**
	 * 根据id更新不为空的字段
	 * 
	 * @param data
	 * @since 1.0.0
	 */
	@Override
	public int updateSelective(GroupMember data) {
		if (null != data) {
			data.setUpdatedTimestamp(System.currentTimeMillis());
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
		GroupMember data = new GroupMember();
		data.setId(id);
		data.setIsDeleted(1);
		data.setUpdatedTimestamp(System.currentTimeMillis());
		return this.updateSelective(data);
	}
}