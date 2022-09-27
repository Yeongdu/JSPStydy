package com.emp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.model.DeptDTO;
import com.emp.model.EmpDAO;
import com.emp.model.EmpDTO;


@WebServlet("/content.do")
public class ContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public ContentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//
		int empno= Integer.parseInt(request.getParameter("empno"));
		
		EmpDAO dao = EmpDAO.getInstance();

        EmpDTO cont = dao.getContentEmp(empno);
		
		// 테이블에서 empno의 mgr 번호를 가져와서 그 mgr 번호에 해당하는 사원의 이름을 가져오는 메서드
        String mgrName = dao.getMgrName(empno);

        // 부서 name 획득 메서드
        String deptName = dao.getDeptName(empno);

        request.setAttribute("eContent", cont);
        request.setAttribute("mgrName", mgrName);
        request.setAttribute("deptName", deptName);

        request.getRequestDispatcher("view/emp_content.jsp").forward(request, response);
		
		//
		
//		EmpDAO dao = EmpDAO.getInstance();
//	
//		String cont2 = dao.getDeptName(empno);
//		String cont3 = dao.getMgrName(empno);
//		
//		request.setAttribute("eContent", cont);
//		request.setAttribute("DeptName", cont2);
//		request.setAttribute("MgrName", cont3);
//		RequestDispatcher rd = request.getRequestDispatcher("view/emp_content.jsp");
//		
//		rd.forward(request, response);
//		
		
	}

}
