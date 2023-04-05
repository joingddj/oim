
package com.oimchat.client.general.kernel.work.module.contact.sender;

import java.util.List;

import com.onlyxiahui.common.annotation.parameter.Define;
import com.onlyxiahui.common.data.common.data.Page;
import com.onlyxiahui.framework.action.dispatcher.annotation.ActionMapping;
import com.onlyxiahui.framework.net.handler.data.action.DataBackAction;
import com.onlyxiahui.wofa.client.net.send.annotation.Sender;

/**
 * Description <br>
 * Date 2021-03-15 14:44:08<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */
@Sender
@ActionMapping(value = "1.2.003")
public interface ContactRelationSender {

	@ActionMapping(value = "1.1.0001")
	public void count(
			DataBackAction back);

	@ActionMapping(value = "1.1.0002")
	public void list(
			@Define("body.page") Page page,
			DataBackAction back);

	@ActionMapping(value = "1.1.0004")
	public void getByContactUserId(
			@Define("body.contactUserId") String contactUserId,
			DataBackAction back);

	@ActionMapping(value = "1.1.0009")
	public void getByContactUserIds(
			@Define("body.contactUserIds") List<String> contactUserIds,
			DataBackAction back);

	@ActionMapping(value = "1.1.0005")
	public void updateRemark(
			@Define("body.contactUserId") String contactUserId,
			@Define("body.remark") String remark,
			DataBackAction back);

	@ActionMapping(value = "1.1.0006")
	public void moveCategory(
			@Define("body.contactUserIds") List<String> contactUserIds,
			@Define("body.categoryId") String categoryId,
			DataBackAction back);

	@ActionMapping(value = "1.1.0007")
	public void deleteByContactUserId(
			@Define("body.contactUserId") String contactUserId,
			DataBackAction back);

	@ActionMapping(value = "1.1.0008")
	public void updateBlocked(
			@Define("body.contactUserId") String contactUserId,
			@Define("body.isBlocked") String isBlocked,
			DataBackAction back);

}
