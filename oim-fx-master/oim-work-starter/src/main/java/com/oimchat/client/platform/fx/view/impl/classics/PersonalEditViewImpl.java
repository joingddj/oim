package com.oimchat.client.platform.fx.view.impl.classics;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

import com.oimchat.app.fx.view.ui.classics.module.avatar.AvatarUploadStage;
import com.oimchat.app.fx.view.ui.classics.module.stage.ClassicsTitleStage;
import com.oimchat.app.fx.view.ui.classics.module.user.UserEditPane;
import com.oimchat.client.basic.util.PathUtil;
import com.oimchat.client.general.kernel.support.file.upload.UserHeadUploader;
import com.oimchat.client.general.kernel.work.module.core.entity.User;
import com.oimchat.client.general.kernel.work.module.core.entity.UserHead;
import com.oimchat.client.general.kernel.work.module.personal.box.PersonalBox;
import com.oimchat.client.general.kernel.work.module.personal.handler.PersonalHandler;
import com.oimchat.client.platform.common.view.PersonalEditView;
import com.oimchat.client.platform.fx.work.common.handler.UserHeadImageHandler;
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
public class PersonalEditViewImpl extends BaseStageView<ClassicsTitleStage> implements PersonalEditView {

	UserEditPane editPane = new UserEditPane();
	AvatarUploadStage avatarUploadStage;

	public PersonalEditViewImpl(AppContext appContext) {
		super(appContext);
		FxUtil.invoke(() -> {
			initStage(getStage());
			initComponent();
			initEvent();
		});
	}

	protected void initEvent() {
		ClassicsTitleStage stage = getStage();
		stage.setDoneAction(a -> {
			update();
		});

		avatarUploadStage.setDoneAction(a -> {
			save();
		});

		editPane.setOnHeadMouseClicked(m -> {
			avatarUploadStage.show();
			avatarUploadStage.toFront();
		});
	}

	@Override
	public void initializeData() {
		setUserData(editPane);
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

			UserHeadUploader uhu = this.appContext.getObject(UserHeadUploader.class);
			UserHeadImageHandler uhim = this.appContext.getObject(UserHeadImageHandler.class);
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
					PersonalBox pub = appContext.getObject(PersonalBox.class);
					User user = pub.getUser();

					UserHead uh = new UserHead();

					uh.setType(UserHead.type_custom);
					uh.setUserId(user.getId());
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

	private void updateHead(UserHead userHead, Image image) {

		PersonalHandler ph = this.appContext.getObject(PersonalHandler.class);

		ph.uploadHead(userHead, (info, uh) -> {
			if (info.isSuccess()) {
				FxUtil.invoke(() -> {
					editPane.setHeadImage(image);
				});
			} else {
				showPrompt("修改失败！");
			}
		});
	}

	private void setUserData(UserEditPane frame) {
		PersonalBox pb = appContext.getObject(PersonalBox.class);
		User user = pb.getUser();

		String nickname = user.getNickname();
		String gender = user.getGender();
		String blood = user.getBlood();
		Date birthdate = user.getBirthDate();
		String homeAddress = user.getHomeAddress();
		String locationAddress = user.getLocationAddress();
		String mobile = user.getMobile();
		String email = user.getEmail();
		String signature = user.getSignature();
		String introduce = user.getIntroduce();
		FxUtil.invoke(() -> {
			frame.setNickname(nickname);
			frame.setGender(gender);
			frame.setBloodType(blood);
			frame.setBirthday(birthdate);
			frame.setHomeAddress(homeAddress);
			frame.setLocationAddress(locationAddress);
			frame.setMobile(mobile);
			frame.setEmail(email);
			frame.setSignature(signature);
			frame.setIntroduce(introduce);
		});
		UserHeadImageHandler him = appContext.getObject(UserHeadImageHandler.class);
		him.loadAvatarImage(user.getHead(), user.getAvatar(), (img) -> {
			FxUtil.invoke(() -> {
				frame.setHeadImage(img);
			});
		});
	}

	public void update() {
		boolean v = editPane.verify();
		if (!v) {
			return;
		}
		String nickname = editPane.getNickname();
		String gender = editPane.getGender();
		String blood = editPane.getBloodType();
		Date birthdate = editPane.getBirthday();
		String homeAddress = editPane.getHomeAddress();
		String locationAddress = editPane.getLocationAddress();
		String mobile = editPane.getMobile();
		String email = editPane.getEmail();
		String signature = editPane.getSignature();
		String introduce = editPane.getIntroduce();

		if (null == nickname || "".equals(nickname)) {
			showPrompt("昵称不能空！");
			return;
		}

		PersonalBox pb = appContext.getObject(PersonalBox.class);
		User userData = pb.getUser();

		User user = new User();
		user.setId(userData.getId());
		// user.setHead(head);
		user.setNickname(nickname);
		user.setGender(gender);
		user.setBlood(blood);
		user.setBirthDate(birthdate);
		user.setHomeAddress(homeAddress);
		user.setLocationAddress(locationAddress);
		user.setMobile(mobile);
		user.setEmail(email);
		user.setSignature(signature);
		user.setIntroduce(introduce);
		PersonalHandler ps = this.appContext.getObject(PersonalHandler.class);
		ps.update(user, (info) -> {
			if (info.isSuccess()) {
				setVisible(false);
			} else {
				setVisible(true);
				showPrompt("修改失败！");
			}
		});
	}

	@Override
	protected ClassicsTitleStage createStage() {
		return new ClassicsTitleStage();
	}

	@Override
	protected void initStage(ClassicsTitleStage stage) {
		stage.setWidth(400);
		stage.setHeight(550);
		stage.setResizable(false);
		stage.setTitlePaneStyle(2);
		stage.setRadius(5);
		stage.setTitle("修改资料");

		stage.setCenter(editPane);

		avatarUploadStage = new AvatarUploadStage();
	}

	@Override
	protected void initComponent() {
		// TODO Auto-generated method stub

	}
}
