<%@page import="com.board1.model.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%
 	BoardDTO cont = (BoardDTO)request.getAttribute("modify");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color="IndianRed">
		<h3>BOARD테이블 게시글 수정 폼</h3>
		<hr width="50%" color="IndianRed">
		<br>

		<form method="post"
			action="<%=request.getContextPath()%>/update_ok.do">
			<input type="hidden" name="board_no" value="<%=cont.getBoard_no()%>">
			<table border="1" cellspacing="0" width="398">

				<tr>
					<th>작성자</th>
					<td><input type="text" name="writer" style="width: 316px; height: 20px;"
						value="<%=cont.getBoard_writer()%>" readonly></td>
				</tr>


				<tr>
					<th>글제목</th>
					<td><input type="text" name="title" style="width: 316px; height: 20px;"
						value="<%=cont.getBoard_title()%>"></td>
				</tr>

				<tr>
					<th>글내용</th>
					<td><textarea rows="9" cols="43" name="content"><%=cont.getBoard_cont()%>"</textarea>
					</td>
				</tr>

				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="pwd" style="width: 316px; height: 20px;"></td>
				</tr>


				<tr>
					<td colspan="2" align="center" style="padding-top: 5px; padding-bottom: 5px;"><input type="submit" value="수정">&nbsp;&nbsp;
						<input type="reset" value="다시작성">&nbsp;&nbsp; <input
						type="button" value="목록" onclick="location.href='select.do'">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>