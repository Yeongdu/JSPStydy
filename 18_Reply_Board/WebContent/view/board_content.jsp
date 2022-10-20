<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
	crossorigin="anonymous"></script>


</head>
<body>

	<c:set var="dto" value="${Cont }" />
	<br>
	<div align="center">
		<h3>${dto.writer }님게시글 상세 내역</h3>
		<hr width="50%">
		<br>

		<table class="table table-bordered" style="width: 36em">
			<tr>
				<th>작성자</th>
				<td>${dto.writer }</td>

			</tr>

			<tr>
				<th>제목</th>
				<td>${dto.title }</td>
			</tr>
			<tr>

				<td td colspan="2" style="word-break: break-all; padding: 15px;"
					width="400">${dto.content }</td>
			</tr>

			<tr>

				<c:if test="${empty dto.regdate }">
					<th>작성일</th>
					<td>${dto.regdate }
				</c:if>

				<c:if test="${!empty dto.regupdate }">
					<th>수정됨</th>
					<td>${dto.regupdate() }
				</c:if>
			</tr>


			<c:if test="${empty dto }">
				<tr>
					<td colspan="2" align="center">
						<h3>게시글 상세내역이 없습니다.</h3>
					</td>
				</tr>
			</c:if>

			<tr>
				<td colspan="2" align="center">
					<button type="button" class="btn btn-outline-secondary"
						onclick="location.href=' board_modify.do?no=${dto.bno }'">수정</button>
					<button type="button" class="btn btn-outline-secondary"
						onclick="location.href=' board_remove.do?no=${dto.bno }'">삭제</button>
					<button type="button" class="btn btn-outline-secondary"
						onclick="location.href='<%=request.getContextPath()%>/board_list.do'">목록</button>
				</td>
			</tr>
		</table>
		
		
		<br>
		<hr width="50%">
		<br>
		
		
		
	</div>





</body>
</html>