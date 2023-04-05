
package com.oimchat.client.general.kernel.work.module.common.util;

import java.util.ArrayList;
import java.util.List;

import com.onlyxiahui.common.utils.base.lang.string.StringUtil;

/**
 * Description <br>
 * Date 2021-03-28 11:47:53<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class StringSelectUtil {

	public static TextValue value(int rank, String text) {
		return new TextValue() {

			@Override
			public int rank() {
				return rank;
			}

			@Override
			public String text() {
				return text;
			}
		};
	}

	public static String select(TextValue... textValues) {
		List<TextValue> list = new ArrayList<>();
		if (null != textValues) {
			for (TextValue v : textValues) {
				list.add(v);
			}
		}
		return select(list);
	}

	public static String select(List<TextValue> list) {
		String text = null;
		if (null != list) {
			list.sort((a, b) -> {
				int rankA = a.rank();
				int rankB = b.rank();
				String textA = a.text();
				String textB = b.text();
				boolean hasA = StringUtil.isNotBlank(textA);
				boolean hasB = StringUtil.isNotBlank(textB);
				if (hasA && !hasB) {
					rankA = rankB - 1;
				}
				if (hasB && !hasA) {
					rankB = rankA - 1;
				}
				return rankA - rankB;
			});
			if (!list.isEmpty()) {
				text = list.get(0).text();
			}
		}
		return text;
	}

	public static String select(String... texts) {
		List<TextValue> list = new ArrayList<>();
		if (null != texts) {
			int size = texts.length;
			for (int i = 0; i < size; i++) {
				String v = texts[i];
				list.add(StringSelectUtil.value(i, v));
			}
		}
		return select(list);
	}

	public interface TextValue {

		int rank();

		String text();
	}

	public static void main(String[] arg) {
		System.out.println(StringSelectUtil.select(
				StringSelectUtil.value(8, "8"),
				StringSelectUtil.value(4, null),
				StringSelectUtil.value(3, "3"),
				StringSelectUtil.value(1, null),
				StringSelectUtil.value(2, "2"),
				StringSelectUtil.value(6, null)));

		System.out.println(StringSelectUtil.select("", null, "3", "6", null));
	}
}
