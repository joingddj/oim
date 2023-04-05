
package com.java.node;

/**
 * Description <br>
 * Date 2021-03-24 10:29:20<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class NodeBean extends RootBean {

	static {
		System.out.println("static:" + NodeBean.class.getName());
	}
	String node = "node";

	public NodeBean(String name) {
		super(name);
		initCNode();
	}

	public void initNode() {
		System.out.println(node);
	}

	public void initCNode() {
		System.out.println("C；" + node);
	}
}
