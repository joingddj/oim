package com.oimchat.client.platform.kernel.work.module.im.box;

import java.net.URL;
import java.util.List;

import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.oim.face.EmojiBox;
import com.onlyxiahui.oim.face.FaceRepository;
import com.onlyxiahui.oim.face.bean.FaceCategory;
import com.onlyxiahui.oim.face.bean.FaceInfo;

/**
 * @author XiaHui
 * @date 2017年10月16日 下午8:02:54
 */
public class FaceBox extends AbstractMaterial {

	FaceRepository fr = new FaceRepository();

	public FaceBox(AppContext appContext) {
		super(appContext);
	}

	public List<FaceCategory> getAllFaceCategoryList() {
		return fr.getAllFaceCategoryList();
	}

	public String getFacePath(String categoryId, String key) {
		return fr.getFacePath(categoryId, key);
	}

	public FaceInfo getFaceInfo(String categoryId, String key) {
		return fr.getFaceInfo(categoryId, key);
	}

	public URL getEmojiURL(String emoji) {
		return EmojiBox.getEmojiUrlByImageName(emoji);
	}

	public FaceRepository getFaceRepository() {
		return fr;
	}
}
