package com.kh.travelMVCProject.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import com.kh.travelMVCProject.model.GuideVO;
import com.kh.travelMVCProject.model.PackageVO;

//private int no;
//private String id;
//private String name;
//private int pCapacity;
//private String national;
//pricate int price;
//private String guideId;
//private Date sDate;
//private Date eDate;

public class PackageRegisterManager {
	public static Scanner sc = new Scanner(System.in);
	
	// select (list)
	public void selectManager() {
		ArrayList<PackageVO> packageList = new ArrayList<PackageVO>();
		PackageDAO pdao = new PackageDAO();
		packageList = pdao.packageSelect();
		
		if(packageList == null) {
			System.out.println("데이터가 존재하지 않습니다.");
			return ;
		}
		printPackageList(packageList);
	}

	
	// insert 
	public void insertManager() {
	    PackageDAO pdao = new PackageDAO();
	    GuideDAO gdao = new GuideDAO();
	    
	    // 가이드 리스트 출력
	    ArrayList<GuideVO> guideList = gdao.guideSelect();
	    if (guideList == null || guideList.isEmpty()) {
	        System.out.println("가이드 데이터가 없습니다.");
	        return;
	    }
	    System.out.println("가이드 전체 리스트");
	    for (GuideVO gvo : guideList) {
	        System.out.println(gvo);
	    }
	    
	    // 입력받기
	    System.out.print("함께할 가이드 id 선택하기: ");
	    String guideId = sc.nextLine();
	    
	    // 랜덤 ID 생성 및 중복 검사
	    String id = generateRandomId();
	    while (pdao.isIdDuplicate(id)) { // 중복 검사
	        id = generateRandomId();
	    }
	    System.out.println("생성된 랜덤 패키지 ID: " + id);
	    
	    System.out.print("여행 상품 이름 입력하기: ");
	    String name = sc.nextLine();
	    
	    System.out.print("여행 상품 수량 입력하기: ");
	    int pCapacity = Integer.parseInt(sc.nextLine());
	    
	    System.out.print("여행 가는 국가 입력하기: ");
	    String national = sc.nextLine();
	    
	    System.out.println("여행 상품 가격 입력하기: ");
	    int price = Integer.parseInt(sc.nextLine());
	    
	    // 날짜 입력받기
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    formatter.setLenient(false); // 엄격한 형식 검사
	    
	    Date sDate = null;
	    System.out.println("여행 시작 날짜 입력하기 (형식: yyyy-MM-dd): ");
	    while (sDate == null) {
	        try {
	            String startDateStr = sc.nextLine();
	            sDate = (Date) formatter.parse(startDateStr);
	        } catch (ParseException e) {
	            System.out.println("잘못된 날짜 형식입니다. yyyy-MM-dd 형식으로 다시 입력해주세요.");
	        }
	    }
	    
	    Date eDate = null;
	    System.out.println("여행 종료 날짜 입력하기 (형식: yyyy-MM-dd): ");
	    while (eDate == null) {
	        try {
	            String endDateStr = sc.nextLine();
	            eDate = (Date) formatter.parse(endDateStr);
	        } catch (ParseException e) {
	            System.out.println("잘못된 날짜 형식입니다. yyyy-MM-dd 형식으로 다시 입력해주세요.");
	        }
	    }
	    
	    PackageVO pvo = new PackageVO(id, name, pCapacity, national, price, guideId, sDate, eDate);
		
		// 데이터 삽입
	    boolean successFlag = pdao.packageInsert(pvo);
	    
	    if(successFlag) {
	    	System.out.println("여행 상품 정보가 성공적으로 입력되었습니다.");
	    	
	    	try {
	    		Connection con = DBUtility.dbCon();
	    		PreparedStatement pstmt = con.prepareStatement(
	    				"SELECT NO, ID, NAME,  PCAPATIRY, NATIONAL, PRICE, GUIDE_ID, SDATE, EDATE FROM PACKAGE WHERE GUIDE_ID = ?"
	    				);
	    		
	    		pstmt.setString(1, guideId);
	    		
	    		ResultSet rs = pstmt.executeQuery();
	    		
	    		if(rs.next()) {
	    			int no = rs.getInt("NO");
	    			
	    			System.out.println("no = " + no);
	    		}
	    		
	    	} catch (SQLException e) {
	    		System.out.println("여행상품 입력 중 예외: " + e.getMessage());
	    	}
	    } else {
	    	System.out.println("여행상품 정보 입력에 실패하였습니다.");
	    }

	}

	
	// update 
	public void updateManager() {
		
	}

	
	// delete
	public void deleteManager() {
		PackageDAO pdao = new PackageDAO();
		ArrayList<PackageVO> packageList = new ArrayList<PackageVO>();
		printPackageList(packageList);
		
		System.out.println("삭제할 상품의 상품 번호를 입력하세요: ");
		int no = Integer.parseInt(sc.nextLine());
		
		PackageVO pvo = new PackageVO();
		pvo.setNo(no);
		
		boolean successFlag = pdao.packageDelete(pvo);
		
		if(successFlag == true) {
			System.out.println("삭제성공");
		}else {
			System.out.println("삭제실패");
		}
	}

	
	// sort
	public void sortManager() {
		PackageDAO pdao = new PackageDAO();
		ArrayList<PackageVO> packageList = pdao.packageSort();
		
		if(packageList == null || packageList.isEmpty()) {
			System.out.println("데이터가 존재하지 않습니다.");
			return;
		}
		printPackageList(packageList);
	}
	
	
	// arrayList
	public void printPackageList(ArrayList<PackageVO> packageList) {
		for(PackageVO data: packageList) {
			System.out.println(data);
		}
	}
	
	
	// 랜덤 ID 생성 메서드
	private String generateRandomId() {
	    Random random = new Random();
	    char letter = (char) (random.nextInt(26) + 'a'); // 소문자 1자리
	    int number = random.nextInt(10000000, 99999999); // 7자리 숫자
	    return letter + String.valueOf(number);
	}
}
