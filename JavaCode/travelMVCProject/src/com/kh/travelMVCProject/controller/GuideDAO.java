package com.kh.travelMVCProject.controller;

import java.util.ArrayList;

import com.kh.travelMVCProject.model.CustomerVO;
import com.kh.travelMVCProject.model.GuideVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kh.travelMVCProject.model.GuideVO;

public class GuideDAO {
	public final String GUIDE_INSERT = "INSERT INTO GUIDE VALUES(GUIDE_SEQ.NEXTVAL, ?, ?, ?, ?)";
	public final String GUIDE_SELECT = "SELECT * FROM GUIDE";
	public final String GUIDE_DELETE = "DELETE FROM GUIDE WHERE NO = ?";
	public final String GUIDE_UPDATE = "UPDATE GUIDE SET NAME = ?, PHONE = ?, LANGUAGES = ? WHERE NO = ?";
	public final String GUIDE_SORT = "SELECT * FROM GUIDE ORDER BY NO";

	// insert
	public boolean guideInsert(GuideVO gvo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean successFlag = false;
		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(GUIDE_INSERT);
			pstmt.setString(1, gvo.getId());
			pstmt.setString(2, gvo.getName());
			pstmt.setString(3, gvo.getPhone());
			pstmt.setString(4, gvo.getLanguages());
			int result = pstmt.executeUpdate();
			successFlag = (result != 0) ? true : false;
			printSingleGuide(gvo);
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt);
		}
		return successFlag;
	}

	// select
	public ArrayList<GuideVO> guideSelect(GuideVO gvo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<GuideVO> guideList = new ArrayList<GuideVO>();
		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(GUIDE_SELECT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int no = rs.getInt("NO");
				String id = rs.getString("ID");
				String name = rs.getString("NAME");
				String phone = rs.getString("PHONE");
				String languages = rs.getString("LANGUAGES");
				GuideVO guideVO = new GuideVO(no, id, name, phone, languages);
				guideList.add(guideVO);
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt, rs);
		}
		return guideList;
	}

	// delete
	public boolean guideDelete(GuideVO gvo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean successFlag = false;
		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(GUIDE_DELETE);
			pstmt.setInt(1, gvo.getNo());
			int result = pstmt.executeUpdate();
			successFlag = (result != 0) ? true : false;
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt);
		}
		return successFlag;
	}

	// update
	public boolean guideUpdate(GuideVO gvo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean successFlag = false;
		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(GUIDE_UPDATE);
			pstmt.setString(1, gvo.getName());
			pstmt.setString(2, gvo.getPhone());
			pstmt.setString(3, gvo.getLanguages());
			pstmt.setInt(4, gvo.getNo());
			int result = pstmt.executeUpdate();
			successFlag = (result != 0) ? true : false;
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt);
		}
		return successFlag;
	}

	// sort
	public ArrayList<GuideVO> guideSort(GuideVO gvo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<GuideVO> guideList = new ArrayList<GuideVO>();
		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(GUIDE_SORT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int no = rs.getInt("NO");
				String id = rs.getString("ID");
				String name = rs.getString("NAME");
				String phone = rs.getString("PHONE");
				String languages = rs.getString("LANGUAGES");
				GuideVO guideVO = new GuideVO(no, id, name, phone, languages);
				guideList.add(guideVO);
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt, rs);
		}
		return guideList;
	}

	
	private void printSingleGuide(GuideVO gvo) {
		System.out.println();
	    // 헤더 출력
	    System.out.printf(
	    	    "%-6s %-10s %-8s %-15s %-10s\n",
	    	    "가이드No", "가이드ID", "이름", "전화번호", "가능언어"
	    );
	    System.out.println("-----------------------------------------------------------------");

	    // 삽입된 고객 데이터 출력
	    System.out.printf(
	    	    "%-6s %-11s %-8s %-15s %-10s\n",
	        gvo.getNo(), gvo.getId(), gvo.getName(), gvo.getPhone(), gvo.getLanguages()
	    );
	    System.out.println();
	}
}