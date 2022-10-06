<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
<style type="text/css">
.pagination{
justify-content: center;
}
a{
text-decoration: none;
}
th{
text-align: center;
}

.title_style:hover{
background-color: LightGrey;
}
.row{
justify-content: center;

}

</style>
</head>
<body>
<br><br>
	<div align="center">

		<h3>BOARD 테이블 게시물 전체 리스트</h3>
		<hr width="50%" color="gray">
		<br>

		<table class="table table-bordered" style="width: 36em">
			<tr>
				<th>글번호</th>
				<th>글제목</th>
				<th>조회수</th>
				<th>작성일자</th>
			</tr>

			<c:set var="list" value="${List }" />
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="dto">
					<tr>
						<td>${dto.getBoard_no() }</td>
						<td class="title_style" style="word-break: break-all;"><a
							href="<%=request.getContextPath()%>/board_content.do?no=${dto.getBoard_no() }&page=${page }"
							style="display: block;"> ${dto.getBoard_title() }</a></td>
						<td>${dto.getBoard_hit() }</td>
						<td>${dto.getBoard_date().substring(0,10) }</td>
					</tr>
				</c:forEach>
			</c:if>

			<c:if test="${empty list }">
				<tr>
					<td colspan="4" align="center">
						<h3>게시물이 없습니다.</h3>
					</td>
				</tr>
			</c:if>
			
			<tr>
				<td colspan="4" align="center">
					<input type="button" class="btn btn-outline-primary" value="글쓰기" onclick="location.href='board_write.do'">
				</td>
			</tr>
		</table>
		<br>




		<nav>
          <ul class="pagination">
            <li class="page-item">
              <a class="page-link" href="board_list.do?page=1">First</a></li>
            <c:choose>
                <c:when test="${ (page - 1) == 0}">
                    <li><a class="page-link" href="board_list.do?page=1">Previous</a></li>
                </c:when>
                <c:otherwise>
                    <li><a class="page-link" href="board_list.do?page=${ page - 1 }">Previous</a></li>
                </c:otherwise>
            </c:choose>
            <c:forEach begin="${ startBlock }" end="${ endBlock }" var="i">
                <c:if test="${ i==page }">
                    <li class="page-item active" aria-current="page">
                    <a class="page-link" href="board_list.do?page=${i }">${i }</a></li>
                </c:if>
                <c:if test="${ i!=page }">
                    <li class="page-item">
                    <a class="page-link" href="board_list.do?page=${i }">${i }</a></li>
                </c:if>
            </c:forEach>
           <c:if test="${ page < allPage }">
                <li class="page-item">
                <a class="page-link" href="board_list.do?page=${ page + 1 }">Next</a>
                </li>
                <li class="page-item">
                <a class="page-link" href="board_list.do?page=${ allPage }">End</a>
                </li>
            </c:if>
          </ul>
        </nav>


		<%-- 페이징 처리 영역 
		<c:if test="${page > block }">
			<a href="board_list.do?page=1">◀◀</a>
			<a href="board_list.do?page=${startBlock -1 }">◀</a>
		</c:if>
		
		<c:forEach begin="${startBlock }" end="${endBlock }" var="i">
			<c:if test="${i == page }">
				<b><a href="board_list.do?page=${i }">[${i }]</a></b>
			</c:if>
			
			<c:if test="${i != page }">
				<a href="board_list.do?page=${i }">[${i }]</a>
			</c:if>
		</c:forEach>
		
		<c:if test="${endBlock < allPage }">
			<a href="board_list.do?page=${endBlock+1 }">▶</a>
			<a href="board_list.do?page=${allPage }">▶▶</a>
		</c:if>
		--%>
		
		<br>
		<hr width="50%" color="gray">
		<br>
		<%-- 검색 기능 처리 --%>

		<form method="post"
			action="<%=request.getContextPath()%>/board_search.do">
			<div class="row">
				<div class="col-2">
					<select class="form-select" aria-label="Default select example"
						name="search_field">
						<option value="title">제목</option>
						<option value="cont">내용</option>
						<option value="title_cont">제목+내용</option>
						<option value="writer">작성자</option>
					</select>
				</div>
				<div class="col-3">
					<input class="form-control" name="search_keyword"
						value="${keyword}">
				</div>
				<div class="col-1">
					<input type="submit" class="btn btn-outline-primary" value="검색">
				</div>
			</div>
		</form>

		<%-- <form method="post" action="<%=request.getContextPath() %>/board_search.do">
			<select name="search_field">
				<option value="title">제목</option>
				<option value="cont">내용</option>
				<option value="title_cont">제목+내용</option>
				<option value="writer">작성자</option>
			</select>
			
			<input name="search_keyword">&nbsp;&nbsp;
			<input type="submit" value="검색">

		</form> --%>
		
	</div>
</body>
</html>