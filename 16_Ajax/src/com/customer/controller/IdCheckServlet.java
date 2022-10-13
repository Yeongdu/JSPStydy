package com.customer.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;

/**
 * Servlet implementation class IdCheckServlet
 */
@WebServlet("/idCheck.do")
public class IdCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IdCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파라미터로 넘어온 아이디가 customer테이블에 등록되어있는 아이디인지 확인하는 비지니스 로직

        response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		String user_id = request.getParameter("id").trim();
		MemberDAO dao = MemberDAO.getInstance();
		
		
		String res = dao.idCheck(user_id);
		out.println(res);
		
	}

}
