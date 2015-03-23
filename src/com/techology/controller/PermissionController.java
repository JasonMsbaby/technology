package com.techology.controller;



import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techology.base.BaseController;
import com.techology.common.Help;
import com.techology.services.PermissionServices;
/**
 * 注释
 * @author jason
 *权限管理控制器
 */
@Controller
public class PermissionController extends BaseController {

	//****************变量定义区****************************************
	
	//****************注入区****************************************
	@Resource
	private PermissionServices permissionServices;
	//****************页面初始化区****************************************
	/**
	 * 权限管理
	 * @return
	 */
	@RequestMapping("permissionManger")
	public String permissionManger(){
		return "System/PermissionManger/permissionManger";
	}
	
	
	//****************页面提交处理区****************************************
	/**
	 * 权限模块初始化
	 * @param response
	 * @return
	 */
	@RequestMapping("permissionManger_init")
	public void permissionManger_init(HttpServletResponse response){
		try {
			permissionServices.init();
			response.getWriter().print(Help.getScript("初始化成功","permissionManger.html"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
