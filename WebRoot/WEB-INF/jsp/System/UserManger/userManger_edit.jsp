<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<jsp:include page="../../Other/dropin.jsp"></jsp:include>
<title>用户管理</title>
</head>

<body onload="document.body.scrollTop='0px'">
	<div class="panel">
		<div class="panel-head icon-bookmark">修改用户</div>
		<div class="panel-body">

			<form action="userManger_edit_submit.html">
				<input type="hidden" name="uId" value="${users.uId}" /> <input
					type="hidden" name="rId" value="${users.uRole.rId}" /> <input
					type="hidden" name="sId" value="${users.uSchool.sId}" /> <label
					class="icon-user" style="float:left;" for="username">用户名</label> <input
					type="text" class="input" name="uName" size="10" placeholder="用户名"
					value="${users.uName}" /><br /> <label class="icon-lock"
					for="username">密码</label> <input type="text" class="input"
					name="uPwd" size="10" placeholder="密码" value="${users.uPwd}" /><br />

				<label class="icon-users" for="username">选择角色</label> <select
					name="rId" class="input">
					<option value="${users.uRole.rId}">${users.uRole.rName}</option>
					<c:forEach items="${roles}" var="li2">
						<c:if test="${li2.rId!=users.uRole.rId}">
							<option value="${li2.rId}">${li2.rName}</option>
						</c:if>
					</c:forEach>
				</select><br /> <label class="icon-users" for="username">选择院系</label> <select
					name="sId" class="input">
					<option value="${users.uSchool.sId}">${users.uSchool.sName}</option>
					<c:forEach items="${schools}" var="li3">
								<c:if test="${li3.sId!=users.uSchool.sId}">
									<option value="${li3.sId}">${li3.sName}</option>
								</c:if>
					</c:forEach>
				</select><br /> <input id="btn_submit" style="width:100%;"
					class="button bg-main" type="submit" value="提交修改" />

			</form>
		</div>

	</div>
</body>
</html>