package com.techology.dao.impl;



import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.techology.base.BaseDaoImpl;
import com.techology.dao.CompetitionDao;
import com.techology.dao.PermissionDao;
import com.techology.entity.Competition;
import com.techology.entity.Permission;
@Repository
public class CompetitionDaoImpl extends BaseDaoImpl<Competition> implements CompetitionDao {

	@Resource
	private SessionFactory sessionFactory;
	@Override
	public List<Competition> getAllByLevelLike(String grade) {
		Session session=sessionFactory.getCurrentSession();
		return session.createQuery("from Competition as c where c.cLevel like '"+grade+"%'").list();
	}
	@Override
	public List<Competition> getAllByLevelLike() {
		Session session=sessionFactory.getCurrentSession();
		return session.createQuery("from Competition as c where c.cLevel like '国家级%' or cLevel like '省级%'").list();
	}
	
}
