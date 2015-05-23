<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>奖励设置</title>
<jsp:include page="../Other/dropin.jsp"></jsp:include>
</head>
<body>
	<div class="panel">
  		<div class="panel-head icon-bookmark">
  			奖励设置
  			<a style="margin-left: 5px;color:blue;font-size: 10px;"href="rewardSetting.html">刷新</a>
  			<a style="float: right;" class="button border-blue button-little icon-plus" href="rewardSetting_add.html"> 增加项目奖励</a>
  		</div>
  		<div class="panel-body">
			<table class="table table-hover">
				<c:if test="${fn:length(rewardList)<1}">
					<tr>
  						<td>还没有任何项目建立！<a class="text-blue" href="rewardSetting_add.html">点此添加项目奖励</a></td>
  					</tr>
				</c:if>
				<c:if test="${fn:length(rewardList)>=1}">
  				<tr>
  					<th>序号</th>
  					<!-- <th>比赛类型</th> -->
  					<th>比赛水平</th>
  					<th>获奖等级</th>
  					<th>老师奖励</th>
  					<th>学生奖励</th>
  					<th>操作</th>
  				</tr>
  				<c:forEach items="${rewardList}" var="reward" varStatus="Status">
  				<tr>
  					<td>${Status.index+1}</td>
  					<%-- <td>${reward.rType}</td> --%>
  					<td>${reward.rLevel}</td>
  					<td>${reward.rGrade}</td>
  					<td><label class="icon-cny (alias)"> 奖金</label>：${fn:split(reward.rTeacher, ':')[0]}元/队  <label class="icon-star-o" > 课时</label>：${fn:split(reward.rTeacher, ':')[1]}个/人</td>
  					<td><label class="icon-cny (alias)"> 奖金</label>：${fn:split(reward.rStudent, ':')[0]}元/队  <label class="icon-star-o" > 学分</label>：${fn:split(reward.rStudent, ':')[1]}分/人</td>
  					<td>
  						<a href="rewardSetting_edit.html?rId=${reward.rId}"><button class="button border-blue button-little icon-pencil-square-o " > 编辑</button></a>
						<button class="button border-dot button-little icon-times dialogs" data-mask="1" data-width="50%" data-target="#mydialog" data-toggle="click" data-target="#mydialog" onclick="record('1','${reward.rId}',this)"> 删除</button>
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
						 href="rewardSetting.html?page=${page-1}">上一页</a>
						总共${count}条，每页8条，总共
						<fmt:formatNumber type="number" value="${(count-count%8)/8+1}" maxFractionDigits="0" />
						页 ，当前第${page}页
						<a class="button border-blue button-little" 
						<c:if test="${(page)>=((count-1)/8)}">
							disabled="disabled" 
						</c:if>
						href="rewardSetting.html?page=${page+1}">下一页</a>
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
		url = "rewardSetting_delete.html?rId="+id;
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
			window.location.href="rewardSetting.html?page="+select;
		});
	});
</script>
</html>
