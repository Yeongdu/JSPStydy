<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="dto" value="${upCont }" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8"
	crossorigin="anonymous"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT"
	crossorigin="anonymous">
<link href="css/font_awesome.css" rel="stylesheet">
<style type="text/css">
.mb-3 {
	width: 35em;
}
</style>
</head>
<body>

	<div align="center">
		<div style="width: 100%; margin: -30px 0 40px 0; text-align: center;">
			<hr style="height: 3px; background-color: #198754;" />
			<h3>${dto.getUpload_writer() }님자료실게시물 상세 페이지</h3>
			<hr style="height: 3px; background-color: #198754;" />
		</div>
		<br>

		<table class="table table-bordered" style="width: 36em">

			<tr>
				<th>작성자</th>
				<td>${dto.getUpload_writer() }</td>
			</tr>

			<tr>
				<th>글제목</th>
				<td>${dto.getUpload_title() }</td>
			</tr>

			<tr>
				<!-- <th>글내용</th> -->

				<td colspan="4" style="word-break: break-all; padding: 15px;"
					width="400">${dto.getUpload_cont() }</td>
				<%-- <td><textarea rows="7" cols="25" readonly>${dto.getUpload_cont() }</textarea> --%>
				</td>
			</tr>

			<tr>
				<th>비밀번호</th>
				<td><c:if test="${dto.getUpload_pwd().length() != 0 }">
						<c:forEach begin="1" end="${dto.getUpload_pwd().length() }">
	           	*
	           	</c:forEach>

					</c:if></td>
			</tr>

			<tr>
				<th>첨부파일</th>
				<c:if test="${!empty dto.getUpload_file() }">
					<td><a
						href="<%=request.getContextPath() %>/upload/${dto.getUpload_file() }"
						target="_blank">${dto.getUpload_file() }</a></td>
				</c:if>

				<c:if test="${empty dto.getUpload_file() }">
					<td></td>
				</c:if>

			</tr>

			<tr>
				<th>조회수</th>
				<td>${dto.getUpload_hit() }</td>
			</tr>

			<tr>
				<c:if test="${empty dto.getUpload_update() }">
					<th>작성일자</th>
					<td>${dto.getUpload_date() }</td>
				</c:if>

				<c:if test="${!empty dto.getUpload_update() }">
					<th>수정일자</th>
					<td>${dto.getUpload_update() }</td>
				</c:if>
			</tr>

			<c:if test="${empty dto }">
				<tr>
					<td colspan="2" align="center">
						<h3>게시물이 없습니다..</h3>
					</td>
				</tr>
			</c:if>

			<tr>
				<td colspan="2" align="center"><input type="submit" value="글수정"
					class="btn btn-primary"
					onclick="location.href='upload_modify.do?no=${dto.getUpload_no() }'">
					&nbsp;&nbsp; 
					
					<input type="button" value="글삭제"
					class="btn btn-outline-primary"
					onclick="if(confirm('게시글을 진짜로 삭제하시겠습니까?')) {
						location.href='upload_delete.do?no=${dto.getUpload_no() }'}else {return;}">

						
					&nbsp;&nbsp; <input type="button" value="전체목록"
					class="btn btn-outline-secondary"
					onclick="location.href='upload_list.do'"></td>
			</tr>

		</table>
	</div>
</body>
</html>