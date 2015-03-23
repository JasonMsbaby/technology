package com.techology.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techology.dao.MajorDao;
import com.techology.entity.Major;
import com.techology.entity.User;
/**
 * 专业服务层
 * @author Soyeon
 *
 */
@Service
@Transactional
public class MajorServices {
	@Resource
	private MajorDao majorDao;
	
	public List<Major> getAll(){
		return majorDao.get();
	}
	public void save(Major major){
		majorDao.save(major);
	}
	public List<Major> getBySID(int id){
		return majorDao.get(new String[]{"mSchool"}, "mId desc", id+"");
	}
	public int getCount(){
		return majorDao.getCount();
	}
	public void update(Major major){
		majorDao.update(major);
	}
	public void deleteById(int id){
		majorDao.delete(majorDao.getById(id));
	}
	public int getCountBySchool(String sId){
		return majorDao.getCount(new String[]{"mSchool"}, sId);
	}
}
