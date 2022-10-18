<%@page import="com.shop.model.CategoryDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.shop.model.CategoryDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	CategoryDAO dao = CategoryDAO.getInstance();

	List<CategoryDTO> list = dao.getCategoryList();

	request.setAttribute("categoryList", list);

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">


#title{
float: right;
margin-right:  17.5%;
word-spacing: 10px;
clear: both;

}

a {
text-decoration: none;
}

td {
	text-align: center;
}

td a {
	display: inline-block;
}

td a:hover {
	text-shadow: 1px 1px 2px gray;
/* 	text-shadow: -1.2px 0 white, 0 1.2px white, 1.2px 0 white, 0 -1.2px white; */
	
}
</style>
</head>
<body>

	<div align="center">
	
	   <table border="1" cellspacing="0" width="850">
	      <tr>
	         <td colspan="2" align="center">
	         &nbsp;&nbsp;&nbsp;
	            <a href="<%=request.getContextPath() %>/user_main.do">쇼핑몰 홈</a>&nbsp;&nbsp;&nbsp;
	            <a href="<%=request.getContextPath() %>/user_cart_list.do">장바구니</a>&nbsp;&nbsp;&nbsp;
	            <a href="#"> ${userName } 님 환영합니다.</a>&nbsp;&nbsp;&nbsp;
	            <a href="<%=request.getContextPath() %>/user_logout.do">로그아웃</a>
	         </td>
	      </tr>
	      
	      <tr>
	         <td>
	            <table class="table table-bordered" cellspacing="0" align="center">
	               <c:set var="list" value="${categoryList }" />
	               <tr>
	                  <td>카테고리 코드</td>
	               </tr>
	               
	               <c:if test="${!empty list }">
	                  <c:forEach items="${list }" var="dto">
	                     <tr>
	                        <td align="center">
	                           <a href="<%=request.getContextPath() %>/user_category_list.do?code=${dto.getCategory_code() }">
	                           	${dto.getCategory_name() } [${dto.getCategory_code() }]
	                           </a>
	                        </td>
	                     </tr>
	                  </c:forEach>
	               </c:if>
	   </table>
	
	   </td>
	   
	   <td>  <%-- 이하 영역은 본문 영역이 됨 --%>
	
	