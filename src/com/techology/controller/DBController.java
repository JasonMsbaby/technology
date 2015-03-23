package com.techology.controller;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techology.base.BaseController;
import com.techology.common.DatabaseBac;
import com.techology.common.Help;

/**
 * @author lcp
 */
@SuppressWarnings({ "static-access", "deprecation" })
@Controller
public class DBController extends BaseController {

	// ****************变量定义区****************************************

	// ****************注入区****************************************
	@Resource
	private HttpServletRequest request;

	// ****************页面初始化区****************************************
	@RequestMapping("dbbacList")
	public String dbbacList() {
		String fPath = request.getRealPath("DBBac");
		File f = new File(fPath);
		File[] files = f.listFiles();
		setAttr("file", files);
		return "Other/dbbacList";
	}

	// ****************页面提交处理区****************************************

	/**
	 * 数据库备份
	 * 
	 * @param response
	 */

	@RequestMapping("dbbacup")
	public void dbbacup(HttpServletResponse response) {
		Calendar c = Calendar.getInstance();
		int y = c.get(Calendar.YEAR);
		int m = c.get(Calendar.MONTH) + 1;
		int d = c.get(Calendar.DATE);
		int h = c.get(Calendar.HOUR);
		int min = c.get(Calendar.MINUTE);
		int s = c.get(Calendar.SECOND);
		String fPath = request.getRealPath("DBBac") + "\\techology" + y + ""
				+ m + "" + d + "" + h + "" + min + "" + s + ".sql";
		DatabaseBac dbc = new DatabaseBac();
		if (dbc.backup(fPath)) {
			try {
				response.getWriter().print(
						Help.getScript("备份成功", "dbbacList.html"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 数据库恢复
	 * 
	 * @param response
	 */
	@RequestMapping("DB_load")
	public void DB_load(HttpServletResponse response) {
		String fPath = request.getRealPath("DBBac") + "\\" + getAttr("fName");
		DatabaseBac dbc = new DatabaseBac();
		if (dbc.load1(fPath)) {
			try {
				response.getWriter().print(
						Help.getScript("数据恢复完成！", "dbbacList.html"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 删除数据库文件
	 * @param response
	 */
	@RequestMapping("DB_delete")
	public void DB_delete(HttpServletResponse response){
		String fPath = request.getRealPath("DBBac") + "\\" + getAttr("fName");
		DatabaseBac dbc = new DatabaseBac();
		if(dbc.delete(response, fPath)){
			try {
				response.getWriter().print(
						Help.getScript("数据已删除！", "dbbacList.html"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
