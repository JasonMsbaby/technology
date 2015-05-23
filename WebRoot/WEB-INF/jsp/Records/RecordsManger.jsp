<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<jsp:include page="../Other/dropin.jsp"></jsp:include>
<title>成果管理</title>
<script type="text/javascript">
$(function(){
	//跳转到
	$("#jump").change(function(){
		var select=$('#jump option:selected').val();
		window.location.href="RecordsManger.html?page="+select;
	});
});
</script>
</head>

<body onload="document.body.scrollTop='0px'">
	<div class="panel">
		<div class="panel-head icon-bookmark">
			成果管理
		</div>
		<div class="panel-body">
			<table class="table table-hover">
			<tr>
					<th>编号</th>
					<th>获奖比赛</th>
					<th>作品名称</th>
					<th>获奖等级</th>
					<th>参赛时间</th>
					<th>提交人</th>
					<th>院级审核状态</th>
					<th>教务处审核状态</th>
					<th>填写时间</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${records}" var="li">
					<tr>
						<td>${li.reId}</td>
						<td>${li.reCompetition.cName}</td>
						<td>${li.reProjectName}</td>
						<td>${li.reGrade}</td>
						<td>${li.reJoinTime}</td>
						<td>${li.reWritePerson}</td>
						<td>
						<c:if test="${li.reCheckStatus==0||li.reCheckStatus==null}">待审核</c:if>
						<c:if test="${li.reCheckStatus==1}">审核通过</c:if>
						<c:if test="${li.reCheckStatus==-1}">审核未通过</c:if>
						</td>
						<td>
						<c:if test="${li.reCheckStatusAdmin==0||li.reCheckStatusAdmin==null}">待审核</c:if>
						<c:if test="${li.reCheckStatusAdmin==1}">审核通过</c:if>
						<c:if test="${li.reCheckStatusAdmin==-1}">审核未通过</c:if>
						</td>
						<td>${li.reWriteTime}</td>
						<td>
						<a class="button border-green button-little"
							href="RecordsManger_detail.html?id=${li.reId}">查看详情</a>
						<a class="button border-blue button-little"
							href="RecordsManger_edit.html?id=${li.reId}">编辑</a> <a
							class="button border-red button-little"
							href="RecordsManger_delete.html?id=${li.reId}"
							onclick="{if(confirm('确认删除?')){return true;}return false;}">删除</a>
						</td>
					</tr>
				
				</c:forEach>
			</table>
		</div>
		<!-- 分页 -->
		<div class="panel-foot text-center">
			<c:choose>
				<c:when test="${currentPage==1}">
					<a class="button border-blue button-little ">上一页</a>
				</c:when>
				<c:otherwise>
					<a class="button border-blue button-little"
						href="RecordsManger.html?page=${currentPage-1}">上一页</a>
				</c:otherwise>
			</c:choose>
			总共${counts}条，每页10条，总共
			<fmt:formatNumber type="number" value="${(counts-counts%10)/10+1}"
				maxFractionDigits="0" />
			页 ，当前第${currentPage}页
			<c:choose>
				<c:when test="${currentPage==((counts-counts%10)/10+1)}">
					<a class="button border-blue button-little disabled">下一页</a>
				</c:when>
				<c:otherwise>
					<a class="button border-blue button-little"
						href="RecordsManger.html?page=${currentPage+1}">下一页</a>
				</c:otherwise>
			</c:choose>
			跳转到 <select id="jump">
				<c:forEach begin="1" end="${(counts-counts%10)/10+1}" var="li">
					<option>${li}</option>
				</c:forEach>
			</select> 页
		</div>
	</div>
</body>
</html>