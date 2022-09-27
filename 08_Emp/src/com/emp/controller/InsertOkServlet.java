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
 * Servlet implementation class InsertOkServlet
 */
@WebServlet("/insert_ok.do")
public class InsertOkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public InsertOkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	 
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 
		
		// 한글 인코딩 작업
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		int eNum = Integer.parseInt(request.getParameter("num").trim());
		String eName = request.getParameter("name").trim();
		String eJob = request.getParameter("job").trim();
		int eMgr = Integer.parseInt(request.getParameter("mgr").trim());
		int eSal = Integer.parseInt(request.getParameter("sal").trim());
		int eComm = Integer.parseInt(request.getParameter("comm").trim());
		int eDept = Integer.parseInt(request.getParameter("dept").trim());
		
		EmpDTO dto = new EmpDTO();
		
		dto.setEmpno(eNum);
		dto.setEname(eName);
		dto.setJob(eJob);
		dto.setMgr(eMgr);
		dto.setSal(eSal);
		dto.setComm(eComm);
		dto.setDeptno(eDept);
		
		EmpDAO dao = EmpDAO.getInstance();
		
		int check = dao.insertEmp(dto);
		PrintWriter out = response.getWriter();

		if (check > 0) {
			out.println("<script>");
			out.println("alert('회원추가성공 ~ ')");
			out.println("location.href='select.do'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('회원추가 실패 !!')");
			out.println("history.back()");
			out.println("</script>");
		}

	}

}
