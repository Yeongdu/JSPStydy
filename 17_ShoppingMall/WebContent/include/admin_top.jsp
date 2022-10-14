<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">


#title{
float: right;
margin-right:  17.5%;
word-spacing: 10px;
clear: both;

}

a {
text-decoration: none;
}

td {
	text-align: center;
}

td a {
	display: block;
}

td a:hover {
	text-shadow: 1px 1px 2px gray;
}
</style>
</head>
<body>

	<div align="center">
		<hr width="65%">
			<h2>관리자 페이지</h2>
			
			<div id="title">
				<a href="#">${adminName }님 환영합니다.</a>
				<a href="<%=request.getContextPath() %>/admin_logout.do">로그아웃</a>
			</div>
			
			<br>
			<hr width="65%">
			<br>
			
			<table width="65%" heigh="13%">
				<tr>
					<td>
						<a href="<%=request.getContextPath() %>/admin_main.do">관리자 홈</a>
					</td>

					<td>
						<a href="<%=request.getContextPath() %>/admin_category_input.do">카테고리 등록</a>
					</td>

					<td>
						<a href="<%=request.getContextPath() %>/admin_category_list.do">카테고리 목록</a>
					</td>

					<td>
						<a href="<%=request.getContextPath() %>/admin_product_input.do">상품 등록</a>
					</td>		

					<td>
						<a href="<%=request.getContextPath() %>/admin_product_list.do">상품 목록</a>
					</td>
				</tr>
			</table>
			
		
		
		<hr width="65%">
		<br>

</body>
</html>