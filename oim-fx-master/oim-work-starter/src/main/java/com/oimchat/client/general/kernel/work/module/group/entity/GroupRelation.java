package com.oimchat.client.general.kernel.work.module.group.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.onlyxiahui.app.basic.storage.bean.PrimaryStringEntity;

/**
 * 群的备注、分组等相关的信息<br>
 * Date 2019-01-20 20:53:06<br>
 * 
 * @author XiaHui<br>
 * @since 1.0.0
 */
@Entity()
@Table(name = "w_group_relation")
public class GroupRelation extends PrimaryStringEntity {

	/**
	 * 所属用户id
	 */

	@Column(nullable = false, length = 40)
	private String userId;

	/**
	 * 群分组id
	 */

	@Column(nullable = false, length = 40)
	private String categoryId;

	/**
	 * 群id
	 */

	@Column(nullable = false, length = 40)
	private String groupId;

	/**
	 * 群备注名
	 */

	@Column(nullable = false, length = 100)
	private String remark;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
