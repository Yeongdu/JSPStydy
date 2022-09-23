<%@page import="com.products.model.ProductDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
List<ProductDTO> product = (List<ProductDTO>)request.getAttribute("pList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<br />
	<div align="center">
		<hr width="50%" color="DarkTurquoise">
		<h3>PRODUCTS 테이블 전체 제품 리스트</h3>
		<hr width="50%" color="DarkTurquoise">
		<br> <br>

		<table border="1" cellspacing="0">
			<tr>
				<th>제품No.</th>
				<th>카테고리 cod</th>
				<th>제품명</th>
				<th>제조사</th>
			</tr>

			<%
				if(product.size() != 0) {
				for (int i = 0; i < product.size(); i++) {
					ProductDTO dto = product.get(i);
			%>

			<tr>
				<td><%=dto.getPnum()%></td>
				<td><%=dto.getCategory_fk()%></td>
				<td><a
					href="<%=request.getContextPath()%>/content.do?pnum=<%=dto.getPnum()%>">
						<%=dto.getProducts_name()%></a></td>
				<%
				if(dto.getCompany() == null) {
				%>
				<td></td>
				<%}else{ %>
				<td><%=dto.getCompany()%></td>

				<%} %>

			</tr>

			<%   } //for문 end
			} else {
			//데이터가 없는 경우
			%>
			<tr>
				<td colspan="4" align="center">
					<h3>전체 제품 목록이 없습니다...</h3>
				</td>
			</tr>
			<%
				}
			%>

			<tr>
				<td colspan="4" align="right"><input type="button" value="제품등록"
					onclick="location.href='insert.do'"/></td>
			</tr>

		</table>

	</div>
</body>
</html>