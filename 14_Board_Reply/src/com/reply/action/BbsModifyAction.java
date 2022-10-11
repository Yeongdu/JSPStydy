package com.reply.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.BbsDAO;
import com.reply.model.BbsDTO;

public class BbsModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// get 방시긍로 넘어온 글번호에 해당하는 상세내역을
		// 수정 폼 페이지로 이동시키는 비지니스 로직
		
		int bbs_no = 
			Integer.parseInt(request.getParameter("no").trim());
		
		BbsDAO dao = BbsDAO.getInstance();
		
		BbsDTO dto = dao.getBbsContent(bbs_no);
		
		request.setAttribute("Modify", dto);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		
		forward.setPath("view/bbs_modify.jsp");
		
		return forward;
	}

}
