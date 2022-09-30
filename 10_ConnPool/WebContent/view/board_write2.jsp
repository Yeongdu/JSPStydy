<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>

	<div align="center">
		<hr width="50%" color="Turquoise">
		<h3>BOARD 테이블 게시판 글쓰기 폼 페이지</h3>
		<hr width="50%" color="Turquoise">
		<br> <br>
		<form method="post" action="<%=request.getContextPath()%>/insert.do">
			<table border="1" cellspacing="0" width="398">

				<tr>
					<th>작성자</th>
					<td><input type="text" name="writter"
						style="width: 316px; height: 20px;"></td>
				</tr>

				<tr>
					<th>글제목</th>
					<td><input type="text" name="title"
						style="width: 316px; height: 20px;"></td>
				</tr>

				<tr>
					<th>글내용</th>
					<td><textarea rows="9" cols="43" name="content"></textarea></td>
				</tr>

				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="pwd"
						style="width: 316px; height: 20px;"></td>
				</tr>

				<tr>
					<td colspan="2" align="center"
						style="padding-top: 5px; padding-bottom: 5px;"><input
						type="submit" value="글쓰기">&nbsp;&nbsp; <input type="reset"
						value="다시작성">&nbsp;&nbsp; <input type="button" value="목록"
						onclick="history.back()"></td>
				</tr>

			</table>
		</form>
	</div>



</body>
</html>