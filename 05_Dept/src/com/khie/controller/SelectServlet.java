package com.khie.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.khie.model.DeptDAO;
import com.khie.model.DeptDTO;

/**
 * Servlet implementation class SelectServlet
 */
@WebServlet("/select")
public class SelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// index.jsp 페이지에서 요청 
		// DEPT테이블의 전체 부서 목록을 화면에 보여달라는 요청 (비지니스 로직)
		// 해당 요청에 대해 응답 
		
		// 1단계 : DB와 연결 작업
		// 1단계 시 준비과정 
		// DAO(Data Access Object) 객체 준비
		// DTO(Date Transfer Object) 객체 준비
		
		DeptDAO dao = new DeptDAO(); //DeptDAO객체 생성 : 1단계+2단계 생성작업 DAO객체에 넣어놨다. 이걸 생성하면 DB연동
		
		// 2단계 : DB에서 DEPT테이블의 전체 목록 조회
		List<DeptDTO> deptList = dao.selectList();
		
		// 3단계 : 페이지 이동 작업시 데이터를 같이 넘겨야 한다.
		request.setAttribute("List", deptList); //주소값 넘겨준거. 키 이름 "List"
		
		// 4단계 : 실제로 페이지 이동 진행
		RequestDispatcher rd = request.getRequestDispatcher("select.jsp");
		
		// 실제 페이지 이동 진행
		rd.forward(request, response);
		
		
	}


}
