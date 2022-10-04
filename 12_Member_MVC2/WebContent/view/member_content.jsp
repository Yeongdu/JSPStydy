<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color="gray">
		<h3>MEMBER10 상세 페이지</h3>
		<hr width="50%" color="gray">
		<br>
		<table border="1" cellspacing="0" width="400">

			<!-- value에 넘어온 키 이름쓰면 getAttribute 한거랑 똑같아진다. -->
			<c:set var="cont" value="${Content }" />
			<c:if test="${!empty cont }">

				<tr>
					<th>회원No.</th>
					<td>${cont.getNum() }</td>
				</tr>

				<tr>
					<th>아이디</th>
					<td>${cont.getMemid() }</td>
				</tr>

				<tr>
					<th>비밀번호</th>
					<td><c:if test="${cont.getPwd().length() != null }">
							<c:forEach begin="1" end="${cont.getPwd().length() }">
						*
						</c:forEach>
						</c:if></td>
				</tr>

				<tr>
					<th>나이</th>
					<td>${cont.getAge() }</td>
				</tr>

				<tr>
					<th>마일리지</th>
					<td><fmt:formatNumber>${cont.getMileage() }</fmt:formatNumber></td>
				</tr>

				<tr>
					<th>직업</th>
					<td>${cont.getJob() }</td>
				</tr>

				<tr>
					<th>주소</th>
					<td>${cont.getAddr() }</td>
				</tr>

				<tr>
					<th>회원가입일</th>
					<td>${cont.getRegdate().substring(0,10) }</td>
				</tr>

			</c:if>

			<c:if test="${empty cont }">
				<tr>
					<td colspan="4" align="center">
						<h3>회원 상세정보가 없습니다...</h3>
					</td>
				</tr>
			</c:if>

			<tr>
			<td colspan="2" align="center">
				<input type="button" value="수정" onclick="location.href='modify.do?num=${cont.getNum() }'">
				<input type="button" value="삭제" onclick="if(confirm('회원을 진짜로 삭제하시겠습니까?')) {
					location.href='delete.do?num=${cont.getNum() }'}else{return;}">
					<input type="button" value="전체회원" onclick="location.href='select.do'">
			</td>

			</tr>


		</table>
	</div>
</body>
</html>