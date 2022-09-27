<%@page import="com.emp.model.DeptDTO"%>
<%@page import="com.emp.model.EmpDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
List<EmpDTO> emp = (List<EmpDTO>)request.getAttribute("eList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
table tr td {
background-color: LightBlue;
text-align: center;}

table tr th {
background-color: LightSteelBlue;}

</style>
</head>
<body>
<br>
	<div align="center">
		<hr width="50%" color="DarkTurquoise">
		<h3>EMP 테이블 전체 직원 리스트</h3>
		<hr width="50%" color="DarkTurquoise">
		<br> <br>

		<table border="1" cellspacing="0" width="500">
			<tr>
				<th>사원번호</th>
				<th>이름</th>
				<th>부서번호</th>
				<th>입사일자</th>
			</tr>
			<%
			if(emp.size()!=0){
				for(int i=0; i<emp.size(); i++){
					EmpDTO dto = emp.get(i);
					
			%>
			
			<tr>
				<td><%=dto.getEmpno() %></td>				
				<td><a href="<%=request.getContextPath()%>/content.do?empno=<%=dto.getEmpno() %>">
				<%=dto.getEname() %></a></td>
				<td><%=dto.getDeptno()%></td>
				
				<%
				if(dto.getHiredate() == null) {
				%>
				<td></td>
				<%}else{ %>
				<td><%=dto.getHiredate().substring(0,10)%></td>
				<%} %>
			
			</tr>
				<%   } //for문 end
			}//if문 끝
			else {//데이터가 없는 경우
			%>
			<tr>
				<td colspan="4" align="center">
					<h3>사원 목록이 없습니다...</h3>
				</td>
			</tr>
			<%
				}
			%>

			<tr>
				<td colspan="4" align="right"><input type="button" value="사원등록"
					onclick="location.href='insert.do'"/></td>
			</tr>
		</table>
	</div>

</body>
</html>