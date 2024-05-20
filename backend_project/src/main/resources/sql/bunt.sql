CREATE SCHEMA IF NOT EXISTS `bunt` CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;
USE `bunt`;

DROP TABLE IF EXISTS `USERS`;
CREATE TABLE `USERS`
(
    `user_id`      INT          NOT NULL AUTO_INCREMENT,
    `id`           VARCHAR(20)  NOT NULL,
    `password`     VARCHAR(30)  NOT NULL,
    `email`        VARCHAR(40)  NOT NULL,
    `name`         VARCHAR(20)  NOT NULL,
    `nickname`     VARCHAR(20)  NOT NULL,
    `age`          INT          NOT NULL,
    `phone_number` VARCHAR(50)  NOT NULL,
    `address`      VARCHAR(100) NOT NULL,
    `join_date`    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `mod_date`     TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`user_id`)
);

DROP TABLE IF EXISTS `TEAM`;
CREATE TABLE `TEAM`
(
    `team_id`   INT          NOT NULL AUTO_INCREMENT,
    `name`      VARCHAR(20)  NOT NULL,
    `song_path` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`team_id`)
);

DROP TABLE IF EXISTS `PLAYERS`;
CREATE TABLE `PLAYERS`
(
    `player_id`   INT          NOT NULL AUTO_INCREMENT,
    `team_id`     INT          NOT NULL,
    `name`        VARCHAR(20)  NOT NULL,
    `age`         INT          NOT NULL,
    `back_number` INT          NOT NULL,
    `song_path`   VARCHAR(100) NOT NULL,
    PRIMARY KEY (`player_id`)
);

DROP TABLE IF EXISTS `COMMUNITY`;
CREATE TABLE `COMMUNITY`
(
    `community_id` INT         NOT NULL AUTO_INCREMENT,
    `team_id`      INT         NOT NULL,
    `name`         VARCHAR(20) NOT NULL,
    `description`  TEXT        NOT NULL,
    PRIMARY KEY (`community_id`)
);

DROP TABLE IF EXISTS `COMMUNITY_BOARD`;
CREATE TABLE `COMMUNITY_BOARD`
(
    `community_board_id` INT          NOT NULL AUTO_INCREMENT,
    `team_id`            INT          NOT NULL,
    `user_id`            VARCHAR(20) NOT NULL,
    `title`              VARCHAR(100) NOT NULL,
    `content`            TEXT         NOT NULL,
    `reg_date`           TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `mod_date`           TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`community_board_id`)
);

DROP TABLE IF EXISTS `COMMUNITY_BOARD_COMMENT`;
CREATE TABLE `COMMUNITY_BOARD_COMMENT`
(
    `community_board_comment_id` INT            NOT NULL AUTO_INCREMENT,
    `community_board_id`         INT            NOT NULL,
    `user_id`                    VARCHAR(100)   NOT NULL,
    `content`                    TEXT           NOT NULL,
    `reg_date`                   TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `mod_date`                   TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`community_board_comment_id`),
    FOREIGN KEY (community_board_id) REFERENCES COMMUNITY_BOARD(community_board_id) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS `COMMUNITY_BOARD_COMMENT`;
CREATE TABLE `COMMUNITY_BOARD_COMMENT`
(
    `community_board_comment_id` INT            NOT NULL AUTO_INCREMENT,
    `community_board_id`         INT            NOT NULL,
    `user_id`                    VARCHAR(20)   NOT NULL,
    `content`                    TEXT           NOT NULL,
    `reg_date`                   TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `mod_date`                   TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`community_board_comment_id`),
    FOREIGN KEY (community_board_id) REFERENCES COMMUNITY_BOARD(community_board_id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- 24.05.20 추가
DROP TABLE IF EXISTS `BOARD_LIKE_LIST`;
CREATE TABLE `BOARD_LIKE_LIST`
(
    `community_board_id` INT NOT NULL,
    `user_id` VARCHAR(20) NOT NULL,
    `reg_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- 외래키 설정하려면 USERS id에 인덱스 필요함
CREATE INDEX idx_users_id ON `USERS` (`id`);

ALTER TABLE `BOARD_LIKE_LIST`
    ADD CONSTRAINT `fk_board_like_list_community_board`
        FOREIGN KEY (`community_board_id`) REFERENCES `COMMUNITY_BOARD` (`community_board_id`);

ALTER TABLE `BOARD_LIKE_LIST`
    ADD CONSTRAINT `fk_board_like_list_users`
        FOREIGN KEY (`user_id`) REFERENCES `USERS` (`id`);



ALTER TABLE `PLAYERS`
    ADD CONSTRAINT `FK_TEAM_TO_PLAYERS_1` FOREIGN KEY (`team_id`) REFERENCES `TEAM` (`team_id`);
ALTER TABLE `COMMUNITY`
    ADD CONSTRAINT `FK_TEAM_TO_COMMUNITY_1` FOREIGN KEY (`team_id`) REFERENCES `TEAM` (`team_id`);
# ALTER TABLE `COMMUNITY_BOARD`
#     ADD CONSTRAINT `FK_COMMUNITY_TO_COMMUNITY_BOARD_1` FOREIGN KEY (`community_id`) REFERENCES `COMMUNITY` (`community_id`);
ALTER TABLE `COMMUNITY_BOARD`
    ADD CONSTRAINT `FK_USERS_TO_COMMUNITY_BOARD_1` FOREIGN KEY (`user_id`) REFERENCES `USERS` (`id`);
-- ALTER TABLE `COMMUNITY_BOARD_COMMENT`
--     ADD CONSTRAINT `FK_COMMUNITY_BOARD_TO_COMMUNITY_BOARD_COMMENT_1` FOREIGN KEY (`community_board_id`) REFERENCES `COMMUNITY_BOARD` (`community_board_id`);
ALTER TABLE `COMMUNITY_BOARD_COMMENT`
    ADD CONSTRAINT `FK_USERS_TO_COMMUNITY_BOARD_COMMENT_1` FOREIGN KEY (`user_id`) REFERENCES `USERS` (`id`);
