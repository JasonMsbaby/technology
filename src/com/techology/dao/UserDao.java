package com.techology.dao;


import java.util.List;

import com.techology.base.BaseDao;
import com.techology.entity.User;

public interface UserDao extends BaseDao<User> {

	List<User> geterror();	
}
