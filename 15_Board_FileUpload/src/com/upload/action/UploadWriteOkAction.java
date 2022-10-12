package com.upload.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.upload.model.UploadDAO;
import com.upload.model.UploadDTO;

public class UploadWriteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 자료실 폼 페이지에서 넘어온 데이터들을 DB에 저장하는 비지니스 로직
		
		UploadDTO dto = new UploadDTO();
		
		//파일 업로드 시에 설정 내용이 있음. 
		
		// 1. 파일 저장 경로 설정
		String saveFolder = "C:\\Users\\user1\\git\\JSPStydy\\15_Board_FileUpload\\WebContent\\upload";
		
		// 2. 첨부 파일 크기 지정
		int fileSize = 10 * 1024 * 1024; //10MB
		
		// 3. MultipartRequest 객체 생성 ==> 파일업로드 진행하기 위한 객체 생성
		MultipartRequest multi = new MultipartRequest(request, saveFolder, fileSize, "UTF-8", 
				new DefaultFileRenamePolicy()); //파일 이름 같은 경우 중복안되게 설정
		
		String upload_writer = multi.getParameter("upload_writer").trim(); 
		String upload_title = multi.getParameter("upload_title").trim(); 
		String upload_cont = multi.getParameter("upload_cont").trim(); 
		String upload_pwd = multi.getParameter("upload_pwd").trim(); 
		
		//자료실 폼페이지에서 type="file" 로 되어있으면 getFile() 메서드로 받아주어야 한다
		File upload_file = multi.getFile("upload_file");
		
		
		if(upload_file != null) {
			
			//우선 첨부파일의 이름을 알아야한다.
			String fileName = upload_file.getName();
			
			//날짜 객체 생성
			Calendar cal =  Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH) + 1;
			int day = cal.get(Calendar.DAY_OF_MONTH);
			
			//...../upload/2022-10-11
			String homedir = saveFolder+"/"+year+"-"+month+"-"+day;
			
			//날짜 폴더를 만들어보자
			File path1 = new File(homedir);
			if(!path1.exists()) { //폴더가 존재하지 않는다면
				path1.mkdir();//실제 폴더를 만들어주는 메서드
			}

			//파일을 만들어보자 ==> 예) 홍길동_파일명
			//...../upload/2022-10-11/홍길동_파일명
			
			String reFileName = upload_writer+"_"+fileName;
			upload_file.renameTo(new File(homedir+"/"+reFileName));
			
			//실제로 DB에 저장되는 파일 이름
			//"/2022-10-11/홍길동_파일명" 으로 저장할 예정
			String fileDBName = "/"+year+"-"+month+"-"+day+"/"+reFileName;
			dto.setUpload_file(fileDBName);
		}
		
		
		dto.setUpload_writer(upload_writer);
		dto.setUpload_title(upload_title);
		dto.setUpload_cont(upload_cont);
		dto.setUpload_pwd(upload_pwd);
		
		UploadDAO dao = UploadDAO.getInstance();
		int res = dao.insertUpload(dto);
		
		ActionForward forward = new ActionForward();
		
		PrintWriter out = response.getWriter();

		if (res > 0) {
			forward.setRedirect(true);
			forward.setPath("upload_list.do");
		} else {
			out.println("<script> alert('업로드 실패.'); history.back(); </script>");
		}
		
		return forward;
	}

}
