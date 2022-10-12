<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="dto" value="${Modify }" />

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
		<h3>${dto.getUpload_writer() }님 자료실 게시물 수정 폼 페이지</h3>
			<hr style="height: 3px; background-color: #198754;" />
		</div>
		<br>

		
		<form method="post" enctype="multipart/form-data" action="<%=request.getContextPath() %>/upload_modify_ok.do">
		<input type="hidden" name="upload_no" value="${dto.getUpload_no() }">


			<div class="mb-3 row">
				<label for="exampleFormControlInput1"
					class="col-sm-2 col-form-label">작성자</label>
				<div class="col-sm-10">
					<input name="upload_writer" class="form-control" value="${dto.getUpload_writer() }">
				</div>
			</div>

			<div class="mb-3 row">
				<label for="exampleFormControlInput1"
					class="col-sm-2 col-form-label">글제목</label>
				<div class="col-sm-10">
					<input name="upload_title"  class="form-control" value="${dto.getUpload_title() }">
				</div>
			</div>

			<div class="mb-3 row">
				<label for="exampleFormControlInput1"
					class="col-sm-2 col-form-label">글내용</label>
				<div class="col-sm-10">
					<textarea class="form-control" name="upload_cont">${dto.getUpload_cont() }</textarea>
				</div>
			</div>

			<div class="mb-3 row">
				<label for="exampleFormControlInput1"
					class="col-sm-2 col-form-label">파일첨부</label>
				<div class="col-sm-10">
					<input class="form-control" type="file" name="upload_file">
				</div>
			</div>
			
			<div class="mb-3 row">
				<label for="exampleFormControlInput1"
					class="col-sm-2 col-form-label">비밀번호</label>
				<div class="col-sm-10">
				<input class="form-control" type="password" name="upload_pwd">
				</div>
			</div>
			
			<div>
					<input class="btn btn-primary" type="submit" value="글수정"> &nbsp;&nbsp; 
					<input class="btn btn-outline-primary" type="reset" value="다시작성"> &nbsp;&nbsp;
					<input type="button" value="뒤로가기" class="btn btn-outline-secondary" 
					onclick="history.back()">
			</div>

		

		</form>
</div>

</body>
</html>