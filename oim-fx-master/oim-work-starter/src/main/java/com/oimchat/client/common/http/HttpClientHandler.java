package com.oimchat.client.common.http;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;

import com.onlyxiahui.common.lib.util.http.HttpClient4Util;
import com.onlyxiahui.common.lib.util.json.JsonUtil;
import com.onlyxiahui.common.message.request.AbstractRequestMessage;
import com.onlyxiahui.common.utils.base.lang.string.StringUtil;
import com.onlyxiahui.framework.action.dispatcher.ActionDispatcher;
import com.onlyxiahui.framework.action.dispatcher.extend.ActionRequest;
import com.onlyxiahui.framework.action.dispatcher.extend.ArgumentBox;
import com.onlyxiahui.framework.action.dispatcher.extend.impl.DefaultArgumentBox;
import com.onlyxiahui.framework.action.dispatcher.general.config.GeneralActionDispatcher;
import com.onlyxiahui.framework.net.handler.data.action.DataBackAction;
import com.onlyxiahui.wofa.client.net.core.back.annotation.Back;

/**
 * @author XiaHui
 * @date 2017-11-26 09:44:37
 */
public class HttpClientHandler {

	ActionDispatcher ad = new GeneralActionDispatcher();

	public void execute(String url, AbstractRequestMessage<?> data, Map<String, String> headMap, DataBackAction dataBackAction) {
		try {
			String json = JsonUtil.toJson(data);
			String result = HttpClient4Util.jsonPost(url, headMap, json);
			back(result, dataBackAction);
		} catch (Exception ex) {
			ex.printStackTrace();
			if (null != dataBackAction) {
				dataBackAction.lost();
			}
		}
	}

	public void back(String result, DataBackAction dataBackAction) {
		if (StringUtil.isNotBlank(result) && null != dataBackAction) {
			if (com.onlyxiahui.common.lib.util.json.JsonUtil.maybeJsonObject(result)) {

				Method[] methods = dataBackAction.getClass().getMethods();
				if (null != methods && methods.length > 0) {
					for (Method method : methods) {
						Annotation[] as = method.getAnnotations();
						for (Annotation a : as) {
							if (a instanceof Back) {

								ActionRequest actionRequest = ad.createActionRequest("", dataBackAction, method, result);
								ArgumentBox beanBox = new DefaultArgumentBox();
								ad.action(actionRequest, (d) -> {
								}, beanBox);
								break;
							}
						}
					}
				}
			} else {
				if (null != dataBackAction) {
					dataBackAction.lost();
				}
			}
		} else {
			if (null != dataBackAction) {
				dataBackAction.lost();
			}
		}
	}
}
