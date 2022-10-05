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
<style type="text/css">

</style>
</head>
<body>
<br>
	<div align="center">
		<c:set var="dto" value="${Cont }" />
		<h3>${dto.getBoard_writer() }님 게시물 상세 내역</h3>
		<hr width="50%" color="Turquoise">
		<br>
		
		<table class="table table-bordered" style="width: 50%">
		<tr>
            <th>작성자</th>
            <td colspan="3">${dto.getBoard_writer() }</td>
           
        </tr>
        
         <tr>
            <th>조회수</th>
            <td>${dto.getBoard_hit() }</td>
            <c:if test="${empty dto.getBoard_update() }">
            <th>작성일</th>
            <td>${dto.getBoard_date() }
            </c:if>
            
            <c:if test="${!empty dto.getBoard_update() }">
            <th>수정됨</th>
            <td>${dto.getBoard_update() }
            </c:if>
            </tr>
            
            <tr>
				<th>글제목</th>
				<td colspan="3">${dto.getBoard_title() }</td>
			</tr>

			<tr>
				<th>글내용</th>
				<td colspan="3" style="word-break:break-all" width="400">${dto.getBoard_cont() }</td>
			</tr>
            
            
            
            <c:if test="${empty dto }">
            	<tr>
            		<td colspan="4" align="center">
					<h3>게시글 상세내역이 없습니다.</h3>
				</td>
            	</tr>
            </c:if>
            
            
            <tr>
				<td colspan="4" align="center">
					<input type="button" value="글수정" id="btn" class="btn btn-outline-primary"
					onclick="location.href='board_update.do?no=${dto.getBoard_no() }&page=${Page }'">
					<input type="button" value="글삭제" id="btn2" class="btn btn-outline-danger"
					onclick="if(confirm('게시글을 삭제하시겠습니까?')) {
					location.href='board_delete.do?no=${dto.getBoard_no() }&page=${Page }'
					} else {return; }">
					<input type="button" value="목록으로" id="btn3" class="btn btn-outline-dark"
					onclick="location.href='board_list.do?page=${Page }'">
				</td> 
			
			</tr>
            
         
		</table>
	</div>

</body>
</html>