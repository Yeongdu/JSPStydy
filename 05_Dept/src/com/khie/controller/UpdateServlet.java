package com.khie.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.khie.model.DeptDAO;
import com.khie.model.DeptDTO;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/updateOk")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 수정폼 페이지에서 넘어온 정보들을 DB에서 부서번호에 해당하는 정보를 수정하는 비지니스 로직

        // 한글 인코딩 설정 작업
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        // 1단계 : 수정 폼 페이지에서 넘어온 정보들을 받아야 한다.
        int deptNo = Integer.parseInt(request.getParameter("deptNo"));
        String dname = request.getParameter("reName").trim();
        String loc = request.getParameter("reLoc").trim();

        // 2단계 : DTO 객체에 수정 폼 페이지에서 넘어온 정보들을 저장해주자
        DeptDTO dto = new DeptDTO();

        dto.setDeptno(deptNo);
        dto.setDname(dname);
        dto.setLoc(loc);

        // 3단계 : DB에 DTO 객체를 전송
        DeptDAO dao = new DeptDAO();
        int res = dao.updateDept(dto);

        PrintWriter out = response.getWriter();

        if(res > 0) {    // 부서 수정이 성공한 경우
            out.println("<script>");
            out.println("alert('부서 수정 성공!')");
            out.println("location.href='select'");
            out.println("</script>");
        }else {        // 부서 수정이 실패한 경우
            out.println("<script>");
            out.println("alert('부서 수정 실패!')");
            out.println("history.back()"); // 이전 페이지로 이동
            out.println("</script>");
        }



    }

}
