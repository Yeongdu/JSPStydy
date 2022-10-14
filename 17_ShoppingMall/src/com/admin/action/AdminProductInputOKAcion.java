package com.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.ProductDAO;
import com.shop.model.ProductDTO;

public class AdminProductInputOKAcion implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 상품 등록 폼 페이지에서 넘어온 데이터들을 DB에 저장하는 비지니스 로직
		
		// 1. 파일 저장 경로 설정
		String saveFolder = "C:\\Users\\user1\\git\\JSPStydy\\17_ShoppingMall\\WebContent\\upload";
		
		// 2. 첨부 파일 크기 지정
		int fileSize = 10 * 1024 * 1024; //10MB

		// 3. MultipartRequest 객체 생성 ==> 파일업로드 진행하기 위한 객체 생성
		MultipartRequest multi = new MultipartRequest(request, saveFolder, fileSize, "UTF-8",
				new DefaultFileRenamePolicy()); // 파일 이름 같은 경우 중복안되게 설정	
		
		String product_name = multi.getParameter("p_name").trim();
		String product_category = multi.getParameter("p_category").trim();
		String product_company = multi.getParameter("p_company").trim();
		int product_qty = Integer.parseInt(multi.getParameter("p_qty").trim());
		int product_price = Integer.parseInt(multi.getParameter("p_price").trim());
		String product_spec = multi.getParameter("p_spec").trim();
		String product_content = multi.getParameter("p_content").trim();
		int product_point = Integer.parseInt(multi.getParameter("p_point").trim());
		
		// getFilesystemName() :업로드 될 파일의 이름을 문자열로 반환해주는 메서드 
		String product_image = multi.getFilesystemName("p_image");
		
		ProductDTO dto = new ProductDTO();
		dto.setPname(product_name);
		dto.setPcategory_fk(product_category);
		dto.setPcompany(product_company);
		dto.setPqty(product_qty);
		dto.setPrice(product_price);
		dto.setPspec(product_spec);
		dto.setPcontents(product_content);
		dto.setPoint(product_point);
		dto.setPimage(product_image);
		
		ProductDAO dao = ProductDAO.getInstance();
		int res = dao.insertProduct(dto);
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		

		if(res>0) {
			forward.setRedirect(true);
			forward.setPath("admin_product_list.do");
		}else {
			out.println("<script> alert('상품 등록 실패'); history.back(); </script>");
		}
		
		return forward;
	}

}
