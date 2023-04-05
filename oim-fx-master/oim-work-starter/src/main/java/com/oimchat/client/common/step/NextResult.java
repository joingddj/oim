
package com.oimchat.client.common.step;

import com.onlyxiahui.common.message.bean.Info;

/**
 * Description <br>
 * Date 2021-03-10 11:19:51<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class NextResult<R> {

	R data;
	Info info;

	public R getData() {
		return data;
	}

	public void setData(R data) {
		this.data = data;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}
}
