package com.techology.dao;

import java.util.List;

import com.techology.base.BaseDao;
import com.techology.entity.Competition;
import com.techology.entity.Permission;

public interface CompetitionDao extends BaseDao<Competition> {

	List<Competition> getAllByLevelLike(String grade);

	List<Competition> getAllByLevelLike();
}
