package com.techology.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techology.common.Help;
import com.techology.dao.RoleDao;
import com.techology.entity.Role;
import com.techology.entity.User;
/**
 * 角色服务层
 * @author jason
 *
 */
@Service
@Transactional
public class RoleServices {

	@Resource
	private RoleDao roleDao;
	/**
	 * 根据ID获取角色
	 * @param id
	 * @return
	 */
	public Role getRoleByID(int id){
		return roleDao.getById(id);
	}

	/**
	 * 根据角色级别获取角色
	 * @param level
	 * @return
	 */
	public Role getRoleByLevel(String level){
		return roleDao.get(new String[]{"rLevel"}, "rId", level).get(0);
	}
	/**
	 * 分页获取所有角色
	 * @param page
	 * @param count
	 * @return
	 */
	public List<Role> getAllRolesByPage(int page,int count){
		return roleDao.get((page-1)*count, count);
	}
	/**
	 * 根据当前用户角色分页获取所有角色
	 * @param page
	 * @param count
	 * @return
	 */
	public List<Role> getAllRolesByPage(int page,int count,String level){
		if(level.equals(Help.XIAOJI)){
			return roleDao.get((page-1)*count, count);	
		}else if(level.equals(Help.YUANJI)){
			return roleDao.get((page-1)*count, count,new String[]{"rLevel"},"rId",Help.JIAOSHI);	
		}else{
			return null;
		}
		
	}

	/**
	 * 添加用户时获取用户角色列表  【李成鹏添加】
	 * 修改：张杰
	 * 通过当前登录的用户角色获取应该看到的角色类型
	 * @return
	 */
	public List<Role> getUserRols(User user) {
		String level=user.getuRole().getrLevel();
		if(level.equals(Help.XIAOJI)){//如果校级管理员
			return roleDao.get();
		}else if(level.equals(Help.YUANJI)){//如果为院级管理员
			return roleDao.get(new String[]{"rLevel"}, "rId",Help.JIAOSHI);
		}else {//如果为教师
			return null;
		}
	}
	
	/**
	 * 获取所有条数
	 * @return
	 */
	public int getCount(){
		return roleDao.getCount();
	}
	/**
	 * 根据用户角色获取所有条数
	 * @return
	 */
	public int getCount(String level){
		if(level.equals(Help.XIAOJI)){
			return roleDao.getCount();
		}else if(level.equals(Help.YUANJI)){
			return roleDao.getCount(new String[]{"rLevel"},Help.JIAOSHI);
		}else{
			return 0;
		}
		
	}

	/**
	 * 更新记录
	 * @param role
	 */
	public void update(Role role) {
		roleDao.update(role);
	}
	/**
	 * 添加实体
	 * @param role
	 */
	public void save(Role role) {
		roleDao.save(role);
		
	}
	/**
	 * 删除角色
	 * @param role
	 */
	public void delete(Role role) {
		roleDao.delete(role);
	}
	
}
