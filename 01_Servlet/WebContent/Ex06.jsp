<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- [문제] 성적 처리 폼 페이지 만들기
		이름, 국어, 영어, 수학, 자바 점수를 입력 받을 수 있는 폼을 만들고
		정보를 입력받아 성적을 처리해 웹브라우저 화면에 출력해주세요 --%>

	<div align="center">
		<h2>성적 처리 페이지</h2>
		<form method="post" action="studentScore">
			<table border="1" cellspacing="0">
				<tr>
					<th>이름</th>
					<td><input type="text" name="student_name"></td>
				</tr>
				
				<tr>
					<th>국어</th>
					<td><input type="number" name="student_han" min="0" max="100" maxlength="3" /></td>
				</tr>
				
				<tr>
					<th>영어</th>
					<td><input type="number" name="student_eng" min="0" max="100" maxlength="3" /></td>
				</tr>
				
				<tr>
					<th>수학</th>
					<td><input type="number" name="student_math" min="0" max="100" maxlength="3" /></td>
				</tr>
				
				<tr>
					<th>자바</th>
					<td><input type="number" name="student_java" min="0" max="100" maxlength="3" /></td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="등록">&nbsp;&nbsp;
						<input type="reset" value="취소"></td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>