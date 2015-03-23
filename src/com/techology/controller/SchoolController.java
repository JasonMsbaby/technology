package com.techology.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.techology.base.BaseController;
import com.techology.common.Help;
import com.techology.entity.Major;
import com.techology.entity.School;
import com.techology.entity.User;
import com.techology.services.MajorServices;
import com.techology.services.SchoolServices;
import com.techology.services.UserServices;

/**
 * 注释
 * 
 * @author jason
 * 
 */
@Controller
public class SchoolController extends BaseController {

	// ****************变量定义区****************************************

	// ****************注入区****************************************
	@Resource
	private SchoolServices schoolServices;
	@Resource
	private UserServices userServices;
	@Resource
	private MajorServices majorServices;

	// ****************页面初始化区****************************************
	/**
	 * 用户管理初始化 【李成鹏添加】
	 * 1、超级管理员可管理所有用户；2、教务处管理员可管理院级及院级以下用户；3、院级管理员可管理普通级别用用户；4、普通用户无权管理用户
	 * 
	 * @return
	 */
	@RequestMapping("schoolManger")
	public String schoolManger(String page) {
		int currentPage = page == null ? 1 : Integer.parseInt(page);
		setAttr("school", schoolServices.getAllSchoolsByPage(currentPage, 10));
		setAttr("counts", schoolServices.getCount());
		setAttr("major", majorServices.getAll());
		setAttr("currentPage", currentPage);
		return "School/schoolManger";
	}

	/**
	 * 添加院系（准备数据并跳转页面） 加载角色信息，院系信息至前台过滤 【李成鹏添加】
	 * 
	 * @return
	 */
	@RequestMapping("schoolManger_add")
	public String schoolManger_add() {
		return "School/schoolManger_add";
	}
	@RequestMapping("major_add")
	public String major_add(){
		int SID=Integer.parseInt(getAttr("sId").toString());
		School school=schoolServices.getByID(SID);
		setAttr("school",school);
		setAttr("sId", SID);
		return "School/majorManger_add";
	}
	/**
	 * 编辑院系（加载数据并跳转页面） 加载角色信息，院系信息至前台过滤 【李成鹏添加】
	 * 
	 * @return
	 */
	@RequestMapping("schoolManger_edit")
	public String schoolManger_edit() {
		int SID = Integer.parseInt(getAttr("sId").toString());
		School s = schoolServices.getByID(SID);
		setAttr("school", s);
		return "School/schoolManger_edit";
	}

	// ****************页面提交处理区****************************************

	/**
	 * 删除院系
	 * 
	 * @param response
	 * @param School
	 */
	@RequestMapping("schoolManger_delete")
	public void schoolManger_delete(HttpServletResponse response, School school) {
		try {
			List<User> users = userServices.getBySchool(school);
			for (User user : users) {
				userServices.delete(user);
			}
			List<Major> majors=majorServices.getBySID(school.getsId());
			for(Major maj:majors){
				majorServices.deleteById(maj.getmId());
			}
			schoolServices.delete(school);
			response.getWriter().print(
					Help.getScript("删除成功", "schoolManger.html"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("major_delete")
	public void major_delete(HttpServletResponse response) {
		int id=Integer.parseInt(getAttr("mId").toString());
		try {
			majorServices.deleteById(id);
			response.getWriter().print(1);
		} catch (IOException e) {
			try {
				response.getWriter().print(0);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	@RequestMapping("major_add_submit")
	public void major_add(HttpServletResponse response) {
		String major_name = getAttr("mName").toString();
		int id=Integer.parseInt(getAttr("sid").toString());
		Major major = new Major();
		major.setmName(major_name);
		major.setmSchool(schoolServices.getByID(id));
		majorServices.save(major);
		try {
			response.sendRedirect("major_add.html?sId=" + id);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加用户（提交处理） 【李成鹏添加】
	 * 
	 * @param response
	 * @param School
	 *            获取用户
	 * @param role
	 *            获取角色
	 * @param school
	 *            获取院系
	 */
	@RequestMapping("schoolManger_add_submit")
	public void SchoolManger_add_submit(HttpServletResponse response) {
		String sName=getAttr("sName").toString();
		School school=new School();
		List<School> slist=schoolServices.getBysName(sName);
		if(slist.size()>=1){
			try {
				response.getWriter().print(0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			try {
				school.setsName(sName);
				schoolServices.save(school);
				int sID=schoolServices.getBysName(sName).get(0).getsId();
				response.getWriter().print(sID);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 编辑专业提交处理
	 * 
	 * @param response
	 * @param school
	 * @param major
	 */
	@RequestMapping("schoolManger_major_submit")
	public void SchoolManger_edit_submit(HttpServletResponse response) {
		int id=Integer.parseInt(getAttr("id").toString());
		int sid=Integer.parseInt(getAttr("sid").toString());
		String name=getAttr("name").toString();
		Major m=new Major();
		m.setmId(id);
		m.setmName(name);
		m.setmSchool(schoolServices.getSchoolByID(sid));
		
		try {
			majorServices.update(m);
			response.getWriter().print(1);
		} catch (IOException e) {
			try {
				response.getWriter().print(0);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
