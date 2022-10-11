<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
    <div align="center">
    <hr />
    <h3>게시글 삭제 폼 페이지</h3>
    <hr />
    <br />
    <form method="post" action="<%= request.getContextPath() %>/bbs_delete_ok.do">
        <input type="hidden" name="bbs_no" value="${ param.no }" />
        <table class="table">
            <tr>
                <th>게시글 비밀번호</th>
                <td>
                    <input type="password" name="pwd" />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <button type="submit" class="btn btn-danger" >글삭제</button>&nbsp;&nbsp;&nbsp;
                    <button type="reset" class="btn btn-warning" >다시작성</button>
                </td>
            </tr>
        </table>

    </form>
    </div>
</body>
</html>