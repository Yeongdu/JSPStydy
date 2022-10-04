package com.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

public class MemberModifyOkAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String member_id = request.getParameter("mem_id").trim();
		String member_name = request.getParameter("mem_name").trim();
		String member_pwd = request.getParameter("mem_pwd").trim();
		int member_age = Integer.parseInt(request.getParameter("mem_age").trim());
		int member_mileage = Integer.parseInt(request.getParameter("mem_mileage").trim());
		String member_job = request.getParameter("mem_job").trim();
		String member_addr = request.getParameter("mem_addr").trim();
		
		int member_no = Integer.parseInt(request.getParameter("mem_no").trim());
		String db_pwd = request.getParameter("db_pwd").trim();
		
		MemberDTO dto = new MemberDTO();
		
		dto.setNum(member_no);
		dto.setMemid(member_id);
		dto.setMemname(member_name);
		dto.setPwd(member_pwd);
		dto.setAge(member_age);
		dto.setMileage(member_mileage);
		dto.setJob(member_job);
		dto.setAddr(member_addr);
		
		MemberDAO dao = MemberDAO.getInstance();
		PrintWriter out = response.getWriter();
//		if(dto.getPwd().equals(db_pwd)) {
//			int res = dao.updateMember(dto);
//			if(res > 0) {
//				out.println("<script>");
//				out.println("alert('회원수정성공 ~ ')");
//				out.println("location.href='content.do?num="+dto.getNum()+"'");
//				out.println("</script>");
//			}else {
//				out.println("<script>");
//				out.println("alert('회원 정보 수정 실패 !.')");
//				out.println("history.back()");
//				out.println("</script>");}
//		}else {
//			out.println("<script>");
//			out.println("alert('비밀번호가 틀립니다. 확인하세요.')");
//			out.println("history.back()");
//			out.println("</script>");
//		}
		
		if(dto.getPwd().equals(db_pwd)) {
            int res = dao.updateMember(dto);
            if(res > 0) {
                out.println("<script> alert('성공적으로 회원 정보가 수정되었습니다!');"
                        + "location.href='content.do?num="+dto.getNum()+"'; </script>");
            }else {
                out.println("<script> alert('회원 정보 수정 실패!');"
                        + "history.back(); </script>");
            }
        }else {
            out.println("<script> alert('비밀번호가 틀립니다. 다시 확인해주세요.'); history.back(); </script>");
        }
		
		return null;
	}

}
