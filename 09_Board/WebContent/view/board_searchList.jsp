<%@page import="com.board.model.BoardDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	List<BoardDTO> search = 	
		(List<BoardDTO>)request.getAttribute("Search");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<div align="center">
	   <hr width="50%" color="tomato">
	      <h3>BOARD 테이블 게시물 검색 목록 페이지</h3>
	   <hr width="50%" color="tomato">
	   <br>
	   
	   <table border="1" cellspacing="0" width="450">
	      <tr>
	         <th>글번호</th> <th>글제목</th>
	      	 <th>작성자</th> <th>조회수</th> <th>작성일자</th>
	      </tr>
	      
	      <%
	         if(search.size() != 0) {
	        	 
	        	 for(int i=0; i<search.size(); i++) {
	        		 BoardDTO dto = search.get(i);
	      %>
	      			<tr>
	      			   <td> <%=dto.getBoard_no() %> </td>
	      			   <td> <%=dto.getBoard_title() %> </td>
	      			   <td> <%=dto.getBoard_writer() %> </td>
	      			   <td> <%=dto.getBoard_hit() %> </td>
	      			   <td> <%=dto.getBoard_date().substring(0,10) %> </td>
	      			</tr>
	        <% 	 }  // for 문 end
	         }else {  // 검색된 게시물이 없는 경우
	        %>
	        		<tr>
	        		   <td colspan="5" align="center">
	        		      <h3>검색된 게시물이 없습니다.....</h3>
	        		   </td>
	        		</tr>	 
	       <% } %>
	       
	       <tr>
	          <td colspan="5" align="center">
	             <input type="button" value="전체 게시물"
	                onclick="location.href='select.do'">
	          </td>
	       </tr>
	       
	   </table>
	</div>
</body>
</html>



