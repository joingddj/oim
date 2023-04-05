
package com.oimchat.client.platform.kernel.work.module.im.manager;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.oimchat.client.basic.util.TextFileUtil;
import com.oimchat.client.platform.kernel.work.module.im.box.FaceBox;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.oim.face.EmojiBox;
import com.onlyxiahui.oim.face.bean.FaceCategory;
import com.onlyxiahui.oim.face.bean.FaceInfo;
import com.onlyxiahui.oim.face.util.EmojiUtil;

/**
 * Description <br>
 * Date 2021-03-22 18:10:49<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class FaceManager extends AbstractMaterial {

	Map<String, String> emojiImageNameMap = new HashMap<String, String>();
	Map<String, FaceInfo> emojiInfoMap = new HashMap<String, FaceInfo>();

	public FaceManager(AppContext appContext) {
		super(appContext);
		initData();
	}

	private void initData() {
		String json = TextFileUtil.getTextByClassPath("/com/onlyxiahui/oim/text/emoji/text.json");
		JSONArray jArray = JSONArray.parseArray(json);
		List<String> emojiList = new ArrayList<>();
		int count = 0;
		if (null != jArray) {
			int size = jArray.size();
			for (int i = 0; i < size; i++) {
				Object co = jArray.get(i);
				if (co instanceof JSONObject) {
					JSONObject cjo = jArray.getJSONObject(i);
					Object cio = cjo.get("items");
					if (cio instanceof JSONArray) {
						JSONArray cja = cjo.getJSONArray("items");

						int gsize = cja.size();
						for (int j = 0; j < gsize; j++) {
							Object cji = cja.get(j);
							if (cji instanceof JSONObject) {
								JSONObject fjo = cja.getJSONObject(j);

								JSONArray fja = fjo.getJSONArray("items");
								int fsize = fja.size();
								for (int k = 0; k < fsize; k++) {
									Object fo = fja.get(k);
									if (fo instanceof JSONObject) {
										JSONObject fijo = fja.getJSONObject(k);

										String key = fijo.getString("key");
										String text = fijo.getString("text");

										if (count < 200) {
											emojiList.add(key);
										}
										count++;
										putEmoji(key, text);
									}
								}
							}
						}
					}
				}
			}
		}

		FaceBox box = appContext.getObject(FaceBox.class);
		if (!emojiInfoMap.isEmpty()) {

			List<FaceInfo> faces = new ArrayList<>();
			FaceCategory fc = new FaceCategory();
			fc.setId("emoji");
			fc.setName("字符表情");
			fc.setFaceInfoList(faces);

			for (String key : emojiList) {
				FaceInfo info = emojiInfoMap.get(key);
				if (null != info) {
					faces.add(info);
				}
			}

			box.getFaceRepository().put(fc);
		}
	}

	private void putEmoji(String key, String text) {
		String categoryId = "emoji";
		String imageName = emojiToHtmlImageName(key);
		URL url = EmojiBox.getEmojiUrlByImageName(imageName);
		if (null != url) {
			String realPath = url.toExternalForm();
			FaceInfo fi = new FaceInfo();
			fi.setCategoryId(categoryId);
			fi.setKey(key);
			fi.setRealPath(realPath);
			fi.setShowPath(realPath);
			fi.setText(text);

			fi.setWidth(40);
			fi.setHeight(40);

			fi.setImageWidth(28);
			fi.setImageHeight(28);
			
			emojiInfoMap.put(key, fi);
		}
	}

	public FaceInfo getFaceInfo(String categoryId, String key) {
		FaceBox box = appContext.getObject(FaceBox.class);
		FaceInfo info = box.getFaceInfo(categoryId, key);
		return info;
	}

	public String emojiToHtmlImageName(String key) {
		String image;
		String code = EmojiUtil.emojiMultiToUnicode(key, "-");
		if (emojiImageNameMap.containsKey(code)) {
			image = emojiImageNameMap.get(code);
		} else {
			image = code + ".png";
			emojiImageNameMap.put(code, image);
		}
		return image;
	}

	public String createFaceImageTagHtml(FaceInfo face) {
		String value = face.getCategoryId() + "," + face.getKey();
		String style = "";
		if (face.getImageWidth() > 0 && face.getImageHeight() > 0) {
			style = "width:" + face.getImageWidth() + "px;" + "height:" + face.getImageHeight() + "px;";
		}
		String html = "<img" +
				" src=\"" + face.getRealPath() + "\"" +
				" title=\"" + face.getText() + "\"" +
				" value=\"" + value + "\"" +
				" path=\"" + face.getRealPath() + "\"" +
				" name=\"face\"" +
				" style=\"" + style + "\"" +
				" alt=\"" + face.getText() + "\"/>";
		return html;
	}

//
	public String replaceEmojiToImageTag(String source) {
		if (source != null) {
			Set<String> set = EmojiUtil.matchSet(source);
			for (String v : set) {
				FaceInfo info = emojiInfoMap.get(v);
				if (null != info) {
					String tag = createFaceImageTagHtml(info);
					source = source.replace(v, tag);
				}
			}
		}
		return source;
	}
}
