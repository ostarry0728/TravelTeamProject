package com.kh.travelMVCProject.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.travelMVCProject.model.PackageVO;

//private int no;
//private String id;
//private String name;
//private int pCapacity;
//private String national;
//private String guideId;
//private Date sDate;
//private Date eDate;

public class PackageDAO {

	// query 
	
	
	
	// select (list)
	public ArrayList<PackageVO> packageSelect(){
		Connection con = null; // 오라클 접속
		Statement stmt = null; // 오라클의 쿼리문 사용 가능하게
		ResultSet rs = null; // 오라클에서 결과문 받아오기
		
		ArrayList<PackageVO> packageList = new ArrayList<PackageVO>();
		
		// 내용 
		
		return packageList;
	}
	
	
	
	
	 // insert 
	public boolean packageInsert(PackageVO pvo) {
		boolean successFlag = false; // 성공여부
		Connection con = null;
		PreparedStatement pstmt = null;
		
		// 내용 
		
		return successFlag;
	}
	
	
	
	
	// update 
	public static boolean packageUpdate(PackageVO pvo) {
	    boolean successFlag = false; 
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    
	    // 내용 
	    
	    return successFlag;
	}
	
	
	
	
	
	// delete
	public boolean packageDelete(PackageVO pvo) {
		boolean successFlag =false; 
		Connection con = null;
		PreparedStatement pstmt = null;
		
		// 내용
		
		return successFlag;
	}
	
	
	
	
	// sort
	public ArrayList<PackageVO> packageSort(){
		Connection con = null; // 오라클 접속
		Statement stmt = null; // 오라클의 쿼리문 사용 가능하게
		ResultSet rs = null; // 오라클에서 결과문 받아오기
		
		ArrayList<PackageVO> packageList = new ArrayList<PackageVO>();
		
		// 내용
		
		return packageList;
	}
	
	// PackageDAO 클래스 내부에 추가
	public boolean isIdDuplicate(String id) {
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    boolean isDuplicate = false; // 중복 여부를 나타내는 플래그

	    try {
	        // 데이터베이스 연결 (DBConnection 클래스의 getConnection 메서드가 있다고 가정)
	        con = DBUtility.dbCon();

	        // SQL 쿼리 작성
	        String sql = "SELECT COUNT(*) FROM PACKAGES WHERE ID = ?";

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
