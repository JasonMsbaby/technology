package com.techology.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techology.base.BaseController;
import com.techology.common.Help;
import com.techology.entity.Reward;
import com.techology.services.RewardService;
/**
 * 奖励设置控制层
 * 
 * @author Andyxq
 *
 */
@Controller
public class RewardController extends BaseController{
	
	@Resource
	private RewardService rewardService;	
	
	/**
	 * 显示项目奖励列表
	 * */
	@RequestMapping("rewardSetting")
	public String RewarSetting(){
		int pagesize = 8;//每页显示条数 
		String p = (String) getAttr("page");
		int page = 1;
		page = ("".equals(p)||p==null)?1:(Integer.parseInt(p));//请求的页数
		int count = rewardService.count();//总共条数
		
		List<Reward> rewardList = rewardService.getRewardByPage((page-1)*pagesize, pagesize);
		setAttr("rewardList",rewardList );
		setAttr("count", count);
		setAttr("page", page);
		
		return "Competition/RewardSetting";
	}
	
	/**
	 * 显示增加项目奖励页
	 * */
	@RequestMapping("rewardSetting_add")
	public String RewarSettingAdd(){
		setAttr("grade", Help.GRADE);
		setAttr("order", Help.ORDER);
		return "Competition/RewardSetting_add";
	}
	
	/**
	 * 接收增加项目奖励表单数据
	 * */
	@RequestMapping("rewardSetting_add_submit")
	public String rewarSettingAddSubmit(Reward reward){
		reward.setrTeacher(getAttr("rTeacher_q")+":"+getAttr("rTeacher_k"));
		reward.setrStudent(getAttr("rStudent_q")+":"+getAttr("rStudent_f"));
		Reward reward2=rewardService.getByLevelAndGrade(reward.getrLevel(), reward.getrGrade());
		if(reward2==null){
			rewardService.save(reward);
		}else{
			reward.setrId(reward2.getrId());
			rewardService.update(reward);
		}
		setAttr("info", Help.getAlert("添加成功！"));
		return "redirect:rewardSetting_add.html";
	}
	
	
	/**
	 * 删除项目奖励
	 * */
	@RequestMapping("rewardSetting_delete")
	public String rewarSettingDelete(Reward reward){
		
		try {
			rewardService.delete(reward);
		} catch (Exception e) {
			setAttr("message", "error");
			return "Competition/MessageTemple";		
		}		
		
		setAttr("message", "success");
		return "Competition/MessageTemple";
	}
	
	/**
	 * 接收增加项目奖励表单数据
	 * */
	@RequestMapping("rewardSetting_edit")
	public String rewarSettingEdit(Reward reward){
		setAttr("rewardd",rewardService.getRewardById(reward));
		setAttr("grade", Help.GRADE);
		setAttr("order", Help.ORDER);
		return "Competition/RewardSetting_edit";
	}
	
	/**
	 * 接收编辑项目奖励表单数据
	 * */
	@RequestMapping("rewardSetting_edit_submit")
	public String rewarSettingEditSubmit(Reward reward){
		
		reward.setrTeacher(getAttr("rTeacher_q")+":"+getAttr("rTeacher_k"));
		reward.setrStudent(getAttr("rStudent_q")+":"+getAttr("rStudent_f"));
		rewardService.update(reward);
		setAttr("info", Help.getAlert("修改成功！"));
		getList();
		return "Competition/RewardSetting";
	}
	
	/**
	 * 获得去掉重复的比赛级别
	 * */
	@RequestMapping("rewardSetting_getrLevelDistinct")
	public String getrLevelDistinct(){
		String message = "";
		String rType = (String)getAttr("rType");
		if("".equals(rType)||rType==null){
			List<String> list = rewardService.getDistinct("rLevel",null);
			for(String leve : list){
				message += leve+"#";
			}
			setAttr("message", message);
			return "Competition/MessageTemple";
		}
		Map<String,String> map = new HashMap<String, String>();
		map.put("rLevel", rType);
		List<String> list = rewardService.getDistinct("rType",map);
		for(String type : list){
			message += type+"#";
		}
		setAttr("message", message);
		return "Competition/MessageTemple";
	}
	
	/**
	 * 获取项目奖励列表
	 * (因为好几个地方用到这片代码所以将他封装起来)
	 * */
	private void getList(){
		List<Reward> rewardList = rewardService.getAll();
		setAttr("rewardList", rewardList);
	}
}

