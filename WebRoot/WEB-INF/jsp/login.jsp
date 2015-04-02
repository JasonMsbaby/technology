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
<title>登录-后台管理</title>
<link rel="stylesheet" href="style/pintuer/css/pintuer.css">
<link rel="stylesheet" href="style/pintuer/css/admin.css">
<script src="style/pintuer/js/jquery.js"></script>
<script src="style/pintuer/js/pintuer.js"></script>
<script src="style/pintuer/js/respond.js"></script>
<script src="style/pintuer/js/admin.js"></script>
<link type="image/x-icon" href="http://www.pintuer.com/favicon.ico"
	rel="shortcut icon" />
<link href="http://www.pintuer.com/favicon.ico" rel="bookmark icon" />
</head>
<body>
	<div class="container">
		<div class="line">
			<div class="xs6 xm4 xs3-move xm4-move">
				<br />
				<br />
				<div class="media media-y">
					<a href="#" target="_blank"> <!-- <img src="style/images/login.jpg" class="radius" alt="后台管理系统" /></a> -->
						<label
						style="font-size: xx-large;font-family:'微软雅黑';font-weight: 700;">科研管理系统</label>
					</a>
				</div>
				<br />
				<br />
				<form action="login_submit.html" method="post">
					<div class="panel">
						<div class="panel-head">
							<strong>登录后台管理系统</strong>&nbsp;&nbsp;&nbsp;&nbsp; <strong
								style="color:red">${info}</strong>
						</div>
						<div class="panel-body" style="padding:30px;">
							<div class="form-group">
								<div class="field field-icon-right">
									<input type="text" class="input" name="uName"
										placeholder="登录账号"
										data-validate="required:请填写账号,length#>=2:账号长度不符合要求" /> <span
										class="icon icon-user"></span>
								</div>
							</div>
							<div class="form-group">
								<div class="field field-icon-right">
									<input type="password" class="input" name="uPwd"
										placeholder="登录密码"
										data-validate="required:请填写密码,length#>=2:密码长度不符合要求" /> <span
										class="icon icon-key"></span>
								</div>
							</div>
							<div class="form-group">
								<div class="field">
									<input name="code2" type="text" class="input" placeholder="填写右侧的验证码"
										data-validate="required:请填写右侧的验证码" /> 
										<img onclick="this.src='getCode.html'" src="getCode.html" width="80" height="32"
										class="passcode" />
								</div>
							</div>
						</div>
						<div class="panel-foot text-center">
							<input type="submit" class="button button-block bg-main text-big"
								value="立即登录后台" />
						</div>
					</div>
				</form>

			</div>
		</div>
	</div>
</body>
</html>