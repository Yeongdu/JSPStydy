package com.products.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.products.model.CategoryDTO;
import com.products.model.ProductDAO;
import com.products.model.ProductDTO;
import java.util.List;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/update.do")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get방식으로 넘어온 제품번호에 해당하는 제품의 정보를 조회해서 수정폼페이지로 이동시키는 비지니스 로직
		
		
		int product_no = Integer.parseInt(request.getParameter("pnum").trim());
		
		ProductDAO dao = ProductDAO.getInstance();
		dao.getContentProduct(product_no);
		ProductDTO content =  dao.getContentProduct(product_no);
		List<CategoryDTO> categoryList = dao.getCategoryList();
		request.setAttribute("modify", content);
		request.setAttribute("List", categoryList);
		
		RequestDispatcher rd = request.getRequestDispatcher("view/product_modify.jsp");
		rd.forward(request, response);
		
		
	}

}
