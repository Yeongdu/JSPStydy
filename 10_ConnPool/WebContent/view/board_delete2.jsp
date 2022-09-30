<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	int board_no = Integer.parseInt(request.getParameter("no").trim());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color="marmoon">
		<h3>글을 삭제하려면 비밀번호를 입력해주세요</h3>
		<hr width="50%" color="marmoon">
		<br>

		<form method="post" action="<%=request.getContextPath() %>/delete_ok.do">

			<input type="hidden" name="no" value="<%=board_no %>">

			<table border="1" cellspacing="0" width="350">
				<tr>
					<th width="90">비밀번호</th>
					<td><input type="password" name="pwd" style="width: 250px; height: 20px;"></td>
				</tr>

				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="글 삭제"> &nbsp;&nbsp; <input type="reset"
						value="다시작성"></td>
				</tr>
			</table>
			
		</form>
	</div>
</body>
</html>