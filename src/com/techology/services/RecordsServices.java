package com.techology.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.dialect.function.VarArgsSQLFunction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techology.dao.CompetitionDao;
import com.techology.dao.RecordsDao;
import com.techology.dao.RewardDao;
import com.techology.entity.Competition;
import com.techology.entity.Records;
import com.techology.entity.Reward;
import com.techology.entity.StudentInfo;
import com.techology.entity.TeacherInfo;
import com.techology.entity.User;

/**
 * 成果管理服务层
 * 
 * @author jason
 * 
 */
@Service
@Transactional
public class RecordsServices {

	@Resource
	private RecordsDao recordsDao;
	@Resource
	private CompetitionDao competitionDao;
	@Resource
	private RewardDao rewardDao;

	public void save(Records records) {
		recordsDao.save(records);
	}

	public void deleteById(int id) {
		recordsDao.delete(recordsDao.getById(id));
	}

	public List<Records> getAll() {
		return recordsDao.get();
	}

	public List<Records> getByPage(int page, User currentUser) {
		int level = currentUser.getuRole().getrLevel();
		if (level == 1 || level == 2) {// 教务处管理员或者超级管理员
			return recordsDao.get((page - 1) * 10, 10);
		} else if (level == 3) {// 院系管理员
			return recordsDao.get((page - 1) * 10, 10,
					new String[] { "reSchool" }, "reId",
					String.valueOf(currentUser.getuSchool().getsId()));
		} else {// 普通教师
			return recordsDao.get((page - 1) * 10, 10,
					new String[] { "reWritePerson" }, "reId",
					String.valueOf(currentUser.getuName()));
		}

	}

	public int getCounts() {
		return recordsDao.getCount();
	}

	public Records getById(int idd) {

		return recordsDao.getById(idd);
	}

	public void update(Records records) {
		recordsDao.update(records);

	}

	// 根据比赛记录ID获取学生信息并将其转化为Json字符串
	public String getReStudentInfoToJson(int id) {
		Records records = recordsDao.getById(id);
		List<StudentInfo> list = records.getReStudentInfo();
		Map<String, String> map_stu = distributeStuReward(records);
		String str = "[";
		for (StudentInfo stu : list) {
			str += "{";
			str += "\"sId\":\"" + stu.getsId() + "\",";
			str += "\"sName\":\"" + stu.getsName() + "\",";
			str += "\"sSex\":\"" + stu.getsSex() + "\",";
			str += "\"sGrade\":\"" + stu.getsGrade() + "\",";
			str += "\"sPhone\":\"" + stu.getsPhone() + "\",";
			str += "\"sSchool\":\"" + stu.getsSchool().getsName() + "\",";
			str += "\"sMajor\":\"" + stu.getsMajor().getmName() + "\",";
			str += "\"sIDCard\":\"" + stu.getsIDCard() + "\",";
			str += "\"sIDBank\":\"" + stu.getsIDBank() + "\",";
			str += "\"sSchool_Id\":\"" + stu.getsSchool().getsId() + "\",";
			str += "\"sMajor_Id\":\"" + stu.getsMajor().getmId() + "\",";
			str += "\"sMoney\":\"" + map_stu.get(stu.getsId()) + "\"";
			str += "},";
		}
		str = str.substring(0, str.length() - 1);
		str += "]";
		return str;
	}

	// 根据比赛记录ID获取教师并将其转化为Json字符串
	public String getReTeacherInfoToJson(int id) {
		Records records = recordsDao.getById(id);
		List<TeacherInfo> list = records.getReTeacherInfo();
		Map<String, String> map_tch = distributeTchReward(records);
		String str = "[";
		for (TeacherInfo tch : list) {
			str += "{";
			str += "\"tId\":\"" + tch.gettId() + "\",";
			str += "\"tName\":\"" + tch.gettName() + "\",";
			str += "\"tSex\":\"" + tch.gettSex() + "\",";
			str += "\"tSchool_Id\":\"" + tch.gettSchool().getsId() + "\",";
			str += "\"tSchool\":\"" + tch.gettSchool().getsName() + "\",";
			str += "\"tIdcard\":\"" + tch.gettIdcard() + "\",";
			str += "\"tBankNum\":\"" + tch.gettBankNum() + "\",";
			str += "\"tPhone\":\"" + tch.gettPhone() + "\",";
			str += "\"tMoney\":\"" + map_tch.get(tch.gettId()) + "\"";
			str += "},";
		}
		str = str.substring(0, str.length() - 1);
		str += "]";
		return str;
	}

	/**
	 * 分页查询待审核的比赛记录
	 * 
	 * @param currentPage
	 * @return
	 */
	public List<Records> getWaitCheckByPage(int currentPage, User currentUser) {
		int roleLevel = currentUser.getuRole().getrLevel();
		if (roleLevel == 1) {// 当前用户为超级管理员，获取所有的自己没有审核的
			return recordsDao.get((currentPage - 1) * 10, 10, new String[] {
					"reCheckStatusAdmin", "reCheckStatus" },
					"reWriteTime desc", "0", "1");
		} else if (roleLevel == 2) {// 当前用户为教务处管理员，获取所有的自己没有审核，院级审核通过的
			return recordsDao.get((currentPage - 1) * 10, 10, new String[] {
					"reCheckStatusAdmin", "reCheckStatus" },
					"reWriteTime desc", "0", "1");
		} else if (roleLevel == 3) {// 当前用户为院级管理员//获取本院的自己没有审核的
			return recordsDao.get((currentPage - 1) * 10, 10, new String[] {
					"reCheckStatus", "reSchool" }, "reWriteTime desc", "0",
					String.valueOf(currentUser.getuSchool().getsId()));
		} else {// 当前用户为普通教师，没有权限进行审核
			return null;
		}

	}

	/**
	 * 获取待审核记录的数目
	 */
	public String getWaitCheckCount(User currentUser) {
		int level = currentUser.getuRole().getrLevel();
		String str = "";
		if (level == 1 || level == 2) {// 超级管理员或者教务处管理员
			str += "{";
			str += "\"role\":" + 1 + ",";
			str += "\"content\":[{";
			int waitCount = recordsDao.getCount(new String[] { "reCheckStatus",
					"reCheckStatusAdmin" }, "1", "0");
			str += "\"wait\":" + waitCount + "}]}";
		} else if (level == 3) {// 院级管理员
			str += "{";
			str += "\"role\":" + 3 + ",";
			str += "\"content\":[{";
			int waitCount = recordsDao.getCount(new String[] { "reCheckStatus",
					"reSchool" }, "0",
					String.valueOf(currentUser.getuSchool().getsId()));
			int passCount = recordsDao.getCount(new String[] {
					"reCheckStatusAdmin", "reSchool" }, "-1",
					String.valueOf(currentUser.getuSchool().getsId()));
			str += "\"wait\":" + waitCount + ",";
			str += "\"pass\":" + passCount + "}]}";
		} else {// 教师用户
			str += "{";
			str += "\"role\":" + 4 + ",";
			str += "\"content\":[{";
			int passCount = recordsDao.getCount(new String[] { "reWritePerson",
					"reCheckStatus" }, currentUser.getuName(), "-1");
			str += "\"pass\":" + passCount + "}]}";
		}
		return str;

	}

	/**
	 * 通过项目ID和比赛等级获取奖励
	 * 
	 * @param id
	 * @param grade
	 * @return
	 */
	public String getRewardByCompetition(String id, String grade) {
		Competition competition = competitionDao.getById(Integer.parseInt(id));
		String level = competition.getcLevel();
		List<Reward> reward = rewardDao.get(
				new String[] { "rLevel", "rGrade" }, "rLevel", level, grade);
		String str = "";
		if (reward.size() > 0) {
			str += reward.get(0).getrTeacher() + ",";
			str += reward.get(0).getrStudent() + "";
		}
		return str;
	}

	/**
	 * 根据传进来的比赛记录为其分配奖励
	 * 
	 * @param records
	 *            返回map key--学号 value--分配的金额
	 * 
	 * @return
	 */
	public Map<String, String> distributeStuReward(Records records) {
		Map<String, String> map_stu = new HashMap<String, String>();
		if (records.getReStudentMoneny() != null) {
			String[] stu_money = records.getReStudentMoneny().split(",");
			for (String str : stu_money) {
				if (str.length() > 0) {
					map_stu.put(str.split(":")[0], str.split(":")[1]);
				}
			}
		}
		return map_stu;

	}

	/**
	 * 根据传进来的比赛记录为其分配奖励
	 * 
	 * @param records
	 *            返回map key--学号 value--分配的金额
	 * 
	 * @return
	 */
	public Map<String, String> distributeTchReward(Records records) {
		Map<String, String> map_tch = new HashMap<String, String>();
		if (records.getReTeacherMoney() != null) {
			String[] tch_money = records.getReTeacherMoney().split(",");

			for (String str : tch_money) {
				if (str.length() > 0) {
					map_tch.put(str.split(":")[0], str.split(":")[1]);
				}
			}
		}
		return map_tch;

	}

	/**
	 * 根据获奖比赛的类型获取所有国赛的获奖记录
	 * 
	 * @return
	 */
	public List<Records> getAllByCompetionLevel(String years, String grade) {
		//通过比赛的等级获取该等级的比赛有哪些
		List<Competition> competition = competitionDao.getAllByLevelLike(grade);
		if (competition.size()>0) {
			String str = "(";
			for (Competition c : competition) {
				str += c.getcID() + ",";
			}
			str = str.substring(0, str.length() - 1);
			str += ")";
			//获取所有查询出来的比赛去获取所对应的比赛记录
			return recordsDao.getAllByIn(str, years);
		} else{
			return null;
		}
	}
	public List<Records> getAllByCompetionLevel(String years) {
		//通过比赛的等级获取该等级的比赛有哪些
		List<Competition> competition = competitionDao.getAllByLevelLike();
		if (competition.size()>0) {
			String str = "(";
			for (Competition c : competition) {
				str += c.getcID() + ",";
			}
			str = str.substring(0, str.length() - 1);
			str += ")";
			//获取所有查询出来的比赛去获取所对应的比赛记录
			return recordsDao.getAllByIn(str, years);
		} else{
			return null;
		}
	}

}
