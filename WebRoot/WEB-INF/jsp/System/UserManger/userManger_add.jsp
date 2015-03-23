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
		<div class="panel-head icon-bookmark">添加用户</div>
		<div class="panel-body">

			<form action="userManger_add_submit.html">
				<label class="icon-user" style="float:left;" for="username">用户名</label>
				<input type="text" class="input" id="user" name="uName" size="10"
					placeholder="用户名"
					data-validate="required:请填写账号,length#>=5:账号长度不符合要求" /><br /> <label
					class="icon-key" for="username">密码</label> <input type="password"
					class="input" id="pwd" name="uPwd" size="10" placeholder="密码"
					data-validate="required:请填写密码,length#>=8:密码长度不符合要求" /><br /> <label
					class="icon-users" for="username">选择角色</label> <select name="rId"
					class="input">
					<c:forEach items="${roles}" var="li2">
						<option value="${li2.rId}">${li2.rName}</option>
					</c:forEach>
				</select><br /> <label class="icon-users" for="username">选择院系</label> <select
					name="sId" class="input">
					<c:forEach items="${schools}" var="li3">
						<c:choose>
							<c:when test="${user.uRole.rLevel>2}">
								<c:if test="${li3.sId==user.uSchool.sId}">
									<option value="${li3.sId}">${li3.sName}</option>
								</c:if>
							</c:when>
							<c:otherwise>
								<option value="${li3.sId}">${li3.sName}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select><br /> <input style="width:100%;" class="button bg-main"
					onclick="return check()" type="submit" value="提交添加" />

			</form>
		</div>

	</div>
</body>
<script>
	function check(){
		var user=$("#user").val();
		var pwd=$("#pwd").val();
		if(user.length<=5){
			alert("用户名长度不能低于6位！");
			return false;
		}else{
			if(pwd.length<=7){
			alert("密码长度不能少于8位");
			return false;
			}else{
				return true;
			}
		}
	}
</script>
</html>