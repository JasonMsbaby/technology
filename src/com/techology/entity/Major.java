package com.techology.entity;

import org.springframework.stereotype.Component;


/**
 * 专业表
 * @author zhangjie
 *
 */
@Component
public class Major {
	private int mId;//专业ID
	private String mName;//专业名称
	private School mSchool;//专业所属院系
	
	public int getmId() {
		return mId;
	}
	public void setmId(int mId) {
		this.mId = mId;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public School getmSchool() {
		return mSchool;
	}
	public void setmSchool(School mSchool) {
		this.mSchool = mSchool;
	}
	@Override
	public String toString() {
		return "Major [mId=" + mId + ", mName=" + mName + ", mSchool="
				+ mSchool.getsName() + "]";
	}
	
	
	
}
