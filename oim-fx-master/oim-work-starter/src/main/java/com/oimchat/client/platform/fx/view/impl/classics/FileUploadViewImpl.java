package com.oimchat.client.platform.fx.view.impl.classics;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;

import com.oimchat.app.fx.base.component.list.ListRootPane;
import com.oimchat.app.fx.view.ui.classics.module.file.FileUpItem;
import com.oimchat.app.fx.view.ui.classics.module.stage.ClassicsCommonStage;
import com.oimchat.client.common.event.ValueAction;
import com.oimchat.client.common.net.file.bean.FileData;
import com.oimchat.client.general.common.task.RunExecutor;
import com.oimchat.client.general.kernel.support.file.upload.FileUploader;
import com.oimchat.client.platform.common.view.FileUploadView;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.app.view.fx.common.util.FxUtil;
import com.onlyxiahui.app.view.fx.common.util.ImageLoadUtil;
import com.onlyxiahui.common.utils.base.lang.bytes.BytesSizeUtil;

import javafx.scene.image.Image;

/**
 * @author XiaHui
 * @date 2017年6月2日 下午9:35:57
 */
public class FileUploadViewImpl extends BaseStageView<ClassicsCommonStage> implements FileUploadView {

	ListRootPane listPane = new ListRootPane();

	public FileUploadViewImpl(AppContext appContext) {
		super(appContext);
		FxUtil.invoke(() -> {
			initStage(getStage());
			initComponent();
			initEvent();
		});
	}

	@Override
	protected ClassicsCommonStage createStage() {
		return new ClassicsCommonStage();
	}

	@Override
	protected void initStage(ClassicsCommonStage stage) {
		stage.setWidth(380);
		stage.setHeight(520);
		stage.setResizable(false);
		stage.setTitlePaneStyle(2);
		stage.setTitle("文件发送");
		stage.setCenter(listPane);

	}

	@Override
	protected void initComponent() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initEvent() {
		// TODO Auto-generated method stub

	}

	@Override
	public void upload(File file, ValueAction<FileData> callBackAction) {
		setVisible(true);
		RunExecutor run = appContext.getObject(RunExecutor.class);
		run.execute(() -> {

			FileUploader fm = appContext.getObject(FileUploader.class);
			String fileName = file.getName();
			Image image = ImageLoadUtil.getImageByClassPath("/general/resources/common/images/common/file/default.png");
			FileUpItem fui = new FileUpItem();
			FxUtil.invoke(() -> {
				fui.setImage(image);
				fui.setName(fileName);
				// fui.setSize(value);

				fui.setOnCloseAction(c -> {
					listPane.removeNode(fui);
				});
				listPane.addNode(fui);
			});

			fm.syncUpload(file, (p) -> {
				long speed = p.getSpeed();
				long fileSize = p.getSize();
				long finishSize = p.getFinishSize();
				double progress = p.getProgress();
				String speedText = BytesSizeUtil.getSizeUnit(speed, 2) + "/S";
				String fileSizeText = BytesSizeUtil.getSizeUnit(fileSize, 2);
				String finishSizeText = BytesSizeUtil.getSizeUnit(finishSize, 2);
				BigDecimal bg = new BigDecimal(progress).setScale(2, RoundingMode.UP);

				FxUtil.invoke(() -> {
					fui.setSpeed(speedText);
					fui.setSize(finishSizeText + "/" + fileSizeText);
					fui.setProgress(bg.doubleValue());
				});
			}, (info, fd) -> {
				if (info.isSuccess()) {
					FxUtil.invoke(() -> {
						fui.setProgress(1.0d);
					});
					callBackAction.value(fd);
				} else {
					FxUtil.invoke(() -> {
						fui.setProgress(0.1d);
						showPrompt("文件发送失败！");
					});
				}
			});
		});
	}

	@Override
	public void showWaiting(boolean show) {
		// TODO Auto-generated method stub

	}
}
