<%@page import="com.emp.model.DeptDTO"%>
<%@page import="com.emp.model.EmpDTO"%>
<%@ page import="java.text.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	EmpDTO cont = (EmpDTO)request.getAttribute("eContent");
	String mgrName = ((String)request.getAttribute("mgrName"));
	String deptName = ((String)request.getAttribute("deptName"));

	DecimalFormat df = new DecimalFormat("###,###");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
table tr td, table tr, table{
background-color: LightBlue;
text-align: center;
border : 1px solid gray;
border-spacing: 0px;
}

table th {
background-color: PeachPuff;
text-align: center;
border : 1px solid gray;
border-spacing: 0px;
}

</style>
</head>
<body>
<br />
	<div align="center">
		<hr width="50%" color="IndianRed">
		<h3>EMP 테이블 사원 상세 정보</h3>
		<hr width="50%" color="IndianRed">
		<br> <br>
		<table width="400">
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
				<td><%=cont.getMgr()%> [<%=mgrName %>]</td>
			</tr>
			<tr>
				<th>급여</th>
				<td><%=df.format(cont.getSal())%></td>
			</tr>
			<tr>
				<th>보너스</th>
				<td><%=cont.getComm()%></td>
			</tr>
			<tr>
				<th>부서번호</th>
				<td><%=cont.getDeptno()%> [<%=deptName %>]</td>
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
					<input type="button" value="수정"
					onclick="location.href='update.do?no=<%=cont.getEmpno()%>'">
					&nbsp;&nbsp; 
					
					<input type="button" value="삭제"
					onclick="if(confirm('정말로 삭제하시겠습니까?')){location.href='delete.do?no=<%=cont.getEmpno()%>'}else { return; }">
					&nbsp;&nbsp; 
					
					<input type="button" value="목록"
					onclick="location.href='select.do'"></td>
			</tr>
		</table>
			
	</div>
</body>
</html>