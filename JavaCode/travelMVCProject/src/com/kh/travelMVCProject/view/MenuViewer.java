package com.kh.travelMVCProject.view;

public class MenuViewer {

	public static void mainMenuView() {
		System.out.println();
		System.out.println("========================= KH 투어 고객 관리 프로그램 =========================");
		System.out.println("1.고객정보메뉴 2.여행상품정보메뉴 3.가이드정보메뉴 4.예약정보메뉴 5.리뷰정보메뉴 6.프로그램종료");
		System.out.println("========================================================================");
		System.out.print("번호 선택 >> ");		
	}
	
	public static void customerMenuView() {
		System.out.println();
		System.out.println("-------------------------------------- 고객 정보 메뉴 --------------------------------------");
		System.out.println("1.고객 전체출력하기  2고객정보 추가하기  3.고객정보 수정하기  4.고객정보 삭제하기  5.고객id순으로 나열하기 6.메인메뉴");
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.print("번호 선택 >> ");	
	}
	
	public static void packageMenuView() {
		System.out.println();
		System.out.println("---------------------------------------- 여행 상품 정보 메뉴 ----------------------------------------");
		System.out.println("1.여행상품 전체출력하기  2.여행상품 추가하기  3.여행상품 수정하기  4.여행상품 삭제하기  5.여행상품id순으로 나열하기  6.메인메뉴");
		System.out.println("-----------------------------------------------------------------------------------------------");
		System.out.print("번호 선택 >> ");	
	}
	
	public static void guideMenuView() {
		System.out.println();
		System.out.println("------------------------------------------ 가이드 정보 메뉴 ------------------------------------------");
		System.out.println("1.가이드 전체출력하기  2.가이드정보 추가하기  3.가이드정보 수정하기  4.가이드정보 삭제하기  5.가이드id순으로 나열하기  6.메인메뉴");
		System.out.println("-------------------------------------------------------------------------------------------------");
		System.out.print("번호 선택 >> ");	
	}
	
	public static void reservationMenuView() {
		System.out.println();
		System.out.println("--------------------------------------- 예약 정보 메뉴 ---------------------------------------");
		System.out.println("1.예약 전체출력하기  2.예약정보 추가하기  3.예약정보 수정하기  4.예약정보 삭제하기  5.예약id순으로 나열하기  6.메인메뉴");
		System.out.println("-------------------------------------------------------------------------------------------");
		System.out.print("번호 선택 >> ");	
	}
	public static void reviewMenuView() {
		System.out.println();
		System.out.println("----------------------------------- 리뷰 정보 메뉴 -----------------------------------");
		System.out.println("1.리뷰 전체출력하기  2.리뷰 추가하기  3.리뷰 수정하기  4.리뷰 삭제하기  5.리뷰id순으로 나열하기  6.메인메뉴");
		System.out.println("------------------------------------------------------------------------------------");
		System.out.print("번호 선택 >> ");	
	}
}