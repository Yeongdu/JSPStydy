package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;




public class BoardDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 
		
		String board_pwd = request.getParameter("pwd").trim();
		int board_no = Integer.parseInt(request.getParameter("no").trim());
		BoardDAO dao = BoardDAO.getInstance();
		
		
		dao.updateSequence(board_no);
		int res = dao.getBoardDelete(board_no, board_pwd);
		PrintWriter out = response.getWriter();
		
		if (res > 0) {//location.href='board_update.do?no=${dto.getBoard_no() }&page=${Page }'"
			dao.updateSequence(board_no);
			out.println("<script> alert('게시글 삭제 성공!');" + "location.href='board_list.do'" + "</script>");
		} else {
			out.println("<script> alert('게시글 삭제 실패! 비밀번호를 확인하세요.');" + "history.back(); </script>");
		}

	}

}
