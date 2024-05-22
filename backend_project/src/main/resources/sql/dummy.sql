-- 외래키 제약 조건을 다시 활성화
SET FOREIGN_KEY_CHECKS = 1;

-- 새로운 데이터 삽입
INSERT INTO `USERS` (`id`, `password`, `email`, `name`, `nickname`, `age`, `phone_number`, `address`)
VALUES ('prao', 'pass', 'prao@gmail.com', '박창호', 'prao96', 27, '010-1234-5678', '부산광역시 부산진구 전포대로155'),
       ('admin', 'pass', 'admin@gmail.com', '관리자', '관리자', 25, '010-2345-6789', '대전광역시 유성구 봉명동');

-- TEAM 테이블 더미 데이터 생성
INSERT INTO `TEAM` (`name`)
VALUES ('롯데 자이언츠'),
       ('삼성 라이온즈');

-- PLAYERS 테이블 더미 데이터 생성
INSERT INTO `PLAYERS` (`team_id`, `name`, `age`, `back_number`, `position`)
VALUES (1, '황성빈', 26, 0, 'LF'),
       (1, '윤동희', 20, 91, 'CF'),
       (1, '고승민', 23, 65, '2B'),
       (1, '레이예스', 29, 29, 'RF'),
       (1, '한동희', 24, 25, 'DH'),
       (1, '나승엽', 22, 51, '1B'),
       (1, '박승욱', 31, 53, '3B'),
       (1, '유강남', 31, 27, 'C'),
       (1, '이학주', 33, 7, 'SS'),
       (1, '윌커슨', 34, 46, 'SP'),
       (1, '정보근', 24, 42, 'C'),
       (1, '손성빈', 22, 00, 'C'),
       (1, '정훈', 36, 9, '1B'),
       (1, '최항', 30, 14, '1B'),
       (1, '손호영', 29, 33, '2B'),
       (1, '노진혁', 52, 34, 'SS'),
       (1, '이주찬', 25, 63, '2B'),
       (1, '김민석', 20, 2, 'CF'),
       (1, '전준우', 38, 8, 'LF'),
       (1, '이정훈', 29, 48, '48'),

       (2, '구자욱', 26, 5, 'LF'),
       (2, '김헌곤', 20, 32, 'RF'),
       (2, '맥키넌', 23, 24, '1B'),
       (2, '김영웅', 29, 30, 'DH'),
       (2, '류지혁', 24, 16, '2B'),
       (2, '이재현', 22, 7, 'SS'),
       (2, '강민호', 31, 47, 'C'),
       (2, '전병우', 31, 34, '3B'),
       (2, '김지찬', 33, 58, 'CF'),
       (2, '레예스', 34, 43, 'SP');

-- COMMUNITY 테이블 더미 데이터 생성
INSERT INTO `COMMUNITY` (`team_id`, `name`, `description`)
VALUES (1, '롯데 자이언츠 게시판', '진정한 부산 사람들만 글 쓸 수 있음'),
       (2, '삼성 라이온즈 게시판', '그냥 그저 그런 대구 팀');

-- COMMUNITY_BOARD 테이블 더미 데이터 생성
INSERT INTO `COMMUNITY_BOARD` (`team_id`, `user_id`, `title`, `content`)
VALUES (1, 'admin', '롯데, 그들은 올라갈 힘을 비축하고 있다', '롯데는 이번 시즌 5등으로 마무리할 것이다. 한화는 까불지 마라.'),
       (2, 'admin', '삼성, 어짜피 내려올 거 얼른 내려와', '거기 어울리는 자리 아닌 거 알잖아. 사람이 몸에 맞는 옷을 입어야해. 너넨 8등이 어울려.');

select *
from community_board;

SELECT *
FROM `community_board`
WHERE `community_board_id` IN (1, 2);

-- COMMUNITY_BOARD_COMMENT 테이블 더미 데이터 생성
INSERT INTO `COMMUNITY_BOARD_COMMENT` (`community_board_id`, `user_id`, `content`)
VALUES (1, 'admin', '마 ! 붓 싼 아니가 ~'),
       (2, 'admin', '샘송은 뭐;;');
