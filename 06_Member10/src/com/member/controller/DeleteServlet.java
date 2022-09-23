package com.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/delete.do")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get 방식으로 넘어온 회원 번호에 해당하는 회원을 MEMBER10 테이블에서 삭제하는 비지니스 로직
		
		// 한글 인코딩 작업
		response.setContentType("text/html; charset=UTF-8");
		
		int member_no = Integer.parseInt(request.getParameter("num").trim());
		
		MemberDAO dao = new MemberDAO();	//dao 객체 생성
		
		int check = dao.deleteMember(member_no);
		
		dao.updateNum(member_no); //회원번호 재작업
		
		PrintWriter out = response.getWriter();
		
		if(check > 0) {
			out.println("<script>");
			out.println("alert('회원삭제성공 ~ ')");
			out.println("location.href='select.do'");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('회원 삭제 실패 !!!')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		
		
	}

}
