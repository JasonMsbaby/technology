package com.techology.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techology.base.BaseController;
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
public class CompetitionController extends BaseController{
	
	@Resource
	private CompetitionServices competitionServices;	
	
	
	/**
	 * 项目管理
	 * */
	@RequestMapping("competitionManager")
	public String competitionManager(){
		
		int pagesize = 8;//每页显示条数 
		String p = (String) getAttr("page");
		int page = 1;
		page = ("".equals(p)||p==null)?1:(Integer.parseInt(p));//请求的页数
		int count = competitionServices.count();//总共条数
		List<Competition> competitionList = competitionServices.getcompetitionByPage((page-1)*pagesize, pagesize);
		
		setAttr("count", count);
		setAttr("page", page);
		setAttr("competitionList", competitionList);	
		return "Competition/CompetitionManager";
	}
	
	/**
	 * 添加项目
	 * */
	@RequestMapping("competitionManager_add")
	public String competitionManagerAdd(){
		
		return "Competition/CompetitionManager_add";
	}
	
	/**
	 * 接收表单-添加项目
	 * */
	@RequestMapping("competitionManager_add_submit")
	public String competitionManagerAddSubmit(Competition competition){
		
		if(competition.getcLevel() == null||competition.getcName()==null||competition.getcOrganization()==null||competition.getcType()==null){
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
	public String competitionManagerDelete(Competition competition){
		
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
	public String competitionManagerEdit(){
		
		String id = (String)getAttr("id");
		setAttr("competition", competitionServices.getCompetitionById(id));
		return "Competition/CompetitionManager_edit";
	}
	
	/**
	 * 编辑项目接收表单
	 * */
	@RequestMapping("competitionManager_edit_submit")
	public String competitionManagerEditSubmit(Competition competition){
		competitionServices.update(competition);
		setAttr("message", Help.getScript("提交成功！", "competitionManager.html"));
		return "Competition/MessageTemple";
	}

}
