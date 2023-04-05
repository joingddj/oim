package com.oimchat.client.general.resources.head;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.oimchat.client.basic.util.FileNameUtil;
import com.oimchat.client.general.resources.head.bean.HeadInfo;

/**
 * @author: XiaHui
 * @date: 2018-01-27 13:14:21
 */
public class HeadRepository {

	final String defaultUserHeadPath = "/general/resources/common/images/common/head/user/*.jpg";
	final String defaultGroupHeadPath = "/general/resources/common/images/common/head/group/*.png";

	final Map<String, URL> userHeadMap = new HashMap<String, URL>();
	final Map<String, URL> groupHeadMap = new HashMap<String, URL>();
	final Map<String, HeadInfo> userHeadListMap = new HashMap<String, HeadInfo>();
	final Map<String, HeadInfo> groupHeadListMap = new HashMap<String, HeadInfo>();

	Set<String> set = new HashSet<>();

	public HeadRepository() {

		List<Resource> userheads = getListByClassPath("classpath*:" + defaultUserHeadPath);
		for (Resource resource : userheads) {
			try {

				URL url = resource.getURL();
				// File file = resource.getFile();
				String name = resource.getFilename();// .getName();

				String key = FileNameUtil.getSimpleName(name);

				HeadInfo hi = new HeadInfo();
				hi.setKey(key);
				hi.setUrl(url);

				userHeadMap.put(key, hi.getUrl());
				userHeadListMap.put(key, hi);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		List<Resource> groupheads = getListByClassPath("classpath*:" + defaultGroupHeadPath);
		for (Resource resource : groupheads) {
			try {

				URL url = resource.getURL();
				// File file = resource.getFile();
				String name = resource.getFilename();// .getName();

				String key = FileNameUtil.getSimpleName(name);

				HeadInfo hi = new HeadInfo();
				hi.setKey(key);
				hi.setUrl(url);

				groupHeadMap.put(key, hi.getUrl());
				groupHeadListMap.put(key, hi);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public URL getUserHeadPath(String key) {
		URL path = userHeadMap.get(key);
		return path;
	}

	public URL getGroupHeadPath(String key) {
		URL path = groupHeadMap.get(key);
		return path;
	}

	public List<HeadInfo> getUserHeadPathList() {
		List<HeadInfo> list = new ArrayList<HeadInfo>(userHeadListMap.values());
		return list;
	}

	public List<HeadInfo> getGroupHeadPathList() {
		List<HeadInfo> list = new ArrayList<HeadInfo>(groupHeadListMap.values());
		return list;
	}

	public static List<Resource> getListByClassPath(String classPtah) {
		List<Resource> list = new ArrayList<>();
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		try {
			Resource[] resources = resolver.getResources(classPtah);
			if (resources != null && resources.length > 0) {
				for (Resource resource : resources) {
					if (resource.exists()) {
						list.add(resource);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return list;
	}
}
