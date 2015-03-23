<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title>院系管理</title>
<link rel="stylesheet" href="style/pintuer/css/pintuer.css">
<script src="style/pintuer/js/jquery.js"></script>
<script src="style/pintuer/js/pintuer.js"></script>
<script src="style/pintuer/js/respond.js"></script>
<script src="style/js/page.js"></script>
<link type="image/x-icon" href="http://www.pintuer.com/favicon.ico"
	rel="shortcut icon" />
<link href="http://www.pintuer.com/favicon.ico" rel="bookmark icon" />
</head>

<body onload="document.body.scrollTop='0px'">
	<div class="panel">
		<div class="panel-head icon-bookmark">
			院系管理
			<a style="margin-left: 5px;color:blue;font-size: 10px;"
				href="schoolManger.html">刷新</a>
		</div>
		<div class="panel-body">
			<table class="table table-hover">
				<tr>
					<th>编号</th>
					<th>院系名称</th>
					<th>下设专业</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${school}" var="li">
					<tr>
						<td>${li.sId}</td>
						<td>${li.sName}</td>
						<td><a class="dialogs" data-toggle="click" data-target="#mydialog${li.sId}" data-mask="1"
							data-width="50%" style="color:#09c;cursor: pointer;">查看下设专业</a></td>
						<td>
						<a class="button border-blue button-little"
							href="schoolManger_edit.html?sId=${li.sId}">编辑</a> 
							<a
							class="button border-red button-little"
							href="schoolManger_delete.html?sId=${li.sId}"
							onclick="return confirm('确认删除？')">删除</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<!-- 分页 -->
		<div class="panel-foot text-center">
			<c:choose>
				<c:when test="${currentPage==1}">
					<a class="button border-blue button-little ">上一页</a>
				</c:when>
				<c:otherwise>
					<a class="button border-blue button-little"
						href="schoolMenger.html?page=${currentPage-1}">上一页</a>
				</c:otherwise>
			</c:choose>
			总共${counts}条，每页10条，总共
			<fmt:formatNumber type="number" value="${(counts-counts%10)/10+1}"
				maxFractionDigits="0" />
			页 ，当前第${currentPage}页
			<c:choose>
				<c:when test="${currentPage==((counts-counts%10)/10+1)}">
					<a class="button border-blue button-little disabled">下一页</a>
				</c:when>
				<c:otherwise>
					<a class="button border-blue button-little"
						href="schoolMenger.html?page=${currentPage+1}">下一页</a>
				</c:otherwise>
			</c:choose>
			跳转到 <select id="jump">
				<c:forEach begin="1" end="${(counts-counts%10)/10+1}" var="li">
					<option>${li}</option>
				</c:forEach>
			</select> 页
		</div>
	</div>
	
	
	
	
	
	<c:forEach items="${school}" var="li2">
	<div id="mydialog${li2.sId}" style="height:500px;">
			<div class="dialog">
				<div class="dialog-head">
					<span class="close rotate-hover"></span> <strong><font color="#09c">${li2.sName}</font>设有以下专业</strong>
				</div>
				<div class="dialog-body">
					<ul class="list-group">
						<c:forEach items="${major}" var="li3">
						<c:if test="${li3.mSchool.sId==li2.sId}">
							<li>${li3.mName}</li>
						</c:if>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		</c:forEach>
</body>
</html>