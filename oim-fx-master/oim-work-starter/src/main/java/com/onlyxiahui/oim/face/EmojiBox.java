package com.onlyxiahui.oim.face;

/**
 * @author XiaHui
 * @date 2017-09-29 2:40:53
 */
public class EmojiBox {

	public static java.net.URL getEmojiUrlByImageName(String emojiImageName) {
		return EmojiBox.class.getResource("/com/onlyxiahui/oim/face/emoji/72x72/" + emojiImageName);
	}
}
