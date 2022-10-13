<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
<script type="text/javascript">

	  $(function() {
        $.ajax({
            type : "post",
            url : "data/data.json",
            datatype : "json",
            success : function(data) {
            	
            	// index : data객체 내의 인덱스를 의미
            	// item : data객체 내의 이름과 값을 가지고있는 객체를 말한다.
            	$.each(data, function(index, item){
            		let txt = "<li> 제목 : " +item.title+"</li>" +
    	 						"<li> 저자 : " +item.author+ "</li>" +
    	 						"<li> 가격 : " +item.price+ "</li><hr>" ;
    	 						
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