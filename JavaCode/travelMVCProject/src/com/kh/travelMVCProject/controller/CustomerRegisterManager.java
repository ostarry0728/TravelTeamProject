package com.kh.travelMVCProject.controller;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.kh.travelMVCProject.model.CustomerVO;


public class CustomerRegisterManager {
	public Scanner sc = new Scanner(System.in);

	// 과목등록(insert)
	public void insertManager() {
		CustomerDAO cdao = new CustomerDAO();
		String id = makeId();
		System.out.print("이름 입력>>");
		String name = (sc.nextLine()).trim();
		System.out.print("생년월일 입력>>");
		int birth = Integer.parseInt((sc.nextLine()).trim());
		System.out.print("국적 입력>>");
		String national = (sc.nextLine()).trim();
		System.out.print("성별 입력>>");
		String gender = (sc.nextLine()).trim();
		System.out.print("이메일 입력 (xxxxx@xxxx.com) >>");
		String email = (sc.nextLine()).trim();
		System.out.print("전화번호 입력 (010-xxxx-xxxx) >>");
		String phone = (sc.nextLine()).trim();

		CustomerVO cvo = new CustomerVO(0, id, name, birth, national, gender, email, phone);

		boolean successFlag = cdao.customerInsert(cvo);

		// 화면 출력
		if (successFlag == true) {
			System.out.println(name + "님의 고객 정보 입력을 완료하였습니다.");
		} else {
			System.out.println(name + "님의 고객 정보 입력을 실패하였습니다.");
		}
	}

	// 고객목록(select)
	public void selectManager() {
		CustomerDAO cdao = new CustomerDAO();
		// 화면으로부터 입력받는다
		// 데이타베이스 요청
		CustomerVO cvo = new CustomerVO();
		ArrayList<CustomerVO> customerList = cdao.customerSelect(cvo);

		// 화면 출력
		if (customerList.size() != 0) { // 값이 있으면
			printCustomerList(customerList);

		} else {
			System.out.println("출력할 데이터가 없습니다.");
		}

	}

	// 고객삭제(delete)
	public void deleteManager() {
		CustomerDAO cdao = new CustomerDAO();
		CustomerVO cvo = new CustomerVO();
		// 전체 리스트를 보여준다.

		ArrayList<CustomerVO> customerList = cdao.customerSelect(cvo);
		if (customerList.size() != 0) { // 값이 있으면
			printCustomerList(customerList);
		} else {
			System.out.println("출력할 데이터가 없습니다.");
			return;
		}
		// 화면으로부터 입력 받는다.
		System.out.print("삭제할 번호>>");
		int no = Integer.parseInt(sc.nextLine().trim()); // 정수형 변환

		cvo = new CustomerVO(); // 통일시키기 위해 다 담아서 주기
		cvo.setNo(no);
		boolean successFlag = cdao.customerDelete(cvo);

		// 화면 출력
		if (successFlag == true) {
			System.out.println(no + "번호 고객 삭제 완료하였습니다.");
		} else {
			System.out.println(no + "번호 고객 삭제 실패하였습니다.");
		}
	}

	// 고객수정(update)
	public void updateManager() {
		CustomerDAO cdao = new CustomerDAO();
		CustomerVO cvo = new CustomerVO();
		// 수정하기 전체출력요청
		ArrayList<CustomerVO> customerList = cdao.customerSelect(cvo);
		if (customerList.size() != 0) {
			printCustomerList(customerList);
		} else {
			System.out.println("출력할 데이터가 없습니다");
			return;
		}
		// 화면으로붕터 입력받는다
		System.out.print("수정할 번호(no) 선택>>");
		int no = Integer.parseInt(sc.nextLine());
		
		System.out.print("이름 입력>>");
		String name = (sc.nextLine()).trim();
		System.out.print("생년월일 입력>>");
		int birth = Integer.parseInt((sc.nextLine()).trim());
		System.out.print("국적 입력>>");
		String national = (sc.nextLine()).trim();
		System.out.print("성별 입력>>");
		String gender = (sc.nextLine()).trim();
		System.out.print("이메일 입력 (xxxxx@xxxx.com) >>");
		String email = (sc.nextLine()).trim();
		System.out.print("전화번호 입력 (010-xxxx-xxxx) >>");
		String phone = (sc.nextLine()).trim();

		cvo = new CustomerVO(no, phone, name, birth, national, gender, email, phone);
		boolean successFlag = cdao.customerUpdate(cvo);

		// 화면 출력
		if (successFlag == true) {
			System.out.println(no + "님의 정보를 수정하였습니다.");
		} else {
			System.out.println(no + "님의 정보 수정을 실패하였습니다.");
		}
	}

	// 과목정렬(selectsort)
	public void selectSortManager() {
		CustomerDAO cdao = new CustomerDAO();
		// 화면으로부터 입력받는다
		// 데이타베이스 요청
		CustomerVO cvo = new CustomerVO();
		ArrayList<CustomerVO> customerList = cdao.customerSelectSort(cvo);

		// 화면 출력
		if (customerList.size() != 0) { // 값이 있으면
			printCustomerList(customerList);

		} else {
			System.out.println("출력할 데이터가 없습니다.");
		}
	}

	// 화면 출력
	private void printCustomerList(ArrayList<CustomerVO> customerList) {
		for (CustomerVO data : customerList) {
			System.out.println(data);
		}
	}

	// 랜덤ID생성
	public static String makeId() {
		List<String> id1 = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
				"q", "r", "s", "t", "u", "v", "w", "x", "y", "z");
		List<String> id2 = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
		Collections.shuffle(id1);
		Collections.shuffle(id2);
		return id1.get(0) + id2.get(0) + id2.get(1) + id2.get(2) + id2.get(3) + id2.get(4) + id2.get(5) + id2.get(6);
	}

}
