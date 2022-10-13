<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
<script type="text/javascript">
	function process() {
		$.ajax({
			type : "post",
			url : "data/test.jsp",
			data : {param : "Hello Ajax!"}, //test.jsp로 넘어갈 변수명과 값
			datatype : "text",
			success : function(data) {
				$(".message").append(data);
			},
			error : function(data) {
				alert("에러가 발생했습니다.");
			}
		});
	}
</script>
</head>
<body>
	<div align="center">
		<input type="button" value="전송하기" onclick="process()"> <br /><br />
		<div class="message"></div>
	
	</div>

</body>
</html>