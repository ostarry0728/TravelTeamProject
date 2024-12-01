package com.kh.travelMVCProject.model;

import java.sql.Date;

public class ReservationAllVO {
	private int no;
	private String id;
	private String custID;
	private String customerName;
	private String customerEmail;
	private String packID;
	private String packageName;
	private String packageNational;
	private int rCapacity;
	private String method;
	private Date rDate;

	public ReservationAllVO() {
		super();
	}

	public ReservationAllVO(int no, String id, String custID, String customerName, String customerEmail, String packID,
			String packageName, String packageNational, int rCapacity, String method, Date rDate) {
		super();
		this.no = no;
		this.id = id;
		this.custID = custID;
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.packID = packID;
		this.packageName = packageName;
		this.packageNational = packageNational;
		this.rCapacity = rCapacity;
		this.method = method;
		this.rDate = rDate;
	}

	@Override
	public String toString() {
		return "ReservationsAllVO [no=" + no + ", id=" + id + ", custID=" + custID + ", customerName=" + customerName
				+ ", customerEmail=" + customerEmail + ", packID=" + packID + ", packageName=" + packageName
				+ ", packageNational=" + packageNational + ", rCapacity=" + rCapacity + ", method=" + method
				+ ", rDate=" + rDate + "]";
	}

}