<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
<style type="text/css">
ul li {
list-style: none;
display: inline-block;
margin-left: 80px;
}

</style>

</head>
<body>

	<jsp:include page="../include/user_top.jsp" />
	
	<div align="center">
	   <h3>쇼핑몰에 오신 걸 환영합니다.</h3>
	   
	   <c:set var="list" value="${productList }" />
	   
	   <c:if test="${empty list }">
	      <span>제품 목록이 없습니다.....</span>
	   </c:if>
	   
	   <c:if test="${!empty list }">
	      <hr width="85%" color="tomato">
	         <h3>제품 목록 리스트 페이지</h3>
	      <hr width="85%" color="tomato">
	      <br>
	      
	      <table border="1" cellspacing="0">
	         <tr>
	            <c:forEach items="${list }" var="dto">
	               <c:set var="count" value="${count + 1 }" />
	               <td align="center">
	                  <a href="<%=request.getContextPath() %>/user_product_view.do?pnum=${dto.getPnum() }">
	                     <img src="<%=request.getContextPath() %>/upload/${dto.getPimage() }"
	                            width="120" height="120" border="0">
	                  </a>
	                  <br>
	                    ${dto.getPname() } <br>
	                    <fmt:formatNumber value="${dto.getPrice() }" /> 원<br>
	                    <fmt:formatNumber value="${dto.getPoint() }" var="commaPoint" />
	                    [${commaPoint }] 포인트
	               </td>
	               
	               <c:if test="${count % 3 == 0 }">
	                  </tr>
	                  <tr>
	               </c:if>
	            </c:forEach>   
	      
	      </table>
	   
	   </c:if>
       </div>
	
	<jsp:include page="../include/user_bottom.jsp" />

</body>
</html>