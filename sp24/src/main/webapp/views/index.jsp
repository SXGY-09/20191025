<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
</head>
<body>
	<h2>图书信息列表</h2>
	<div align="center">
		<table>
			<thead>
				<tr align="center">
					<th>封面</th>
					<th>书名</th>
					<th>作者</th>
					<th>价格</th>
				</tr>
			</thead>
			<tbody align="center">
				<c:forEach items="${requestScope.books}" var="book">
				<tr>
					<td><img src="images/${book.image}" height="60"/></td>
					<td>${book.title}</td>
					<td>${book.author}</td>
					<td>${book.price}></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>