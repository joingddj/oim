package com.onlyxiahui.aware.basic.util;

import java.nio.charset.StandardCharsets;

import com.google.common.hash.Hashing;
import com.onlyxiahui.common.utils.base.net.AddressUtil;

/**
 * ID生成器<br>
 * Date 2020-04-17 09:54:54<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */
public class IdUtil {

	public static int nodeCount = 31;
	public static int nodeNumber = Hashing.consistentHash(Hashing.sha256().hashString(AddressUtil.getLocalIpV4(), StandardCharsets.UTF_8), nodeCount);
	public static SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker(nodeNumber, 0);

	/**
	 * 生成唯一ID
	 *
	 * @return
	 */
	public static Long generateId() {
		return snowflakeIdWorker.nextId();
	}
}
