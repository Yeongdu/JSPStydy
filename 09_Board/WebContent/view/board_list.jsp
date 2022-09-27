<%@page import="com.board.model.BoardDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	List<BoardDTO> boardList = (List<BoardDTO>)request.getAttribute("bList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOARD 글 목록</title>
<style type="text/css">

table tr th {
background-color: LightCyan;}

</style>
</head>
<body>

	<div align="center">
		<hr width="50%" color="DarkTurquoise">
		<h3>BOARD 테이블 글 목록</h3>
		<hr width="50%" color="DarkTurquoise">
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
			if(boardList.size()!=0){
				for(int i=0; i<boardList.size(); i++){
					BoardDTO dto = boardList.get(i);
			%>
			
			<tr>
				<td><%=dto.getBoard_no() %></td>
				<td><%=dto.getBoard_title() %></td>
				<td><%=dto.getBoard_writer() %></td>
				<td><%=dto.getBoard_hit() %></td>
				<td><%=dto.getBoard_date() %></td>
			<tr>
				<%   } //for문 end
			} //if문 끝
			else { //데이터가 없는 경우
			%>
			<tr>
				<td colspan="4" align="center">
					<h3>글이 없습니다.</h3>
				</td>
			</tr>
			<%
				}
			%>
			
		</table>
		
		
	</div>

</body>
</html>