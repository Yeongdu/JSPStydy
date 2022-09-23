package com.khie.model;
//DB상의 DEPT테이블의 컬럼명과 동일하게 멤버변수 선언

public class DeptDTO {
	
	private int deptno;
	private String dname;
	private String loc;
	
	
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	

}
