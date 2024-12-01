package com.kh.travelMVCProject.model;

import java.sql.Date;

//NO NUMBER, --PK
//ID VARCHAR2(30),
//NAME VARCHAR2(50) NOT NULL,
//PCAPACITY NUMBER,
//NATIONAL VARCHAR2(20) NOT NULL,
//PRICE NUMBER NOT NULL,
//GUIDE_ID VARCHAR2(30), --FK
//SDATE DATE DEFAULT SYSDATE,
//EDATE DATE DEFAULT SYSDATE

public class PackageVO {
	private int no;
	private String id;
	private String name;
//	private int pCapacity;
	private String national;
	private int price;
	private String guideId;
	private Date sDate;
	private Date eDate;
	
	public PackageVO() {
		super();
	}
	
//	// guideId, id, name, pCapacity, national, sDate, eDate
//	public PackageVO(String name, int pCapacity, String national, int price, String guideId, Date sDate, Date eDate) {
//		super();
//		this.name = name;
//		this.pCapacity = pCapacity;
//		this.national = national;
//		this.price = price;
//		this.guideId = guideId;
//		this.sDate = sDate;
//		this.eDate = eDate;
//	}
//	public PackageVO(String id, String name, int pCapacity, String national, int price, String guideId, Date sDate, Date eDate) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.pCapacity = pCapacity;
//		this.national = national;
//		this.price = price;
//		this.guideId = guideId;
//		this.sDate = sDate;
//		this.eDate = eDate;
//	}
//	public PackageVO(int no, String id, String name, int pCapacity, String national, int price, String guideId, Date sDate,
//			Date eDate) {
//		super();
//		this.no = no;
//		this.id = id;
//		this.name = name;
//		this.pCapacity = pCapacity;
//		this.national = national;
//		this.price = price;
//		this.guideId = guideId;
//		this.sDate = sDate;
//		this.eDate = eDate;
//	}
	// guideId, id, name, pCapacity, national, sDate, eDate
		public PackageVO(String name, String national, int price, String guideId, Date sDate, Date eDate) {
			super();
			this.name = name;
			this.national = national;
			this.price = price;
			this.guideId = guideId;
			this.sDate = sDate;
			this.eDate = eDate;
		}
		public PackageVO(String id, String name, String national, int price, String guideId, Date sDate, Date eDate) {
			super();
			this.id = id;
			this.name = name;
			this.national = national;
			this.price = price;
			this.guideId = guideId;
			this.sDate = sDate;
			this.eDate = eDate;
		}
		public PackageVO(int no, String id, String name, String national, int price, String guideId, Date sDate,
				Date eDate) {
			super();
			this.no = no;
			this.id = id;
			this.name = name;
			this.national = national;
			this.price = price;
			this.guideId = guideId;
			this.sDate = sDate;
			this.eDate = eDate;
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
//	public int getPCapacity() {
//		return pCapacity;
//	}
//	public void setPCapacity(int pCapacity) {
//		this.pCapacity = pCapacity;
//	}
	public String getNational() {
		return national;
	}
	public void setNational(String national) {
		this.national = national;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getGuideId() {
		return guideId;
	}
	public void setGuideId(String guideId) {
		this.guideId = guideId;
	}
	public Date getSDate() {
		return sDate;
	}
	public void setSDate(Date sDate) {
		this.sDate = sDate;
	}
	public Date getEDate() {
		return eDate;
	}
	public void setEDate(Date eDate) {
		this.eDate = eDate;
	}

//	@Override
//	public String toString() {
//	    return String.format(
//	        "%-10d %-15s %-20s %-10d %-15s %-15d %-15s %-15s %-15s",
//	        no, id, name, pCapacity, national, price, guideId, sDate, eDate
//	    );
//	}
	@Override
	public String toString() {
	    return String.format(
	        "%-10d %-15s %-15s %-15s %-10d %-15s %-15s %-15s",
	        no, id, name, national, price, guideId, sDate, eDate
	    );
	}



	
	
}
