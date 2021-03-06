<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<jsp:include page="../../Other/dropin.jsp"></jsp:include>
<title>角色编辑</title>
<link rel="stylesheet" href="style/css/permission.css">
<script src="style/js/permission.js"></script>
</head>

<body>
	<div class="panel">
		<form action="roleManger_edit_submit.html" method="post">
			<div class="panel-head icon-bookmark">角色编辑</div>
			<div class="panel-body">
				<div class="form-group">
					<div class="label">
						<label class="icon-user" for="username">名称修改</label>
					</div>
					<div class="field">
						<input type="hidden" value="${role.rId}" name="rId" /> <input
							type="text" class="input" name="rName" size="30"
							value="${role.rName}" /> <input id="permission" type="hidden"
							value="${role.rPermission}" name="rPermission" /> 
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label class="icon-user" for="username">级别修改</label>
					</div>
					<div class="field">
						【当前角色：${role.rLevel}】 &nbsp;&nbsp;&nbsp; <input type="radio"
							id="leve1" name="rLevel" value="校级" /><label for="leve1">校级</label>
						<input type="radio" id="leve2" name="rLevel" value="院级" /><label
							for="leve2">院级</label> <input type="radio" id="leve3"
							name="rLevel" value="教师" /><label for="leve3">教师</label>
					</div>
				</div>
				<div class="panel">
					<div class="panel-head icon-list">
						权限修改
						<div style="float: right;">
							<input id="allCheck" type="checkbox" value="全选" /><label
								for="allCheck">全选</label>
						</div>
					</div>
					<div id="permission_panel" class="panel-body">
						<ul class="list-group">
							<c:forEach items="${permissions}" var="li1">
								<c:if test="${li1.pType==0}">
									<li class="icon-folder-open-o">${li1.pContent}<a
										style="margin-left: 10px;" class="icon-minus-square-o nav1"></a>
										<ul>
											<c:forEach items="${permissions}" var="li2">
												<c:if test="${li2.pType==li1.pId}">
													<li class="icon-folder-open-o">${li2.pContent} <a
														style="margin-left: 10px;"
														class="icon-minus-square-o nav2"></a> <c:choose>
															<c:when test="${role.rPermission.contains(li2.pId)}">
																<input class="check2_${li2.pId}" value="${li2.pId}"
																	style="float: right;" checked="checked" type="checkbox" />
															</c:when>
															<c:otherwise>
																<input class="check2_${li2.pId}" value="${li2.pId}"
																	style="float: right;" type="checkbox" />
															</c:otherwise>
														</c:choose>
														<ul>
															<c:forEach items="${permissions}" var="li3">
																<c:if test="${li3.pType==li2.pId}">
																	<li class="icon-folder-o">${li3.pContent} <c:choose>
																			<c:when test="${role.rPermission.contains(li3.pId)}">
																				<input class="check3_${li3.pType}"
																					value="${li3.pId}" style="float: right;"
																					checked="checked" type="checkbox" />
																			</c:when>
																			<c:otherwise>
																				<input class="check3_${li3.pType}"
																					value="${li3.pId}" style="float: right;"
																					type="checkbox" />
																			</c:otherwise>
																		</c:choose>
																	</li>
																</c:if>
															</c:forEach>
														</ul></li>
												</c:if>
											</c:forEach>
										</ul></li>
								</c:if>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
			<div class="panel-foot">
				<input style="width:100%;" id="btn_submit" class="button bg-main"
					type="button" value="提交编辑" />
			</div>
		</form>
	</div>
</body>
</html>