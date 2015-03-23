package com.techology.dao.impl;



import java.util.List;

import org.springframework.stereotype.Repository;

import com.techology.base.BaseDaoImpl;
import com.techology.dao.UserDao;
import com.techology.entity.User;
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@SuppressWarnings("unchecked")
	/**
	 * 查询非法用户
	 */
	public List<User> geterror() {
		return getSession().createQuery(//
				"FROM User WHERE uRole IS NULL OR uSchool IS NULL")//
				.list();
	}

	


}
