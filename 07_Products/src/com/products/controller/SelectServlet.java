package com.products.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.products.model.ProductDAO;
import com.products.model.ProductDTO;

import java.util.List;


@WebServlet("/select.do")
public class SelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 제품 전체 목록 요청에 대한 응답
		//ProductDAO dao = new ProductDAO; //기본 생성자 private 해서 이걸로는 접근이 안된다.
		
		ProductDAO dao =  ProductDAO.getInstance();//getInstance하면 리턴되는 값은 ProductDAO생성값... 그걸 참조변수dao에 저장
		//싱글턴방식으로 만든 주소값을 dao에 넣어준다.
		
		System.out.println("dao 객체 생성 후 >>> " + dao);
		
		List<ProductDTO> productList = dao.getProductList();
		
		request.setAttribute("pList", productList);
		RequestDispatcher rd = request.getRequestDispatcher("view/product_list.jsp");

		rd.forward(request, response);
	}

}
