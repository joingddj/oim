
package com.oimchat.client.platform.fx.view.impl.classics;

import java.util.HashMap;
import java.util.Map;

import com.oimchat.app.fx.view.ui.classics.module.stage.ClassicsCommonStage;
import com.oimchat.client.platform.common.view.GeneralApplyNoticeView;
import com.oimchat.client.platform.fx.module.contact.wrap.ContactApplyNoticeListViewWrap;
import com.oimchat.client.platform.fx.module.group.wrap.GroupInviteNoticeListViewWrap;
import com.oimchat.client.platform.fx.module.group.wrap.GroupInviteeNoticeListViewWrap;
import com.oimchat.client.platform.fx.module.group.wrap.GroupJoinNoticeListViewWrap;
import com.oimchat.client.platform.fx.module.list.store.type.GeneralApplyType;
import com.oimchat.client.platform.fx.work.inform.service.GeneralApplyInformationService;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.app.view.fx.common.util.FxUtil;

import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * Description <br>
 * Date 2021-04-02 16:53:40<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class GeneralApplyNoticeViewImpl extends BaseStageView<ClassicsCommonStage> implements GeneralApplyNoticeView {

	private final TabPane tabPane = new TabPane();
	private final Map<Integer, Tab> tabMap = new HashMap<>();

	public GeneralApplyNoticeViewImpl(AppContext appContext) {
		super(appContext);
		FxUtil.invoke(() -> {
			initStage(getStage());
			initComponent();
			initEvent();
		});
	}

	public void addTab(Integer type, String text, Node node) {
		Tab tab = new Tab();
		tab.setText(text);
		tab.setContent(node);
		tab.setClosable(false);
		tab.setUserData(type);
		tabPane.getTabs().add(tab);
		tabMap.put(type, tab);
	}

	@Override
	public void selectedTab(int index) {
		if (GeneralApplyType.ContactAddApply == index) {
			ContactApplyNoticeListViewWrap view = this.appContext.getObject(ContactApplyNoticeListViewWrap.class);
			view.initPage();
		}

		if (GeneralApplyType.GroupJoinApply == index) {
			GroupJoinNoticeListViewWrap view = this.appContext.getObject(GroupJoinNoticeListViewWrap.class);
			view.initPage();
		}

		if (GeneralApplyType.GroupInviteApply == index) {
			GroupInviteNoticeListViewWrap view = this.appContext.getObject(GroupInviteNoticeListViewWrap.class);
			view.initPage();
		}

		if (GeneralApplyType.GroupInviteeApply == index) {
			GroupInviteeNoticeListViewWrap view = this.appContext.getObject(GroupInviteeNoticeListViewWrap.class);
			view.initPage();
		}

		Tab tab = tabMap.get(index);
		if (null != tab) {
			tabPane.getSelectionModel().select(tab);
		}
	}

	@Override
	protected ClassicsCommonStage createStage() {
		return new ClassicsCommonStage();
	}

	@Override
	protected void initStage(ClassicsCommonStage stage) {
		stage.setWidth(930);
		stage.setHeight(590);

		stage.setMinWidth(930);
		stage.setMinHeight(590);

		stage.setCenter(tabPane);
		tabPane.setSide(Side.TOP);
	}

	@Override
	protected void initComponent() {
		ContactApplyNoticeListViewWrap contactApplyView = this.appContext.getObject(ContactApplyNoticeListViewWrap.class);
		addTab(GeneralApplyType.ContactAddApply, "联系人验证", contactApplyView.getRootPane());

		GroupJoinNoticeListViewWrap groupJoinView = this.appContext.getObject(GroupJoinNoticeListViewWrap.class);
		addTab(GeneralApplyType.GroupJoinApply, "入群申请验证", groupJoinView.getRootPane());

		GroupInviteNoticeListViewWrap groupInviteView = this.appContext.getObject(GroupInviteNoticeListViewWrap.class);
		addTab(GeneralApplyType.GroupInviteApply, "邀请入群验证", groupInviteView.getRootPane());

		GroupInviteeNoticeListViewWrap groupInviteeView = this.appContext.getObject(GroupInviteeNoticeListViewWrap.class);
		addTab(GeneralApplyType.GroupInviteeApply, "被邀请入群验证", groupInviteeView.getRootPane());
	}

	@Override
	protected void initEvent() {
		tabPane.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {
			Object ud = nv.getUserData();
			if (ud instanceof Integer) {
				int type = (Integer) ud;
				GeneralApplyInformationService service = this.appContext.getObject(GeneralApplyInformationService.class);
				service.removePrompt(type);
			}
		});
	}
}
