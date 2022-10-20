package com.reply1.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply1.controller.Action;
import com.reply1.controller.ActionForward;
import com.reply1.model.BoardDAO;
import com.reply1.model.BoardDTO;

public class BoardContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// get 방식으로 넘어온 글번호에 해당하는 게시글에 해당하는 상세글을 view page로 이동시키는 비지니스로직
		
		int board_no = Integer.parseInt(request.getParameter("no").trim());
		
		BoardDAO dao = BoardDAO.getInstance();
		BoardDTO dto = dao.getBoardContent(board_no);
		
		request.setAttribute("Cont", dto);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("view/board_content.jsp");
		
		return forward;
	}

}
