<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 메인 페이지</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
	crossorigin="anonymous"></script>
<style type="text/css">
ul li {
list-style: none;
display: inline-block;
margin-left: 80px;
}

</style>
</head>
<body>

<jsp:include page="../include/admin_top.jsp" />


<ul>
	<li><a href="<%=request.getContextPath() %>/admin_category_input.do">쇼핑 카테고리 등록</a></li>
	<li><a href="<%=request.getContextPath() %>/admin_category_list.do">쇼핑 카테고리 리스트</a></li>
	<li><a href="<%=request.getContextPath() %>/admin_product_input.do">쇼핑 상품 등록</a></li>
</ul>


<jsp:include page="../include/admin_bottom.jsp" />
</body>
</html>