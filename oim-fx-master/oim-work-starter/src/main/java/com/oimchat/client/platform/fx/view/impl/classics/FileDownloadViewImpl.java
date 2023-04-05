package com.oimchat.client.platform.fx.view.impl.classics;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;

import com.oimchat.app.fx.base.component.list.ListRootPane;
import com.oimchat.app.fx.view.ui.classics.module.file.FileDownItem;
import com.oimchat.app.fx.view.ui.classics.module.stage.ClassicsCommonStage;
import com.oimchat.client.common.net.file.bean.FileData;
import com.oimchat.client.general.kernel.support.file.download.FileDownload;
import com.oimchat.client.platform.common.view.FileDownloadView;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.app.view.fx.common.util.FxUtil;
import com.onlyxiahui.app.view.fx.common.util.ImageLoadUtil;
import com.onlyxiahui.common.utils.base.lang.bytes.BytesSizeUtil;

import javafx.scene.image.Image;
import javafx.stage.FileChooser;

/**
 * @author XiaHui
 * @date 2017年6月2日 下午10:30:12
 */
public class FileDownloadViewImpl extends BaseStageView<ClassicsCommonStage> implements FileDownloadView {

	ListRootPane listPane = new ListRootPane();

	private FileChooser fileChooser;

	public FileDownloadViewImpl(AppContext appContext) {
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
		stage.setCenter(listPane);
		stage.setTitle("文件下载");
		fileChooser = new FileChooser();
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
	public void download(FileData fileData) {
		setVisible(true);

		FileDownload fm = appContext.getObject(FileDownload.class);

		Image image = ImageLoadUtil.getImageByClassPath("/general/resources/common/images/common/file/default.png");

		FileDownItem fdi = new FileDownItem();
		String sizeText = BytesSizeUtil.getSizeUnit(fileData.getSize(), 2);

		FxUtil.invoke(() -> {

			fdi.setName(fileData.getName());
			fdi.setImage(image);
			fdi.setSize(sizeText);
			fdi.showSaveAs(false);

			fdi.setOnCloseAction(c -> {
				listPane.removeNode(fdi);
			});
			listPane.addNode(fdi);

			fileChooser.setInitialFileName(fileData.getName());
			File file = fileChooser.showSaveDialog(getStage());
			if (null != file) {
				fm.asynDownload(fileData.getUrl(), file, (p) -> {
					long speed = p.getSpeed();
					long fileSize = p.getSize();
					long finishSize = p.getFinishSize();
					double progress = p.getProgress();
					String speedText = BytesSizeUtil.getSizeUnit(speed, 2) + "/S";
					String fileSizeText = BytesSizeUtil.getSizeUnit(fileSize, 2);
					String finishSizeText = BytesSizeUtil.getSizeUnit(finishSize, 2);
					BigDecimal bg = new BigDecimal(progress).setScale(2, RoundingMode.UP);

					FxUtil.invoke(() -> {
						fdi.setSpeed(speedText);
						fdi.setSize(finishSizeText + "/" + fileSizeText);
						fdi.setProgress(bg.doubleValue());
					});
				}, (info, fd) -> {
					if (info.isSuccess()) {
						FxUtil.invoke(() -> {
							fdi.setProgress(1.0d);
						});
					} else {
						FxUtil.invoke(() -> {
							fdi.setProgress(0.1d);
							showPrompt("下载失败！");
						});
					}
				});
			}
		});
	}
}
