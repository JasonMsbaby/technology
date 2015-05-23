package com.techology.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.techology.common.Help;
import com.techology.dao.*;
import com.techology.entity.*;

/**
 * 此处的导出表格功能内容写的较死，没有时间去研究通用性的设计 若你们更改，建议用首先导出html表格，然后解析html表格的方式生成excel
 * 
 * @author Jason
 * 
 */
@Service
public class ExcelServices {

	@Resource
	private CompetitionServices competitionServices;
	@Resource
	private RewardService rewardServices;

	@Resource
	private RecordsServices recordsServices;
	@Resource
	private SchoolServices schoolServices;

	// **********************************************************************
	/**
	 * 获取比赛基本表列宽度
	 * 
	 * @return
	 */
	public ArrayList<Integer> getCompetionColumWidth() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int danwei = 1500;
		list.add(danwei);
		list.add(danwei * 7);
		list.add(danwei * 7);
		list.add(danwei * 2);
		list.add(danwei * 3);
		list.add(danwei);
		return list;
	}

	/**
	 * 获取竞赛基本表的列标题集合
	 * 
	 * @return
	 */
	public ArrayList<String> getCompetionTitle() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("序号");
		list.add("竞赛名称");
		list.add("主办单位");
		list.add("认定级别");
		list.add("承办单位");
		list.add("备注");
		return list;
	}

	public ArrayList<ArrayList<String>> getCompetionContent() {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		List<Competition> competitions = competitionServices.getAll();
		int i = 0;
		for (Competition c : competitions) {
			i++;
			ArrayList<String> cell = new ArrayList<String>();
			cell.add(String.valueOf(i));
			cell.add(c.getcName());
			cell.add(c.getcOrganize());
			cell.add(c.getcLevel());
			cell.add(c.getcOrganization());
			cell.add("");
			list.add(cell);
		}
		return list;
	}

	// *******************************德州学院2014年度学生获得大学生科技文化竞赛国家级奖励统计表***************************************

	public ArrayList<String> getStudentRecordColums() {
		ArrayList<String> arry = new ArrayList<String>();
		arry.add("序号");
		arry.add("教学单位");
		arry.add("竞赛名称");
		arry.add("竞赛级别");
		arry.add("项目名称");
		arry.add("获奖类别");
		// 学生一
		arry.add("学号");
		arry.add("姓名");
		arry.add("金额");
		arry.add("性别");
		arry.add("班级");
		arry.add("身份证号");
		arry.add("建行卡号");
		// 学生二
		arry.add("学号");
		arry.add("姓名");
		arry.add("金额");
		arry.add("性别");
		arry.add("班级");
		arry.add("身份证号");
		arry.add("建行卡号");
		// 学生三
		arry.add("学号");
		arry.add("姓名");
		arry.add("金额");
		arry.add("性别");
		arry.add("班级");
		arry.add("身份证号");
		arry.add("建行卡号");
		arry.add("备注");
		return arry;
	}

	public ArrayList<Integer> getStudentRecordColumsWidth() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int danwei = 1500;
		list.add(danwei);
		list.add(danwei * 4);
		list.add(danwei * 8);
		list.add(danwei * 2);
		list.add(danwei * 6);
		list.add(danwei * 2);

		list.add(danwei * 3);
		list.add(danwei * 2);
		list.add(danwei * 1);
		list.add(danwei * 1);
		list.add(danwei * 4);
		list.add(danwei * 4);
		list.add(danwei * 4);

		list.add(danwei * 3);
		list.add(danwei * 2);
		list.add(danwei * 1);
		list.add(danwei * 1);
		list.add(danwei * 4);
		list.add(danwei * 4);
		list.add(danwei * 4);

		list.add(danwei * 3);
		list.add(danwei * 2);
		list.add(danwei * 1);
		list.add(danwei * 1);
		list.add(danwei * 4);
		list.add(danwei * 4);
		list.add(danwei * 4);

		list.add(danwei * 2);
		return list;
	}

	/*
	 * 获取学生获奖比赛奖励成果的数据
	 */
	public ArrayList<ArrayList<String>> getStudentRecordData(String years,
			String grade) {
		ArrayList<ArrayList<String>> rows = new ArrayList<ArrayList<String>>();

		List<Records> list = recordsServices.getAllByCompetionLevel(years,
				grade);
		if (list != null && list.size() > 0) {
			int i = 0;
			for (Records r : list) {

				i++;
				ArrayList<String> cell = new ArrayList<String>();
				cell.add(i + "");
				cell.add(schoolServices.getByID(r.getReSchool()).getsName());
				cell.add(r.getReCompetition().getcName());
				cell.add(r.getReCompetition().getcLevel());
				cell.add(r.getReProjectName());
				cell.add(r.getReGrade());
				List<StudentInfo> stuList = r.getReStudentInfo();
				for (StudentInfo s : stuList) {
					cell.add(s.getsId());
					cell.add(s.getsName());
					cell.add(recordsServices.distributeStuReward(r).get(
							s.getsId()));
					cell.add(s.getsSex());
					cell.add(s.getsGrade() + s.getsMajor().getmName());
					cell.add(s.getsIDCard());
					cell.add(s.getsIDBank());
				}
				cell.add("");
				rows.add(cell);
			}
			return rows;
		} else {
			return null;
		}

	}

	// *******************************德州学院2014年度学生获得大学生科技文化竞赛教师奖励统计表***************************************

	public ArrayList<String> getTeacherRecordColums() {
		ArrayList<String> arry = new ArrayList<String>();
		arry.add("序号");// 1
		arry.add("教学单位");// 4
		arry.add("竞赛名称");// 4
		arry.add("竞赛级别");// 2
		arry.add("项目名称");// 4
		arry.add("获奖类别");// 2
		arry.add("教工号");// 2
		arry.add("姓名");// 2
		arry.add("性别");// 1
		arry.add("金额");// 1
		arry.add("课时");// 1
		arry.add("身份证号");// 3
		arry.add("备注");// 2
		return arry;
	}

	public ArrayList<Integer> getTeacherRecordColumsWidth() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int danwei = 1500;
		list.add(danwei);
		list.add(danwei * 4);
		list.add(danwei * 4);
		list.add(danwei * 2);
		list.add(danwei * 4);
		list.add(danwei * 2);

		list.add(danwei * 2);
		list.add(danwei * 2);
		list.add(danwei * 1);
		list.add(danwei * 1);
		list.add(danwei * 1);

		list.add(danwei * 3);
		list.add(danwei * 2);
		return list;
	}

	/*
	 * 获取教师获奖比赛奖励成果的数据
	 */
	public ArrayList<ArrayList<String>> getTeacherRecordData(String years) {
		ArrayList<ArrayList<String>> rows = new ArrayList<ArrayList<String>>();

		List<Records> list = recordsServices.getAllByCompetionLevel(years);
		if (list != null && list.size() > 0) {
			int i = 0;
			for (Records r : list) {
				List<TeacherInfo> tchList = r.getReTeacherInfo();
				for (TeacherInfo t : tchList) {
					i++;
					ArrayList<String> cell = new ArrayList<String>();
					cell.add(i + "");
					cell.add(schoolServices.getByID(r.getReSchool()).getsName());
					cell.add(r.getReCompetition().getcName());
					cell.add(r.getReCompetition().getcLevel());
					cell.add(r.getReProjectName());
					cell.add(r.getReGrade());
					cell.add(t.gettId());
					cell.add(t.gettName());
					cell.add(t.gettSex());
					cell.add(recordsServices.distributeTchReward(r).get(
							t.gettId()));

					Reward re = rewardServices.getByLevelAndGrade(r
							.getReCompetition().getcLevel(), r.getReGrade());
					if (re != null) {
						cell.add(re.getrTeacher().split(":")[1]);
					} else {
						cell.add("-");
					}
					cell.add(t.gettIdcard());
					cell.add("");
					rows.add(cell);
				}

			}
			return rows;
		} else {
			return null;
		}

	}

	// *******************************获取奖励信息一览表的数据***************************************
	public ArrayList<Integer> getRewardColumsWidth() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int danwei = 1500;
		list.add(danwei * 2);
		for (int i = 0; i < 30; i++) {
			list.add(danwei);
		}
		return list;
	}

	public ArrayList<String> getRewardColumNames() {
		ArrayList<String> str = new ArrayList<String>();
		str.add("比赛级别");
		String[] strings = Help.GRADE;
		for (String s : strings) {
			str.add(s);
		}
		return str;
	}

	/**
	 * 获取所有奖项等级
	 * 
	 * @return
	 */
	private ArrayList<String> getRewardColumNames2() {
		ArrayList<String> str = new ArrayList<String>();
		str.add("获奖等级");
		for (int i = 0; i < 6; i++) {
			str.add("特等奖");
			str.add("一");
			str.add("二");
			str.add("三");
			str.add("鼓励奖");
		}
		return str;
	}

	public ArrayList<ArrayList<String>> getRewardData() {
		ArrayList<ArrayList<String>> rows = new ArrayList<ArrayList<String>>();
		rows.add(getRewardColumNames2());
		rows.add(getRewardStudent1());
		rows.add(getRewardStudent2());
		rows.add(getRewardTeacher1());
		rows.add(getRewardTeacher2());
		return rows;
	}

	/**
	 * 获取教师的奖励金钱
	 * 
	 * @return
	 */
	private ArrayList<String> getRewardTeacher2() {
		ArrayList<String> arry = new ArrayList<String>();
		arry.add("教师奖励/元");
		String[] names = Help.GRADE;
		for (String str : names) {
			List<Reward> list = rewardServices.getByLevel(str);
			Map<String, String> map = new HashMap<String, String>();
			for (Reward r : list) {
				map.put(r.getrGrade(), r.getrTeacher());
			}
			String one = map.get("特等奖");
			String two = map.get("一等奖");
			String three = map.get("二等奖");
			String four = map.get("三等奖");
			String five = map.get("鼓励奖");
			if (one != null) {
				arry.add(one.split(":")[0]);
			} else {
				arry.add("-");
			}
			if (two != null) {
				arry.add(two.split(":")[0]);
			} else {
				arry.add("-");
			}
			if (three != null) {
				arry.add(three.split(":")[0]);
			} else {
				arry.add("-");
			}
			if (four != null) {
				arry.add(four.split(":")[0]);
			} else {
				arry.add("-");
			}
			if (five != null) {
				arry.add(five.split(":")[0]);
			} else {
				arry.add("-");
			}
		}
		return arry;
	}

	/**
	 * 获取教师的奖励成果
	 * 
	 * @return
	 */
	private ArrayList<String> getRewardTeacher1() {
		ArrayList<String> arry = new ArrayList<String>();
		arry.add("教师课时/课");
		String[] names = Help.GRADE;
		for (String str : names) {
			List<Reward> list = rewardServices.getByLevel(str);
			Map<String, String> map = new HashMap<String, String>();
			for (Reward r : list) {
				map.put(r.getrGrade(), r.getrTeacher());
			}
			String one = map.get("特等奖");
			String two = map.get("一等奖");
			String three = map.get("二等奖");
			String four = map.get("三等奖");
			String five = map.get("鼓励奖");
			if (one != null) {
				arry.add(one.split(":")[1]);
			} else {
				arry.add("-");
			}
			if (two != null) {
				arry.add(two.split(":")[1]);
			} else {
				arry.add("-");
			}
			if (three != null) {
				arry.add(three.split(":")[1]);
			} else {
				arry.add("-");
			}
			if (four != null) {
				arry.add(four.split(":")[1]);
			} else {
				arry.add("-");
			}
			if (five != null) {
				arry.add(five.split(":")[1]);
			} else {
				arry.add("-");
			}
		}
		return arry;
	}

	/**
	 * 获取学生奖励金钱
	 * 
	 * @return
	 */
	private ArrayList<String> getRewardStudent2() {
		ArrayList<String> arry = new ArrayList<String>();
		arry.add("学生奖励/元");
		String[] names = Help.GRADE;
		for (String str : names) {
			List<Reward> list = rewardServices.getByLevel(str);
			Map<String, String> map = new HashMap<String, String>();
			for (Reward r : list) {
				map.put(r.getrGrade(), r.getrStudent());
			}
			String one = map.get("特等奖");
			String two = map.get("一等奖");
			String three = map.get("二等奖");
			String four = map.get("三等奖");
			String five = map.get("鼓励奖");
			if (one != null) {
				arry.add(one.split(":")[0]);
			} else {
				arry.add("-");
			}
			if (two != null) {
				arry.add(two.split(":")[0]);
			} else {
				arry.add("-");
			}
			if (three != null) {
				arry.add(three.split(":")[0]);
			} else {
				arry.add("-");
			}
			if (four != null) {
				arry.add(four.split(":")[0]);
			} else {
				arry.add("-");
			}
			if (five != null) {
				arry.add(five.split(":")[0]);
			} else {
				arry.add("-");
			}
		}
		return arry;
	}

	/**
	 * 获取学生奖励学分
	 * 
	 * @return
	 */
	private ArrayList<String> getRewardStudent1() {
		ArrayList<String> arry = new ArrayList<String>();
		arry.add("学生学分/分");
		String[] names = Help.GRADE;
		for (String str : names) {
			List<Reward> list = rewardServices.getByLevel(str);
			Map<String, String> map = new HashMap<String, String>();
			for (Reward r : list) {
				map.put(r.getrGrade(), r.getrStudent());
			}
			String one = map.get("特等奖");
			String two = map.get("一等奖");
			String three = map.get("二等奖");
			String four = map.get("三等奖");
			String five = map.get("鼓励奖");
			if (one != null) {
				arry.add(one.split(":")[1]);
			} else {
				arry.add("-");
			}
			if (two != null) {
				arry.add(two.split(":")[1]);
			} else {
				arry.add("-");
			}
			if (three != null) {
				arry.add(three.split(":")[1]);
			} else {
				arry.add("-");
			}
			if (four != null) {
				arry.add(four.split(":")[1]);
			} else {
				arry.add("-");
			}
			if (five != null) {
				arry.add(five.split(":")[1]);
			} else {
				arry.add("-");
			}
		}
		return arry;
	}
}
