<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<% 
	Cookie[] cookies = request.getCookies(); //쿠키가 여러개일수도 있으니 배열 형태로 반환
	if(cookies!=null) {
		for(int i=0; i<cookies.length; i++){
			out.println("cookies["+i+"] name : " +cookies[i].getName()+"<br>"); // 웹브라우저에 출력시키는 방법
			out.println("cookies["+i+"] name : " +cookies[i].getValue()+"<br>");			
		}
	}


%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>