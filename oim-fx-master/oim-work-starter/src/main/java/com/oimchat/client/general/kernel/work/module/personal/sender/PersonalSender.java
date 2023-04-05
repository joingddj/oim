
package com.oimchat.client.general.kernel.work.module.personal.sender;

import com.oimchat.client.general.kernel.work.module.core.entity.User;
import com.oimchat.client.general.kernel.work.module.core.entity.UserHead;
import com.onlyxiahui.common.annotation.parameter.Define;
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
@ActionMapping(value = "1.1.002")
public interface PersonalSender {

	@ActionMapping(value = "1.1.0007")
	public void get(DataBackAction back);

	@ActionMapping(value = "1.1.0009")
	public void update(@Define("body") User user, DataBackAction back);

	@ActionMapping(value = "1.1.0010")
	public void updatePassword(
			@Define("body.oldPassword") String oldPassword,
			@Define("body.newPassword") String newPassword,
			DataBackAction back);

	@ActionMapping(value = "1.1.0011")
	public void uploadHead(
			@Define("body") UserHead userHead, DataBackAction back);

	@ActionMapping(value = "1.1.0012")
	public void updateSignature(
			@Define("body.signature") String signature, DataBackAction back);

	@ActionMapping(value = "1.1.0013")
	public void updateStatus(
			@Define("body.status") String status, DataBackAction back);
}
