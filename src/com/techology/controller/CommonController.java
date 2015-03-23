package com.techology.controller;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techology.base.BaseController;
import com.techology.services.PermissionServices;
/**
 * 系统模块设置的控制层
 * @author jason
 *
 */
@Controller
public class CommonController extends BaseController {
	@Resource
	private PermissionServices permissionServices;
	
	
	/**
	 * 初始化模块数据
	 * @return
	 */
	@RequestMapping("moduleSetting")
	public String init(){
		setAttr("status", "尚未初始化");
		return "Other/moduleSetting";
	}
	
	@RequestMapping("moduleSettingInit")
	public String start(){
		permissionServices.init();
		setAttr("status", "<label style='color:red'>初始化成功</label>");
		return "Other/moduleSetting";
	}
	
	@RequestMapping("error")
	public String notFound(){
		return "Other/404";
	}
	
	
	
}
