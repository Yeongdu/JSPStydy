package com.upload.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class UploadDAO {

	// DB와 연동하는 객체.
	Connection con = null;
	
	// DB에 SQL문을 전송하는 객체
	PreparedStatement pstmt = null;
	
	// SQL문을 실행한 후에 결과 값을 가지고 있는 객체.
	ResultSet rs = null;
	
	// 쿼리문을 저장할 변수
	String sql = null;
	
	// UploadDAO 객체를 싱글턴 방식으로 만들어 보자.
	// 1단계 : 싱글턴 방식으로 객체를 만들기 위해서는 우선적으로
	//        기본생성자의 접근제어자를 public이 아닌 private
	//        으로 바꾸어 주어야 한다.
	//        즉, 외부에서 직접적으로 기본생성자를 호출하지
	//        못하게 하는 방법이다.
	
	// 2단계 : UploadDAO 객체를 정적(static) 멤버로 선언을 
	//        해 주어야 한다.
	private static UploadDAO instance;
	
	private UploadDAO() {  }  // 기본 생성자
	
	
	// 3단계 : 기본 생성자 대신에 싱글턴 객체를 return 해 주는
	//        getInstance() 라는 메서드를 만들어서 해당
	//        getInstance() 라는 메서드를 외부에서 접근할 수
	//        있도록 해 주면 됨.
	public static UploadDAO getInstance() {
		
		if(instance == null) {
			instance = new UploadDAO();
		}
		
		return instance;
	}
	
	
	// DB를 연동하는 작업을 진행하는 메서드.
	public void openConn() {
		
		try {
			// 1단계 : JNDI 서버 객체 생성
			Context ctx = new InitialContext();
			
			// 2단계 : lookup() 메서드를 이용하여 매칭되는
			//        커넥션을 찾는다.
			DataSource ds =
				(DataSource)ctx.lookup("java:comp/env/jdbc/myoracle");
			
			// 3단계 : DataSource 객체를 이용하여
			//        커넥션을 하나 가져온다.
			con = ds.getConnection();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}  // openConn() 메서드 end
	
	
	// DB에 연결된 자원 종료하는 메서드.
	public void closeConn(ResultSet rs,
			PreparedStatement pstmt, Connection con) {
		
		try {
			if(rs != null) rs.close();
			
			if(pstmt != null) pstmt.close();
			
			if(con != null) con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}  // closeConn() 메서드 end
	
	
	//upload테이블에서 전체 리스트를 조회하는 메서드
	public List<UploadDTO> getUploadList() {
		List<UploadDTO> list = new ArrayList<UploadDTO>();

		try {
			openConn();
			sql = "select * from upload order by upload_no desc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				UploadDTO dto = new UploadDTO();
				
				dto.setUpload_no(rs.getInt("upload_no"));
				dto.setUpload_writer(rs.getString("upload_writer"));
				dto.setUpload_title(rs.getString("upload_title"));
				dto.setUpload_cont(rs.getString("upload_cont"));
				dto.setUpload_pwd(rs.getString("upload_pwd"));
				dto.setUpload_file(rs.getString("upload_file"));
				dto.setUpload_hit(rs.getInt("upload_hit"));
				dto.setUpload_date(rs.getString("upload_date"));
				dto.setUpload_update(rs.getString("upload_update"));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return list;
	}
	
	
	//upload 테이블에 게시글을 추가하는 메서드
	public int insertUpload(UploadDTO dto) {
		int result = 0, count = 0;

		try {
			openConn();
			sql = "select max(upload_no) from upload";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1) + 1;
			}
			sql = "insert into upload values(?,?,?,?,?,?,default,sysdate,'')";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, count);
			pstmt.setString(2, dto.getUpload_writer());
			pstmt.setString(3, dto.getUpload_title());
			pstmt.setString(4, dto.getUpload_cont());
			pstmt.setString(5, dto.getUpload_pwd());
			pstmt.setString(6, dto.getUpload_file());
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}//insertUpload() end
	
	
	//upload 테이블의 글번호에 해당하는 게시글의 조회수를 증가시키는 메서드
	public void uploadHit(int no) {
		try {
			openConn();
			sql = "update upload set upload_hit = upload_hit + 1 where upload_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
	}//uploadHit() end
	
	
	public UploadDTO uploadContent(int no) {
		UploadDTO dto = null;
		try {
			openConn();
			sql = "select * from upload where upload_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				
				dto = new UploadDTO();
				
				dto.setUpload_no(rs.getInt("upload_no"));
				dto.setUpload_writer(rs.getString("upload_writer"));
				dto.setUpload_title(rs.getString("upload_title"));
				dto.setUpload_cont(rs.getString("upload_cont"));
				dto.setUpload_pwd(rs.getString("upload_pwd"));
				dto.setUpload_file(rs.getString("upload_file"));
				dto.setUpload_hit(rs.getInt("upload_hit"));
				dto.setUpload_date(rs.getString("upload_date"));
				dto.setUpload_update(rs.getString("upload_update"));
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return dto;

	}
	
	public int modifyUpload(UploadDTO dto) {
		int result = 0;
		openConn();

		try {
			sql = "select * from upload where upload_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getUpload_no());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				// 처음부터 물어볼 수는 없음 / rs 가져오지 않았기 때문
				if (dto.getUpload_pwd().equals(rs.getString("upload_pwd"))) { // 비번 대조
					if (dto.getUpload_file() == null) { // 첨부파일을 새로 선택하지 않은 경우
						sql = "update upload set upload_title = ?, upload_cont = ?, upload_update = sysdate "
								+ "where upload_no = ?";
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, dto.getUpload_title());
						pstmt.setString(2, dto.getUpload_cont());
						pstmt.setInt(3, dto.getUpload_no());
					} else { // 수정 폼 페이지에서 첨부파일을 선택한 경우
						sql = "update upload set upload_title = ?, upload_cont = ?, "
								+ "upload_file = ?, upload_update = sysdate where upload_no = ?";
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, dto.getUpload_title());
						pstmt.setString(2, dto.getUpload_cont());
						pstmt.setString(3, dto.getUpload_file());
						pstmt.setInt(4, dto.getUpload_no());
					}
					result = pstmt.executeUpdate();
				} else { // 비번 틀린 경우
					result = -1;
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}
	
	
	public int delteUpload(int no) {
		int result = 0;
		try {
			openConn();
			sql = "delete from upload where upload_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
			sql = "update upload set upload_no = upload_no = 1 where upload_no > ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}
	
	
	
}
