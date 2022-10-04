package com.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class MemberDAO {
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = null;

    private static MemberDAO instance;

    public MemberDAO() {}


    public static MemberDAO getInstance() {
        if(instance == null) {
            instance = new MemberDAO();
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
            DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/myoracle");

            // 3단계 : DataSource 객체를 이용하여 커넥션을 하나 가져온다.
            con = ds.getConnection();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    // ========================================================
    // DB에 연결된 자원 종료하는 메서드
    // ========================================================
    public void closeConn(ResultSet rs, PreparedStatement pstmt, Connection con) {
        try {
            if(rs != null) rs.close();
            if(pstmt != null) pstmt.close();
            if(con != null) con.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void closeConn(PreparedStatement pstmt, Connection con) {
        try {
            if(pstmt != null) pstmt.close();
            if(con != null) con.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    //MEMBER10 테이블에서 전체 회원 리스트를 조회하는 메서드
    public List<MemberDTO> getMemberList() {
    	List<MemberDTO> list = new ArrayList<MemberDTO>();
    	
    	try {
    	openConn(); //커넥션풀방식으로 DB와 연동작업
    	sql ="select * from member10 order by num desc";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			MemberDTO dto = new MemberDTO();
			dto.setNum(rs.getInt("num"));
			dto.setMemid(rs.getString("memid"));
			dto.setMemname(rs.getString("memname"));
			dto.setPwd(rs.getString("pwd"));
			dto.setAge(rs.getInt("age"));
			dto.setMileage(rs.getInt("mileage"));
			dto.setJob(rs.getString("job"));
			dto.setAddr(rs.getString("addr"));
			dto.setRegdate(rs.getString("regdate"));

			list.add(dto);
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
    	return list;
	}//getMemberList end
    
    public int insertMember(MemberDTO dto) {
		int result = 0, count = 0;
		
		try {
		openConn();
		sql = "select max(num) from member10";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			count = rs.getInt(1)+1;
		}
		
		sql = "insert into member10 values(?,?,?,?,?,?,?,?,sysdate)";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, count);
		pstmt.setString(2, dto.getMemid());
		pstmt.setString(3, dto.getMemname());
		pstmt.setString(4, dto.getPwd());
		pstmt.setInt(5, dto.getAge());
		pstmt.setInt(6, dto.getMileage());
		pstmt.setString(7, dto.getJob());
		pstmt.setString(8, dto.getAddr());
		
		result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}
    
	public MemberDTO getMemberContent(int no) {
		MemberDTO dto = null;
		try {
			openConn();
			sql = "select * from member10 where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dto = new MemberDTO();
				dto.setNum(rs.getInt("num"));
				dto.setMemid(rs.getString("memid"));
				dto.setMemname(rs.getString("memname"));
				dto.setPwd(rs.getString("pwd"));
				dto.setAge(rs.getInt("age"));
				dto.setMileage(rs.getInt("mileage"));
				dto.setJob(rs.getString("job"));
				dto.setAddr(rs.getString("addr"));
				dto.setRegdate(rs.getString("regdate"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return dto;
	}
	
	
	public int updateMember(MemberDTO dto) {

        int result = 0;
        openConn();

        try {

            sql="update member10 set age=?, mileage=?, job=?, addr=? where num=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, dto.getAge());
            pstmt.setInt(2, dto.getMileage());
            pstmt.setString(3, dto.getJob());
            pstmt.setString(4, dto.getAddr());
            pstmt.setInt(5, dto.getNum());
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