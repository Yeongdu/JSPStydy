package com.member.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberDeleteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// get방식으로 넘어온 회원번호를 회원삭제폼 페이지로 이동시키는 비지니스 로직
		
		int member_no = Integer.parseInt(request.getParameter("num").trim());
		request.setAttribute("No", member_no);
		
		
		return "view/member_delete.jsp";
	}

}
