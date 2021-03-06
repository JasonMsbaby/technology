<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="../Other/dropin.jsp"></jsp:include>
<title>科研管理系统-后台管理</title>
</head>
<script type="text/javascript">
	$(function() {
		var myDate = new Date();
		var years = myDate.getFullYear();
		for ( var i = 2001; i <= years; i++) {
			$(".years").append("<option value='"+i+"'>" + i + "年</option>");
		}
	});
</script>
<body>
	<div style="background: #ffffff" class="tab">
		<div class="tab-head">
			<strong>导出报表</strong> <span class="tab-more"> </span>
			<ul class="tab-nav">
				<li class="active"><a href="#tab-1">竞赛级别认定、承办单位汇总表</a>
				</li>
				<li><a href="#tab-2">竞赛类别等级、奖励规定一览表</a>
				</li>
				<li><a href="#tab-3">学生奖励发放表(学生)</a>
				</li>
				<li><a href="#tab-4">竞赛国家级和省级奖励统计表(教师)</a>
				</li>
			</ul>
		</div>
		<div style="height: 100%" class="tab-body">
			<div class="tab-panel active" id="tab-1">
				<a class="button bg-main radius-none" href="exportExcel1.html">导出德州学院大学生科技文化竞赛级别认定、承办单位汇总表</a>
			</div>
			<div class="tab-panel" id="tab-2">
				<a class="button bg-main radius-none" href="exportExcel2.html">导出德州学院大学生科技文化竞赛类别等级、奖励规定一览表</a>
			</div>
			<div class="tab-panel" id="tab-3">
				<form method="post" action="exportExcel3.html" class="form-inline">
					<div class="form-group">
						<div class="label">
							<label for="username">获奖年份</label>
						</div>
						<div class="field">
							<select name="years" class="input years">
							</select>
						</div>
					</div>
					<div class="form-group">
						<div class="label">
							<label for="password">获奖级别</label>
						</div>
						<div class="field">
							<select name="grade" class="input">
								<c:forEach items="${grade}" var="li">
									<option value="${li}">${li}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-button">
						<button class="button bg-main radius-none">导出学生奖励发放表（学生）</button>
					</div>
				</form>

			</div>
			<div class="tab-panel" id="tab-4">
				<form method="post" action="exportExcel4.html" class="form-inline">
					<div class="form-group">
						<div class="label">
							<label for="username">获奖年份</label>
						</div>
						<div class="field">
							<select name="years" class="input years">
							</select>
						</div>
					</div>
					<div class="form-button">
						<button class="button bg-main radius-none">导出表德州学院教师指导学生获得大学生科技文化竞赛国家级和省级奖励统计表(教师)</button>
					</div>
				</form>

			</div>
		</div>
	</div>
</body>
</html>
