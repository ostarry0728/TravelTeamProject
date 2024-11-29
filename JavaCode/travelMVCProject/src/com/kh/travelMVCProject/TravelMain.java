package com.kh.travelMVCProject;

import java.util.Scanner;

import com.kh.travelMVCProject.comtroller.GuideRegisterManager;
import com.kh.travelMVCProject.view.GUIDE_CHOICE;
import com.kh.travelMVCProject.view.MENU_CHOICE;
import com.kh.travelMVCProject.view.MenuViewer;

public class TravelMain {
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int no;
		boolean exitFlag = false;
		while (!exitFlag) {
			try {
				MenuViewer.mainMenuView();
				no = Integer.parseInt(sc.nextLine());
				switch (no) {
				case MENU_CHOICE.GUIDE:
					guideMenu();
					break;
				case MENU_CHOICE.EXIT:
					System.out.println("프로그램을 종료합니다.");
					exitFlag = true;
					break;
				default:
					System.out.println("해당 메뉴 번호만 입력하세요.");
				}
			} catch (Exception e) {
				System.out.println("\n입력에 오류가 있습니다.\n프로그램을 다시 시작하세요.");
			}
		}
	}

	// 가이드정보
	private static void guideMenu() {
		int no;
		GuideRegisterManager grm = new GuideRegisterManager();

		MenuViewer.guideMenuView();
		no = Integer.parseInt(sc.nextLine());
		switch (no) {
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

}
