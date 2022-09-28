package com.board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

 
@WebServlet("/insert.do")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	
    public InsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

 
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 폼에서 입력된 정보들을 DB로 넘겨주기
		
		// 한글 인코딩 작업
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		//정보 받기
		String board_writter = request.getParameter("writter").trim();
		String board_title = request.getParameter("title").trim();
		String board_content = request.getParameter("content").trim();
		String board_pwd = request.getParameter("pwd").trim();
		
		
		//DB에 전송을 해주어야 한다. 이때 DTO객체의 setter() 메서드의 인자에 넘겨받은 정보들을 넘겨주면된다.
		BoardDTO dto = new BoardDTO();
		
		dto.setBoard_writer(board_writter);
		dto.setBoard_title(board_title);
		dto.setBoard_cont(board_content);
		dto.setBoard_pwd(board_pwd);
		
		//해당DTO객체를 DB에 전송: DAO 객체
		BoardDAO dao = BoardDAO.getInstance();
		
		int res = dao.insertBoard(dto);
		PrintWriter out = response.getWriter();

		if (res > 0) {
			out.println("<script>");
			out.println("alert('게시글 등록 성공')");
			out.println("location.href='select.do'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('게 시 글 등 록 실 패')");
			out.println("history.back()");
			out.println("</script>");
		}
		
	}

}
