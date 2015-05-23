package com.techology.entity;

import org.springframework.stereotype.Component;

/**
 *角色
 * @author zhangjie
 *
 */
@Component
public class Role {
	
	private int rId;//角色ID
	private String rName;//角色名称
	private String rPermission;//角色权限
	private String rLevel;//角色级别  分为 校级 院级 教师
	public int getrId() {
		return rId;
	}
	public void setrId(int rId) {
		this.rId = rId;
	}
	public String getrName() {
		return rName;
	}
	public void setrName(String rName) {
		this.rName = rName;
	}
	public String getrPermission() {
		return rPermission;
	}
	public void setrPermission(String rPermission) {
		this.rPermission = rPermission;
	}
	public String getrLevel() {
		return rLevel;
	}
	public void setrLevel(String rLevel) {
		this.rLevel = rLevel;
	}
	
	
	
}
