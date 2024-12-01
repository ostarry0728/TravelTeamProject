package com.kh.travelMVCProject.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kh.travelMVCProject.model.CustomerVO;


public class CustomerDAO {
	public final String CUSTOMER_SELECT = "SELECT * FROM CUSTOMER";
	public final String CUSTOMER_DELETE = "DELETE FROM CUSTOMER WHERE NO = ?";
	public final String CUSTOMER_UPDATE = "UPDATE CUSTOMER SET NAME = ?, BIRTH = ?, NATIONAL = ?, GENDER = ?, EMAIL = ?, PHONE = ? WHERE NO = ?";
	public final String CUSTOMER_INSERT = "INSERT INTO CUSTOMER VALUES (CUSTOMER_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
	public final String CUSTOMER_SELECT_SORT = "SELECT * FROM CUSTOMER ORDER BY NO";

	// Lesson 테이블에서 select 출력레코드를 리턴한다. (Read)
	public ArrayList<CustomerVO> customerSelect(CustomerVO cvo) {
		Connection con = null; // 오라클접속관문
		PreparedStatement pstmt = null; // 오라클에서 작업할 쿼리문 사용할게 하는 명령문
		ResultSet rs = null; // 오라클에서 결과물을 받는객체
		ArrayList<CustomerVO> customerList = new ArrayList<CustomerVO>(); // 결과값을 다른객체전달하기 위해서 사용하는객체

		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(CUSTOMER_SELECT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int no = rs.getInt("NO");
				String id = rs.getString("ID");
				String name = rs.getString("NAME");
				int birth = rs.getInt("BIRTH");
				String national = rs.getString("NATIONAL");
				String gender = rs.getString("GENDER");
				String email = rs.getString("EMAIL");
				String phone = rs.getString("PHONE");
				CustomerVO customerVO = new CustomerVO(no, id, name, birth, national, gender, email, phone);
				customerList.add(customerVO);
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt, rs);
		}

		return customerList;
	}

	// Lesson 테이블에서 delete 레코드를 삭제한다. (delete)
	public boolean customerDelete(CustomerVO cvo) {
		Connection con = null; // 오라클에 DB접속
		PreparedStatement pstmt = null; // 오라클에서 작업할 쿼리문을 사용할 수 있게하는 명령문
		boolean successFlag = false;
		try {
			con = DBUtility.dbCon();
			con.setAutoCommit(false);

			pstmt = con.prepareStatement(CUSTOMER_DELETE);
			pstmt.setInt(1, cvo.getNo());
			int count = pstmt.executeUpdate();
			if (count != 0) {
				con.commit();
				successFlag = true;
			} else {
				con.rollback();
				successFlag = false;
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt);
		}
		return successFlag;
	}

	// Lesson 테이블에서 update 레코드를 수정한다. (update)
	public boolean customerUpdate(CustomerVO cvo) {
		Connection con = null; // 오라클접속관문
		PreparedStatement pstmt = null; // 오라클에서 작업할 쿼리문 사용할게 하는 명령문
		boolean successFlag = false;

		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(CUSTOMER_UPDATE);
			pstmt.setString(1, cvo.getName());
			pstmt.setInt(2, cvo.getBirth());
			pstmt.setString(3, cvo.getNational());
			pstmt.setString(4, cvo.getGender());
			pstmt.setString(5, cvo.getEmail());
			pstmt.setString(6, cvo.getPhone());
			pstmt.setInt(7, cvo.getNo());

			int count = pstmt.executeUpdate();
			successFlag = (count != 0) ? (true) : (false);
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt);
		}
		return successFlag;
	}

	// Lesson 테이블에서 insert 레코드를 삽입한다. (insert)
	public boolean customerInsert(CustomerVO cvo) {
		Connection con = null; // 오라클접속관문
		PreparedStatement pstmt = null; // 오라클에서 작업할 쿼리문 사용할게 하는 명령문
		boolean successFlag = false;

		try {
			con = DBUtility.dbCon();
			System.out.println("cvo "+ cvo.toString());
			pstmt = con.prepareStatement(CUSTOMER_INSERT);
			pstmt.setString(1, cvo.getId());
			pstmt.setString(2, cvo.getName());
			pstmt.setInt(3, cvo.getBirth());
			pstmt.setString(4, cvo.getNational());
			pstmt.setString(5, cvo.getGender());
			pstmt.setString(6, cvo.getEmail());
			pstmt.setString(7, cvo.getPhone());

			int count = pstmt.executeUpdate();
			successFlag = (count != 0) ? (true) : (false);
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt);
		}
		return successFlag;
	}

	// Lesson 테이블에서 select 출력레코드를 리턴한다. (Read)
	public ArrayList<CustomerVO> customerSelectSort(CustomerVO cvo) {
		Connection con = null; // 오라클접속관문
		PreparedStatement pstmt = null; // 오라클에서 작업할 쿼리문 사용할게 하는 명령문
		ResultSet rs = null; // 오라클에서 결과물을 받는객체
		ArrayList<CustomerVO> customerList = new ArrayList<CustomerVO>(); // 결과값을 다른객체전달하기 위해서 사용하는객체

		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(CUSTOMER_SELECT_SORT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int no = rs.getInt("NO");
				String id = rs.getString("ID");
				String name = rs.getString("NAME");
				int birth = rs.getInt("BIRTH");
				String national = rs.getString("NATIONAL");
				String gender = rs.getString("GENDER");
				String email = rs.getString("EMAIL");
				String phone = rs.getString("PHONE");
				CustomerVO customerVO = new CustomerVO(no, id, name, birth, national, gender, email, phone);
				customerList.add(customerVO);
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt, rs);
		}

		return customerList;
	}

	
	
}