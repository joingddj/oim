package com.onlyxiahui.app.basic.storage.manager;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.onlyxiahui.app.basic.storage.bean.BaseEntity;
import com.onlyxiahui.app.basic.storage.dao.CommonDAO;
import com.onlyxiahui.common.data.common.data.Page;
import com.onlyxiahui.extend.query.hibernate.QueryWrapper;
import com.onlyxiahui.extend.query.hibernate.syntax.data.QueryHandleData;
import com.onlyxiahui.extend.query.hibernate.syntax.data.QueryHandleUtil;
import com.onlyxiahui.extend.query.hibernate.util.QueryUtil;
import com.onlyxiahui.extend.query.page.QueryPage;

/**
 * Description <br>
 * Date 2020-05-19 10:37:15<br>
 * 
 * @author XiaHui [onlovexiahui@qq.com]<br>
 * @since 1.0.0
 */

public class BaseManager<T extends BaseEntity> extends AbstractEntityType<T> {

	protected CommonDAO commonDAO;

	public CommonDAO getCommonDAO() {
		return commonDAO;
	}

	@Autowired
	public void setCommonDAO(CommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}

	public T getById(Serializable id) {
		return commonDAO.get(this.getEntityClass(), id);
	}

	public void add(T data) {
		commonDAO.save(data);
	}

	public void update(T data) {
		commonDAO.update(data);
	}

	public int updateSelective(T data) {
		return commonDAO.updateSelective(data);
	}

	public void addOrUpdate(T data) {
		commonDAO.saveOrUpdate(data);
	}

	public int deleteById(Serializable id) {
		return commonDAO.deleteById(this.getEntityClass(), id);
	}

	public T get(Object query) {
		if (null == query) {
			return null;
		} else {
			Class<T> entityClass = (Class<T>) getEntityClass();
			return commonDAO.get(entityClass, query);
		}
	}

	public T get(QueryWrapper queryWrapper) {
		if (null == queryWrapper ||
				null == queryWrapper.getParameterMap() ||
				queryWrapper.getParameterMap().isEmpty()) {
			return null;
		} else {
			Class<T> entityClass = (Class<T>) getEntityClass();
			return commonDAO.get(entityClass, queryWrapper);
		}
	}

	public List<T> list(Object query) {
		return commonDAO.list(this.getEntityClass(), query);
	}

	public int queryCount(Object query) {
		QueryHandleData queryHandleData = QueryHandleUtil.get(query);
		QueryWrapper queryWrapper = QueryUtil.getQueryWrapperType(query, queryHandleData.getOptions());
		QueryPage p = queryWrapper.setPage(1, 1);
		commonDAO.list(this.getEntityClass(), queryWrapper, queryHandleData);
		return p.getTotalCount();
	}

	public List<T> queryList(Page page, Object query) {
		if (null == page) {
			page = new Page();
		}
		QueryHandleData queryHandleData = QueryHandleUtil.get(query);
		QueryWrapper queryWrapper = QueryUtil.getQueryWrapperType(query, queryHandleData.getOptions());
		QueryPage p = queryWrapper.setPage(page.getNumber(), page.getSize());
		List<T> list = commonDAO.list(this.getEntityClass(), queryWrapper, queryHandleData);
		page.setTotalCount(p.getTotalCount());
		page.setTotalPage(p.getTotalPage());
		return list;
	}
}
