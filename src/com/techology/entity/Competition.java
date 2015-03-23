package com.techology.entity;

import org.springframework.stereotype.Component;

/**
 * 赛事项目基本信息表
 * @author zhangjie
 */
@Component
public class Competition {

	private int cID;//比赛项目编号
	private String cName;//比赛名称
	private String cLevel;//比赛级别 校赛 or 国赛
	private String cOrganize;//比赛主办单位
	private String cOrganization;//承办单位
	private String cType;//比赛类型--个人赛 or 团体赛
	public int getcID() {
		return cID;
	}
	public void setcID(int cID) {
		this.cID = cID;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public String getcLevel() {
		return cLevel;
	}
	public void setcLevel(String cLevel) {
		this.cLevel = cLevel;
	}
	public String getcOrganize() {
		return cOrganize;
	}
	public void setcOrganize(String cOrganize) {
		this.cOrganize = cOrganize;
	}
	public String getcOrganization() {
		return cOrganization;
	}
	public void setcOrganization(String cOrganization) {
		this.cOrganization = cOrganization;
	}
	
	public String getcType() {
		return cType;
	}
	public void setcType(String cType) {
		this.cType = cType;
	}
	
	
}
