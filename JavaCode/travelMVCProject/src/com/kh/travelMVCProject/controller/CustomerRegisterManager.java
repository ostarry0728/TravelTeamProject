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
		System.out.println("고객 정보 입력");
		String id = makeId();
		System.out.print("고객의 이름을 입력해주세요 >> ");
		String name = (sc.nextLine()).trim();
		System.out.print("고객의 생년월일을 입력해주세요 (형식:20011217) >> ");
		int birth = Integer.parseInt((sc.nextLine()).trim());
		System.out.print("고객의 국적을 입력해주세요 >> ");
		String national = (sc.nextLine()).trim();
		System.out.print("고객의 성별을 입력해주세요 >> ");
		String gender = (sc.nextLine()).trim();
		System.out.print("고객의 이메일을 입력해주세요 (형식:xxxxx@xxxx.com) >> ");
		String email = (sc.nextLine()).trim();
		System.out.print("고객의 전화번호를 입력해주세요 (형식:010-xxxx-xxxx) >> ");
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
			System.out.println("출력할 고객 데이터가 없습니다.");
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
			System.out.println("출력할 고객 데이터가 없습니다.");
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
			System.out.println(no + "번의 고객 정보를 삭제 완료하였습니다.");
		} else {
			System.out.println(no + "번의 고객 정보를 삭제 실패하였습니다.");
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
		// 화면으로부터 입력받는다
		System.out.print("수정할 고객 번호를(no) 선택해주세요 >> ");
		int no = Integer.parseInt(sc.nextLine());
		
		System.out.print("수정한 고객의 이름을 입력해주세요 >> ");
		String name = (sc.nextLine()).trim();
		System.out.print("수정한 고객의 생년월일을 입력해주세요 (형식:20011217) >> ");
		int birth = Integer.parseInt((sc.nextLine()).trim());
		System.out.print("수정한 고객의 국적을 입력해주세요 >> ");
		String national = (sc.nextLine()).trim();
		System.out.print("수정한 고객의 성별을 입력해주세요 >> ");
		String gender = (sc.nextLine()).trim();
		System.out.print("수정한 고객의 이메일을 입력해주세요 (형식:xxxxx@xxxx.com) >> ");
		String email = (sc.nextLine()).trim();
		System.out.print("수정한 고객의 전화번호을 입력해주세요 (형식:010-xxxx-xxxx) >> ");
		String phone = (sc.nextLine()).trim();

		cvo = new CustomerVO(no, phone, name, birth, national, gender, email, phone);
		boolean successFlag = cdao.customerUpdate(cvo);

		// 화면 출력
		if (successFlag == true) {
			System.out.println(no + "번의 고객 정보를 수정 완료하였습니다.");
		} else {
			System.out.println(no + "번의 고객 정보를 수정 실패하였습니다.");
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
			System.out.println("출력할 고객 데이터가 없습니다.");
		}
	}

	private void printCustomerList(ArrayList<CustomerVO> customerList) {
	    // 헤더 출력
	    System.out.printf(
		        "%-7s %-10s %-9s %-10s %-7s %-7s %-22s %-12s\n",
	        "고객No", "고객ID", "이름", "생년월일", "국적", "성별", "이메일", "전화번호"
	    );
	    System.out.println("-------------------------------------------------------------------------------------------------------------------------");

	    // 데이터 출력
	    for (CustomerVO data : customerList) {
	        System.out.printf(
			        "%-7s %-11s %-8s %-11s %-7s %-7s %-23s %-12s\n",
	            data.getNo(), data.getId(), data.getName(), data.getBirth(),
	            data.getNational(), data.getGender(), data.getEmail(), data.getPhone()
	        );
	    }
	    System.out.println();
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
