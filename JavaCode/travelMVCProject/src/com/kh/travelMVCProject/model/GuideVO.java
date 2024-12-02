package com.kh.travelMVCProject.model;

public class GuideVO {
	private int no; // --PK
	private String id; // 가이드ID
	private String name; // 이름
	private String phone; // 전화번호
	private String languages; // 언어

	public GuideVO() {
	}
//	id, name, phone, languages
	
	public GuideVO(String id, String name, String phone, String languages) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.languages = languages;
	}
	public GuideVO(int no, String id, String name, String phone, String languages) {
		super();
		this.no = no;
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.languages = languages;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLanguages() {
		return languages;
	}

	public void setLanguages(String languages) {
		this.languages = languages;
	}

	@Override
	public String toString() {
	    return String.format(
	    	    "%-6s %-11s %-8s %-15s %-10s",
	        no, id, name, phone, languages
	    );
	}

}