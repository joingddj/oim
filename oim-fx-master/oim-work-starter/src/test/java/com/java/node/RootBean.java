
package com.java.node;

/**
 * Description <br>
 * Date 2021-03-24 10:27:13<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class RootBean {

	static {
		System.out.println("static:" + RootBean.class.getName());
	}
	String root = "root";

	public RootBean(String name) {
		initRoot();
		initNode();
	}

	public void initRoot() {
		System.out.println(root);
	}

	public void initNode() {

	}
}
