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

details {
    display: inline-block;
}


</style>
</head>
<body>
<br>
	<div align="center">
		<c:set var="dto" value="${Cont }" />
		<h3>${dto.getBoard_writer() }님 게시물 상세 내역</h3>
		<input type="hidden" name="page" value="${Page }">
		<hr width="50%" color="Turquoise">
		<br>
		
		<table class="table table-bordered" style="width: 36em">
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
				<th>제목</th>
				<td colspan="3">${dto.getBoard_title() }</td>
			</tr>

			<tr>
				
				<td colspan="4" style="word-break:break-all; padding:15px;" width="400" >${dto.getBoard_cont() }</td>
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
					onclick="location.href='board_update.do?no=${dto.getBoard_no() }&page=${Page }'">&nbsp;&nbsp;
					
					<input type="button" value="목록으로" id="btn3" class="btn btn-outline-dark"
					onclick="location.href='board_list.do?page=${Page }'">
					
					
					<%-- <input type="button" value="글삭제" id="btn2" class="btn btn-outline-danger"
					onclick="if(confirm('게시글을 삭제하시겠습니까?')) {
					location.href='board_delete.do?no=${dto.getBoard_no() }&page=${Page }'
					} else {return; }"> --%>
					<br><br />
					<details>
						<summary class="btn btn-outline-danger">삭제</summary>
						<form method="post"
							action="<%=request.getContextPath()%>/board_delete_ok.do">
							
							<input type="hidden" name="no" value="${dto.getBoard_no() }">


							<div class="mb-3 row" style="padding-top: 8px">
								<label for="exampleFormControlInput1"
									class="col-sm-2 col-form-label">비번</label>
								<div class="col-sm-10">
									<input type="password" name="pwd" class="form-control">
								</div>
								
								<input type="submit"
										value="글 삭제" class="btn btn-outline-danger" style="margin-top: 8px"> &nbsp;&nbsp; <input type="reset"
										value="다시작성" class="btn btn-outline-primary">
							</div>
							
							


							<!-- <table style="display: line-block;">
								<tr style="display: line-block;">
									<th width="90">비밀번호</th>
									<td><input type="password" name="pwd"
										style="width: 250px; height: 20px; display: inline-block;"></td>
								</tr>

								<tr>
									<td colspan="2" align="center"><input type="submit"
										value="글 삭제"> &nbsp;&nbsp; <input type="reset"
										value="다시작성"></td>
								</tr>
							</table> -->

						</form>

					</details> 
					
					
				</td> 
			
			</tr>
            
         
		</table>
	</div>

</body>
</html>