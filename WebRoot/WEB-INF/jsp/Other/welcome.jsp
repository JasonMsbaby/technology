<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<jsp:include page="../Other/dropin.jsp"></jsp:include>
<title>欢迎页</title>
</head>
<body style="background-color: white;">
	<div class="line-big">
		<div class="xm3">
			<div class="panel border-back">
				<div class="panel-body text-center">
					<img src="style/pintuer/images/welcom.jpg" width="120"
						class="radius-circle" /><br /> admin
				</div>
				<div class="panel-foot bg-back border-back">您好，admin，这是您第100次登录，上次登录为2014-10-1。</div>
			</div>
			<br /> <br />
		</div>
		<div class="xm9">
			
			<div class="alert">
				<h4>系统介绍</h4>
				<p class="text-gray padding-top">系统介绍、系统介绍、系统介绍、系统介绍、系统介绍、
					系统介绍、系统介绍、系统介绍、系统介绍、系统介绍、</p>
				<a target="_blank" class="button border-main icon-file" href="">
					使用教程</a>
			</div>
			<div class="panel">
				<div class="panel-head">
					<strong>系统信息</strong>
				</div>
				<table class="table">
					<tr>
						<th colspan="2">服务器信息</th>
						<th colspan="2">系统信息</th>
					</tr>
					<tr>
						<td width="110" align="right">操作系统：</td>
						<td>Windows Server 2003</td>
						<td width="90" align="right">系统开发：</td>
						<td><a href="#" target="_blank">Spring MVC框架</a></td>
					</tr>
					<tr>
						<td align="right">Web服务器：</td>
						<td>Tomcat</td>
						<td align="right">主页：</td>
						<td><a href="" target="_blank">www.baidu.com</a></td>
					</tr>
					<tr>
						<td align="right">程序语言：</td>
						<td>JAVA</td>
						<td align="right">演示：</td>
						<td><a href="" target="_blank">www.baidu.com</a></td>
					</tr>
					<tr>
						<td align="right">数据库：</td>
						<td>MySQL</td>
						<td align="right">联系方式：</td>
						<td><a href="#" target="_blank">201916085</a>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<br />

</body>
</html>