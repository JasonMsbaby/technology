package com.techology.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.servlet.http.HttpServletResponse;

/**
 * @author lcp
 */
public class DatabaseBac {
	
	/**
	 * 数据库备份
	 * @param fPath
	 * @return
	 */
	public static boolean backup(String fPath) {
		  try {
		   Runtime rt = Runtime.getRuntime();
		   // 调用 调用mysql的安装目录的命令
		   Process child = rt
		     .exec("F://mysql//bin//mysqldump -h localhost -uroot -p1122  techology");
		   // 设置导出编码为utf-8。这里必须是utf-8
		   // 把进程执行中的控制台输出信息写入.sql文件，即生成了备份文件。注：如果不对控制台信息进行读出，则会导致进程堵塞无法运行
		   InputStream in = child.getInputStream();// 控制台的输出信息作为输入流
		   InputStreamReader xx = new InputStreamReader(in, "utf-8");
		   // 设置输出流编码为utf-8。这里必须是utf-8，否则从流中读入的是乱码
		   String inStr;
		   StringBuffer sb = new StringBuffer("");
		   String outStr;
		   // 组合控制台输出信息字符串
		   BufferedReader br = new BufferedReader(xx);
		   while ((inStr = br.readLine()) != null) {
		    sb.append(inStr + "\r\n");
		   }
		   outStr = sb.toString();
		   // 要用来做导入用的sql目标文件：
		   FileOutputStream fout = new FileOutputStream(
				   fPath);
		   OutputStreamWriter writer = new OutputStreamWriter(fout, "utf-8");
		   writer.write(outStr);
		   writer.flush();
		   in.close();
		   xx.close();
		   br.close();
		   writer.close();
		   fout.close();
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		return true;
		 }
	
	
	
	/**
	 * 数据恢复
	 * @param fPath
	 * @return
	 */
	public static boolean load1(String fPath) {    
		
        try {       
            Runtime rt = Runtime.getRuntime();      
            // 调用 mysql 的 cmd:      
            Process child = rt.exec("F://mysql//bin//mysql.exe -uroot -p1122 techology ");      
            OutputStream out = child.getOutputStream();//控制台的输入信息作为输出流      
            String inStr;      
            StringBuffer sb = new StringBuffer("");      
            String outStr;      
            BufferedReader br = new BufferedReader(new InputStreamReader(      
                    new FileInputStream(fPath), "utf8"));      
            while ((inStr = br.readLine()) != null) {      
                sb.append(inStr + "\r\n");      
            }      
            outStr = sb.toString();      
     
            OutputStreamWriter writer = new OutputStreamWriter(out, "utf8");      
            writer.write(outStr);      
            // 注：这里如果用缓冲方式写入文件的话，会导致中文乱码，用flush()方法则可以避免      
            writer.flush();      
            // 别忘记关闭输入输出流      
            out.close();      
            br.close();      
            writer.close();      
     
            System.out.println("ok");      
     
        } catch (Exception e) {      
            e.printStackTrace();      
        }
		return true;      
     
    } 
	
	
	
	/** 
	  * 删除文件，可以是文件或文件夹 
	  *  
	  * @param fileName 
	  *            要删除的文件名 
	  * @return 删除成功返回true，否则返回false 
	  */  
	 public static boolean delete(HttpServletResponse response,String fileName) {  
	  File file = new File(fileName);  
	  if (!file.exists()) {  
		  try {
			response.getWriter().print(
						Help.getScript("删除失败！"+fileName+"不存在！", "dbbacList.html"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   return false;  
	  } else {  
	   if (file.isFile())  
	    return deleteFile(fileName);  
	   else  
	    return deleteDirectory(fileName);  
	  }  
	 }  
	  
	 
	 
/*************************其他*****************************/
	 /** 
	  * 删除单个文件 
	  *  
	  * @param fileName 
	  *            要删除的文件的文件名 
	  * @return 单个文件删除成功返回true，否则返回false 
	  */  
	 public static boolean deleteFile(String fileName) {  
	  File file = new File(fileName);  
	  // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除  
	  if (file.exists() && file.isFile()) {  
	   if (file.delete()) {  
	    System.out.println("删除单个文件" + fileName + "成功！");  
	    return true;  
	   } else {  
	    System.out.println("删除单个文件" + fileName + "失败！");  
	    return false;  
	   }  
	  } else {  
	   System.out.println("删除单个文件失败：" + fileName + "不存在！");  
	   return false;  
	  }  
	 }  
	  
	 /** 
	  * 删除目录及目录下的文件 
	  *  
	  * @param dir 
	  *            要删除的目录的文件路径 
	  * @return 目录删除成功返回true，否则返回false 
	  */  
	 public static boolean deleteDirectory(String dir) {  
	  // 如果dir不以文件分隔符结尾，自动添加文件分隔符  
	  if (!dir.endsWith(File.separator))  
	   dir = dir + File.separator;  
	  File dirFile = new File(dir);  
	  // 如果dir对应的文件不存在，或者不是一个目录，则退出  
	  if ((!dirFile.exists()) || (!dirFile.isDirectory())) {  
	   System.out.println("删除目录失败：" + dir + "不存在！");  
	   return false;  
	  }  
	  boolean flag = true;  
	  // 删除文件夹中的所有文件包括子目录  
	  File[] files = dirFile.listFiles();  
	  for (int i = 0; i < files.length; i++) {  
	   // 删除子文件  
	   if (files[i].isFile()) {  
	    flag = DatabaseBac.deleteFile(files[i].getAbsolutePath());  
	    if (!flag)  
	     break;  
	   }  
	   // 删除子目录  
	   else if (files[i].isDirectory()) {  
	    flag = DatabaseBac.deleteDirectory(files[i]  
	      .getAbsolutePath());  
	    if (!flag)  
	     break;  
	   }  
	  }  
	  if (!flag) {  
	   System.out.println("删除目录失败！");  
	   return false;  
	  }  
	  // 删除当前目录  
	  if (dirFile.delete()) {  
	   System.out.println("删除目录" + dir + "成功！");  
	   return true;  
	  } else {  
	   return false;  
	  }  
	 }  
}
