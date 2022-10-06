package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardUpdateOkAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub

		int board_no = Integer.parseInt(request.getParameter("board_no").trim());
		int nowPage = Integer.parseInt(request.getParameter("page").trim());


		String board_writer = request.getParameter("writter").trim();
		String board_title = request.getParameter("title").trim();
		String board_cont = request.getParameter("content").trim();
		String board_pwd = request.getParameter("pwd").trim();

		BoardDTO dto = new BoardDTO();

		dto.setBoard_no(board_no);
		dto.setBoard_writer(board_writer);
		dto.setBoard_title(board_title);
		dto.setBoard_cont(board_cont);
		dto.setBoard_pwd(board_pwd);
		

		BoardDAO dao = BoardDAO.getInstance();
		int check = dao.getBoardUpdate(dto);
		PrintWriter out = response.getWriter();
		// request.setAttribute("Page", nowPage);

		if (check > 0) {//location.href='board_update.do?no=${dto.getBoard_no() }&page=${Page }'"
			out.println("<script> alert('게시글 수정 성공!');" + "location.href='board_content.do?no="+board_no+"&page="+ nowPage+ "'" + "</script>");
		} else {
			out.println("<script> alert('게시글 수정 실패! 비밀번호를 확인하세요.');" + "history.back(); </script>");
		}
	}

}
