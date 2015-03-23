package com.techology.entity;

import java.util.Set;

import org.springframework.stereotype.Component;
/**
 * 
 * @author zhangjie
 *院系表
 */
@Component
public class School {

	private int sId;//院系ID
	private String sName;//院系名称
	private Set<Major> sMajor;//院系拥有专业
	public int getsId() {
		return sId;
	}
	public void setsId(int sId) {
		this.sId = sId;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public Set<Major> getsMajor() {
		return sMajor;
	}
	public void setsMajor(Set<Major> sMajor) {
		this.sMajor = sMajor;
	}
	@Override
	public String toString() {
		return "School [sId=" + sId + ", sName=" + sName + ", sMajor=" + sMajor
				+ "]";
	}	
	
}
