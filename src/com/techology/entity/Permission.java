package com.techology.entity;

import org.springframework.stereotype.Component;




/**
 * 权限
 * @author zhangjie
 *
 */
@Component
public class Permission {
	private String pId;//权限ID
	private String pContent;//权限内容
	private String pType;//权限类型
	private String pLink;//模块请求action连接
	private String pAllow;//权限允许请求的连接
	private int pShow;//是否在菜单栏中显示
	private int pOrder;//排序顺序
	private String pIcon;//图标样式

	public String getpContent() {
		return pContent;
	}
	public void setpContent(String pContent) {
		this.pContent = pContent;
	}
	public String getpType() {
		return pType;
	}
	public void setpType(String pType) {
		this.pType = pType;
	}
	public String getpLink() {
		return pLink;
	}
	public void setpLink(String pLink) {
		this.pLink = pLink;
	}
	public int getpShow() {
		return pShow;
	}
	public void setpShow(int pShow) {
		this.pShow = pShow;
	}
	public int getpOrder() {
		return pOrder;
	}
	public void setpOrder(int pOrder) {
		this.pOrder = pOrder;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	
	@Override
	public String toString() {
		return "Permission [pId=" + pId + ", pContent=" + pContent + ", pType="
				+ pType + ", pLink=" + pLink + ", pShow=" + pShow + ", pOrder="
				+ pOrder + "]";
	}
	public String getpAllow() {
		return pAllow;
	}
	public void setpAllow(String pAllow) {
		this.pAllow = pAllow;
	}
	public String getpIcon() {
		return pIcon;
	}
	public void setpIcon(String pIcon) {
		this.pIcon = pIcon;
	}
	

	
}
