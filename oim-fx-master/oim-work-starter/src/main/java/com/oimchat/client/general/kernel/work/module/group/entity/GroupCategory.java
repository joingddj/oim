package com.oimchat.client.general.kernel.work.module.group.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.onlyxiahui.app.basic.storage.bean.PrimaryStringEntity;

/**
 * 群分组信息<br>
 * Date 2019-01-20 20:51:06<br>
 * 
 * @author XiaHui<br>
 * @since 1.0.0
 */
@Entity()
@Table(name = "w_group_category")
public class GroupCategory extends PrimaryStringEntity {

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

	public static final int type_default = 1;// 系统默认生成的
	public static final int type_custom = 2;// 用户自己新增的

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
