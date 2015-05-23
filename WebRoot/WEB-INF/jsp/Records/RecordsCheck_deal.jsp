<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<jsp:include page="../Other/dropin.jsp"></jsp:include>
<title>审核处理</title>
<link rel="stylesheet" href="style/datepicker/css/jquery-ui.css"
	type="text/css" />
<style type="text/css">
.td_label {
	font-weight: 700;
	text-align: center;
	width: 20%;
}
</style>
<script type="text/javascript">
	$(function() {
		$("#btn_back").click(function() {
			var txt_back = $("#txt_back").val();
			var reId=$("#reId").val();
			if (txt_back == "") {
				alert("打回提交时，请注明审核意见。");
			}else{
				$.post("RecordsCheck_deal_defeat.html",{"id":reId,"backMsg":txt_back},function(data){
					if(data==0){
						alert("系统发生错误，驳回请求失败");
					}else{
						alert("系统已将记录打回，请等待对方修改后提交");
						window.location.href="RecordsCheck.html";
					}
				});
			}
		});
	});
</script>
</head>
<body>
	<input type="hidden" value="0" id="index" />
	<input id="reId" type="hidden" value="${detail.reId}" />
	<div class="panel">
		<div class="panel-head icon-bookmark">审核处理</div>
		<div class="panel-body">
			<table class="table table-bordered">
				<tr>
					<td class="td_label">获奖比赛</td>
					<td class="td_content">${detail.reCompetition.cName}</td>
				</tr>
				<tr>
					<td class="td_label">作品名称</td>
					<td class="td_content">${detail.reProjectName}</td>
				</tr>
				<tr>
					<td class="td_label">获奖级别</td>
					<td class="td_content">${detail.reGrade}</td>
				</tr>
				<tr>
					<td class="td_label">参赛时间</td>
					<td class="td_content">${detail.reJoinTime}</td>
				</tr>
				<tr>
					<td class="td_label">院系领导审核状态</td>
					<td class="td_content"><c:if
							test="${detail.reCheckStatus==0||detail.reCheckStatus==null}">待审核</c:if>
						<c:if test="${detail.reCheckStatus==1}">审核通过</c:if> <c:if
							test="${detail.reCheckStatus==-1}">审核未通过</c:if></td>
				</tr>
				<tr>
					<td class="td_label">院系审核意见</td>
					<td class="td_content">${detail.recheckSuggestion}</td>
				</tr>
				<tr>
					<td class="td_label">院系审核人</td>
					<td class="td_content">${detail.reCheckPerson}</td>
				</tr>
				<tr>
					<td class="td_label">教务处领导审核状态</td>
					<td class="td_content"><c:if
							test="${li.reCheckStatusAdmin==0||li.reCheckStatusAdmin==null}">待审核</c:if>
						<c:if test="${li.reCheckStatusAdmin==1}">审核通过</c:if> <c:if
							test="${li.reCheckStatusAdmin==-1}">审核未通过</c:if></td>
				</tr>
				<tr>
					<td class="td_label">教务处审核意见</td>
					<td class="td_content">${detail.recheckSuggestionAdmin}</td>
				</tr>
				<tr>
					<td class="td_label">教务处审核人</td>
					<td class="td_content">${detail.reCheckPersonAdmin}</td>
				</tr>
				<tr>
					<td class="td_label">资金发放状态</td>
					<td class="td_content"><c:choose>
							<c:when
								test="${detail.reGiveStatus==0||detail.reGiveStatus==null}">待发放</c:when>
							<c:otherwise>已发放</c:otherwise>
						</c:choose></td>
				</tr>
				<tr>
					<td class="td_label">提交人</td>
					<td class="td_content">${detail.reWritePerson}</td>

				</tr>
				<tr>
				<tr>
					<td class="td_label">参赛学生</td>
					<td class="td_content"><table class="table table-condensed"
							id="table_info">
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
								<th>奖励</th>
							</tr>
							<c:forEach items="${detail.reStudentInfo}" var="li">
								<tr>
									<td>${li.sId}</td>
									<td>${li.sName}</td>
									<td>${li.sSex}</td>
									<td>${li.sGrade}</td>
									<td>${li.sSchool.sName}</td>
									<td>${li.sMajor.mName}</td>
									<td>${li.sIDCard}</td>
									<td>${li.sIDBank}</td>
									<td>${li.sPhone}</td>
									<td>${stu_reward.get(li.sId)}元</td>
								</tr>
							</c:forEach>


						</table>
					</td>
				</tr>
				<tr>
					<td class="td_label">指导教师信息</td>
					<td class="td_content"><table class="table table-condensed"
							id="table_info2">
							<tr>
								<th>职工号</th>
								<th>姓名</th>
								<th>性别</th>
								<th>院系</th>
								<th>身份证</th>
								<th>银行卡</th>
								<th>联系方式</th>
								<th>奖励</th>
							</tr>
							<c:forEach items="${detail.reTeacherInfo}" var="li">
								<tr>
									<td>${li.tId}</td>
									<td>${li.tName}</td>
									<td>${li.tSex}</td>
									<td>${li.tSchool.sName}</td>
									<td>${li.tIdcard}</td>
									<td>${li.tBankNum}</td>
									<td>${li.tPhone}</td>
									<td>${tch_reward.get(li.tId)}元</td>
								</tr>
							</c:forEach>


						</table>
					</td>
				</tr>
			</table>

		</div>
		<div class="panel-foot">
			<input id="txt_back" type="text" class="input radius-none"
				placeholder="请在此填写审核意见/若审核通过此处可以省略" /> <a
				href="RecordsCheck_deal_pass.html?id=${detail.reId}"><button
					id="btn_success" style="margin-top:10px"
					class="button bg-main radius-none">通过</button>
			</a>
			<button id="btn_back" class="button bg-dot radius-none">打回</button>
		</div>
	</div>
</body>
</html>