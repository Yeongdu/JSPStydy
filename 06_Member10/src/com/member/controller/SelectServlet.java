package com.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.*;


@WebServlet("/select.do")
public class SelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public SelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// MEMBER10테이블의 전체회원목록을 화면에 보여달라고 클라이언트가 요청
				//응답 : DB에서 MEMBER10테이블의 회원 전체목록을 조회하여 해당 전체 리스트를 view page로 넘겨주면 된다.
				
				//1단계 : 오라클 DB와 연동 작업
				MemberDAO dao = new MemberDAO();
				
				//2단계 MEMBER10테이블의 회원 전체목록 조회
				List<MemberDTO> memberList = dao.memberList();
				
				//3단계 DB에서 가져온 정보를 view page로 이동 시켜야 한다.
				request.setAttribute("member", memberList);
				
				RequestDispatcher rd = request.getRequestDispatcher("view/member_list.jsp");//view폴더 만들어서 그안에 member_list.jsp 만들어서 거기로 페이지 이동
				
				rd.forward(request, response);
	}

}
