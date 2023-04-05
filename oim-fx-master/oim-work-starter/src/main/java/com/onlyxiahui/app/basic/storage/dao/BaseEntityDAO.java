package com.onlyxiahui.app.basic.storage.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.onlyxiahui.common.data.common.data.Page;
import com.onlyxiahui.extend.query.hibernate.AbstractEntityDAO;
import com.onlyxiahui.extend.query.hibernate.QueryContext;
import com.onlyxiahui.extend.query.hibernate.QueryWrapper;
import com.onlyxiahui.extend.query.hibernate.syntax.data.QueryHandleData;
import com.onlyxiahui.extend.query.hibernate.syntax.data.QueryHandleUtil;
import com.onlyxiahui.extend.query.hibernate.util.QueryUtil;
import com.onlyxiahui.extend.query.page.QueryPage;

/**
 * 
 * Date 2019-01-07 21:43:56<br>
 * Description
 * 
 * @author XiaHui<br>
 * @since 1.0.0
 */
public class BaseEntityDAO<T> extends AbstractEntityDAO<T> {

	@Autowired
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
		HibernateTemplate t = this.getHibernateTemplate();
		if (null != t) {
			t.setCheckWriteOperations(false);
		}
	}

	@Autowired
	@Override
	public void setQueryContext(QueryContext queryContext) {
		super.setQueryContext(queryContext);
	}

	public List<T> list(Object query, Page page) {
		QueryHandleData queryHandleData = QueryHandleUtil.get(query);
		QueryWrapper queryWrapper = QueryUtil.getQueryWrapperType(query, queryHandleData.getOptions());
		boolean hasPage = null != page;
		if (hasPage) {
			QueryPage queryPage = queryWrapper.setPage(page.getNumber(), page.getSize());
			List<T> list = list(queryWrapper, queryHandleData);
			page.setTotalCount(queryPage.getTotalCount());
			page.setTotalPage(queryPage.getTotalPage());
			return list;
		} else {
			List<T> list = list(queryWrapper, queryHandleData);
			return list;
		}
	}
}
