<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
<script type="text/javascript">

	//문서의 body 부분을 읽고 난 후 제이쿼리를 실행하라는 의미
	  $(function() {
        $.ajax({
            type : "post",
            url : "data/data.html",
            datatype : "html",
            success : function(data) {
                document.body.innerHTML = data;
            },

            error : function(data) {
                alert("에러가 발생하였습니다.");
            }
        });
    });

</script>
</head>
<body>


</body>
</html>