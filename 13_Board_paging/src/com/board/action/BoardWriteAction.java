package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardWriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		
		String board_writter = request.getParameter("writter").trim();
		String board_title = request.getParameter("title").trim();
		String board_content = request.getParameter("content").trim();
		String board_pwd = request.getParameter("pwd").trim();
		
		BoardDTO dto = new BoardDTO();
		dto.setBoard_writer(board_writter);
		dto.setBoard_title(board_title);
		dto.setBoard_cont(board_content);
		dto.setBoard_pwd(board_pwd);
		
		BoardDAO dao = BoardDAO.getInstance();
		int check = dao.BoardWrite(dto);
		PrintWriter out = response.getWriter();
		
		if (check > 0) {
            out.println("<script> alert('글이 등록되었습니다'); location.href='board_list.do'; </script>");
		}else {
            out.println("<script> alert('게시글 등록 실패'); history.back(); </script>");
        }

	}

}
