
package com.oimchat.client.basic.util;

import com.onlyxiahui.common.data.common.data.Page;

/**
 * Description <br>
 * Date 2021-03-16 11:15:05<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class PageUtil {

	public static int getTotalPage(int totalCount, int pageSize) {
		int totalPage = totalCount / pageSize;
		if (totalCount % pageSize > 0) {
			totalPage = totalPage + 1;
		}
		return totalPage;
	}

	public static <Q> void stream(int totalCount, int pageSize, Q query, PageStream<Q> ps) {
		int totalPage = getTotalPage(totalCount, pageSize);
		Page page = new Page();
		page.setSize(pageSize);
		for (int i = 0; i < totalPage; i++) {
			page.setNumber(i + 1);
			ps.stream(page, query);
		}
	}

	public static <Q> void stream(int totalCount, int pageSize, Q query, NextPageStream<Q> ps) {
		int totalPage = getTotalPage(totalCount, pageSize);
		Page page = new Page();
		page.setSize(pageSize);
		page.setTotalPage(totalPage);
		page.setTotalCount(totalCount);
		NextPage np = new NextPage() {
			int i = 0;

			@Override
			public void next() {
				NextPage n = this;
				page.setNumber(i + 1);
				ps.stream(n, page, query);
				i++;
			}
		};
		np.next();
	}

	public interface NextPage {

		void next();

	}

	public interface NextPageStream<Q> {

		void stream(NextPage n, Page page, Q query);

	}

	public interface PageStream<Q> {

		void stream(Page page, Q query);
	}
}
