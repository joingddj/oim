package com.oimchat.client.general.kernel.work.module.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.onlyxiahui.app.basic.storage.bean.PrimaryStringEntity;

/**
 * 
 * 用户头像 <br>
 * Date 2019-05-20 08:14:12<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */
@Entity()
@Table(name = "w_user_head")
public class UserHead extends PrimaryStringEntity {

	/**
	 * 用户id
	 */
	@Column(nullable = false, length = 40)
	private String userId;
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
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getHeadId() {
		return headId;
	}

	public void setHeadId(String headId) {
		this.headId = headId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
