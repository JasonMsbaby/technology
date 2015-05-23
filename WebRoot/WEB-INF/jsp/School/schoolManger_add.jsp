<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>添加院系</title>
<jsp:include page="../Other/dropin.jsp"></jsp:include>
</head>

<body style="min-height: 400px">
	<div class="panel">
		<form action="">
			<div class="panel-head icon-bookmark">添加院系</div>
			<div class="panel-body">
				<input id="ssid" type="hidden" name="sId" value="" /> <label
					class="icon-user" style="float:left;">院系名</label> <input
					type="text" class="input" name="sName" size="10" placeholder="院系名" /><br />
				<input class="button bg-main" type="button" onclick="school_add(this)"
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
			</div>
		</form>
	</div>
</body>
</html>