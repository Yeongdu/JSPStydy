package com.emp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpDAO {

	// DB와 연동하는 객체
	Connection con = null;

	// DB에 SQL문을 전송하는 객체
	PreparedStatement pstmt = null;

	// SQL문을 실행한 후 결과값을 가지고 있는 객체
	ResultSet rs = null;

	// 쿼리문을 저장할 변수
	String sql = null;

	// EmpDAO객체를 싱글턴 방식으로 만들어보자
	// 1단계 : 싱글턴 방식으로 객체를 만들기 위해서 우선적으로 기본생성자의 접근제어자를 public이 아닌 private으로 바꿔주어야한다
	// 즉, 외부에서 기본생성자를 호출하지 못하게 하는 방법

	// 2단계 : EmpDAO 객체를 정적(static) 멤버로 선언

	private static EmpDAO instance; // 2

	private EmpDAO() {
	} // 기본생성자 //1

	// 3단계 : 기본 생성자 대신 싱글턴 객체를 return해주는 getInstance() 라는 메서드를 만들어
	// 해당 getInstance() 메서드를 외부에서 접근할 수 있도록 해준다.

	public static EmpDAO getInstance() {
		if (instance == null) {
			instance = new EmpDAO(); // 객체 생성하면 주소값이 나온다. 그 주소값을 instance멤버변수에 지정
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

	public List<EmpDTO> getEmpList() {
		List<EmpDTO> list = new ArrayList<EmpDTO>();

		try {
			openConn();
			sql = "select * from emp order by empno desc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				EmpDTO dto = new EmpDTO();
				dto.setEmpno(rs.getInt("empno"));
				dto.setEname(rs.getString("ename"));
				dto.setJob(rs.getString("job"));
				dto.setMgr(rs.getInt("mgr"));
				dto.setHiredate(rs.getString("hiredate"));
				dto.setSal(rs.getInt("sal"));
				dto.setComm(rs.getInt("comm"));
				dto.setDeptno(rs.getInt("deptno"));

				list.add(dto);
			}
			closeConn(rs, pstmt, con);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}// getEmpList end

	public List<String> getJobList() {
		List<String> jobList = new ArrayList<String>();

		try {
			openConn();
			sql = "select distinct(job) from emp order by job";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String job = rs.getString("job");
				jobList.add(job);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return jobList;
	}// getJobList end

	public List<EmpDTO> getMgrList() {
		List<EmpDTO> mgrList = new ArrayList<EmpDTO>();
		try {
			openConn();
			sql = "select * from emp where empno in (select distinct(mgr) from emp)";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				EmpDTO dto = new EmpDTO();
				dto.setEmpno(rs.getInt("empno"));
				dto.setEname(rs.getString("ename"));

				mgrList.add(dto);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return mgrList;
	}// getMgrList end

	public List<DeptDTO> getDeptList() {
		List<DeptDTO> deptList = new ArrayList<DeptDTO>();
		try {
			openConn();
			sql = "select * from dept order by deptno";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				DeptDTO dto = new DeptDTO();
				dto.setDeptno(rs.getInt("deptno"));
				dto.setDname(rs.getString("dname"));
				dto.setLoc(rs.getString("loc"));

				deptList.add(dto);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return deptList;
	}// getDeptList end

	public int insertEmp(EmpDTO dto) {
		int result = 0;

		try {
			openConn();
			sql = "insert into emp values(?,?,?,?,sysdate,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getEmpno());
			pstmt.setString(2, dto.getEname());
			pstmt.setString(3, dto.getJob());
			pstmt.setInt(4, dto.getMgr());
			pstmt.setInt(5, dto.getSal());
			pstmt.setInt(6, dto.getComm());
			pstmt.setInt(7, dto.getDeptno());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}// insertEmp end

	
	public EmpDTO getContentEmp(int empno) {
		EmpDTO dto = null;

		try {
			openConn();

			sql = "select * from emp where empno = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, empno);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto = new EmpDTO();

				dto.setEmpno(rs.getInt("empno"));
				dto.setEname(rs.getString("ename"));
				dto.setJob(rs.getString("job"));
				dto.setMgr(rs.getInt("mgr"));
				dto.setHiredate(rs.getString("hiredate"));
				dto.setSal(rs.getInt("sal"));
				dto.setComm(rs.getInt("comm"));
				dto.setDeptno(rs.getInt("deptno"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return dto;
	}// getContentEmp end
	

	public String getDeptName(int empno) {

		String deptName = "";

		try {
			openConn();
			sql = "select distinct(dname) from dept d join emp e on d.deptno = e.deptno where e.deptno = (select deptno from emp where empno = ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, empno);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				deptName = rs.getString("dname");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return deptName;
	} // getDeptName() 메서드 종료

	
	
	// Manager 이름 값을 얻는 메서드
	public String getMgrName(int empno) {

		String mgrName = "";

		try {
			openConn();
			sql = "select ename from emp where empno = (select mgr from emp where empno = ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, empno);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				mgrName = rs.getString("ename");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return mgrName;
	} // getMgrName() 메서드 종료
	
	public int modifyEmp(EmpDTO dto) {
		
		int result=0;
		try {
			openConn();
			sql = "update emp set job=?, mgr=?, sal=?, comm=?, deptno=? where empno=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getJob());
			pstmt.setInt(2, dto.getMgr());
			pstmt.setInt(3, dto.getSal());
			pstmt.setInt(4, dto.getComm());
			pstmt.setInt(5, dto.getDeptno());
			pstmt.setInt(6, dto.getEmpno());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}//modifyEmp end
	
	
	public int deleteEmp(int num) {
		int result = 0;
		
		try {
		openConn();
		sql="delete from emp where empno=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, num);
		result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		
		return result;
		
	}//deleteEmp end
	
	
	
}
