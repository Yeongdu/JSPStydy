<%@page import="com.board.model.BoardDTO"%>
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
		<h3>BOARD테이블 게시글 수정 폼</h3>
		<hr width="50%" color="IndianRed">
		<br>

		<form method="post"
			action="<%=request.getContextPath() %>/update_ok.do">
			
			<table width="400">
			
       		<tr>
            	<th>작성자</th>
            	<td><input type="text" name="writer" value="<%=cont.getBoard_writer()%>" readonly></td>
            </tr>
            
            
			<tr>
				<th>글제목</th>
				<td>
				<input type="text" name="title" value="<%=cont.getBoard_title()%>">
				</td>
			</tr>
			
			<tr>
				<th>글내용</th>
				<td>
				<textarea rows="7" cols="25" name="content"><%=cont.getBoard_cont()%>"</textarea>
				</td>
			</tr>
			
			<tr>
				<th>비밀번호</th>
				<td>
					<input type="password" name="pwd">
				</td>
			</tr>
			
			
			<tr>
					<td colspan="2" align="center">
						<input type="submit" value="수정">&nbsp;&nbsp;
						<input type="reset" value="다시작성">&nbsp;&nbsp;
						<input type="button" value="목록"
						onclick="location.href='select.do'">
					</td>
				</tr>
			
			</form>
	</div>

</body>
</html>