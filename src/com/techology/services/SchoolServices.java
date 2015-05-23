package com.techology.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techology.dao.SchoolDao;
import com.techology.entity.Role;
import com.techology.entity.School;
import com.techology.entity.User;

/**
 * 院系服务层
 * 
 * @author jason
 * 
 */
@Service
@Transactional
public class SchoolServices {

	@Resource
	private SchoolDao schoolDao;

	public School getByID(int id) {
		return schoolDao.getById(id);
	}

	public void save(School school) {
		schoolDao.save(school);

	}

	/**
	 * 获取所有的学院专业
	 * 
	 * @return
	 */
	public List<School> getAll() {
		return schoolDao.get();
	}

	/**
	 * 分页获取院系信息
	 * 
	 * @param currentPage
	 * @param count
	 * @return
	 */

	public List<School> getAllSchoolsByPage(int currentPage, int count) {

		return schoolDao.get((currentPage - 1) * count, count);
	}

	/**
	 * 获取总记录条数
	 * 
	 * @return
	 */
	public int getCount() {
		return schoolDao.getCount();
	}

	/**
	 * 更新记录
	 * 
	 * @param school
	 */
	public void update(School school) {
		schoolDao.update(school);
	}

	/**
	 * 删除院系
	 * 
	 * @param school
	 */
	public void delete(School school) {
		schoolDao.delete(schoolDao.getById(school.getsId()));
	}

	public School getSchoolByID(int getsId) {

		return schoolDao.getById(getsId);
	}

	public List<School> getBysName(String sName) {
		return schoolDao.get(new String[] { "sName" }, "sId", sName);
	}
}
