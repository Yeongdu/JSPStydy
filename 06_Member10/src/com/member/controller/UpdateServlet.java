package com.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/update.do")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get방식으로 넘어온 회원번호에 해당하는 회원의 정보를 조회해서 수정 폼 페이지로 이동 시키는 비지니스로직
		
		int member_no = Integer.parseInt(request.getParameter("num"));
		
		MemberDAO dao = new MemberDAO();
		MemberDTO cont = dao.getContentMember(member_no); //member_no 넣어주면 반환타입 dto.. MemberDTO에 넣어준다. 한 사람의 상세정보가 들어있다.
		
		request.setAttribute("modify", cont);
		RequestDispatcher rd = request.getRequestDispatcher("view/member_update.jsp");
		rd.forward(request, response);
		
	}

}
