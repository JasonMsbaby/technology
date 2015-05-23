package com.techology.entity;

import java.util.List;

import org.springframework.stereotype.Component;

/**
 * 获奖记录
 * @author jason
 *
 */
@Component
public class Records {

	private int reId;//获奖记录ID
	private Competition reCompetition;//获奖比赛
	private String reGrade;//获奖等级 一二三等奖
	private String reJoinTime;//参加时间
	private String reWriteTime;//填写时间
	private String reWritePerson;//提交人
	private int reSchool;//该记录属于什么院系的成果
	private String reCheckPerson;//院级审核人
	private String reCheckPersonAdmin;//教务处审核人
	private int reGiveStatus;//资金发放状态
	private int reCheckStatus;//院级管理员审核状态 0-待审核，1-审核通过，-1-审核未通过
	private int reCheckStatusAdmin;//教务处审核状态 0-待审核，1-审核通过，-1-审核未通过
	private String recheckSuggestionAdmin;//教务处审核意见
	private String recheckSuggestion;//院级管理员审核意见
	private String reStudentMoneny;//学生奖金分配情况 格式：学号：奖金，学号：奖金
	private String reTeacherMoney;//教师奖金分配情况 格式：职工号：奖金，职工号：奖金
	private String reProjectName;//获奖作品名称
	private List<TeacherInfo> reTeacherInfo;//关联的指导教师信息
	private List<StudentInfo> reStudentInfo;//关联的参赛学生信息
	
	
	
	
	
	
	
	
	
	
	
	
	public int getReId() {
		return reId;
	}
	public void setReId(int reId) {
		this.reId = reId;
	}
	public Competition getReCompetition() {
		return reCompetition;
	}
	public void setReCompetition(Competition reCompetition) {
		this.reCompetition = reCompetition;
	}
	public String getReGrade() {
		return reGrade;
	}
	public void setReGrade(String reGrade) {
		this.reGrade = reGrade;
	}
	public String getReJoinTime() {
		return reJoinTime;
	}
	public void setReJoinTime(String reJoinTime) {
		this.reJoinTime = reJoinTime;
	}
	public String getReWriteTime() {
		return reWriteTime;
	}
	public void setReWriteTime(String reWriteTime) {
		this.reWriteTime = reWriteTime;
	}
	public String getReWritePerson() {
		return reWritePerson;
	}
	public void setReWritePerson(String reWritePerson) {
		this.reWritePerson = reWritePerson;
	}
	public String getReCheckPerson() {
		return reCheckPerson;
	}
	public void setReCheckPerson(String reCheckPerson) {
		this.reCheckPerson = reCheckPerson;
	}
	public int getReGiveStatus() {
		return reGiveStatus;
	}
	public void setReGiveStatus(int reGiveStatus) {
		this.reGiveStatus = reGiveStatus;
	}
	public int getReCheckStatus() {
		return reCheckStatus;
	}
	public void setReCheckStatus(int reCheckStatus) {
		this.reCheckStatus = reCheckStatus;
	}
	public List<StudentInfo> getReStudentInfo() {
		return reStudentInfo;
	}
	public void setReStudentInfo(List<StudentInfo> reStudentInfo) {
		this.reStudentInfo = reStudentInfo;
	}
	public int getReCheckStatusAdmin() {
		return reCheckStatusAdmin;
	}
	public void setReCheckStatusAdmin(int reCheckStatusAdmin) {
		this.reCheckStatusAdmin = reCheckStatusAdmin;
	}
	public String getRecheckSuggestionAdmin() {
		return recheckSuggestionAdmin;
	}
	public void setRecheckSuggestionAdmin(String recheckSuggestionAdmin) {
		this.recheckSuggestionAdmin = recheckSuggestionAdmin;
	}
	public String getRecheckSuggestion() {
		return recheckSuggestion;
	}
	public void setRecheckSuggestion(String recheckSuggestion) {
		this.recheckSuggestion = recheckSuggestion;
	}
	public String getReStudentMoneny() {
		return reStudentMoneny;
	}
	public void setReStudentMoneny(String reStudentMoneny) {
		this.reStudentMoneny = reStudentMoneny;
	}
	public String getReTeacherMoney() {
		return reTeacherMoney;
	}
	public void setReTeacherMoney(String reTeacherMoney) {
		this.reTeacherMoney = reTeacherMoney;
	}
	public List<TeacherInfo> getReTeacherInfo() {
		return reTeacherInfo;
	}
	public void setReTeacherInfo(List<TeacherInfo> reTeacherInfo) {
		this.reTeacherInfo = reTeacherInfo;
	}
	public String getReProjectName() {
		return reProjectName;
	}
	public void setReProjectName(String reProjectName) {
		this.reProjectName = reProjectName;
	}
	public String getReCheckPersonAdmin() {
		return reCheckPersonAdmin;
	}
	public void setReCheckPersonAdmin(String reCheckPersonAdmin) {
		this.reCheckPersonAdmin = reCheckPersonAdmin;
	}
	public int getReSchool() {
		return reSchool;
	}
	public void setReSchool(int reSchool) {
		this.reSchool = reSchool;
	}

	

	
	
	
	
}
