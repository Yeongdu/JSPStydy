package com.member.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

public class MemberModifyAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// get 방식으로 넘어온 회원번호에 해당하는 회원의 정보를 DB에서 조회하여 수정 폼 페이지로 이동시키는 비지니스 로직
		int member_no = Integer.parseInt(request.getParameter("num"));
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberDTO dto = dao.getMemberContent(member_no);
		request.setAttribute("Modify", dto);
		return "view/member_modify.jsp";
	}

}
