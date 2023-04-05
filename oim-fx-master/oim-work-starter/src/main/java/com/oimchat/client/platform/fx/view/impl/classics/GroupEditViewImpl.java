package com.oimchat.client.platform.fx.view.impl.classics;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

import com.oimchat.app.fx.view.ui.classics.module.avatar.AvatarUploadStage;
import com.oimchat.app.fx.view.ui.classics.module.group.GroupEditStage;
import com.oimchat.client.basic.util.PathUtil;
import com.oimchat.client.general.kernel.support.file.upload.GroupHeadUploader;
import com.oimchat.client.general.kernel.work.module.group.entity.Group;
import com.oimchat.client.general.kernel.work.module.group.entity.GroupHead;
import com.oimchat.client.general.kernel.work.module.group.handler.GroupBusinessHandler;
import com.oimchat.client.general.kernel.work.module.group.handler.GroupHandler;
import com.oimchat.client.platform.common.view.GroupEditView;
import com.oimchat.client.platform.fx.work.common.handler.GroupHeadImageHandler;
import com.oimchat.client.platform.kernel.work.common.env.PathEnv;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.app.view.fx.common.util.FxUtil;
import com.onlyxiahui.common.utils.base.io.FileUtil;
import com.onlyxiahui.common.utils.base.security.Md5Util;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

/**
 * 描述：
 * 
 * @author XiaHui
 * @date 2015年3月16日 下午10:42:19
 * @version 0.0.1
 */
public class GroupEditViewImpl extends BaseStageView<GroupEditStage> implements GroupEditView {

	AvatarUploadStage avatarUploadStage;
	String head;
	Group group;
	String groupId;

	public GroupEditViewImpl(AppContext appContext) {
		super(appContext);
		FxUtil.invoke(() -> {
			initStage(getStage());
			initComponent();
			initEvent();
		});
	}

	protected void initEvent() {
		stage.setDoneAction(a -> {
			update();
		});
		avatarUploadStage.setDoneAction(a -> {
			save();
		});

		stage.setOnHeadMouseClicked(m -> {
			avatarUploadStage.show();
			avatarUploadStage.toFront();
		});
	}

	public void update() {

		if (group == null) {
			return;
		}

		String name = stage.getName();
		String introduce = stage.getIntroduce();
		if (null == name || "".equals(name)) {
			showPrompt("名称不能空！");
			return;
		}

		group.setName(name);
		group.setIntroduce(introduce);

		GroupBusinessHandler gh = this.appContext.getObject(GroupBusinessHandler.class);
		gh.update(group, (info) -> {
			if (info.isSuccess()) {
				setVisible(false);
			} else {
				showPrompt("修改失败！");
			}
		});
	}

	public void setGroup(Group group) {
		this.group = group;
		int i = new Random().nextInt(3);
		i = i + 1;
		this.head = i + "";
		String head = this.head;
		String avatar = null;
		if (null != group) {
			head = group.getHead();
			avatar = group.getAvatar();
			FxUtil.invoke(() -> {
				stage.setName(group.getName());
				stage.setIntroduce(group.getIntroduce());
				stage.setTitleText("编辑群");
			});
		}
		GroupHeadImageHandler uhim = this.appContext.getObject(GroupHeadImageHandler.class);
		uhim.loadAvatarImage(head, avatar, (img) -> {
			FxUtil.invoke(() -> {
				stage.setHeadImage(img);
			});
		});
	}

	@Override
	public void setGroupId(String groupId) {
		this.groupId = groupId;
		if (null != groupId && !groupId.trim().isEmpty()) {
			GroupHandler uh = this.appContext.getObject(GroupHandler.class);
			uh.getById(groupId, (info, group) -> {
				setGroup(group);
			});
		}
	}

	private void save() {
		boolean v = avatarUploadStage.verify();
		if (v) {
			avatarUploadStage.hide();
			Image image = avatarUploadStage.getImage();
			save(image);
		}
	}

	public void save(Image image) {
		try {

			PathEnv pb = appContext.getObject(PathEnv.class);
			String path = pb.getPersonalHeadPath();
			String filePath = path + "temp.png";
			FileUtil.checkOrCreateFolder(filePath);
			File file = new File(filePath);
			ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);

			GroupHeadUploader uhu = this.appContext.getObject(GroupHeadUploader.class);
			GroupHeadImageHandler uhim = this.appContext.getObject(GroupHeadImageHandler.class);
			uhu.syncUpload(file, (p) -> {
			}, (info, fileData) -> {
				if (info.isSuccess()) {
					// String id = fileData.getId();
					String url = fileData.getUrl();

					String rootPath = uhim.rootPath();
					String newFilePath = PathUtil.merge(rootPath, Md5Util.lower32(url));
					// String newFilePath = path + id + ".png";
					File newFile = new File(newFilePath);
					try {
						FileUtils.copyFile(file, newFile);
					} catch (IOException e) {
						e.printStackTrace();
					}

					GroupHead uh = new GroupHead();

					uh.setType(GroupHead.type_custom);
					uh.setGroupId(groupId);
					uh.setFileName(newFile.getName());
					uh.setUrl(url);

					updateHead(uh, image);
				} else {
					showPrompt("上传失败！");
				}
			});
		} catch (IOException ex) {
			showPrompt("上传失败！");
		}
	}

	private void updateHead(GroupHead groupHead, Image image) {

		GroupBusinessHandler ph = this.appContext.getObject(GroupBusinessHandler.class);

		ph.uploadHead(groupHead, (info, uh) -> {
			if (info.isSuccess()) {
				FxUtil.invoke(() -> {
					stage.setHeadImage(image);
				});
			} else {
				showPrompt("修改失败！");
			}
		});
	}

	@Override
	protected GroupEditStage createStage() {
		return new GroupEditStage();
	}

	@Override
	protected void initStage(GroupEditStage stage) {
		stage.setTitleText("");
		avatarUploadStage = new AvatarUploadStage();
		putStage(avatarUploadStage);
	}

	@Override
	protected void initComponent() {
		// TODO Auto-generated method stub

	}
}
