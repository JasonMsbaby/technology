package com.techology.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techology.dao.CompetitionDao;
import com.techology.entity.Competition;

@Service
@Transactional
public class CompetitionServices {
	@Resource
	private CompetitionDao competitionDao;
	/**
	 * 获取所有比赛项目
	 * @return
	 */
	public List<Competition> getAll(){
		return competitionDao.get();
	}
	
	/**
	 * 保存比赛项目
	 * @return
	 */
	public void save(Competition competition){
		competitionDao.save(competition);
		
	}
	
	/**
	 * 删除比赛项目
	 * @return
	 */
	public void delete(Competition competition){
		competitionDao.delete(competition);
	}
	
	/**
	 * 根据id找比赛项目
	 * @return
	 */
	public Competition getCompetitionById(String id){
		
		return competitionDao.getById(Integer.parseInt(id));
	}
	
	/**
	 * 分页显示比赛项目
	 * @return 
	 * @return
	 */
	public List<Competition> getcompetitionByPage(int page,int length){
		return competitionDao.get(page, length);
	}
	
	/**
	 * 查询总条数
	 * @return
	 */
	public int count(){
		return competitionDao.getCount();
	}
	
	/**
	 * 查询总条数
	 * @return
	 */
	public void update(Competition competition){
		competitionDao.update(competition);
	}
}
