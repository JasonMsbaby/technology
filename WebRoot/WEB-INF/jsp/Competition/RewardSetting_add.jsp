<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<jsp:include page="../Other/dropin.jsp"></jsp:include>
<title>增加项目奖励-页面设置</title>

</head>

<body >
	${info}
	<div class="panel">
		<div class="panel-head icon-plus">增加项目奖励</div>
		<div class="panel-body">

			<form action="rewardSetting_add_submit.html" method="post">
				<!-- <div class="form-group">
					<div class="label">
						<label class="icon-share-alt"> 比赛类型</label>
					</div>
					<div class="field">
						<input type="text" class="input" id="rLevel" name="rType"
							size="10" placeholder="如：团体赛" data-validate="required:必填" />
					</div>
				</div> -->
				<div class="form-group">
					<div class="label">
						<label class="icon-signal"> 比赛级别</label>
					</div>
					<div class="field">
						<select class="input" id="rLevel" name="rLevel">
							<c:forEach items="${grade}" var="li">
								<option value="${li}">${li}</option>
							</c:forEach>
						</select>
						<!-- <input type="text" class="input" id="rLevel" name="rLevel"
							size="10" placeholder="如：国家级A类" data-validate="required:必填" /> -->
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label class="icon-thumbs-o-up"> 获奖等级</label>
					</div>
					<div class="field">
					
						<select class="input" id="rGrade" name="rGrade">
							<c:forEach items="${order}" var="li">
								<option value="${li}">${li}</option>
							</c:forEach>
						</select>
						
						<!-- <input type="text" class="input" id="rGrade" name="rGrade"
							size="10" placeholder="如：一等奖" data-validate="required:必填" /> -->
					</div>
				</div>
				<div class="line-big">
					<div class="x6 field form-group">
						<label class="icon-cny (alias)"> 教师奖励奖金</label>
						<div class="input-group">
							<input type="text" class="input" id="rTeacher_q"
								name="rTeacher_q" size="10" placeholder="如：1000"
								data-validate="plusdouble:奖金只能填写数字" /> <span class="addon">元</span>
						</div>
						<br />
					</div>
					<div class="x6 field form-group">
						<label class="icon-star-o" for="username"> 教师奖励课时</label>
						<div class="input-group">
							<input type="text" class="input" id="rTeacher_k"
								name="rTeacher_k" size="10" placeholder="如：50"
								data-validate="number:课时只能填写数字" /><span class="addon">课时</span>
						</div>
						<br />
					</div>
				</div>
				<div class="line-big">
					<div class="x6 field form-group">
						<label class="icon-cny (alias)" for="username"> 学生奖励奖金</label>
						<div class="input-group">
							<input type="text" class="input" id="rStudent_q"
								name="rStudent_q" size="10" placeholder="如：1000"
								data-validate="plusdouble:奖金只能填写数字" /><span class="addon">元</span>
						</div>
						<br />
					</div>
					<div class="x6 field form-group">
						<label class="icon-star-o" for="username">奖励奖励学分</label>
						<div class="input-group">
							<input type="text" class="input" id="rStudent_f"
								name="rStudent_f" size="10" placeholder="如：5"
								data-validate="plusdouble:学分只能填写数字" /><span class="addon">学分</span>
						</div>
						<br />
					</div>
				</div>
				<input style="width:100%;" class="button bg-main"
					onclick="return check()" type="submit" value="提交添加" />
			</form>
		</div>
	</div>
</body>
</html>