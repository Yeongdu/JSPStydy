<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="list" value="${List }" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TBL_BOARD LIST</title>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
<style type="text/css">
.table{
	width: 50%;
}
a{
text-decoration: none;
}

</style>
</head>
<body>
	<br>

	<div align="center">
		<table class="table table-bordered table-hover">
			<tr>
				<th style="width: 10%;">번호</th>
				<th style="width: 40%;">제목</th>
				<th style="width: 25%;">작성일</th>
				<th style="width: 25%;">수정일</th>
			</tr>
			
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="dto">
					<tr class="align-middle">
						<td>${dto.bno }</td>
						<td>
						<a href="<%=request.getContextPath()%>/board_content.do?no=${dto.bno }" style="display: block;">
						${dto.title }</a>
						</td>
						<td>${dto.regdate.substring(0,10) }</td>
						<c:if test="${empty dto.regupdate }">
							<td></td>
						</c:if>
						<c:if test="${!empty dto.regupdate }">
							<td>${dto.regupdate.substring(0,10) }</td>
						</c:if>
					</tr>
				</c:forEach>
			</c:if>

			<c:if test="${empty list }">
				<tr>
					<td colspan="4" align="center">
						<h3>게시글이 없음</h3>
					</td>
				</tr>
			</c:if>

			<tr>
				<td colspan="4" align="center">
					<button type="button" class="btn btn-outline-secondary"
						onclick="location.href='<%=request.getContextPath()%>/board_write.do'">글쓰기</button>

				</td>
			</tr>
		</table>
	</div>

</body>
</html>