
package com.oimchat.client.platform.kernel.work.common.app;

import com.oimchat.client.basic.util.PathUtil;

/**
 * Description <br>
 * Date 2021-03-13 22:41:50<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class AppEnvInfo {

	private String name = "oim-fx";
	private String type = "1";
	private String version = "1.0.0";
	private int build = 1;

	private String home = ".oim";
	private String charset = "UTF-8";

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

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public int getBuild() {
		return build;
	}

	public void setBuild(int build) {
		this.build = build;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getUserHomePath() {
		return System.getProperty("user.home");
	}

	public String getAppHomePath() {
		StringBuilder sb = new StringBuilder();
		sb.append(getUserHomePath());
		sb.append("/");
		sb.append(getHome());
		return sb.toString();
	}

	public String getPathByAppHome(String path) {
		return PathUtil.merge(getAppHomePath(), path);
	}
}
