package com.emp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.model.DeptDTO;
import com.emp.model.EmpDAO;
import com.emp.model.EmpDTO;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/update.do")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int empno = Integer.parseInt(request.getParameter("no").trim());
		
		EmpDAO dao = EmpDAO.getInstance();
		
		// EMP 테이블에서 담당업무 리스트를 조회하자
        List<String> jobList = dao.getJobList();

        // EMP 테이블에서 담당업무가 "MANAGER"인 사원 리스트를 조회하자
        List<EmpDTO> mgrList = dao.getMgrList();

        // 부서 테이블 전체 리스트를 조회하자
        List<DeptDTO> deptList = dao.getDeptList();
        
        EmpDTO dto = dao.getContentEmp(empno);

        // 페이지 이동 시 바인딩된 값들도 같이 넘겨주자
        request.setAttribute("jList", jobList);
        request.setAttribute("mList", mgrList);
        request.setAttribute("dList", deptList);
        request.setAttribute("modify", dto);

        request.getRequestDispatcher("view/emp_modify.jsp").forward(request, response);
		
	}

}
