$(function() {
	// 加载日期选择控件
	$("#date").datepicker();
	// 填充入学年份
	var myDate = new Date();
	var years = myDate.getFullYear();
	for ( var i = 2001; i <= years; i++) {
		$("#grade").append("<option>" + i + "级</option>");
	}
	// 初始化时获取奖励信息
	gradeChange();

});
/**
 * 奖项等级改变时获取相应的教师奖励以及学生奖励的信息
 */
function gradeChange() {
	var competitionID = $("#competition_name").val();
	var competitionGrade = $("#competition_grade").val();
	$.post("getRewardByCompetition.html", {
		"id" : competitionID,
		"grade" : competitionGrade
	}, function(data) {
		if (data == "") {
			$("#record_money_teacher").html("没有该奖项的奖励信息");
			$("#record_money_student").html("");
		} else {
			var stu = data.split(",")[0];
			var tch = data.split(",")[1];
			$("#record_money_teacher").html(
					"<label style='font-weight:700;font-size:large;'>教师奖励</label>："
							+ stu.split(":")[0] + "元/队 " + stu.split(":")[1]
							+ "课时/人");
			$("#record_money_student").html(
					"<label style='font-weight:700;font-size:large;'>学生奖励</label>："
							+ tch.split(":")[0] + "元/队 " + tch.split(":")[1]
							+ "学分/人");
		}
	});
}
// 添加学生信息
function btn_add() {
	var index = $("#index").val();
	var num = $(".dialog-win .dialog .dialog-body #m_num").val();
	var name = $(".dialog-win .dialog .dialog-body #m_name").val();
	var sex1 = $(".dialog-win .dialog .dialog-body #sex1").val();
	var grade = $(".dialog-win .dialog .dialog-body #grade option:selected")
			.text();
	var school = $(".dialog-win .dialog .dialog-body #school option:selected")
			.text();
	var schoolId = $(".dialog-win .dialog .dialog-body #school option:selected")
			.val();
	var major = $(".dialog-win .dialog .dialog-body #major option:selected")
			.text();
	var majorId = $(".dialog-win .dialog .dialog-body #major option:selected")
			.val();
	var idCard = $(".dialog-win .dialog .dialog-body #m_idCard").val();
	var bank = $(".dialog-win .dialog .dialog-body #m_bank").val();
	var phone = $(".dialog-win .dialog .dialog-body #m_phone").val();
	var money = $(".dialog-win .dialog .dialog-body #m_money").val();
	var str = "<tr>";
	str += "<td><input class='input_add' name='reStudentInfo[" + index
			+ "].sId' type='text' readonly value='" + num + "' /></td>";
	str += "<td><input class='input_add' name='reStudentInfo[" + index
			+ "].sName' type='text' readonly value='" + name + "' /></td>";
	str += "<td><input class='input_add' name='reStudentInfo[" + index
			+ "].sSex' type='text' readonly value='" + sex1 + "' /></td>";
	str += "<td><input class='input_add' name='reStudentInfo[" + index
			+ "].sGrade' type='text' readonly value='" + grade + "' /></td>";
	str += "<input class='input_add' name='reStudentInfo[" + index
			+ "].sSchool.sId' type='hidden' readonly value='" + schoolId
			+ "' />";
	str += "<input class='input_add' name='reStudentInfo[" + index
			+ "].sMajor.mId' type='hidden' readonly value='" + majorId + "' />";
	str += "<td><input class='input_add'  type='text' readonly value='"
			+ school + "' /></td>";
	str += "<td><input class='input_add'  type='text' readonly value='" + major
			+ "' /></td>";
	str += "<td><input class='input_add' name='reStudentInfo[" + index
			+ "].sIDCard' type='text' readonly value='" + idCard + "' /></td>";
	str += "<td><input class='input_add' name='reStudentInfo[" + index
			+ "].sIDBank' type='text' readonly value='" + bank + "' /></td>";
	str += "<td><input class='input_add' name='reStudentInfo[" + index
			+ "].sPhone' type='text' readonly value='" + phone + "' /></td>";
	str += "<td><input class='input_add' name='stu_reward[" + index
			+ "].sPhone' type='text' readonly value='" + money + "' /></td>";
	str += "</tr>";
	$("#index").attr("value", parseInt(index) + 1);
	$("#table_info").append(str);
	// 处理学生奖金分配
	$("#reStudentMoneny").attr("value",
			$("#reStudentMoneny").val() + "," + num + ":" + money);
	// 移除弹出层
	$(".dialog-win").remove();
	$(".dialog-mask").remove();
	// 动态设置iframe的高度
	var iframe = window.parent.document.getElementById("iframepage");
	$(iframe).css("height", $(iframe).height() + 40 + "px");
}

// 获取教师信息
function btn_add2() {
	var index = $("#index2").val();
	var num = $(".dialog-win .dialog .dialog-body #t_num").val();
	var name = $(".dialog-win .dialog .dialog-body #t_name").val();
	var sex2 = $(".dialog-win .dialog .dialog-body #sex2").val();
	var school = $(".dialog-win .dialog .dialog-body #t_school option:selected")
			.text();
	var schoolId = $(
			".dialog-win .dialog .dialog-body #t_school option:selected").val();
	var idCard = $(".dialog-win .dialog .dialog-body #t_idCard").val();
	var bank = $(".dialog-win .dialog .dialog-body #t_bank").val();
	var phone = $(".dialog-win .dialog .dialog-body #t_phone").val();
	var money = $(".dialog-win .dialog .dialog-body #t_money").val();
	var str = "<tr>";
	str += "<td><input class='input_add' name='reTeacherInfo[" + index
			+ "].tId' type='text' readonly value='" + num + "' /></td>";
	str += "<td><input class='input_add' name='reTeacherInfo[" + index
			+ "].tName' type='text' readonly value='" + name + "' /></td>";
	str += "<td><input class='input_add' name='reTeacherInfo[" + index
			+ "].tSex' type='text' readonly value='" + sex2 + "' /></td>";
	str += "<input class='input_add' name='reTeacherInfo[" + index
			+ "].tSchool.sId' type='hidden' readonly value='" + schoolId
			+ "' />";
	str += "<td><input class='input_add'  type='text' readonly value='"
			+ school + "' /></td>";
	str += "<td><input class='input_add' name='reTeacherInfo[" + index
			+ "].tIdcard' type='text' readonly value='" + idCard + "' /></td>";
	str += "<td><input class='input_add' name='reTeacherInfo[" + index
			+ "].tBankNum' type='text' readonly value='" + bank + "' /></td>";
	str += "<td><input class='input_add' name='reTeacherInfo[" + index
			+ "].tPhone' type='text' readonly value='" + phone + "' /></td>";
	str += "<td><input class='input_add' name='tch_reward[" + index
			+ "]' type='text' readonly value='" + money + "' /></td>";
	str += "</tr>";
	// 处理教师奖金分配
	$("#reTeacherMoney").attr("value",
			$("#reTeacherMoney").val() + "," + num + ":" + money);
	$("#table_info2").append(str);
	$("#index2").attr("value", parseInt(index) + 1);
	// 移除弹出层
	$(".dialog-win").remove();
	$(".dialog-mask").remove();
	// 动态设置iframe的高度
	var iframe = window.parent.document.getElementById("iframepage");
	$(iframe).css("height", $(iframe).height() + 40 + "px");
}
// 动态获取专业
function school_change(id) {
	$.post("getMajorBySchool.html?id=" + id, "", function(data) {
		var da = data.split(",");
		var res = "";
		for ( var i = 0; i < da.length; i++) {
			res += "<option>" + da[i] + "</option>";
		}
		$(".dialog-win .dialog .dialog-body #major").html(res);
	}, "text");
}

