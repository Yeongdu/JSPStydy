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
		<hr width="50%" color="lightgray">
		<h3>MEMBER10 회원 삭제 폼 페이지</h3>
		<hr width="50%" color="lightgray">
		<br>
		<form action="<%=request.getContextPath()%>/delete_ok.do">
		<input type="hidden" name="mem_no" value="${No }">
			<table border="1" cellspacing="0" width="350">
				<tr>
					<th>삭제할 회원 비밀번호</th>
					<td><input type="password" name="mem_pwd"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="회원삭제">&nbsp;&nbsp; 
						<input type="reset" value="다시작성">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>