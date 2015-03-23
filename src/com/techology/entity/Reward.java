package com.techology.entity;

import org.springframework.stereotype.Component;


/**
 * 奖励信息表
 * @author jason
 *
 */
@Component
public class Reward {

	private int rId;//奖励ID
	private String rType;//获奖比赛类型  个人赛 or团体赛
	private String rLevel;//比赛水平  国家赛A类
	private String rGrade;//获奖等级  1、2、3等奖
	private String rTeacher;//老师奖励 格式：奖金：课时
	private String rStudent;//学生奖励 格式：奖金：学分
	private String rRemark;//备注
	public int getrId() {
		return rId;
	}
	public void setrId(int rId) {
		this.rId = rId;
	}
	public String getrType() {
		return rType;
	}
	public void setrType(String rType) {
		this.rType = rType;
	}
	public String getrLevel() {
		return rLevel;
	}
	public void setrLevel(String rLevel) {
		this.rLevel = rLevel;
	}
	public String getrGrade() {
		return rGrade;
	}
	public void setrGrade(String rGrade) {
		this.rGrade = rGrade;
	}
	public String getrTeacher() {
		return rTeacher;
	}
	public void setrTeacher(String rTeacher) {
		this.rTeacher = rTeacher;
	}
	public String getrStudent() {
		return rStudent;
	}
	public void setrStudent(String rStudent) {
		this.rStudent = rStudent;
	}
	public String getrRemark() {
		return rRemark;
	}
	public void setrRemark(String rRemark) {
		this.rRemark = rRemark;
	}
	@Override
	public String toString() {
		return "Reward [rId=" + rId + ", rType=" + rType + ", rLevel=" + rLevel
				+ ", rGrade=" + rGrade + ", rTeacher=" + rTeacher
				+ ", rStudent=" + rStudent + ", rRemark=" + rRemark + "]";
	}

	
}
