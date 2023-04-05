package com.onlyxiahui.oim.face.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: XiaHui
 * @date: 2017-06-01 5:50:42
 */
public class EmojiUtil {

	static Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]", Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);

	public static boolean isEmoji(String text) {
		boolean isEmoji = false;
		if (text != null) {
			Matcher matcher = emoji.matcher(text);
			isEmoji = (matcher.matches());
		}
		return isEmoji;
	}

	public static String emojiMultiToUnicode(String text, String separate, String prefix) {
		String code = null;
		if (null != separate) {
			List<String> list = matchList(text);
			if (!list.isEmpty()) {
				StringBuilder sb = new StringBuilder();
				int size = list.size();
				for (int i = 0; i < size; i++) {
					String key = list.get(i);
					if (i > 0) {
						sb.append(separate);
					}
					sb.append(emojiToUnicode(key, prefix));
				}
				code = sb.toString();
			} else {
				code = emojiToUnicode(text, prefix);
			}
		} else {
			code = emojiToUnicode(text, prefix);
		}
		return code;
	}

	public static String emojiMultiToUnicode(String text, String separate) {
		return emojiMultiToUnicode(text, separate, null);
	}

	public static String emojiToUnicode(String text, String prefix) {
		StringBuffer sb = new StringBuffer();
		if (null != text) {
			for (int i = 0; i < text.length(); i++) {
				int ch = text.codePointAt(i);

				if (ch <= 128) {
					sb.appendCodePoint(ch);
				} else if (ch > 128 && (ch < 159 || (ch >= 55296 && ch <= 57343))) {
					continue;
				} else {
					if (null != prefix) {
						sb.append(prefix);
					}
					sb.append(Integer.toHexString(ch));
				}
			}
		} else {
			return text;
		}
		return sb.toString();
	}

	public static String emojiToUnicode(String text) {
		return emojiToUnicode(text, null);
	}

	public static Set<String> matchSet(String text) {
		Set<String> set = new HashSet<>();
		if (null != text) {
			Matcher emojiMatcher = emoji.matcher(text);
			while (emojiMatcher.find()) {
				String e = emojiMatcher.group();
				set.add(e);
			}
		}
		return set;
	}

	public static List<String> matchList(String text) {
		List<String> list = new ArrayList<>();
		if (null != text) {
			Matcher emojiMatcher = emoji.matcher(text);
			while (emojiMatcher.find()) {
				String e = emojiMatcher.group();
				list.add(e);
			}
		}
		return list;
	}
}
