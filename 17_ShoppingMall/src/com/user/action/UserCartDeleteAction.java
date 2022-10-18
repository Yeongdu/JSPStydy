package com.user.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.CartDAO;

public class UserCartDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// get방식으로 넘어온 장바주니 번호에 해당하는 장바구니 내역을 삭제하는 비지니스 로직
		
		int cart_num = Integer.parseInt(request.getParameter("num").trim());
		
		CartDAO dao = CartDAO.getInstance();
		int check = dao.deleteCart(cart_num);
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		
		if(check > 0) { 
		forward.setRedirect(true);
		forward.setPath("user_cart_list.do");
		}else {
			out.println("<script> alert('장바구니 삭제 실패'); history.back(); </script>;");
		}
		return forward;
	}

}
