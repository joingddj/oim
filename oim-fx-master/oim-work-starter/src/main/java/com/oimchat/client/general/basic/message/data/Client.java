
package com.oimchat.client.general.basic.message.data;

/**
 * Description <br>
 * Date 2021-03-11 17:04:47<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class Client {

	private String name;
	/**
	 * type:客户端类型<br>
	 * 1:oim-fx(javafx客户端)<br>
	 * 2:oim-e(electron客户端)<br>
	 * 3:oim-m(移动端h5)<br>
	 * 4:oim-a(android)<br>
	 * 5:oim-i(ios)<br>
	 * 6:oim-swing<br>
	 */
	private String type;
	/**
	 * 内部构建版本（累增）
	 */
	private int build;;
	/**
	 * 发布版本号（主版本.功能版本.迭代版本）
	 */
	private String version;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getBuild() {
		return build;
	}

	public void setBuild(int build) {
		this.build = build;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}
