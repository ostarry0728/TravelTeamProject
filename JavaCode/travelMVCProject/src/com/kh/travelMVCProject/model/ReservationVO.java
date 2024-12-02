package com.kh.travelMVCProject.model;

import java.sql.Date;

public class ReservationVO {
	private int no; // -- pk seq
	private String ID; // --UK
	private String custID; // --FK
	private String packID; // --FK
//	private int rCapacity;
	private String method;
	private Date rDate;

	public ReservationVO() {
		super();
	}

//	public ReservationVO(int no, String iD, String custID, String packID, int rCapacity, String method, Date rDate) {
//		super();
//		this.no = no;
//		ID = iD;
//		this.custID = custID;
//		this.packID = packID;
//		this.rCapacity = rCapacity;
//		this.method = method;
//		this.rDate = rDate;
//	}
	
//	0, id, custID, packID, method, rdate
	public ReservationVO(String ID, String custID, String packID, String method, Date rDate) {
		super();
		this.ID = ID;
		this.custID = custID;
		this.packID = packID;
		this.method = method;
		this.rDate = rDate;
	}
	public ReservationVO(int no, String ID, String custID, String packID, String method, Date rDate) {
		super();
		this.no = no;
		this.ID = ID;
		this.custID = custID;
		this.packID = packID;
		this.method = method;
		this.rDate = rDate;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public String getCustID() {
		return custID;
	}

	public void setCustID(String custID) {
		this.custID = custID;
	}

	public String getPackID() {
		return packID;
	}

	public void setPackID(String packID) {
		this.packID = packID;
	}

//	public int getrCapacity() {
//		return rCapacity;
//	}
//
//	public void setrCapacity(int rCapacity) {
//		this.rCapacity = rCapacity;
//	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Date getrDate() {
		return rDate;
	}

	public void setrDate(Date rDate) {
		this.rDate = rDate;
	}

//	@Override
//	public String toString() {
//	    return String.format(
//	        "%-10d %-15s %-15s %-15s %-10d %-15s",
//	        no, ID, custID, packID, rCapacity, method, rDate
//	    );
//	}
	@Override
	public String toString() {
	    // rDate를 문자열로 변환
	    String formattedDate = (rDate != null) ? rDate.toString() : "N/A";

	    return String.format(
		        "%-8s %-10s %-13s %-13s %-8s %-10s",
	        no, ID, custID, packID, method, formattedDate
	    );
	}



}