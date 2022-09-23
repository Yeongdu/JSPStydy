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
		<hr width="50%" color="blue">
		<h2>로그인 페이지</h2>
		<hr width="50%" color="blue">

		<br>
		<br>
		

		<form method="get" action="LoginServlet">
			<table border="1" cellspacing="0">
				<tr>
					<th>아이디</th>
					<%-- 텍스트박스에 입력된 아이디를 userId라는 변수에 저장하여 Servlet으로 전송하라는 의미
						 만약 폼 창에서 아이디에 "hong" 이라고 입력하면 String userId = "hong"이라고 저장이 되어 
						 LoginServlet으로 정보가 넘어가게 된다. --%>
					<td><input type="text" name="userId" ></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="userPwd"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="전송">&nbsp;
						<input type="reset" value="취소"></td>
				</tr>

			</table>
			
		</form>

	</div>

</body>
</html>