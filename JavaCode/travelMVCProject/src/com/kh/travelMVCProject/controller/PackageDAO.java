package com.kh.travelMVCProject.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.travelMVCProject.model.PackageVO;
import com.kh.travelMVCProject.model.ReviewVO;

//private int no;
//private String id;
//private String name;
//private int pCapacity;
//private String national;
//int price
//private String guideId;
//private Date sDate;
//private Date eDate;

public class PackageDAO {

	// query 
	public final String PACKAGE_SELECT = "SELECT * FROM PACKAGE";
	public final String PACKAGE_INSERT = 
		    "INSERT INTO PACKAGE (NO, ID, NAME, NATIONAL, PRICE, GUIDE_ID, SDATE, EDATE) " +
		    "VALUES (PACKAGE_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
	public final static String PACKAGE_UPDATE = 
		    "UPDATE PACKAGE SET NAME = ?, NATIONAL = ?, PRICE = ?, SDATE = ?, EDATE = ? WHERE NO = ?";

	public final String PACKAGE_DELETE = "DELETE FROM PACKAGE WHERE NO = ?";
	public final String PACKAGE_SORT = "SELECT * FROM PACKAGE ORDER BY PRICE DESC";
	
	
	
	// select (list)
	public ArrayList<PackageVO> packageSelect(){
		Connection con = null; // 오라클 접속
		Statement stmt = null; // 오라클의 쿼리문 사용 가능하게
		ResultSet rs = null; // 오라클에서 결과문 받아오기
		
		ArrayList<PackageVO> packageList = new ArrayList<PackageVO>();
		
		try {
			con = DBUtility.dbCon();
			stmt = con.createStatement();
			rs = stmt.executeQuery(PACKAGE_SELECT);
			
			if(rs.next()) {
				do {
					int no = rs.getInt("NO");
					String id = rs.getString("ID");
					String name = rs.getString("NAME");
//					int pCapacity = rs.getInt("PCAPACITY");
					String national = rs.getString("NATIONAL");
					int price = rs.getInt("PRICE");
					String guideId = rs.getString("GUIDE_ID");
					Date sDate = rs.getDate("SDATE");
					Date eDate = rs.getDate("EDATE");
					
//					PackageVO pvo = new PackageVO(no, id, name, pCapacity, national, price, guideId, sDate, eDate);
					PackageVO pvo = new PackageVO(no, id, name, national, price, guideId, sDate, eDate);
					packageList.add(pvo);
				} while(rs.next());
			}else {
				
			packageList = null;
				
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, stmt, rs);
		}
		
		return packageList;
	}
	
	
	
	
//	 // insert 
//	public boolean packageInsert(PackageVO pvo) {
//		boolean successFlag = false; // 성공여부
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			con = DBUtility.dbCon();
//			con.setAutoCommit(false);
//			
//			pstmt = con.prepareStatement(PACKAGE_INSERT);
//			
//			pstmt.setString(1, pvo.getId());
//			pstmt.setString(2, pvo.getName());
//			pstmt.setInt(3, pvo.getPCapacity());
//			pstmt.setString(4, pvo.getNational());
//			pstmt.setInt(5, pvo.getPrice());
//			pstmt.setString(6, pvo.getGuideId());
//			pstmt.setDate(7, pvo.getSDate());
//			pstmt.setDate(8, pvo.getEDate());
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
//		
//		return successFlag;
//	}
//	
	
	public boolean packageInsert(PackageVO pvo) {
	    boolean successFlag = false; // 성공 여부
	    Connection con = null;
	    PreparedStatement pstmt = null;

	    try {
	        con = DBUtility.dbCon();
	        con.setAutoCommit(false); // 트랜잭션 설정

	        pstmt = con.prepareStatement(PACKAGE_INSERT);

	        pstmt.setString(1, pvo.getId());
	        pstmt.setString(2, pvo.getName());
//	        pstmt.setInt(3, pvo.getPCapacity());
	        pstmt.setString(3, pvo.getNational());
	        pstmt.setInt(4, pvo.getPrice());
	        pstmt.setString(5, pvo.getGuideId());
	        pstmt.setDate(6, pvo.getSDate());
	        pstmt.setDate(7, pvo.getEDate());

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

	
	
	
	// update 
	public static boolean packageUpdate(PackageVO pvo) {
	    boolean successFlag = false; 
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    
	    try {
	        con = DBUtility.dbCon();
	        con.setAutoCommit(false);
	        pstmt = con.prepareStatement(PACKAGE_UPDATE);
	        
	        pstmt.setString(1, pvo.getName());
	        pstmt.setString(2, pvo.getNational());
	        pstmt.setInt(3, pvo.getPrice());
	        pstmt.setDate(4, pvo.getSDate());
	        pstmt.setDate(5, pvo.getEDate());
	        pstmt.setInt(6, pvo.getNo()); // 6번째 매개변수 바인딩

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
	public boolean packageDelete(PackageVO pvo) {
		boolean successFlag =false; 
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(PACKAGE_DELETE);
			pstmt.setInt(1, pvo.getNo());
			int result = pstmt.executeUpdate();
			successFlag = (result != 0 ) ? true: false;

		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt);
		}
		
		return successFlag;
	}
	
	
	
	
	// sort
	public ArrayList<PackageVO> packageSort(){
		Connection con = null; // 오라클 접속
		Statement stmt = null; // 오라클의 쿼리문 사용 가능하게
		ResultSet rs = null; // 오라클에서 결과문 받아오기
		
		ArrayList<PackageVO> packageList = new ArrayList<PackageVO>();
		
		try {
			con = DBUtility.dbCon();
			stmt = con.createStatement();
			rs = stmt.executeQuery(PACKAGE_SORT);
			
			while (rs.next()) {
				int no = rs.getInt("NO");
				String id = rs.getString("ID");
				String name = rs.getString("NAME");
//				int pCapacity = rs.getInt("PCAPACITY");
				String national = rs.getString("NATIONAL");
				int price = rs.getInt("PRICE");
				String guideId = rs.getString("GUIDE_ID");
				Date sDate = rs.getDate("SDATE");
				Date eDate = rs.getDate("EDATE");
			
//				PackageVO pvo = new PackageVO(no, id, name, pCapacity, national, price, guideId, sDate, eDate);
				PackageVO pvo = new PackageVO(no, id, name, national, price, guideId, sDate, eDate);
				packageList.add(pvo);
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, stmt, rs);
		}
		
		return packageList;
	}
	
	// 랜덤생성아이디 중복 확ㅇ니 
	public boolean isIdDuplicate(String id) {
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    boolean isDuplicate = false; // 중복 여부를 나타내는 플래그

	    try {
	        // 데이터베이스 연결 (DBConnection 클래스의 getConnection 메서드가 있다고 가정)
	        con = DBUtility.dbCon();

	        // SQL 쿼리 작성
	        String sql = "SELECT COUNT(*) FROM PACKAGE WHERE ID = ?";

	        // PreparedStatement에 쿼리 및 파라미터 설정
	        pstmt = con.prepareStatement(sql);
	        pstmt.setString(1, id);

	        // 쿼리 실행
	        rs = pstmt.executeQuery();

	        // 결과 처리
	        if (rs.next()) {
	            int count = rs.getInt(1); // COUNT(*) 값 가져오기
	            isDuplicate = (count > 0); // 1개 이상 존재하면 중복
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        // 자원 반납
	        try {
	            if (rs != null) rs.close();
	            if (pstmt != null) pstmt.close();
	            if (con != null) con.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    return isDuplicate;
	}

}
