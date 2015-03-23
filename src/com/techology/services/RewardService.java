package com.techology.services;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techology.dao.RewardDao;
import com.techology.entity.Reward;

/**
 * 奖励设置服务层
 * @author Andyxq
 *
 */
@Service
@Transactional
public class RewardService {

	@Resource
	private RewardDao rewardDao; 
	
	/**
	 * @param reward
	 * 保存项目奖励
	 * */
	public void save(Reward reward){
		rewardDao.save(reward);
	}
	
	/**
	 * @param reward
	 * 更新项目奖励
	 * */
	public void update(Reward reward){
		rewardDao.update(reward);
	}
	
	/**
	 * 获取所有项目奖励
	 * */
	public List<Reward> getAll(){
		return rewardDao.get();
	}
	
	/**
	 * @param reward
	 * 保存项目奖励
	 * */
	public void delete(Reward reward){
		rewardDao.delete(reward);
	}
	
	/**
	 * @param reward
	 * 保存项目奖励
	 * */
	public int count(){
		return rewardDao.getCount();
	}
	
	/**
	 * 根据ID获取项目奖励
	 * */
	public Reward getRewardById(Reward reward){
		
		return rewardDao.getById(reward.getrId());
	}
	
	/**
	 * 根据页码返回相关条数
	 * */	
	public List<Reward> getRewardByPage(int page,int length){
		return rewardDao.get(page, length);
	}
	
	/**
	 * 去重查询
	 * */
	public  List<String> getDistinct(String field, Map<String,String> conditions){
		
		return rewardDao.getDistinct(field, conditions);
	}
}
