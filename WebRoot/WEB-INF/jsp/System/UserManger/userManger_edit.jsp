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
<title>用户管理</title>
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
		<div class="panel-head icon-bookmark">修改用户</div>
		<div class="panel-body">

			<form action="userManger_edit_submit.html">
				<input type="hidden" name="uId" value="${users.uId}" />
				<input type="hidden" name="rId" value="${users.uRole.rId}" />
				<input type="hidden" name="sId" value="${users.uSchool.sId}" />
				 <label
					class="icon-user" style="float:left;" for="username">用户名</label> <input
					type="text" class="input" name="uName" size="10" placeholder="用户名"
					value="${users.uName}" /><br /> <label class="icon-lock"
					for="username">密码</label> <input type="text" class="input"
					name="uPwd" size="10" placeholder="密码" value="${users.uPwd}" /><br />
					
					<c:if test="${users.uRole.rLevel>1}">
				<label class="icon-users" for="username">选择角色</label> <select
					name="rId" class="input">
					<option value="${users.uRole.rId}">${users.uRole.rName}</option>
					<c:forEach items="${roles}" var="li2">
						<c:choose>
							<c:when test="${user.uRole.rLevel>2}">
								<c:if test="${li2.rLevel==4&&li2.rId!=users.uRole.rId}">
									<option value="${li2.rId}">${li2.rName}</option>
								</c:if>
							</c:when>
							<c:otherwise>
								<c:if test="${li2.rId!=users.uRole.rId}">
									<option value="${li2.rId}">${li2.rName}</option>
								</c:if>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select><br /> <label class="icon-users" for="username">选择院系</label> <select
					name="sId" class="input">
					<option value="${users.uSchool.sId}">${users.uSchool.sName}</option>
					<c:forEach items="${schools}" var="li3">
						<c:choose>
							<c:when test="${user.uRole.rLevel>2}">
								<c:if test="${li3.sId==user.uSchool.sId}">
									<option value="${li3.sId}">${li3.sName}</option>
								</c:if>
							</c:when>
							<c:otherwise>
								<c:if test="${li3.sId!=users.uSchool.sId}">
									<option value="${li3.sId}">${li3.sName}</option>
								</c:if>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select><br /> </c:if><input id="btn_submit" style="width:100%;"
					class="button bg-main" type="submit" value="提交修改" />

			</form>
		</div>

	</div>
</body>
</html>