# 1. Project Overview (프로젝트 개요)

- 프로젝트 이름: 여행사 관리 시스템
- 프로젝트 설명: 여행사 고객, 상품, 가이드, 리뷰 등등의 데이터를 관리하는 시스템으로 설계했습니다.<br>
   시스템 내에서 데이터를 조회, 입력, 수정, 삭제(CURD) 할 수 있게 기능을 구현한 미니 프로젝트입니다.

# 2. Technology Stack (기술 스택)

## 2.1 Language

|             |                                                                                                          |
| ----------- | -------------------------------------------------------------------------------------------------------- |
| ORACLE SQL Developer 21c | <img src="https://img.shields.io/badge/oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white">   |
| JAVA JDK 21 | <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">       |
| Eclipse IDE | <img src="https://img.shields.io/badge/Eclipse-2C2255?style=for-the-badge&logo=eclipse logoColor=white"> |

## 2.2 Collaboration Tool

|        |                                                                                                               |
| ------ | ------------------------------------------------------------------------------------------------------------- |
| GITHUB | <img src="https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white"> |
<br/>
<br/>

# 3. 코드 주요 기능 및 공통 특징

- **dbCon**:

  - 데이터베이스 연결을 생성. db.properties 파일에서 접속 정보를 로드.

- **dbClose**:

  - 데이터베이스 연결과 Statement, ResultSet 등의 자원을 안전하게 해제.

- **데이터베이스 연동**:

  - 모든 데이터 액세스 클래스는 DBUtility를 통해 DB 연결을 관리.

- **사용자 입력 기반**:

  - RegisterManager 계열 클래스는 사용자 입력을 처리하여 DAO 클래스를 호출.

- **유지보수성 높은 구조**:

  - 각 기능이 모듈화되어 있어 독립적으로 테스트 및 확장 가능.

- **DB 트리거를 사용**:
  - 추가적으로 값을 입력하지 않아도 입력받은 값의 평균을 계산, 출력하는 트리거 기능 사용.

- **shuffle, random 명령어를 사용**:
  - 추가적으로 id를 입력하지 않아도 아이디를 정해진 범위내에서 랜덤으로 생성하는 기능 사용.

# 4. Project Implementation (프로젝트 구현)
<details>
<summary><b>전체 쿼리문(Customer)</b></summary>
  
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
ALTER TABLE RESERVATION MODIFY RDATE DEFAULT SYSDATE;

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

</details>


![123](https://github.com/user-attachments/assets/36cf1609-5b5b-4218-a5ea-180ff3e9985d)


![1234](https://github.com/user-attachments/assets/68d5b5e7-3ba9-451b-9536-6120469f80cc)


<br/>
<br/>

# 5. Project Structure (프로젝트 구조)

```plaintext
travleMVCProject/
├── src/
│   └── com.kh.travelMVCProject
│                             └── TravelMain          # 메인 파일
|   └── com.kh.travelMVCProject.controller
|                                        └── DBUtility 파일과 Customer, Guide, Package, Reservation, Review DAO 파일
|                                            # DBUtility : 데이터베이스 연결과 자원 관리를 담당하는 유틸리티 클래스 파일.
|                                            # DAO :정보를 관리하는 DAO 클래스 파일.
|                                        └── Customer, Guide, Package, Reservation, Review RegisterManager 파일
|                                            # RegisterManager : 비즈니스 로직과 사용자 상호작용 관련 클래스 파일.
│   └── com.kh.travelMVCProject.model
│                                   └── Customer, Guide, Package, Reservation, ReservationAll, Review VO 파일
                                        # VO : 데이터를 객체로 관리하기 위해 사용하는 클래스 파일.
|   └── com.kh.travelMVCProject.view
|                                  └── CUSTOMER, GUIDE, MENU, PACKAGE,RESERVETION, REVIEW _CHOICE 파일
|                                  └── MenuViewer 파일
|
├── db.properties            # 데이터베이스에 연결 설정 정보
├── .gitignore               # Git 무시 파일 목록
└── README.md                # 프로젝트 개요 및 사용법
```

# 6. Test view (테스트 화면)

<details>
<summary><b>고객(Customer)</b></summary>
<p>1. 입력하기</p>
  
![고객정보 1](https://github.com/user-attachments/assets/b646d878-4aa2-4229-8ca4-221f783e788a)

<p>2. 수정하기</p>

![고객정보 2](https://github.com/user-attachments/assets/f81a7c1b-6cdf-4df5-bd29-12de4461d2ac)


<p>3. 삭제하기</p>

![고객정보 3](https://github.com/user-attachments/assets/23a64674-798f-430c-b71a-50f83d697f55)


<p>4. 정렬하기</p>

![고객정보 4](https://github.com/user-attachments/assets/a82fa7ad-3e47-48b2-913d-795ff5915914)


</details>

<details>
<summary><b>가이드(Guide)</b></summary>
<p>1. 입력하기</p>

![가이드정보 1](https://github.com/user-attachments/assets/ba2ddfb8-f39c-4c6a-9854-9494bec470ff)


<p>2. 수정하기</p>

![가이드정보 2](https://github.com/user-attachments/assets/7284bfb8-06d5-49db-b7da-2e538ba131ae)


<p>3. 삭제하기</p>

![가이드정보 3](https://github.com/user-attachments/assets/878d576e-26fb-47db-87f9-508158bd27a9)
![가이드정보 3 1](https://github.com/user-attachments/assets/233b4c3f-058f-40b4-98a4-b126534736d5)

<p>4. 정렬하기</p>

![가이드정보 4](https://github.com/user-attachments/assets/e3a2beae-3a97-4ece-a13e-ee8f68f53da3)

</details>

<details>
<summary><b>여행상품(Package)</b></summary>
<p>1. 입력하기</p>

![여행상품정보 1](https://github.com/user-attachments/assets/e8767b04-bb84-4071-b0ac-0e846d9427bc)


<p>2. 수정하기</p>

![여행상품정보 2](https://github.com/user-attachments/assets/44fdab06-6c21-4162-8e9b-90b7cc779ada)


<p>3. 삭제하기</p>

![여행상품정보 3](https://github.com/user-attachments/assets/1498d29f-1df2-470d-a04a-004d27c17d85)


<p>4. 정렬하기</p>

![여행상품정보 4](https://github.com/user-attachments/assets/9cee4851-f8ca-407b-8bf4-96ab1d10b706)


</details>

<details>
<summary><b>예약(Reservation)</b></summary>
<p>1. 입력하기</p>

![예약정보 1](https://github.com/user-attachments/assets/4e75d804-7721-457c-bd67-281a01001694)


<p>2. 수정하기</p>

![예약정보 2](https://github.com/user-attachments/assets/6fbb98aa-3f0c-4400-a915-91ba59e4719b)


<p>3. 삭제하기</p>

![예약정보 3](https://github.com/user-attachments/assets/40f38a35-13dc-47db-909b-38330fa86c23)


<p>4. 정렬하기</p>

![예약정보 4](https://github.com/user-attachments/assets/d722d8e1-dbad-4436-9f0d-7baef168572c)


</details>

<details>
<summary><b>종료(Exit)</b></summary>

<p>입력하기</p>

![프로그램종료](https://github.com/user-attachments/assets/0a10b526-9541-4dd8-8bb5-7e9adebe1779)


</details>

<br>
<br>
<br>



<br/>

<br/>
<br/>
