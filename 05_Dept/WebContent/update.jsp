<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	int deptno = Integer.parseInt(request.getParameter("deptno"));
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div style="width: 400px; margin: 0 auto; text-align: center;">
		<hr color="blue" />
		<h3>DEPT 테이블 수정</h3>
		<hr color="blue" /><br />

		<form method="post" action="updateOk" class="border-top">
			<div class="form-group row border-bottom py-2">
				<label for="deptNo" class="col-sm-4 col-form-label">부서No.</label>
				<div class="col-sm-8">
					<input type="text" name="deptNo" id="deptNo" value="<%=deptno%>" class="form-control-plaintext" readonly />
				</div>
			</div>
			<div class="form-group row border-bottom py-2">
				<label for="deptName" class="col-sm-4 col-form-label">부서명</label>
				<div class="col-sm-8">
					<input type="text" name="reName" id="deptName"
						class="form-control" />
				</div>
			</div>
			<div class="form-group row border-bottom py-2">
				<label for="deptLoc" class="col-sm-4 col-form-label">부서위치</label>
				<div class="col-sm-8">
					<input type="text" name="reLoc" id="deptLoc" class="form-control" />
				</div>
			</div>
			<br />
			<div class="form-group row mt-4">
				<div class="col-12">
					<button type="button" class="btn btn-outline-secondary"
						onclick="location.href='select';">목록보기</button>
					<button type="submit" class="btn btn-primary">수정완료</button>
					<button type="reset" class="btn btn-outline-secondary">다시작성</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>