<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>添加专业</title>
<jsp:include page="../Other/dropin.jsp"></jsp:include>
</head>

<body onload="document.body.scrollTop='0px'">
	<div class="panel">
		<form action="">
			<div class="panel-head icon-bookmark">【${school.sName}】添加专业</div>
			<div class="panel-body">
				<input id="ssid" type="hidden" name="sId" value="${sId}" />
				<input
					class="button button-small bg-main dialogs" data-toggle="click"
					data-target="#mydialog" data-mask="1" data-width="50%"
					type="button" value="添加专业" />
				<div class="form-group">
					<br />
					<div class="label">
						<label for="password">下设专业</label>
					</div>
					<div class="field">

						<c:forEach items="${school.sMajor}" var="li" varStatus="aa">
							<div class="form-group">
								<div class="input-inline clearfix">
									<input type="text"
										class="input" style="width:90%;" disabled="disabled" value="${li.mName}" /> 
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="panel-foot">
				<!-- 	<input style="width:100%;" class="button bg-main" type="submit"
					value="提交编辑" /> -->
			</div>


		</form>
		<div id="mydialog">
			<div class="dialog">
				<div class="dialog-head">
					<span class="close rotate-hover"></span> <strong>对话框标题</strong>
				</div>
				<div class="dialog-body">

					<div class="form-group">
						<div class="label">
							<label for="username">专业名称</label>
						</div>
						<div class="field">
							<input type="text" class="input" id="major_name"
								placeholder="请填写专业名称" />
						</div>
					</div>
				</div>
				<div class="dialog-foot">
					<button class="button dialog-close">取消</button>
					<button onclick="btn_ok()" class="button bg-green">确认</button>
				</div>
			</div>
		</div>
	</div>
</body>

<script type="text/javascript">
	function btn_ok() {
		var major_name = $(".dialog-win .dialog .dialog-body #major_name")
				.val();
		var sid = $("#ssid").val();
		$.post("major_add_submit.html", {
			"sid" : sid,
			"mName" : major_name
		}, function(data) {
			//移除弹出层
			$(".dialog-win").remove();
			$(".dialog-mask").remove();
			self.location.reload();
		});
	}
</script>
</html>