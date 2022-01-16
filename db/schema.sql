# DB 생성
DROP DATABASE IF EXISTS sb_c_2022;
CREATE DATABASE sb_c_2022;

USE sb_c_2022;
#게시물 테이블 생성
CREATE TABLE article (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    title CHAR(100) NOT NULL,
    `body` TEXT NOT NULL
);

#게시물, 테스트 데이터
INSERT INTO article
SET 
regDate = NOW(),
updateDate = NOW(),
title = "제목1",
`body`= "내용1";

INSERT INTO article
SET 
regDate = NOW(),
updateDate = NOW(),
title = "제목2",
`body`= "내용2";

INSERT INTO article
SET 
regDate = NOW(),
updateDate = NOW(),
title = "제목3",
`body`= "내용3";


SELECT * FROM article;

SELECT LAST_INSERT_ID();

#멤버 테이블 생성
CREATE TABLE `member` (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    loginId CHAR(100) NOT NULL,
    loginPw CHAR(100) NOT NULL,
    `name` CHAR(20) NOT NULL,
    nickName CHAR(20) NOT NULL,
    cellphoneNo CHAR(30) NOT NULL,
    email CHAR(20) NOT NULL
);

#게시물, 테스트 데이터
INSERT INTO MEMBER
SET 
regDate = NOW(),
updateDate = NOW(),
loginId = 'user1',
loginPw = '1234',
`name` = '사용자1',
nickName = '용사1',
cellphoneNo = '1234-1234',
email = 'go@naver.com';

INSERT INTO MEMBER
SET 
regDate = NOW(),
updateDate = NOW(),
loginId = 'user2',
loginPw = '1234',
`name` = '사용자2',
nickName = '용사2',
cellphoneNo = '1234-1234',
email = 'go@naver.com';

INSERT INTO MEMBER
SET 
regDate = NOW(),
updateDate = NOW(),
loginId = 'user3',
loginPw = '1234',
`name` = '사용자3',
nickName = '용사3',
cellphoneNo = '1234-1234',
email = 'go@naver.com';

#게시물 테이블에 게시한 회원정보 추가하기
ALTER TABLE article ADD COLUMN memberId INT(10) UNSIGNED NOT NULL AFTER updateDate; 

#기존 게시물에 memberId 넣어주기
UPDATE article
SET memberId = 1
WHERE memberId = 0;

# 게시판 테이블 생성
CREATE TABLE board (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    `code` CHAR(50) NOT NULL UNIQUE COMMENT 'notice(공지사항),free1(자유게시판1),free2(자유게시판2),...',
    `name` CHAR(50) NOT NULL UNIQUE COMMENT '게시판 이름',
    delStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '삭제여부(0=탈퇴전,1=탈퇴)',
    delDate DATETIME COMMENT '삭제날짜'
);

# 기본 게시판 생성
INSERT INTO board
SET regDate = NOW(),
updateDate = NOW(),
`code` = 'notice',
`name` = '공지사항';

INSERT INTO board
SET regDate = NOW(),
updateDate = NOW(),
`code` = 'free1',
`name` = '자유게시판';

# 게시판 테이블에 boardId 칼럼 추가
ALTER TABLE article ADD COLUMN boardId INT(10) UNSIGNED NOT NULL AFTER memberId;

# 1, 2번 게시물을 공지사항 게시물로 지정
UPDATE article
SET boardId = 1
WHERE id IN (1, 2);

# 3번 게시물을 자유게시판 게시물로 지정
UPDATE article
SET boardId = 2
WHERE id IN (3);



