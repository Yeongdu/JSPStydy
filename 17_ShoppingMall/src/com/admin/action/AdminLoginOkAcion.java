package com.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.AdminDAO;
import com.shop.model.AdminDTO;

public class AdminLoginOkAcion implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 관리자 로그인페이지에서 입력한 아이디 비밀번호가 관리자DB상에 있는 일치하는지 확인하는 비니지스 로직
		
		String admin_id = request.getParameter("admin_id").trim();
		String admin_pwd = request.getParameter("admin_pwd").trim();
		
		AdminDAO dao = AdminDAO.getInstance();
		int check = dao.adminCheck(admin_id, admin_pwd);
		
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		
		//세션을 설정하는 방법
		//request.getSession() : HttpSession이 존재하면 현재 HttpSession을 반환하고, 
		//존재하지 않으면 세션을 생성하는 메서드
		HttpSession session = request.getSession();
		
		
		
		if (check > 0) {
			AdminDTO dto = dao.getAdmin(admin_id);
			session.setAttribute("adminId", dto.getAdmin_id());
			session.setAttribute("adminName", dto.getAdmin_name());

			// 세션에 저장된 정보를 가지고 view page로 이동
			forward.setRedirect(false);
			forward.setPath("admin/admin_main.jsp");

		} else if (check == -1) { // 아이디일치. 비번 틀린경우
			out.println("<script> alert('관리자 비밀번호가 틀립니다.'); history.back(); </script>");
		} else { // 관리자 아이디 존재하지 않는경우
			out.println("<script> alert('존재하지 않는 관리자입니다.'); history.back(); </script>");
		}
		
		return forward;
	}

}
