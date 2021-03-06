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
`name` = '자유';

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

#게시물 개수 늘리기
/*INSERT INTO article
(
    regDate, updateDate, memberId, boardId, title, `body`
)
select now(), now(), floor(rand()*2)+1, floor(rand()*2)+1, concat('제목_',rand()), concat('내용_',rand())
from article;

select count(*) from article;
*/

#게시물 테이블 조회수 칼럼을 추가
ALTER TABLE article
ADD COLUMN hitCount INT(10) UNSIGNED NOT NULL DEFAULT 0;


#like 테이블
CREATE TABLE reactionPoint (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    memberId INT(10) UNSIGNED NOT NULL,
    relTypeCode CHAR(30) NOT NULL COMMENT '관련 데이터 타입코드',
    relId INT(10) UNSIGNED NOT NULL COMMENT '관련 데이터 번호',
    `point` SMALLINT(2) NOT NULL
);

#like 테스트 데이터
#1번 회원이 1번 아티클에 대해서 싫어요(-1)을 했다.
INSERT INTO reactionPoint  
SET regDate = NOW(),
    updateDate = NOW(),
    memberId = 1,
    relTypeCode = 'article',
    relId = 1,
    `point` = -1;
    
#like 테스트 데이터
#1번 회원이 2번 아티클에 대해서 좋아요를 했다.
INSERT INTO reactionPoint 
SET regDate = NOW(),
    updateDate = NOW(),
    memberId = 1,
    relTypeCode = 'article',
    relId = 2,
    `point` = 1;
    
#like 테스트 데이터
#2번 회원이 1번 아티클에 대해서 싫어요(-1)을 했다.
INSERT INTO reactionPoint 
SET regDate = NOW(),
    updateDate = NOW(),
    memberId = 2,
    relTypeCode = 'article',
    relId = 1,
    `point` = -1;
#like 테스트 데이터
#2번 회원이 2번 아티클에 대해서 좋아요를 했다.
INSERT INTO reactionPoint 
SET regDate = NOW(),
    updateDate = NOW(),
    memberId = 2,
    relTypeCode = 'article',
    relId = 2,
    `point` = 1;
    
#like 테스트 데이터
#3번 회원이 1번 아티클에 대해서 좋아요를 했다.
INSERT INTO reactionPoint 
SET regDate = NOW(),
    updateDate = NOW(),
    memberId = 3,
    relTypeCode = 'article',
    relId = 1,
    `point` = 1;
#게시물 테이블에 goodReac 칼럼 추가
ALTER TABLE article
ADD COLUMN goodReactionPoint INT(10) UNSIGNED NOT NULL DEFAULT 0;

#게시물 테이블에 badReac 칼럼 추가
ALTER TABLE article
ADD COLUMN badReactionPoint INT(10) NOT NULL DEFAULT 0;
 
#각 게시물 별, 좋아요, 싫어요 총합
/*   
select RP.relId,
       sum(if(RP.point > 0, RP.point, 0)) AS goodReactionPoint,
       sum(if(RP.point < 0, RP.point * -1, 0)) AS badReactionPoint
from reactionPoint AS RP
where relTypeCode= 'article'
group by RP.relTypeCode, RP.relId
*/

#기존 게시물 goodR, badR 필드에 값 채우기
UPDATE article AS A
INNER JOIN (
    SELECT RP.relId,
       SUM(IF(RP.point > 0, RP.point, 0)) AS goodReactionPoint,
       SUM(IF(RP.point < 0, RP.point * -1, 0)) AS badReactionPoint
    FROM reactionPoint AS RP
    WHERE relTypeCode= 'article'
    GROUP BY RP.relTypeCode, RP.relId
) AS RP_SUM
ON A.id = RP_SUM.relId
SET A.goodReactionPoint = RP_SUM.goodReactionPoint,
    A.badReactionPoint = RP_SUM.badReactionPoint;
    
#댓글 테이블 추가
CREATE TABLE reply (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    memberId INT(10) UNSIGNED NOT NULL,
    relTypeCode CHAR(30) NOT NULL COMMENT '관련 데이터타입 코드',
    relId INT(10) UNSIGNED NOT NULL COMMENT '관련 데이터 번호',
    `body` TEXT NOT NULL
);

INSERT INTO reply 
SET regDate = NOW(),
    updateDate = NOW(),
    memberId = 1,
    relTypeCode = 'article',
    relId = 1,
    `body` = '첫 번째 댓글 테스트입니다.';
    
INSERT INTO reply 
SET regDate = NOW(),
    updateDate = NOW(),
    memberId = 1,
    relTypeCode = 'article',
    relId = 1,
    `body` = '두 번째 댓글 테스트입니다.';
    
INSERT INTO reply 
SET regDate = NOW(),
    updateDate = NOW(),
    memberId = 2,
    relTypeCode = 'article',
    relId = 1,
    `body` = '세 번째 댓글 테스트입니다.';

#댓글에 좋아요 수, 싫어요 수 칼럼 추가
ALTER TABLE reply
ADD COLUMN goodReactionPoint INT(10) UNSIGNED NOT NULL DEFAULT 0;
    
ALTER TABLE reply
ADD COLUMN badReactionPoint INT(10) UNSIGNED NOT NULL DEFAULT 0;


#고속 검색을 위해서 인덱스 걸기
ALTER TABLE `reply` ADD KEY (`relTypeCode` , `relId`); 
#select from reply where relTypeCode ='article' and relId =5

# 부가정보테이블
# 댓글 테이블 추가
CREATE TABLE attr (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    `relTypeCode` CHAR(20) NOT NULL,
    `relId` INT(10) UNSIGNED NOT NULL,
    `typeCode` CHAR(30) NOT NULL,
    `type2Code` CHAR(70) NOT NULL,
    `value` TEXT NOT NULL
);

# attr 유니크 인덱스 걸기
## 중복변수 생성금지
## 변수찾는 속도 최적화
ALTER TABLE `attr` ADD UNIQUE INDEX (`relTypeCode`, `relId`, `typeCode`, `type2Code`);

## 특정 조건을 만족하는 회원 또는 게시물(기타 데이터)를 빠르게 찾기 위해서
ALTER TABLE `attr` ADD INDEX (`relTypeCode`, `typeCode`, `type2Code`);

# attr에 만료날짜 추가
ALTER TABLE `attr` ADD COLUMN `expireDate` DATETIME NULL AFTER `value`;