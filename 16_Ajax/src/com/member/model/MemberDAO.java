package com.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class MemberDAO {
	

	// DB와 연동하는 객체.
	Connection con = null;
	
	// DB에 SQL문을 전송하는 객체
	PreparedStatement pstmt = null;
	
	// SQL문을 실행한 후에 결과 값을 가지고 있는 객체.
	ResultSet rs = null;
	
	// 쿼리문을 저장할 변수
	String sql = null;
	
	// MemberDAO 객체를 싱글턴 방식으로 만들어 보자.
	// 1단계 : 싱글턴 방식으로 객체를 만들기 위해서는 우선적으로
	//        기본생성자의 접근제어자를 public이 아닌 private
	//        으로 바꾸어 주어야 한다.
	//        즉, 외부에서 직접적으로 기본생성자를 호출하지
	//        못하게 하는 방법이다.
	
	// 2단계 : MemberDAO 객체를 정적(static) 멤버로 선언을 
	//        해 주어야 한다.
	private static MemberDAO instance;
	
	private MemberDAO() {  }  // 기본 생성자
	
	
	// 3단계 : 기본 생성자 대신에 싱글턴 객체를 return 해 주는
	//        getInstance() 라는 메서드를 만들어서 해당
	//        getInstance() 라는 메서드를 외부에서 접근할 수
	//        있도록 해 주면 됨.
	public static MemberDAO getInstance() {
		
		if(instance == null) {
			instance = new MemberDAO();
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

	} // closeConn() 메서드 end
	
	
	///////////////////////절 취 선///////////////////////////////////////////////
	
	// 아이디 중복체크
	public int checkMemberId(String id) {
		int result = 0;
		try {
			openConn();
			sql = "select * from customer where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = 1;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}
	
	
	// customer 테이블의 전체 고객을 조회하는 메서드
	public String getCustomerList() {
		String result = "";
		try {
			openConn();
			sql = "select * from customer order by no desc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			result += "<customers>";
			while (rs.next()) {
				result += "<customer>";
				result += "<no>" + rs.getInt("no") + "</no>";
				result += "<id>" + rs.getString("id") + "</id>";
				result += "<name>" + rs.getString("name") + "</name>";
				result += "<age>" + rs.getInt("age") + "</age>";
				result += "<phone>" + rs.getString("phone") + "</phone>";
				result += "<addr>" + rs.getString("addr") + "</addr>";
				result += "</customer>";
			}
			result += "</customers>";

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}
	
	
	// 입력폼 창에서 넘어온 아이디가 중복인지 여부를 확인하는 메서드
    public String idCheck(String id) {

        String result = "사용 가능합니다.";

        try {
            openConn();

            sql = "select * from customer where id = ?";

            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, id);

            rs = pstmt.executeQuery();

            if(rs.next()) {        // true : 아이디 중복인 경우
                result = "중복 아이디입니다.";
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            closeConn(rs, pstmt, con);
        }

        return result;
    }    // idCheck() 메서드 end
	
	
	public int insertCustomer(MemberDTO dto) {
		int result = 0, count = 0;
		try {
			openConn();
			sql = "select max(no) from customer";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1)+1;
			}
			sql ="insert into customer values(?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, count);
			pstmt.setString(2, dto.getId());
			pstmt.setString(3, dto.getName());
			pstmt.setInt(4, dto.getAge());
			pstmt.setString(5, dto.getPhone());
			pstmt.setString(6, dto.getAddr());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}	//insertCustomer() end
	
	
	// 넘겨 받은 번호에 해당하는 고객을 DB에서 삭제하는 메서드.
		public int deleteCustomer(int no) {
			int result = 0;
			try {
				openConn();
				
				sql = "delete from customer where no = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, no);
				result = pstmt.executeUpdate();
				sql = "update customer set  no = no - 1 where no > ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, no);
				pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeConn(rs, pstmt, con);
			}
			return result;
		}  // deleteCustomer() 메서드 end
	

}
