package com.board.model;

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
	Connection con = null;
	PreparedStatement pstmt = null; //DB전송 객체 
	ResultSet rs = null;
	String sql = null;

	private static BoardDAO instance;

	public BoardDAO() {
	}

	public static BoardDAO getInstance() {
		if (instance == null) {
			instance = new BoardDAO();
		}

		return instance;
	}

	// ========================================================
	// DB 연동하는 작업을 진행하는 메서드
	// ========================================================
	public void openConn() {
		try {
			// 1단계 : JDNI 객체 생성
			Context ctx = new InitialContext();

			// 2단계 : lookup() 메서드를 이용하여 매칭되는 커넥션을 찾는다.
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/myoracle");

			// 3단계 : DataSource 객체를 이용하여 커넥션을 하나 가져온다.
			con = ds.getConnection();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ========================================================
	// DB에 연결된 자원 종료하는 메서드
	// ========================================================
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
	}

	public void closeConn(PreparedStatement pstmt, Connection con) {
		try {
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//board 테이블의 전체 게시물의 수를 확인하는 메서드
	public int getBoardCount() {
		int count = 0;
		try {
			openConn();
			sql = "select count(*) from board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return count;
	}
	
	
	
	public List<BoardDTO> getBoardList(int page, int rowsize) {
		List<BoardDTO> list = new ArrayList<BoardDTO>();

		// 해당 페이지에서 시작번호
		int startNo = (page * rowsize) - (rowsize - 1);

		// 해당 페이지에서 끝번호
		int endNo = (page * rowsize);

		try {
			openConn();
			sql = "select * from (select row_number() over(order by board_no desc) rnum,"
					+ "b.* from board b) where rnum >= ? and rnum <= ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startNo);
			pstmt.setInt(2, endNo);
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
	}
	
	public int BoardWrite(BoardDTO dto) {
		int result = 0, count = 0;
		
		try {
		openConn();
		sql = "select max(board_no) from board";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			count = rs.getInt(1)+1;
		}
		
		sql = "insert into board values(?, ?, ?, ?, ?, default, sysdate, '')";
		
		pstmt = con.prepareStatement(sql);
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
	}
	
	
	//조회수 증가 메서드
	public void getBoardHit(int num) {
		try {
			openConn();
			sql = "update board set board_hit = board_hit + 1 where board_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
	}
	
	
	public BoardDTO getBoardContent(int num) {
		BoardDTO dto = null;
		try {
			openConn();
			sql = "select * from board where board_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
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
		} finally {
			closeConn(rs, pstmt, con);
		}
		return dto;
	}
	
	public int getBoardUpdate(BoardDTO dto) {
		int result = 0;
		try {
			openConn();
			sql = "select * from board where board_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getBoard_no());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(dto.getBoard_pwd().equals(rs.getString("board_pwd"))) {
					sql = "update board set board_title=?, board_cont=?, board_update=sysdate where board_no=?";
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
	}
	
	
	
	
	public void updateSequence(int no) {

		try {
			openConn();
			sql = "update board set board_no = board_no - 1 where board_no > ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
	} // updateSequence() 메서드 end
	
	
	
	
	public int getBoardDelete(int no, String pwd) {

		int result = 0;

		try {
			openConn();
			sql = "select * from board where board_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (pwd.equals(rs.getString("board_pwd"))) {
					sql = "delete from board where board_no = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, no);
					result = pstmt.executeUpdate();
				} else {
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
	} // deleteBoard() 메서드 end
	
	
	//검색어에 해당하는 게시물의 수를 조회하는 메서드
	public int searchListCount(String field, String keyword) {
		int count = 0;

		try {
			openConn();
			String searchSql = "";
			if (field != null && keyword != null) {
				if (field.equals("title")) {
					searchSql = " where board_title like '%" + keyword + "%'";
				} else if (field.equals("cont")) {
					searchSql = " where board_cont like '%" + keyword + "%'";
				} else if (field.equals("title_cont")) {
					searchSql = " where (board_title like '%" + keyword + "%') or (board_cont like '%" + keyword
							+ "%')";
				} else if (field.equals("writer")) {
					searchSql = " where board_writer like '%" + keyword + "%'";
				}
			}

			sql = "select count(*) from board" + searchSql;
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next())
				count = rs.getInt(1);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return count;
	}
		
	public List<BoardDTO> searchBoardList(String field, String keyword, int page, int rowsize) {
	    List<BoardDTO> list = new ArrayList<BoardDTO>();

	    // 해당 페이지에서 시작번호
	    int startNo = (page * rowsize) - (rowsize - 1);

	    // 해당 페이지에서 끝번호
	    int endNo = (page * rowsize);

	    openConn();

	    try {
	        String searchSql = "";
	        if(field != null && keyword != null){
	            if(field.equals("title")){
	                searchSql = " where board_title like '%" + keyword + "%'";
	            }else if(field.equals("cont")){
	                searchSql = " where board_cont like '%" + keyword + "%'";
	            }else if(field.equals("title_cont")){
	                searchSql = " where (board_title like '%" + keyword + "%') or (board_cont like '%" + keyword + "%')";
	            }else if(field.equals("writer")){
	                searchSql = " where board_writer like '%" + keyword + "%'";
	            }
	        }

	        sql = "select * from"
	            + "(select row_number() over(order by board_no desc) rnum, b.* from board b "+searchSql+")"
	            + "where rnum >= ? and rnum <= ?";
	        pstmt = con.prepareStatement(sql);
	        pstmt.setInt(1, startNo);
	        pstmt.setInt(2, endNo);
	        rs = pstmt.executeQuery();

	        while(rs.next()){
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

	    } catch(Exception e) {
	        e.printStackTrace();

	    } finally {
	        closeConn(rs, pstmt, con);
	    }

	    return list;
	}
	
	
}
