package com.kh.travelMVCProject.model;

public class CustomerVO {
	private int no; // pk
	private String id;
	private String name;
	private int birth;
	private String national;
	private String gender;
	private String email;
	private String phone;

	public CustomerVO() {
		super();
	}

	public CustomerVO(int no, String id, String name, int birth, String national, String gender, String email,
			String phone) {
		super();
		this.no = no;
		this.id = id;
		this.name = name;
		this.birth = birth;
		this.national = national;
		this.gender = gender;
		this.email = email;
		this.phone = phone;
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

	public int getBirth() {
		return birth;
	}

	public void setBirth(int birth) {
		this.birth = birth;
	}

	public String getNational() {
		return national;
	}

	public void setNational(String national) {
		this.national = national;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "CustomerVO [no=" + no + ", id=" + id + ", name=" + name + ", birth=" + birth + ", national=" + national
				+ ", gender=" + gender + ", email=" + email + ", phone=" + phone + "]";
	}

}
