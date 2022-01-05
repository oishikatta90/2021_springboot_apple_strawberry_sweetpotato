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

SELECT * FROM `member`;

