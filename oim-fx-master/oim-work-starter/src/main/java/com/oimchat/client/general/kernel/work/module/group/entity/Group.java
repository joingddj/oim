package com.oimchat.client.general.kernel.work.module.group.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.onlyxiahui.app.basic.storage.bean.PrimaryStringEntity;

/**
 * 群信息<br>
 * Date 2019-01-20 20:50:45<br>
 * 
 * @author XiaHui<br>
 * @since 1.0.0
 */
@Entity()
@Table(name = "w_group")
public class Group extends PrimaryStringEntity {

	/**
	 * 群号码
	 */
	@Column(nullable = false, updatable = false)
	private Long number;

	/**
	 * 群名称
	 */
	@Column(nullable = false, length = 200)
	private String name;

	/**
	 * 系统头像
	 */
	@Column(nullable = false, length = 5)
	private String head;

	/**
	 * 自定义头像，（当用户选择系统头像时为空）
	 */
	@Column(nullable = false, length = 500)
	private String avatar;

	/**
	 * 分类
	 */
	@Column(nullable = false, length = 50)
	private String classification;

	/**
	 * 介绍
	 */
	@Column(nullable = false, length = 1000)
	private String introduce;

	/**
	 * 群位置
	 */
	@Column(nullable = false, length = 500)
	private String location;

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
