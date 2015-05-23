package com.techology.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 通用工具帮助类 获取当前时间 获取返回script的字符串
 * 
 * @author jason
 * 
 */
public class Help {

	public static String YUANJI = "院级";
	public static String XIAOJI = "校级";
	public static String JIAOSHI = "教师";

	public static String[] GRADE = new String[] { "国家级A", "国家级B", "省部级A",
			"省部级B", "市厅级", "校级" };
	public static String[] ORDER = new String[] { "特等奖", "一等奖", "二等奖", "三等奖",
			"鼓励奖" };

	/**
	 * 返回弹窗并重定向
	 * 
	 * @param msg
	 * @param url
	 * @return
	 */
	public static String getScript(String msg, String url) {
		return "<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /></head><script>alert('"
				+ msg
				+ "');window.location.href='"
				+ url
				+ "';</script></html>";
	}

	/**
	 * 返回弹窗，不做处理
	 * 
	 * @param msg
	 * @return
	 */
	public static String getScript(String msg) {
		return "<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /></head><script>alert('"
				+ msg + "');</script></html>";
	}

	public static String getAlert(String msg) {
		return "<script>alert('" + msg + "');</script>";
	}

	/**
	 * 获取当前系统时间
	 * 
	 * @return
	 */
	public static String getCurrentTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		return df.format(new Date());// new Date()为获取当前系统时间
	}

}
