package com.reply.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.BbsDAO;
import com.reply.model.BbsDTO;

public class BbsModifyOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 수정 폼 페이지에서 넘어온 데이터들을
		// DB에서 수정하는 비지니스 로직.
		
		String bbs_writer = 
			request.getParameter("writer").trim();
		
		String bbs_title = 
				request.getParameter("title").trim();
		
		String bbs_content = 
				request.getParameter("content").trim();
		
		String bbs_pwd = 
				request.getParameter("pwd").trim();
		
		int bbs_no = 
			Integer.parseInt(request.getParameter("no").trim());
		
		
		BbsDTO dto = new BbsDTO();
		
		dto.setBoard_no(bbs_no);
		
		dto.setBoard_writer(bbs_writer);
		
		dto.setBoard_title(bbs_title);
		
		dto.setBoard_cont(bbs_content);
		
		dto.setBoard_pwd(bbs_pwd);
		
		BbsDAO dao = BbsDAO.getInstance();
		
		int res = dao.updateBbs(dto);
		
		ActionForward forward = new ActionForward();
		
		PrintWriter out = response.getWriter();
		
		if(res > 0) {
			forward.setRedirect(true);
			forward.setPath("bbs_content.do?no="+bbs_no);
		}else if(res == -1) {
			out.println("<script>");
			out.println("alert('비밀번호가 틀립니다.')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('답변형 게시판 게시글 수정 실패~~~')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}

}
