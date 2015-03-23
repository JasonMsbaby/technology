<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title>编辑项目奖励-页面设置</title>
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
		<div class="panel-head icon-edit (alias)"> 编辑项目奖励</div>
		<div class="panel-body">

			<form action="rewardSetting_edit_submit.html" method="post">
				<div class="form-group">
					<div class="label">
						<label class="icon-share-alt"> 比赛类型</label>
					</div>
					<div class="field">
						<input type="text" class="input" id="rLevel" name="rType"
							size="10" value="${rewardd.rType}" placeholder="如：团体赛" data-validate="required:必填" />
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label class="icon-signal"> 比赛水平</label>
					</div>
					<div class="field">
						<input type="text" class="input" id="rLevel" name="rLevel"
							size="10" value="${rewardd.rLevel}" placeholder="如：国家级A级" data-validate="required:必填" />
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label class="icon-thumbs-o-up"> 获奖等级</label>
					</div>
					<div class="field">
						<input type="text" class="input" id="rGrade" name="rGrade"
							size="10" value="${rewardd.rGrade }" placeholder="如：一等奖" data-validate="required:必填" />
					</div>
				</div>
				<div class="line-big">
					<div class="x6 field form-group">
						<label class="icon-cny (alias)"> 教师奖励奖金</label>
						<div class="input-group">
							<input type="text" class="input" id="rTeacher_q" value="${fn:split(rewardd.rTeacher, ':')[0]}"
								name="rTeacher_q" size="10" placeholder="如：1000"
								data-validate="plusdouble:奖金只能填写数字" /> <span class="addon">元</span>
						</div>
						<br />
					</div>
					<div class="x6 field form-group">
						<label class="icon-star-o" for="username"> 教师奖励课时</label>
						<div class="input-group">
							<input type="text" class="input" id="rTeacher_k" value="${fn:split(rewardd.rTeacher, ':')[1]}"
								name="rTeacher_k" size="10" placeholder="如：50"
								data-validate="number:课时只能填写数字"  /><span class="addon">课时</span>
						</div>
						<br />
					</div>
				</div>
				<div class="line-big">
					<div class="x6 field form-group">
						<label class="icon-cny (alias)" for="username"> 学生奖励奖金</label>
						<div class="input-group">
							<input type="text" class="input" id="rStudent_q" value="${fn:split(rewardd.rStudent, ':')[0]}"
								name="rStudent_q" size="10" placeholder="如：1000"
								data-validate="plusdouble:奖金只能填写数字" /><span class="addon">元</span>
						</div>
						<br />
					</div>
					<div class="x6 field form-group">
						<label class="icon-star-o" for="username">奖励奖励学分</label>
						<div class="input-group">
							<input type="text" class="input" id="rStudent_f" value="${fn:split(rewardd.rStudent, ':')[0]}"
								name="rStudent_f" size="10" placeholder="如：5"
								data-validate="plusdouble:学分只能填写数字" /><span class="addon">学分</span>
						</div>
						<br />
					</div>
				</div>
				<input type="hidden" value="${rewardd.rId }" name="rId" />
				<input style="width:100%;" class="button bg-main"
					onclick="return check()" type="submit" value="提交添加" />
			</form>
		</div>
	</div>
</body>
</html>