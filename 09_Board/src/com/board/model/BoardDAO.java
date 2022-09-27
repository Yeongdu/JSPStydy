package com.board.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	
	public List<BoardDTO> getBoardList() {
		List<BoardDTO> bList = new ArrayList<BoardDTO>();
		
		try {
		openConn();
		sql = "select * from board order by board_no desc ";
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
			
			bList.add(dto);
			
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		return bList;
	}

	
	
}
