<%@page import="com.products.model.ProductDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	ProductDTO cont = (ProductDTO)request.getAttribute("content");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color="IndianRed">
		<h3>PRODUCTS 테이블 제품 상세 목록</h3>
		<hr width="50%" color="IndianRed">
		<br> <br>
		<table border="1" cellspacing="0" width="400">
			<%
				if (cont != null) {//데이터가 있다면
			%>
			<tr>
				<th>제품No.</th>
				<td><%=cont.getPnum()%></td>
			</tr>
			<tr>
				<th>카테고리코드</th>
				<td><%=cont.getCategory_fk()%></td>
			</tr>
			<tr>
				<th>제품명</th>
				<td><%=cont.getProducts_name()%></td>
			</tr>
			<tr>
				<th>제품코드</th>
				<td><%=cont.getEp_code_fk()%></td>
			</tr>
			<tr>

				<th>입고가</th>
				<td><%=cont.getInput_price()%></td>
			</tr>
			<tr>

				<th>출고가</th>
				<td><%=cont.getOutput_price()%></td>
			</tr>
			<tr>

				<th>배송비</th>
				<td><%=cont.getTrans_cost()%></td>
			</tr>
			<tr>

				<th>마일리지</th>
				<td><%=cont.getMileage()%></td>
			</tr>
			<tr>
				<th>제조사</th>
				<td><%=cont.getCompany()%></td>
			</tr>


			<%
			} else { //데이터가 없다면
		%>

			<tr>
				<td colspan="2" align="center">
					<h3>제품 목록이 없습니다...</h3>
				</td>
			</tr>
			<%
				}
			%>

			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="제품수정"
					onclick="location.href='update.do?num=<%=cont.getPnum()%>'">
					&nbsp;&nbsp; <input type="submit" value="제품삭제"
					onclick="if(confirm('정말로 삭제하시겠습니까?')){
						location.href='delete.do?num=<%=cont.getPnum()%>'
					}else { return; }">
					&nbsp;&nbsp; <input type="button" value="제품목록"
					onclick="location.href='select.do'"></td>
			</tr>

		</table>
	</div>

</body>
</html>