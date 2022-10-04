package com.member.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

public class MemberContent implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int member_no = Integer.parseInt(request.getParameter("num"));
		MemberDAO dao = new MemberDAO();
		MemberDTO cont = dao.getMemberContent(member_no);

		request.setAttribute("Content", cont);
		
		return "view/member_content.jsp";
	}

}
