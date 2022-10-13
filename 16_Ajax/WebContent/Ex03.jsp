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
            url : "data/book.xml",
            datatype : "xml",
            success : function(data) {
                $(data).find("book").each(function() {
                	let title = $("title", this).text();
                	let author = $("author", this).text();
                	let price = $("price", this).text();
                	
                	let txt = "<li> 제목 : " +title+ "</li>" +
                	 			"<li> 저자 : " +author+ "</li>" +
                	 			"<li> 가격 : " +price+ "</li><hr>" ;
                	 
                	$("body").append(txt);
                });
            },

            error : function(data) {
                alert("데이터 통신 오류입니다.");
            }
        });
    });

</script>
</head>
<body>

</body>
</html>