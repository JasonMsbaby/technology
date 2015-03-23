package com.techology.common;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Controller
public class uploadController {
	@RequestMapping("upload")
	public String init(){
		return "upload";
	}
	/**
	 * 单文件上传
	 * @param file
	 * @return
	 */
	@RequestMapping("upload_submit")
	public String submit(@RequestParam CommonsMultipartFile file){
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());
		System.out.println(file.getContentType());
		return "hello";
	}
	/**
	 * 多文件上传
	 * @param request
	 * @return
	 */
	@RequestMapping("upload_submit2")
	public String sumbit2(HttpServletRequest request){
		CommonsMultipartResolver commonsMultipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());
		if(commonsMultipartResolver.isMultipart(request)){
			MultipartHttpServletRequest multipartHttpServletRequest=(MultipartHttpServletRequest) request;
			Iterator<String> iterator=multipartHttpServletRequest.getFileNames();
			while(iterator.hasNext()){
				MultipartFile multipartFile=multipartHttpServletRequest.getFile(iterator.next());
				System.out.println(multipartFile.getOriginalFilename());
			}
		}
		return "hello";
	}

}
