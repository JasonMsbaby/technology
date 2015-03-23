package com.techology.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.techology.base.BaseController;
import com.techology.common.Help;
import com.techology.entity.Major;
import com.techology.entity.Records;
import com.techology.entity.School;
import com.techology.entity.StudentInfo;
import com.techology.entity.TeacherInfo;
import com.techology.entity.User;
import com.techology.services.CompetitionServices;
import com.techology.services.RecordsServices;
import com.techology.services.SchoolServices;
import com.techology.services.StudentInfoServices;
import com.techology.services.TeacherInfoServices;

/**
 * 注释
 * 
 * @author jason 成果管理控制器
 */
@Controller
public class RecordsController extends BaseController {

	// ****************变量定义区****************************************

	// ****************注入区****************************************
	@Resource
	private SchoolServices schoolServices;
	@Resource
	private CompetitionServices competitionServices;
	@Resource
	private RecordsServices recordsServices;
	@Resource
	private StudentInfoServices studentInfoServices;
	@Resource
	private TeacherInfoServices teacherInfoServices;

	// ****************页面初始化区****************************************
	/**
	 * 录入成果初始化
	 * 
	 * @return
	 */
	@RequestMapping("RecordsManger_add")
	public String RecordsManger_add() {
		setAttr("competition", competitionServices.getAll());
		setAttr("schools", schoolServices.getAll());
		return "Records/RecordsManger_add";
	}

	/**
	 * 编辑
	 * 
	 * @return
	 */
	@RequestMapping("RecordsManger_edit")
	public String RecordsManger_edit(String id) {
		int idd = id == "" ? 0 : Integer.parseInt(id);
		setAttr("competition", competitionServices.getAll());
		setAttr("schools", schoolServices.getAll());
		setAttr("record", recordsServices.getById(idd));
		// Records records = recordsServices.getById(idd);
		return "Records/RecordsManger_edit";
	}

	/**
	 * 成果管理初始化
	 * 
	 * @return
	 */
	@RequestMapping("RecordsManger")
	public String RecordsManger(String page) {
		int currentPage = page == null ? 1 : Integer.parseInt(page);
		setAttr("currentPage", currentPage);
		setAttr("counts", recordsServices.getCounts());
		List<Records> list = recordsServices.getByPage(currentPage,
				(User) getSession("currentUser"));
		setAttr("records", list);
		return "Records/RecordsManger";
	}

	/**
	 * 成果审核初始化
	 * 
	 * @return
	 */
	@RequestMapping("RecordsCheck")
	public String RecordsCheck(String page) {
		int currentPage = page == null ? 1 : Integer.parseInt(page);
		User currentUser = (User) getSession("currentUser");
		setAttr("currentPage", currentPage);
		setAttr("counts", recordsServices.getCounts());
		setAttr("records",
				recordsServices.getWaitCheckByPage(currentPage, currentUser));
		return "Records/RecordsCheck";
	}

	/**
	 * 根据院系ID获取专业
	 */
	@RequestMapping("getMajorBySchool")
	public void getMajorBySchool(int id, HttpServletResponse response) {
		try {
			Iterator<Major> i = schoolServices.getByID(id).getsMajor()
					.iterator();
			String str = "";
			while (i.hasNext()) {
				str += i.next().getmName() + ",";
			}
			str = str.substring(0, str.length() - 1);
			response.getWriter().println(str);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// ****************页面提交处理区****************************************
	/**
	 * 添加提交记录
	 * 
	 * @param response
	 * @param records
	 */
	@RequestMapping("RecordsManger_add_submit")
	public void RecordsManger_add_submit(HttpServletResponse response,
			@ModelAttribute Records records) {
		try {
			// 学生信息处理
			for (StudentInfo s : records.getReStudentInfo()) {
				if (studentInfoServices.exit(s)) {
					studentInfoServices.update(s);
				} else {
					studentInfoServices.save(s);
				}
			}
			// 教师信息处理
			for (TeacherInfo t : records.getReTeacherInfo()) {
				if (teacherInfoServices.exit(t)) {
					teacherInfoServices.update(t);
				} else {
					teacherInfoServices.save(t);
				}
			}
			records.setReCheckStatus(0);
			records.setReCheckStatusAdmin(0);
			records.setReGiveStatus(0);
			records.setReWriteTime(Help.getCurrentTime());
			User user=((User) getSession("currentUser"));
			records.setReWritePerson(user.getuName());
			records.setReSchool(user.getuSchool().getsId());
			recordsServices.save(records);
			response.getWriter().print(
					Help.getScript("提交成功", "RecordsManger_add.html"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 编辑提交
	 * 
	 * @param response
	 * @param records
	 */
	@RequestMapping("RecordsManger_edit_submit")
	public void RecordsManger_edit_submit(HttpServletResponse response,
			@ModelAttribute Records records) {
		try {
			// 学生信息处理
			for (StudentInfo s : records.getReStudentInfo()) {
				if (s.getsId() != null) {
					if (studentInfoServices.exit(s)) {
						studentInfoServices.update(s);
					} else {
						studentInfoServices.save(s);
					}
				}
			}
			// 教师信息处理
			for (TeacherInfo t : records.getReTeacherInfo()) {
				if (teacherInfoServices.exit(t)) {
					teacherInfoServices.update(t);
				} else {
					teacherInfoServices.save(t);
				}
			}
			records.setReWriteTime(Help.getCurrentTime());
			records.setReWritePerson(((User) getSession("currentUser"))
					.getuName());
			recordsServices.update(records);
			response.getWriter().print(
					Help.getScript("修改成功", "RecordsManger.html"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查看详情
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("RecordsManger_detail")
	public String RecordsManger_detail(String id) {
		int idd = id == "" ? 0 : Integer.parseInt(id);
		Records records = recordsServices.getById(idd);
		setAttr("stu_reward", recordsServices.distributeStuReward(records));
		setAttr("tch_reward", recordsServices.distributeTchReward(records));
		setAttr("detail", records);
		return "Records/RecordsManger_detail";
	}

	/**
	 * 审核处理
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("RecordsCheck_deal")
	public String RecordsCheck_deal(String id) {
		int idd = id == "" ? 0 : Integer.parseInt(id);
		Records records = recordsServices.getById(idd);
		setAttr("detail", records);
		setAttr("stu_reward", recordsServices.distributeStuReward(records));
		setAttr("tch_reward", recordsServices.distributeTchReward(records));
		return "Records/RecordsCheck_deal";
	}

	/**
	 * 审核通过
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("RecordsCheck_deal_pass")
	public void RecordsCheck_deal_pass(String id, HttpServletResponse response) {
		int idd = id == "" ? 0 : Integer.parseInt(id);
		Records records = recordsServices.getById(idd);
		User user = (User) getSession("currentUser");
		if (user.getuRole().getrLevel() == 1
				|| user.getuRole().getrLevel() == 2) {//当前登录角色为超级管理员或者教务处管理员
			records.setReCheckStatusAdmin(1);
			records.setReCheckPersonAdmin(user.getuName());
			records.setRecheckSuggestionAdmin("通过");
		} else {//当前用户为院级管理员
			records.setReCheckStatus(1);
			records.setReCheckPerson(user.getuName());
			records.setRecheckSuggestion("通过");
		}
		recordsServices.update(records);
		try {
			response.getWriter().print(
					Help.getScript("审核通过", "RecordsCheck.html"));
		} catch (IOException e) {
		}
	}

	/**
	 * 审核通过
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("RecordsCheck_deal_defeat")
	public void RecordsCheck_deal_defeat(String id, String backMsg,
			HttpServletResponse response) {
		try {
			int idd = id == "" ? 0 : Integer.parseInt(id);
			Records records = recordsServices.getById(idd);
			User user = (User) getSession("currentUser");
			if (user.getuRole().getrLevel() == 1
					|| user.getuRole().getrLevel() == 2) {
				records.setReCheckStatusAdmin(-1);
				records.setReCheckPersonAdmin(user.getuName());
				records.setRecheckSuggestionAdmin(backMsg);
			} else {
				records.setReCheckStatus(-1);
				records.setReCheckPerson(user.getuName());
				records.setRecheckSuggestion(backMsg);
			}
			recordsServices.update(records);
			response.getWriter().print(1);
		} catch (IOException e) {
			try {
				response.getWriter().print(0);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * 异步获取关于项目的学生信息
	 * 
	 * @param response
	 */
	@RequestMapping("RecordsManger_detail_getStudentInfoById")
	public void RecordsManger_detail_getStudentInfoById(
			HttpServletResponse response) {
		int id = Integer.parseInt(getAttr("id").toString());
		String res = recordsServices.getReStudentInfoToJson(id);
		try {
			response.getWriter().print(res);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 异步获取关于项目的指导教师信息
	 * 
	 * @param response
	 */
	@RequestMapping("RecordsManger_detail_getTeacherInfoById")
	public void RecordsManger_detail_getTeacherInfoById(
			HttpServletResponse response) {
		int id = Integer.parseInt(getAttr("id").toString());
		String res = recordsServices.getReTeacherInfoToJson(id);
		try {
			response.getWriter().print(res);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除记录
	 * 
	 * @param id
	 * @param response
	 */
	@RequestMapping("RecordsManger_delete")
	public void RecordsManger_delete(String id, HttpServletResponse response) {
		int idd = id == "" ? 0 : Integer.parseInt(id);
		try {
			recordsServices.deleteById(idd);
			response.getWriter().print(
					Help.getScript("删除成功", "RecordsManger.html"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取待审核记录的数目
	 */
	@RequestMapping("getWaitCheckNum")
	public void getWaitCheckNum(HttpServletResponse response) {
		try {
			User currentUser=(User) getSession("currentUser");
			response.getWriter().print(recordsServices.getWaitCheckCount(currentUser));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据比赛ID以及奖项等级获取其奖励信息
	 */
	@RequestMapping("getRewardByCompetition")
	public void getRewardByCompetition(HttpServletResponse response) {
		try {
			response.getWriter().print(
					recordsServices.getRewardByCompetition(getAttr("id")
							.toString(), getAttr("grade").toString()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
