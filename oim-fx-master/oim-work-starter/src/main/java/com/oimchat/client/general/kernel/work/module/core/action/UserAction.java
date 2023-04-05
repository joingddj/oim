package com.oimchat.client.general.kernel.work.module.core.action;

import com.oimchat.client.general.kernel.work.module.core.service.UserService;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.annotation.parameter.Define;
import com.onlyxiahui.framework.action.dispatcher.annotation.ActionMapping;

/**
 * 
 * 用户业务推送 <br>
 * Date 2020-04-09 18:59:25<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 * @docModuleSuperKey 1.1.0
 */
@ActionMapping("1.1.003")
public class UserAction extends AbstractMaterial {

	public UserAction(AppContext appContext) {
		super(appContext);
	}

	/**
	 * 推送用户更新信息<br>
	 * Date 2019-01-26 10:57:37<br>
	 * 
	 * @param userId 信息变更的用户id
	 * @since 1.0.0
	 */
	@ActionMapping("1.2.0001")
	public void update(
			@Define("body.id") String userId) {
		UserService service = this.appContext.getObject(UserService.class);
		service.update(userId);
	}

	/**
	 * 推送用户头像修改信息<br>
	 * Date 2019-01-26 11:00:24<br>
	 * 
	 * @param userId 用户id
	 * @since 1.0.0
	 */
	@ActionMapping("1.2.0002")
	public void updateHead(
			@Define("body.id") String userId) {
		UserService service = this.appContext.getObject(UserService.class);
		service.updateHead(userId);
	}

	/**
	 * 
	 * 推送用户签名更新<br>
	 * Date 2019-01-26 09:50:00<br>
	 * 
	 * @param userId    用户id
	 * @param signature 签名
	 * @since 1.0.0
	 */
	@ActionMapping("1.2.0003")
	public void updateSignature(
			@Define("body.id") String userId,
			@Define("body.signature") String signature) {
		UserService service = this.appContext.getObject(UserService.class);
		service.updateSignature(userId, signature);
	}

	/**
	 * 推送用户在先状态变化<br>
	 * Date 2019-01-26 09:49:08<br>
	 * 
	 * @param userId 状态变化用户id
	 * @param status 状态
	 * @since 1.0.0
	 */
	@ActionMapping("1.2.0004")
	public void updateStatus(
			@Define("body.id") String userId,
			@Define("body.status") String status) {
		UserService service = this.appContext.getObject(UserService.class);
		service.updateStatus(userId, status);
	}

	/**
	 * 
	 * 推送用户删除 <br>
	 * Date 2020-05-29 10:08:21<br>
	 * 
	 * @param userId
	 * @since 1.0.0
	 */
	@ActionMapping("1.2.0005")
	public void delete(
			@Define("body.id") String userId) {
		UserService service = this.appContext.getObject(UserService.class);
		service.delete(userId);
	}
}
