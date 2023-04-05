
package com.java.asyn;

import java.util.concurrent.CompletableFuture;

/**
 * Description <br>
 * Date 2021-03-16 11:40:57<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class AskThread implements Runnable {
	private CompletableFuture<Integer> re = null;

	public AskThread(CompletableFuture<Integer> re) {
		super();
		this.re = re;
	}

	public void run() {
		int myRe = 0;
		try {
			myRe = re.get() * re.get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(myRe);
	}

	public static void main(String[] args) throws InterruptedException {
		final CompletableFuture<Integer> future = new CompletableFuture<>();
		new Thread(new AskThread(future)).start();
		// 模拟长时间的计算过程
		Thread.sleep(1000);
		// 告知完成结果
		future.complete(60);
	}

}
