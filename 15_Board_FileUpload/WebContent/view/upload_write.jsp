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

		 <hr style="height: 3px; background-color: MistyRose;" />
		<h3>Upload 게시판 글쓰기 폼 페이지</h3>
		 <hr style="height: 3px; background-color: MistyRose;" />
		<br>
		<%-- enctype : 파일을 업로드 하기 위한 속성 --%>
		<form method="post" enctype="multipart/form-data"
			action="<%=request.getContextPath() %>/upload_write_ok.do">

		<div class="mb-3 row">
				<label for="exampleFormControlInput1"
					class="col-sm-2 col-form-label">작성자</label>
				<div class="col-sm-10">
					<input name="upload_writer" class="form-control">
				</div>
			</div>

			<div class="mb-3 row">
				<label for="exampleFormControlInput1"
					class="col-sm-2 col-form-label">글제목</label>
				<div class="col-sm-10">
					<input name="upload_title"  class="form-control">
				</div>
			</div>

			<div class="mb-3 row">
				<label for="exampleFormControlInput1"
					class="col-sm-2 col-form-label">글내용</label>
				<div class="col-sm-10">
					<textarea class="form-control" name="upload_cont"></textarea>
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
				<td colspan="2" align="center">
					<input class="btn btn-primary" type="submit" value="글쓰기"> &nbsp;&nbsp;&nbsp; 
					<input class="btn btn-outline-primary" type="reset" value="다시작성"> &nbsp;&nbsp;&nbsp;
					<input type="button" class="btn btn-outline-secondary" value="뒤로가기" onclick="history.back()">
				</td>
			</div>

















			<!-- <table border="1" cellspacing="0" width="400">
				<tr>
					<td>작성자</td>
					<td><input name="upload_writer"></td>
				</tr>

				<tr>
					<td>글제목</td>
					<td><input name="upload_title"></td>
				</tr>

				<tr>
					<td>글내용</td>
					<td><textarea rows="7" cols="25" name="upload_cont"></textarea></td>
				</tr>

				<tr>
					<td>파일첨부</td>
					<td><input type="file" name="upload_file"></td>
				</tr>

				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="upload_pwd"></td>
				</tr>

				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="글쓰기"> &nbsp;&nbsp;&nbsp; 
						<input type="reset" value="다시작성">
					</td>
				</tr>
			</table> -->
		</form>
	</div>
</body>
</html>