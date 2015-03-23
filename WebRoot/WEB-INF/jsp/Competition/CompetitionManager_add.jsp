<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title>添加用户</title>
<link rel="stylesheet" href="style/pintuer/css/pintuer.css">
<script src="style/pintuer/js/jquery.js"></script>
<script src="style/pintuer/js/pintuer.js"></script>
<script src="style/pintuer/js/respond.js"></script>
<script src="style/js/page.js"></script>
<link type="image/x-icon" href="http://www.pintuer.com/favicon.ico"
	rel="shortcut icon" />
<link href="http://www.pintuer.com/favicon.ico" rel="bookmark icon" />
</head>
<body>
${info }
	<div class="panel">
  		<div class="panel-head icon-plus">
  			添加管理
  			<a style="margin-left: 5px;color:blue;font-size: 10px;"href="competitionManager_add.html">刷新</a>
  		</div>
  		<div class="panel-body">
	  		<form action="competitionManager_add_submit.html" method="post">
				<div class="form-group">
					<div class="label">
						<label class="icon-asterisk"> 比赛名称</label>
					</div>
					<div class="field">
						<input type="text" class="input" id="cName" name="cName"
							size="50" placeholder="如：团体赛" data-validate="required:必填" />
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label class="icon-bar-chart-o"> 比赛级别</label>
					</div>
					<div class="field">
						<select name="cLevel" class="input" id="bsjb" data-validate="required:请选择比赛级别" >
							
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label class="icon-bank (alias)"> 比赛类型</label>
					</div>
					<div class="field">
						<select name="cType" class="input" id="bslx" data-validate="required:请选择比赛类型">
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label class="icon-briefcase"> 主办单位</label>
					</div>
					<div class="field">
						<input name="cOrganize" type="text" class="input"  size="50"  />
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label class="icon-bank (alias)"> 承办单位</label>
					</div>
					<div class="field">
						<input name="cOrganization" type="text" class="input" id="rLevel" size="50" />
						
					</div>
				</div>
				<div class="form-group">
					<div class="field">
						<input style="width:100%;" class="button bg-main" onclick="return check()" type="submit" value="提交添加" />
					</div>
				</div>
			</form>
		</div>
 	</div>
</body>
<script>
//获取比赛级别
$(function(){
	var url="rewardSetting_getrLevelDistinct.html";
	$.get(url,function(date){
		var strs="<option value=''>请选择</option>";
		date = $.trim(date);
		if(date.indexOf("#")>0){
			var str = date.split("#");
			for(var i=0;i<str.length-1 ;i++ ){
				strs +="<option value='"+str[i]+"'>"+str[i]+"</option>"
			}
		}
		$("#bsjb").html(strs);
	})
});

//根据比赛级别获取比赛类型
$(function(){
	$("#bsjb").change(function(){
		var va = $("#bsjb").val();
		if(va==""){ return; }
		var url = "rewardSetting_getrLevelDistinct.html?rType="+va;
		var strs="<option value=''>请选择</option>";
		$.get(url,function(date){
			date = date.substring(0,date.length-1); 
			date = $.trim(date);
			var str = date.split("#");
			
			for(var i=0;i<str.length ;i++ ){
				strs += "<option value='"+str[i]+"'>"+str[i]+"</option>"
			}
			$("#bslx").html(strs);
		});	
	})
});
</script>
</html>
