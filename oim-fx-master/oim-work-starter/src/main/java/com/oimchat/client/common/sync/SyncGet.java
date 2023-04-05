
package com.oimchat.client.common.sync;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Description <br>
 * Date 2020-12-28 19:39:20<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class SyncGet<T> {

	BlockingQueue<T> q = new ArrayBlockingQueue<>(1);

	public T get(SyncBuild<T> syncBuild) {
		T t = null;
		try {
			syncBuild.build(d -> {
				q.add(d);
			});
			t = q.take();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	public static <T> T syncGet(SyncBuild<T> syncBuild) {
		return (T) new SyncGet<T>().get(syncBuild);
	}
}
