package com.techology.dao;

import java.util.List;

import com.techology.base.BaseDao;
import com.techology.entity.Records;

public interface RecordsDao extends BaseDao<Records> {

	public List<Records> getAllByIn(String in,String years);
		
	
	
}
