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
.center{
text-align: center;
}

</style>
</head>
<body>

	<jsp:include page="../include/user_top.jsp" />
		<table class="table table-bordered" border="1" cellspacing="0" width="650" align="center">
			<tr>
				<td colspan="7" align="center">
					<h3>장바구니 보기</h3>
				</td>
			</tr>
			
			<tr>
				<th width="10%">주문No</th>
				<th width="10%">상품No</th>
				<th width="15%">상품명</th>
				<th width="12%">수량</th>
				<th width="15%">단가</th>
				<th width="15%">합계액</th>
				<th width="10%">삭제</th>
			</tr>
			
			<c:set var="list" value="${cartList }" />
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="dto">
					<tr>
						<td class="center"> ${dto.getCart_num() }</td>
						<td class="center"> ${dto.getCart_pnum() }</td>
						<td class="center"> <img src="<%=request.getContextPath()%>/upload/${dto.getCart_pimage()}" width="50" height="50"/>
						<br>
						${dto.getCart_pname() }
						</td>
						<td class="center"> ${dto.getCart_pqty() }</td>
						<td class="center"> 
						<fmt:formatNumber value="${dto.getCart_price() }" />원
						</td>
						<td class="center">
							<c:set var="danga" value="${dto.getCart_price() }" />
							<c:set var="amount" value="${dto.getCart_pqty() }" />
							<fmt:formatNumber value="${danga*amount }"/> 원
						</td>
						
						<td align="center">
						<a href="<%=request.getContextPath() %>/user_cart_delete.do?num=${dto.getCart_num() }">[삭제]</a>
						</td>
						
						<c:set var="total" value="${total+ (danga*amount)}" />
						
					</tr>
				
				</c:forEach>
				
				<tr>
					<td colspan="4" align="center">
						<b><font color="red">장바구니 총액 : <fmt:formatNumber value="${total }"/>원</font></b>
					</td>
					
					<td colspan="3" align="center">
						<button type="button" class="btn btn-outline-primary" onclick="location.href="#">결제하기</button>&nbsp;&nbsp;
						<button type="button" class="btn btn-outline-primary" onclick="location.href="user_main.do">계속쇼핑</button>&nbsp;&nbsp;
						<a href="javascript:history.go(-2);">[계속쇼핑]</a>
					</td>
				</tr>
			</c:if>
			
			<c:if test="${empty list }">
				<tr>
					<td colspan="7" align="center">
						<h3>장바구니가 비어 있습니다...</h3>
					</td>
				</tr>
			</c:if>
		</table>
	
	<jsp:include page="../include/user_bottom.jsp" />

</body>
</html>