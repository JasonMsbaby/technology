//消息动态处理提醒
$(function() {
	run();
	var interval;
	function run() {
		interval = setInterval(fun, "2000");
	}
	function fun() {
		var role = $("#role").val();
		$.getJSON("getWaitCheckNum.html", function(data) {
			$(data).each(function(i, da) {
				var role = da["role"];
				if (role == 2) {// 教务处管理员
					$(da["content"]).each(function(a,aa){
						var wait=aa["wait"];
						 if(wait>0){ // 有待审核消息
						 $("#news_2").html("您有"+wait+"条待审核的信息,点击前往处理"); 
						 $("#news_2").attr("href","RecordsCheck.html");
						 }else{
							 $("#news_2").html(""); 
						 }
						 
					});
				}else if(role==3){// 院级管理员
					
					$(da["content"]).each(function(b,bb){
						
						var wait=bb["wait"];
						var pass=bb["pass"];
						 if(wait>0){ // 有待审核消息
							 	$("#news_2").html("您有"+wait+"条待审核的信息,点击前往处理"); 
							 	$("#news_2").attr("href","RecordsCheck.html");
							 }
						 if(pass>0){
								 $("#news_2").html("您有"+pass+"条审核未通过的信息,点击前往处理"); 
								 $("#news_2").attr("href","RecordsCheck.html");
								 
							 }
						 if(wait==0&&pass==0)
						 	{
							$("#news_2").html(""); 
						 	}
						
					});
				}else{// 教师
					$(da["content"]).each(function(c,cc){
						var pass=cc["pass"];
						 if(pass>0){ // 有审核没有通过的消息
							 $("#news_2").html("您有"+pass+"条审核未通过的信息,点击前往处理"); 
							 $("#news_2").attr("href","RecordsManger.html");
							 }else{
								 $("#news_2").html(""); 
							 }
					});
				}
			});
			
		});

		
	}
	

});
