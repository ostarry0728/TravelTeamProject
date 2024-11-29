package com.kh.travelMVCProject;

import java.util.Scanner;

import com.kh.travel.controller.CustomersRegisterManager;
import com.kh.travel.view.CUSTOMER_CHOICE;
import com.kh.travel.view.MENU_CHOICE;
import com.kh.travel.view.MenuViewer;

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
            case MENU_CHOICE.CUSTOMER:
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
      CustomersRegisterManager crm = new CustomersRegisterManager();

      MenuViewer.customerMenuView();
      no = Integer.parseInt(sc.nextLine());
      switch (no) {
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

}
