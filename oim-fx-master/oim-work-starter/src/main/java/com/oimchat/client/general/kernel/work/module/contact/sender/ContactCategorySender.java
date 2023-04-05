
package com.oimchat.client.general.kernel.work.module.contact.sender;

import com.oimchat.client.general.kernel.work.module.contact.entity.ContactCategory;
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
@ActionMapping(value = "1.2.002")
public interface ContactCategorySender {

	@ActionMapping(value = "1.1.0001")
	public void count(
			DataBackAction back);

	@ActionMapping(value = "1.1.0002")
	public void list(
			@Define("body.page") Page page,
			DataBackAction back);

	@ActionMapping(value = "1.1.0003")
	public void getById(
			@Define("body.id") String id,
			DataBackAction back);

	@ActionMapping(value = "1.1.0004")
	public void add(
			@Define("body") ContactCategory data,
			DataBackAction back);

	@ActionMapping(value = "1.1.0006")
	public void updateName(
			@Define("body.id") String id,
			@Define("body.name") String name,
			DataBackAction back);

	@ActionMapping(value = "1.1.0007")
	public void updateSort(
			@Define("body.id") String id,
			@Define("body.sort") Integer sort,
			DataBackAction back);

	@ActionMapping(value = "1.1.0008")
	public void deleteById(
			@Define("body.id") String id,
			DataBackAction back);
}
