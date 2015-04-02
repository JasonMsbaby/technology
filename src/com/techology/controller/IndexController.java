package com.techology.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techology.base.BaseController;
import com.techology.common.ValidateCode;
import com.techology.entity.User;
import com.techology.services.PermissionServices;
import com.techology.services.UserServices;
/**
 * 首页请求控制层
 * 
 * @author jason
 *
 */
@Controller
public class IndexController extends BaseController {

	@Resource
	private PermissionServices permissionServices;
	@Resource
	private UserServices userServices;

	/**
	 * 首页请求
	 * 
	 * @return
	 */
	@RequestMapping("index")
	public String index() {
		setAttr("list", permissionServices.getAll());
		return "index";
	}
	/**
	 * 欢迎页
	 * @return
	 */
	@RequestMapping("welcome")
	public String welcome() {
		return "Other/welcome";
	}
	/**
	 * 登录页首次加载
	 * @return
	 */
	@RequestMapping("login")
	public String login_init() {
		return "login";
	}
	/**
	 * 登录请求
	 * @param user
	 * @return
	 */
	@RequestMapping("login_submit")
	public String login(User user,String code2) {
		
		String session_code=(String) getSession("code");
		if(code2.toLowerCase().equals(session_code.toLowerCase())){
			User user2 = userServices.exit(user);
			if (user2 == null) {
				setAttr("info", "用户名或密码错误");
				return "login";
			} else {
				setSession("currentUser", user2);
				return "redirect:/index.html";
			}
		}else{
			setAttr("info", "验证码错误");
			return "login";
		}
		

	}
	/**
	 * 注销登录
	 * @return
	 */
	@RequestMapping("exit")
	public String exit(){
		getSession().invalidate();
		return "login";
	}
	/**
	 * 获取验证码
	 */
	@RequestMapping("getCode")
	public void getValidateCode(HttpServletResponse response){
		// 设置响应的类型格式为图片格式  
        response.setContentType("image/jpeg");  
        //禁止图像缓存。  
        response.setHeader("Pragma", "no-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);   
        ValidateCode vCode = new ValidateCode();  
        setSession("code", vCode.getCode());  
        try {
			vCode.write(response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
}
