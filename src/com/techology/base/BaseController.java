package com.techology.base;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 控制层基类，封装了控制层常用操作 请求方法返回值类型：ModelAndView
 * 
 * @author jason
 * 
 */
public class BaseController {
	//注入session
	@Resource
	private HttpSession session;
	//注入request
	@Resource
	private HttpServletRequest request;
	/**
	 * 设置传递前台的变量
	 * @param name
	 * @param value
	 */
	public void setAttr(String key, Object value) {
		request.setAttribute(key, value);
	}
	/**
	 * 获取request内传递的值
	 * @param key
	 * @return
	 */
	public Object getAttr(String key){
		return request.getParameter(key);
	}
	/**
	 * 获取seesion内的值
	 * @param key
	 * @return
	 */
	public Object getSession(String key){
		return session.getAttribute(key);
	}
	/**
	 * 设置session的值
	 * @param key
	 * @param value
	 */
	public void setSession(String key,Object value){
		session.setAttribute(key, value);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public HttpSession getSession() {
		return session;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	
	
	
	

}
