
package com.onlyxiahui.app.view.fx.common.util;

import com.oimchat.client.basic.util.TextFileUtil;

/**
 * Description <br>
 * Date 2021-03-27 09:41:56<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class MaterialIconsRegularTest {

	public static void main(String[] args) {
		String TTF_PATH = "/com/onlyxiahui/app/view/fx/default/font/icon/mi/4.0.0/MaterialIcons-Regular.codepoints";
		String text = TextFileUtil.getTextByClassPath(TTF_PATH);
		// System.out.println(text);

		if (null != text) {
			String[] lines = text.split("\n");
			for (String line : lines) {
				if (null != line) {
					String[] array = line.split(" ");
					if (array.length > 1) {
						String name = array[0];
						String code = array[1];

						System.out.println("name:" + name + "/" + "code:" + code);
					}
				}
			}
		}
	}
}
