<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>用户管理</title>
<jsp:include page="../../Other/dropin.jsp"></jsp:include>
</head>

<body>
	<div class="panel">
		<div class="panel-head icon-bookmark">添加用户</div>
		<div class="panel-body">
			<form action="userManger_add_submit.html" method="post">
				<label class="icon-user" style="float:left;" for="username">用户名</label>
				<input type="text" class="input" id="user" name="uName" size="10"
					placeholder="用户名" data-validate="required:请填写账号" /><br /> <label
					class="icon-key" for="username">密码</label> <input type="password"
					class="input" id="pwd" name="uPwd" size="10" placeholder="密码"
					data-validate="required:请填写密码" /><br /> <label class="icon-users">选择角色</label>
				<select name="rId" class="input">
					<c:forEach items="${roles}" var="li2">
						<option value="${li2.rId}">${li2.rName}</option>
					</c:forEach>
				</select><br /> <label class="icon-users">选择院系</label> <select name="sId"
					class="input">
					<option value="0">请选择</option>
					<c:forEach items="${schools}" var="li3">
						<option value="${li3.sId}">${li3.sName}</option>
					</c:forEach>
				</select><br /> <input style="width:100%;" class="button bg-main"
					type="submit" value="提交添加" />

			</form>
		</div>

	</div>
</body>
</html>