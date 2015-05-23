package com.techology.base;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings("unchecked")
public abstract class BaseDaoImpl<T> implements BaseDao<T> {

	@Resource
	private SessionFactory sessionFactory;
	private Class<T> clazz;

	public BaseDaoImpl() {
		// 使用反射技术得到T的真实类型
		ParameterizedType pt = (ParameterizedType) this.getClass()
				.getGenericSuperclass(); // 获取当前new的对象的 泛型的父类 类型
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0]; // 获取第一个类型参数的真实类型
	}

	/**
	 * 获取当前可用的Session
	 * 
	 * @return
	 */
	protected Session getSession() {
		Session session = null;
		session = sessionFactory.getCurrentSession();
		return session;

	}

	public int getCount() {
		String hql = "SELECT COUNT(*) FROM " + clazz.getSimpleName();
		return Integer.parseInt(getSession().createQuery(hql).uniqueResult()
				.toString());
	}

	public int getCount(String[] colums, String... parmeters) {
		String hql = "SELECT COUNT(*) FROM " + clazz.getSimpleName()
				+ " where ";
		for (String str : colums) {
			hql += str + "=? and ";
		}
		hql = hql.substring(0, hql.length() - 4);
		Query query = getSession().createQuery(hql);
		for (int i = 0; i < parmeters.length; i++) {
			query.setString(i, parmeters[i]);
		}
		return Integer.parseInt(query.uniqueResult().toString());
	}

	public void save(T entity) {
		getSession().save(entity);
	}

	public void merge(T entity) {
		getSession().merge(entity);
	}

	public void update(T entity) {
		getSession().update(entity);
	}

	public void delete(T entity) {
		getSession().delete(entity);
	}

	public T getById(int id) {
		return (T) getSession().get(clazz, id);
	}

	/**
	 * 根据级别查询实体 【李成鹏添加】
	 */
	public List<T> getByLevel(int level) {
		return getSession().createQuery(//
				"FROM " + clazz.getSimpleName() + " WHERE rLevel = ?")//
				.setParameter(0, level)//
				.list();
	}

	public List<T> getByIds(Integer[] ids) {
		return getSession().createQuery(//
				"FROM " + clazz.getSimpleName() + "WHERE id IN (:ids)")//
				.setParameterList("ids", ids)//
				.list();
	}

	public List<T> get() {
		String hql = "FROM " + clazz.getSimpleName() + " ";
		Query query = getSession().createQuery(hql);
		return query.list();
	}

	public List<T> get(int pageIndex, int count) {
		String hql = "FROM " + clazz.getSimpleName() + " ";
		Query query = getSession().createQuery(hql).setFirstResult(pageIndex)
				.setMaxResults(count);
		return query.list();
	}

	public List<T> get(String[] colums, String orderColum, String... parmeters) {

		String hql = "FROM " + clazz.getSimpleName();
		hql += " WHERE ";
		for (String str : colums) {
			hql += str + "=? AND ";
		}
		hql = hql.substring(0, hql.length() - 4);
		if (orderColum != "") {
			hql += " ORDER BY " + orderColum;
		}
		Query query = getSession().createQuery(hql);
		for (int i = 0; i < parmeters.length; i++) {
			// System.out.println(parmeters[i]);
			query.setString(i, parmeters[i]);
		}

		List<T> list = query.list();
		return list;
	}

	public List<T> get(int pageIndex, int count, String[] colums,
			String orderColum, String... parmeters) {
		String hql = "FROM " + clazz.getSimpleName() + " where ";
		for (String str : colums) {
			hql += str + "=? AND ";
		}
		hql = hql.substring(0, hql.length() - 4);
		hql += " ORDER BY " + orderColum;
		Query query = getSession().createQuery(hql).setFirstResult(pageIndex)
				.setMaxResults(count);
		for (int i = 0; i < parmeters.length; i++) {
			query.setString(i, parmeters[i]);
		}
		return query.list();
	}




	/**
	 *  【去重查询】
	 *  @author Andyxq
	 * */
	public List<String> getDistinct(String field, Map<String,String> conditions){
		Query query;
		String hql = "SELECT DISTINCT e"+"." + field + " FROM "+ clazz.getSimpleName()+" e ";
		if(conditions!=null){
			hql += " WHERE ";
			for (Entry<String, String> entry:conditions.entrySet()) {
				hql += "e."+entry.getKey() +"=? AND ";
				hql = hql.substring(0, hql.length() - 4);	
			}
			query = getSession().createQuery(hql);
			int mun = 0;
			for (Entry<String, String> entry:conditions.entrySet()) {
				query.setString(mun, entry.getValue());
			}
		}else{
			query = getSession().createQuery(hql);
		}
		return query.list();
	}

	public List<T> query(String sql,String ...para) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery(sql);
		int i=0;
		for(String str:para){
			query.setString(i, str);
			i++;
		}
		return query.list();
	}
	
	

}
