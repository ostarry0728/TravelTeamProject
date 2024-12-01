package com.kh.travelMVCProject.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.travelMVCProject.model.ReviewVO;

//private int no;
//private String reservId;
//private int guideReview;
//private int scheReview;
//private int avgReview;

//NO NUMBER, --PK
//RESERV_ID VARCHAR2(30), --FK
//GUIDE_REVIEW NUMBER(10) NOT NULL,
//SCHE_REVIEW NUMBER(10) NOT NULL,
//AVG_REVIEW NUMBER(10)

// REVIEW_SELECT: 리뷰 불러오기 
// REVIEW_INSERT: 리뷰 작성하기 (reservation 불러와서 reservId 원하는 값 골라서 입력하기)
// REVIEW_UPDATE: 리뷰 수정하기 (reservation 불러와서  수정시 리뷰점수만 바꾸기)
// REVIEW_DELETE: 리뷰 삭제하기 (reservation 불러와서 reservId 원하는 값 골라서 삭제하기)
// REVIEW_SORT: 리뷰 평균점수순으로 정렬하기 

public class ReviewDAO {
	// query 
	public final String REVIEW_SELECT = "SELECT * FROM REVIEW";
	public final String REVIEW_INSERT = 
		    "INSERT INTO REVIEW (NO, RESERV_ID, GUIDE_REVIEW, SCHE_REVIEW, AVG_REVIEW) " +
		    "VALUES (REVIEW_SEQ.NEXTVAL, ?, ?, ?, ?)";
	public final static String REVIEW_UPDATE = 
		    "UPDATE REVIEW SET GUIDE_REVIEW = ?, SCHE_REVIEW = ? WHERE RESERV_ID = ?";

	public final String REVIEW_DELETE = "DELETE FROM REVIEW WHERE NO = ?";
	public final String REVIEW_SORT = "SELECT * FROM REVIEW ORDER BY AVG_REVIEW DESC";
	
	
	// select 
	public ArrayList<ReviewVO> reviewSelect() {
		Connection con = null; // 오라클 접속
		Statement stmt = null; // 오라클의 쿼리문 사용 가능하게
		ResultSet rs = null; // 오라클에서 결과문 받아오기
		
		ArrayList<ReviewVO> reviewList = new ArrayList<ReviewVO>();
		
		con = DBUtility.dbCon();
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(REVIEW_SELECT);
			
			if(rs.next()) {
				do {
					int no = rs.getInt("NO");
					String reservId = rs.getString("RESERV_ID");
					int guideReview = rs.getInt("GUIDE_REVIEW");
					int scheReview = rs.getInt("SCHE_REVIEW");
					double avgReview = rs.getDouble("AVG_REVIEW");  
					
					ReviewVO rvo = new ReviewVO(no, reservId, guideReview, scheReview, avgReview);
					reviewList.add(rvo);
				} while (rs.next());
			}else {
				reviewList = null;
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, stmt, rs);
		}
		return reviewList;
	}
	
//	// insert
//	public boolean reviewInsert(ReviewVO rvo) {
//		boolean successFlag = false; // 성공여부
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			con = DBUtility.dbCon();
//			con.setAutoCommit(false); // 자동커밋 비활성화 
//			
//			pstmt = con.prepareStatement(REVIEW_INSERT);
//			
//			pstmt.setInt(1, rvo.getGuideReview());
//			pstmt.setInt(2,  rvo.getScheReview());
//			
//			// 쿼리 실핼 확인
//			int result = pstmt.executeUpdate();
//			
//			if(result > 0) {
//				con.commit();
//				successFlag = true; // 성공시 변경사항 커밋
//			}else {
//				con.rollback(); //실패시 롤백 
//			}
//		} catch (SQLException e) {
//			System.out.println("SQL 예외 발생: " + e.getMessage());
//			try {
//				if(con != null) {
//					con.rollback(); // 오류 발생시 롤백 
//				}
//			} catch (SQLException sqle) {
//				sqle.printStackTrace();
//			}
//		} finally {
//			DBUtility.dbClose(con, pstmt);
//		}
//		return successFlag;
//	}
	
	public boolean reviewInsert(ReviewVO rvo) {
	    boolean successFlag = false; // 성공 여부
	    Connection con = null;
	    PreparedStatement pstmt = null;

	    try {
	        con = DBUtility.dbCon();
	        con.setAutoCommit(false); // 트랜잭션 설정

	        pstmt = con.prepareStatement(REVIEW_INSERT);

	        pstmt.setString(1, rvo.getReservId()); // 예약 ID
	        pstmt.setInt(2, rvo.getGuideReview()); // 가이드 리뷰 점수
	        pstmt.setInt(3, rvo.getScheReview()); // 여행 일정 점수
	        pstmt.setDouble(4, (rvo.getGuideReview() + rvo.getScheReview()) / 2.0); // 평균 점수 계산

	        // 실행
	        int result = pstmt.executeUpdate();

	        if (result > 0) {
	            con.commit();
	            successFlag = true;
	        } else {
	            con.rollback();
	        }
	    } catch (SQLException e) {
	        System.out.println("SQL 예외 발생: " + e.getMessage());
	        try {
	            if (con != null) con.rollback(); // 롤백
	        } catch (SQLException rollbackEx) {
	            rollbackEx.printStackTrace();
	        }
	    } finally {
	        DBUtility.dbClose(con, pstmt);
	    }

	    return successFlag;
	}

	
//	// update 
//	public static boolean reviewUpdate(ReviewVO rvo) {
//	    boolean successFlag = false; 
//	    Connection con = null;
//	    PreparedStatement pstmt = null;
//	    
//	    try {
//	        con = DBUtility.dbCon();
//	        con.setAutoCommit(false); // 자동커밋 비활성화
//	        pstmt = con.prepareStatement(REVIEW_UPDATE);
//	        
//			pstmt.setInt(1, rvo.getGuideReview());
//			pstmt.setInt(2,  rvo.getScheReview());
//			
//	        int result = pstmt.executeUpdate();
//	        if (result > 0) {
//	            con.commit(); // 변경 사항 커밋
//	            successFlag = true;
//	        } else {
//	            con.rollback(); // 실패 시 롤백
//	            System.out.println("업데이트 쿼리 결과: 수정할 정보를 찾을 수 없습니다.");
//	        }
//	    } catch(SQLException e) {
//	        System.out.println("예외 발생: " + e.getMessage());
//	        try {
//	            if (con != null) con.rollback(); // 오류 발생 시 롤백
//	        } catch (SQLException rollbackEx) {
//	            rollbackEx.printStackTrace();
//	        }
//	    } finally {
//	    	DBUtility.dbClose(con, pstmt);
//	    }
//	    return successFlag;
//	}
	//update
	public static boolean reviewUpdate(ReviewVO rvo) {
	    boolean successFlag = false; 
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    
	    try {
	        con = DBUtility.dbCon();
	        con.setAutoCommit(false); // 자동 커밋 비활성화
	        pstmt = con.prepareStatement(REVIEW_UPDATE);
	        
	        pstmt.setInt(1, rvo.getGuideReview()); // 가이드 리뷰 점수
	        pstmt.setInt(2, rvo.getScheReview());  // 여행 일정 점수
	        pstmt.setString(3, rvo.getReservId()); // 예약 ID (WHERE 조건)

	        int result = pstmt.executeUpdate();

	        if (result > 0) {
	            con.commit(); // 변경 사항 커밋
	            successFlag = true;
	        } else {
	            con.rollback(); // 실패 시 롤백
	            System.out.println("업데이트 쿼리 결과: 수정할 정보를 찾을 수 없습니다.");
	        }
	    } catch(SQLException e) {
	        System.out.println("예외 발생: " + e.getMessage());
	        try {
	            if (con != null) con.rollback(); // 오류 발생 시 롤백
	        } catch (SQLException rollbackEx) {
	            rollbackEx.printStackTrace();
	        }
	    } finally {
	        DBUtility.dbClose(con, pstmt);
	    }
	    return successFlag;
	}

	
	// delete
	public boolean reviewDelete(ReviewVO rvo) {
		boolean successFlag =false; 
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(REVIEW_DELETE);
			pstmt.setInt(1, rvo.getNo());
			int result = pstmt.executeUpdate();
			successFlag = (result != 0) ? true : false ;
		} catch(SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt);
		}
		return successFlag; 
	}
	
	
	// sort
	public ArrayList<ReviewVO> reviewSort() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		ArrayList<ReviewVO> reviewList = new ArrayList<ReviewVO>();
		
		try {
			con = DBUtility.dbCon();
			stmt = con.createStatement();
			rs = stmt.executeQuery(REVIEW_SORT);
			
			while (rs.next()) {
				int no = rs.getInt("NO");
				String reservId = rs.getString("RESERV_ID");
				int guideReview = rs.getInt("GUIDE_REVIEW");
				int scheReview = rs.getInt("SCHE_REVIEW");
				double avgReview = rs.getDouble("AVG_REVIEW");  
				
				ReviewVO rvo = new ReviewVO(no, reservId, guideReview, scheReview, avgReview);
				reviewList.add(rvo);
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, stmt, rs);
		}
		return reviewList;
	}
	
}
