
package com.oimchat.client.platform.os.windows;

import java.io.File;
import java.io.IOException;

import com.oimchat.client.common.event.ValueAction;
import com.oimchat.client.platform.basic.constant.AppConstant;

/**
 * Description <br>
 * Date 2021-03-25 09:06:05<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class WindowsScreenShot {

	public void WindowsScreenShot() {

	}

	public void startProcess(ValueAction<File> f) {
		try {
			String classPath = "/general/block/screenshot/windows/";
			String file1 = "PrintScr.exe";
			String file2 = "PrintScr.exe";
			String home = AppConstant.getAppHomePath();
			// String cmd =
			// WindowsScreenShot.class.getResource("/general/block/screenshot/windows/PrintScr.exe").toExternalForm();
			String userdir = System.getProperty("user.dir");
			String cmd = userdir + "/file/wc/PrintScr.exe";
			Process process = Runtime.getRuntime().exec("cmd /c " + cmd);
			try {
				process.waitFor();
				System.out.println(111);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void startProcess() {
		try {
			String classPath = "/general/block/screenshot/windows/";
			String file1 = "PrintScr.exe";
			String file2 = "PrintScr.exe";
			String home = AppConstant.getAppHomePath();
			// String cmd =
			// WindowsScreenShot.class.getResource("/general/block/screenshot/windows/PrintScr.exe").toExternalForm();
			String userdir = System.getProperty("user.dir");
			String cmd = userdir + "/file/qq/Camera.dll,CameraSubArea";
			Process process = Runtime.getRuntime().exec("rundll32.exe " + cmd);
			try {
				process.waitFor();
				System.out.println(111);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] arg) {
		WindowsScreenShot w = new WindowsScreenShot();
//		w.startProcess((f) -> {
//
//		});
		w.startProcess();
	}
}
