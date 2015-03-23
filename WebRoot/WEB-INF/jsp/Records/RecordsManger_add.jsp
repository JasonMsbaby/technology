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
<title>成果录入</title>
<script type="text/javascript" src="style/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="style/js/record_add.js"></script>
<link rel="stylesheet" href="style/datepicker/css/jquery-ui.css"
	type="text/css" />
<script src="style/pintuer/js/pintuer.js"></script>
<link rel="stylesheet" href="style/pintuer/css/pintuer.css">
<script type="text/javascript"
	src="style/datepicker/js/jquery-ui-datepicker.js"></script>
<style type="text/css">
.model {
	margin-top: 10px;
}

.input_add {
	width: 100%;
	border: 0;
}

</style>
</head>
<body>
	<input type="hidden" value="0" id="index" />
	<input type="hidden" value="0" id="index2" />
	<form action="RecordsManger_add_submit.html">
	
	<input type="hidden" id="reStudentMoneny" name="reStudentMoneny" />
	<input type="hidden" id="reTeacherMoney" name="reTeacherMoney" />
		<div class="panel">
			<div class="panel-head icon-bookmark">成果录入 <a style="color:blue" href="#">刷新</a></div>
			<div class="panel-body">
				<div class="form-group">
					<div class="label">
						<label for="username">获奖比赛</label>
					</div>

					<div class="field">
						<select id="competition_name" name="reCompetition.cID"
							class="input">
							<c:forEach items="${competition}" var="li">
								<option value="${li.cID}">${li.cName}</option>
							</c:forEach>
						</select>
					</div>
				</div>

				<div class="form-group">
					<div class="label">
						<label for="username">获奖等级</label>
					</div>
					<div class="field form-inline">
						<select id="competition_grade" name="reGrade" class="input"
							style="width: 200px;" onchange="gradeChange()">
							<option>特等奖</option>
							<option>一等奖</option>
							<option>二等奖</option>
							<option>三等奖</option>
							<option>鼓励奖</option>
						</select> &nbsp; &nbsp;&nbsp;&nbsp;<label id="record_money_teacher"></label>&nbsp;
						&nbsp;&nbsp;&nbsp;<label id="record_money_student"></label>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label for="username">作品名称</label>
					</div>
					<div class="field">
						<input name=reProjectName class="input" type="text" /> 
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label for="username">参赛时间</label>
					</div>
					<div class="field">
						<input name="reJoinTime" placeholder="点击选择日期" class="input"
							type="text" readonly="readonly" id="date" />

					</div>
				</div>
				<br>
				<div class="form-group">
					<div class="label">
						<label for="username">获奖学生</label> <span
							class="icon-plus-square-o"></span> <input
							class="button button-small  dialogs " type="button"
							data-toggle="click" data-target="#mydialog" data-mask="1"
							data-width="50%" value="添加学生信息" />



					</div>
					<div class="field">
						<table class="table" id="table_info">
							<tr>
								<th>学号</th>
								<th>姓名</th>
								<th>性别</th>
								<th>年级</th>
								<th>院系</th>
								<th>专业</th>
								<th>身份证</th>
								<th>银行卡</th>
								<th>联系方式</th>
								<th>分配奖金</th>
							</tr>
							<tr>
							</tr>

						</table>
					</div>
				</div>

				<div class="form-group">
					<div class="label">
						<label for="username">指导老师</label> <span
							class="icon-plus-square-o"></span> <input
							class="button button-small  dialogs " type="button"
							data-toggle="click" data-target="#mydialog2" data-mask="1"
							data-width="50%" value="添加指导老师信息" />



					</div>
					<div class="field">
						<table class="table" id="table_info2">
							<tr>
								<th>职工号</th>
								<th>姓名</th>
								<th>性别</th>
								<th>院系</th>
								<th>身份证</th>
								<th>银行卡</th>
								<th>联系方式</th>
								<th>分配奖金</th>
							</tr>
							<tr>
							</tr>

						</table>
					</div>
				</div>

			</div>
			<div class="panel-foot">
				<input style="width:100%;" class="button bg-blue" type="submit"
					value="确认" />
			</div>
		</div>

		<div id="mydialog">
			<div class="dialog">
				<div class="dialog-head">
					<span class="close rotate-hover"></span> <strong>添加学生信息</strong>
				</div>
				<div class="dialog-body">
					<div class="form-group">
						<div class="field">
							<input type="text" class="input" id="m_num" size="20"
								placeholder="学号" />
						</div>
					</div>
					<div class="form-group">
						<div class="field">
							<input type="text" class="input" id="m_name" size="20"
								placeholder="姓名" />
						</div>
					</div>
					<div class="form-group">
						<div class="field ">
							<select id="sex1" class="input">
								<option value="男">男</option>
								<option value="女">女</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<div class="field">
							<select id="grade" class="input">

							</select>
						</div>
					</div>
					<div class="form-group">
						<div class="field form-inline">
							<select id="school"
								onchange="school_change(this.options[this.selectedIndex].value)"
								class="input">
								<c:forEach items="${schools}" var="li">
									<option value="${li.sId}">${li.sName}</option>
								</c:forEach>
							</select> <select id="major" class="input">
								<c:forEach items="${schools[0].sMajor}" var="li">
									<option value="${li.mId}">${li.mName}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<div class="field">
							<input type="text" class="input" id="m_idCard" size="20"
								placeholder="身份证" />
						</div>
					</div>
					<div class="form-group">
						<div class="field">
							<input type="text" class="input" id="m_bank" size="20"
								placeholder="银行卡" />
						</div>
					</div>
					<div class="form-group">
						<div class="field">
							<input type="text" class="input" id="m_phone" size="20"
								placeholder="联系方式" />
						</div>
					</div>
					<div class="form-group">
						<div class="field">
							<input type="text" class="input" id="m_money" size="20"
								placeholder="奖金/没有请填写 0"  />
						</div>
					</div>

				</div>
				<div class="dialog-foot">
					<button class="button dialog-close">取消</button>
					<input type="button" onclick="btn_add()" class="button bg-green"
						value="确认" />
				</div>
			</div>
		</div>

		<div id="mydialog2">
			<div class="dialog">
				<div class="dialog-head">
					<span class="close rotate-hover"></span> <strong>添加教师信息</strong>
				</div>
				<div class="dialog-body">
					<div class="form-group">
						<div class="field">
							<input type="text" class="input" id="t_num" size="20"
								placeholder="职工号" />
						</div>
					</div>
					<div class="form-group">
						<div class="field">
							<input type="text" class="input" id="t_name" size="20"
								placeholder="姓名" />
						</div>
					</div>

					<div class="form-group">
						<div class="field ">
							<select id="sex2" class="input">
								<option value="男">男</option>
								<option value="女">女</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<div class="field">
							<select id="t_school"
								onchange="school_change(this.options[this.selectedIndex].value)"
								class="input">
								<c:forEach items="${schools}" var="li">
									<option value="${li.sId}">${li.sName}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<div class="field">
							<input type="text" class="input" id="t_idCard" size="20"
								placeholder="身份证" />
						</div>
					</div>
					<div class="form-group">
						<div class="field">
							<input type="text" class="input" id="t_bank" size="20"
								placeholder="银行卡" />
						</div>
					</div>
					<div class="form-group">
						<div class="field">
							<input type="text" class="input" id="t_phone" size="20"
								placeholder="联系方式" />
						</div>
					</div>
					<div class="form-group">
						<div class="field">
							<input type="text" class="input" id="t_money" size="20"
								placeholder="奖金" />
						</div>
					</div>

				</div>
				<div class="dialog-foot">
					<button class="button dialog-close">取消</button>
					<input type="button" onclick="btn_add2()" class="button bg-green"
						value="确认" />
				</div>
			</div>
		</div>

	</form>
</body>
</html>