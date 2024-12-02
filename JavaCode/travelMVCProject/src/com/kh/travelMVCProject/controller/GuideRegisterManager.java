package com.kh.travelMVCProject.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.kh.travelMVCProject.model.GuideVO;

public class GuideRegisterManager {
	public static Scanner sc = new Scanner(System.in);

	// select
	public void selectManager() {
		GuideDAO gdao = new GuideDAO();
		GuideVO gvo = new GuideVO();
		ArrayList<GuideVO> guideList = gdao.guideSelect(gvo);
		if (guideList.size() != 0) {
			printGuideList(guideList);
		} else {
			System.out.println("가이드 정보가 존재하지 않습니다.");
			return;
		}
	}

	// insert
	public void insertManager() {
		GuideDAO gdao = new GuideDAO();
		GuideVO gvo = new GuideVO();



		System.out.println();
		System.out.println("가이드 정보 입력");
		String id = makeId();
		System.out.print("가이드의 이름을 입력해주세요 >> ");
		String name = (sc.nextLine()).trim();
		System.out.print("가이드의 전화번호를 입력해주세요 (형식:010-xxxx-xxxx) >> ");
		String phone = (sc.nextLine()).trim();
		System.out.print("가이드가 사용하는 언어를 입력해주세요 :");
		String languages = (sc.nextLine());

		gvo = new GuideVO(id, name, phone, languages);
		boolean successFlag = gdao.guideInsert(gvo);

		if (successFlag == true) {
			System.out.println(name + " 가이드 정보를 등록하였습니다.");
		} else {
			System.out.println(name + " 가이드 정보를 등록 실패하였습니다.");
		}
		
//		// 전체 가이드 출력
//		ArrayList<GuideVO> guideList = gdao.guideSelect(gvo);
//		if (guideList.size() != 0) {
//			printGuideList(guideList);
//		} else {
//			System.out.println("가이드 정보가 존재하지 않습니다.");
//		}
//		// 전체 가이드 출력
//		guideList = gdao.guideSelect(gvo);
//		if (guideList.size() != 0) {
//			printGuideList(guideList);
//		} else {
//			System.out.println("가이드가 존재하지 않습니다.");
//		}
	}

	// update
	public void updateManager() {
		GuideDAO gdao = new GuideDAO();
		GuideVO gvo = new GuideVO();

		// 전체 가이드 출력
		ArrayList<GuideVO> guideList = gdao.guideSelect(gvo);
		if (guideList.size() != 0) {
			printGuideList(guideList);
		} else {
			System.out.println("가이드 정보가 존재하지 않습니다.");
			return;
		}
		// 수정할 정보
		System.out.print("수정할 가이드의 번호(NO)를 선택하세요 >> ");
		int no = Integer.parseInt((sc.nextLine()).trim());
		String id = makeId();
		System.out.print("수정할 가이드의 이름을 입력해주세요 >> ");
		String name = (sc.nextLine()).trim();
		System.out.print("수정할 전화번호를 입력해주세요 (형식:010-xxxx-xxxx) >> ");
		String phone = (sc.nextLine()).trim();
		System.out.print("수정할 사용하는 언어를 입력해주세요 :");
		String languages = (sc.nextLine()).trim();

		gvo = new GuideVO(no, id, name, phone, languages);
		boolean successFlag = gdao.guideUpdate(gvo);

		if (successFlag == true) {
			System.out.println(no + "번 가이드의 정보를 수정하였습니다.");
		} else {
			System.out.println(no + "번 가이드의 정보를 수정 실패하였습니다.");
		}
	}

	// delete
	public void deleteManager() {
		GuideDAO gdao = new GuideDAO();
		GuideVO gvo = new GuideVO();

		// 전체 가이드 출력
		ArrayList<GuideVO> guideList = gdao.guideSelect(gvo);
		if (guideList.size() != 0) {
			printGuideList(guideList);
		} else {
			System.out.println("가이드 정보가 존재하지 않습니다.");
			return;
		}

		System.out.print("삭제할 가이드 번호(NO)를 입력하세요 : ");
		int no = Integer.parseInt((sc.nextLine()).trim());

		gvo = new GuideVO();
		gvo.setNo(no);
		boolean successFlag = gdao.guideDelete(gvo);

		if (successFlag == true) {
			System.out.println(no + "번 가이드의 정보를 삭제하였습니다.");
		} else {
			System.out.println(no + "번 가이드의 정보를 삭제 실패하였습니다.");
		}
	}

	// sort
	public void sortManager() {
		GuideDAO gdao = new GuideDAO();
		GuideVO gvo = new GuideVO();
		ArrayList<GuideVO> guideList = gdao.guideSort(gvo);
		if (guideList.size() != 0) {
			printGuideList(guideList);
		} else { 
			System.out.println("가이드 정보가 존재하지 않습니다.");
			return;
		}
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


	// 랜덤ID생성
	public static String makeId() {
		List<String> id1 = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", 
										"n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z");
		List<String> id2 = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
		Collections.shuffle(id1);
		Collections.shuffle(id2);
		return id1.get(0) + id2.get(0) + id2.get(1) + id2.get(2) + id2.get(3) + id2.get(4) + id2.get(5) + id2.get(6);
	}

}