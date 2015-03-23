package com.techology.common;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.techology.entity.Permission;
import com.techology.services.PermissionServices;

/**
 * 解析项目目录结构 将xml文件的目录结构存入数据库 初始化项目目录
 * 
 * @author jason
 * 
 */
@Component
public class SaxModule {

	@Resource
	private PermissionServices permissionServices;
	private Permission nav1, nav2, nav3;

	/**
	 * 开始解析
	 */
	public void start() {
		// 解析器的获取
		// 1、获取sax解析工厂
		try {
			SAXParserFactory sf = SAXParserFactory.newInstance();
			// 2、获取解析器
			SAXParser sp = sf.newSAXParser();

			// 创建xml文件的输入流
			InputStream inStream = this.getClass().getClassLoader()
					.getResourceAsStream("module.xml");

			// 3、解析xml文件，将事件处理器传入。
			sp.parse(inStream, new ModuleHandle());

		} catch (SAXException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @author jason
	 * 
	 */
	class ModuleHandle extends DefaultHandler {

		@Override
		public void startDocument() throws SAXException {
			//System.out.println("解析文档开始");
		}

		@Override
		public void endDocument() throws SAXException {
			//System.out.println("解析文档结束");
		}

		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			//System.out.println("开始读取节点");
			String id = "";
			String content = "";
			String type = "";                 
			String link = "";
			String allow="";
			int show = 0;
			int order = 0;
			if (qName != "nav") {
				id = attributes.getValue("id");
				content = attributes.getValue("content");
				type = attributes.getValue("type");
				link = attributes.getValue("link");
				show = Integer.parseInt(attributes.getValue("show"));
				order = Integer.parseInt(attributes.getValue("order"));
				allow=attributes.getValue("allow");
			}
			if ("nav1".equals(qName)) {
				nav1 = new Permission();
				nav1.setpId(id);
				nav1.setpContent(content);
				nav1.setpType(type);
				nav1.setpLink(link);
				nav1.setpShow(show);
				nav1.setpOrder(order);
				nav1.setpAllow(allow);
			}
			if ("nav2".equals(qName)) {
				nav2 = new Permission();
				nav2 = new Permission();
				nav2.setpId(id);
				nav2.setpContent(content);
				nav2.setpType(type);
				nav2.setpLink(link);
				nav2.setpShow(show);
				nav2.setpOrder(order);
				nav2.setpAllow(allow);
			}
			if ("nav3".equals(qName)) {
				nav3 = new Permission();
				nav3 = new Permission();
				nav3.setpId(id);
				nav3.setpContent(content);
				nav3.setpType(type);
				nav3.setpLink(link);
				nav3.setpShow(show);
				nav3.setpOrder(order);
				nav3.setpAllow(allow);
			}
		}

		@Override
		public void endElement(String uri, String localName, String qName)
				throws SAXException {
			//System.out.println("结束节点结束");
			
			if ("nav1".equals(qName)) {
				//System.out.println(nav1.toString());
				permissionServices.save(nav1);
			}
			if ("nav2".equals(qName)) {
				//System.out.println(nav2.toString());
				permissionServices.save(nav2);
			}
			if ("nav3".equals(qName)) {
				//System.out.println(nav3.toString());
				permissionServices.save(nav3);
			}
			
			
			
		}

	}

}
