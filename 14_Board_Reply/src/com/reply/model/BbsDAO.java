package com.reply.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class BbsDAO {

	// DB와 연동하는 객체.
	Connection con = null;
	
	// DB에 SQL문을 전송하는 객체
	PreparedStatement pstmt = null;
	
	// SQL문을 실행한 후에 결과 값을 가지고 있는 객체.
	ResultSet rs = null;
	
	// 쿼리문을 저장할 변수
	String sql = null;
	
	// BbsDAO 객체를 싱글턴 방식으로 만들어 보자.
	// 1단계 : 싱글턴 방식으로 객체를 만들기 위해서는 우선적으로
	//        기본생성자의 접근제어자를 public이 아닌 private
	//        으로 바꾸어 주어야 한다.
	//        즉, 외부에서 직접적으로 기본생성자를 호출하지
	//        못하게 하는 방법이다.
	
	// 2단계 : BbsDAO 객체를 정적(static) 멤버로 선언을 
	//        해 주어야 한다.
	private static BbsDAO instance;
	
	private BbsDAO() {  }  // 기본 생성자
	
	
	// 3단계 : 기본 생성자 대신에 싱글턴 객체를 return 해 주는
	//        getInstance() 라는 메서드를 만들어서 해당
	//        getInstance() 라는 메서드를 외부에서 접근할 수
	//        있도록 해 주면 됨.
	public static BbsDAO getInstance() {
		
		if(instance == null) {
			instance = new BbsDAO();
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
	
	
	// jsp_bbs 테이블의 전체 레코드를 조회하는 메서드.
	public List<BbsDTO> getBbsList() {
		
		List<BbsDTO> list = 
				new ArrayList<BbsDTO>();
		
		try {
			openConn();
			
			sql = "select * from jsp_bbs "
					+ " order by board_group desc, "
					+ " board_step";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				BbsDTO dto = new BbsDTO();
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_writer(rs.getString("board_writer"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_pwd(rs.getString("board_pwd"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_date(rs.getString("board_date"));
				dto.setBoard_update(rs.getString("board_update"));
				dto.setBoard_group(rs.getInt("board_group"));
				dto.setBoard_step(rs.getInt("board_step"));
				dto.setBoard_indent(rs.getInt("board_indent"));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return list;
	}  // getBbsList() 메서드 end
	
	
	// jsp_bbs 테이블에 게시글을 추가하는 메서드.
	public int insertBbs(BbsDTO dto) {
		
		int result = 0, count = 0;
		
		try {
			openConn();
		
			sql = "select max(board_no) from jsp_bbs";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1) + 1;
			}
			
			sql = "insert into jsp_bbs "
					+ " values(?, ?, ?, ?, ?, default, sysdate, '', ?, 0, 0)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, count);
			pstmt.setString(2, dto.getBoard_writer());
			pstmt.setString(3, dto.getBoard_title());
			pstmt.setString(4, dto.getBoard_cont());
			pstmt.setString(5, dto.getBoard_pwd());
			pstmt.setInt(6, count);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}  // insertBbs() 메서드 end
	
	
	// jsp_bbs 테이블의 게시물 번호에 해당하는 게시글의
	// 조회수를 증가시키는 메서드.
	public void bbsHit(int no) {
		
		try {
			openConn();
			
			sql = "update jsp_bbs set board_hit = board_hit + 1 "
					+ " where board_no = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
	}  // bbsHit() 메서드 end
	
	
	// jsp_bbs 테이블에서 게시글 번호에 해당하는 
	// 게시글의 상세내역을 조회하는 메서드.
	public BbsDTO getBbsContent(int no) {
		
		BbsDTO dto = null;
		
		try {
			openConn();
			
			sql = "select * from jsp_bbs where board_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new BbsDTO();
				
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_writer(rs.getString("board_writer"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_pwd(rs.getString("board_pwd"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_date(rs.getString("board_date"));
				dto.setBoard_update(rs.getString("board_update"));
				dto.setBoard_group(rs.getInt("board_group"));
				dto.setBoard_step(rs.getInt("board_step"));
				dto.setBoard_indent(rs.getInt("board_indent"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return dto;
	}  // getBbsContent() 메서드 end
	
	
	// jsp_bbs 테이블에 글번호에 해당하는 게시글을
	// 수정하는 메서드.
	public int updateBbs(BbsDTO dto) {
		
		int result = 0;
		
		try {
			openConn();
			
			sql = "select * from jsp_bbs where board_no = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getBoard_no());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(dto.getBoard_pwd().equals(
						rs.getString("board_pwd"))) {
					
					sql = "update jsp_bbs set "
							+ " board_title = ?, "
							+ " board_cont = ?, "
							+ " board_update = sysdate "
							+ " where board_no = ?";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, dto.getBoard_title());
					pstmt.setString(2, dto.getBoard_cont());
					pstmt.setInt(3, dto.getBoard_no());
					
					result = pstmt.executeUpdate();
				}else {
					// 비밀번호가 틀린 경우
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
	}  // updateBbs() 메서드 end
	
	
	// jsp_bbs 테이블 게시판 답변 글의 step을 
	// 하나 증가시켜 주는 메서드.
	public void replyUpdate(int group, int step) {
		
		try {
			openConn();
			
			sql = "update jsp_bbs set "
					+ " board_step = board_step + 1 "
					+ " where board_group = ? and "
					+ " board_step > ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, group);
			pstmt.setInt(2, step);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
	}  // replyUpdate() 메서드 end
	
	
	// jsp_bbs 테이블의 게시글 원글에 답변글을 추가하는 메서드.
	public int replyBbs(BbsDTO dto) {
		
		int result = 0, count = 0;
		
		try {
			openConn();
			
			sql = "select max(board_no) from jsp_bbs";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1) + 1;
			}
			
			sql = "insert into jsp_bbs "
					+ " values(?, ?, ?, ?, ?, "
					+ " default, sysdate, '', ?, ?, ?)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, count);
			pstmt.setString(2, dto.getBoard_writer());
			pstmt.setString(3, dto.getBoard_title());
			pstmt.setString(4, dto.getBoard_cont());
			pstmt.setString(5, dto.getBoard_pwd());
			pstmt.setInt(6, dto.getBoard_group());
			pstmt.setInt(7, dto.getBoard_step() + 1);
			pstmt.setInt(8, dto.getBoard_indent() + 1);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}  // replyBbs() 메서드 end
	
	
	
	
	
	// jsp_bbs 테이블에서 게시글 삭제하는 메서드
    public int deleteBbs(int no, String pwd) {

        int result = 0;
        openConn();

        try {
            sql = "select * from jsp_bbs where board_no = ? ";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, no);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                if(pwd.equals(rs.getString("board_pwd"))) {
                    if(rs.getInt("board_step")==0) {
                        sql = "update jsp_bbs set board_title = ? where board_no = ?";
                        pstmt = con.prepareStatement(sql);
                        pstmt.setString(1, "(원글) 삭제된 게시글입니다.");
                        pstmt.setInt(2, no);

                        result = pstmt.executeUpdate();
                    }else if(rs.getInt("board_step") > 0) { // 댓글인 경우
                        sql="delete from jsp_bbs where board_no = ?";
                        pstmt = con.prepareStatement(sql);
                        pstmt.setInt(1, no);
                        pstmt.executeUpdate();

                        sql="update jsp_bbs set board_no = board_no-1 where board_no>?";
                        pstmt = con.prepareStatement(sql);
                        pstmt.setInt(1, no);
                        pstmt.executeUpdate();

                        result = -2; // 삭제된 경우
                    }
                }else { // 비번 틀린 경우
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
    } // deleteBbs() 메서드 end
	
	
}
