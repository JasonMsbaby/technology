package com.techology.entity;

import org.springframework.stereotype.Component;

/**
 * 教师基本信息表
 * @author jason
 *
 */
@Component
public class TeacherInfo {

	private String tId;//教工号
	private String tName;//教师姓名
	private String tSex;//性别
	private School tSchool;//所属学院
	private String tIdcard;//身份证号
	private String tBankNum;//银行卡号
	private String tPhone;//联系方式
	public String gettIdcard() {
		return tIdcard;
	}
	public void settIdcard(String tIdcard) {
		this.tIdcard = tIdcard;
	}
	public String gettBankNum() {
		return tBankNum;
	}
	public void settBankNum(String tBankNum) {
		this.tBankNum = tBankNum;
	}
	public String gettName() {
		return tName;
	}
	public void settName(String tName) {
		this.tName = tName;
	}

	public School gettSchool() {
		return tSchool;
	}
	public void settSchool(School tSchool) {
		this.tSchool = tSchool;
	}
	public String gettId() {
		return tId;
	}
	public void settId(String tId) {
		this.tId = tId;
	}
	public String gettPhone() {
		return tPhone;
	}
	public void settPhone(String tPhone) {
		this.tPhone = tPhone;
	}
	public String gettSex() {
		return tSex;
	}
	public void settSex(String tSex) {
		this.tSex = tSex;
	}
	
	
}
