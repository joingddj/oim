
package com.oimchat.client.common.lib.util.file;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * Description <br>
 * Date 2021-03-13 18:01:48<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ClassPathFileUtil {

	public static List<Resource> getResourceListByClassPath(String classPtah) {
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
