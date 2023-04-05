
package com.oimchat.client.platform.common.config.login;

import java.util.ArrayList;
import java.util.List;

import com.oimchat.client.platform.common.config.login.data.LoginConfig;
import com.oimchat.client.platform.common.config.login.data.LoginSaveData;

/**
 * Description <br>
 * Date 2021-03-11 10:41:26<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class LoginConfigBox {
	LoginConfigAccess access = new LoginConfigAccess();

	public void put(String key, LoginSaveData data) {
		LoginConfig lc = access.get();
		if (null == lc) {
			lc = new LoginConfig();
		}
		lc.put(key, data);
		access.save(lc);
	}

	public LoginSaveData get(String key) {
		LoginConfig lc = access.get();
		if (null == lc) {
			lc = new LoginConfig();
		}
		return lc.get(key);
	}

	public LoginConfig get() {
		LoginConfig lc = access.get();
		if (null == lc) {
			lc = new LoginConfig();
		}
		return lc;
	}

	public List<LoginSaveData> allList() {
		LoginConfig lc = get();
		return (null == lc || null == lc.getConfigMap()) ? new ArrayList<>() : new ArrayList<>(lc.getConfigMap().values());
	}

	public void remove(String key) {
		LoginConfig lc = access.get();
		if (null != lc) {
			lc.remove(key);
			access.save(lc);
		}
	}

	public int size() {
		LoginConfig lc = access.get();
		return (null == lc || null == lc.getConfigMap()) ? 0 : lc.getConfigMap().size();
	}

	public int getSize() {
		return size();
	}

	public void remove(int count) {
		LoginConfig lc = access.get();
		if (null != lc) {

			List<LoginSaveData> list = allList();
			int size = list.size();
			int index = (count > size) ? size : count;
			for (int i = 0; i < index; i++) {
				lc.remove(list.get(i).getAccount());
			}
			access.save(lc);
		}
	}
}
