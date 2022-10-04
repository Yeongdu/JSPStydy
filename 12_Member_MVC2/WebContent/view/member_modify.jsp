<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color="Coral">
		<h3>MEMBER10 테이블 회원 수정 폼 페이지</h3>
		<hr width="50%" color="Coral">
		<br> <br>
		<c:set var="dto" value="${Modify }" />

		<form method="post"
			action="<%=request.getContextPath()%>/modify_ok.do">
			<input type="hidden" name="mem_no" value="${dto.getNum() }">
			<input type="hidden" name="db_pwd" value="${dto.getPwd() }">
			<table border="1" cellspacing="0" width="350">
				<c:if test="${!empty dto}">
					<tr>
						<th>아이디</th>
						<td><input type="text" name="mem_id"
							value="${dto.getMemid() }" readonly></td>
					</tr>

					<tr>
						<th>이름</th>
						<td><input type="text" name="mem_name"
							value="${dto.getMemname() }"></td>
					</tr>

					<tr>
						<th>비밀번호</th>
						<td><input type="password" name="mem_pwd"></td>
					</tr>

					<tr>
						<th>나이</th>
						<td><input type="text" name="mem_age"
							value="${dto.getAge() }"></td>
					</tr>

					<tr>
						<th>마일리지</th>
						<td><input type="text" name="mem_mileage"
							value="${dto.getMileage() }"></td>
					</tr>

					<tr>
						<th>직업</th>
						<td><input type="text" name="mem_job"
							value="${dto.getJob() }"></td>
					</tr>

					<tr>
						<th>주소</th>
						<td><input type="text" name="mem_addr"
							value="${dto.getAddr() }"></td>
					</tr>
				</c:if>

				<c:if test="${ empty dto }">
                    <tr>
                        <td colspan="2" align="center">
                            회원 정보가 없습니다....
                        </td>
                    </tr>
                </c:if>
                    <tr>
                        <td colspan="2" align="center">
                            <button type="submit">회원수정</button>&nbsp;&nbsp;
                            <button type="reset">다시작성</button>
                        </td>
                    </tr>

			</table>
		</form>
	</div>
</body>
</html>