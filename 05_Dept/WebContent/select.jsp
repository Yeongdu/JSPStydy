<%@page import="com.khie.model.DeptDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    // key에 해당하는 value값 반환함
    // List / DeptDTO import문 필요
    List<DeptDTO> dept = (List<DeptDTO>)request.getAttribute("List");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" crossorigin="anonymous"></script>

</head>
<body>

    <div style="width: 50%; margin: 0 auto; text-align: center;">
        <hr color="blue"/>
            <h3>💙💙PT 테이블 전체 리스트 목록✨✨</h3>
        <hr color="blue"/>
        <br><br>

        <table class="table table-striped border-top align-middle">
            <thead class="thead-dark">
				<tr>
					<th scope="col">부서No.</th>
					<th scope="col">부서명</th>
					<th scope="col">부서위치</th>					
					<th scope="col">부서관리</th>
				</tr>
			</thead>
            <tbody>
				<%
					if (dept.size() != 0) { // 데이터가 있다면
					// 데이터 수만큼 반복해서 화면에 출력
					for (int i = 0; i < dept.size(); i++) {
						DeptDTO dto = dept.get(i);
				%>
                <tr>
                    <th scope="row"><%=dto.getDeptno()%></th>
                    <td><%=dto.getDname()%></td>
                    <td><%=dto.getLoc()%></td>
                    <td>
                        <button class="btn btn-sm btn-outline-success" onclick="location.href='update.jsp?deptno=<%=dto.getDeptno() %>'">수정</button>
                        <a href="delete?deptno=<%=dto.getDeptno()%>" class="btn btn-sm btn-outline-danger" onclick="return confirm('정말 삭제하시겠습니까?');">삭제</a>
                    </td>
                </tr>

            <%
                    }// for문 end

                }else { // 데이터가 없다면

            %>
                <tr>
                    <td colspan="4" align="center">
                        <h3 class="my-5">검색된 데이터가 없습니다.</h3>
                    </td>
                </tr>
            <%
                }
            %>
            
		</tbody>
            <tfoot>
                <tr>
                    <td colspan="4" align="right" class="border-0 pt-3"><button type="button" class="btn btn-primary" onclick="location.href='insert.jsp';">부서추가</button></td>
                </tr>
            </tfoot>
	
        </table>

</body>
</html>