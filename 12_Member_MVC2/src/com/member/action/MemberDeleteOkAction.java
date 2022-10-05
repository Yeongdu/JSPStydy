package com.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;

public class MemberDeleteOkAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 삭제 폼 페이지에서 넘어온 회원번호와 입력한 비밀번호를 가지고 DB에 해당하는 회원을 삭제하는 비지니스 로직
		
		String member_pwd = request.getParameter("mem_pwd").trim();
		int member_no = Integer.parseInt(request.getParameter("mem_no").trim());
		MemberDAO dao = MemberDAO.getInstance();
		int res = dao.deleteMember(member_no,member_pwd);
		
		PrintWriter out = response.getWriter();
		
		if (res > 0) {
			dao.updateSequence(member_no);
			out.println("<script> alert('회원 삭제 성공!'); location.href='select.do'; </script>");
		} else if (res == -1) {
			out.println("<script> alert('비밀번호가 틀립니다.'); history.back(); </script>");
		} else {
			out.println("<script> alert('회원삭제실패'); history.back(); </script>");
		}
		return null;
	}

}
