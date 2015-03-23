package com.techology.entity;

import java.util.Set;

import org.springframework.stereotype.Component;

/**
 * 学生信息表
 * @author zhangjie
 *
 */
@Component
public class StudentInfo {
	private String sId;//学号
	private String sName;//学生姓名
	private String sSex;//性别
	private String sGrade;//年级
	private School sSchool;//学生院系
	private Major sMajor;//学生专业
	private String sPhone;//联系方式
	private String sIDCard;//学生身份证
	private String sIDBank;//学生银行卡号
	
	
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public String getsGrade() {
		return sGrade;
	}
	public void setsGrade(String sGrade) {
		this.sGrade = sGrade;
	}
	

	public School getsSchool() {
		return sSchool;
	}
	public void setsSchool(School sSchool) {
		this.sSchool = sSchool;
	}

	public Major getsMajor() {
		return sMajor;
	}
	public void setsMajor(Major sMajor) {
		this.sMajor = sMajor;
	}
	public String getsIDCard() {
		return sIDCard;
	}
	public void setsIDCard(String sIDCard) {
		this.sIDCard = sIDCard;
	}
	public String getsIDBank() {
		return sIDBank;
	}
	public void setsIDBank(String sIDBank) {
		this.sIDBank = sIDBank;
	}
	public String getsId() {
		return sId;
	}
	public void setsId(String sId) {
		this.sId = sId;
	}


	
	public StudentInfo() {
	}
	public StudentInfo(String sId, String sName, String sGrade, School sSchool,
			Major sMajor, String sIDCard, String sIDBank) {
		super();
		this.sId = sId;
		this.sName = sName;
		this.sGrade = sGrade;
		this.sSchool = sSchool;
		this.sMajor = sMajor;
		this.sIDCard = sIDCard;
		this.sIDBank = sIDBank;
	}
	public String getsPhone() {
		return sPhone;
	}
	public void setsPhone(String sPhone) {
		this.sPhone = sPhone;
	}
	@Override
	public String toString() {
		return "StudentInfo [sId=" + sId + ", sName=" + sName + ", sGrade="
				+ sGrade + ", sSchool=" + sSchool + ", sMajor=" + sMajor
				+ ", sPhone=" + sPhone + ", sIDCard=" + sIDCard + ", sIDBank="
				+ sIDBank + "]";
	}
	public String getsSex() {
		return sSex;
	}
	public void setsSex(String sSex) {
		this.sSex = sSex;
	}
	
	
	
	
}
