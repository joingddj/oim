package com.oimchat.client.general.kernel.work.module.personal.action;

import com.oimchat.client.general.basic.message.data.Client;
import com.oimchat.client.general.kernel.work.module.personal.service.PersonalService;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.annotation.parameter.Define;
import com.onlyxiahui.framework.action.dispatcher.annotation.ActionMapping;

/**
 * 
 * 个人业务推送 <br>
 * Date 2020-04-09 20:34:28<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 * @docModuleSuperKey 1.1.0
 */
@ActionMapping("1.1.002")
public class PersonalAction extends AbstractMaterial {

	public PersonalAction(AppContext appContext) {
		super(appContext);
	}

	/**
	 * 推送其他客户端登陆信息 <br>
	 * Date 2020-04-10 09:37:49<br>
	 * 
	 * @param client  上线的客户端信息
	 * @param offline 收到消息的客户端是否下线
	 * @since 1.0.0
	 */
	@ActionMapping()
	public void otherOnline(
			@Define("body.client") Client client,
			@Define("body.offline") boolean offline) {
		PersonalService service = this.appContext.getObject(PersonalService.class);
		service.otherOnline(client, offline);
	}

	/**
	 * 
	 * 推送个人信息更新消息 <br>
	 * Date 2020-04-10 09:39:46<br>
	 * 
	 * @since 1.0.0
	 */
	@ActionMapping("1.2.0002")
	public void update() {
		PersonalService service = this.appContext.getObject(PersonalService.class);
		service.loadPersonal();
	}

	/**
	 * 
	 * 推送个人密码更新消息<br>
	 * Date 2020-04-10 09:41:50<br>
	 * 
	 * @param writeKey
	 * @param key
	 * @since 1.0.0
	 */
	@ActionMapping("1.2.0003")
	public void updatePassword() {
		PersonalService service = this.appContext.getObject(PersonalService.class);
		service.updatePassword();
	}

	/**
	 * 
	 * 推送个人头像修改消息<br>
	 * Date 2020-04-10 09:41:46<br>
	 * 
	 * @param writeKey
	 * @param key
	 * @since 1.0.0
	 */
	@ActionMapping("1.2.0004")
	public void updateHead() {
		PersonalService service = this.appContext.getObject(PersonalService.class);
		service.loadPersonal();
	}

	/**
	 * 
	 * 推送个人签名修改消息<br>
	 * Date 2020-04-10 09:41:43<br>
	 * 
	 * @param writeKey
	 * @param key
	 * @param signature
	 * @since 1.0.0
	 */
	@ActionMapping("1.2.0005")
	public void updateSignature(
			@Define("body.signature") String signature) {
		PersonalService service = this.appContext.getObject(PersonalService.class);
		service.loadPersonal();
	}

	/**
	 * 
	 * 推送个人状态变更消息<br>
	 * Date 2020-04-10 09:41:39<br>
	 * 
	 * @param writeKey
	 * @param key
	 * @param status
	 * @since 1.0.0
	 */
	@ActionMapping("1.2.0006")
	public void updateStatus(
			@Define("body.status") String status) {
		PersonalService service = this.appContext.getObject(PersonalService.class);
		service.updateStatus(status);
	}
}
