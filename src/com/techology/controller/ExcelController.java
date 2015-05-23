package com.techology.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techology.base.BaseController;
import com.techology.common.ExcelExport;
import com.techology.common.Help;
import com.techology.common.RewardExcelUtil;
import com.techology.services.CompetitionServices;
import com.techology.services.ExcelServices;
import com.techology.services.RewardService;

/**
 * 导出报表
 * 
 * @author jason
 * 
 */
@Controller
public class ExcelController extends BaseController {

	// ****************变量定义区****************************************

	// ****************注入区****************************************
	@Resource
	private ExcelServices excelServices;
	@Resource
	private RewardService rewardService;
	@Resource
	private CompetitionServices competitionServices;

	// ****************页面初始化区****************************************
	/**
	 * 导出报表
	 */
	@RequestMapping("exportExcel")
	public String exportExcel() {
		setAttr("grade", Help.GRADE);
		return "Other/exportExcel";
	}

	/**
	 * 复杂表格html模板
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping("rewardModel") public String modelReward(){
	 * setAttr("reward", rewardService.getAll()); return "Other/rewardModel"; }
	 */

	// ****************页面提交处理区****************************************
	@RequestMapping("exportExcel1")
	public void exportExcel1(HttpServletResponse response) {
		ExcelExport excelUtil = new ExcelExport(
				excelServices.getCompetionTitle(),
				excelServices.getCompetionContent(),
				"德州学院大学生科技文化竞赛级别认定、承办单位汇总表", "德州学院大学生科技文化竞赛级别认定、承办单位汇总表",
				excelServices.getCompetionColumWidth());
		try {
			excelUtil.exportExcel(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@RequestMapping("exportExcel2")
	public void exportExcel2(HttpServletResponse response) {
		RewardExcelUtil excelUtil = new RewardExcelUtil(
				excelServices.getRewardColumNames(),
				excelServices.getRewardData(), "德州学院大学生科技文化竞赛类别等级、奖励规定一览表",
				"德州学院大学生科技文化竞赛类别等级、奖励规定一览表",
				excelServices.getRewardColumsWidth());
		try {
			excelUtil.exportExcel(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 待修改
	 * @param response
	 */
	@RequestMapping("exportExcel3")
	public void exportExcel3(HttpServletResponse response) {
		String years = getAttr("years").toString();
		String grade = getAttr("grade").toString();
		ArrayList<ArrayList<String>> listData = excelServices
				.getStudentRecordData(years, grade);
		if (listData != null) {
			ExcelExport excelUtil = new ExcelExport(
					excelServices.getStudentRecordColums(), listData,
					"德州学院学生获得大学生科技文化竞赛国家级奖励统计表", "德州学院" + years
							+ "年度学生获得大学生科技文化竞赛" + grade + "奖励统计表",
					excelServices.getStudentRecordColumsWidth());
			try {
				excelUtil.exportExcel(response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				response.getWriter().print(
						Help.getScript("暂无数据", "exportExcel.html"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@RequestMapping("exportExcel4")
	public void exportExcel4(HttpServletResponse response) {
		String years = getAttr("years").toString();
		ArrayList<ArrayList<String>> listData = excelServices
				.getTeacherRecordData(years);
		if (listData != null) {
			ExcelExport excelUtil = new ExcelExport(
					excelServices.getTeacherRecordColums(), listData, "德州学院"
							+ years + "年度教师指导学生获得大学生科技文化竞赛国家级和省级奖励统计表", "德州学院"
							+ years + "年度教师指导学生获得大学生科技文化竞赛国家级和省级奖励统计表",
					excelServices.getTeacherRecordColumsWidth());
			try {
				excelUtil.exportExcel(response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				response.getWriter().print(
						Help.getScript("暂无数据", "exportExcel.html"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
