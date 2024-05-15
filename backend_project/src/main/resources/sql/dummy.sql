-- 외래키 제약 조건을 비활성화
SET FOREIGN_KEY_CHECKS = 0;

-- 테이블의 데이터 삭제
DELETE FROM `COMMUNITY_BOARD_COMMENT`;
DELETE FROM `COMMUNITY_BOARD`;
DELETE FROM `COMMUNITY`;
DELETE FROM `PLAYERS`;
DELETE FROM `TEAM`;
DELETE FROM `USERS`;

-- AUTO_INCREMENT 값을 초기화
ALTER TABLE `USERS` AUTO_INCREMENT = 1;
ALTER TABLE `TEAM` AUTO_INCREMENT = 1;
ALTER TABLE `PLAYERS` AUTO_INCREMENT = 1;
ALTER TABLE `COMMUNITY` AUTO_INCREMENT = 1;
ALTER TABLE `COMMUNITY_BOARD` AUTO_INCREMENT = 1;
ALTER TABLE `COMMUNITY_BOARD_COMMENT` AUTO_INCREMENT = 1;

-- 외래키 제약 조건을 다시 활성화
SET FOREIGN_KEY_CHECKS = 1;

-- 새로운 데이터 삽입
INSERT INTO `USERS` (`id`, `password`, `email`, `name`, `nickname`, `age`, `phone_number`, `address`)
VALUES
    ('prao', 'pass', 'prao@gmail.com', '박창호', 'prao96', 27, '010-1234-5678', '부산광역시 부산진구 전포대로155'),
    ('admin', 'pass', 'admin@gmail.com', '관리자', '관리자', 25, '010-2345-6789', '대전광역시 유성구 봉명동');

-- TEAM 테이블 더미 데이터 생성
INSERT INTO `TEAM` (`name`, `song_path`)
VALUES
    ('롯데 자이언츠', 'songs/teamA.mp3'),
    ('삼성 라이온즈', 'songs/teamB.mp3');

-- PLAYERS 테이블 더미 데이터 생성
INSERT INTO `PLAYERS` (`team_id`, `name`, `age`, `back_number`, `song_path`)
VALUES
    (1, '황성빈', 26, 0, 'songs/player1.mp3'),
    (1, '김원중', 30, 34, 'songs/player2.mp3'),
    (2, '구자욱', 31, 5, 'songs/player3.mp3');

-- COMMUNITY 테이블 더미 데이터 생성
INSERT INTO `COMMUNITY` (`team_id`, `name`, `description`)
VALUES
    (1, '롯데 자이언츠 게시판', '진정한 부산 사람들만 글 쓸 수 있음'),
    (2, '삼성 라이온즈 게시판', '그냥 그저 그런 대구 팀');

-- COMMUNITY_BOARD 테이블 더미 데이터 생성
INSERT INTO `COMMUNITY_BOARD` (`community_id`, `user_id`, `title`, `content`)
VALUES
    (1, 1, '롯데, 그들은 올라갈 힘을 비축하고 있다', '롯데는 이번 시즌 5등으로 마무리할 것이다. 한화는 까불지 마라.'),
    (2, 2, '삼성, 어짜피 내려올 거 얼른 내려와', '거기 어울리는 자리 아닌 거 알잖아. 사람이 몸에 맞는 옷을 입어야해. 너넨 8등이 어울려.');

-- COMMUNITY_BOARD_COMMENT 테이블 더미 데이터 생성
INSERT INTO `COMMUNITY_BOARD_COMMENT` (`community_board_id`,`user_id`, `content`)
VALUES
    (1, 2, '마 ! 붓 싼 아니가 ~'),
    (2, 1, '샘송은 뭐;;');
