<%@page import="com.emp.model.EmpDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	EmpDTO cont = (EmpDTO)request.getAttribute("eContent");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<br />
	<div align="center">
		<hr width="50%" color="IndianRed">
		<h3>EMP 테이블 사원 상세 정보</h3>
		<hr width="50%" color="IndianRed">
		<br> <br>
		<table border="1" cellspacing="0" width="400">
			<%
				if (cont != null) {//데이터가 있다면
			%>
			<tr>
				<th>사번</th>
				<td><%=cont.getEmpno()%></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><%=cont.getEname()%></td>
			</tr>
			<tr>
				<th>직무</th>
				<td><%=cont.getJob()%></td>
			</tr>
			<tr>
				<th>관리자</th>
				<td><%=cont.getMgr()%></td>
			</tr>
			<tr>
				<th>급여</th>
				<td><%=cont.getSal()%></td>
			</tr>
			<tr>
				<th>보너스</th>
				<td><%=cont.getComm()%></td>
			</tr>
			<tr>
				<th>부서번호</th>
				<td><%=cont.getDeptno()%></td>
			</tr>
			<tr>
				<th>입사일</th>
				<td><%=cont.getHiredate()%></td>
			</tr>
			
			<%
				} else { //데이터가 없다면
			%>
			
			<tr>
				<td colspan="2" align="center">
					<h3>사원이 없습니다...</h3>
				</td>
			</tr>
			
			<%
				}
			%>
			
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="수정"
					onclick="location.href='update.do?empno=<%=cont.getEmpno()%>'">
					&nbsp;&nbsp; 
					
					<input type="button" value="삭제"
					onclick="if(confirm('정말로 삭제하시겠습니까?')){location.href='delete.do?empno=<%=cont.getEmpno()%>'}else { return; }">
					&nbsp;&nbsp; 
					
					<input type="button" value="목록"
					onclick="location.href='select.do'"></td>
			</tr>
		</table>
	</div>
</body>
</html>