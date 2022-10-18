package com.user.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.CartDAO;
import com.shop.model.CartDTO;

public class UserCartAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 제품상세내역에서 장바구니 버튼을 클릭하면 폼 태그 안의 테이터들을 장바구니 테이블 안에 저장하는 비지니스 로직
		String cart_name = request.getParameter("p_name").trim();
		int cart_price = Integer.parseInt(request.getParameter("p_price").trim());
		int cart_pqty =  Integer.parseInt(request.getParameter("p_qty").trim());
		
		//히든으로 넘어온 데이터 받아주기
		int cart_num =  Integer.parseInt(request.getParameter("p_num").trim());
		String cart_pspec = request.getParameter("p_spec").trim();
		String cart_pimage = request.getParameter("p_image").trim();
		String userId = request.getParameter("userId").trim();
		
		CartDTO dto = new CartDTO();
		dto.setCart_pnum(cart_num);
		dto.setCart_pname(cart_name);
		dto.setCart_pqty(cart_pqty);
		dto.setCart_price(cart_price);
		dto.setCart_pspec(cart_pspec);
		dto.setCart_pimage(cart_pimage);
		dto.setCart_userid(userId);
		
		CartDAO dao = CartDAO.getInstance();
		int check = dao.insertCart(dto);
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		
		if(check > 0) {
			forward.setRedirect(true);
			forward.setPath("user_cart_list.do");
		}else {
			out.println("<script> alert('장바구니 등록 실패'); history.back(); </script>");
		}
		
		return forward;
	}

}
