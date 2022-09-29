<%@page import="com.board.model.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	BoardDTO bCont = (BoardDTO)request.getAttribute("bContent");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
table tr td, table tr, table{
background-color: MintCream;
border : 1px solid gray;
border-spacing: 0px;

}

table tr td, table tr{
padding: 0.5em;
}

table th {
width: 4em;
background-color: GhostWhite;
text-align: center;
border : 1px solid gray;
border-spacing: 0px;
}

</style>
</head>
<body>
	<div align="center">
		<hr width="50%" color="IndianRed">
		<h3><%=bCont.getBoard_title()%></h3>
		<hr width="50%" color="IndianRed">
		<br> <br>
		<table width="400">
			<%
				if (bCont != null) {//데이터가 있다면
			%>
			
<!-- 			<tr> -->
<%--             <th colspan="4" align="center"><%=bCont.getBoard_title()%></th> --%>
<!--        		</tr> -->
       		
       		        
        <tr>
            <th>비밀번호</th>
            <td colspan="3">
        <%
        	if(bCont.getBoard_pwd().length() != 0){
        		for(int i=1; i<=bCont.getBoard_pwd().length(); i++) {
        			%>
        			*
      <%   	}
				}  %>
        </td>
        </tr>
       		
       		
       		  <tr>
            <th>작성자</th>
            <td><%=bCont.getBoard_writer()%></td>
            <th>작성일</th>
            <td><%=bCont.getBoard_date()%></td>
        </tr>
        <tr>
            <th>조회수</th>
            <td><%=bCont.getBoard_hit()%></td>
            <th>수정일</th>
            <td><% if(bCont.getBoard_update() != null){ %><%=bCont.getBoard_update()%><% }else{ %>없음<% } %></td>
        </tr>

<!-- 			<tr> -->
<!-- 				<th>글번호</th> -->
<%-- 				<td><%=bCont.getBoard_no()%></td> --%>
<!-- 			</tr> -->

<!-- 			<tr> -->
<!-- 				<th>작성자</th> -->
<%-- 				<td><%=bCont.getBoard_writer()%></td> --%>
<!-- 			</tr> -->

			

			<!-- 			<tr> -->
			<!-- 				<th>작성일</th> -->
			<%-- 				<td><%=bCont.getBoard_date()%></td> --%>
			<!-- 			</tr> -->

			<!-- 			<tr> -->
			<!-- 				<th>조회수</th> -->
			<%-- 				<td><%=bCont.getBoard_hit()%></td> --%>
			<!-- 			</tr> -->


			<tr>
				<th>글제목</th>
				<td colspan="3"><%=bCont.getBoard_title()%></td>
			</tr>

			<tr>
				<th>글내용</th>
				<td colspan="3"><%=bCont.getBoard_cont().replace("\n", "<br />")%></td>
			</tr>

			<%
				} else { //데이터가 없다면
			%>

			<tr>
				<td colspan="4" align="center">
					<h3>게시글 상세내역이 없습니다.</h3>
				</td>
			</tr>

			<%
				}
			%>




			<tr>
				<td colspan="4" align="center">
				<input type="button" value="수정"
					onclick="location.href='update.do?no=<%=bCont.getBoard_no()%>'">
					&nbsp;&nbsp; 
				
<!-- 				<input type="button" value="삭제" -->
<%-- 					onclick="if(confirm('정말로 삭제하시겠습니까?')){location.href='view/board_delete.do?no=<%=bCont.getBoard_no()%>'}else { return; }"> --%>
<!-- 					&nbsp;&nbsp; //삭제시 비밀번호 입력해야 하니까 board_delete.jsp 페이지로 이동 해야함--> 
					
				<input type="button" value="글 삭제"
					onclick="if(confirm('정말로 게시글을 삭제하시겠습니까?')) {
	               				location.href='view/board_delete.jsp?no=<%=bCont.getBoard_no() %>'
	               			}else { return; }">
							&nbsp;&nbsp;


				<input type="button" value="목록" onclick="location.href='select.do'"></td>
			</tr>
		</table>
		</div>
</body>
</html>