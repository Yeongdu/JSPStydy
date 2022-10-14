<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 로그인</title>
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
	
}

</style>
</head>
<body>
	<div align="center">
		<hr width="65%" color="MediumTurquoise">
		<h3>관리자 페이지</h3>
		<hr width="65%" color="MediumTurquoise">
		<br>

		<form method="post"
			action="<%=request.getContextPath() %>/admin_login_ok.do">
			<div class="mb-3 row">
				<div class="col-sm-10">
					<input type="text" name="admin_id" class="form-control" placeholder="Admin ID" autocomplete="off" required autofocus>
				</div>
			</div>

			<div class="mb-3 row">
				<div class="col-sm-10">
					<input type="password" name="admin_pwd" class="form-control" placeholder="Admin PW" autocomplete="off" required>
				</div>
			</div>
			
			<div>
				<input type="submit" value="로그인" class="btn btn-primary">&nbsp;&nbsp;
				<input type="reset" value="다시작성" class="btn btn-outline-primary">&nbsp;&nbsp;
			</div>
		</form>
	</div>
</body>
</html>