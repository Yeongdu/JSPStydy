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

public class AdminProductModifyOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 상품 수정 폼 페이지에서 넘어온 데이터들을
		// DB에 수정하는 비지니스 로직
		
		// 첨부파일이 저장될 위치(경로) 설정.
		String saveFolder =
				"C:\\Users\\user1\\git\\JSPStydy\\17_ShoppingMall\\WebContent\\upload";
		
		// 첨부파일 용량(크기) 제한 - 파일 업로드 최대 크기
		int fileSize = 10 * 1024 * 1024;  // 10MB
		
		// 이미지파일 업로드를 위한 객체 생성.
		MultipartRequest multi = new MultipartRequest(
				request, 
				saveFolder, 
				fileSize,
				"UTF-8",
				new DefaultFileRenamePolicy()
		);
		
		// 상품 수정 폼 페이지에서 넘어온 데이터들을 받아 주자.
		String product_name = 
			multi.getParameter("p_name").trim();
		
		String product_category = 
			multi.getParameter("p_category").trim();
		
		String product_company = 
			multi.getParameter("p_company").trim();
		
		int product_qty = 
			Integer.parseInt(multi.getParameter("p_qty").trim());
		
		int product_price = 
			Integer.parseInt(multi.getParameter("p_price").trim());
		
		String product_spec = 
			multi.getParameter("p_spec").trim();
		
		String product_content = 
			multi.getParameter("p_content").trim();
		
		int product_point = 
			Integer.parseInt(multi.getParameter("p_point").trim());
		
		int product_no = 
			Integer.parseInt(multi.getParameter("p_num").trim());
		
		// getFilesystemName()
		// ==> 업로드될 파일의 이름을 문자열로 반환해 주는 메서드.
		String product_image =
			multi.getFilesystemName("p_image_new");
		
		if(product_image == null) {
			product_image =
				multi.getParameter("p_image_old");
		}
		
		ProductDTO dto = new ProductDTO();
		
		dto.setPnum(product_no);
		
		dto.setPname(product_name);
		
		dto.setPcategory_fk(product_category);
		
		dto.setPcompany(product_company);
		
		dto.setPimage(product_image);
		
		dto.setPqty(product_qty);
		
		dto.setPrice(product_price);
		
		dto.setPspec(product_spec);
		
		dto.setPcontent(product_content);
		
		dto.setPoint(product_point);
		
		ProductDAO dao = ProductDAO.getInstance();
		
		int check = dao.updateProduct(dto);
		
		ActionForward forward = new ActionForward();
		
		PrintWriter out = response.getWriter();
		
		if(check > 0) {
			
			forward.setRedirect(true);
			
			forward.setPath("admin_product_list.do");
		}else {
			out.println("<script>");
			out.println("alert('상품 정보 수정 실패~~~')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}

}
