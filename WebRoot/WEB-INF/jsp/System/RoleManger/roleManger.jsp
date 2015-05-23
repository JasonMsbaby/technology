<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<jsp:include page="../../Other/dropin.jsp"></jsp:include>
<title>角色管理</title>
</head>

<body onload="document.body.scrollTop='0px';">
	<div class="panel">
		<div class="panel-head icon-bookmark">
			角色管理 <a style="margin-left: 5px;color:blue;font-size: 10px;"
				href="roleManger.html">刷新</a> <a style="float: right;"
				class="button border-blue button-little" href="roleManger_add.html">添加角色</a>
		</div>
		<div class="panel-body">
			<table class="table table-hover">
				<tr>
					<th>编号</th>
					<th>角色名称</th>
					<th>角色级别</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${roles}" var="li">
					<tr>
						<td>${li.rId}</td>
						<td>${li.rName}</td>
						<td>${li.rLevel}</td>
						<td><a class="button border-blue button-little"
							href="roleManger_edit.html?id=${li.rId}">编辑</a> <a
							class="button border-red button-little"
							href="roleManger_delete.html?rId=${li.rId}"
							onclick="return confirm('删除角色？')">删除</a></td>
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
						style="cursor: pointer;"
						href="roleManger.html?page=${currentPage-1}">上一页</a>
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
						style="cursor: pointer;"
						href="roleManger.html?page=${currentPage+1}">下一页</a>
				</c:otherwise>
			</c:choose>
			跳转到 <select id="jump">
				<c:forEach begin="1" end="${(counts-counts%10)/10+1}" var="li">
					<option>${li}</option>
				</c:forEach>
			</select> 页
		</div>
	</div>
</body>
</html>