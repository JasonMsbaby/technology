$(function() {
	initStudentInfo();
	initTeacherInfo();
	// 加载日期选择控件
	$("#date").datepicker();
	gradeChange();
	// 填充入学年份
	var myDate = new Date();
	var years = myDate.getFullYear();
	for ( var i = 2001; i <= years; i++) {
		$("#grade").append("<option>" + i + "级</option>");
	}

});
// 添加学生信息
function btn_add() {
	var num = $(".dialog-win .dialog .dialog-body #m_num").val();
	var name = $(".dialog-win .dialog .dialog-body #m_name").val();
	var grade = $(".dialog-win .dialog .dialog-body #grade option:selected")
			.text();
	var sex = $(".dialog-win .dialog .dialog-body #m_sex option:selected")
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
	var str = "<tr class='tr_common' id='tr_0'>";
	str += "<td><input class='input_add' name='reStudentInfo[0].sId' type='text' readonly value='"
			+ num + "' /></td>";
	str += "<td><input class='input_add' name='reStudentInfo[0].sName' type='text' readonly value='"
			+ name + "' /></td>";
	str += "<td><input class='input_add' name='reStudentInfo[0].sSex' type='text' readonly value='"
			+ sex + "' /></td>";
	str += "<td><input class='input_add' name='reStudentInfo[0].sGrade' type='text' readonly value='"
			+ grade + "' /></td>";
	str += "<input class='input_add' name='reStudentInfo[0].sSchool.sId' type='hidden' readonly value='"
			+ schoolId + "' />";
	str += "<input class='input_add' name='reStudentInfo[0].sMajor.mId' type='hidden' readonly value='"
			+ majorId + "' />";
	str += "<td><input class='input_add'  type='text' readonly value='"
			+ school + "' /></td>";
	str += "<td><input class='input_add'  type='text' readonly value='" + major
			+ "' /></td>";
	str += "<td><input class='input_add' name='reStudentInfo[0].sIDCard' type='text' readonly value='"
			+ idCard + "' /></td>";
	str += "<td><input class='input_add' name='reStudentInfo[0].sIDBank' type='text' readonly value='"
			+ bank + "' /></td>";
	str += "<td><input class='input_add' name='reStudentInfo[0].sPhone' type='text' readonly value='"
			+ phone + "' /></td>";
	str += "<td><input class='input_add' name='stu_money[0]' type='text' readonly value='"
			+ money + "' /></td>";
	str += "<td><a class='button border-red button-little' style='cursor: default;'  onclick='removeStudent(this)' >删除</a>";
	str += "</tr>";
	$("#table_info").append(str);
	// 移除弹出层
	$(".dialog-win").remove();
	$(".dialog-mask").remove();
	// 处理学生奖金分配
	$("#reStudentMoneny").attr("value",
			$("#reStudentMoneny").val() + "," + num + ":" + money);
	order1();
	// 动态设置iframe的高度
	var iframe = window.parent.document.getElementById("iframepage");
	$(iframe).css("height", $(iframe).height() + 70 + "px");
}
// 添加教师信息
function btn_add2() {
	var num = $(".dialog-win .dialog .dialog-body #t_num").val();
	var name = $(".dialog-win .dialog .dialog-body #t_name").val();
	var sex = $(".dialog-win .dialog .dialog-body #t_sex option:selected")
			.text();
	var school = $(".dialog-win .dialog .dialog-body #t_school option:selected")
			.text();
	var schoolId = $(
			".dialog-win .dialog .dialog-body #t_school option:selected").val();
	var idCard = $(".dialog-win .dialog .dialog-body #t_idCard").val();
	var bank = $(".dialog-win .dialog .dialog-body #t_bank").val();
	var phone = $(".dialog-win .dialog .dialog-body #t_phone").val();
	var money = $(".dialog-win .dialog .dialog-body #t_money").val();

	var str = "<tr class='tr_common' id='tr_0'>";
	str += "<td><input class='input_add' name='reTeacherInfo[0].tId' type='text' readonly value='"
			+ num + "' /></td>";
	str += "<td><input class='input_add' name='reTeacherInfo[0].tName' type='text' readonly value='"
			+ name + "' /></td>";
	str += "<td><input class='input_add' name='reTeacherInfo[0].tSex' type='text' readonly value='"
			+ sex + "' /></td>";
	str += "<input class='input_add' name='reTeacherInfo[0].tSchool.sId' type='hidden' readonly value='"
			+ schoolId + "' />";
	str += "<td><input class='input_add'  type='text' readonly value='"
			+ school + "' /></td>";
	str += "<td><input class='input_add' name='reTeacherInfo[0].tIdcard' type='text' readonly value='"
			+ idCard + "' /></td>";
	str += "<td><input class='input_add' name='reTeacherInfo[0].tBankNum' type='text' readonly value='"
			+ bank + "' /></td>";
	str += "<td><input class='input_add' name='reTeacherInfo[0].tPhone' type='text' readonly value='"
			+ phone + "' /></td>";
	str += "<td><input class='input_add' name='tch_money[0]' type='text' readonly value='"
			+ money + "' /></td>";
	str += "<td><a class='button border-red button-little' style='cursor: default;'  onclick='removeTeacher(this)' >删除</a>";
	str += "</tr>";

	$("#table_info2").append(str);
	// 移除弹出层
	$(".dialog-win").remove();
	$(".dialog-mask").remove();
	// 处理教师奖金分配
	$("#reTeacherMoney").attr("value",
			$("#reTeacherMoney").val() + "," + num + ":" + money);
	order2();
	// 动态设置iframe的高度
	var iframe = window.parent.document.getElementById("iframepage");
	$(iframe).css("height", $(iframe).height() + 70 + "px");

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
// 删除某个学生的信息
function removeStudent(id) {
	distributeStuMoney(id);
	$(id).parent().parent().remove();
	order1();
}
// 删除某个教师的信息
function removeTeacher(id) {
	distributeTchMoney(id);
	$(id).parent().parent().remove();
	order2();
}
// 删除学生时重新计算学生分配的奖金
function distributeStuMoney(id){
	var num=$(id).parent().parent().first().children().children().val();
	var values=$(id).parent().prev().children().val();
	var match=","+num+":"+values;
	$("#reStudentMoneny").attr("value",$("#reStudentMoneny").val().replace(match,""));
}
//删除教师时重新计算教师分配的奖金
function distributeTchMoney(id){
	var num=$(id).parent().parent().first().children().children().val();
	var values=$(id).parent().prev().children().val();
	var match=","+num+":"+values;
	$("#reTeacherMoney").attr("value",$("#reTeacherMoney").val().replace(match,""));
}
// 学生重新将顺序进行排列
function order1() {
	var str = "<tr id='tr_ - 1'><th>学号</th><th>姓名</th><th>性别</th><th>年级</th><th>院系</th><th>专业</th><th>身份证</th><th>银行卡</th><th>联系方式</th><th>分配金额</th><th>操作</th></tr>";
	$("#table_info").find("tr").each(
			function(index) {
				if (index != 0) {
					var index2 = parseInt(index) - 1;
					var oldContent = $(this).html();
					var newContent = oldContent.replace(
							/reStudentInfo\[(\d+)\]/g, "reStudentInfo["
									+ index2 + "]");
					newContent = newContent.replace(/stu_money\[(\d+)\]/g,
							"stu_money[" + index2 + "]");
					str += "<tr class='tr_common' id='tr_" + index2 + "'>"
							+ newContent + "</tr>";
				}
			});
	$("#table_info").html(str);
}
// 重新排列教师的信息
function order2() {
	var str = "<tr id='tr_-1'><th>职工号</th><th>姓名</th><th>性别</th><th>院系</th><th>身份证</th><th>银行卡</th><th>联系方式</th><th>金额分配</th><th>操作</th></tr>";
	$("#table_info2").find("tr").each(
			function(index) {
				if (index != 0) {
					var index2 = parseInt(index) - 1;
					var oldContent = $(this).html();
					var newContent = oldContent.replace(
							/reTeacherInfo\[(\d+)\]/g, "reTeacherInfo["
									+ index2 + "]");
					newContent = newContent.replace(/tch_money\[(\d+)\]/g,
							"tch_money[" + index2 + "]");
					str += "<tr class='tr_common' id='tr_" + index2 + "'>"
							+ newContent + "</tr>";
				}
			});
	$("#table_info2").html(str);
}
// 初始化学生信息
function initStudentInfo() {
	$
			.post(
					"RecordsManger_detail_getStudentInfoById.html",
					{
						"id" : getQueryStringByName("id")
					},
					function(data) {
						var obj = eval(data);
						// alert(data);
						var str = "";
						$
								.each(
										obj,
										function(index, ele) {
											str += "<tr class='tr_common' id='tr_"
													+ index + "'>";
											str += "<td><input class='input_add' name='reStudentInfo["
													+ index
													+ "].sId' type='text' readonly value='"
													+ ele["sId"] + "' /></td>";
											str += "<td><input class='input_add' name='reStudentInfo["
													+ index
													+ "].sName' type='text' readonly value='"
													+ ele["sName"]
													+ "' /></td>";
											str += "<td><input class='input_add' name='reStudentInfo["
													+ index
													+ "].sSex' type='text' readonly value='"
													+ ele["sSex"] + "' /></td>";
											str += "<td><input class='input_add' name='reStudentInfo["
													+ index
													+ "].sGrade' type='text' readonly value='"
													+ ele["sGrade"]
													+ "' /></td>";
											str += "<input class='input_add' name='reStudentInfo["
													+ index
													+ "].sSchool.sId' type='hidden' readonly value='"
													+ ele["sSchool_Id"]
													+ "' />";
											str += "<input class='input_add' name='reStudentInfo["
													+ index
													+ "].sMajor.mId' type='hidden' readonly value='"
													+ ele["sMajor_Id"] + "' />";
											str += "<td><input class='input_add'  type='text' readonly value='"
													+ ele["sSchool"]
													+ "' /></td>";
											str += "<td><input class='input_add'  type='text' readonly value='"
													+ ele["sMajor"]
													+ "' /></td>";
											str += "<td><input class='input_add' name='reStudentInfo["
													+ index
													+ "].sIDCard' type='text' readonly value='"
													+ ele["sIDCard"]
													+ "' /></td>";
											str += "<td><input class='input_add' name='reStudentInfo["
													+ index
													+ "].sIDBank' type='text' readonly value='"
													+ ele["sIDBank"]
													+ "' /></td>";
											str += "<td><input class='input_add' name='reStudentInfo["
													+ index
													+ "].sPhone' type='text' readonly value='"
													+ ele["sPhone"]
													+ "' /></td>";
											str += "<td><input class='input_add' name='stu_money["
													+ index
													+ "]' type='text' readonly value='"
													+ ele["sMoney"]
													+ "' /></td>";
											str += "<td><a  class='button border-red button-little' style='cursor: default;'  onclick='removeStudent(this)' >删除</a>";
											str += "</tr>";
											// 动态设置iframe的高度
											var iframe = window.parent.document
													.getElementById("iframepage");
											$(iframe).css(
													"height",
													$(iframe).height() + 70
															+ "px");

										});
						$("#table_info").append(str);
					});
}
/**
 * 初始化教师信息
 */
function initTeacherInfo() {
	$
			.post(
					"RecordsManger_detail_getTeacherInfoById.html",
					{
						"id" : getQueryStringByName("id")
					},
					function(data) {
						var obj = eval(data);
						// alert(data);
						var str = "";
						$
								.each(
										obj,
										function(index, ele) {
											str += "<tr class='tr_common' id='tr_"
													+ index + "'>";
											str += "<td><input class='input_add' name='reTeacherInfo["
													+ index
													+ "].tId' type='text' readonly value='"
													+ ele["tId"] + "' /></td>";
											str += "<td><input class='input_add' name='reTeacherInfo["
													+ index
													+ "].tName' type='text' readonly value='"
													+ ele["tName"]
													+ "' /></td>";
											str += "<td><input class='input_add' name='reTeacherInfo["
													+ index
													+ "].tSex' type='text' readonly value='"
													+ ele["tSex"] + "' /></td>";
											str += "<input class='input_add' name='reTeacherInfo["
													+ index
													+ "].tSchool.sId' type='hidden' readonly value='"
													+ ele["tSchool_Id"]
													+ "' />";
											str += "<td><input class='input_add'  type='text' readonly value='"
													+ ele["tSchool"]
													+ "' /></td>";
											str += "<td><input class='input_add' name='reTeacherInfo["
													+ index
													+ "].tIdcard' type='text' readonly value='"
													+ ele["tIdcard"]
													+ "' /></td>";
											str += "<td><input class='input_add' name='reTeacherInfo["
													+ index
													+ "].tBankNum' type='text' readonly value='"
													+ ele["tBankNum"]
													+ "' /></td>";
											str += "<td><input class='input_add' name='reTeacherInfo["
													+ index
													+ "].tPhone' type='text' readonly value='"
													+ ele["tPhone"]
													+ "' /></td>";
											str += "<td><input class='input_add' name='tch_reward["
													+ index
													+ "]' type='text' readonly value='"
													+ ele["tMoney"]
													+ "' /></td>";
											str += "<td><a  class='button border-red button-little' style='cursor: default;'  onclick='removeTeacher(this)' >删除</a>";
											str += "</tr>";
											// 动态设置iframe的高度
											var iframe = window.parent.document
													.getElementById("iframepage");
											$(iframe).css(
													"height",
													$(iframe).height() + 70
															+ "px");

										});
						$("#table_info2").append(str);
					});
}
// 获取id参数
function getQueryStringByName(name) {
	var result = location.search.match(new RegExp(
			"[\?\&]" + name + "=([^\&]+)", "i"));
	if (result == null || result.length < 1) {
		return "";
	}
	return result[1];

}
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
			// alert(data);
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
