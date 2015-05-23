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
import com.techology.entity.User;
import com.techology.services.PermissionServices;
import com.techology.services.RoleServices;
import com.techology.services.UserServices;

/**
 * 角色管理控制器
 * 
 * @author jason
 * 
 */
@Controller
public class RoleController extends BaseController {
	// *************注入区**************************************************************************************
	@Resource
	private PermissionServices permissionServices;
	@Resource
	private RoleServices roleServices;
	@Resource
	private UserServices userServices;
	@Resource
	private HttpServletRequest request;

	/**
	 * 角色管理
	 * 
	 * @return
	 */
	@RequestMapping("roleManger")
	public String roleMangerInint(String page) {
		int currentPage = page == null ? 1 : Integer.parseInt(page);
		User user = (User) request.getSession().getAttribute("currentUser");// 获取登陆用户信息
		String roleLevel = user.getuRole().getrLevel();
		setAttr("roles",
				roleServices.getAllRolesByPage(currentPage, 10, roleLevel));
		setAttr("counts", roleServices.getCount(roleLevel));
		setAttr("currentPage", currentPage);
		return "System/RoleManger/roleManger";
	}

	/**
	 * 添加角色（初始化）
	 * 
	 * @return
	 */
	@RequestMapping("roleManger_add")
	public String roleManger_add() {
		setAttr("permissions", permissionServices.getAll());
		return "System/RoleManger/roleManger_add";
	}

	/**
	 * 编辑角色（初始化）
	 * 
	 * @return
	 */
	@RequestMapping("roleManger_edit")
	public String roleManger_edit() {
		setAttr("permissions", permissionServices.getAll());
		Role role = roleServices.getRoleByID(Integer.parseInt(getAttr("id")
				.toString()));
		setAttr("role", role);
		return "System/RoleManger/roleManger_edit";
	}

	/**
	 * 添加角色提交处理
	 * 
	 * @param response
	 */
	@RequestMapping("roleManger_add_submit")
	public void roleManger_add_submit(HttpServletResponse response, Role role) {
		try {
			User user = (User) request.getSession().getAttribute("currentUser");
			if (user.getuRole().getrLevel().equals(Help.XIAOJI)) {
				roleServices.save(role);
			} else {
				if (role.getrLevel().equals(Help.JIAOSHI)) {
					roleServices.save(role);
				} else {
					response.getWriter().print(
							Help.getScript("您只有教师级别添加的权限",
									"roleManger_add.html"));
				}
			}
			response.getWriter().print(
					Help.getScript("提交成功", "roleManger.html"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 编辑角色提交处理
	 * 
	 * @param response
	 */
	@RequestMapping("roleManger_edit_submit")
	public void roleManger_edit_submit(HttpServletResponse response, Role role) {
		try {
			User user = (User) request.getSession().getAttribute("currentUser");
			if (user.getuRole().getrLevel().equals(Help.XIAOJI)) {
				roleServices.update(role);
			} else {
				if (role.getrLevel().equals(Help.JIAOSHI)) {
					roleServices.update(role);
				} else {
					response.getWriter().print(
							Help.getScript("您只有更改为教师的权限的权限",
									"roleManger_edit.html"));
				}
			}

			response.getWriter().print(
					Help.getScript("修改成功", "roleManger.html"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除角色
	 * 
	 * @param response
	 */
	@RequestMapping("roleManger_delete")
	public void roleManger_delete(HttpServletResponse response, Role role) {
		try {
			roleServices.delete(role);
			response.getWriter().print(
					Help.getScript("删除成功", "roleManger.html"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
