package com.techology.services;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
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
	 * 根据条件模糊查找比赛项目
	 * @param keyword
	 * @return
	 */
	public String getAllJson(String keyword){
		List<Competition> list=competitionDao.likeSearch(keyword);
		String json="[";
		for(Competition c:list){
			json+="{\"id\":\""+c.getcID()+"\",";
			if(c.getcName().contains("\"")){
				c.setcName(c.getcName().replace("\"", "\'"));
			}
			if(c.getcName().contains("”")){
				c.setcName(c.getcName().replace("“", "\'"));
			}
			json+="\"name\":\""+c.getcName()+"\"},";
		}
		json=json.substring(0, json.length()-1);
		json+="]";
		return json;
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
