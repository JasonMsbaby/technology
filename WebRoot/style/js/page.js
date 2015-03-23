$(function(){
	//跳转到
	$("#jump").change(function(){
		var select=$('#jump option:selected').val();
		window.location.href="roleManger.html?page="+select;
	});
});