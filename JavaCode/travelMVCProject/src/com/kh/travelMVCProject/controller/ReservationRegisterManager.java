package com.kh.travelMVCProject.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

import com.kh.travelMVCProject.model.CustomerVO;
import com.kh.travelMVCProject.model.PackageVO;
import com.kh.travelMVCProject.model.ReservationAllVO;
import com.kh.travelMVCProject.model.ReservationVO;

public class ReservationRegisterManager {
	public Scanner sc = new Scanner(System.in);

	
	// 예약 등록 (Insert)
	public void insertManager() {
	    ReservationDAO rdao = new ReservationDAO();
	    CustomerDAO customerDAO = new CustomerDAO();
	    PackageDAO packageDAO = new PackageDAO();

	    System.out.print("예약 ID 입력>> ");
	    String ID = (sc.nextLine()).trim();

	    ArrayList<CustomerVO> customerList = customerDAO.customerSelect(new CustomerVO());
	    if (customerList == null || customerList.isEmpty()) {
	        System.out.println("고객 데이터가 없습니다.");
	        return;
	    }
	    printCustomerList(customerList);

	    System.out.print("고객 ID 입력>> ");
	    String custID = (sc.nextLine()).trim();

	    ArrayList<PackageVO> packageList = packageDAO.packageSelect();
	    if (packageList == null || packageList.isEmpty()) {
	        System.out.println("상품 데이터가 없습니다.");
	        return;
	    }
	    printPackageList(packageList);

	    System.out.print("상품 ID 입력>> ");
	    String packID = (sc.nextLine()).trim();

	    System.out.print("결제 방식 입력(CARD, CASH 등)>> ");
	    String method = (sc.nextLine()).trim();

	    // rdate 초기화
	    Date rdate = new Date(System.currentTimeMillis());

	    ReservationVO rvo = new ReservationVO(ID, custID, packID, method, rdate);
	    boolean successFlag = rdao.reservationInsert(rvo);

	    if (successFlag) {
	        System.out.println("예약 등록이 완료되었습니다.");
	    } else {
	        System.out.println("예약 등록에 실패했습니다.");
	    }
	}



	// 예약 목록 조회 (Select)
	public void selectManager() {
		ReservationDAO rdao = new ReservationDAO();

		// 데이터베이스 요청
		ArrayList<ReservationVO> reservationList = rdao.reservationSelect();

		// 화면 출력
		if (reservationList.size() != 0) {
			printReservationList(reservationList);
		} else {
			System.out.println("출력할 데이터가 없습니다.");
		}
	}

//	// join포함 예약 목록 조회 (Select)
//	public void selectAllManager() {
//		ReservationDAO rdao = new ReservationDAO();
//		ArrayList<ReservationAllVO> reservationAllList = new ArrayList<ReservationAllVO>();
//
//		reservationAllList = rdao.reservationAllSelect();
//		if (reservationAllList == null) {
//			System.out.println("데이터가 존재하지 않습니다.");
//			return;
//		}
//		printReservationAllList(reservationAllList);
//	}

	// 예약 삭제 (Delete)
	public void deleteManager() {
		ReservationDAO rdao = new ReservationDAO();

		// 전체 리스트를 보여준다.
		ArrayList<ReservationVO> reservationList = rdao.reservationSelect();
		if (reservationList.size() != 0) {
			printReservationList(reservationList);
		} else {
			System.out.println("출력할 데이터가 없습니다.");
			return;
		}

		// 화면으로부터 입력받는다.
		System.out.print("삭제할 예약 번호(NO) 입력>> ");
		int no = Integer.parseInt((sc.nextLine()).trim());

		boolean successFlag = rdao.reservationDelete(no);

		// 화면 출력
		if (successFlag) {
			System.out.println(no + "번 예약이 삭제되었습니다.");
		} else {
			System.out.println(no + "번 예약 삭제에 실패했습니다.");
		}
	}


	// 예약 수정 (Update)
	public void updateManager() {
	    ReservationDAO rdao = new ReservationDAO();

	    ArrayList<ReservationVO> reservationList = rdao.reservationSelect();
	    if (reservationList.size() != 0) {
	        printReservationList(reservationList);
	    } else {
	        System.out.println("출력할 데이터가 없습니다.");
	        return;
	    }

	    System.out.println();
	    System.out.print("수정할 예약 NO 선택하기 >> ");
	    int no = Integer.parseInt(sc.nextLine());

	    System.out.print("수정할 예약 ID 입력 >> ");
	    String id = (sc.nextLine()).trim();

	    CustomerDAO customerDAO = new CustomerDAO();
	    CustomerVO cvo = new CustomerVO();
	    ArrayList<CustomerVO> customerList = customerDAO.customerSelect(cvo);
	    if (customerList == null || customerList.isEmpty()) {
	        System.out.println("고객 데이터가 없습니다.");
	        return;
	    }
	    printCustomerList(customerList);

	    System.out.print("수정할 고객 ID 입력 >> ");
	    String custID = (sc.nextLine()).trim();

	    PackageDAO packageDAO = new PackageDAO();
	    ArrayList<PackageVO> packageList = packageDAO.packageSelect();
	    if (packageList == null || packageList.isEmpty()) {
	        System.out.println("상품 데이터가 없습니다.");
	        return;
	    }
	    printPackageList(packageList);

	    System.out.print("수정할 상품 ID 입력>> ");
	    String packID = (sc.nextLine()).trim();

//	    System.out.print("수정할 예약 인원 입력>> ");
//	    int rCapacity = Integer.parseInt((sc.nextLine()).trim());

	    System.out.print("수정할 결제 방식 입력(CARD, CASH 등)>> ");
	    String method = (sc.nextLine()).trim();

	    // `RDATE`는 기본값으로 처리
//	    ReservationVO rvo = new ReservationVO(no, id, custID, packID, rCapacity, method, null);
	    ReservationVO rvo = new ReservationVO(no, id, custID, packID,  method, null);
	    boolean successFlag = rdao.reservationUpdate(rvo);

	    if (successFlag) {
	        System.out.println(no + "번 예약이 수정되었습니다.");
	    } else {
	        System.out.println(no + "번 예약 수정에 실패했습니다.");
	    }
	}


	// 예약 정렬 조회 (Select Sorted)
	public void selectSortManager() {
		ReservationDAO rdao = new ReservationDAO();

		// 데이터베이스 요청
		ArrayList<ReservationVO> reservationList = rdao.reservationSelectSort();

		// 화면 출력
		if (reservationList.size() != 0) {
			printReservationList(reservationList);
		} else {
			System.out.println("출력할 데이터가 없습니다.");
		}
	}

	// 화면 출력
	public void printReservationAllList(ArrayList<ReservationAllVO> reservationAllList) {
		System.out.println();
	    // 헤더 출력
	    System.out.printf(
	        "%-10s %-15s %-15s %-15s %-25s %-15s %-20s %-15s %-10s %-15s %-15s\n",
	        "번호", "예약ID", "고객ID", "고객이름", "고객이메일", "상품ID", "상품명", "상품국가", "인원", "결제방식", "날짜"
	    );
	    System.out.println("--------------------------------------------------------------------------------------------------------------------------");

	    // 데이터 출력 (toString 사용)
	    for (ReservationAllVO data : reservationAllList) {
	        System.out.println(data.toString());
	    }
	}


//	// 화면 출력
//	public void printReservationList(ArrayList<ReservationVO> reservationList) {
//		System.out.println();
//	    // 헤더 출력
//	    System.out.printf(
//	        "%-9s %-15s %-14s %-13s %-9s %-14s %-15s\n",
//	        "예약No", "예약ID", "고객ID", "패키지ID", "예약인원", "결제방식", "예약날짜"
//	    );
//	    System.out.println("---------------------------------------------------------------------------------------------");
//
//	    // 데이터 출력 (toString 사용)
//	    for (ReservationVO data : reservationList) {
//	        System.out.println(data.toString());
//	    }
//	}
	
	// 화면 출력
	public void printReservationList(ArrayList<ReservationVO> reservationList) {
		System.out.println();
	    // 헤더 출력
	    System.out.printf(
	        "%-8s %-10s %-12s %-13s %-8s %-10s\n",
	        "예약No", "예약ID", "고객ID", "패키지ID", "결제방식", "예약날짜"
	    );
	    System.out.println("---------------------------------------------------------------------------------------------");

	    // 데이터 출력 (toString 사용)
	    for (ReservationVO data : reservationList) {
	        System.out.println(data.toString());
	    }
	}

	
	// 화면 출력
	private void printCustomerList(ArrayList<CustomerVO> customerList) {
		System.out.println();
	    // 헤더 출력
	    System.out.printf(
		        "%-7s %-10s %-9s %-10s %-7s %-7s %-22s %-12s\n",
	        "고객No", "고객ID", "이름", "생년월일", "국적", "성별", "이메일", "전화번호"
	    );
	    System.out.println("-------------------------------------------------------------------------------------------------------------------------");

	    // 데이터 출력 (toString 사용)
	    for (CustomerVO data : customerList) {
	        System.out.println(data.toString());
	    }
	}
	
	// arrayList
	public void printPackageList(ArrayList<PackageVO> packageList) {
		System.out.println();
	    // 헤더 출력
	    System.out.printf(
    	        "%-8s %-12s %-13s %-10s %-10s %-12s %-12s %-15s\n",
	        "여행상품NO", "여행상품ID", "여행상품명", "여행할 국가", "가격", "가이드ID", "출국일", "입국일"
	    );
	    System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");

	    // 데이터 출력 (toString 사용)
	    for (PackageVO data : packageList) {
	        System.out.println(data.toString());
	    }
	    System.out.println();
	}
}