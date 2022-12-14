package com.member.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.action.*;

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
		if(command.equals("select.do")) {
			action = new MemberListAction();
		}else if(command.equals("insert.do")) {
			action = new MemberJoinAction();
		}else if(command.equals("insert_ok.do")) {
			action = new MemberJoinOkAction();
		}else if(command.equals("content.do")) {
			action = new MemberContent();
		}else if(command.equals("modify.do")) {
			action = new MemberModifyAction();
		}else if(command.equals("modify_ok.do")) {
			action = new MemberModifyOkAction();
		}else if(command.equals("delete.do")) {
			action = new MemberDeleteAction();
		}else if(command.equals("delete_ok.do")) {
			action = new MemberDeleteOkAction();
		}
		
		
		//path1 = "view/member_list.jsp";
		String path1 = action.execute(request, response);
		
		//페이지 이동
		RequestDispatcher rd = request.getRequestDispatcher(path1);
		rd.forward(request, response);
		
	}

}
