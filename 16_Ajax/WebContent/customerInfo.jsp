<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>

<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
<script type="text/javascript" src="js/customer.js"></script>
<style type="text/css">

	span {
		width: 150px;
		color: red;
	}
	
	input {
		border: 1px solid gray;
	}
	
	table {
		width: 85%;
	}
	
	.table_bg {
		background-color: LightCyan;
	}
	
	th, td {
		border: 1px solid gray;
		text-align: center;
		padding: 3px;
	}
</style>
</head>
<body>
	<div align="center">
		<hr width="85%" color="Teal">
		<h3>회원가입 폼 페이지</h3>
		<hr width="85%" color="Teal">
		<br>

		<form method="post" name="inForm" id="inForm">
			<table border="1" cellspacing="0" width="350">
				<tr class="table_bg">
					<th>아이디</th>
					<th>이 름</th>
					<th>나 이</th>
					<th>연락처</th>
					<th>주 소</th>
				</tr>

				<tr>
					<td><input type="text" name="id" id="id" size="15"> <span>중복결과여부</span>
					</td>
					<td><input type="text" name="name" id="name" size="15"></td>
					<td><input type="text" name="age" id="age" size="3"></td>
					<td><input type="text" name="phone" id="phone" size="20"></td>
					<td><input type="text" name="addr" id="addr" size="40"></td>
				</tr>

				<tr>
					<td colspan="5" align="center">
					<input type="button" class="btn btn-outline-secondary" value="가입하기" id="btn">
					</td>
				</tr>
			</table>
		</form>

		<br>

		<hr width="85%" color="Teal">
		<br>
		<h2>고객 리스트</h2>
		<table id="listTable" cellspacing="0">
			<tr>
				<th>번 호</th>
				<th>아이디</th>
				<th>이 름</th>
				<th>나 이</th>
				<th>연락처</th>
				<th>주 소</th>
				<th>삭 제</th>
			</tr>

		</table>



	</div>

</body>
</html>