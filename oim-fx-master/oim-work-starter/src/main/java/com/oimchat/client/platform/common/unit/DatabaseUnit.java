package com.oimchat.client.platform.common.unit;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.alibaba.druid.pool.DruidDataSource;
import com.oimchat.client.common.event.GetBasicAction;
import com.onlyxiahui.app.context.AbstractMaterial;
import com.onlyxiahui.app.context.AppContext;
import com.onlyxiahui.extend.query.hibernate.QueryContext;
import com.onlyxiahui.extend.query.hibernate.handler.xml.QueryItemException;

/**
 * @author XiaHui
 * @date 2017年9月20日 下午5:49:06
 */
public class DatabaseUnit extends AbstractMaterial {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	QueryContext queryContext;
	FactoryBean<SessionFactory> factoryBean;
	TransactionTemplate transactionTemplate;

	public DatabaseUnit(AppContext appContext) {
		super(appContext);
	}

	public void initialize(
			String url,
			String username,
			String password,
			String[] entityPackages,
			String[] daoPaths) {
		DataSource dataSource = dataSource(url, username, password);
		queryContext = queryContext(daoPaths);
		factoryBean = sessionFactory(dataSource, entityPackages);

		// Transaction tx = dm.getSessionFactory().openSession().beginTransaction();
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(getSessionFactory());
		transactionManager.afterPropertiesSet();
		transactionTemplate = new TransactionTemplate(transactionManager);
	}

	private DataSource dataSource(String url, String username, String password) {

//		String url = "jdbc:h2:file:~/.oim/db/oim;AUTO_SERVER=TRUE";
//		String username = "root";
//		String password = "123456";

		String driverClassName = "org.h2.Driver";

		String filters = "stat";
		// 最大连接池数量
		Integer maxActive = 10;
		// 初始化连接池数量
		Integer initialSize = 1;
		// 最小连接池数量
		Integer minIdle = 1;
		// 获取连接时最大等待时间,单位毫秒
		Long maxWait = 60000L;
		Long timeBetweenEvictionRunsMillis = 60000L;
		Long minEvictableIdleTimeMillis = 300000L;
		String validationQuery = "SELECT 'x'";
		// 申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis,执行validationQuery检测连接是否有效
		Boolean testWhileIdle = true;
		// 申请连接时执行validationQuery检测连接是否有效
		Boolean testOnBorrow = true;
		// 归还连接时执行validationQuery检测连接是否有效
		Boolean testOnReturn = true;
		// Integer maxOpenPreparedStatements = 20;
		// 对于长时间不使用的连接强制关闭
		// Boolean removeAbandoned = true;
		// 超过30分钟开始关闭空闲连接
		// Long removeAbandonedTimeout = 1800L;
		// 将当前关闭动作记录到日志
		Boolean logAbandoned = true;

		DruidDataSource database = new DruidDataSource();
		database.setDriverClassName(driverClassName);
		database.setUrl(url);
		database.setUsername(username);
		database.setPassword(password);
		try {
			database.setFilters(filters);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		database.setMaxActive(maxActive);
		database.setInitialSize(initialSize);
		database.setMaxWait(maxWait);
		database.setMinIdle(minIdle);
		database.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		database.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		database.setValidationQuery(validationQuery);
		database.setTestWhileIdle(testWhileIdle);
		database.setTestOnBorrow(testOnBorrow);
		database.setTestOnReturn(testOnReturn);
		database.setPoolPreparedStatements(false);
		// database.setRemoveAbandoned(removeAbandoned",
		// Boolean.class));
		// database.setRemoveAbandonedTimeout(removeAbandonedTimeout",
		// Integer.class));
		database.setLogAbandoned(logAbandoned);
		return database;
	}

	private QueryContext queryContext(String[] paths) {
		QueryContext bean = new QueryContext();

		// String[] paths = { "classpath*:dao/query/*/*.*", "classpath*:dao/**/*.*" };
		bean.setConfigLocations(paths);
		// bean.setConfigLocation("file:config/query/*.xml");
		try {
			bean.load();
		} catch (QueryItemException e) {
			e.printStackTrace();
		}
		return bean;
	}

	/**
	 * 持久层会话工厂类
	 *
	 * @return
	 */

	private FactoryBean<SessionFactory> sessionFactory(DataSource dataSource, String[] entityPackages) {
		LocalSessionFactoryBean bean = new LocalSessionFactoryBean();
		bean.setDataSource(dataSource);

		if (null != entityPackages && entityPackages.length > 0) {
			bean.setPackagesToScan(entityPackages);
		} else {
			bean.setPackagesToScan(new String[] {});
		}

		Properties properties = new Properties();

		Map<String, String> map = new HashMap<>(256);

		map.put("dialect", "org.hibernate.dialect.H2Dialect");
		map.put("show_sql", "false");
		map.put("hbm2ddl.auto", "update");
		map.put("query.substitutions", "true 1, false 0");
		map.put("jdbc.fetch_size", "50");
		map.put("jdbc.batch_size", "50");
		map.put("transaction.coordinator_class", "jdbc");
		map.put("allow_update_outside_transaction", "true");

		map.put("cache.use_query_cache", "true");
		map.put("cache.use_second_level_cache", "true");
		map.put("cache.region.factory_class", "org.hibernate.cache.jcache.JCacheRegionFactory");

		map.put("javax.cache.provider", "org.ehcache.jsr107.EhcacheCachingProvider");
		map.put("javax.cache.missing_cache_strategy", "create");

		for (Map.Entry<String, String> e : map.entrySet()) {
			properties.setProperty("hibernate." + e.getKey(), e.getValue());
		}

		bean.setHibernateProperties(properties);

		try {
			bean.afterPropertiesSet();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
	}

	public QueryContext getQueryContext() {
		return queryContext;
	}

	public FactoryBean<SessionFactory> getFactoryBean() {
		return factoryBean;
	}

	public SessionFactory getSessionFactory() {
		try {
			return factoryBean.getObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public <T> T transaction(GetBasicAction<T> a) {
		try {
			return transactionTemplate.execute(new TransactionCallback<T>() {

				@Override
				public T doInTransaction(TransactionStatus status) {
					return a.get();
				}
			});
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
