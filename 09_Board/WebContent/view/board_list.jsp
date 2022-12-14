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

table tr td, table tr{
padding: 0.5em;
}

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
				<td><a href="<%=request.getContextPath()%>/content.do?no=<%=dto.getBoard_no() %>">
				<%=dto.getBoard_title() %></a></td>
				<td><%=dto.getBoard_writer() %></td>
				<td><%=dto.getBoard_hit() %></td>
				<td><%=dto.getBoard_date().substring(0,10) %></td>
			<tr>
				<%   } //for문 end
			} //if문 끝
			else { //데이터가 없는 경우
			%>
			<tr>
				<td colspan="5" align="center">
					<h3>글이 없습니다.</h3>
				</td>
			</tr>
			<%
				}
			%>
			
			<tr>
				<td colspan="5" align="center">
					<input type="button" value="글쓰기" onclick="location.href='view/board_write.jsp'">
				</td>
			</tr>
			
		</table>
		
		
		 <br>
	   <hr width="50%" color="red">
	   <br>
	   
	   <%-- 검색 관련 요청 태그 --%>
	   <form method="post"
	      action="<%=request.getContextPath() %>/search.do">
	   
	      <select name="find_field">
	      	 <%-- String find_field = "title"; --%>
	         <option value="title">제목</option>
	         
	         <%-- String find_field = "cont"; --%>
	         <option value="cont">내용</option>
	         
	         <%-- String find_field = "title_cont"; --%>
	         <option value="title_cont">제목+내용</option>
	         
	         <%-- String find_field = "writer"; --%>
	         <option value="writer">작성자</option>
	      </select>
	      
	      <input type="text" name="find_name">
	      
	      <input type="submit" value="검색">
	      
	   </form>
		
		
	</div>

</body>
</html>