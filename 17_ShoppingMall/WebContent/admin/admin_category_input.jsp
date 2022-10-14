<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

input.btn.btn-primary{
	width: 90px;
}
</style>
</head>
<body>

	<jsp:include page="../include/admin_top.jsp" />
	<hr width="65%" color="marmoon">
	<h3>카테고리 등록 폼 페이지</h3>
	<hr width="65%" color="marmoon">
	<br>
	
<%-- 	<form method="post" action="<%=request.getContextPath() %>/admin_category_input_ok.do">
		<table>
			<tr>
				<th>카테고리 코드</th>
				<td><input name="category_code"></td>
			</tr>
			
			<tr>
				<th>카테고리 이름</th>
				<td><input name="category_name"></td>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
				<input type="submit" value="등록">&nbsp;&nbsp;&nbsp;
				<input type="reset" value="취소">
				</td>
			</tr>

		</table>
	</form> --%>
	
	
	
	
	
	<form method="post"
			action="<%=request.getContextPath() %>/admin_category_input_ok.do">
			<div class="mb-3 row">
				<div class="col-sm-10">
					<input type="text" name="category_code" class="form-control" placeholder="카테고리 코드" autocomplete="off" required autofocus>
				</div>
			</div>

			<div class="mb-3 row">
				<div class="col-sm-10">
					<input type="text" name="category_name" class="form-control" placeholder="카테고리 이름" autocomplete="off" required>
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