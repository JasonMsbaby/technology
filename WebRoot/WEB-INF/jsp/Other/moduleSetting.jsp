<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>模块设置</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="style/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	$(function() {
		//alert("asdfsdf");
	});
</script>
</head>
<body style="height: 2000px;background-color: gray;">
	【模块设置】===========》状态：${status}<br><br>
	<form action="moduleSettingInit.html">
		<input name="name" type="text" />
		<input type="submit" value="初始化模块"  />
	</form>
</body>
</html>
