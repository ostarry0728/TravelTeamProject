package com.kh.travelMVCProject.model;

import java.sql.Date;

public class ReservationVO {
private int no; // -- pk seq
private String ID; // --UK
private String custID; // --FK
private String packID; // --FK
private int rCapacity;
private String method;
private Date rDate;

public ReservationVO() {
super();
}

public ReservationVO(int no, String iD, String custID, String packID, int rCapacity, String method, Date rDate) {
super();
this.no = no;
ID = iD;
this.custID = custID;
this.packID = packID;
this.rCapacity = rCapacity;
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

public void setID(String iD) {
ID = iD;
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

public int getrCapacity() {
return rCapacity;
}

public void setrCapacity(int rCapacity) {
this.rCapacity = rCapacity;
}

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

@Override
public String toString() {
return "[no=" + no + ", ID=" + ID + ", custID=" + custID + ", packID=" + packID + ", rCapacity=" + rCapacity
+ ", method=" + method + ", rDate=" + rDate + "]";
}

}