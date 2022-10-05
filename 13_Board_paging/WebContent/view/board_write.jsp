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
	width: 50%
}

</style>
</head>
<body>
	<br><br>
	<div align="center">

		<h3>BOARD 글쓰기 페이지</h3>
		<hr width="50%" color="Turquoise">
		<br>
		<form method="post"
			action="<%=request.getContextPath()%>/board_write_OK.do">

			<div class="mb-3 row">
				<label for="exampleFormControlInput1"
					class="col-sm-2 col-form-label">작성자</label>
				<div class="col-sm-10">
					<input type="text" name="writter" class="form-control">
				</div>
			</div>

			<div class="mb-3 row">
				<label for="exampleFormControlInput1"
					class="col-sm-2 col-form-label">글제목</label>
				<div class="col-sm-10">
					<input type="text" name="title" class="form-control">
				</div>
			</div>

			<div class="mb-3 row">
				<label for="exampleFormControlInput1"
					class="col-sm-2 col-form-label">글내용</label>
				<div class="col-sm-10">
					<textarea class="form-control" name="content"
						id="exampleFormControlTextarea1" rows="3"></textarea>
				</div>
			</div>

			<div class="mb-3 row">
				<label for="exampleFormControlInput1"
					class="col-sm-2 col-form-label">비밀번호</label>
				<div class="col-sm-10">
					<input type="password" name="pwd" class="form-control">
				</div>
			</div>
			<br>

			<div>
				<input type="submit" value="글쓰기" class="btn btn-primary">&nbsp;&nbsp;
				<input type="reset" value="다시작성" class="btn btn-outline-primary">&nbsp;&nbsp;
				<input type="button" value="목록" class="btn btn-outline-secondary" onclick="history.back()">
			</div>
		</form>
	</div>
</body>
</html>