package com.kh.travelMVCProject.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.travelMVCProject.model.ReservationsAllVO;
import com.kh.travelMVCProject.model.ReservationsVO;

public class ReservationsRegisterManager {
	public Scanner sc = new Scanner(System.in);

	// 예약 등록 (Insert)
	public void insertManager() {
		ReservationsDAO rdao = new ReservationsDAO();

		// 화면으로부터 입력받는다.
		System.out.print("예약 ID 입력>> ");
		String id = (sc.nextLine()).trim();

		System.out.print("고객 ID 입력>> ");
		String custID = (sc.nextLine()).trim();

		System.out.print("상품 ID 입력>> ");
		String packID = (sc.nextLine()).trim();

		System.out.print("예약 인원 입력>> ");
		int rCapacity = Integer.parseInt((sc.nextLine()).trim());

		System.out.print("결제 방식 입력(CARD, CASH 등)>> ");
		String method = (sc.nextLine()).trim();

		// ID 포함 ReservationsVO 객체 생성
		ReservationsVO rvo = new ReservationsVO(0, id, custID, packID, rCapacity, method, null);
		boolean successFlag = rdao.reservationInsert(rvo);

		// 화면 출력
		if (successFlag) {
			System.out.println("예약 등록이 완료되었습니다.");
		} else {
			System.out.println("예약 등록에 실패했습니다.");
		}
	}

	// 예약 목록 조회 (Select)
	public void selectManager() {
		ReservationsDAO rdao = new ReservationsDAO();

		// 데이터베이스 요청
		ArrayList<ReservationsVO> reservationList = rdao.reservationSelect();

		// 화면 출력
		if (reservationList.size() != 0) {
			printReservationList(reservationList);
		} else {
			System.out.println("출력할 데이터가 없습니다.");
		}
	}

	// join포함 예약 목록 조회 (Select)
	public void selectAllManager() {
		ReservationsDAO rdao = new ReservationsDAO();
		ArrayList<ReservationsAllVO> reservationAllList = new ArrayList<ReservationsAllVO>();

		reservationAllList = rdao.reservationAllSelect();
		if (reservationAllList == null) {
			System.out.println("데이터가 존재하지 않습니다.");
			return;
		}
		printReservationAllList(reservationAllList);
	}

	// 예약 삭제 (Delete)
	public void deleteManager() {
		ReservationsDAO rdao = new ReservationsDAO();

		// 전체 리스트를 보여준다.
		ArrayList<ReservationsVO> reservationList = rdao.reservationSelect();
		if (reservationList.size() != 0) {
			printReservationList(reservationList);
		} else {
			System.out.println("출력할 데이터가 없습니다.");
			return;
		}

		// 화면으로부터 입력받는다.
		System.out.print("삭제할 예약 번호 입력>> ");
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
		ReservationsDAO rdao = new ReservationsDAO();

		// 수정할 전체 리스트 출력
		ArrayList<ReservationsVO> reservationList = rdao.reservationSelect();
		if (reservationList.size() != 0) {
			printReservationList(reservationList);
		} else {
			System.out.println("출력할 데이터가 없습니다.");
			return;
		}

		// 화면으로부터 입력받는다.
		System.out.print("수정할 예약 번호 입력>> ");
		int no = Integer.parseInt(sc.nextLine());

		System.out.print("수정할 예약 ID 입력>> ");
		String id = (sc.nextLine()).trim(); // ID 추가

		System.out.print("수정할 고객 ID 입력>> ");
		String custID = (sc.nextLine()).trim();

		System.out.print("수정할 상품 ID 입력>> ");
		String packID = (sc.nextLine()).trim();

		System.out.print("수정할 예약 인원 입력>> ");
		int rCapacity = Integer.parseInt((sc.nextLine()).trim());

		System.out.print("수정할 결제 방식 입력(CARD, CASH 등)>> ");
		String method = (sc.nextLine()).trim();

		// ID 포함 ReservationsVO 객체 생성
		ReservationsVO rvo = new ReservationsVO(no, id, custID, packID, rCapacity, method, null);
		boolean successFlag = rdao.reservationUpdate(rvo);

		// 화면 출력
		if (successFlag) {
			System.out.println(no + "번 예약이 수정되었습니다.");
		} else {
			System.out.println(no + "번 예약 수정에 실패했습니다.");
		}
	}

	// 예약 정렬 조회 (Select Sorted)
	public void selectSortManager() {
		ReservationsDAO rdao = new ReservationsDAO();

		// 데이터베이스 요청
		ArrayList<ReservationsVO> reservationList = rdao.reservationSelectSort();

		// 화면 출력
		if (reservationList.size() != 0) {
			printReservationList(reservationList);
		} else {
			System.out.println("출력할 데이터가 없습니다.");
		}
	}

	// 화면 출력
	public void printReservationAllList(ArrayList<ReservationsAllVO> reservationAllList) {
		System.out.println("번호 | ID | 고객 이름 | 고객 이메일 | 상품 이름 | 상품 국가 | 인원 | 결제방식 | 날짜");
		for (ReservationsAllVO data : reservationAllList) {
			System.out.println(data);
		}
	}

	// 화면 출력
	public void printReservationList(ArrayList<ReservationsVO> reservationList) {
		System.out.println("번호 | ID | 고객 ID | 상품 ID | 인원 | 결제방식 | 날짜");
		for (ReservationsVO data : reservationList) {
			System.out.println(data);
		}
	}
}
