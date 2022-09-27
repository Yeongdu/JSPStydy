<%@page import="org.apache.catalina.Contained"%>
<%@page import="com.emp.model.DeptDTO"%>
<%@page import="com.emp.model.EmpDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	List<String> jobList = (List<String>)request.getAttribute("jList");
	List<EmpDTO> mgrList = (List<EmpDTO>)request.getAttribute("mList");
	List<DeptDTO> deptList = (List<DeptDTO>)request.getAttribute("dList");
	EmpDTO dto = (EmpDTO)request.getAttribute("modify");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<br>
	<div align="center">
		<hr width="50%" color="Coral">
		<h3>EMP 테이블 사원 정보 수정 폼 페이지</h3>
		<hr width="50%" color="Coral">
		<br> <br>
		<form method="post" action="<%=request.getContextPath() %>/update_ok.do">
	
			<table border="1" cellspacing="0" width="400">
			
				<tr>
				<th>사번</th>
				<td><input type="text" name="num"
					value="<%=dto.getEmpno() %>" readonly></td>
				</tr>

				<tr>
					<th>이름</th>
					<td>
					<input type="text" name="name"
					value="<%=dto.getEname() %>" readonly>
					</td>
				</tr>
				
				<tr>
					<th>담당업무</th>
					<td>
					<select name="job">
					<%
						if(jobList.size()==0){
					%>
					<option value="">:::담당업무 없음:::</option>
					<%}else { //담당업무 잆다면
						for(int i=0; i<jobList.size(); i++){
							String str = jobList.get(i);
							
							if(str.equals(dto.getJob())) {
					%>
							<option value="<%=str %>" selected><%=str %></option>
							
					<% 	} else { %>
								<option value="<%=str %>"><%=str %></option>
					<%	}
					}
					}%>
					</select>
					</td>
				</tr>
				
				
				<tr>
					<th>관리자No.</th>
					<td>
					<select name="mgr">
					<%
 						if(mgrList.size()==0){
					%>
					<option value="">:::관리자 없음:::</option>
					<%}else { //관리자 잆다면
						
						for(int i=0; i<mgrList.size(); i++){
							EmpDTO mgr = mgrList.get(i);

							if(mgr.getEmpno() == dto.getMgr()) {
					%>
							<option value="<%=mgr.getEmpno() %>" selected><%=mgr.getEname() %> [<%=mgr.getEmpno() %>]</option>
							
					<% 	} else { %>
							<option value="<%=mgr.getEmpno() %>"><%=mgr.getEname() %> [<%=mgr.getEmpno() %>]</option>
					<%	}
					}
					}%>
					</select>
					</td>
				</tr>
				
				<tr>
				<th>급여</th>
				<td><input type="text" name="sal"
					value="<%=dto.getSal() %>"></td>
				</tr>
				
				<tr>
				<th>보너스</th>
				<td><input type="text" name="comm"
					value="<%=dto.getComm() %>"></td>
				</tr>
				
				<tr>
					<th>부서No.</th>
					<td>
					<select name="deptno">
					<%
						if(deptList.size()==0){
					%>
					<option value="">:::부서 없음:::</option>
					<%}else { //부서 잆다면
						for(int i=0; i<deptList.size(); i++){
							DeptDTO dept = deptList.get(i);
							
							if(dept.getDeptno() == dto.getDeptno()) {
					%>
							<option value="<%=dept.getDeptno() %>" selected><%=dept.getDname() %> [<%=dept.getDeptno() %>] : <%=dept.getLoc() %></option>
							
					<% 	} else { %>
							<option value="<%=dept.getDeptno() %>"><%=dept.getDname() %> [<%=dept.getDeptno() %>] : <%=dept.getLoc() %></option>
					<%	}
					}
					}%>
					</select>
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
				
			</table>
		</form>
			
	</div>
</body>
</html>