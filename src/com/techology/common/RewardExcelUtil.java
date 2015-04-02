package com.techology.common;

import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.Region;
import org.springframework.stereotype.Component;
/**
 * 针对于导出奖励信息一览表特定的导出工具类
 * @author Jason
 *
 */
public class RewardExcelUtil {

	private int SPLIT_COUNT = 15; // Excel每个工作表的行数

	private String fileName = null;// 下载时的文件名
	private String title = null;// 标题名
	private ArrayList fieldName = null; // excel数据的抬头栏，即名称栏

	private ArrayList fieldData = null; // excel导出的实际数据

	private HSSFWorkbook workBook = null;// 一个excel文件

	/**
	 * 样式
	 */
	private ArrayList<Integer> columWidth;

	// 有参构造器，限定了使用此类时，必须首先构建好两个list参数，并将所需数据放入上述两个list。
	// 其中fieldName这个list可以使用泛型约束List<String>
	// fieldData这个可以使用泛型约束List<List<Object>>
	public RewardExcelUtil(ArrayList fieldName, ArrayList fieldData,
			String fileName, String title, ArrayList<Integer> columWidth) {
		this.fieldName = fieldName;
		this.fieldData = fieldData;
		this.SPLIT_COUNT = fieldData.size();
		this.fileName = fileName + Help.getCurrentTime();
		this.title = title;
		this.columWidth = columWidth;
	}

	/**
	 * @return HSSFWorkbook
	 */
	public HSSFWorkbook createWorkbook() {
		workBook = new HSSFWorkbook();// 创建一个工作簿
		int rows = fieldData.size();// 清点出输入数据的行数
		int sheetNum = 0;// 将工作表个数清零
		// 根据数据的行数与每个工作表所能容纳的行数，求出需要创建工作表的个数
		if (rows % SPLIT_COUNT == 0) {
			sheetNum = rows / SPLIT_COUNT;
		} else {
			sheetNum = rows / SPLIT_COUNT + 1;
		}

		for (int i = 1; i <= sheetNum; i++) {
			HSSFSheet sheet = workBook.createSheet("Page " + i);// 创建工作表

			HSSFRow titleRow = sheet.createRow(0);
			// 合并单元格
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, ((ArrayList)fieldData.get(0))
					.size() - 1));
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 5));
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 6, 10));
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 11, 15));
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 16, 20));
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 21, 25));
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 26, 30));
			// 设置行高
			titleRow.setHeightInPoints(50);
			//设置列宽
			for(int c=0;c<columWidth.size();c++){
				sheet.setColumnWidth(c, columWidth.get(c));
			}
			// 创建标题列
			HSSFCell titleCell = titleRow.createCell(0);
			// 创建列样式
			HSSFCellStyle titleStyle = workBook.createCellStyle();
			;
			titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			HSSFFont titlefont = workBook.createFont();
			titlefont.setFontName("宋体");
			titlefont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			titlefont.setFontHeightInPoints(Short.parseShort("14"));
			titleStyle.setFont(titlefont);
			titleCell.setCellStyle(titleStyle);
			titleCell.setCellValue(title);

			HSSFRow headRow = sheet.createRow(1); // 创建抬头栏
			// 设置行高
			headRow.setHeightInPoints(35);
			
			for (int j = 0; j < fieldName.size(); j++) {
				HSSFCell cell=null;
				if(j!=0){
					int currentIndex=(j-1)*5+1;
					cell = headRow.createCell(currentIndex);
					for(int t=currentIndex;t<currentIndex+5;t++){
						HSSFCell cel = headRow.createCell(t);
						HSSFCellStyle style = workBook.createCellStyle();
						style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
						style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
						style.setBorderRight(HSSFCellStyle.BORDER_THIN);
						style.setBorderTop(HSSFCellStyle.BORDER_THIN);
						cel.setCellStyle(style);
						cel.setCellValue("");
					}
				}else{
					cell = headRow.createCell(j);
				}
				
				// 设置单元格格式
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				

				HSSFCellStyle style = workBook.createCellStyle();
				// 设置居中
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				// 设置字体
				HSSFFont font = workBook.createFont();
				font.setFontName("宋体");
				font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				font.setFontHeightInPoints(Short.parseShort("10"));
				style.setFont(font);
				style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				style.setBorderRight(HSSFCellStyle.BORDER_THIN);
				style.setBorderTop(HSSFCellStyle.BORDER_THIN);
				// 应用样式
				cell.setCellStyle(style);

				// 将数据填入单元格
				if (fieldName.get(j) != null) {
					cell.setCellValue((String) fieldName.get(j));
				} else {
					cell.setCellValue("-");
				}
			}

			// 创建数据栏单元格并填入数据
			for (int k = 0; k < (rows < SPLIT_COUNT ? rows : SPLIT_COUNT); k++) {
				if (((i - 1) * SPLIT_COUNT + k) >= rows)
					break;
				HSSFRow row = sheet.createRow(k + 2);
				// 设置样式
				row.setHeightInPoints(32);

				ArrayList rowList = (ArrayList) fieldData.get((i - 1)
						* SPLIT_COUNT + k);
				for (int n = 0; n < rowList.size(); n++) {
					HSSFCell cell = row.createCell(n);

					HSSFCellStyle style = workBook.createCellStyle();
					// 设置字体
					HSSFFont font = workBook.createFont();
					font.setFontName("宋体");
					font.setFontHeightInPoints(Short.parseShort("10"));
					style.setFont(font);
					style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
					style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
					style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
					style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					style.setBorderRight(HSSFCellStyle.BORDER_THIN);
					style.setBorderTop(HSSFCellStyle.BORDER_THIN);
					// 应用样式
					cell.setCellStyle(style);

					if (rowList.get(n) != null) {
						cell.setCellValue((String) rowList.get(n).toString());
					} else {
						cell.setCellValue("");
					}
				}
			}
		}
		return workBook;
	}

	// 将信息写入输出流的方法。
	public void exportExcel(HttpServletResponse response) throws Exception {
		OutputStream os = response.getOutputStream();// 取得输出流
		response.reset();// 清空输出流

		// 下面是对中文文件名的处理
		response.setCharacterEncoding("UTF-8");// 设置相应内容的编码格式
		fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ new String(fileName.getBytes("UTF-8"), "GBK") + ".xls");
		response.setContentType("application/msexcel");// 定义输出类型
		workBook = createWorkbook();
		workBook.write(os);
		os.close();
	}

}