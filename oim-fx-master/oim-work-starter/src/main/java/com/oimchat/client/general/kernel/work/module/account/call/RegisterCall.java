
package com.oimchat.client.general.kernel.work.module.account.call;

import java.util.List;

import com.oimchat.client.common.asyn.ValueBack;
import com.oimchat.client.common.asyn.ValueBackInvoker;
import com.oimchat.client.common.net.back.ValueBackActionAdapter;
import com.oimchat.client.general.basic.message.builder.MessageBuilder;
import com.oimchat.client.general.kernel.work.common.call.BaseCall;
import com.oimchat.client.general.kernel.work.common.call.PathBuilder;
import com.oimchat.client.general.kernel.work.module.account.data.dto.SecurityQuestion;
import com.oimchat.client.general.kernel.work.module.account.data.dto.UserRegister;
import com.oimchat.client.general.kernel.work.module.core.entity.User;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.annotation.parameter.Define;
import com.onlyxiahui.common.message.bean.Info;
import com.onlyxiahui.common.message.request.RequestMessage;
import com.onlyxiahui.framework.net.handler.data.action.DataBackAction;
import com.onlyxiahui.wofa.client.net.core.back.annotation.Back;

/**
 * Description <br>
 * Date 2021-03-12 14:12:44<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class RegisterCall extends BaseCall {

	private String action = "1.1.001";

	public RegisterCall(AppContext appContext) {
		super(appContext);
	}

	public void register(UserRegister data, List<SecurityQuestion> questions, ValueBack<User> vb) {
		ValueBackActionAdapter<User> back = new ValueBackActionAdapter<User>(vb) {

			@Back
			public void back(@Define("info") Info info, @Define("body") User value) {
				ValueBackInvoker.back(vb, info, value);
			}
		};
		this.register(data, questions, back);
	}

	public void register(UserRegister data, List<SecurityQuestion> questions, DataBackAction back) {
		String method = "1.1.0001";
		String path = PathBuilder.path(action, method);
		RequestMessage rm = MessageBuilder.request(action, method);
		rm.bodyPut("user", data);
		rm.bodyPut("questions", questions);
		this.execute(path, rm, back);
	}
}
