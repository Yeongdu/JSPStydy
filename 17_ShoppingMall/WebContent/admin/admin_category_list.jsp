<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
<script type="text/javascript">
	function check(no) {
		let res = confirm("정말로 삭제하시겠습니까?");
		if(res){
			location.href="admin_category_delete.do?cnum="+no;
		}
		
	}
</script>

<style type="text/css">
.table {
width: 60%;
text-align: center;
}

.btn.btn-outline-danger{
    /* height: 27px; */
    width: 80px;
    padding-bottom: 0px;
    padding-top: 0px;
}
</style>
</head>
<body>

<jsp:include page="../include/admin_top.jsp" />

<hr width="65%">
<h3>카테고리 테이블 전체 리스트 페이지</h3>
<hr width="65%">

<table class="table table-bordered table-hover">
	<tr>
	<th style="width: 13%;">카테고리 번호</th>
	<th style="width: 37%;">카테고리 코드</th>
	<th style="width: 37%;">카테고리 이름</th>
	<th style="width: 13%;">카테고리 삭제</th>
	
	</tr>
	<c:set var="list" value="${CategoryList }" />
	<c:if test="${!empty list }">
		<c:forEach items="${list }" var="dto">
		<tr class="align-middle">
		<td> ${dto.getCategory_num() }</td>
		<td> ${dto.getCategory_code() }</td>
		<td> ${dto.getCategory_name() }</td>
		<td>
		<%-- <a href="<%=request.getContextPath()%>/admin/admin_category_delete.do?cnum=${dto.getCategory_num()}" onclick="return confirm('이 카테고리를 삭제하시겠습니까?');" class="btn btn-sm btn-outline-danger">삭제</a> --%>
		
		<input type="button" class="btn btn-outline-danger" value="삭제" onclick="check(${dto.getCategory_num()})" />
		</td>
		</tr>
		</c:forEach>
	</c:if>
	
	<c:if test="${empty list }">
	<tr>
	<td colspan="4" align="left">
	<h3>카테고리 코드 목록이 없음</h3>
	</td>
	</tr>
	
	</c:if>

</table>

<jsp:include page="../include/admin_bottom.jsp" />

</body>
</html>