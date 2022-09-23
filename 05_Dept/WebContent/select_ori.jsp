<%@page import="com.khie.model.DeptDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% 
	List<DeptDTO> dept = (List<DeptDTO>)request.getAttribute("List");	
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" crossorigin="anonymous"></script>

</head>
<body>
	
		<div align="center">
			<hr width="50%" color="red">
				<h3>DEPT 테이블 전체 리스트 목록</h3>
			<hr width="50%" color="red">
			<br> <br>	
			
			<table border="1" cellspacing="0">
				<tr>
					<th>부서NO</th>
					<th>부서명</th>
					<th>부서위치</th>
					<th>부서수정</th>
					<th>부서삭제</th>
				
				</tr>
					
					<% 
						if(dept.size() != 0) { //데이터가 있다면
							//데이터 수 만큼 반복해서 화면에 출력
							for(int i=0; i<dept.size(); i++) {
								DeptDTO dto = dept.get(i);
					%>			
						<tr> 
							<td><%= dto.getDeptno() %> </td>
							<td><%= dto.getDname() %> </td>
							<td><%= dto.getLoc() %> </td>
							<td> 
								<input type="button" value="부서수정" onclick="location.href='update_ori.jsp?deptno=<%=dto.getDeptno() %>'">
							</td>
							<td> </td>
						</tr>
					<% 	
								
							}	// for문 end
						}else {	// 데이터가 없다면
					%>
						<tr>
							<td colspan="5" align="center">
								<h3>검색된 데이터가 없습니다.....</h3>
							</td>
						</tr>
						
					<% } %>
					
					<tr>
						<td colspan="5" align="right">
							<input type="button" value="부서추가" onclick="location.href='insert_ori.jsp'">&nbsp;&nbsp;
								
						</td>
						
					</tr>
					
					
			</table>
		</div>
		



</body>
</html>