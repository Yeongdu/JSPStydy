<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" --%>
<%--     pageEncoding="UTF-8"%> --%>
<!-- <!DOCTYPE html> -->
<!-- <html> -->
<!-- <head> -->
<!-- <meta charset="UTF-8"> -->
<!-- <title>Insert title here</title> -->
<!-- </head> -->
<!-- <body> -->
<!-- 	<div align="center"> -->
<!-- 		<hr width="50%" color="gray"> -->
<!-- 		<h3>DEPT 테이블 부서 추가 폼 페이지</h3> -->
<!-- 		<hr width="50%" color="gray"><br /><br /> -->
<!-- 		<form method="post" action="insertOk"> -->
<!-- 			<table border="1" cellspacing="0"> -->
<!-- 				<tr> -->
<!-- 					<th>부서No.</th> -->
<!-- 					<td> <input type="text" name="deptNo"> </td> -->
<!-- 				</tr> -->
				
<!-- 				<tr> -->
<!-- 					<th>부서명</th> -->
<!-- 					<td> <input type="text" name="deptName"> </td> -->
<!-- 				</tr> -->
				
<!-- 				<tr> -->
<!-- 					<th>부서위치</th> -->
<!-- 					<td> <input type="text" name="deptLoc"> </td> -->
<!-- 				</tr> -->
				
<!-- 				<tr> -->
<!-- 					<td colspan="2" align="center"> -->
<!-- 						<input type="submit" value="부서추가"> -->
<!-- 						<input type="reset" value="다시작성"> -->
<!-- 					</td> -->
<!-- 				</tr> -->
<!-- 			</table> -->
<!-- 		</form> -->
<!-- 	</div> -->

<!-- </body> -->
<!-- </html> -->




<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>부서(DEPT) 테이블</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" crossorigin="anonymous"></script>
</head>
<body>
    <div style="width: 400px; margin: 0 auto; text-align: center;">
        <hr color="blue" />
        <h3>DEPT 테이블 추가</h3>
        <hr color="blue" />

        <br />

        <form method="post" action="insertOk" class="border-top">
            <div class="form-group row border-bottom py-2">
                <label for="deptNo" class="col-sm-4 col-form-label">부서No.</label>
                <div class="col-sm-8">
                    <input type="text" name="deptNo" id="deptNo" class="form-control" />
                </div>
            </div>
            <div class="form-group row border-bottom py-2">
                <label for="deptName" class="col-sm-4 col-form-label">부서명</label>
                <div class="col-sm-8">
                    <input type="text" name="deptName" id="deptName" class="form-control" />
                </div>
            </div>
            <div class="form-group row border-bottom py-2">
                <label for="deptLoc" class="col-sm-4 col-form-label">부서위치</label>
                <div class="col-sm-8">
                    <input type="text" name="deptLoc" id="deptLoc" class="form-control" />
                </div>
            </div>
            <div class="form-group row mt-4">
                <div class="col-12">
                    <button type="button" class="btn btn-outline-secondary" onclick="location.href='select';">목록보기</button>
                    <button type="submit" class="btn btn-primary">추가하기</button>
                    <button type="reset" class="btn btn-outline-secondary">다시작성</button>
                </div>
            </div>
        </form>
    </div>
</body>
</html>