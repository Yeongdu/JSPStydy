<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%
// 유효하지 않다
// 	String userId = request.getParameter("id").trim();
// 	String userPwd = request.getParameter("pwd").trim();

	//Ex02_01.jsp 페이지에서 넘어온 세션 정보도 받을 수 있다.
	String sessionName = (String)session.getAttribute("name");//object타입으로 반환되기 때문에 String으로 형변환
	String sessionAddr = (String)session.getAttribute("addr");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>세션으로 넘어온 데이터</h2>
	<h3>
		세션으로 받은 이름 : <%=sessionName %><br>
		세션으로 받은 주소 : <%=sessionAddr %><br>
	</h3>
	
	<a href="Ex02_05.jsp">다음으로</a>

</body>
</html>