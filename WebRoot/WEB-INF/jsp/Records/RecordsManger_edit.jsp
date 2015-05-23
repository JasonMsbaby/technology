<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>成果编辑</title>
<jsp:include page="../Other/dropin.jsp"></jsp:include>
<script type="text/javascript" src="style/js/record_edit.js"></script>
<link rel="stylesheet" href="style/datepicker/css/jquery-ui.css"
	type="text/css" />
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
	<form action="RecordsManger_edit_submit.html">
		<input type="hidden" id="reStudentMoneny" name="reStudentMoneny"
			value="${record.reStudentMoneny}" /> <input type="hidden"
			id="reTeacherMoney" name="reTeacherMoney"
			value="${record.reTeacherMoney}" /> <input name="reId" type="hidden"
			value="${record.reId}" />
		<div class="panel">
			<div class="panel-head icon-bookmark">成果编辑</div>
			<div class="panel-body">
				<div class="form-group">
					<div class="label">
						<label for="username">作品名称</label>
					</div>
					<div class="field">
						<input name=reProjectName class="input" type="text"
							value="${record.reProjectName}" />
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label for="username">获奖比赛</label>
					</div>

					<div class="field">
						<select onchange="gradeChange()" id="competition_name"
							name="reCompetition.cID" class="input">
							<c:forEach items="${competition}" var="li">
								<c:choose>
									<c:when test="${li.cName==record.reCompetition.cName}">
										<option selected="selected" value="${li.cID}">${li.cName}</option>
									</c:when>
									<c:otherwise>
										<option value="${li.cID}">${li.cName}</option>
									</c:otherwise>
								</c:choose>

							</c:forEach>
						</select>
					</div>
				</div>

				<div class="form-group">
					<div class="label">
						<label for="username">获奖等级</label>
					</div>
					<div class="field">
						<select onchange="gradeChange()" id="competition_grade"
							name="reGrade" class="input">
							<c:choose>
								<c:when test="${record.reGrade=='特等奖'}">
									<option selected="selected">特等奖</option>
								</c:when>
								<c:otherwise>
									<option>特等奖</option>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${record.reGrade=='一等奖'}">
									<option selected="selected">一等奖</option>
								</c:when>
								<c:otherwise>
									<option>一等奖</option>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${record.reGrade=='二等奖'}">
									<option selected="selected">二等奖</option>
								</c:when>
								<c:otherwise>
									<option>二等奖</option>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${record.reGrade=='三等奖'}">
									<option selected="selected">三等奖</option>
								</c:when>
								<c:otherwise>
									<option>三等奖</option>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${record.reGrade=='鼓励奖'}">
									<option selected="selected">鼓励奖</option>
								</c:when>
								<c:otherwise>
									<option>鼓励奖</option>
								</c:otherwise>
							</c:choose>
						</select> &nbsp;<label id="record_money_teacher"></label>&nbsp;
						&nbsp;&nbsp;&nbsp;<label id="record_money_student"></label>
					</div>
				</div>

				<div class="form-group">
					<div class="label">
						<label for="username">参赛时间</label>
					</div>
					<div class="field">
						<input name="reJoinTime" placeholder="点击选择日期" class="input"
							type="text" readonly="readonly" id="date"
							value="${record.reJoinTime}" />

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
							<tr id="tr_-1">
								<th>学号</th>
								<th>姓名</th>
								<th>性别</th>
								<th>年级</th>
								<th>院系</th>
								<th>专业</th>
								<th>身份证</th>
								<th>银行卡</th>
								<th>联系方式</th>
								<th>分配金额</th>
								<th>操作</th>
							</tr>

						</table>
					</div>
				</div>

				<div class="form-group">
					<div class="label">
						<label for="username">指导教师</label> <span
							class="icon-plus-square-o"></span> <input
							class="button button-small  dialogs " type="button"
							data-toggle="click" data-target="#mydialog2" data-mask="1"
							data-width="50%" value="添加指导教师信息" />



					</div>
					<div class="field">
						<table class="table" id="table_info2">
							<tr id="tr_-1">
								<th>职工号</th>
								<th>姓名</th>
								<th>性别</th>
								<th>院系</th>
								<th>身份证</th>
								<th>银行卡</th>
								<th>联系方式</th>
								<th>金额分配</th>
								<th>操作</th>
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
						<div class="field">
							<select id="m_sex" class="input">
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
								placeholder="分配金额" />
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
					<span class="close rotate-hover"></span> <strong>添加指导教师信息</strong>
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
						<div class="field">
							<select id="t_sex" class="input">
								<option value="男">男</option>
								<option value="女">女</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<div class="field form-inline">
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
								placeholder="分配金额" />
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