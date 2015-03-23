package com.techology.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techology.common.SaxModule;
import com.techology.dao.PermissionDao;
import com.techology.entity.Permission;

@Service
@Transactional
public class PermissionServices {
	@Resource
	private PermissionDao permissionDao;
	@Resource
	private SaxModule saxModule;
	/**
	 * 初始化模块数据库
	 */
	public void init(){
		saxModule.start();
	}
	/**
	 * 添加
	 * @param permission
	 */
	public void save(Permission permission){
		permissionDao.merge(permission);
	}
	/**
	 * 获取所有权限集合
	 * @return
	 */
	public List<Permission> getAll(){
		return permissionDao.get();
	}
	/**
	 * 通过权限ID获取权限
	 * @param id
	 * @return
	 */
	public Permission getById(String id){
		return permissionDao.get(new String[]{"pId"}, "pId", id).get(0);
	}
}
