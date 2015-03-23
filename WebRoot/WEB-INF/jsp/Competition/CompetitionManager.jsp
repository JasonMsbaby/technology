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
<title>项目管理</title>
<link rel="stylesheet" href="style/pintuer/css/pintuer.css">
<script src="style/pintuer/js/jquery.js"></script>
<script src="style/pintuer/js/pintuer.js"></script>
<script src="style/pintuer/js/respond.js"></script>

<link type="image/x-icon" href="http://www.pintuer.com/favicon.ico"
	rel="shortcut icon" />
<link href="http://www.pintuer.com/favicon.ico" rel="bookmark icon" />
</head>
<body>
	<div class="panel">
  		<div class="panel-head icon-bookmark">
  			项目管理
  			<a style="margin-left: 5px;color:blue;font-size: 10px;"href="competitionManager.html">刷新</a>
  			<a style="float: right;" class="button border-blue button-little icon-plus" href="competitionManager_add.html"> 增加项目</a>
  		</div>
  		<div class="panel-body">
			<table class="table table-hover">
				<c:if test="${fn:length(competitionList)<1}">
					<tr>
  						<td>还没有任何项目建立！<a class="text-blue" href=competitionManager_add.html>点此添加项目</a></td>
  					</tr>
				</c:if>
				<c:if test="${fn:length(competitionList)>=1}">
  				<tr>
  					<th>序号</th>
  					<th>比赛名称</th>
  					<th>比赛水平</th>
  					<th>比赛类型</th>
  					<th>主办单位</th>
  					<th>承办单位</th>
  					<th>操作</th>
  				</tr>
  				<c:forEach items="${competitionList}" var="competition" varStatus="Status">
  				<tr>
  					<td>${Status.index+1}</td>
  					<td>${competition.cName}</td>
  					<td>${competition.cLevel}</td>
  					<td>${competition.cType}</td>
  					<td>${competition.cOrganize}</td>
  					<td>${competition.cOrganization}</td>
  					<td>
  						<a href="competitionManager_edit.html?id=${competition.cID }"><button class="button border-blue button-little icon-pencil-square-o " > 编辑</button></a>
						<button class="button border-dot button-little icon-times dialogs" data-mask="1" data-width="50%" data-target="#mydialog" data-toggle="click" data-target="#mydialog" onclick="record('1','${competition.cID}',this)"> 删除</button>
					</td>
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
						<fmt:formatNumber type="number" value="${(count-count%8)/8+1}" maxFractionDigits="0" />
						页 ，当前第${page}页
						<a class="button border-blue button-little" 
						<c:if test="${(page)>=((count-1)/8)}">
							disabled="disabled" 
						</c:if>
						href="competitionManager.html?page=${page+1}">下一页</a>
						跳转到 <select id="jump">
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
				<span class="close rotate-hover" onclick="record('0','0')"></span>
				<strong>确定要删除？</strong>
	    	</div>
    		<div class="dialog-body"> 您确定要删除这条信息吗？</div>
    		<div class="dialog-foot">
				<button class="button dialog-close" onclick="record('0','0')" >取消</button>
				<button class="button bg-green dialog-close" onclick="dele()" >确认</button>
    		</div>
		</div>
</div>
${info}
</body>
<!-- 删除条目ajax -->
<script>
	function record(method,i,obj){
		if(method==1){
			id=i;
			$this = $(obj);
		}else{
			id=null;
		}
	}
	
	function dele(){
		url = "competitionManager_delete.html?cID="+id;
		$.get(url,function(date){
			if($.trim(date)=="success"){
				$this.parent().parent("tr").remove();
			}else{
				alert("删除失败！");
			}
		});
	}
	
	$(function(){
		//跳转到
		$("#jump").change(function(){
			var select=$('#jump option:selected').val();
			window.location.href="competitionManager.html?page="+select;
		});
	});
</script>
</html>
