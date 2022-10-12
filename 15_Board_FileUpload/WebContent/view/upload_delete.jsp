<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
<style type="text/css">
.mb-3 {
	width: 35em;
}

</style>
</head>
<body>
	<div align="center">
		<div style="width: 100%; margin: -30px 0 40px 0; text-align: center;">
			<hr style="height: 3px; background-color: #198754;" />
		<h3>${dto.getUpload_writer() }님 자료실 게시물 삭제 폼 페이지</h3>
			<hr style="height: 3px; background-color: #198754;" />
		</div>
		<br>
		
		<form method="post" action="<%=request.getContextPath() %>/upload_delete_ok.do">
		
		<input type="hidden" name="no" value="${param.no }">
		
			<div class="mb-3 row">
				<label for="exampleFormControlInput1"
					class="col-sm-2 col-form-label">비밀번호</label>
					<div class="col-sm-10">
					<input class="form-control" type="password" name="upload_pwd">
					</div>
			</div>
			
			
			<div>

					<input class="btn btn-primary" type="submit" value="글삭제"> &nbsp;&nbsp; 
					<input class="btn btn-outline-primary" type="reset" value="다시작성"> &nbsp;&nbsp;
					<input type="button" value="뒤로가기" class="btn btn-outline-secondary" 
					onclick="history.back()">

			</div>
		
		</form>
		
		</div>

</body>
</html>