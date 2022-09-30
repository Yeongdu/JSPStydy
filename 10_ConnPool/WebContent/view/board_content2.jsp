<%@page import="com.board1.model.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	BoardDTO cont = (BoardDTO)request.getAttribute("Content");
%>

<%
	int board_no = Integer.parseInt(request.getParameter("no").trim());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">

details > summary {
 background-color: Red;
  border: 1px solid gray;
  list-style: none;
  border-radius: 3px;
  font-size: 15px;
  color:white;

  padding: 1px 6px 1px 6px;
}

summary :hover{
background-color: Tomato;

}

table tr td, table tr, table{


}

table tr td, table tr{
padding: 0.5em;
}

table {

}

</style>
</head>
<body>

<div align="center">
		<hr width="50%" color="IndianRed">
		<h3>(((o(*ﾟ▽ﾟ*)o)))(((o(*ﾟ▽ﾟ*)o))))</h3>
		<hr width="50%" color="IndianRed">
		<br> <br>
		<table border="1" cellspacing="0" width="440">
			<%
				if (cont != null) {//데이터가 있다면
			%>
			
			<!-- 비밀번호는 딱히 안보여줘도 될것같아서 생략 -->
<!-- 		 <tr> -->
<!--             <th>비밀번호</th> -->
<!--             <td colspan="3"> -->
<%--         <% --%>
<!--          	if(cont.getBoard_pwd().length() != 0){ -->
<!--          		for(int i=1; i<=cont.getBoard_pwd().length(); i++) { -->
<%--         			%> --%>
<!--         			* -->
<%--       <%   	} --%>
<%-- 				}  %> --%>
<!--         </td> -->
<!--         </tr> -->
       		
       		
       		  <tr>
            <th>작성자</th>
            <td><%=cont.getBoard_writer()%></td>
            <th>작성일</th>
            <td><%=cont.getBoard_date()%></td>
        </tr>
        <tr>
            <th>조회수</th>
            <td><%=cont.getBoard_hit()%></td>
            <th>수정일</th>
            <td><% if(cont.getBoard_update() != null){ %><%=cont.getBoard_update()%><% }else{ %>없음<% } %></td>
        </tr>
        
        <tr>
				<th>제목</th>
				<td colspan="3"><%=cont.getBoard_title()%></td>
			</tr>

			<tr>
				<td colspan="4" style="padding: 15px;"><%=cont.getBoard_cont().replace("\n", "<br />")%></td>
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
			<td colspan="4" align="center" style="padding-top: 5px; padding-bottom: 5px;">
			
			<input type="button" value="수정"
					onclick="location.href='update.do?no=<%=cont.getBoard_no()%>'" style="
    margin: 10px 0px 10px 0px;
">
					&nbsp;&nbsp; 
					
			<input type="button" value="목록" onclick="location.href='select.do'">
			&nbsp;&nbsp; 
					
			<details style="display: inline-block;">
						<summary>삭제</summary>
						<form method="post"
							action="<%=request.getContextPath()%>/delete_ok.do">

							<input type="hidden" name="no" value="<%=board_no%>">

							<table border="1" cellspacing="0" width="350">
								<tr>
									<th width="90">비밀번호</th>
									<td><input type="password" name="pwd"
										style="width: 250px; height: 20px;"></td>
								</tr>

								<tr>
									<td colspan="2" align="center"><input type="submit"
										value="글 삭제"> &nbsp;&nbsp; <input type="reset"
										value="다시작성"></td>
								</tr>
							</table>

						</form>

			</details>







				</td>
			</tr>
		</table>
		</div>
</body>
</html>