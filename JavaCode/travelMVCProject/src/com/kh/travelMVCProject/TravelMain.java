package com.kh.travelMVCProject;

import java.util.Scanner;

import com.kh.travelMVCProject.controller.CustomerRegisterManager;
import com.kh.travelMVCProject.controller.GuideRegisterManager;
import com.kh.travelMVCProject.controller.PackageRegisterManager;
import com.kh.travelMVCProject.controller.ReservationRegisterManager;
import com.kh.travelMVCProject.controller.ReviewRegisterManager;
import com.kh.travelMVCProject.view.CUSTOMER_CHOICE;
import com.kh.travelMVCProject.view.GUIDE_CHOICE;
import com.kh.travelMVCProject.view.MENU_CHOICE;
import com.kh.travelMVCProject.view.MenuViewer;
import com.kh.travelMVCProject.view.PACKAGE_CHOICE;
import com.kh.travelMVCProject.view.RESERVATION_CHOICE;
import com.kh.travelMVCProject.view.REVIEW_CHOICE;

public class TravelMain {
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		int no;
		boolean exitFlag = false;
		
		while(!exitFlag) {
			try {
				MenuViewer.mainMenuView();
				no = Integer.parseInt(sc.nextLine());
				switch(no) {
				case MENU_CHOICE.CUSTOMER:
					customerMenu();
					break;
				case MENU_CHOICE.GUIDE:
					guideMenu();
					break;
				case MENU_CHOICE.PACKAGE:
					packageMenu();
					break;
				case MENU_CHOICE.RESERVATION:
					reservationMenu();
					break;
				case MENU_CHOICE.REVIEW:
					reviewMenu();
					break;
				case MENU_CHOICE.EXIT:
					System.out.println("프로그램을 종료합니다.");
					exitFlag = true;
					break;
				default:
					System.out.println("해당 메뉴 번호만 입력하세요.");
				}
			} catch (NumberFormatException e) {
			    System.out.println("숫자 형식으로 입력해주세요.");
			} catch (Exception e) {
			    System.out.println("예기치 못한 오류가 발생했습니다: " + e.getMessage());
			}
		}
	}
	
	
	public static void customerMenu() {
		int no;
		CustomerRegisterManager crm = new CustomerRegisterManager();
		
		MenuViewer.customerMenuView();
		no = Integer.parseInt(sc.nextLine());
		switch(no) {
		case CUSTOMER_CHOICE.LIST:
			System.out.println("");
			crm.selectManager();
			break;
		case CUSTOMER_CHOICE.INSERT:
			System.out.println("");
			crm.insertManager();
			break;
		case CUSTOMER_CHOICE.UPDATE:
			System.out.println("");
			crm.updateManager();
			break;
		case CUSTOMER_CHOICE.DELETE:
			System.out.println("");
			crm.deleteManager();
			break;
		case CUSTOMER_CHOICE.SORT:
			System.out.println("");
			crm.selectSortManager();
			break;
		case CUSTOMER_CHOICE.MAIN:
			return;
		default:
			System.out.println("해당 메뉴 번호만 입력하세요.");
		}
	}
	
	
	public static void guideMenu() {
		int no;
		GuideRegisterManager grm = new GuideRegisterManager();
		
		MenuViewer.guideMenuView();
		no = Integer.parseInt(sc.nextLine());
		switch(no) {
		case GUIDE_CHOICE.LIST:
			System.out.println("");
			grm.selectManager();
			break;
		case GUIDE_CHOICE.INSERT:
			System.out.println("");
			grm.insertManager();
			break;
		case GUIDE_CHOICE.UPDATE:
			System.out.println("");
			grm.updateManager();
			break;
		case GUIDE_CHOICE.DELETE:
			System.out.println("");
			grm.deleteManager();
			break;
		case GUIDE_CHOICE.SORT:
			System.out.println("");
			grm.sortManager();
			break;
		case GUIDE_CHOICE.MAIN:
			return;
		default:
			System.out.println("해당 메뉴 번호만 입력하세요.");
		}
	}
	
	
	public static void packageMenu() {
		int no;
		PackageRegisterManager prm = new PackageRegisterManager();
		
		MenuViewer.packageMenuView();
		no = Integer.parseInt(sc.nextLine());
		switch(no) {
		case PACKAGE_CHOICE.LIST:
			System.out.println("");
			prm.selectManager();
			break;
		case PACKAGE_CHOICE.INSERT:
			System.out.println("");
			prm.insertManager();
			break;
		case PACKAGE_CHOICE.UPDATE:
			System.out.println("");
			prm.updateManager();
			break;
		case PACKAGE_CHOICE.DELETE:
			System.out.println("");
			prm.deleteManager();
			break;
		case PACKAGE_CHOICE.SORT:
			System.out.println("");
			prm.sortManager();
			break;
		case PACKAGE_CHOICE.MAIN:
			return;
		default:
			System.out.println("해당 메뉴 번호만 입력하세요.");
		}
	}
	
	
	public static void reservationMenu() {
		int no;
		ReservationRegisterManager rrm = new ReservationRegisterManager();
		
		MenuViewer.reservationMenuView();
		no = Integer.parseInt(sc.nextLine());
		switch(no) {
		case RESERVATION_CHOICE.LIST:
			System.out.println("");
			rrm.selectManager();
			break;
		case RESERVATION_CHOICE.INSERT:
			System.out.println("");
			rrm.insertManager();
			break;
		case RESERVATION_CHOICE.UPDATE:
			System.out.println("");
			rrm.updateManager();
			break;
		case RESERVATION_CHOICE.DELETE:
			System.out.println("");
			rrm.deleteManager();
			break;
		case RESERVATION_CHOICE.SORT:
			System.out.println("");
			rrm.selectSortManager();
			break;
		case RESERVATION_CHOICE.MAIN:
			return;
		default:
			System.out.println("해당 메뉴 번호만 입력하세요.");
		}
	}
	
	
	public static void reviewMenu() {
		int no;
		ReviewRegisterManager rrm = new ReviewRegisterManager();
		
		MenuViewer.reviewMenuView();
		no = Integer.parseInt(sc.nextLine());
		switch(no) {
		case REVIEW_CHOICE.LIST:
			System.out.println("");
			rrm.selectManager();
			break;
		case REVIEW_CHOICE.INSERT:
			System.out.println("");
			rrm.insertManager();
			break;
		case REVIEW_CHOICE.UPDATE:
			System.out.println("");
			rrm.updateManager();
			break;
		case REVIEW_CHOICE.DELETE:
			System.out.println("");
			rrm.deleteManager();
			break;
		case REVIEW_CHOICE.SORT:
			System.out.println("");
			rrm.sortManager();
			break;
		case REVIEW_CHOICE.MAIN:
			return;
		default:
			System.out.println("해당 메뉴 번호만 입력하세요.");
		}
	}
	
}

	