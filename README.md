# 📖 Travel MVC Project
<p>
여행 대행사 프로그램입니다. <br>
고객 정보와 가이드 정보를 입력할 수 있고, 여행 상품에 대한 정보도 입력 할 수 있습니다. <br>
이에 대한 예약도 진행이 가능하며, 예약에 대한 리뷰 작성 기능까지 포함한 미니 프로젝트입니다. <br>
입력된 정보에 대하여 수정, 삭제 기능이 포함되어 있으며, 특정 기준에 따라 정렬 기능을 추가했습니다.
</p>
<br>
<br>

## ⚙Tech Stack
<p><strong> Window <br></strong>
<br>
<img src="https://img.shields.io/badge/Windows-0078D6?style=for-the-badge&logo=windows&logoColor=white">
</p>
<p><strong> Database <br></strong>
<br>
<img src="https://img.shields.io/badge/Oracle-F80000?style=for-the-badge&logo=Oracle&logoColor=white">
</p>
<p><strong> Collaboration Tool <br></strong>
<br>
<img src="https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white"> 
</p>
<p><strong> Software <br></strong>
<br>
<img src="https://img.shields.io/badge/Eclipse-2C2255?style=for-the-badge&logo=eclipse&logoColor=white">
</p>
<br>
<br>
<br>

## 💾 Project Implementation
### Queries
```sql
-- DROP 기존 테이블 및 시퀀스
BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE REVIEW CASCADE CONSTRAINTS';
    EXECUTE IMMEDIATE 'DROP TABLE RESERVATION CASCADE CONSTRAINTS';
    EXECUTE IMMEDIATE 'DROP TABLE PACKAGE CASCADE CONSTRAINTS';
    EXECUTE IMMEDIATE 'DROP TABLE GUIDE CASCADE CONSTRAINTS';
    EXECUTE IMMEDIATE 'DROP TABLE CUSTOMER CASCADE CONSTRAINTS';
    EXECUTE IMMEDIATE 'DROP SEQUENCE CUSTOMER_SEQ';
    EXECUTE IMMEDIATE 'DROP SEQUENCE GUIDE_SEQ';
    EXECUTE IMMEDIATE 'DROP SEQUENCE PACKAGE_SEQ';
    EXECUTE IMMEDIATE 'DROP SEQUENCE RESERVATION_SEQ';
    EXECUTE IMMEDIATE 'DROP SEQUENCE REVIEW_SEQ';
EXCEPTION
    WHEN OTHERS THEN NULL;
END;
/

-- 고객 테이블
CREATE TABLE CUSTOMER (
    NO NUMBER, --PK
    ID VARCHAR2(30), --UK
    NAME VARCHAR2(50) NOT NULL,
    BIRTH NUMBER NOT NULL,
    NATIONAL VARCHAR2(20) NOT NULL,
    GENDER VARCHAR2(10) NOT NULL,
    EMAIL VARCHAR2(50) NOT NULL,
    PHONE VARCHAR2(14) NOT NULL
);
Alter table CUSTOMER add constraint CUSTOMER_NO_PK primary key(NO);
Alter table CUSTOMER add constraint CUSTOMER_ID_UK UNIQUE(ID); 

CREATE SEQUENCE CUSTOMER_SEQ
START WITH 1
INCREMENT BY 1;

-- 가이드 테이블
CREATE TABLE GUIDE (
    NO NUMBER, --PK
    ID VARCHAR2(30), --UK
    NAME VARCHAR(50) NOT NULL,
    PHONE VARCHAR(15) NOT NULL,
    LANGUAGES VARCHAR(100) NOT NULL
);
Alter table GUIDE add constraint GUIDE_NO_PK primary key(NO);
Alter table GUIDE add constraint GUIDE_ID_UK UNIQUE(ID); 

CREATE SEQUENCE GUIDE_SEQ
START WITH 1
INCREMENT BY 1;

-- 여행상품 테이블
CREATE TABLE PACKAGE (
    NO NUMBER, --PK
    ID VARCHAR2(30),--UK
    NAME VARCHAR2(50) NOT NULL,
    NATIONAL VARCHAR2(20) NOT NULL,
    PRICE NUMBER NOT NULL,
    GUIDE_ID VARCHAR2(30), --FK
    SDATE DATE DEFAULT SYSDATE,
    EDATE DATE DEFAULT SYSDATE
);
Alter table PACKAGE add constraint PACKAGE_NO_PK primary key(NO);
Alter table PACKAGE add constraint PACKAGE_ID_UK UNIQUE(ID);   
Alter table PACKAGE add constraint PACKAGE_GUIDE_ID_FK 
    FOREIGN key(GUIDE_ID) References GUIDE(ID) on delete set null;
 

CREATE SEQUENCE PACKAGE_SEQ
START WITH 1
INCREMENT BY 1;

--drop table reservation;
--drop table review;
-- 예약 테이블
CREATE TABLE RESERVATION(
    NO NUMBER, --PK
    ID VARCHAR2(30), --UK
    CUST_ID VARCHAR2(30), --FK
    PACK_ID VARCHAR2(30), --FK
    METHOD VARCHAR2(20) NOT NULL,
    RDATE DATE DEFAULT SYSDATE
);
Alter table RESERVATION add constraint RESERVATION_NO_PK primary key(NO);
Alter table RESERVATION add constraint RESERVATION_ID_UK UNIQUE(ID);  
Alter table RESERVATION add constraint RESERVATION_CUST_ID_FK 
    FOREIGN key(CUST_ID) References CUSTOMER(ID) on delete set null;
Alter table RESERVATION add constraint RESERVATION_PACK_ID_FK 
    FOREIGN key(PACK_ID) References PACKAGE(ID) on delete set null;   
       
CREATE SEQUENCE RESERVATION_SEQ
START WITH 1
INCREMENT BY 1;

-- 리뷰 테이블
CREATE TABLE REVIEW(
    NO NUMBER, --PK
    RESERV_ID VARCHAR2(30), --FK
    GUIDE_REVIEW NUMBER(2) NOT NULL,
    SCHE_REVIEW NUMBER(2) NOT NULL,
    AVG_REVIEW NUMBER(3, 1)
);
Alter table REVIEW add constraint REVIEW_NO_PK primary key(NO);
Alter table REVIEW add constraint REVIEW_RESERV_ID_FK 
    FOREIGN key(RESERV_ID) References RESERVATION(ID) on delete set null;
  
--  drop sequence review_seq;    
CREATE SEQUENCE REVIEW_SEQ
START WITH 1
INCREMENT BY 1;

COMMIT;


--=============================================================================================
-- 리뷰의 평균값 구하는 트리거
CREATE OR REPLACE TRIGGER REVIEW_TRIGGER
BEFORE INSERT OR UPDATE ON REVIEW
FOR EACH ROW
BEGIN
    IF :NEW.GUIDE_REVIEW IS NOT NULL AND :NEW.SCHE_REVIEW IS NOT NULL THEN
        :NEW.AVG_REVIEW := (:NEW.GUIDE_REVIEW + :NEW.SCHE_REVIEW) / 2;
    ELSE
        -- 기본값 설정
        :NEW.AVG_REVIEW := 0;
    END IF;
END;
/

```
![image](https://github.com/user-attachments/assets/94464c3c-9f1b-4536-87aa-843367f7854b)
<br>
<br>
<br>



### Test Case
<details>
<summary><b>👨‍👩‍👧‍👦 회원</b></summary>
	<!-- <a href="https://github.com/user-attachments/files/15525372/userTest.pdf">
		회원 Test Case 저장하기
	</a>
	<p><img alt="회원 Test Case" src="https://github.com/beyond-sw-camp/be08-1st-YesterdayWork-Bookify/assets/121468393/395622d8-4624-4cc5-a58b-fa82d5156775"></p> -->
</details>
<details>
<summary><b>🗂️ 고객 CRUD</b></summary>
//
</details>
<details>
<summary><b>📆 가이드 CRUD</b></summary>
//
</details>
<details>

<summary><b>📚 여행상품 CRUD</b></summary>
//
</details>
<details>
<summary><b>💵 예약 CRUD</b></summary>
//
</details>
<details>
<summary><b>🏠 리뷰 CRUD</b></summary>
//
</details>

<br>
<br>
<br>

## 👨‍👩‍👧‍👦 Member

이름 | 맡은 일
|-----|-----|
김연아 | Customer 테이블에 대한 CRUD
권민성 | Guide 테이블에 대한 CRUD
전지연 | Package, Review 테이블에 대한 CRUD, 전체 로직 연결
오승택 | Reservation 테이블에 대한 CRUD


<br>
