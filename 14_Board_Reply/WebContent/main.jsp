<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>

</head>
<body>
<br />
	<div align="center">
	   <hr width="50%" color="blue">
	      <h3>JSP_BBS 답변형 게시판 메인 페이지</h3>
	   <hr width="50%" color="blue">
	   <br>
	   
	   
	   <button type="button"  class="btn btn-outline-dark btn-lg" onclick="location.href='<%=request.getContextPath()%>/bbs_list.do'">게시판 GO</button>
<%-- 	   <a href="<%=request.getContextPath() %>/bbs_list.do">[전체 게시물 목록]</a> --%>
	
	</div>

</body>
</html>