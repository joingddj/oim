package com.oimchat.client.general.kernel.work.module.contact.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.onlyxiahui.app.basic.storage.bean.PrimaryStringEntity;

/**
 * 好友分组信息<br>
 * Date 2019-01-17 23:47:26<br>
 * 
 * @author XiaHui<br>
 * @since 1.0.0
 */
@Entity()
@Table(name = "w_contact_category")
public class ContactCategory extends PrimaryStringEntity {
	/**
	 * 所属用户id
	 */
	@Column(nullable = false, length = 40)
	private String userId;
	/**
	 * 排序
	 */
	@Column(name = "`sort`", nullable = false)
	private Integer sort;
	/**
	 * 类型：1、系统默认生成的 2、用户自己新增的
	 */
	@Column(nullable = false)
	private Integer type;
	/**
	 * 分组名字
	 */
	@Column(nullable = false, length = 100)
	private String name;

	public static final int type_default = 1;
	public static final int type_custom = 2;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
