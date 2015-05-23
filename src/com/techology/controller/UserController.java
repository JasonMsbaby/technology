package com.techology.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techology.base.BaseController;
import com.techology.common.Help;
import com.techology.entity.Role;
import com.techology.entity.School;
import com.techology.entity.User;
import com.techology.services.RoleServices;
import com.techology.services.SchoolServices;
import com.techology.services.UserServices;

/**
 * 注释
 * 
 * @author jason
 * 
 */
@Controller
public class UserController extends BaseController {

	// ****************变量定义区****************************************

	// ****************注入区****************************************
	@Resource
	private UserServices userServices;
	@Resource
	private RoleServices roleServices;
	@Resource
	private SchoolServices schoolServices;
	@Resource
	private HttpServletRequest request;

	// ****************页面初始化区****************************************
	/**
	 * 用户管理初始化 【李成鹏添加】
	 * 1、超级管理员可管理所有用户；2、教务处管理员可管理院级及院级以下用户；3、院级管理员可管理普通级别用用户；4、普通用户无权管理用户
	 * 
	 * @return
	 */
	@RequestMapping("userManger")
	public String userManger(String page) {
		int currentPage = page == null ? 1 : Integer.parseInt(page);
		User user = (User) request.getSession().getAttribute("currentUser");// 获取登陆用户信息
		setAttr("users", userServices.getAllUsersByPage(currentPage, 10, user));
				setAttr("counts", userServices.getCount(user.getuRole()));
		setAttr("currentPage", currentPage);
		return "System/UserManger/userManger";
	}

	/**
	 * 添加用户（准备数据并跳转页面） 加载角色信息，院系信息至前台过滤 【李成鹏添加】
	 * 
	 * @return
	 */
	@RequestMapping("userManger_add")
	public String userManger_add() {
		User user = (User) request.getSession().getAttribute("currentUser");
		setAttr("user", user);
		setAttr("roles", roleServices.getUserRols(user));
		setAttr("schools", schoolServices.getAll());
		return "System/UserManger/userManger_add";
	}

	/**
	 * 编辑用户（加载数据并跳转页面） 加载角色信息，院系信息至前台过滤 【李成鹏添加】
	 * 
	 * @return
	 */
	@RequestMapping("userManger_edit")
	public String userManger_edit() {
		setAttr("users", userServices.getUser(Integer.parseInt(getAttr("uId")
				.toString())));
		User user = (User) request.getSession().getAttribute("currentUser");
		setAttr("user", user);
		setAttr("roles", roleServices.getUserRols(user));
		setAttr("schools", schoolServices.getAll());
		return "System/UserManger/userManger_edit";
	}

	// ****************页面提交处理区****************************************

	/**
	 * 删除用户
	 * 
	 * @param response
	 * @param user
	 */
	@RequestMapping("userManger_delete")
	public void userManger_delete(HttpServletResponse response, User user) {
		try {
			userServices.delete(user);
			response.getWriter().print(
					Help.getScript("删除成功", "userManger.html"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加用户（提交处理） 【李成鹏添加】
	 * 
	 * @param response
	 * @param user
	 *            获取用户
	 * @param role
	 *            获取角色
	 * @param school
	 *            获取院系
	 */
	@RequestMapping("userManger_add_submit")
	public void userManger_add_submit(HttpServletResponse response, User user,
			Role role, School school) {
		try {
			user.setuRole(roleServices.getRoleByID(role.getrId()));// 获取角色实体
			user.setuSchool(schoolServices.getSchoolByID(school.getsId()));// 获取院系实体
			int i = userServices.getByuName(user.getuName()).size();
			if (i != 0) {
				response.getWriter().print(
						Help.getScript("用户已存在！", "userManger.html"));
			} else {
				userServices.save(user);
				response.getWriter().print(
						Help.getScript("提交成功", "userManger.html"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 编辑用户提交处理 【李成鹏添加】
	 * 
	 * @param response
	 * @param user
	 * @param role
	 * @param school
	 */
	@RequestMapping("userManger_edit_submit")
	public void userManger_edit_submit(HttpServletResponse response, User user,
			Role role) {
		int school=getAttr("sId").equals("")?0:Integer.parseInt(getAttr("sId").toString());
		user.setuRole(roleServices.getRoleByID(role.getrId()));// 获取角色实体
		user.setuSchool(schoolServices.getSchoolByID(school));// 获取院系实体
		userServices.update(user);
		try {
			response.getWriter().print(
					Help.getScript("修改成功", "userManger.html"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
