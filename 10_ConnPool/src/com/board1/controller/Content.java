package com.board1.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board1.model.BoardDAO;
import com.board1.model.BoardDTO;


@WebServlet("/content.do")
public class Content extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Content() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int board_no = Integer.parseInt(request.getParameter("no"));
		BoardDAO dao = BoardDAO.getInstance();
		
		dao.boardHit(board_no);
		BoardDTO cont = dao.getContentBoard(board_no);
		
		request.setAttribute("Content", cont);
		request.getRequestDispatcher("view/board_content2.jsp").forward(request, response);
	}

}
