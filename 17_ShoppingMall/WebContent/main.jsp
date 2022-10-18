<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쇼핑몰이에요</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>

</head>
<body>
	<div align="center">
	   <hr width="65%" color="PaleTurquoise">
	      <h3>쇼핑몰 메인 페이지</h3>
	   <hr width="65%" color="PaleTurquoise">
	   <br>
	   
	    <button type="button"  class="btn btn-outline-secondary btn-lg" onclick="location.href='<%=request.getContextPath()%>/member_login.do'">회원로그인</button>
	   &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
	   &nbsp;
	    <button type="button"  class="btn btn-outline-secondary btn-lg" onclick="location.href='<%=request.getContextPath()%>/admin_login.do'">관리자로그인</button>
	   
	</div>
</body>
</html>