package com.techology.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techology.base.BaseController;
/**
 * 注释
 * @author jason
 *
 */
@Controller
public class ExampleController extends BaseController {

	//****************变量定义区****************************************
	
	//****************注入区****************************************

	//****************页面初始化区****************************************
	/**
	 * 
	 * @return
	 */
	@RequestMapping("test")
	public String test(){
		return "test";
	}
	
	//****************页面提交处理区****************************************
	
}
