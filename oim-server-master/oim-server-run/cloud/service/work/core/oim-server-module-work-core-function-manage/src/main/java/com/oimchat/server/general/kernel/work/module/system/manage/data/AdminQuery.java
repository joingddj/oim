package com.oimchat.server.general.kernel.work.module.system.manage.data;

import com.oimchat.server.general.kernel.work.module.core.base.data.query.UserQuery;
import com.onlyxiahui.extend.query.hibernate.syntax.annotation.OrderBy;

/**
 * date 2018-07-12 10:22:36<br>
 * description
 * 
 * @author XiaHui<br>
 * @since
 */
@OrderBy(value = { "updatedTimestamp" }, sort = "desc")
public class AdminQuery extends UserQuery {

}
