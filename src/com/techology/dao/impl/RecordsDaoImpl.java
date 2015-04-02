package com.techology.dao.impl;



import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.techology.base.BaseDaoImpl;
import com.techology.dao.RecordsDao;
import com.techology.entity.Records;
@Repository
public class RecordsDaoImpl extends BaseDaoImpl<Records> implements RecordsDao {
	@Resource
	private SessionFactory sessionFactory;

	@Override
	public List<Records> getAllByIn(String in,String years) {
		Session session=sessionFactory.getCurrentSession();
		String sql="from Records as r where r.reCompetition in "+in+" and r.reJoinTime like '"+years+"%'";
		return session.createQuery(sql).list();
	}
}
