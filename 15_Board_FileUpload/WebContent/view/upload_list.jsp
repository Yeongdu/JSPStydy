<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="list" value="${List}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시판 목록</title>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">

 <style type="text/css">
 #container {
 margin: 15px;
 }
 </style>
</head>
<body>

	
<main id="container" class="pb-5">
        <div class="contents board-list">
            <div style="width: 100%; margin: -30px 0 40px 0; text-align: center;">
                <hr style="height: 3px; background-color: #198754;" />
                <h3>Upload 테이블 게시판 목록 페이지</h3>
                <hr style="height: 3px; background-color: #198754;" />
            </div>


            <div class="row">
                <div class="col-lg">
                    <div class="card border-0">
                        <div class="card-header bg-white border-0 pt-0 pl-0">총 <b class="text-primary eng"><fmt:formatNumber value="${list.size()}" /></b> 개의 게시물</div>
                    </div>
                </div>
            </div>


            <div class="row row-cols-1 row-cols-md-3">
                <c:if test="${!empty list}">
                <c:forEach items="${list}" var="dto">
                <div class="col mb-4" onclick="location.href='<%=request.getContextPath() %>/upload_content.do?no=${dto.getUpload_no() }'">
                    <div class="card h-100">
                        <img src="<%=request.getContextPath()%>/upload/${dto.getUpload_file()}" class="card-top" alt="" />
                        <div class="card-body">
                            <p class="card-text">${dto.getUpload_writer()}</p>
                            <h5 class="card-title">${dto.getUpload_title()}</h5>
                        </div>
                        <div class="card-footer">
                            <small class="text-muted">${dto.getUpload_date().substring(0, 10)}</small>
                        </div>
                    </div>
                </div>
                </c:forEach>
                </c:if>

                <c:if test="${empty list}">
                <div class="py-5 text-center">No Data</div>
                </c:if>
            </div>



            <div class="row mt-2 list-bottom-util">
                <div class="col-12 text-right"><a href="<%=request.getContextPath()%>/upload_write.do" class="btn btn-success"><i class="fa fa-pencil mr-1"></i> 새로운 글쓰기</a></div>
            </div>



        </div>
    </main>
    

	<%-- <div align="center">
		<hr width="50%" color="marmoon">
		<h3>Upload 게시판 전체 리스트 페이지</h3>
		<hr width="50%" color="marmoon">
		<br>

		<table border="1" cellspacing="0" width="500">
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
						<td>${dto.getUpload_no() }</td>
						<td>
						<a href="<%=request.getContextPath() %>/upload_content.do?no=${dto.getUpload_no() }">
	               						${dto.getUpload_title() } </a>
	               		</td>
						<td>${dto.getUpload_hit() }</td>
						<td>${dto.getUpload_date().substring(0, 10) }</td>
					</tr>
				</c:forEach>
			</c:if>

			<c:if test="${empty list }">
				<tr>
					<td colspan="4" align="center">
						<h3>조회된 글이 없습니다.</h3>
					</td>
				</tr>

			</c:if>
			
			<tr>
	         <td colspan="4" align="center">
	            <input type="button" value="글쓰기"
	               onclick="location.href='upload_write.do'">
	         </td>
	      </tr>

		</table>
	</div> --%>

</body>
</html>