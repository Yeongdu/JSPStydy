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
		<hr width="50%" color="gray">
		<h3>MEMBER10 테이블 회원등록 폼 페이지</h3>
		<hr width="50%" color="gray">
		<br>
		<form action="<%=request.getContextPath() %>/insert_ok.do">
		<table border="1" cellspacing="0" width="290">
			<tr>
				<th>회원 아이디</th>
				<td><input type="text" name="mem_id"></td>
			</tr>

			<tr>
				<th>회원 이름</th>
				<td><input type="text" name="mem_name"></td>
			</tr>

			<tr>
				<th>회원 비밀번호</th>
				<td><input type="password" name="mem_pwd"></td>
			</tr>

			<tr>
				<th>회원 나이</th>
				<td><input type="text" name="mem_age"></td>
			</tr>

			<tr>
				<th>회원 마일리지</th>
				<td><input type="text" name="mem_mileage"></td>
			</tr>

			<tr>
				<th>회원 직업</th>
				<td><input type="text" name="mem_job"></td>
			</tr>

			<tr>
				<th>회원 주소</th>
				<td><input type="text" name="mem_addr"></td>
			</tr>

			<tr>
				<td colspan="2" align="center">
				<input type="submit" value="회원등록">&nbsp;&nbsp;
				<input type="reset" value="다시작성">&nbsp;&nbsp;
				<input type="button" value="회원목록" onclick="location.href='../select.do'">
				</td>
			</tr>
</table>
		</form>

	</div>

</body>
</html>