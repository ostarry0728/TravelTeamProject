package com.kh.travelMVCProject.view;

public class MenuViewer {
	// 메인메뉴
	public static void mainMenuView() {
		System.out.println();
		System.out.println("여행사 예약관리 프로그램");
		System.out.println("해당 번호를 입력하세요.");
		System.out.println("1. 가이드 정보 들어가기");
		System.out.println("2. 프로그램 종료");
		System.out.print("번호 선택 : ");
	}

	// 가이드 메뉴
	public static void guideMenuView() {
		System.out.println();
		System.out.println("가이드 정보 메뉴 번호를 입력하세요.");
		System.out.println("1. 가이드 정보 목록");
		System.out.println("2. 가이드 정보 입력");
		System.out.println("3. 가이드 정보 수정");
		System.out.println("4. 가이드 정보 삭제");
		System.out.println("5. 가이드 정보 정렬");
		System.out.println("6. 메인 메뉴");
		System.out.print("번호 선택 : ");
	}
}
