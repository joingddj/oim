package com.oimchat.client.general.kernel.work.module.contact.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.onlyxiahui.app.basic.storage.bean.PrimaryStringEntity;

/**
 * 联系人的相关信息<br>
 * Date 2019-01-17 23:48:58<br>
 * 
 * @author XiaHui<br>
 * @since 1.0.0
 */
@Entity()
@Table(name = "w_contact_relation")
public class ContactRelation extends PrimaryStringEntity {
	/**
	 * 拥有者id
	 */
	@Column(nullable = false, length = 40)
	private String ownerUserId;
	/**
	 * 联系人分组id
	 */
	@Column(nullable = false, length = 40)
	private String categoryId;
	/**
	 * 联系人用户id
	 */
	@Column(nullable = false, length = 40)
	private String contactUserId;
	/**
	 * 联系人备注名
	 */
	@Column(nullable = false, length = 100)
	private String remark;

	/**
	 * 是否加入黑名单：0：否、1：是
	 */
	@Column(nullable = false, length = 2)
	private String isBlocked;

	public static String is_blocked_no = "0";

	public static String is_blocked_yes = "1";

	public String getOwnerUserId() {
		return ownerUserId;
	}

	public void setOwnerUserId(String ownerUserId) {
		this.ownerUserId = ownerUserId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getContactUserId() {
		return contactUserId;
	}

	public void setContactUserId(String contactUserId) {
		this.contactUserId = contactUserId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIsBlocked() {
		return isBlocked;
	}

	public void setIsBlocked(String isBlocked) {
		this.isBlocked = isBlocked;
	}
}
