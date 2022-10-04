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

    private MemberDAO() {}


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
}