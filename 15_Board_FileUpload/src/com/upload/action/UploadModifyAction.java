package com.upload.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.upload.model.UploadDAO;
import com.upload.model.UploadDTO;

public class UploadModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		
		int upload_no = Integer.parseInt(request.getParameter("no").trim());
		UploadDAO dao = UploadDAO.getInstance();
		dao.uploadContent(upload_no);
		UploadDTO dto = dao.uploadContent(upload_no);
		request.setAttribute("Modify", dto);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("view/upload_modify.jsp");
		
		return forward;
	}

}
