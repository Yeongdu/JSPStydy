<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>부서(Dept) 테이블</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color="blue">
			<h3>DEPT 테이블 메인 페이지</h3>
		<hr width="50%" color="blue">
		<br><br>
		<%-- request.getContextPath() : 현재 프로젝트명을 문자열로 반환해주는 메서드 --%>
		<a href="<%=request.getContextPath() %>/select">[전체부서목록]</a>
	</div>

</body>
</html>