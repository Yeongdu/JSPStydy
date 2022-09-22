package com.member.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.member.model.MemberDTO;

import java.sql.DriverManager;

public class MemberDAO {
	// DB와 연동하는 객체
	Connection con = null;

	// DB에 SQL문을 전송하는 객체
	PreparedStatement pstmt = null;

	// SQL문을 실행한 후 결과값을 가지고 있는 객체
	ResultSet rs = null;

	// 쿼리문을 저장할 변수
	String sql = null;

	public MemberDAO() { // 기본 생성자.
		// 데이터베이스 연동과 관련된 연산자
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "web";
		String password = "1234";

		try {
			// 1단계 : 오라클 드라이버를 메모리로 로딩
			Class.forName(driver);

			// 2단계 : 오라클 DB와 연결 작업 진행
			con = DriverManager.getConnection(url, user, password); // return값이 커넥션 객체값으로 리턴된다

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}// 기본생성자end

	public List<MemberDTO> memberList() {
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		try {
			// DB에 전송할 SQL문 작성
			sql = "select * from member10 order by num desc";

			// SQL문을 DB에 전송 및 객체에 저장
			pstmt = con.prepareStatement(sql);

			// SQL문을 DB에 전송 및 실행
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MemberDTO dto = new MemberDTO(); // DTO 데이터 가져올때랑 넘겨줄때 사용
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
			rs.close();pstmt.close();con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}//getMemberList() 메서드 end
	
	//MEMBER10 테이블에 회원을 등록하는 메서드
	public int insertMember(MemberDTO dto) {
		
		int result = 0, count = 0;
		try {
			
			sql = "select max(num) from member10";
		
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
			sql ="insert into member10 values(?, ?, ?, ?, ?, ?, ?, ?, sysdate)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, count+1);
			pstmt.setString(2, dto.getMemid());
			pstmt.setString(3, dto.getMemname());
			pstmt.setString(4, dto.getPwd());
			pstmt.setInt(5, dto.getAge());
			pstmt.setInt(6, dto.getMileage());
			pstmt.setString(7, dto.getJob());
			pstmt.setString(8, dto.getAddr());
			
			result = pstmt.executeUpdate();
			
			rs.close();pstmt.close();con.close();

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}//insertMember end
	
	
	
	//회원번호에 해당하는 회원의 정보를 조회하는 메서드
	public MemberDTO getContentMember(int no) {
		MemberDTO dto = null;
		
		try {
			sql = "select * from member10 where num = ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
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
			rs.close();pstmt.close();con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	}//getContentMember end
	
	
	
	//회원번호에 해당하는 회원의 정보를 수정하는 메서드
	public int updateMember(MemberDTO dto) {
		int result = 0;

		try {
			sql = "select * from member10 where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getNum());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (dto.getPwd().equals(rs.getString("pwd"))) {
					sql = "update member10 set age=?, mileage=?, job=?, addr=? where num = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, dto.getAge());
					pstmt.setInt(2, dto.getMileage());
					pstmt.setString(3, dto.getJob());
					pstmt.setString(4, dto.getAddr());
					pstmt.setInt(5, dto.getNum());
					result = pstmt.executeUpdate();
				} else {// 비밀번호 틀린 경우
					result = -1;
				}
			}

			rs.close();
			pstmt.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}// updateMember end
}
