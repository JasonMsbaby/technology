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
<title>添加院系</title>
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
		<form action="">
			<div class="panel-head icon-bookmark">添加院系</div>
			<div class="panel-body">
				<input id="ssid" type="hidden" name="sId" value="" /> <label
					class="icon-user" style="float:left;">院系名</label> <input
					type="text" class="input" name="sName" size="10" placeholder="院系名" /><br />
				<input class="button" type="button" onclick="school_add(this)"
					value="添加" />

				<script type="text/javascript">
					function school_add(id) {
						var sName = $(id).prev().prev().val();
						if (sName == "") {
							alert("请填入院系名称");
						} else {
							$
									.post(
											"schoolManger_add_submit.html",
											{
												"sName" : sName
											},
											function(data) {
												if (data == 0) {
													alert("添加失败！，已存在同名院系！");
												} else {
													window.location.href = "major_add.html?sId="
															+ data;
													//alert(data);
												}
											});
						}

					}
				</script>
</html>