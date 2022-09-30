package com.board1.model;

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

	// DB와 연동하는 객체
	Connection con = null;

	// DB에 SQL문을 전송하는 객체
	PreparedStatement pstmt = null;

	// SQL문을 실행한 후 결과값을 가지고 있는 객체
	ResultSet rs = null;

	// 쿼리문을 저장할 변수
	String sql = null;

	// BoardDAO객체를 싱글턴 방식으로 만들어보자
	// 1단계 : 싱글턴 방식으로 객체를 만들기 위해서 우선적으로 기본생성자의 접근제어자를 public이 아닌 private으로 바꿔주어야한다
	// 즉, 외부에서 기본생성자를 호출하지 못하게 하는 방법

	// 2단계 : BoardDAO 객체를 정적(static) 멤버로 선언

	private static BoardDAO instance; // 2

	private BoardDAO() {	} // 기본생성자 //1

	// 3단계 : 기본 생성자 대신 싱글턴 객체를 return해주는 getInstance() 라는 메서드를 만들어
	// 해당 getInstance() 메서드를 외부에서 접근할 수 있도록 해준다.

	public static BoardDAO getInstance() {
		if (instance == null) {
			instance = new BoardDAO(); // 객체 생성하면 주소값이 나온다. 그 주소값을 instance멤버변수에 지정
		}
		return instance;
	}

	public void openConn() {
		
		try {
			// 1단계 : JNDI 서버 객체 생성
			Context ctx = new InitialContext();
			
			// 2단계 : lookup() 메서드를 이용하여 매칭되는 커넥션을 찾는다.
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/myoracle");
			
			// 3단계 : DataSource 객체를 이용하여 커넥션을 하나 가져온다.
			con = ds.getConnection();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}// openConn end

	public void closeConn(ResultSet rs, PreparedStatement pstmt, Connection con) {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}// closeConn end
	
	
	
	//board 테이블의 전체 레코드를 조회하는 메서드
	public List<BoardDTO> getBoardList() {
		
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		try {
		openConn();
		sql = "select * from board order by board_no desc";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
			
		while(rs.next()) {
			BoardDTO dto = new BoardDTO();
			
			dto.setBoard_no(rs.getInt("board_no"));
			dto.setBoard_writer(rs.getString("board_writer"));
			dto.setBoard_title(rs.getString("board_title"));
			dto.setBoard_cont(rs.getString("board_cont"));
			dto.setBoard_pwd(rs.getString("board_pwd"));
			dto.setBoard_hit(rs.getInt("board_hit"));
			dto.setBoard_date(rs.getString("board_date"));
			dto.setBoard_update(rs.getString("board_update"));
			
			list.add(dto);
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}

		return list;
	}//getBoardList end
	
	
	//새 글 쓰기
	public int insertBoard(BoardDTO dto) {
		int result = 0, count = 0;
		
		try {
		openConn();
		sql = "select max(board_no) from board";
		pstmt=con.prepareStatement(sql);
		rs=pstmt.executeQuery();
		
		if(rs.next()) {
			count = rs.getInt(1)+1;
		}
		
		sql = "insert into board values(?,?,?,?,?,default,sysdate,'')";
		pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, count);
		pstmt.setString(2, dto.getBoard_writer());
		pstmt.setString(3, dto.getBoard_title());
		pstmt.setString(4, dto.getBoard_cont());
		pstmt.setString(5, dto.getBoard_pwd());
		result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}//insertBoard end
	
	
	//조회수 증가
	public void boardHit(int num) {
		try {
		openConn();
		sql = "update board set board_hit = board_hit + 1 where board_no=?";
		pstmt=con.prepareStatement(sql);
		
		pstmt.setInt(1, num);
		pstmt.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
	}//boardHit end
	
	public BoardDTO getContentBoard(int num) {
		BoardDTO dto = null;

		try {
			openConn();
			sql = "select * from board where board_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto = new BoardDTO();
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_writer(rs.getString("board_writer"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_pwd(rs.getString("board_pwd"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_date(rs.getString("board_date"));
				dto.setBoard_update(rs.getString("board_update"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	}//getContentBoard end
	
	public int updateBoard(BoardDTO dto) {
		int result = 0;
		
		try {
		openConn();
		sql = "select * from board where board_no=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, dto.getBoard_no());
		rs = pstmt.executeQuery();
		if(rs.next()) {
			if(dto.getBoard_pwd().equals(rs.getString("board_pwd"))) {
				sql = "update board set board_title = ?, board_cont = ?, board_update = sysdate where board_no = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, dto.getBoard_title());
				pstmt.setString(2, dto.getBoard_cont());
				pstmt.setInt(3, dto.getBoard_no());
				result = pstmt.executeUpdate();
			}else {
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
	}//updateBoard end
	
	
	public int deleteBoard(String pwd, int no) {
		int result = 0;
		try {
			openConn();
			sql= "select * from board where board_no = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(pwd.equals(rs.getString("board_pwd"))) {
					sql = "delete from board where board_no = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, no);
					result = pstmt.executeUpdate();
				}else {
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
	}//deleteBoard end
	
	public void updateSequence(int no) {
		try {
		openConn();
		sql = "update board set board_no = board_no-1 where board_no > ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, no);
		pstmt.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
	}//updateSequence end
	
	
	
	public List<BoardDTO> searchboard(String field, String keyword) {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		openConn();
		if(field.equals("title")) {
			try {
			sql = "select * from board where board_title like ? order by board_no desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_writer(rs.getString("board_writer"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_pwd(rs.getString("board_pwd"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_date(rs.getString("board_date"));
				dto.setBoard_update(rs.getString("board_update"));
				
				list.add(dto);
			}
	
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeConn(rs, pstmt, con);
			}
	}else if (field.equals("cont")) {
		try {
			sql = "select * from board where board_cont like ? order by board_no desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_writer(rs.getString("board_writer"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_pwd(rs.getString("board_pwd"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_date(rs.getString("board_date"));
				dto.setBoard_update(rs.getString("board_update"));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
	}else if (field.equals("title_cont")) {
		try {
		sql = "select * from board where board_cont like ? or board_title like ? order by board_no desc";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, "%"+keyword+"%");
		pstmt.setString(2, "%"+keyword+"%");
		rs = pstmt.executeQuery();
		while(rs.next()) {
			BoardDTO dto = new BoardDTO();
			dto.setBoard_no(rs.getInt("board_no"));
			dto.setBoard_writer(rs.getString("board_writer"));
			dto.setBoard_title(rs.getString("board_title"));
			dto.setBoard_cont(rs.getString("board_cont"));
			dto.setBoard_pwd(rs.getString("board_pwd"));
			dto.setBoard_hit(rs.getInt("board_hit"));
			dto.setBoard_date(rs.getString("board_date"));
			dto.setBoard_update(rs.getString("board_update"));
			
			list.add(dto);
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
	}else {
		try {
			sql = "select * from board where board_writer like ? order by board_no desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_writer(rs.getString("board_writer"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_pwd(rs.getString("board_pwd"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_date(rs.getString("board_date"));
				dto.setBoard_update(rs.getString("board_update"));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
	}
		return list;
	}//searchboard end
	
	
}
	
	

