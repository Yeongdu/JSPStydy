package com.upload.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.upload.model.UploadDAO;
import com.upload.model.UploadDTO;

public class UploadDeleteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		
		int upload_no = Integer.parseInt(request.getParameter("no").trim());
		String upload_pwd = request.getParameter("upload_pwd").trim(); 
		
		UploadDAO dao = UploadDAO.getInstance();
		
		//글번호에 해당하는 게시글 상세내역 조회 
		UploadDTO dto = dao.uploadContent(upload_no);
		
		String upload = "C:\\Users\\user1\\git\\JSPStydy\\15_Board_FileUpload\\WebContent\\upload";
		
		String fileName = dto.getUpload_file();
		
		ActionForward forward = new ActionForward();
		
		PrintWriter out = response.getWriter();

		if(upload_pwd.equals(dto.getUpload_pwd())) {
			int res = dao.delteUpload(upload_no);
			if (fileName != null) {//첨부파일 존재
				File file = new File(upload + fileName);
				file.delete();
			}
			
			if(res > 0) {
				forward.setRedirect(true);
				forward.setPath("upload_list.do");
			}else {
				out.println("<script> alert('삭제 실패.'); history.back(); </script>");
			}
		}else { //비밀번호 틀린경우
			out.println("<script> alert('비밀번호가 틀립니다.'); history.go(-1); </script>");
		}
		
		return forward;
	}

}
