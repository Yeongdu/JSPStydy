<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
<h2>두 수 더하기(애노테이션 (1:1) 등록)</h2>
<form action="adder">
<p>첫 번째 수 : <input type="text" name="num1"></p>
<p>두 번째 수 : <input type="text" name="num2"></p>
<input type="submit" value="계산">
</form>

<hr>

<h2>두 수 더하기(web.xml파일에 등록)</h2>
<form method="post" action="adder1">
<p>첫 번째 수 : <input type="text" name="num1"></p>
<p>두 번째 수 : <input type="text" name="num2"></p>
<input type="submit" value="계산">
</form>

</div>

<%-- 서블릿 매핑 방법 2가지
	1. 배포서술자(web.xml) 등록
	- 매핑할 서블릿이 많은 경우 일괄 처리 용이
	- web.xml 파일에 등록
	
	2. 애노테이션 등록
	 - Servlet 클래스의 url-mapping에 등록
     - 1:1 방식으로 등록
     - 설정 파일이 필요 없다.

    ※ Servlet의 매핑 목적
     - 처리 프로그램을 url 에서 숨긴다. 파일명과 폴더명(패키지)까지 숨긴다.
     - 보안을 목적으로 숨긴다 .
 --%>>

</body>
</html>