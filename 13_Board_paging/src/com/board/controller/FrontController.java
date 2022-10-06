package com.board.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.action.*;

public class FrontController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException, ServletException {
		
		//한글 인코딩 처리
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		//getRequestURI() : "/프로젝트명/파일명(*.do)"라는 문자열을 반환해주는 메서드
		String uri = request.getRequestURI();
		System.out.println("uri >>> "+ uri);
		
		//.getContextPath() : 현재 프로젝트명을 문자열로 반환해주는 메서드
		String path = request.getContextPath();
		System.out.println("Path >>> "+ path);
		
		
		String command = uri.substring(path.length()+1);
		System.out.println("command >>> "+ command);
		
		Action action = null;
		String viewPage = null;
		if(command.equals("board_list.do")) {
			action = new BoardListAction();
			action.execute(request, response);
			viewPage="view/board_list.jsp";
		}else if(command.equals("board_write.do")) {
			viewPage="view/board_write.jsp";
		}else if(command.equals("board_write_OK.do")) {
			action = new BoardWriteAction();
			action.execute(request, response);
		}else if(command.equals("board_content.do")) {
			action = new BoardContentAction();
			action.execute(request, response);
			viewPage="view/board_content.jsp";
		}else if(command.equals("board_update.do")) {
			action = new BoardUpdateAction();
			action.execute(request, response);
			viewPage="view/board_update.jsp";
		}else if(command.equals("board_update_OK.do")) {
			action = new BoardUpdateOkAction();
			action.execute(request, response);
		}else if(command.equals("board_delete_ok.do")) {
			action = new BoardDeleteAction();
			action.execute(request, response);
		}else if(command.equals("board_search.do")) {
			action = new BoardSearchAction();
			action.execute(request, response);
			viewPage="view/board_search.jsp";
		}
		
		request.getRequestDispatcher(viewPage).forward(request, response);
		
	}

}
