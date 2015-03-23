package com.techology.services;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techology.dao.StudentInfolDao;
import com.techology.entity.StudentInfo;

@Service
@Transactional
public class StudentInfoServices {

	@Resource
	private StudentInfolDao studentInfolDao;
	
	public void save(StudentInfo st){
		studentInfolDao.save(st);
	}

	public boolean exit(StudentInfo s) {
		int size=studentInfolDao.get(new String[]{"sId"}, "sId", s.getsId()).size();
		if(size>0){
			return true;
		}else{
			return false;
		}
		
	}

	public void update(StudentInfo s) {
		studentInfolDao.update(s);
		
	}
}
