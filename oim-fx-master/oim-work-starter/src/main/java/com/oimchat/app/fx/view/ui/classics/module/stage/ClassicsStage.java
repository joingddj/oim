package com.oimchat.app.fx.view.ui.classics.module.stage;

import com.oimchat.app.fx.base.stage.BaseStage;

/**
 * 
 * Description <br>
 * Date 2020-07-02 18:48:55<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */
public class ClassicsStage extends BaseStage {

	public ClassicsStage() {
		init();
	}

	private void init() {
		this.getScene().getStylesheets().add(this.getClass().getResource("/general/view/theme/classics/css/base.css").toString());
		this.getScene().getStylesheets().add(this.getClass().getResource("/general/view/theme/classics/css/component.css").toString());
	}
}
