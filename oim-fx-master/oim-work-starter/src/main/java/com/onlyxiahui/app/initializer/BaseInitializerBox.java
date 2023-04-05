
package com.onlyxiahui.app.initializer;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.utils.base.lang.reflect.ClassUtil;
import com.onlyxiahui.framework.action.dispatcher.util.ClassScaner;

/**
 * Description <br>
 * Date 2021-03-09 21:25:16<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public abstract class BaseInitializerBox<T extends BaseInitializer> extends AbstractMaterial {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	public static final String INITIALIZER_RESOURCE_LOCATION = "META-INF/app.initializer";
	private final List<BaseInitializer> list = new ArrayList<>();

	public BaseInitializerBox(AppContext appContext) {
		super(appContext);
		load();
	}

	public abstract String key();

	public abstract Class<T> initializerBaseClass();

	protected void load() {
		logger.debug(this.getClass().getName());
		String key = this.key();
		if (null != key && !key.isEmpty()) {
			ClassLoader classLoader = getDefaultClassLoader();
			List<String> packages = loadPackages(key, classLoader);
			for (String packagePath : packages) {
				initializerLoad(packagePath);
				logger.debug("loadPackages:" + packagePath);
			}
		}
	}

	protected void initializerLoad(String packagePath) {
		Class<?> baseClass = initializerBaseClass();
		if (null != baseClass && (null != packagePath && !packagePath.isEmpty())) {
			Set<Class<?>> classSet = ClassScaner.scan(packagePath);
			Set<Class<?>> initializerSet = new HashSet<>();
			for (Class<?> c : classSet) {
				if (baseClass.isAssignableFrom(c) && ClassUtil.isCanInstance(c)) {
					initializerSet.add(c);
					logger.debug(packagePath + "/" + c + "");
				}
			}
			for (Class<?> c : initializerSet) {
				BaseInitializer enter = (BaseInitializer) appContext.getObject(c);
				add(enter);
			}
		}
	}

	public void add(BaseInitializer enter) {
		if (null != enter && !list.contains(enter)) {
			list.add(enter);
		}
	}

	public void initialize() {
		List<Class<?>> completeList = new ArrayList<>();
		initialize(list, completeList);
	}

	private void initialize(List<BaseInitializer> readyList, List<Class<?>> completeList) {
		if (!readyList.isEmpty()) {
			List<BaseInitializer> tempList = new ArrayList<>();
			for (BaseInitializer enter : readyList) {
				List<Class<?>> cs = enter.onAfter();

				if (null != cs) {
					boolean can = true;
					for (Class<?> tc : cs) {
						if (!completeList.contains(tc)) {
							can = false;
							break;
						}
					}

					if (can) {
						enter.initialize(appContext);
						completeList.add(enter.getClass());
					} else {
						if (hasAllAfter(cs)) {
							tempList.add(enter);
						}
					}
				} else {
					enter.initialize(appContext);
					completeList.add(enter.getClass());
				}
			}
			initialize(tempList, completeList);
		}
	}

	private boolean hasAllAfter(List<Class<?>> cs) {
		boolean has = true;
		if (null != cs) {
			for (Class<?> tc : cs) {
				boolean ex = false;
				for (BaseInitializer enter : list) {
					if (tc == enter.getClass()) {
						ex = true;
						break;
					}
				}
				if (!ex) {
					has = false;
					break;
				}
			}
		}
		return has;
	}

	public static ClassLoader getDefaultClassLoader() {
		ClassLoader cl = null;
		try {
			cl = Thread.currentThread().getContextClassLoader();
		} catch (Throwable ex) {
			// Cannot access thread context ClassLoader - falling back...
		}
		if (cl == null) {
			// No thread context class loader -> use class loader of this class.
			cl = BaseInitializerBox.class.getClassLoader();
			if (cl == null) {
				// getClassLoader() returning null indicates the bootstrap ClassLoader
				try {
					cl = ClassLoader.getSystemClassLoader();
				} catch (Throwable ex) {
					// Cannot access system ClassLoader - oh well, maybe the caller can live with
					// null...
				}
			}
		}
		return cl;
	}

	public static List<String> loadPackages(String key, ClassLoader classLoader) {
		return loadInitializerPackageMap(classLoader).getOrDefault(key, Collections.emptyList());
	}

	private static Map<String, List<String>> loadInitializerPackageMap(ClassLoader classLoader) {
		MultiValueMap<String, String> result = new LinkedMultiValueMap<>();

		try {
			Enumeration<URL> urls = (classLoader != null ? classLoader.getResources(INITIALIZER_RESOURCE_LOCATION) : ClassLoader.getSystemResources(INITIALIZER_RESOURCE_LOCATION));
			while (urls.hasMoreElements()) {
				URL url = urls.nextElement();
				UrlResource resource = new UrlResource(url);
				Properties properties = PropertiesLoaderUtils.loadProperties(resource);
				for (Map.Entry<?, ?> entry : properties.entrySet()) {
					String factoryTypeName = ((String) entry.getKey()).trim();
					for (String factoryImplementationName : StringUtils.commaDelimitedListToStringArray((String) entry.getValue())) {
						result.add(factoryTypeName, factoryImplementationName.trim());
					}
				}
			}
			return result;
		} catch (IOException ex) {
			throw new IllegalArgumentException("Unable to load initializer from location [" + INITIALIZER_RESOURCE_LOCATION + "]", ex);
		}
	}
}
