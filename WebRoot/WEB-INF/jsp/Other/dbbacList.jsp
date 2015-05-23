<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>欢迎页</title>
<jsp:include page="../Other/dropin.jsp"></jsp:include>
</head>

<body style="background-color: white;">
	<a class="button" href="dbbacup.html">备份数据库</a>
	<a class="button" href="dbbacList.html">刷新列表</a>
	<br />
	<br />
	<table class="table table-hover">
				<tr>
					<th>文件名</th>
					<th>文件大小</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${file}" var="fil">
					<tr>
						<td>${fil.getName()}</td>
						<td>${fil.length()} B</td>
						<td>
							<a  class="button border-blue button-little"
							href="DB_load.html?fName=${fil.getName()}"
							onclick="{if(confirm('恢复数据？')){this.innerHTML='正在恢复。。。';return true;}return false;}">恢复</a>
						<a
							class="button border-red button-little"
							href="DB_delete.html?fName=${fil.getName()}"
							onclick="return confirm('删除文件？')">删除</a>
						</td>
					</tr>
				</c:forEach>
			</table>
</body>
</html>