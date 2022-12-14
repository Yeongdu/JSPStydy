package com.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

/**
 * Servlet implementation class SelectServlet
 */
@WebServlet("/select.do")
public class SelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public SelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// DB에서 BOARD테이블의 게시글 전체 목록을 조회하여 넘겨받은 후 view page로 이동시키는 비지니스 로직
		BoardDAO dao = BoardDAO.getInstance();
		List<BoardDTO> BoardList = dao.getBoardList();
		request.setAttribute("bList", BoardList);
		RequestDispatcher rd = request.getRequestDispatcher("view/board_list.jsp");
		rd.forward(request, response);
		
		
	}

}
