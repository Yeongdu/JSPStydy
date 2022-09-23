package com.products.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.products.model.ProductDAO;
import com.products.model.ProductDTO;

/**
 * Servlet implementation class InsertOkSetvlet
 */
@WebServlet("/insert_ok.do")
public class InsertOkSetvlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public InsertOkSetvlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 제품등록 페이지에서 넘어온 데이터들을 DB의 products테이블에 저장하는 비지니스 로직
		
		// 한글 인코딩 작업
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String product_category =request.getParameter("product_category").trim();
		String products_name =request.getParameter("products_name").trim();
		String products_code =request.getParameter("products_code").trim();
		int input_price =Integer.parseInt(request.getParameter("input_price").trim());
		int output_price =Integer.parseInt(request.getParameter("output_price").trim());
		int trans_cost =Integer.parseInt(request.getParameter("trans_cost").trim());
		int mileage =Integer.parseInt(request.getParameter("mileage").trim());
		String company =request.getParameter("company").trim();
		
		ProductDTO dto = new ProductDTO();
		
		dto.setCategory_fk(product_category);
		dto.setProducts_name(products_name);
		dto.setEp_code_fk(products_code);
		dto.setInput_price(input_price);
		dto.setOutput_price(output_price);
		dto.setTrans_cost(trans_cost);
		dto.setMileage(mileage);
		dto.setCompany(company);
		
		ProductDAO dao = ProductDAO.getInstance();
		System.out.println("제품 등록시 dao >>> " + dao);
		
		int check = dao.insertProduct(dto);
		PrintWriter out = response.getWriter();
		
		if(check > 0) {
			out.println("<script>");
			out.println("alert('제품등록성공')");
			out.println("location.href='select.do'");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('제품등록실패')");
			out.println("history.back()");
			out.println("</script>");
		}
	}

}
