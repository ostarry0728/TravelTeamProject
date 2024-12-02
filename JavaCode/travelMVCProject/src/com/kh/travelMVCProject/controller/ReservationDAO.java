package com.kh.travelMVCProject.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kh.travelMVCProject.model.ReservationAllVO;
import com.kh.travelMVCProject.model.ReservationVO;

public class ReservationDAO {

    public final String RESERVATION_SELECT = "SELECT * FROM RESERVATION";
    public final String RESERVATION_SELECT_SORT = "SELECT * FROM RESERVATION ORDER BY NO";
    public final String RESERVATION_DELETE = "DELETE FROM RESERVATION WHERE NO = ?";
    public final String RESERVATION_UPDATE = 
            "UPDATE RESERVATION SET ID = ?, CUST_ID = ?, PACK_ID = ?, METHOD = ? WHERE NO = ?";
    public final String RESERVATION_INSERT = 
            "INSERT INTO RESERVATION (NO, ID, CUST_ID, PACK_ID, METHOD, RDATE) VALUES (RESERVATION_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";

    // 예약 정보를 조회
    public ArrayList<ReservationVO> reservationSelect() {
        ArrayList<ReservationVO> reservationList = new ArrayList<>();
        try (Connection con = DBUtility.dbCon();
             PreparedStatement pstmt = con.prepareStatement(RESERVATION_SELECT);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int no = rs.getInt("NO");
                String id = rs.getString("ID");
                String custID = rs.getString("CUST_ID");
                String packID = rs.getString("PACK_ID");
                String method = rs.getString("METHOD");
                Date rDate = rs.getDate("RDATE");

                ReservationVO reserv = new ReservationVO(no, id, custID, packID, method, rDate);
                reservationList.add(reserv);
            }
        } catch (SQLException e) {
            System.out.println("Error in reservationSelect: " + e.getMessage());
        }
        return reservationList;
    }

    // 예약 정보를 정렬하여 조회
    public ArrayList<ReservationVO> reservationSelectSort() {
        ArrayList<ReservationVO> reservationList = new ArrayList<>();
        try (Connection con = DBUtility.dbCon();
             PreparedStatement pstmt = con.prepareStatement(RESERVATION_SELECT_SORT);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int no = rs.getInt("NO");
                String id = rs.getString("ID");
                String custID = rs.getString("CUST_ID");
                String packID = rs.getString("PACK_ID");
                String method = rs.getString("METHOD");
                Date rDate = rs.getDate("RDATE");

                ReservationVO reserv = new ReservationVO(no, id, custID, packID, method, rDate);
                reservationList.add(reserv);
            }
        } catch (SQLException e) {
            System.out.println("Error in reservationSelectSort: " + e.getMessage());
        }
        return reservationList;
    }

    // 예약 정보를 삭제
    public boolean reservationDelete(int no) {
        boolean successFlag = false;
        try (Connection con = DBUtility.dbCon();
             PreparedStatement pstmt = con.prepareStatement(RESERVATION_DELETE)) {

            pstmt.setInt(1, no);
            int count = pstmt.executeUpdate();

            if (count != 0) {
                successFlag = true;
            }
//            System.out.println("Deleted rows: " + count);
        } catch (SQLException e) {
            System.out.println("Error in reservationDelete: " + e.getMessage());
        }
        return successFlag;
    }

    // 예약 정보를 수정
    public boolean reservationUpdate(ReservationVO rvo) {
        boolean successFlag = false;
        try (Connection con = DBUtility.dbCon();
             PreparedStatement pstmt = con.prepareStatement(RESERVATION_UPDATE)) {

            pstmt.setString(1, rvo.getID());
            pstmt.setString(2, rvo.getCustID());
            pstmt.setString(3, rvo.getPackID());
            pstmt.setString(4, rvo.getMethod());
            pstmt.setInt(5, rvo.getNo());

            int count = pstmt.executeUpdate();
            successFlag = (count != 0);
        } catch (SQLException e) {
            System.out.println("Error in reservationUpdate: " + e.getMessage());
        }
        return successFlag;
    }

    // 예약 정보를 추가
    public boolean reservationInsert(ReservationVO rvo) {
        boolean successFlag = false;
	    Connection con = null;
	    PreparedStatement pstmt = null;
        try {
	        con = DBUtility.dbCon();
	        con.setAutoCommit(false); // 자동 커밋 비활성화
	        
        	 pstmt = con.prepareStatement(RESERVATION_INSERT);
        	 pstmt.setString(1, rvo.getID());
             pstmt.setString(2, rvo.getCustID());
             pstmt.setString(3, rvo.getPackID());
             pstmt.setString(4, rvo.getMethod());
             pstmt.setDate(5, rvo.getrDate()); // RDATE 추가


//             int count = pstmt.executeUpdate();
//             successFlag = (count != 0);
 	        // 쿼리 실행
 	        int result = pstmt.executeUpdate();
 	        if (result > 0) {
 	            con.commit(); // 변경 사항 커밋
 	            successFlag = true;
 	        } else {
 	            con.rollback(); // 실패 시 롤백
 	        }
	    } catch (SQLException e) {
	        System.out.println("SQL 예외 발생: " + e.getMessage());
	        try {
	            if (con != null) con.rollback(); // 오류 발생 시 롤백
	        } catch (SQLException rollbackEx) {
	            rollbackEx.printStackTrace();
	        }
	    } finally {
	        DBUtility.dbClose(con, pstmt); // 자원 해제
	    }

	    return successFlag;
	}
    
    
}
