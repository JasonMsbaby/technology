package com.techology.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * 读取表格工具类 返回list数组
 * 
 * @author Jason
 * 
 */
public class ExcelImport {
	// private Class<T> clazz;//返回数据泛型
	private List<ArrayList<String>> data;// 输出数据
	private InputStream inputStream;// 表格文件路径+名称
	private int startColums;// 开始读取的行数
	private String fileName;

	public ExcelImport(InputStream inputStream, int start, String fileName) {
		this.inputStream = inputStream;
		startColums = start;
		this.fileName = fileName;
		data = new ArrayList<ArrayList<String>>();
	}

	public List<ArrayList<String>> getData() {
		try {
			Workbook wb = null;
			if (fileName.endsWith("xls")) {
				wb = new HSSFWorkbook(inputStream);// 解析xls格式
			}
			Sheet sheet = wb.getSheetAt(0);// 第一个工作表
			int firstRowIndex = startColums;
			// int firstRowIndex = sheet.getFirstRowNum();
			int lastRowIndex = sheet.getLastRowNum();
			for (int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {
				Row row = sheet.getRow(rIndex);
				if (row != null) {
					int firstCellIndex = row.getFirstCellNum();
					int lastCellIndex = row.getLastCellNum();
					ArrayList<String> l = new ArrayList<String>();
					for (int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex++) {
						Cell cell = row.getCell(cIndex);
						if (cell != null) {
							l.add(cell.toString());
						}
					}
					data.add(l);
				}
			}
			return data;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
