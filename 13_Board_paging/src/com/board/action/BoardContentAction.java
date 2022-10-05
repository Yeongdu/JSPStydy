package com.board.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardContentAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// get방식으로 넘어온 글번호에 해당하는 게시글의 상세내역을 DB에서 조회하여 view page로 이동시키는 비지니스 로직

		int board_no = Integer.parseInt(request.getParameter("no").trim());
		int nowPage = Integer.parseInt(request.getParameter("page").trim());
		
		BoardDAO dao = BoardDAO.getInstance();
		
		//글 제목 클릭 시 조회수 증가시키는 메서드 호출
		dao.getBoardHit(board_no);
		BoardDTO content = dao.getBoardContent(board_no);
		request.setAttribute("Cont", content);
		request.setAttribute("Page", nowPage);
	}

}
