package com.products.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//DAO객체를 싱글턴 방식으로 만들어서 사용해보자
public class ProductDAO {
	// DB와 연동하는 객체
	Connection con = null;

	// DB에 SQL문을 전송하는 객체
	PreparedStatement pstmt = null;

	// SQL문을 실행한 후 결과값을 가지고 있는 객체
	ResultSet rs = null;
	
	// 쿼리문을 저장할 변수
	String sql = null;

	
	//ProductDAO객체를 싱글턴 방식으로 만들어보자
	//1단계 : 싱글턴 방식으로 객체를 만들기 위해서 우선적으로 기본생성자의 접근제어자를 public이 아닌 private으로 바꿔주어야한다
	//즉, 외부에서 기본생성자를 호출하지 못하게 하는 방법 
	
	//2단계 : ProductDAO 객체를 정적(static) 멤버로 선언
	
	private static ProductDAO instance;     //2
	
	private ProductDAO() {	} //기본생성자 //1
	
	
	//3단계 : 기본 생성자 대신 싱글턴 객체를 return해주는 getInstance() 라는 메서드를 만들어
	//		  해당 getInstance() 메서드를 외부에서 접근할 수 있도록 해준다.
	
	public static ProductDAO getInstance() {
		if(instance == null) {
			instance = new ProductDAO(); //객체 생성하면 주소값이 나온다. 그 주소값을 instance멤버변수에 지정		
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

	}//openConn end
	
	
	public void closeConn(ResultSet rs, PreparedStatement pstmt, Connection con) {
		try {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(con!=null) con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}//closeConn end
	
	public void closeConn(PreparedStatement pstmt, Connection con) {
		try {
			if(pstmt!=null) pstmt.close();
			if(con!=null) con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//closeConn end
	
	
	
	//products 테이블에서 전체 제품 리스트를 조회하는 메서드
	public List<ProductDTO> getProductList() {
		List<ProductDTO> list = new ArrayList<ProductDTO>();
		
		openConn();
		
		try {
		sql = "select * from products order by pnum desc";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		while (rs.next()) {
			ProductDTO dto = new ProductDTO();
			
			dto.setPnum(rs.getInt("pnum"));
			dto.setCategory_fk(rs.getString("category_fk"));
			dto.setProducts_name(rs.getString("products_name"));
			dto.setEp_code_fk(rs.getString("ep_code_fk"));
			dto.setInput_price(rs.getInt("input_price"));
			dto.setOutput_price(rs.getInt("output_price"));
			dto.setTrans_cost(rs.getInt("trans_cost"));
			dto.setMileage(rs.getInt("mileage"));
			dto.setCompany(rs.getString("company"));
			
			list.add(dto);
		}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return list;
	}//getProductList end
	
	//category 테이블의 전체 리스트를 조회하는 메서드
	public List<CategoryDTO> getCategoryList() {
		List<CategoryDTO> cateList = new ArrayList<CategoryDTO>();
		
		openConn();
		try {
		
			sql="select * from category order by category_code";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				CategoryDTO dto = new CategoryDTO();
				
				dto.setCnum(rs.getInt("cnum"));
				dto.setCategory_code(rs.getString("category_code"));
				dto.setCategory_name(rs.getString("category_name"));
				
				cateList.add(dto);
			}
		

			} catch (Exception e) {
				e.printStackTrace();
			}
		return cateList;
	}
	
	
	
	//products테이블에 제품을 등록하는 메서드
	public int insertProduct(ProductDTO dto) {
        int result = 0;
        int count = 0;

        // 오라클 드라이버 로딩 및 DB 연결작업 진행
        openConn();

        try {
            // pnum 값 구하기
            pstmt = con.prepareStatement("select max(pnum) from products");
            rs = pstmt.executeQuery();
            if(rs.next()) {
                count = rs.getInt(1) + 1;
            }

            // 데이터베이스에 전송할 SQL문 작성
            sql = "insert into products values(?, ?, ?, ?, ?, ?, ?, ?, ?)";

            // SQL문을 데이터베이스 전송 개체에 저장
            pstmt = con.prepareStatement(sql);

            // ?(플레이스 홀더)에 데이터 배정
            pstmt.setInt(1, count);
            pstmt.setString(2, dto.getCategory_fk());
            pstmt.setString(3, dto.getProducts_name());
            pstmt.setString(4, dto.getEp_code_fk());
            pstmt.setInt(5, dto.getInput_price());
            pstmt.setInt(6, dto.getOutput_price());
            pstmt.setInt(7, dto.getTrans_cost());
            pstmt.setInt(8, dto.getMileage());
            pstmt.setString(9, dto.getCompany());

            // SQL문을 데이터베이스에 전송 및 실행
            result = pstmt.executeUpdate();

        } catch(Exception e) {
            e.printStackTrace();

        } finally {
            closeConn(rs, pstmt, con);
        }

        return result;
    }
	
	public ProductDTO getContentProduct(int pno) {
		ProductDTO dto = null;
		openConn();
		
		try {
		sql = "select * from products where pnum = ?";

		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, pno);
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			dto = new ProductDTO();
			dto.setPnum(rs.getInt("pnum"));
			dto.setCategory_fk(rs.getString("category_fk"));
			dto.setProducts_name(rs.getString("products_name"));
			dto.setEp_code_fk(rs.getString("ep_code_fk"));
			dto.setInput_price(rs.getInt("input_price"));
			dto.setOutput_price(rs.getInt("output_price"));
			dto.setTrans_cost(rs.getInt("trans_cost"));
			dto.setMileage(rs.getInt("mileage"));
			dto.setCompany(rs.getString("company"));
		}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return dto;
	}//getContentProduct end
	
	
	//product 테이블의 제품번호에 해당하는 제품의 정보를 수정하는 메서드
	public int updateProduct(ProductDTO dto) {
		int result = 0;

		try {
			openConn();
			sql = "update products set input_price=?, output_price=?, trans_cost=?, mileage=? where pnum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getInput_price());
			pstmt.setInt(2, dto.getOutput_price());
			pstmt.setInt(3, dto.getMileage());
			pstmt.setInt(4, dto.getTrans_cost());
			pstmt.setInt(5, dto.getPnum());
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(pstmt, con);
		}
		return result;
	}//updateProduct end
	
	
	public int deleteProduct(int product_no) {
		int result = 0;
		
		try {
			openConn();
			sql = "delete from products where pnum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, product_no);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}		
		return result;
	}//deleteProduct end
	
	//제품 번호 다시 작업해주는 메서드
	public void updatePnum(int product_no) {
		try {
			sql = "update products set pnum = pnum-1 where pnum > ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, product_no);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(pstmt, con);
		}

	}
	
	
	
	
	}
