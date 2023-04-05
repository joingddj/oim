package com.oimchat.app.fx.base.component.face;

import java.util.List;

import com.onlyxiahui.app.fx.OnlyPopup;
import com.onlyxiahui.app.view.fx.component.icon.IconButton;

import javafx.scene.Node;
import javafx.stage.Window;

/**
 * 
 * Description <br>
 * Date 2020-07-05 21:00:02<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */
public class FacePopup {

	FacePane fp = new FacePane();
	OnlyPopup po = new OnlyPopup();

	public FacePopup() {
		po.setArrowLocation(OnlyPopup.ArrowLocation.BOTTOM_CENTER);
		po.setContentNode(fp);
		po.setArrowSize(0);
	}

	public final void show(Window ownerWindow, double anchorX, double anchorY) {
		po.show(ownerWindow, anchorX, anchorY);
	}

	public final void show(Node owner, double x, double y) {
		po.show(owner, x, y);
	}

	public final void show(Node owner) {
		po.show(owner);
	}

	public void set(String key, String name, List<IconButton> list) {
		fp.set(key, name, list);
	}

	public void hide() {
		po.hide();
	}
//	public void setAction(EventAction<FaceData> a) {
//		this.eventAction = a;
//		// set.add(a);
//	}
//
//	private void handle(String key, FaceData FaceData) {
//		if (null != eventAction) {
//			eventAction.execute(FaceData);
//		}
//		// for (EventAction<FaceData> ea : set) {
//		// ea.execute(FaceData);
//		// }
//	}
}
