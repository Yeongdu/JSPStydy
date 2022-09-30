<%@page import="com.board1.model.BoardDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<BoardDTO> searchList = (List<BoardDTO>)request.getAttribute("Search");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color="DarkCyan">
		<h3>검색 결과</h3>
		<hr width="50%" color="DarkCyan">
		<br> <br>

		<table border="1" cellspacing="0" width="500">
			<tr>
				<th>글번호</th>
				<th>글제목</th>
				<th>작성자</th>
				<th>조회수</th>
				<th>작성일자</th>
			</tr>

			<%
				if (searchList.size() != 0) {
				for (int i = 0; i < searchList.size(); i++) {
					BoardDTO dto = searchList.get(i);
			%>

			<tr>
				<td><%=dto.getBoard_no()%></td>
				<td><a href="<%=request.getContextPath() %>/content.do?no=<%=dto.getBoard_no()%>"><%=dto.getBoard_title() %></a></td>
				<td><%=dto.getBoard_writer()%></td>
				<td><%=dto.getBoard_hit()%></td>
				<td><%=dto.getBoard_date().substring(0, 10)%></td>
			<tr>
				<%
					}
				} else {
				%>
			
			<tr>
				<td colspan="5" align="center">
					<h3>조회된 글이 없습니다.</h3>
				</td>
			</tr>

			<%
				}
			%>

			<tr>
				<td colspan="5" align="center"
					style="padding-top: 5px; padding-bottom: 5px;"><input
					type="button" value="전체 글 목록"
					onclick="location.href='select.do'"></td>
			</tr>

		</table>
	</div>


</body>
</html>