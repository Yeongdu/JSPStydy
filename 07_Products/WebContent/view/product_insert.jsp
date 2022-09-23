<%@page import="com.products.model.CategoryDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
List<CategoryDTO> category =
(List<CategoryDTO>)request.getAttribute("cList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div align="center">
		<hr width="50%" color="Coral">
			<h3>PRODUCTS 테이블 제품 등록 폼 페이지</h3>	
		<hr width="50%" color="Coral">
		<br> <br>
		
		<form method="post" action="<%=request.getContextPath() %>/insert_ok.do">
			<table border="1" cellspacing="0" width="350">
			
				<tr>
					<th>카테고리</th>
					<td>
						<select name="product_category">
						<%
							if(category.size() == 0){
						%>
							<option value="">:::카테고리 코드 없음</option>	
						<%
							} else { //카테고리 코드 있는 경우
								for(int i = 0; i<category.size(); i++){
									CategoryDTO dto = category.get(i);
						%>
							<option value="<%=dto.getCategory_code() %>"><%=dto.getCategory_name() %>[<%=dto.getCategory_code() %>]</option>	
						<%
							}
						}
						 %>
                    </select>
					</td>
				</tr>
				
				<tr>
					<th>제품명</th>
					<td><input type="text" name="products_name">  </td>
				</tr>
			
				<tr>
					<th>제품코드</th>
					<td><input type="text" name="products_code">  </td>
				</tr>
			
				<tr>
					<th>입고가</th>
					<td><input type="text" name="input_price">  </td>
				</tr>
				
				<tr>
					<th>출고가</th>
					<td><input type="text" name="output_price">  </td>
				</tr>
				
				<tr>
					<th>배송비</th>
					<td><input type="text" name="trans_cost">  </td>
				</tr>
				
				<tr>
					<th>마일리지</th>
					<td><input type="text" name="mileage">  </td>
				</tr>
				
				<tr>
					<th>제조사</th>
					<td><input type="text" name="company">  </td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="등록">&nbsp;&nbsp;
						<input type="reset" value="다시작성">&nbsp;&nbsp;
						<input type="button" value="목록"
					onclick="location.href='select.do'">
					</td>
				</tr>
				
			
				
			
			</table>
		</form>
		
		
		
	</div>


</body>
</html>