INSERT ALL
	INTO MEMBER VALUES ('이소미', 'somi', '1234', 'gmd@naver.com', '010-2362-5157', 0, sysdate)
	INTO MEMBER VALUES ('하상오', 'sang12', '1234', 'ha12@naver.com', '010-5629-8888', 1, sysdate)
	INTO MEMBER VALUES ('김윤승', 'light', '1234', 'youn104@naver.com', '010-9999-8282', 0, sysdate)
	SELECT 1 FROM DUAL;
	
SELECT * FROM MEMBER;

INSERT ALL
	INTO TITLE VALUES (1, '사장')
	INTO TITLE VALUES (2, '부장')
	INTO TITLE VALUES (3, '과장')
	INTO TITLE VALUES (4, '대리')
	INTO TITLE VALUES (5, '사원')
	SELECT 1 FROM DUAL;
	
SELECT * FROM TITLE;