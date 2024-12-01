package com.kh.travelMVCProject.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.kh.travelMVCProject.model.ReservationVO;
import com.kh.travelMVCProject.model.ReviewVO;

//private int no;
//private String reservId;
//private int guideReview;
//private int scheReview;
//private int avgReview;

public class ReviewRegisterManager {
	public static Scanner sc = new Scanner(System.in);
	
	// select 
	public void selectManager() {
		ArrayList<ReviewVO> reviewList = new ArrayList<ReviewVO>();
		ReviewDAO rdao = new ReviewDAO();
		reviewList = rdao.reviewSelect();
		
		if(reviewList == null) {
			System.out.println("데이터가 존재하지 않습니다.");
			return;
		}
		printReviewList(reviewList);
	}
	
//	// insert
//	public void insertManager() {
//		ReviewDAO reviewDAO = new ReviewDAO();
//		ReservationDAO reservationDAO = new ReservationDAO();
//		
//		// 예약 리스트 출력
//		ArrayList<ReservationVO> reservationList = reservationDAO.reservationSelect();
//		if(reservationList == null || reservationList.isEmpty()) {
//			System.out.println("예약 데이터가 없습니다.");
//			return;
//		}
//		
//		System.out.println("예약 전체 리스트:");
//		for(ReservationVO rvo : reservationList) {
//			System.out.println(rvo);
//		}
//		
//		// 예약id 입력받기 
//		System.out.println("리뷰를 남길 예약 ID를 입력하세요: ");
//		String reservId = sc.nextLine();
//		
//		System.out.print("가이드에 대한 점수를 매겨주세요(1~10): ");
//		int guideReview = Integer.parseInt(sc.nextLine());
//		
//		System.out.print("여행일정에 대한 점수를 매겨주세요(1~10): ");
//		int scheReview = Integer.parseInt(sc.nextLine());
//		
//		ReviewVO rvo = new ReviewVO(reservId, guideReview, scheReview);
//		
//		// 데이터 삽입
//		boolean successFlag = reviewDAO.reviewInsert(rvo);
//		
//		if(successFlag) {
//			System.out.println("리뷰 정보가 성공적으로 입력되었습니다."); 
//			try {
//				Connection con = DBUtility.dbCon();
//				PreparedStatement pstmt = con.prepareStatement(
//						"SELECT NO, RESERV_ID, GUIDE_REVIEW, SCHE_REVIEW, AVG_REVIEW FROM REVIEW WHERE RESERVID = ? AND GUIDE_REVIEW = ? AND SCHE_REVIEW = ?"			
//				);
//				
//				pstmt.setString(1, reservId);
//				pstmt.setInt(2, guideReview);
//				pstmt.setInt(3, scheReview);
//				
//				ResultSet rs = pstmt.executeQuery();
//				
//				if(rs.next()) {
//					int no = rs.getInt("NO");
//					double avgReview = rs.getDouble("AVG_REVIEW");
//					
//					System.out.println("no = " + no + ", avgReview = " + avgReview);
//				}
//			} catch (SQLException e) {
//				System.out.println("조회중 예외 SQL: " + e.getMessage());
//			} 
//		} else {
//			System.out.println("리뷰 정보 입력에 실패하였습니다.");
//		}
//	}
	
	public void insertManager() {
	    ReviewDAO reviewDAO = new ReviewDAO();
	    ReservationDAO reservationDAO = new ReservationDAO();

	    // 예약 리스트 출력
	    ArrayList<ReservationVO> reservationList = reservationDAO.reservationSelect();
	    if (reservationList == null || reservationList.isEmpty()) {
	        System.out.println("예약 데이터가 없습니다.");
	        return;
	    }

	    System.out.println("예약 전체 리스트:");
	    for (ReservationVO rvo : reservationList) {
	        System.out.println(rvo);
	    }

	    // 예약 ID 입력받기
	    System.out.println("리뷰를 남길 예약 ID를 입력하세요: ");
	    String reservId = sc.nextLine();

	    System.out.print("가이드에 대한 점수를 매겨주세요(1~10): ");
	    int guideReview = Integer.parseInt(sc.nextLine());

	    System.out.print("여행일정에 대한 점수를 매겨주세요(1~10): ");
	    int scheReview = Integer.parseInt(sc.nextLine());

	    // ReviewVO 생성 및 데이터 삽입
	    ReviewVO rvo = new ReviewVO(reservId, guideReview, scheReview);
	    boolean successFlag = reviewDAO.reviewInsert(rvo);

	    if (successFlag) {
	        System.out.println("리뷰 정보가 성공적으로 입력되었습니다.");
	        try {
	            Connection con = DBUtility.dbCon();
	            PreparedStatement pstmt = con.prepareStatement(
	                "SELECT NO, RESERV_ID, GUIDE_REVIEW, SCHE_REVIEW, AVG_REVIEW " +
	                "FROM REVIEW WHERE RESERV_ID = ? AND GUIDE_REVIEW = ? AND SCHE_REVIEW = ?"
	            );

	            pstmt.setString(1, reservId);
	            pstmt.setInt(2, guideReview);
	            pstmt.setInt(3, scheReview);

	            ResultSet rs = pstmt.executeQuery();

	            if (rs.next()) {
	                int no = rs.getInt("NO");
	                double avgReview = rs.getDouble("AVG_REVIEW");

	                System.out.println("리뷰 ID = " + no + ", 평균 리뷰 점수 = " + avgReview);
	            }

	            DBUtility.dbClose(con, pstmt, rs);
	        } catch (SQLException e) {
	            System.out.println("조회 중 예외 SQL: " + e.getMessage());
	        }
	    } else {
	        System.out.println("리뷰 정보 입력에 실패하였습니다.");
	    }
	}

	
	// update 
	public void updateManager() {
		System.out.println("수정할 리뷰의 리뷰번호를 입력하세요: ");
		int no = Integer.parseInt(sc.nextLine());

		System.out.print("수정할 가이드 평점을 입력하세요(1~10): ");
		int guideReview = Integer.parseInt(sc.nextLine());

		System.out.print("수정할 여행일정에 대한 평점을 입력하세요(1~10): ");
		int scheReview = Integer.parseInt(sc.nextLine());

		// 수정할 리뷰 데이터를 가져오기 위해 RESERV_ID를 조회
		ReviewDAO reviewDAO = new ReviewDAO();
		ArrayList<ReviewVO> reviewList = reviewDAO.reviewSelect();

		String reservId = null;
		for (ReviewVO rvo : reviewList) {
		    if (rvo.getNo() == no) {
		        reservId = rvo.getReservId();
		        break;
		    }
		}

		if (reservId == null) {
		    System.out.println("해당 번호의 리뷰를 찾을 수 없습니다.");
		    return;
		}

		System.out.println("수정된 데이터 리뷰번호: " + no + ", 가이드 평점: " + guideReview + ", 여행일정 평점: " + scheReview);

		ReviewVO rvo = new ReviewVO();
		rvo.setReservId(reservId);
		rvo.setGuideReview(guideReview);
		rvo.setScheReview(scheReview);

		boolean successFlag = ReviewDAO.reviewUpdate(rvo);

		if (successFlag) {
		    System.out.println("수정처리 성공");
		} else {
		    System.out.println("수정처리 실패");
		}

	}
	
	// delete
	public void deleteManager() {
		ReviewDAO rdao = new ReviewDAO();
		System.out.print("삭제할 리뷰의 예약 id를 입력하세요: ");
		String reservId = sc.nextLine();
		
		ReviewVO rvo = new ReviewVO();
		rvo.setReservId(reservId);
		
		boolean successFlag = rdao.reviewDelete(rvo);
		
		if(successFlag == true) {
			System.out.println("삭제 성공");
		} else {
			System.out.println("삭제 실패");
		}
	}
	
	// sort
	public void sortManager() {
		ReviewDAO rdao = new ReviewDAO();
		ArrayList<ReviewVO> reviewList = rdao.reviewSort();
		
		if(reviewList == null || reviewList.isEmpty()) {
			System.out.println("데이터가 존재하지 않습니다.");
			return;
		}
		printReviewList(reviewList); 
	}

	// arrayList
	public void printReviewList(ArrayList<ReviewVO> reviewList) {
		for(ReviewVO data : reviewList) {
			System.out.println(data);
		}
		
	}
	
	// arrayList
	public  void printReservationList(ArrayList<ReservationVO> reservationList) {
		for(ReservationVO data : reservationList) {
			System.out.println(data);
		}
	}
}
