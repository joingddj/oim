
package com.java.node;

/**
 * Description <br>
 * Date 2021-03-24 10:30:40<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class NodeBeanTest {

	static {
		System.out.println("static:" + NodeBeanTest.class.getName());
	}

	public static void main(String[] args) {
		System.out.println("main:" + NodeBeanTest.class.getName());
		new NodeBean("");
	}

}
