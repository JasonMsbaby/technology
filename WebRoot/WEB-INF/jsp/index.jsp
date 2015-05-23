<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title>科研管理系统-后台管理</title>
<link rel="stylesheet" href="style/pintuer/css/pintuer.css">
<link rel="stylesheet" href="style/pintuer/css/admin.css">
<script src="style/pintuer/js/jquery.js"></script>
<script src="style/js/news.js"></script>
<script src="style/pintuer/js/pintuer.js"></script>
<script src="style/pintuer/js/respond.js"></script>
<script src="style/pintuer/js/admin.js"></script>
<link type="image/x-icon" href="http://www.pintuer.com/favicon.ico"
	rel="shortcut icon" />
<link href="http://www.pintuer.com/favicon.ico" rel="bookmark icon" />
<script type="text/javascript">
	function SetWinHeight(obj) {
		var win = obj;
		if (document.getElementById) {
			if (win && !window.opera) {
				if (win.contentDocument
						&& win.contentDocument.body.offsetHeight)
					win.height = win.contentDocument.body.offsetHeight;
				else if (win.Document && win.Document.body.scrollHeight)
					win.height = win.Document.body.scrollHeight;
			}
		}
	}
</script>
</head>

<body>
	<div class="lefter">
		<br> <label
			style="font-size: x-large;font-weight: 800;font-family:'微软雅黑'">科研管理系统</label>
	</div>
	<div class="righter nav-navicon" id="admin-nav">
		<div class="mainer">
			<div class="admin-navbar">
				<span class="float-right"> <a
					class="button button-little bg-main" href="index.html"
					target="_self">前台首页</a> <a class="button button-little bg-yellow"
					href="exit.html">注销登录</a> </span>
				<ul class="nav nav-inline admin-nav">
					<li class="active"><a href="index.html" class="icon-home"> 首页
					</a> <c:forEach items="${list}" var="li1" varStatus="status">
							<c:if test="${li1.pType==0&&li1.pShow==1}">
								<li><a href="#" class="${li1.pIcon}"> ${li1.pContent}</a>
									<ul>
										<c:forEach items="${list}" var="li2">
											<c:if test="${li2.pType==li1.pId&&li2.pShow==1}">
												<li style="border-bottom:1px solid #e4e4e4;" class="nav_two"><a
													class="icon-caret-right" target="iframepage"
													href="${li2.pLink}.html"> ${li2.pContent}</a>
											</c:if>
										</c:forEach>
									</ul></li>
							</c:if>
						</c:forEach>
				</ul>
			</div>
			<div class="admin-bread">
				<input id="role" type="hidden"
					value="${sessionScope.currentUser.uRole.rLevel}" /> <span>您好，【${sessionScope.currentUser.uRole.rName}】${sessionScope.currentUser.uName}，欢迎您的光临。</span>
				<ul class="bread">
					<li><a id="adressOne" href="#"
						class="icon-external-link-square">首页</a></li>
					<li id="adressTwo" href="#" class="icon-external-link">后台首页</li>
					<a id="news_2" target="iframepage" style="color:red;"></a>

				</ul>
			</div>
		</div>
	</div>

	<div class="admin">
		<iframe width="100%" align="center" height="200" id="iframepage" name="iframepage"
			onload="Javascript:SetWinHeight(this)" frameborder="0" scrolling="no"
			src="welcome.html"></iframe>
	</div>
</body>
</html>