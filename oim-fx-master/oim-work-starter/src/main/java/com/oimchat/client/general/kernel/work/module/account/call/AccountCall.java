
package com.oimchat.client.general.kernel.work.module.account.call;

import java.util.List;

import com.oimchat.client.common.asyn.InfoBack;
import com.oimchat.client.common.asyn.ValueBack;
import com.oimchat.client.common.asyn.ValueBackInvoker;
import com.oimchat.client.common.net.back.InfoBackActionImpl;
import com.oimchat.client.common.net.back.ValueBackActionAdapter;
import com.oimchat.client.general.basic.message.builder.MessageBuilder;
import com.oimchat.client.general.kernel.work.common.call.BaseCall;
import com.oimchat.client.general.kernel.work.common.call.PathBuilder;
import com.oimchat.client.general.kernel.work.module.account.data.dto.QuestionData;
import com.oimchat.client.general.kernel.work.module.account.data.dto.SecurityQuestion;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.common.annotation.parameter.Define;
import com.onlyxiahui.common.data.common.bean.ExistInfo;
import com.onlyxiahui.common.message.bean.Info;
import com.onlyxiahui.common.message.request.RequestMessage;
import com.onlyxiahui.common.utils.base.lang.string.StringUtil;
import com.onlyxiahui.framework.net.handler.data.action.DataBackAction;
import com.onlyxiahui.wofa.client.net.core.back.annotation.Back;

/**
 * Description <br>
 * Date 2021-03-12 14:12:44<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class AccountCall extends BaseCall {

	private String action = "1.1.001";

	public AccountCall(AppContext appContext) {
		super(appContext);
	}

	public void getSecurityQuestionList(String account, ValueBack<QuestionData> vb) {
		if (StringUtil.isBlank(account)) {
			Info info = new Info();
			info.addPrompt("4.001", "账号不能为空！");
			ValueBackInvoker.back(vb, info, new QuestionData());
			return;
		}
		ValueBackActionAdapter<QuestionData> back = new ValueBackActionAdapter<QuestionData>(vb) {

			@Back
			public void back(@Define("info") Info info, @Define("body") QuestionData value) {
				ValueBackInvoker.back(vb, info, value);
			}
		};
		this.getSecurityQuestionList(account, back);
	}

	public void getSecurityQuestionList(String account, DataBackAction back) {
		String method = "1.1.0002";
		String path = PathBuilder.path(action, method);
		RequestMessage rm = MessageBuilder.request(action, method);
		rm.bodyPut("account", account);
		this.execute(path, rm, back);
	}

	public void updatePassword(String userId, String password, List<SecurityQuestion> answers, InfoBack ib) {
		InfoBackActionImpl back = new InfoBackActionImpl(ib);

		String method = "1.1.0003";
		String path = PathBuilder.path(action, method);
		RequestMessage rm = MessageBuilder.request(action, method);
		rm.bodyPut("userId", userId);
		rm.bodyPut("password", password);
		rm.bodyPut("answers", answers);
		this.execute(path, rm, back);
	}

	public void isExistAccount(String account, ValueBack<ExistInfo> vb) {
		if (StringUtil.isBlank(account)) {
			ValueBackInvoker.back(vb, new Info(), new ExistInfo());
			return;
		}
		ValueBackActionAdapter<ExistInfo> back = new ValueBackActionAdapter<ExistInfo>(vb) {

			@Back
			public void back(@Define("info") Info info, @Define("body") ExistInfo value) {
				ValueBackInvoker.back(vb, info, value);
			}
		};
		String method = "1.1.0006";
		String path = PathBuilder.path(action, method);
		RequestMessage rm = MessageBuilder.request(action, method);
		rm.bodyPut("account", account);
		this.execute(path, rm, back);
	}

	public void isExistEmail(String email, ValueBack<ExistInfo> vb) {
		if (StringUtil.isBlank(email)) {
			ValueBackInvoker.back(vb, new Info(), new ExistInfo());
			return;
		}
		ValueBackActionAdapter<ExistInfo> back = new ValueBackActionAdapter<ExistInfo>(vb) {

			@Back
			public void back(@Define("info") Info info, @Define("body") ExistInfo value) {
				ValueBackInvoker.back(vb, info, value);
			}
		};

		String method = "1.1.0007";
		String path = PathBuilder.path(action, method);
		RequestMessage rm = MessageBuilder.request(action, method);
		rm.bodyPut("email", email);
		this.execute(path, rm, back);
	}

	public void isExistMobile(String mobile, ValueBack<ExistInfo> vb) {
		if (StringUtil.isBlank(mobile)) {
			ValueBackInvoker.back(vb, new Info(), new ExistInfo());
			return;
		}
		ValueBackActionAdapter<ExistInfo> back = new ValueBackActionAdapter<ExistInfo>(vb) {

			@Back
			public void back(@Define("info") Info info, @Define("body") ExistInfo value) {
				ValueBackInvoker.back(vb, info, value);
			}
		};

		String method = "1.1.0008";
		String path = PathBuilder.path(action, method);
		RequestMessage rm = MessageBuilder.request(action, method);
		rm.bodyPut("mobile", mobile);
		this.execute(path, rm, back);
	}
}
