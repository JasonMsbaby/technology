//admin.js

$(function(){	
	$(".nav_one").first().attr("class","active");	
	$(".nav li").click(function(){
		$(".nav li").attr("class","");
		$(this).attr("class","active");
		$("#adressOne").html($(this).children().html());
	});	
	$(".nav_two").click(function(){
		$("#adressTwo").html($(this).children().html());
	});
	
	
});

function iFrameHeight() {
	var ifm = document.getElementById("iframepage");
	var subWeb = document.frames ? document.frames["iframepage"].document
			: ifm.contentDocument;
	if (ifm != null && subWeb != null) {
		ifm.height = subWeb.body.scrollHeight;
	}
}