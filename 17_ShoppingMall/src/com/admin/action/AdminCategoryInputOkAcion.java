package com.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.CategoryDAO;

public class AdminCategoryInputOkAcion implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 카테고리등록 폼에서 넘겨온 데이터들을 DB에 저장하는 비지니스 로직
		
		String category_code = request.getParameter("category_code").trim();
		String category_name = request.getParameter("category_name").trim();
		
		CategoryDAO dao = CategoryDAO.getInstance();
		
		int res = dao.insertCategory(category_code, category_name);
		
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		
		if(res>0) {
			forward.setRedirect(true);
			forward.setPath("admin_category_list.do");
		}else {
			out.println("<script> alert('카테고리 코드 등록 실패'); history.back(); </script>");
		}
		
		return forward;
	}

}
