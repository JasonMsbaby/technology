package com.techology.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techology.dao.TeacherInfoDao;
import com.techology.entity.TeacherInfo;

/**
 * 
 * @author Jason
 * 
 */
@Transactional
@Service
public class TeacherInfoServices {
	@Resource
	private TeacherInfoDao teacherInfoDao;

	/**
	 * 判断教师用户是否已经存在
	 * 
	 * @param t
	 * @return
	 */
	public boolean exit(TeacherInfo t) {

		List<TeacherInfo> teacherInfo = teacherInfoDao.get(new String[]{"tId"},"tId",t.gettId());
		if (teacherInfo.size()>0) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 更新教师用户
	 * 
	 * @param t
	 */
	public void update(TeacherInfo t) {
		teacherInfoDao.update(t);
	}

	/**
	 * 保存教师用户
	 * 
	 * @param t
	 */
	public void save(TeacherInfo t) {
		teacherInfoDao.save(t);
	}

}
