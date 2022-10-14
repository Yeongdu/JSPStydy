<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
.mb-3 {
	width: 50%;
	justify-content: center;
}


</style>
</head>
<body>
	<jsp:include page="../include/admin_top.jsp" />
	<hr width="65%" color="marmoon">
	<h3>상품 등록 폼 페이지</h3>
	<hr width="65%" color="marmoon">
	<br>
	<form method="post" enctype="multipart/form-data"
		action="<%=request.getContextPath()%>/admin_product_input_ok.do">
<c:set var="list" value="${categoryList }" />
			<div class="mb-3 row">
				<div class="col-sm-10">
					<input type="text" name="p_name" class="form-control" placeholder="상품명" autocomplete="off" required autofocus>
				</div>
			</div>

			<div class="mb-3 row">
				<div class="col-sm-10">
				<select name="p_category" class="form-select">
				<c:if test="${empty list }">
				<option value="">::카테고리코드 없음::</option>
				</c:if>
				
				<c:if test="${!empty list }">
				<c:forEach items="${list }" var="dto">
					 <option value="${ dto.getCategory_code() }">[${dto.getCategory_name()}] (${dto.getCategory_code()})</option>
				
				</c:forEach>
				</c:if>
				</select>
				</div>
			</div>
			
			<div class="mb-3 row">
				<div class="col-sm-10">
					<input type="text" name="p_company" class="form-control" placeholder="상품 제조사" autocomplete="off">
				</div>
			</div>
			
			
			<div class="mb-3 row">
				<div class="col-sm-10">
					<input type="file" name="p_image" class="form-control" placeholder="상품 이미지" autocomplete="off">
				</div>
			</div>
			
			
			<div class="mb-3 row">
				<div class="col-sm-10">
					<input type="number" name="p_qty" min="1" max="100" class="form-control" placeholder="상품 수량" autocomplete="off" required>
				</div>
			</div>
			
			<div class="mb-3 row">
				<div class="col-sm-10">
					<input type="number" name="p_price" class="form-control" placeholder="상품 가격" autocomplete="off" required>
				</div>
			</div>
			
			<div class="mb-3 row">
			<div class="col-sm-10">
			<select name="p_spec" class="form-select">
			<option value="none" selected>일반</option>
			<option value="hit">인기</option>
			<option value="new">신상</option>
			<option value="recommend">추천</option>
			</select>
			</div>
			</div>
			
			<div class="mb-3 row">
				<div class="col-sm-10">
					<textarea class="form-control" name="p_content" placeholder="상품 설명" autocomplete="off" required ></textarea>
				</div>
			</div>
			
			<div class="mb-3 row">
				<div class="col-sm-10">
					<input type="number" name="p_point" class="form-control" placeholder="상품 포인트" autocomplete="off">
				</div>
			</div>
			
			<div class="button1">
				<input type="submit" value="등록" class="btn btn-primary" 
				style="width: 58px;">&nbsp;&nbsp;
				<input type="reset" value="취소" class="btn btn-outline-primary">&nbsp;&nbsp;
			</div>

	</form>

	<jsp:include page="../include/admin_bottom.jsp" />
</body>
</html>