
package com.oimchat.client.common.step.task;

/**
 * Description <br>
 * Date 2021-03-31 00:59:00<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class TaskNextTest {

	public static void main(String[] args) {
		TaskNext tn = new TaskNext();
		tn.set((n) -> {
			System.out.println("1");
			n.next();
		}).set((n) -> {
			System.out.println("2");
			n.next();
		}).set((n) -> {
			System.out.println("3");
			n.next();
		}).set((n) -> {
			System.out.println("4");
			n.next();
		});

		tn.next();
	}
}
