package com.gxuwz.core.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.hql.ast.QueryTranslatorImpl;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.gxuwz.core.dao.BaseDao;
import com.gxuwz.core.pagination.Result;

/**
 * This class serves as the Base class for all other DAOs - namely to hold
 * common methods that they might all use. Can be used for standard CRUD(增删改查)
 * operations.
 * 
 * @version $ BaseDaoImpl.java 2015-7-13 10:50:44
 */
// DAO(Data Access Object) 数据访问对象是一个面向对象的数据库接口，它显露了 Microsoft Jet 数据库引擎（由
// Microsoft Access 所使用），并允许 Visual Basic 开发者通过 ODBC 像直接连接到其他数据库一样，直接连接到 Access
// 表。DAO 最适用于单系统应用程序或小范围本地分布使用。
@Repository("baseDao")
public class BaseDaoImpl<T> implements BaseDao<T> {

	HibernateTemplate hibernateTemplate;

	// HibernateTemplate是简单易用一种函数，功能是将Hibernate 的持久层访问模板化，创建HibernateTemplate
	// 实例后，注入一个SessionFactory 的引用，就可执行持久化操作。
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	
	public SessionFactory getSessionFactory(){
		return hibernateTemplate.getSessionFactory();
	}
	
	public Session getSession(){
		return getSessionFactory().getCurrentSession();
	}
	
	@Autowired
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	protected final Log logger = LogFactory.getLog(getClass());

	public T save(T entity) {
		try {
			getHibernateTemplate().saveOrUpdate(entity);
		} catch (RuntimeException e) {
			logger.error("save failed", e);
			throw e;
		}
		return entity;
	}

	public T merge(T entity) {
		getHibernateTemplate().merge(entity);
		return entity;
	}

	public void update(T entity) {
		getHibernateTemplate().update(entity);

	}

	public T get(Class<T> clazz, Serializable id) {
		return getHibernateTemplate().get(clazz, id);
	}

	public void remove(Class<T> clazz, Serializable id) {
		getHibernateTemplate().delete(get(clazz, id));
	}

	public void remove(T entity) {
		getHibernateTemplate().delete(entity);
	}

	public void evict(T entity) {
		getHibernateTemplate().evict(entity);

	}

	public List<T> getAll(final Class<T> clazz) {
		Object result = getHibernateTemplate().execute(
				new HibernateCallback<Object>() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						String hql = "from " + clazz.getName();
						Query query = session.createQuery(hql);
						return query.list();
					}
				});
		return (List) result;
	}

	public List<T> findByHql(String queryString, Object[] params) {
		return getHibernateTemplate().find(queryString, params);
	}

	public T findByHql(final String hql) {
		return (T) getHibernateTemplate().execute(new HibernateCallback<T>() {
			public T doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery(hql);

				return (T) query.uniqueResult();
			}
		});
	}

	public List<T> findByHql(final String queryString, final Object[] values,
			final int start, final int limit) {
		return (List) getHibernateTemplate().execute(
				new HibernateCallback<List>() {
					public List doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(queryString);
						query.setFirstResult(start).setMaxResults(limit);
						if (values != null) {
							for (int i = 0; i < values.length; i++) {
								query.setParameter(i, values[i]);
							}
						}
						return query.list();
					}
				});
	}

	public void flush() {
		getHibernateTemplate().flush();
	}

	public Long update(final String queryString, final Object[] values) {

		int c = getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback<Integer>() {
					public Integer doInHibernate(Session session)
							throws HibernateException {
						Query queryObject = session.createQuery(queryString);

						if (values != null) {
							for (int i = 0; i < values.length; i++) {
								queryObject.setParameter(i, values[i]);
							}
						}
						return queryObject.executeUpdate();
					}
				});
		return new Long(c);
	}

	public Result find(

	final String queryString, final Object[] values, final Type[] types,
			final int start, final int limit) {
		try {
			Result result = new Result(start, limit);

			if (start != -1 && limit != -1) {
				result.setTotal(getTotalItems(queryString, values).intValue());
			}

			HibernateTemplate ht = getHibernateTemplate();

			List data = ht.executeFind(new HibernateCallback<List>() {
				public List doInHibernate(Session session)
						throws HibernateException {
					Query queryObject = session.createQuery(queryString);

					setParameters(queryObject, values, types);

					if (start >= 0) {
						queryObject.setFirstResult(start);
					}

					if (limit >= 0) {
						// queryObject.setMaxResults(limit + 1);
						queryObject.setMaxResults(limit);
					}

					return queryObject.list();
				}
			});

			result.setData(data);

			if (start == -1 && limit == -1) {
				result.setTotal(data.size());
			}

			return result;
		} catch (RuntimeException e) {
			throw e;
		}
	}

	/**
	 * 
	 * @param queryString
	 * @param values
	 * @param types
	 * @return
	 * @throws DataAccessException
	 */
	protected int bulkUpdate(final String queryString, final Object[] values,
			final Type[] types) throws DataAccessException {

		Integer updateCount = (Integer) getHibernateTemplate().execute(
				new HibernateCallback<Integer>() {
					public Integer doInHibernate(Session session)
							throws HibernateException {
						Query queryObject = session.createQuery(queryString);
						setParameters(queryObject, values, types);
						return new Integer(queryObject.executeUpdate());
					}
				});
		return updateCount.intValue();
	}

	/**
	 * SQL
	 * 
	 * @param queryObject
	 * @param values
	 * @param types
	 */
	static void setParameters(Query queryObject, Object[] values, Type[] types) {
		if (values != null) {
			if (types != null) {
				for (int i = 0; i < values.length; i++) {
					queryObject.setParameter(i, values[i], types[i]);
				}
			} else {
				for (int i = 0; i < values.length; i++) {
					queryObject.setParameter(i, values[i]);
				}
			}
		}
	}

	/**
	 * 
	 * @param sql
	 * @param entityAlias
	 * @param entityClass
	 * @param values
	 * @param types
	 * @return
	 */
	protected List findBySQL(final String sql, final String entityAlias,
			final Class<T> entityClass, final Object[] values,
			final Type[] types) {
		return findBySQL(sql, entityAlias, entityClass, values, types, -1, -1)
				.getData();
	}

	protected Result findBySQL(final String sql, final String entityAlias,
			final Class<T> entityClass, final Object[] values,
			final Type[] types, final int start, final int limit) {
		return findBySQL(sql, entityAlias, entityClass, values, types, start,
				limit, true);
	}

	@SuppressWarnings("rawtypes")
	protected Result findBySQL(final String sql, final String entityAlias,
			final Class entityClass, final Object[] values, final Type[] types,
			final int start, final int limit, boolean readOnly) {

		HibernateTemplate ht = getHibernateTemplate();

		Result result = new Result(start, limit);

		if (start != -1 && limit != -1) {

			Number count = getTotalItems(sql, values);
			result.setTotal(count.intValue());
		}

		List data = ht.executeFind(new HibernateCallback<List>() {
			public List doInHibernate(Session session)
					throws HibernateException {
				SQLQuery query = session.createSQLQuery(sql);
				query.addEntity(entityAlias, entityClass);
				query.setParameters(values, types);

				if (start >= 0) {
					query.setFirstResult(start);
				}

				if (limit >= 0) {
					query.setMaxResults(limit + 1);
				}

				return query.list();
			}
		});

		result.setData(data);

		if (start == -1 && limit == -1) {
			result.setTotal(data.size());
		}

		return result;
	}

	/**
	 * 
	 * @param queryString
	 * @param values
	 * @return
	 */
	public Long getTotalItems(String queryString, final Object[] values) {
		int orderByIndex = queryString.toUpperCase().indexOf(" ORDER BY ");

		if (orderByIndex != -1) {
			queryString = queryString.substring(0, orderByIndex);
		}

		QueryTranslatorImpl queryTranslator = new QueryTranslatorImpl(
				queryString, queryString, Collections.EMPTY_MAP,
				(SessionFactoryImplementor) getHibernateTemplate()
						.getSessionFactory());
		queryTranslator.compile(Collections.EMPTY_MAP, false);
		final String sql = "select count(*) from ("
				+ queryTranslator.getSQLString() + ") tmp_count_t";

		Object reVal = getHibernateTemplate().execute(
				new HibernateCallback<Object>() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						SQLQuery query = session.createSQLQuery(sql);
						if (values != null) {
							for (int i = 0; i < values.length; i++) {
								query.setParameter(i, values[i]);
							}
						}
						return query.uniqueResult();
					}
				});
		return new Long(reVal.toString());
	}
}
