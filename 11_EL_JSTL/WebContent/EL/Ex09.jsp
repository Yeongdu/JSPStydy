<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");

// 	String userId = request.getParameter("id").trim();
// 	String userName = request.getParameter("name").trim();
// 	int userAge = Integer.parseInt(request.getParameter("age").trim());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<!-- <h2>JSP 표현식을 이용해 파라미터 값을 출력하는 방법</h2> -->
		<h2>표현언어(EL)을 이용해 파라미터 값을 출력하는 방법</h2>
		<table border="1" cellspacing="0" width="300">
			<tr>
				<th>아이디</th>
<%-- 					<td><%=userId %></td> --%>
				<td>${param.id }</td>
			</tr>

			<tr>
				<th>이름</th>
<%-- 					<td><%=userName %></td> --%>
				<td>${param.name }</td>
			</tr>

			<tr>
				<th>나이</th>
<%-- 					<td><%=userAge %></td> --%>
				<td>${param.age }</td>
			</tr>
		</table>
	</div>
</body>
</html>