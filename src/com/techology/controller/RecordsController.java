package com.techology.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techology.base.BaseController;
import com.techology.common.Help;
import com.techology.entity.Major;
import com.techology.entity.Records;
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
		User user=(User) getSession().getAttribute("currentUser");
		if(!user.getuRole().getrLevel().equals(Help.JIAOSHI)){
			try {
				response.getWriter().print(
						Help.getScript("您并不是教师角色，无法进行成果提交", "RecordsManger_add.html"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
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
				records.setReWritePerson(user.getuName());
				records.setReSchool(user.getuSchool().getsId());
				recordsServices.save(records);
				response.getWriter().print(
						Help.getScript("提交成功", "RecordsManger_add.html"));
			} catch (IOException e) {
				e.printStackTrace();
			}
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
		User currentUser = (User) getSession("currentUser");
		boolean flag = true;
		if (currentUser.getuRole().getrLevel().equals(Help.JIAOSHI)) {
			Records records2 = recordsServices.getById(records.getReId());
			if (records2.getReCheckStatus() == 1
					&& records2.getReCheckStatusAdmin() == 1) {
				flag = false;
				try {
					response.getWriter().print(
							Help.getScript("审核已通过，不能进行编辑",
									"RecordsManger_add.html"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {

			}
		}
		if (flag) {
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
				User u = (User) getSession("currentUser");
				records.setReWritePerson(u.getuName());
				records.setReSchool(u.getuSchool().getsId());
				recordsServices.update(records);
				response.getWriter().print(
						Help.getScript("修改成功", "RecordsManger.html"));
			} catch (IOException e) {
				e.printStackTrace();
			}
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
		String roleLevel = user.getuRole().getrLevel();
		if (roleLevel.equals(Help.XIAOJI)) {// 校级管理员
			records.setReCheckStatusAdmin(1);
			records.setReCheckPersonAdmin(user.getuName());
			records.setRecheckSuggestionAdmin("通过");
		} else {// 院级管理员
			records.setReCheckStatusAdmin(0);
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
	 * 审核未通过
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
			String roleLevel = user.getuRole().getrLevel();
			if (roleLevel.equals(Help.XIAOJI)) {//校级管理员审核不通过
				records.setReCheckStatus(0);
				records.setReCheckPerson(null);
				records.setRecheckSuggestion(null);
				records.setReCheckStatusAdmin(-1);
				records.setReCheckPersonAdmin(user.getuName());
				records.setRecheckSuggestionAdmin(backMsg);
			} else {//院级管理员审核不通过
				records.setReCheckStatusAdmin(0);
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
			User currentUser = (User) getSession("currentUser");
			response.getWriter().print(
					recordsServices.getWaitCheckCount(currentUser));
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
			if (getAttr("id") != null) {
				String id = getAttr("id").toString();
				String grade = getAttr("grade").toString();
				response.getWriter().print(
						recordsServices.getRewardByCompetition(id, grade));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
