<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	int deptno = Integer.parseInt(request.getParameter("deptno"));
%>    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

		<div align="center">
		<hr width="50%" color="gray">
			<h3>DEPT 테이블 부서 수정 폼 페이지</h3>	
		<hr width="50%" color="gray">
		<br> <br>
		
		<form method="post" action="updateOk">
			<table border="1" cellspacing="0">
				<tr>
					<th>부서NO.</th>
					<td> <input type="text" name="deptNo" value="<%= deptno%>" readonly> </td>
				</tr>
				

				<tr>
					<th>부서명</th>
					<td> <input type="text" name="reName"> </td>
				</tr>

				<tr>
					<th>부서위치</th>
					<td> <input type="text" name="reLoc"> </td>
				</tr>
				
				<tr> 
					<td colspan="2" align="center">
					<input type="submit" value="부서수정">&nbsp;&nbsp;
					<input type="reset" value="다시작성">
					</td>
				</tr>
							
			</table>
		
		
		</form>
		
	</div>

</body>
</html>