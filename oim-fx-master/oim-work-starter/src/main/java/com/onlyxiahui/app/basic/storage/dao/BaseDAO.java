package com.onlyxiahui.app.basic.storage.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.onlyxiahui.extend.query.hibernate.QueryContext;
import com.onlyxiahui.extend.query.hibernate.AbstractDAO;

/**
 * 
 * Date 2019-01-07 21:41:49<br>
 * Description 通用的DAO
 * 
 * @author XiaHui<br>
 * @since 1.0.0
 */
public class BaseDAO extends AbstractDAO {

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
}