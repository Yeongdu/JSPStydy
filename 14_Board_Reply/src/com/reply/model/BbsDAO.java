package com.reply.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BbsDAO {
	Connection con = null;
	PreparedStatement pstmt = null; // DB전송 객체
	ResultSet rs = null;
	String sql = null;

	private static BbsDAO instance;

	public BbsDAO() {
	}

	public static BbsDAO getInstance() {
		if (instance == null) {
			instance = new BbsDAO();
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

}
