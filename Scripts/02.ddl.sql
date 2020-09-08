DROP TABLE MEMBER;
-- MEMBER TABLE 생성
CREATE TABLE member(
	name varchar2(20),
	userid varchar2(10),
	pwd varchar2(10),
	email varchar2(20),
	phone char(13),
	admin number(1) DEFAULT 0,
	joinDate DATE DEFAULT sysdate,
	PRIMARY KEY (userid)
);


SELECT *
  FROM USER_TABLES
 WHERE TABLE_NAME = 'MEMBER';
 
-- 접속한 계정의 테이블별 컬럼 조회
SELECT *
  FROM USER_TAB_COLUMNS 
 WHERE table_name IN ('MEMBER');
 
-- 접속한 계정의 테이블별 제약조건 검색
SELECT *
  FROM USER_CONSTRAINTS 
 WHERE table_name IN ('MEMBER');


-- title 
CREATE TABLE title(
	TITLE_NO NUMBER PRIMARY KEY,
	TITLE_NAME VARCHAR2(40)
);













