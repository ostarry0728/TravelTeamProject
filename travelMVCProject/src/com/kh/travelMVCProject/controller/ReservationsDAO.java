package com.kh.travelMVCProject.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kh.travelMVCProject.model.ReservationsAllVO;
import com.kh.travelMVCProject.model.ReservationsVO;

public class ReservationsDAO {

	public final String RESERVATIONS_SELECT = "SELECT * FROM RESERVATION";
	public final String RESERVATIONS_CUSTOMERS_PACKAGES_SELECT_JOIN = "SELECT RES.NO, RES.ID, RES.CUST_ID, CUST.NAME AS CUSTOMER_NAME, CUST.EMAIL AS CUSTOMER_EMAIL, "
			+ "RES.PACK_ID, PKG.NAME AS PACKAGE_NAME, PKG.NATIONAL AS PACKAGE_NATIONAL, "
			+ "RES.RCAPACITY, RES.METHOD, RES.RDATE " + "FROM RESERVATION RES "
			+ "INNER JOIN CUSTOMERS CUST ON RES.CUST_ID = CUST.ID " + "INNER JOIN PACKAGES PKG ON RES.PACK_ID = PKG.ID";
	public final String RESERVATIONS_SELECT_SORT = "SELECT * FROM RESERVATION ORDER BY RDATE";
	public final String RESERVATIONS_DELETE = "DELETE FROM RESERVATION WHERE NO = ?";
	public final String RESERVATIONS_UPDATE = "UPDATE RESERVATION SET CUST_ID = ?, PACK_ID = ?, RCAPACITY = ?, METHOD = ?, RDATE = ? WHERE NO = ?";
	public final String RESERVATIONS_INSERT = "INSERT INTO RESERVATION (NO, ID, CUST_ID, PACK_ID, RCAPACITY, METHOD, RDATE) VALUES (RESERVATION_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?)";

	// 예약 정보를 조회
	public ArrayList<ReservationsVO> reservationSelect() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ReservationsVO> reservationList = new ArrayList<>();

		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(RESERVATIONS_SELECT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int no = rs.getInt("NO");
				String id = rs.getString("ID");
				String custID = rs.getString("CUST_ID");
				String packID = rs.getString("PACK_ID");
				int rCapacity = rs.getInt("RCAPACITY");
				String method = rs.getString("METHOD");
				Date rDate = rs.getDate("RDATE");

				ReservationsVO reserv = new ReservationsVO(no, id, custID, packID, rCapacity, method, rDate);
				reservationList.add(reserv);
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt, rs);
		}

		return reservationList;
	}

	// 예약 정보를 정렬하여 조회
	public ArrayList<ReservationsVO> reservationSelectSort() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ReservationsVO> reservationList = new ArrayList<>();

		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(RESERVATIONS_SELECT_SORT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int no = rs.getInt("NO");
				String id = rs.getString("ID");
				String custID = rs.getString("CUST_ID");
				String packID = rs.getString("PACK_ID");
				int rCapacity = rs.getInt("RCAPACITY");
				String method = rs.getString("METHOD");
				Date rDate = rs.getDate("RDATE");

				ReservationsVO reserv = new ReservationsVO(no, id, custID, packID, rCapacity, method, rDate);
				reservationList.add(reserv);
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt, rs);
		}

		return reservationList;
	}

	// 예약 정보를 삭제
	public boolean reservationDelete(int no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean successFlag = false;

		try {
			con = DBUtility.dbCon();
			con.setAutoCommit(false);

			pstmt = con.prepareStatement(RESERVATIONS_DELETE);
			pstmt.setInt(1, no);
			int count = pstmt.executeUpdate();

			if (count != 0) {
				con.commit();
				successFlag = true;
			} else {
				con.rollback();
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt);
		}

		return successFlag;
	}

	// 예약 정보를 수정
	public boolean reservationUpdate(ReservationsVO rvo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean successFlag = false;

		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(RESERVATIONS_UPDATE);
			pstmt.setString(1, rvo.getCustID());
			pstmt.setString(2, rvo.getPackID());
			pstmt.setInt(3, rvo.getrCapacity());
			pstmt.setString(4, rvo.getMethod());
			pstmt.setDate(5, rvo.getrDate());
			pstmt.setInt(6, rvo.getNo());

			int count = pstmt.executeUpdate();
			successFlag = (count != 0);
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt);
		}

		return successFlag;
	}

	// 예약 정보를 추가
	public boolean reservationInsert(ReservationsVO rvo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean successFlag = false;

		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(RESERVATIONS_INSERT);
			pstmt.setString(1, rvo.getID()); // ID 추가
			pstmt.setString(2, rvo.getCustID());
			pstmt.setString(3, rvo.getPackID());
			pstmt.setInt(4, rvo.getrCapacity());
			pstmt.setString(5, rvo.getMethod());
			pstmt.setDate(6, rvo.getrDate());

			int count = pstmt.executeUpdate();
			successFlag = (count != 0);
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt);
		}
		return successFlag;
	}

	// 고객 및 패키지 정보를 포함한 예약 정보 조회
	public ArrayList<ReservationsAllVO> reservationAllSelect() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ReservationsAllVO> reservationList = new ArrayList<>();
		con = DBUtility.dbCon();

		try {
			pstmt = con.prepareStatement(RESERVATIONS_CUSTOMERS_PACKAGES_SELECT_JOIN);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int no = rs.getInt("NO");
				String id = rs.getString("ID");
				String custID = rs.getString("CUST_ID");
				String customerName = rs.getString("CUSTOMER_NAME");
				String customerEmail = rs.getString("CUSTOMER_EMAIL");
				String packID = rs.getString("PACK_ID");
				String packageName = rs.getString("PACKAGE_NAME");
				String packageNational = rs.getString("PACKAGE_NATIONAL");
				int rCapacity = rs.getInt("RCAPACITY");
				String method = rs.getString("METHOD");
				Date rDate = rs.getDate("RDATE");

				// JOIN 데이터를 포함한 ReservationsVO 생성
				ReservationsAllVO reserAv = new ReservationsAllVO(no, id, custID, customerName, customerEmail, packID,
						packageName, packageNational, rCapacity, method, rDate);
				reservationList.add(reserAv);
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt, rs);
		}

		return reservationList;
	}

}