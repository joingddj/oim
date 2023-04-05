
package com.java.asyn;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Description <br>
 * Date 2021-03-16 14:04:13<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class Calc {
	public static Integer calc(Integer para) {
		try {
			// 模拟一个长时间的执行
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return para * para;
	}

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		final CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> calc(50))
				.thenApply((i) -> Integer.toString(i))
				.thenApply((str) -> "\"" + str + "\"")
				.thenAccept(System.out::println);
		future.get();
	}
}
