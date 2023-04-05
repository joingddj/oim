
package com.onlyxiahui.app.basic.view;

/**
 * Description <br>
 * Date 2020-12-26 15:08:32<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public interface View {

	public void setVisible(boolean visible);

	/**
	 * 
	 * @description: 是否显示中
	 * @author XiaHui
	 * @date 2017-10-21 07:53:21
	 * @return
	 */
	public boolean isShowing();

	/**
	 * 
	 * @description:弹出提示信息
	 * @author XiaHui
	 * @date 2017-10-21 07:53:36
	 * @param text
	 */
	public void showPrompt(String text);

}
