package com.techology.entity;

import org.springframework.stereotype.Component;
/**
 * 用户信息表
 * @author jason
 *
 */
@Component
public class User {
	
	private int uId;//用户ID
	private String uName;//用户名
	private String uPwd;//用户密码
	private Role uRole;//用户角色  数据库中是角色外键
	private School uSchool;//用户所属院系 数据库中是院系外键
	

	public int getuId() {
		return uId;
	}

	public void setuId(int uId) {
		this.uId = uId;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public String getuPwd() {
		return uPwd;
	}

	public void setuPwd(String uPwd) {
		this.uPwd = uPwd;
	}


	public Role getuRole() {
		return uRole;
	}

	public void setuRole(Role uRole) {
		this.uRole = uRole;
	}


	public School getuSchool() {
		return uSchool;
	}

	public void setuSchool(School uSchool) {
		this.uSchool = uSchool;
	}

	@Override
	public String toString() {
		return "User [uId=" + uId + ", uName=" + uName + ", uPwd=" + uPwd
				+ ", uRole=" + uRole + ", uSchool=" + uSchool + "]";
	}
	
}
