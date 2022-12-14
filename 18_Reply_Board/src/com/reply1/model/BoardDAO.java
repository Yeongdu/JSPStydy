package com.reply1.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class BoardDAO {
	// DB와 연동하는 객체.
	Connection con = null;
	
	// DB에 SQL문을 전송하는 객체
	PreparedStatement pstmt = null;
	
	// SQL문을 실행한 후에 결과 값을 가지고 있는 객체.
	ResultSet rs = null;
	
	// 쿼리문을 저장할 변수
	String sql = null;
	
	// UserDAO 객체를 싱글턴 방식으로 만들어 보자.
	// 1단계 : 싱글턴 방식으로 객체를 만들기 위해서는 우선적으로
	//        기본생성자의 접근제어자를 public이 아닌 private
	//        으로 바꾸어 주어야 한다.
	//        즉, 외부에서 직접적으로 기본생성자를 호출하지
	//        못하게 하는 방법이다.
	
	// 2단계 : BoardDAO 객체를 정적(static) 멤버로 선언을 
	//        해 주어야 한다.
	private static BoardDAO instance;
	
	private BoardDAO() {  }  // 기본 생성자
	
	
	// 3단계 : 기본 생성자 대신에 싱글턴 객체를 return 해 주는
	//        getInstance() 라는 메서드를 만들어서 해당
	//        getInstance() 라는 메서드를 외부에서 접근할 수
	//        있도록 해 주면 됨.
	public static BoardDAO getInstance() {
		
		if(instance == null) {
			instance = new BoardDAO();
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
	
	
	public List<BoardDTO> getBoardList() {
		
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			openConn();
			sql = "select * from tbl_board order by bno desc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setBno(rs.getInt("bno"));
				dto.setWriter(rs.getString("writer"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setPwd(rs.getString("pwd"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setRegupdate(rs.getString("regupdate"));
				
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
	
	public BoardDTO getBoardContent(int no) {

		BoardDTO dto = null;

		try {

			openConn();
			sql = "select * from tbl_board where bno = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				dto = new BoardDTO();
				dto.setBno(rs.getInt("bno"));
				dto.setWriter(rs.getString("writer"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setPwd(rs.getString("pwd"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setRegupdate(rs.getString("regupdate"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}

		return dto;

	}// getBoardContent() 메서드 end

//	public BoardDTO getBoardContent(int no) {
//		BoardDTO dto = null;
//		try {
//			openConn();
//			sql = "select * from tbl_board where bno = ?";
//			pstmt = con.prepareStatement(sql);
//			pstmt.setInt(1, no);
//			rs = pstmt.executeQuery();
//			if(rs.next()) {
//				dto = new BoardDTO();
//				dto.setBno(rs.getInt("bno"));
//				dto.setWriter(rs.getString("writer"));
//				dto.setTitle(rs.getString("title"));
//				dto.setContent(rs.getString("content"));
//				dto.setPwd(rs.getString("pwd"));
//				dto.setRegdate(rs.getString("regdate"));
//				dto.setRegupdate(rs.getString("regupdate"));
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			closeConn(rs, pstmt, con);
//		}
//		return dto;
//	}

	// 글번호에 해당하는 댓글 리스트를 조회하는 메서드.
		public String getReplyList(int no) {
			
			String result = "";
			
			try {
				openConn();
				
				sql = "select * from tbl_reply "
						+ " where bno = ? "
						+ " order by redate desc";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, no);
				
				rs = pstmt.executeQuery();
				
				result += "<replys>";
				
				while(rs.next()) {
					
					result += "<reply>";
					result += "<rno>" + rs.getInt("rno") +"</rno>";
					result += "<bno>" + rs.getInt("bno") +"</bno>";
					result += "<rewriter>" + rs.getString("rewriter") +"</rewriter>";
					result += "<recont>" + rs.getString("recont") +"</recont>";
					result += "<redate>" + rs.getString("redate") +"</redate>";
					result += "<reupdate>" + rs.getString("reupdate") +"</reupdate>";
					result += "</reply>";
				}
				
				result += "</replys>";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeConn(rs, pstmt, con);
			}
			
			return result;
		}  // getReplyList() 메서드 end
	
	
	
	//글번호에 해당하는 댓글 리스트를 조회
//	public String getReplyList(int no) {
//		String result = "";
//		try {
//			openConn();
//			sql = "select * from tbl_reply where bno = ? order by redate desc";
//			pstmt = con.prepareStatement(sql);
//			pstmt.setInt(1, no);
//			
//			rs = pstmt.executeQuery();
//			result += "<replys>";
//			while(rs.next()) {
//				result += "<reply>";
//				result += "<rno>" + rs.getInt("rno") +"</rno>";
//				result += "<bno>" + rs.getInt("bno") +"</bno>";
//				result += "<rewriter>" + rs.getString("rewriter") +"</rewriter>";
//				result += "<recont>" + rs.getString("recont") +"</recont>";
//				result += "<redate>" + rs.getString("redate") +"</redate>";
//				result += "<reupdate>" + rs.getString("reupdate") +"</reupdate>";
//				result += "</reply>";
//			}
//			result += "</replys>";
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			closeConn(rs, pstmt, con);
//		}
//		return result;		
//	}
	
	//답변글을 tbl_reply 테이블에 저장하는 메서드
	public int replyInsert(ReplyDTO dto) {
		int result = 0, count = 0;
		try {
			openConn();
			sql ="select max(rno) from tbl_reply";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1)+1;
			}
			
			sql = "insert into tbl_reply values(?,?,?,?,sysdate,'')";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, count); //답변글 번호
			pstmt.setInt(2, dto.getBno()); //원글 번호
			pstmt.setString(3, dto.getRewriter());
			pstmt.setString(4, dto.getRecont());
			
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
