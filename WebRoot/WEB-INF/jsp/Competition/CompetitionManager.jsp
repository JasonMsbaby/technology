<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<jsp:include page="../Other/dropin.jsp"></jsp:include>
<title>项目管理</title>
</head>
<body style="min-height: 400px">
	<div class="panel">
		<div class="panel-head icon-bookmark">
			项目管理 <a style="margin-left: 5px;color:blue;font-size: 10px;"
				href="competitionManager.html">刷新</a>

			<div style="float: right;">
				<a class="button border-green button-little icon-download"
					href="Excel/德州学院大学生科技文化竞赛级别认定、承办单位汇总表 -模板.xls"> 下载模板</a>
				<button
					class="button border-main button-little icon-plus-square dialogs"
					data-mask="1" data-width="50%" data-target="#mydialog2"
					data-toggle="click">批量导入</button>
				<a class="button border-blue button-little icon-plus"
					href="competitionManager_add.html"> 增加项目</a>
			</div>
		</div>
		<div class="panel-body">
			<table class="table table-hover">
				<c:if test="${fn:length(competitionList)<1}">
					<tr>
						<td>还没有任何项目建立！<a class="text-blue"
							href=competitionManager_add.html>点此添加项目</a>
						</td>
					</tr>
				</c:if>
				<c:if test="${fn:length(competitionList)>=1}">
					<tr>
						<th>序号</th>
						<th>比赛名称</th>
						<th>比赛水平</th>
						<!-- <th>比赛类型</th> -->
						<th>主办单位</th>
						<th>承办单位</th>
						<th>操作</th>
					</tr>
					<c:forEach items="${competitionList}" var="competition"
						varStatus="Status">
						<tr>
							<td>${Status.index+1}</td>
							<td>${competition.cName}</td>
							<td>${competition.cLevel}</td>
							<%-- <td>${competition.cType}</td> --%>
							<td>${competition.cOrganize}</td>
							<td>${competition.cOrganization}</td>
							<td><a
								href="competitionManager_edit.html?id=${competition.cID }"><button
										class="button border-blue button-little icon-pencil-square-o ">
										编辑</button> </a>
								<button
									class="button border-dot button-little icon-times dialogs"
									data-mask="1" data-width="50%" data-target="#mydialog"
									data-toggle="click" data-target="#mydialog"
									onclick="record('1','${competition.cID}',this)">删除</button></td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</div>
		<div class="panel-foot">
			<div class="line-middle">
				<div class="x12">
					<div class="panel-foot text-center">
						<a class="button border-blue button-little"
							<c:if test="${(page)<=1}">
							disabled="disabled"
						</c:if>
							href="competitionManager.html?page=${page-1}">上一页</a>
						总共${count}条，每页8条，总共
						<fmt:formatNumber type="number" value="${(count-count%8)/8+1}"
							maxFractionDigits="0" />
						页 ，当前第${page}页 <a class="button border-blue button-little"
							<c:if test="${(page)>=((count-1)/8)}">
							disabled="disabled" 
						</c:if>
							href="competitionManager.html?page=${page+1}">下一页</a> 跳转到 <select
							id="jump">
							<c:forEach begin="1" end="${(count-count%8)/8+1}" var="li">
								<option>${li}</option>
							</c:forEach>
						</select> 页
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="mydialog">
		<div class="dialog">
			<div class="dialog-head">
				<span class="close rotate-hover" onclick="record('0','0')"></span> <strong>确定要删除？</strong>
			</div>
			<div class="dialog-body">您确定要删除这条信息吗？</div>
			<div class="dialog-foot">
				<button class="button dialog-close" onclick="record('0','0')">取消</button>
				<button class="button bg-green dialog-close" onclick="dele()">确认</button>
			</div>
		</div>
	</div>
	<div id="mydialog2">
		<div class="dialog">
			<div class="dialog-head">
				<span class="close rotate-hover"></span> <strong>批量导入</strong>
			</div>
			<div class="dialog-body">
				<form action="CompetionImportExcel.html"
					enctype="multipart/form-data" method="post">
					<div class="form-group">
						<div class="label">
							<label for="upfile">文件浏览</label>
						</div>
						<div class="field">
							<a class="button input-file" href="javascript:void(0);"> +
								请选择上传文件 <input size="100" name="file"
								data-validate="required:请选择文件,regexp#.+.(xls)$:只能上传xls格式文件"
								type="file" /> </a>
						</div>
					</div>
					<div class="form-button">
						<button class="button" onclick="mysubmit()" type="submit">提交</button>
						<div id="btn_wait" style="display: none;">
							<span class="icon-spinner rotate"></span>正在处理，请稍后...
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	${info}
</body>
<!-- 删除条目ajax -->
<script>
	function mysubmit() {
		$(".dialog-win .dialog .dialog-body #btn_wait").css("display","block");
	}
	function record(method, i, obj) {
		if (method == 1) {
			id = i;
			$this = $(obj);
		} else {
			id = null;
		}
	}

	function dele() {
		url = "competitionManager_delete.html?cID=" + id;
		$.get(url, function(date) {
			if ($.trim(date) == "success") {
				$this.parent().parent("tr").remove();
			} else {
				alert("删除失败！");
			}
		});
	}

	$(function() {
		//跳转到
		$("#jump").change(function() {
			var select = $('#jump option:selected').val();
			window.location.href = "competitionManager.html?page=" + select;
		});
	});
</script>
</html>
