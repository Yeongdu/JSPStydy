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


@WebServlet("/insertOk")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// insert.jsp페이지에서 넘어온 데이터들을 DB에 저장시키는 비지니스 로직
		
		// 한글 인코딩 작업
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// 1단계 : 부서등록 폼페이지에서 넘어온 데이터들을 받아주기
		int deptno = Integer.parseInt(request.getParameter("deptNo").trim());
		String deptName = request.getParameter("deptName").trim();
		String location = request.getParameter("deptLoc").trim();

		// 2단계 : DTO 객체를 이용하여 DB에 데이터를 전송해야
		DeptDTO dto = new DeptDTO();
		
		dto.setDeptno(deptno);
		dto.setDname(deptName);
		dto.setLoc(location);
		
		// 3단계 : DB에 DTO객체 전송
		DeptDAO dao = new DeptDAO();
		int res = dao.insertDept(dto);
		//매서드 호출
		dao.insertDept(dto);
		
		PrintWriter out = response.getWriter();
		if(res>0) {//부서추가 성공한 경우
			out.println("<script>");
			out.println("alert('부서추가성공@');");
			out.println("location.href='select'");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('부서추가실패@');");
			out.println("history.back()");//이전페이지 이동
			out.println("</script>");
		}
		
	
	}

}
