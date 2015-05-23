<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<jsp:include page="../../Other/dropin.jsp"></jsp:include>
<title>用户管理</title>
</head>
<body >
	<div class="panel">
		<div class="panel-head icon-bookmark">
			用户管理 <a style="margin-left: 5px;color:blue;font-size: 10px;"
				href="userManger.html">刷新</a> <a style="float: right;"
				class="button border-blue button-little" href="userManger_add.html">添加用户</a>
		</div>
		<div class="panel-body">
			<table class="table table-hover">
				<tr>
					<th>编号</th>
					<th>用户名</th>
					<th>用户密码</th>
					<th>用户角色</th>
					<th>角色级别</th>
					<th>用户院系</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${users}" var="li">
					<tr>
						<td>${li.uId}</td>
						<td>${li.uName}</td>
						<td>${li.uPwd}</td>
						<td>${li.uRole.rName}</td>
						<td>${li.uRole.rLevel}</td>
						<td>${li.uSchool.sName}</td>
						<td><a class="button border-blue button-little"
							href="userManger_edit.html?uId=${li.uId}">编辑</a> <a
							class="button border-red button-little"
							href="userManger_delete.html?uId=${li.uId}"
							onclick="return confirm('确认删除？')">删除</a></td>
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
						href="userManger.html?page=${currentPage-1}">上一页</a>
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
						href="userManger.html?page=${currentPage+1}">下一页</a>
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