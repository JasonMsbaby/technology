package com.techology.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.techology.base.BaseController;
import com.techology.common.ExcelImport;
import com.techology.common.Help;
import com.techology.entity.Competition;
import com.techology.services.CompetitionServices;

/**
 * 项目管理控制层
 * 
 * @author Andyxq
 * 
 */
@Controller
public class CompetitionController extends BaseController {

	@Resource
	private CompetitionServices competitionServices;

	/**
	 * 项目管理
	 * */
	@RequestMapping("competitionManager")
	public String competitionManager() {

		int pagesize = 8;// 每页显示条数
		String p = (String) getAttr("page");
		int page = 1;
		page = ("".equals(p) || p == null) ? 1 : (Integer.parseInt(p));// 请求的页数
		int count = competitionServices.count();// 总共条数
		List<Competition> competitionList = competitionServices
				.getcompetitionByPage((page - 1) * pagesize, pagesize);

		setAttr("count", count);
		setAttr("page", page);
		setAttr("competitionList", competitionList);
		return "Competition/CompetitionManager";
	}

	/**
	 * 添加项目
	 * */
	@RequestMapping("competitionManager_add")
	public String competitionManagerAdd() {
		setAttr("grade", Help.GRADE);
		return "Competition/CompetitionManager_add";
	}

	/**
	 * 接收表单-添加项目
	 * */
	@RequestMapping("competitionManager_add_submit")
	public String competitionManagerAddSubmit(Competition competition) {

		if (competition.getcLevel() == null || competition.getcName() == null
				|| competition.getcOrganization() == null) {
			setAttr("info", Help.getAlert("信息不完整请重新输入！"));
			return "Competition/CompetitionManager_add";
		}

		competitionServices.save(competition);
		setAttr("info", Help.getAlert("保存成功！"));
		return "Competition/CompetitionManager_add";
	}

	/**
	 * 删除项目
	 * */
	@RequestMapping("competitionManager_delete")
	public String competitionManagerDelete(Competition competition) {

		try {
			competitionServices.delete(competition);
		} catch (Exception e) {
			setAttr("message", "error");
			return "Competition/MessageTemple";
		}

		setAttr("message", "success");
		return "Competition/MessageTemple";
	}

	/**
	 * 编辑项目
	 * */
	@RequestMapping("competitionManager_edit")
	public String competitionManagerEdit() {
		setAttr("grade", Help.GRADE);
		String id = (String) getAttr("id");
		setAttr("competition", competitionServices.getCompetitionById(id));
		return "Competition/CompetitionManager_edit";
	}

	/**
	 * 编辑项目接收表单
	 * */
	@RequestMapping("competitionManager_edit_submit")
	public String competitionManagerEditSubmit(Competition competition) {
		competitionServices.update(competition);
		setAttr("message", Help.getScript("提交成功！", "competitionManager.html"));
		return "Competition/MessageTemple";
	}

	/**
	 * 批量上传
	 */
	@RequestMapping("CompetionImportExcel")
	public void CompetionImportExcel(@RequestParam CommonsMultipartFile file,
			HttpServletResponse response) {
		InputStream inputStream = null;
		try {
			inputStream = file.getInputStream();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if (file.getOriginalFilename().endsWith("xls")) {
			ExcelImport import1 = new ExcelImport(inputStream, 2,
					file.getOriginalFilename());
			List<ArrayList<String>> data = import1.getData();
			for (ArrayList<String> array : data) {
				Competition c = new Competition();
				c.setcName(array.get(1).trim());
				c.setcOrganize(array.get(2).trim());
				c.setcLevel(array.get(3).trim());
				c.setcOrganization(array.get(4).trim());
				competitionServices.save(c);
				try {
					response.getWriter().print(
							Help.getScript("上传成功", "competitionManager.html"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			try {
				response.getWriter().print(
						Help.getScript("请上传xls文件", "competitionManager.html"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * 根据比赛名称模糊查询
	 * 
	 * @param keyword
	 * @param response
	 */
	@RequestMapping("Competition_likeSearch")
	public void Competition_likeSearch(String keyword,
			HttpServletResponse response) {
		keyword=keyword==null?"":keyword;
		String json = competitionServices.getAllJson(keyword);
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
