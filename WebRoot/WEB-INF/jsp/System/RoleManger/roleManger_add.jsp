<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>角色添加</title>
<jsp:include page="../../Other/dropin.jsp"></jsp:include>
<link rel="stylesheet" href="style/css/permission.css">
<script src="style/js/permission.js"></script>
</head>
<body>
	<form action="roleManger_add_submit.html">
		<div class="panel">
			<div class="panel-head icon-bookmark">角色添加</div>
			<div class="panel-body">
				<div class="form-group">
					<div class="label">
						<label class="icon-user" for="username">角色名称</label>
					</div>
					<div class="field">
						<input type="text" class="input" name="rName" size="30"
							placeholder="角色名称" />
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label class="icon-user">角色级别</label>
					</div>
					<div class="field">
						<input type="radio" id="leve1" checked="checked" name="rLevel" value="校级" /><label
							for="leve1">校级</label> <input type="radio" id="leve2"
							name="rLevel" value="院级" /><label for="leve2">院级</label> <input
							type="radio" id="leve3" name="rLevel" value="教师" /><label
							for="leve3">教师</label>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label class="icon-user" for="username">角色权限</label>
						<div style="float: right;">
							<input id="allCheck" type="checkbox" value="全选" /><label
								for="allCheck">全选</label>
						</div>
					</div>
					<input type="hidden" id="permission" name="rPermission" />
					<div class="field">
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
																		</c:choose></li>
																</c:if>
															</c:forEach>
														</ul>
													</li>
												</c:if>
											</c:forEach>
										</ul>
									</li>
								</c:if>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
			<div class="panel-foot">
				<input id="btn_submit" style="width:100%;" class="button bg-main"
					type="button" value="提交添加" />
			</div>
		</div>
	</form>
</body>
</html>