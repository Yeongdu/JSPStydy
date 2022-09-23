package com.khie.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.khie.model.DeptDAO;
import com.khie.model.DeptDTO;


@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 삭제버튼을 누르면 get방식으로 넘어온 부서 번호를 가지고 DB에서 해당 번호 삭제하는 비지니스 로직
		
		response.setContentType("text/html; charset=UTF-8");
		
		// 1단계 : 삭제 눌렀을 때 get방식으로 넘어온 부서번호 받기
		int no = Integer.parseInt(request.getParameter("deptno"));

		// 2단계 : 삭제할 부서번호를 DB에 넘겨주기
		DeptDAO dao = new DeptDAO();
		int res = dao.deleteDept(no);
		
		PrintWriter out = response.getWriter();

	        if(res > 0) {    // 부서 수정이 성공한 경우
	            out.println("<script>");
	            out.println("alert('부서 삭제 성공!')");
	            out.println("location.href='select'");
	            out.println("</script>");
	        }else {        // 부서 수정이 실패한 경우
	            out.println("<script>");
	            out.println("alert('부서 삭제 실패!')");
	            out.println("history.back()"); // 이전 페이지로 이동
	            out.println("</script>");
	        }
	}

}
