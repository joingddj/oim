package com.oimchat.client.basic.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.onlyxiahui.common.utils.base.lang.string.StringUtil;


/**
 * 
 * 
 * <br>
 * Date 2019-09-10 17:03:36<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */
public class FileNameUtil {
	/**
	 * The Unix separator character.
	 */
	private static final char UNIX_SEPARATOR = '/';

	/**
	 * The Windows separator character.
	 */
	private static final char WINDOWS_SEPARATOR = '\\';

	public static String createSaveName(String name) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
		String date = format.format(new Date());
		StringBuilder saveName = new StringBuilder();// 拼接文件名称
		saveName.append(date);
		int length = 150;
		if (StringUtil.isNotBlank(name)) {
			saveName.append("_");
			if (name.length() > length) {
				return name.substring(0, length);
			} else {
				saveName.append(name);
			}
		}
		return saveName.toString();
	}

	/**
	 * 获取根目录
	 * 
	 * @param rootPath
	 * @return
	 */
	public static StringBuilder getRootPath(String rootPath) {
		StringBuilder root = new StringBuilder();
		return root.append((StringUtil.isNotBlank(rootPath)) ? rootPath : "");
	}

	/**
	 * 获取文件名，去掉后缀名
	 * 
	 * @param name
	 * @return
	 */
	public static String getSimpleName(String fullName) {
		if (StringUtil.isBlank(fullName)) {
			return null;
		} else {
			int index = fullName.lastIndexOf(".");
			if (index != -1) {
				return fullName.substring(0, index);
			}
		}
		return fullName;
	}

	/**
	 * 获取文件后缀名
	 * 
	 * @param name
	 * @return
	 */
	public static String getSuffixName(String name) {
		if (StringUtil.isNotBlank(name)) {
			int length = name.length();
			int index = name.lastIndexOf(".");
			int cutIndex = (index + 1);
			if (index != -1) {
				if (cutIndex < length) {
					name = name.substring(cutIndex, length);
				}
			} else {
				return null;
			}
		}
		return name;
	}

	/**
	 * Gets the name minus the path from a full filename.
	 * <p>
	 * This method will handle a file in either Unix or Windows format. The text
	 * after the last forward or backslash is returned.
	 * 
	 * <pre>
	 * a/b/c.txt --> c.txt
	 * a.txt     --> a.txt
	 * a/b/c     --> c
	 * a/b/c/    --> ""
	 * </pre>
	 * <p>
	 * The output will be the same irrespective of the machine that the code is
	 * running on.
	 *
	 * @param filename the filename to query, null returns null
	 * @return the name of the file without the path, or an empty string if none
	 *         exists
	 */
	public static String getName(String filename) {
		if (filename == null) {
			return null;
		}
		int index = indexOfLastSeparator(filename);
		return filename.substring(index + 1);
	}

	/**
	 * Returns the index of the last directory separator character.
	 * <p>
	 * This method will handle a file in either Unix or Windows format. The position
	 * of the last forward or backslash is returned.
	 * <p>
	 * The output will be the same irrespective of the machine that the code is
	 * running on.
	 * 
	 * @param filename the filename to find the last path separator in, null returns
	 *                 -1
	 * @return the index of the last separator character, or -1 if there is no such
	 *         character
	 */
	public static int indexOfLastSeparator(String filename) {
		if (filename == null) {
			return -1;
		}
		int lastUnixPos = filename.lastIndexOf(UNIX_SEPARATOR);
		int lastWindowsPos = filename.lastIndexOf(WINDOWS_SEPARATOR);
		return Math.max(lastUnixPos, lastWindowsPos);
	}
}
