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
	    GuideVO gvo = new GuideVO();

	    // 가이드 리스트 출력
	    ArrayList<GuideVO> guideList = gdao.guideSelect(gvo);
	    if (guideList == null || guideList.isEmpty()) {
	        System.out.println("가이드 데이터가 없습니다.");
	        return;
	    }
	    printGuideList(guideList); // 가이드 리스트를 보기 좋게 출력

	    // 입력받기
	    System.out.print("함께할 가이드 id 선택하기 >> ");
	    String guideId = sc.nextLine();

	    // 랜덤 ID 생성 및 중복 검사
	    String id = generateRandomId();
	    while (pdao.isIdDuplicate(id)) { // 중복 검사
	        id = generateRandomId();
	    }
//	    System.out.println("생성된 랜덤 패키지 ID: " + id);

	    System.out.print("여행 상품 이름 입력하기 >> ");
	    String name = sc.nextLine();

//	    System.out.print("여행 상품 수량 입력하기: ");
//	    int pCapacity = 0;
//	    while (true) {
//	        try {
//	            pCapacity = Integer.parseInt(sc.nextLine());
//	            if (pCapacity > 0) break;
//	            System.out.println("상품 수량은 1 이상의 값을 입력하세요.");
//	        } catch (NumberFormatException e) {
//	            System.out.println("정확한 숫자를 입력하세요.");
//	        }
//	    }

	    System.out.print("여행 가는 국가 입력하기 >> ");
	    String national = sc.nextLine();

	    System.out.print("여행 상품 가격 입력하기 >> ");
	    int price = 0;
	    while (true) {
	        try {
	            price = Integer.parseInt(sc.nextLine());
	            if (price > 0) break;
	            System.out.println("상품 가격은 1 이상의 값을 입력하세요.");
	        } catch (NumberFormatException e) {
	            System.out.println("정확한 숫자를 입력하세요.");
	        }
	    }

	    // 날짜 입력 처리
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    formatter.setLenient(false);

	    Date sDate = null;
	    System.out.print("여행 시작 날짜 입력하기 (형식: yyyy-MM-dd) >> ");
	    while (sDate == null) {
	        try {
	            String startDateStr = sc.nextLine().trim();
	            java.util.Date utilDate = formatter.parse(startDateStr);
	            sDate = new java.sql.Date(utilDate.getTime());
	        } catch (ParseException e) {
	            System.out.println("잘못된 날짜 형식입니다. yyyy-MM-dd 형식으로 다시 입력해주세요.");
	        }
	    }

	    Date eDate = null;
	    System.out.print("여행 종료 날짜 입력하기 (형식: yyyy-MM-dd) >> ");
	    while (eDate == null) {
	        try {
	            String endDateStr = sc.nextLine().trim();
	            java.util.Date utilDate = formatter.parse(endDateStr);
	            eDate = new java.sql.Date(utilDate.getTime());
	        } catch (ParseException e) {
	            System.out.println("잘못된 날짜 형식입니다. yyyy-MM-dd 형식으로 다시 입력해주세요.");
	        }
	    }

	    // PackageVO 생성
//	    PackageVO pvo = new PackageVO(id, name, pCapacity, national, price, guideId, sDate, eDate);
	    PackageVO pvo = new PackageVO(id, name, national, price, guideId, sDate, eDate);

	    // 데이터 삽입
	    boolean successFlag = pdao.packageInsert(pvo);

	    if (successFlag) {
	        System.out.println("여행 상품 정보가 성공적으로 입력되었습니다.");

	        // 삽입된 데이터 확인
	        try {
	            Connection con = DBUtility.dbCon();
//	            PreparedStatement pstmt = con.prepareStatement(
//	                "SELECT NO, ID, NAME, PCAPACITY, NATIONAL, PRICE, GUIDE_ID, SDATE, EDATE FROM PACKAGE WHERE GUIDE_ID = ?"
//	            );
	            PreparedStatement pstmt = con.prepareStatement(
		                "SELECT NO, ID, NAME, NATIONAL, PRICE, GUIDE_ID, SDATE, EDATE FROM PACKAGE WHERE GUIDE_ID = ?"
		            );

	            pstmt.setString(1, guideId);

	            ResultSet rs = pstmt.executeQuery();

//	            while (rs.next()) {
//	                int no = rs.getInt("NO");
//	                System.out.println("삽입된 상품 번호: " + no);
//	            }

	            DBUtility.dbClose(con, pstmt, rs);
	        } catch (SQLException e) {
	            System.out.println("삽입된 상품 정보 확인 중 예외: " + e.getMessage());
	        }
	    } else {
	        System.out.println("여행 상품 정보 입력에 실패하였습니다.");
	    }
	}


// update	
	public void updateManager() {
	    PackageDAO pdao = new PackageDAO();
	    ArrayList<PackageVO> packageList = pdao.packageSelect();
	    if (packageList == null || packageList.isEmpty()) {
	        System.out.println("데이터가 존재하지 않습니다.");
	        return;
	    }
	    printPackageList(packageList);

	    System.out.print("수정할 패키지의 패키지 no를 입력하세요 >> ");
	    int no = Integer.parseInt(sc.nextLine());

	    boolean noExists = packageList.stream().anyMatch(pvo -> pvo.getNo() == no);
	    if (!noExists) {
	        System.out.println("입력한 패키지 번호가 존재하지 않습니다. 다시 시도해주세요");
	        return;
	    }

	    System.out.print("수정할 여행상품 이름을 입력해주세요 >> ");
	    String name = sc.nextLine();

	    System.out.print("수정할 여행상품 국가를 입력해주세요 >> ");
	    String national = sc.nextLine();

	    System.out.print("수정할 여행상품 가격을 입력해주세요 >> ");
	    int price = Integer.parseInt(sc.nextLine());

	    GuideDAO gdao = new GuideDAO();
	    GuideVO gvo = new GuideVO();
	    ArrayList<GuideVO> guideList = gdao.guideSelect(gvo);
	    if (guideList == null || guideList.isEmpty()) {
	        System.out.println("가이드 데이터가 없습니다.");
	        return;
	    }
	    printGuideList(guideList);

	    System.out.print("수정할 여행상품의 가이드ID를 입력해주세요 >> ");
	    String guideId = sc.nextLine();

	    boolean guideExists = guideList.stream().anyMatch(g -> g.getId().equals(guideId));
	    if (!guideExists) {
	        System.out.println("입력한 가이드가 존재하지 않습니다. 다시 시도해주세요");
	        return;
	    }

	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    formatter.setLenient(false);

	    Date sDate = null;
	    System.out.print("여행 시작 날짜 입력하기 (형식: yyyy-MM-dd) >> ");
	    while (sDate == null) {
	        try {
	            String startDateStr = sc.nextLine().trim();
	            java.util.Date utilDate = formatter.parse(startDateStr);
	            sDate = new java.sql.Date(utilDate.getTime());
	        } catch (ParseException e) {
	            System.out.println("잘못된 날짜 형식입니다. yyyy-MM-dd 형식으로 다시 입력해주세요.");
	        }
	    }

	    Date eDate = null;
	    System.out.print("여행 종료 날짜 입력하기 (형식: yyyy-MM-dd) >> ");
	    while (eDate == null) {
	        try {
	            String endDateStr = sc.nextLine().trim();
	            java.util.Date utilDate = formatter.parse(endDateStr);
	            eDate = new java.sql.Date(utilDate.getTime());
	        } catch (ParseException e) {
	            System.out.println("잘못된 날짜 형식입니다. yyyy-MM-dd 형식으로 다시 입력해주세요.");
	        }
	    }

	    PackageVO pvo = new PackageVO();
	    pvo.setNo(no);
	    pvo.setName(name);
	    pvo.setNational(national);
	    pvo.setPrice(price);
	    pvo.setGuideId(guideId);
	    pvo.setSDate(sDate);
	    pvo.setEDate(eDate);

	    boolean successFlag = PackageDAO.packageUpdate(pvo);

	    if (successFlag) {
	        System.out.println("수정처리 성공");
	        try {
	            Connection con = DBUtility.dbCon();
	            PreparedStatement pstmt = con.prepareStatement(
	                "SELECT NO, NAME, NATIONAL, PRICE, GUIDE_ID, SDATE, EDATE FROM PACKAGE WHERE NO = ?"
	            );
	            pstmt.setInt(1, no);

	            ResultSet rs = pstmt.executeQuery();

//	            while (rs.next()) {
//	                System.out.printf(
//	                    "수정된 여행상품 번호: %d, 이름: %s, 국가: %s, 가격: %d, 가이드: %s, 출발날짜: %s, 도착날짜: %s\n",
//	                    rs.getInt("NO"), rs.getString("NAME"), rs.getString("NATIONAL"),
//	                    rs.getInt("PRICE"), rs.getString("GUIDE_ID"), rs.getDate("SDATE"), rs.getDate("EDATE")
//	                );
//	            }

	            DBUtility.dbClose(con, pstmt, rs);
	        } catch (SQLException e) {
	            System.out.println("데이터 확인 중 예외 발생: " + e.getMessage());
	        }
	    } else {
	        System.out.println("수정처리 실패");
	    }
	}



	
	// delete
	public void deleteManager() {
//		PackageDAO pdao = new PackageDAO();
//		ArrayList<PackageVO> packageList = new ArrayList<PackageVO>();
//		printPackageList(packageList);
	    PackageDAO pdao = new PackageDAO();
	    ArrayList<PackageVO> packageList = pdao.packageSelect();
	    if (packageList == null || packageList.isEmpty()) {
	        System.out.println("데이터가 존재하지 않습니다.");
	        return;
	    }
	    printPackageList(packageList);
		
		System.out.print("삭제할 상품의 상품NO 를 입력하세요 >> ");
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
	    System.out.println();
	    // 헤더 출력
	    System.out.printf(
    	        "%-8s %-12s %-13s %-10s %-10s %-12s %-12s %-15s\n",
	        "여행상품NO", "여행상품ID", "여행상품명", "여행할 국가", "가격", "가이드ID", "출국일", "입국일"
	    );
	    System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");

	    // 데이터 출력 (toString 사용)
	    for (PackageVO data : packageList) {
	        System.out.printf(
	    	        "%-8s %-13s %-13s %-10s %-10s %-13s %-13s %-15s\n",
	            data.getNo(), data.getId(), data.getName(), data.getNational(),
	            data.getPrice(), data.getGuideId(), data.getSDate(), data.getEDate()
	        );
	    }
	    System.out.println();
	}


	// 전체 가이드 리스트
	public void printGuideList(ArrayList<GuideVO> guideList) {
	    System.out.println();
	    // 헤더 출력
	    System.out.printf(
	    	    "%-6s %-10s %-8s %-15s %-10s\n",
	        "가이드No", "가이드ID", "이름", "전화번호", "가능언어"
	    );
	    System.out.println("----------------------------------------------------------------------------");

	    // 데이터 출력 (toString 사용)
	    for (GuideVO data : guideList) {
	        System.out.println(data.toString());
	    }
	    System.out.println();
	}

	
	
	// 랜덤 ID 생성 메서드
	private String generateRandomId() {
	    Random random = new Random();
	    char letter = (char) (random.nextInt(26) + 'a'); // 소문자 1자리
	    int number = random.nextInt(10000000, 99999999); // 7자리 숫자
	    return letter + String.valueOf(number);
	}
}
