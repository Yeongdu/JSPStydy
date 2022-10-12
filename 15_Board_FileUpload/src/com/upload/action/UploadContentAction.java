package com.upload.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.upload.model.UploadDAO;
import com.upload.model.UploadDTO;

public class UploadContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// get 방식으로 넘어온 글번호에 해당하는 게시글의 상세정보를 조회
		
		int upload_no = Integer.parseInt(request.getParameter("no").trim());
		
		UploadDAO dao = UploadDAO.getInstance();
		
		//조회수 증가 메서드 호출
		dao.uploadHit(upload_no);
		
		//글번호에 해당하는 상세내역을 조회하는 메서드 호출
		UploadDTO content = dao.uploadContent(upload_no);
		
		request.setAttribute("upCont", content);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("view/upload_content.jsp");

		
		return forward;
	}

}
