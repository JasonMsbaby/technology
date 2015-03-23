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
 * @author jason
 *
 */
@Controller
public class RoleController extends BaseController {
	//*************注入区**************************************************************************************
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
	 * 超级管理员可管理所有，教务处可管理院级及院级以下角色，院级以下没有角色管理权限。  【李成鹏修改】
	 * @return
	 */
	@RequestMapping("roleManger")
	public String roleMangerInint(String page){
		int currentPage=page==null?1:Integer.parseInt(page);
		User user=(User) request.getSession().getAttribute("currentUser");//获取登陆用户信息
		int rid=user.getuRole().getrLevel();
		if(rid==1||rid==2){//教务处获取角色列表
			setAttr("roles",roleServices.getAllRolesByPage(currentPage, 10));
			setAttr("counts", roleServices.getCount());
		}else{
			setAttr("roles",roleServices.getRolesByPage(currentPage, 10));
			setAttr("counts",roleServices.getRolesByPage(currentPage, 10).size());
		}
		setAttr("rid",rid);
		setAttr("currentPage", currentPage);
		return "System/RoleManger/roleManger";
	}
	/**
	 * 添加角色（初始化）
	 * @return
	 */
	@RequestMapping("roleManger_add")
	public String roleManger_add(){
		setAttr("permissions", permissionServices.getAll());
		return "System/RoleManger/roleManger_add";
	}
	/**
	 * 编辑角色（初始化）
	 * @return
	 */
	@RequestMapping("roleManger_edit")
	public String roleManger_edit(){
		setAttr("permissions", permissionServices.getAll());
		setAttr("role", roleServices.getRoleByID(Integer.parseInt(getAttr("id").toString())));
		return "System/RoleManger/roleManger_edit";
	}
	/**
	 * 添加角色提交处理
	 * @param response
	 */
	@RequestMapping("roleManger_add_submit")
	public void roleManger_add_submit(HttpServletResponse response,Role role){
		try {
			role.setrPermission(role.getrPermission());
			roleServices.save(role);
			response.getWriter().print(Help.getScript("提交成功", "roleManger.html"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 超级管理员可酌情添加教务处管理员  【李成鹏添加】
	 * @param response
	 */
	@RequestMapping("addOfficeManger")
	public void addOfficeManger(HttpServletResponse response){
		Role role=new Role();
		role.setrLevel(2);
		role.setrName("教务处管理员");
		//role.setrPermission("01,02,03,04,05,06,11,12,51,52,13,28,14,15,53,54,16,17,18,19,20,55,56,21,22,23,57,58,24,59,60,68,25,61,62,63,26,64,65,27,66,67,");
		roleServices.save(role);
		try {
			response.getWriter().print(Help.getScript("添加成功！", "roleManger.html"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 编辑角色提交处理
	 * @param response
	 */
	@RequestMapping("roleManger_edit_submit")
	public void roleManger_edit_submit(HttpServletResponse response,Role role){
		role.setrPermission("01,02,03,04,05,06,"+role.getrPermission());
		roleServices.update(role);
		try {
			response.getWriter().print(Help.getScript("修改成功", "roleManger.html"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 删除角色
	 * @param response
	 */
	@RequestMapping("roleManger_delete")
	public void roleManger_delete(HttpServletResponse response,Role role){
		try {
			/**
			 * 先删除此角色下的所有用户，再删除此角色，同级联删除 【李成鹏添加】
			 */
			List<User> users=userServices.getByRole(role);
			for(User user:users)
			{
				userServices.delete(user);
			}
			roleServices.delete(role);
			response.getWriter().print(Help.getScript("删除成功", "roleManger.html"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
