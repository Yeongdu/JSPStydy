<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div align="center">
	   <hr width="50%" color="gray">
	      <h3>JSP_BBS 테이블 답변형 게시판 수정 폼 페이지</h3>
	   <hr width="50%" color="gray">
	   <br>
	   
	   <c:set var="dto" value="${Modify }" />
	   <form method="post"
	      action="<%=request.getContextPath() %>/bbs_modify_ok.do">
	      
	      <input type="hidden" name="no" value="${dto.getBoard_no() }">
	      
	      <table border="1" cellspacing="0" width="400">
		     <tr> 
		        <th>작성자</th>
		        <td>
		           <input name="writer" readonly
		              value="${dto.getBoard_writer() }">
		        </td>
		     </tr>
		     
		     <tr> 
		        <th>글 제목</th>
		        <td>
		           <input name="title"
		              value="${dto.getBoard_title() }">
		        </td>
		     </tr>
		     
		     <tr> 
		        <th>글 내용</th>
		        <td>
		           <textarea rows="7" cols="25" name="content">${dto.getBoard_cont() }</textarea>
		        </td>
		     </tr>
		     
		     <tr> 
		        <th>글 비밀번호</th>
		        <td>
		           <input type="password" name="pwd">
		        </td>
		     </tr>
		     
		     <tr>
		        <td colspan="2" align="center">
		           <input type="submit" value="글수정">
		               &nbsp;&nbsp;&nbsp;
		           <input type="reset" value="다시작성">
		        </td>
		     </tr>
		     
		  </table>
	   </form>
	
	</div>
</body>
</html>