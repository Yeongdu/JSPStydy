package com.khie.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * DAO (Date Access Object)
 *  - 데이터 접근 객체 ==> DB에 접속(연동)하는 객체
 *  - DAO란 데이터베이스에 접속해서 데이터 추가, 수정, 삭제, 조회 등의 작업을 하는 클래스
 *  - 일반적으로 JSP또는 Servlet에서 위의 작업들을 같이 사용할 수 있지만 유지보수, 코드의 모듈화 등을 위해 DAO클래스를 따로 만들어 사용한다.
 */

public class DeptDAO {
	
	//DB와 연동하는 객체
	Connection con = null;
	
	//DB에 SQL문을 전송하는 객체
	PreparedStatement pstmt = null;
	
	//SQL문을 실행한 후 결과값을 가지고 있는 객체
	ResultSet rs = null;
	
	//쿼리문을 저장할 변수
	String sql = null;
	
	public DeptDAO() { // 기본 생성자
		
		String driver = "oracle.jdbc.driver.OracleDriver";
	    String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "web";
		String password = "1234";
		
		try {
			//1단계 : 오라클 드라이버를 메모리로 로딩
			Class.forName(driver);
			
			//2단계 : 오라클 DB와 연결 작업 진행
			con = DriverManager.getConnection(url, user, password); //return값이 커넥션 객체값으로 리턴된다
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} //기본 생성자 end
	
	//selectList() 매서드 만들기
	//DEPT테이블 부서 목록 전체 리스트 조회 메서드
	public List<DeptDTO> selectList() {
		List<DeptDTO> list = new ArrayList<DeptDTO>(); // 다형성

		try {
			// 3단계 : DB에 전송할 SQL문 작성
			sql = "select * from dept order by deptno";

			// 4단계 : SQL문을 DB에 전송 및 객체에 저장
			pstmt = con.prepareStatement(sql);

			// 5단계 : SQL문을 DB에 전송 및 실행
			rs = pstmt.executeQuery();

			while (rs.next()) {
				DeptDTO dto = new DeptDTO(); // DTO 데이터 가져올때랑 넘겨줄때 사용
				dto.setDeptno(rs.getInt("deptno"));
				dto.setDname(rs.getString("dname"));
				dto.setLoc(rs.getString("loc"));

				list.add(dto); // 주소값이ArrayList에 들어가게 된다
			}

			// 6단계 : 연결되어 있던 자원 종료하기
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}//List<DeptDTO> selectList() end
	
	// DEPT테이블에 부서를 추가하는 매서드
	public int insertDept(DeptDTO dto) {
		System.out.println("dao에서 dto주소 >>>");
		int result = 0;

		try {
		//3단계 : 데이터베이스에 전달될 SQL문 작성
		sql = "insert into dept values(?,?,?)";
		
		//4단계 : SQL문을 데이터베이스전송 객체에 저장
		pstmt = con.prepareStatement(sql);
		
		// ?(플레이스홀더)에 데이터 배정
		
			pstmt.setInt(1, dto.getDeptno());
			pstmt.setString(2, dto.getDname());
			pstmt.setString(3, dto.getLoc());
			
			//5단계 
			result = pstmt.executeUpdate();
			
			//6단계
			pstmt.close();
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}//insertDept end

	
	//DB에 수정된 정보를 저장하는 메서드
	public int updateDept(DeptDTO dto) {
		int result = 0;
		
		try {
		//3단계 : 데이터베이스에 전송할 SQL문 작성
			sql = "update dept set dname=?, loc=? where deptno=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getDname());
			pstmt.setString(2, dto.getLoc());
			pstmt.setInt(3, dto.getDeptno());
			
			result = pstmt.executeUpdate();
			
			pstmt.close();
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}//updateDept end
	
	
	//매개변수로 넘어온 부서번호에 해당하는 부서를 삭제하는 메서드
	public int deleteDept(int dno) {
		int result = 0;
		
		try {
			
		//3단계 : 데이터베이스에 전송할 SQL문 작성
		sql = "delete from dept where deptno=?";
		
		//4단계 : sql문을 데이터베이스 전공 객체에 저장
		pstmt = con.prepareStatement(sql);
		
		pstmt.setInt(1, dno);
			
		//5단계 : sql문 데이터베이스에 전송 및 실행
		result = pstmt.executeUpdate();
	
		pstmt.close();con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}//deleteDept end
}
