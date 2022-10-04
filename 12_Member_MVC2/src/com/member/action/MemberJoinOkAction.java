package com.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

public class MemberJoinOkAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 회원등록 폼페이지에서 넘어온 데이터들을 MEMBER10 테이블에 회원으로 등록하는 비지니스 로직
		
		// 1 단계 : 회원등록 폼 페이지에서 넘어온 데이터들을 받아주자
		String member_id = request.getParameter("mem_id").trim();
		String member_name = request.getParameter("mem_name").trim();
		String member_pwd = request.getParameter("mem_pwd").trim();
		int member_age = Integer.parseInt(request.getParameter("mem_age").trim());
		int member_mileage = Integer.parseInt(request.getParameter("mem_mileage").trim());
		String member_job = request.getParameter("mem_job").trim();
		String member_addr = request.getParameter("mem_addr").trim();
		
		
		// 2 단계 : 데이터들을 DTO 객체의 setter() 메서드를 인자로 넘겨주어서 멤버변수에 초기값으로 할당
		MemberDTO dto = new MemberDTO();
		
		dto.setMemid(member_id);
		dto.setMemname(member_name);
		dto.setPwd(member_pwd);
		dto.setAge(member_age);
		dto.setMileage(member_mileage);
		dto.setJob(member_job);
		dto.setAddr(member_addr);
		
		// 3 단계 : DTO 객체를 DAO 객체의 호출 메서드 인자로 넘겨서 MEMBER10 테이블의 회원으로 저장하자
		MemberDAO dao = MemberDAO.getInstance();
		int res = dao.insertMember(dto);
		PrintWriter out = response.getWriter();
		
		if(res > 0) {
			out.println("<script>");
			out.println("alert('회원등록성공')");
			out.println("location.href='select.do'");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('회원등록실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return null;
	}

}
