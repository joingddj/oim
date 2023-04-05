
package com.oimchat.client.common.event;

import java.util.ArrayList;
import java.util.List;

/**
 * Description <br>
 * Date 2021-03-10 15:42:46<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class ChangeObservable<T> {

	List<ChangeAction<T>> list = new ArrayList<>();
}
