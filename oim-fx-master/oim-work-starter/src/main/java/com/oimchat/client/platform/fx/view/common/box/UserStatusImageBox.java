
package com.oimchat.client.platform.fx.view.common.box;

import com.oimchat.client.general.kernel.work.module.core.type.UserStatus;
import com.onlyxiahui.app.view.fx.common.util.ImageLoadUtil;

import javafx.scene.image.Image;

/**
 * Description <br>
 * Date 2021-03-17 15:20:28<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class UserStatusImageBox {

	public static Image getStatusImageIcon(String status) {
		Image image = null;
		status = (null == status) ? "" : status;
		switch (status) {
		case UserStatus.status_online:
			image = ImageLoadUtil.getImageByClassPath("/general/resources/common/images/common/status/flag/big/imonline.png");
			break;
		case UserStatus.status_call_me:
			image = ImageLoadUtil.getImageByClassPath("/general/resources/common/images/common/status/flag/big/call_me.png");
			break;
		case UserStatus.status_away:
			image = ImageLoadUtil.getImageByClassPath("/general/resources/common/images/common/status/flag/big/away.png");
			break;
		case UserStatus.status_busy:
			image = ImageLoadUtil.getImageByClassPath("/general/resources/common/images/common/status/flag/big/busy.png");
			break;
		case UserStatus.status_mute:
			image = ImageLoadUtil.getImageByClassPath("/general/resources/common/images/common/status/flag/big/mute.png");
			break;
		case UserStatus.status_invisible:
			image = ImageLoadUtil.getImageByClassPath("/general/resources/common/images/common/status/flag/big/invisible.png");
			break;
		case UserStatus.status_offline:
			image = ImageLoadUtil.getImageByClassPath("/general/resources/common/images/common/status/flag/big/imoffline.png");
			break;
		default:
			image = ImageLoadUtil.getImageByClassPath("/general/resources/common/images/common/status/flag/big/imoffline.png");
			break;
		}
		return image;
	}

	public static Image getContactStatusImageIcon(String status) {
		Image image = null;
		status = (null == status) ? "" : status;
		switch (status) {

		case UserStatus.status_invisible:
			image = ImageLoadUtil.getImageByClassPath("/general/resources/common/images/common/status/flag/big/imoffline.png");
			break;
		case UserStatus.status_offline:
			image = ImageLoadUtil.getImageByClassPath("/general/resources/common/images/common/status/flag/big/imoffline.png");
			break;
		default:
			image = getStatusImageIcon(status);
			break;
		}
		return image;
	}
}
