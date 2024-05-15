-- 외래키 제약 조건을 비활성화
SET FOREIGN_KEY_CHECKS = 0;

-- 테이블의 데이터 삭제
DELETE FROM `COMMUNITY_BOARD_COMMENT`;
DELETE FROM `COMMUNITY_BOARD`;
DELETE FROM `COMMUNITY`;
DELETE FROM `ZZIM`;
DELETE FROM `PLAYERS`;
DELETE FROM `TEAM`;
DELETE FROM `CART`;
DELETE FROM `INQUIRY_COMMENT`;
DELETE FROM `INQUIRY`;
DELETE FROM `REVIEW_COMMENT`;
DELETE FROM `REVIEW`;
DELETE FROM `ORDER_ITEM`;
DELETE FROM `ORDERS`;
DELETE FROM `ITEM`;
DELETE FROM `DELIVERY`;
DELETE FROM `USERS`;

-- AUTO_INCREMENT 값을 초기화
ALTER TABLE `USERS` AUTO_INCREMENT = 1;
ALTER TABLE `DELIVERY` AUTO_INCREMENT = 1;
ALTER TABLE `ORDERS` AUTO_INCREMENT = 1;
ALTER TABLE `ORDER_ITEM` AUTO_INCREMENT = 1;
ALTER TABLE `ITEM` AUTO_INCREMENT = 1;
ALTER TABLE `REVIEW` AUTO_INCREMENT = 1;
ALTER TABLE `REVIEW_COMMENT` AUTO_INCREMENT = 1;
ALTER TABLE `INQUIRY` AUTO_INCREMENT = 1;
ALTER TABLE `INQUIRY_COMMENT` AUTO_INCREMENT = 1;
ALTER TABLE `CART` AUTO_INCREMENT = 1;
ALTER TABLE `TEAM` AUTO_INCREMENT = 1;
ALTER TABLE `PLAYERS` AUTO_INCREMENT = 1;
ALTER TABLE `ZZIM` AUTO_INCREMENT = 1;
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

INSERT INTO `TEAM` (`name`, `song_path`)
VALUES
    ('팀 A', 'songs/teama.mp3'),
    ('팀 B', 'songs/teamb.mp3');

INSERT INTO `DELIVERY` (`status`, `city`, `street`, `zipcode`, `delivery_request`)
VALUES
    ('배송중', '서울', '강남구 테헤란로 123', '06241', '경비실에 맡겨주세요'),
    ('배송완료', '부산', '해운대구 우동 456', '48095', '문 앞에 두세요');

INSERT INTO `ITEM` (`team_id`, `name`, `price`, `stock_quantity`, `image_path`, `category`, `item_detail`)
VALUES
    (1, '야구 모자', 20000, 100, 'images/cap.png', '악세서리', '고품질 야구 모자입니다.'),
    (2, '야구 유니폼', 50000, 50, 'images/jersey.png', '의류', '공식 팀 유니폼입니다.');

INSERT INTO `ORDERS` (`user_id`, `delivery_id`, `order_date`, `status`, `total_price`)
VALUES
    (1, 1, NOW(), '처리중', 10000),
    (2, 2, NOW(), '배송중', 15000);

INSERT INTO `ORDER_ITEM` (`order_id`, `item_id`, `count`, `order_price`)
VALUES
    (1, 1, 2, 40000),
    (2, 2, 2, 50000);

INSERT INTO `REVIEW` (`user_id`, `item_id`, `title`, `content`, `image_path`)
VALUES
    (1, 1, '멋진 모자!', '정말 품질이 좋아요.', 'images/review1.png'),
    (2, 2, '유니폼 후기', '유니폼이 정말 편하고 멋져요.', 'images/review2.png');

INSERT INTO `INQUIRY` (`user_id`, `item_id`, `title`, `content`)
VALUES
    (1, 1, '모자 사이즈', '모자는 어떤 사이즈가 있나요?'),
    (2, 2, '유니폼 재질', '유니폼은 어떤 재질로 만들어졌나요?');

INSERT INTO `REVIEW_COMMENT` (`user_id`, `board_id`, `content`)
VALUES
    (2, 1, '저도 이 모자 좋아해요!'),
    (1, 2, '유니폼 멋지네요!');

INSERT INTO `INQUIRY_COMMENT` (`user_id`, `board_id`, `content`)
VALUES
    (2, 1, '모자는 S, M, L 사이즈로 나옵니다.'),
    (1, 2, '유니폼은 통기성 있는 재질로 만들어졌습니다.');

INSERT INTO `CART` (`user_id`, `item_id`, `quantity`, `order_status`)
VALUES
    (1, 1, 1, '대기중'),
    (2, 2, 2, '대기중');

INSERT INTO `PLAYERS` (`team_id`, `name`, `age`, `back_number`, `song_path`)
VALUES
    (1, '박찬호', 28, 10, 'songs/player1.mp3'),
    (2, '이승엽', 30, 20, 'songs/player2.mp3');

INSERT INTO `ZZIM` (`user_id`, `item_id`, `status`)
VALUES
    (1, 1, '활성'),
    (2, 2, '활성');

INSERT INTO `COMMUNITY` (`team_id`, `name`, `description`)
VALUES
    (1, '팀 A 팬', '팀 A 팬을 위한 커뮤니티입니다.'),
    (2, '팀 B 팬', '팀 B 팬을 위한 커뮤니티입니다.');

INSERT INTO `COMMUNITY_BOARD` (`community_id`, `user_id`, `title`, `content`)
VALUES
    (1, 1, '팀 A 팬 환영합니다', '팀 A 커뮤니티 첫 게시글입니다.'),
    (2, 2, '팀 B 팬 환영합니다', '팀 B 커뮤니티 첫 게시글입니다.');

INSERT INTO `COMMUNITY_BOARD_COMMENT` (`community_board_id`, `user_id`, `content`)
VALUES
    (1, 2, '반가워요!'),
    (2, 1, '팬 여러분 반갑습니다!');
