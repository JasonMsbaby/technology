package com.techology.common;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.techology.entity.Permission;
import com.techology.entity.Role;
import com.techology.entity.User;
import com.techology.services.PermissionServices;
import com.techology.services.RoleServices;
import com.techology.services.UserServices;

/**
 * 自定义拦截器 登录权限验证
 * 
 * @author jason
 * 
 */
public class MyHandlerInterceptorAdapter extends HandlerInterceptorAdapter {
	@Resource
	private PermissionServices permissionServices;

	/* 任何人在登陆情况下能够查看的页面 */
	/**
	 * 权限字符比较规则
	 * 
	 * @param url
	 * @param target
	 * @return
	 */
	private boolean compare(String url, String target) {
		if (target.contains(",")) {
			boolean flag = false;
			String targets[] = target.split(",");
			for (String str : targets) {
				if (str.equals(url)) {
					flag = true;
					break;
				}
			}
			return flag;
		} else {
			if (url.equals(target)) {
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		//系统首次运行初始化
		init();
		// 获取请求地址
		String uri = request.getRequestURI().toString().trim();
		// 解析请求action
		String action = uri.length() <= 12 ? "index" : uri.substring(12,
				uri.length() -5);
		System.err.println(action);
		// 判断用户访问的是否是登录页
		if (compare(action, "login,login_submit,getCode")) {
			return super.preHandle(request, response, handler);
		} else {
			// 判断用户是否登录
			if (request.getSession().getAttribute("currentUser") != null) {
				if (compare(action,
						"index,welcome,error,exit,permissionManger_init")) {// 判断是否请求的允许列表
					return super.preHandle(request, response, handler);
				} else {// 判断是否请求的允许列表
						// 判断用户是否有权限
					boolean flag = false;
					User user = (User) request.getSession().getAttribute(
							"currentUser");
					// 超级管理员拥有所有权限 【李成鹏修改】
					if (user.getuRole().getrLevel().equals(Help.XIAOJI)) {
						return super.preHandle(request, response, handler);
					} else {
						String p=user.getuRole().getrPermission();
						if(p!=null&&!p.equals("")){
							String[] permissions = p.split(",");
							for (String str : permissions) {
								Permission permission = permissionServices
										.getById(str);
								String allow = permission.getpAllow();
								if (compare(action, allow)) {
									flag = true;
									break;
								}
							}
						}
						
						if (flag) {// 拥有权限
							return super.preHandle(request, response, handler);
						} else {// 没有权限
							response.getWriter().print(Help.getScript("权限不足","welcome.html"));
							return false;
						}
					}
				}

			} else {
				response.sendRedirect("login.html");
				return false;
			}
		}
	}

	@Resource
	private RoleServices roleServices;
	@Resource
	private UserServices userServices;
	/**
	 * // * 初始化操作
	 */
	public void init() {
		// 如果模块列表为空就初始化列表，初始化超级管理员
		if (permissionServices.getAll().size() == 0) {
			permissionServices.init();
			Role role=new Role();
			role.setrLevel("校级");
			role.setrName("教务处管理员");
			roleServices.save(role);
			User user=new User();
			user.setuName("admin");
			user.setuRole(role);
			user.setuPwd("123");
			userServices.save(user);
		}
	}

}
