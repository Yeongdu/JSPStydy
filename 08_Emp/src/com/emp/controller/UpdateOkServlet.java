package com.emp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.model.EmpDAO;
import com.emp.model.EmpDTO;

/**
 * Servlet implementation class UpdateOkServlet
 */
@WebServlet("/update_ok.do")
public class UpdateOkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateOkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 수정 폼 페이지에서 넘어온 정보들을 DB에서 수정하는 비니지스 로직
		
		// 한글 인코딩 작업
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		int re_empno = Integer.parseInt(request.getParameter("num").trim());
		String re_name = request.getParameter("name").trim();
		String re_job = request.getParameter("job").trim();
		int re_mgr = Integer.parseInt(request.getParameter("mgr").trim());
		int re_sal = Integer.parseInt(request.getParameter("sal").trim());
		int re_comm = Integer.parseInt(request.getParameter("comm").trim());
		int re_dept = Integer.parseInt(request.getParameter("deptno").trim());
		
		EmpDTO dto = new EmpDTO();
		
		dto.setEmpno(re_empno);
		dto.setEname(re_name);
		dto.setJob(re_job);
		dto.setMgr(re_mgr);
		dto.setSal(re_sal);
		dto.setComm(re_comm);
		dto.setDeptno(re_dept);
		
		EmpDAO dao = EmpDAO.getInstance();
		int check = dao.modifyEmp(dto);
		PrintWriter out = response.getWriter();
		
		if(check > 0) {
			out.println("<script>");
			out.println("alert('사원정보수정성공 ~ ')");
			out.println("location.href='content.do?empno="+dto.getEmpno()+"'");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('사원 정보 수정 실패 !')");
			out.println("history.back()");
			out.println("</script>");
		}
	}

}
