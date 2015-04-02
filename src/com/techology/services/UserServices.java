package com.techology.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techology.dao.RoleDao;
import com.techology.dao.SchoolDao;
import com.techology.dao.UserDao;
import com.techology.entity.Role;
import com.techology.entity.School;
import com.techology.entity.User;
/**
 * 用户服务层
 * @author jason
 *
 */
@Service
@Transactional
public class UserServices {

	@Resource
	private UserDao userDao;

	@Resource
	private RoleDao roleDao;
	@Resource
	private SchoolDao schoolDao;
	
	/**
	 * 保存用户
	 * @param user
	 */
	public void save(User user){
		userDao.save(user);
	}
	/**
	 * 修改用户
	 * @param user
	 */
	public void update(User user){
		userDao.update(user);
	}
	/**
	 * 根据ID获取用户
	 * @param id
	 * @return
	 */
	public User getUser(int id){
		return userDao.getById(id);
	}
	/**
	 * 获取所有用户
	 * @return
	 */
	public List<User> getAll(){
		return userDao.get();
	}
	/**
	 * 根据用户角色获取用户
	 * @param role
	 * @return
	 */
	public User getByRle(Role role) {
		role=roleDao.get(new String[]{"rName"}, "rId desc", role.getrName()).get(0);
		return userDao.get(new String[]{"uRole"}, "uId desc", String.valueOf(role.getrId())).get(0);
		
	}
	/**
	 * 根据用户名获取用户
	 * 【lcp】
	 * @param uName
	 * @return
	 */
	public List<User> getByuName(String uName){
		return userDao.get(new String[]{"uName"},"uId desc",uName);
	}
	/**
	 * 根据用户角色获取用户返回List
	 * 【李成鹏添加】
	 * @param role
	 * @return
	 */
	public List<User> getByRole(Role role){
		return userDao.get(new String[]{"uRole"}, "uId desc", String.valueOf(role.getrId()));
	}
	/**
	 * 根据用户院系和用户级别获取用户
	 * 分页查询普通级别用户，并筛选同登陆的上级用户院系的用户。 【李成鹏添加】
	 * @param school
	 * @return
	 */
	public List<User> getBySc(int currentPage, int count, School school) {
		school=schoolDao.get(new String[]{"sName"}, "sId desc", school.getsName()).get(0);
		List<User> users=new ArrayList<User>();
		List<Role> roles=roleDao.getByLevel(4);
		for(Role i:roles)
		{
			users.addAll(userDao.get((currentPage-1)*count, count,new String[]{"uSchool","uRole"}, "uId desc", String.valueOf(school.getsId()),String.valueOf(i.getrId())));
		}
		return users;
	}
	/**
	 * 根据用户名和密码判断用户是否存在
	 * 存在返回用户实体
	 * 不存在返回NULL
	 * @param user
	 * @return
	 */
	public User exit(User user){
		List<User> users=userDao.get(new String[]{"uName","uPwd"}, "uId", user.getuName(),user.getuPwd());
		if(users.size()<=0){
			return null;
		}else{
			return users.get(0);
		}
	}
	/**
	 * 分页获取所有用户
	 * @param currentPage
	 * @param count
	 * @return
	 */
	public List<User> getAllUsersByPage(int currentPage, int count) {
		return userDao.get((currentPage-1)*count, count);
	}
	
	
	/**
	 * 获取总记录条数
	 * @return
	 */
	public int getCount() {
		return userDao.getCount();
	}
	/**
	 * 获取指定院系的记录数
	 * @param school
	 * @return
	 */
	public int getCountBySc(School school) {
		school=schoolDao.get(new String[]{"sName"}, "sId desc", school.getsName()).get(0);
		
		List<User> users=new ArrayList<User>();
		List<Role> roles=roleDao.getByLevel(4);
		for(Role i:roles)
		{
			users.addAll(userDao.get(new String[]{"uSchool","uRole"}, "uId desc", String.valueOf(school.getsId()),String.valueOf(i.getrId())));
		}
		return users.size();
	}
	public void delete(User user) {
		userDao.delete(user);
	}
	/**
	 * 教务处获取用户数据
	 * @param page
	 * @param count
	 * @return
	 */
	public List<User> officeget(int page, int count) {
		return userDao.officeGet((page-1)*count, count,new String[]{"uRole"}, "uId", "0");
	}
	/**
	 * 获取非法用户
	 * @return
	 */
	public List<User> getErroruser() {
		return userDao.geterror();
	}
	/**
	 * 根据用户院系获取用户(级联删除用)
	 * @param school
	 * @return
	 */
	public List<User> getBySchool(School school) {
		return userDao.get(new String[]{"uSchool"}, "uId desc", String.valueOf(school.getsId()));
	}
	
	
	
}
