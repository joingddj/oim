
package com.oimchat.client.general.kernel.work.module.core.sender;

import java.util.List;

import com.oimchat.client.general.kernel.work.module.core.data.query.UserQuery;
import com.onlyxiahui.common.annotation.parameter.Define;
import com.onlyxiahui.common.data.common.data.Page;
import com.onlyxiahui.framework.action.dispatcher.annotation.ActionMapping;
import com.onlyxiahui.framework.net.handler.data.action.DataBackAction;
import com.onlyxiahui.wofa.client.net.send.annotation.Sender;

/**
 * Description <br>
 * Date 2021-03-15 14:10:53<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */
@Sender
@ActionMapping(value = "1.1.003")
public interface UserSender {

	@ActionMapping(value = "1.1.0001")
	public void list(
			@Define("body.query") UserQuery query,
			@Define("body.page") Page page,
			DataBackAction back);

	@ActionMapping(value = "1.1.0002")
	public void getById(@Define("body.id") String id, DataBackAction back);

	@ActionMapping(value = "1.1.0003")
	public void getListByIds(@Define("body.ids") List<String> ids, DataBackAction back);

}
