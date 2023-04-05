package com.oimchat.client.general.kernel.work.module.group.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.onlyxiahui.app.basic.storage.bean.PrimaryStringEntity;

/**
 * 群头像<br>
 * Date 2019-01-26 14:14:21<br>
 * 
 * @author XiaHui<br>
 * @since 1.0.0
 */
@Entity()
@Table(name = "w_group_head")
public class GroupHead extends PrimaryStringEntity {

	/**
	 * 群id
	 */
	@Column(nullable = false, length = 40)
	private String groupId;

	/**
	 * 选择系统头像时，系统头像的编码
	 */
	@Column(nullable = false, length = 40)
	private String headId;

	/**
	 * 自定义头像的文件名
	 */
	@Column(nullable = false, length = 100)
	private String fileName;

	/**
	 * 头像类型： 1：系统头像 2：自定义头像
	 */
	@Column(nullable = false, length = 2)
	private String type;

	/**
	 * 用户自定义头像地址
	 */
	@Column(nullable = false, length = 500)
	private String url;

	public static final String type_system = "1";
	public static final String type_custom = "2";

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getHeadId() {
		return headId;
	}

	public void setHeadId(String headId) {
		this.headId = headId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
