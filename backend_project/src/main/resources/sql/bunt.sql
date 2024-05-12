CREATE SCHEMA IF NOT EXISTS `bunt` CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ;
USE `bunt`;

DROP TABLE IF EXISTS `USERS`;
CREATE TABLE `USERS` (
                         `user_id` INT NOT NULL,
                         `id` VARCHAR(20) NOT NULL,
                         `password` VARCHAR(30) NOT NULL,
                         `email` VARCHAR(40) NOT NULL,
                         `name` VARCHAR(20) NOT NULL,
                         `nickname` VARCHAR(20) NOT NULL,
                         `age` INT NOT NULL,
                         `phone_number` VARCHAR(50) NOT NULL,
                         `address` VARCHAR(100) NOT NULL,
                         `join_date` TIMESTAMP NOT NULL DEFAULT NOW(),
                         `mod_date` TIMESTAMP NOT NULL DEFAULT NOW()
);

DROP TABLE IF EXISTS `ORDERS`;
CREATE TABLE `ORDERS` (
                          `order_id` INT NOT NULL,
                          `user_id` INT NOT NULL,
                          `delivery_id` INT NOT NULL,
                          `orderdate` TIMESTAMP NOT NULL,
                          `status` VARCHAR(10) NOT NULL,
                          `total_price` INT NOT NULL
);

DROP TABLE IF EXISTS `DELIVERY`;
CREATE TABLE `DELIVERY` (
                            `delivery_id` INT NOT NULL,
                            `status` VARCHAR(10) NOT NULL,
                            `city` VARCHAR(20) NOT NULL,
                            `street` VARCHAR(20) NOT NULL,
                            `zipcode` VARCHAR(10) NOT NULL,
                            `delivery_request` VARCHAR(100) NOT NULL
);

DROP TABLE IF EXISTS `ORDER_ITEM`;
CREATE TABLE `ORDER_ITEM` (
                              `order_item_id` INT NOT NULL,
                              `order_id` INT NOT NULL,
                              `item_id` INT NOT NULL,
                              `quantity` INT NOT NULL,
                              `price` INT NOT NULL
);

DROP TABLE IF EXISTS `ITEM`;
CREATE TABLE `ITEM` (
                        `item_id` INT NOT NULL,
                        `team_id` INT NOT NULL,
                        `name` VARCHAR(20) NOT NULL,
                        `price` INT NOT NULL,
                        `stock_quantity` INT NOT NULL,
                        `image_path` VARCHAR(100) NOT NULL,
                        `category` VARCHAR(10) NOT NULL,
                        `item_detail` TEXT NOT NULL,
                        `reg_date` TIMESTAMP NOT NULL DEFAULT NOW(),
                        `mod_date` TIMESTAMP NOT NULL DEFAULT NOW()
);

DROP TABLE IF EXISTS `REVIEW`;
CREATE TABLE `REVIEW` (
                          `review_id` INT NOT NULL,
                          `user_id` INT NOT NULL,
                          `item_id` INT NOT NULL,
                          `title` VARCHAR(100) NOT NULL,
                          `content` TEXT NOT NULL,
                          `image_path` VARCHAR(100) NOT NULL,
                          `reg_date` TIMESTAMP NOT NULL DEFAULT NOW(),
                          `mod_date` TIMESTAMP NOT NULL DEFAULT NOW()
);

DROP TABLE IF EXISTS `REVIEW_COMMENT`;
CREATE TABLE `REVIEW_COMMENT` (
                                  `review_comment_id` INT NOT NULL,
                                  `user_id` INT NOT NULL,
                                  `board_id` INT NOT NULL,
                                  `content` TEXT NOT NULL,
                                  `reg_date` TIMESTAMP NOT NULL DEFAULT NOW(),
                                  `mod_date` TIMESTAMP NOT NULL DEFAULT NOW()
);

DROP TABLE IF EXISTS `INQUIRY`;
CREATE TABLE `INQUIRY` (
                           `inquiry_id` INT NOT NULL,
                           `user_id` INT NOT NULL,
                           `item_id` INT NOT NULL,
                           `title` VARCHAR(100) NOT NULL,
                           `content` TEXT NOT NULL,
                           `reg_date` TIMESTAMP NOT NULL DEFAULT NOW(),
                           `mod_date` TIMESTAMP NOT NULL DEFAULT NOW()
);

DROP TABLE IF EXISTS `INQUIRY_COMMENT`;
CREATE TABLE `INQUIRY_COMMENT` (
                                   `inquiry_comment_id` INT NOT NULL,
                                   `user_id` INT NOT NULL,
                                   `board_id` INT NOT NULL,
                                   `content` TEXT NOT NULL,
                                   `reg_date` TIMESTAMP NOT NULL DEFAULT NOW(),
                                   `mod_date` TIMESTAMP NOT NULL DEFAULT NOW()
);

DROP TABLE IF EXISTS `CART`;
CREATE TABLE `CART` (
                        `cart_id` INT NOT NULL,
                        `user_id` INT NOT NULL,
                        `item_id` INT NOT NULL,
                        `quantity` INT NOT NULL,
                        `order_status` VARCHAR(5) NOT NULL,
                        `reg_date` TIMESTAMP NOT NULL DEFAULT NOW()
);

DROP TABLE IF EXISTS `TEAM`;
CREATE TABLE `TEAM` (
                        `team_id` INT NOT NULL,
                        `name` VARCHAR(20) NOT NULL,
                        `song_path` VARCHAR(100) NOT NULL
);

DROP TABLE IF EXISTS `PLAYERS`;
CREATE TABLE `PLAYERS` (
                           `player_id` INT NOT NULL,
                           `team_id` INT NOT NULL,
                           `name` VARCHAR(20) NOT NULL,
                           `age` INT NOT NULL,
                           `back_number` INT NOT NULL,
                           `song_path` VARCHAR(100) NOT NULL
);

DROP TABLE IF EXISTS `ZZIM`;
CREATE TABLE `ZZIM` (
                        `zzim_id` INT NOT NULL,
                        `user_id` INT NOT NULL,
                        `item_id` INT NOT NULL,
                        `status` VARCHAR(10) NOT NULL
);

DROP TABLE IF EXISTS `COMMUNITY`;
CREATE TABLE `COMMUNITY` (
                             `community_id` INT NOT NULL,
                             `team_id` INT NOT NULL,
                             `name` VARCHAR(20) NOT NULL,
                             `description` TEXT NOT NULL
);

DROP TABLE IF EXISTS `COMMUNITY_BOARD`;
CREATE TABLE `COMMUNITY_BOARD` (
                                   `community_board_id` INT NOT NULL,
                                   `community_id` INT NOT NULL,
                                   `user_id` INT NOT NULL,
                                   `title` VARCHAR(100) NOT NULL,
                                   `content` TEXT NOT NULL,
                                   `reg_date` TIMESTAMP NOT NULL DEFAULT NOW(),
                                   `mod_date` TIMESTAMP NOT NULL DEFAULT NOW()
);

DROP TABLE IF EXISTS `COMMUNITY_BOARD_COMMENT`;
CREATE TABLE `COMMUNITY_BOARD_COMMENT` (
                                           `community_board_comment_id` INT NOT NULL,
                                           `community_board_id` INT NOT NULL,
                                           `user_id` INT NOT NULL,
                                           `content` TEXT NOT NULL,
                                           `reg_date` TIMESTAMP NOT NULL DEFAULT NOW(),
                                           `mod_date` TIMESTAMP NOT NULL DEFAULT NOW()
);

ALTER TABLE `USERS` ADD CONSTRAINT `PK_USERS` PRIMARY KEY (`user_id`);
ALTER TABLE `ORDERS` ADD CONSTRAINT `PK_ORDERS` PRIMARY KEY (`order_id`);
ALTER TABLE `DELIVERY` ADD CONSTRAINT `PK_DELIVERY` PRIMARY KEY (`delivery_id`);
ALTER TABLE `ORDER_ITEM` ADD CONSTRAINT `PK_ORDER_ITEM` PRIMARY KEY (`order_item_id`);
ALTER TABLE `ITEM` ADD CONSTRAINT `PK_ITEM` PRIMARY KEY (`item_id`);
ALTER TABLE `REVIEW` ADD CONSTRAINT `PK_REVIEW` PRIMARY KEY (`review_id`);
ALTER TABLE `REVIEW_COMMENT` ADD CONSTRAINT `PK_REVIEW_COMMENT` PRIMARY KEY (`review_comment_id`);
ALTER TABLE `INQUIRY` ADD CONSTRAINT `PK_INQUIRY` PRIMARY KEY (`inquiry_id`);
ALTER TABLE `INQUIRY_COMMENT` ADD CONSTRAINT `PK_INQUIRY_COMMENT` PRIMARY KEY (`inquiry_comment_id`);
ALTER TABLE `CART` ADD CONSTRAINT `PK_CART` PRIMARY KEY (`cart_id`);
ALTER TABLE `TEAM` ADD CONSTRAINT `PK_TEAM` PRIMARY KEY (`team_id`);
ALTER TABLE `PLAYERS` ADD CONSTRAINT `PK_PLAYERS` PRIMARY KEY (`player_id`);
ALTER TABLE `ZZIM` ADD CONSTRAINT `PK_ZZIM` PRIMARY KEY (`zzim_id`);
ALTER TABLE `COMMUNITY` ADD CONSTRAINT `PK_COMMUNITY` PRIMARY KEY (`community_id`);
ALTER TABLE `COMMUNITY_BOARD` ADD CONSTRAINT `PK_COMMUNITY_BOARD` PRIMARY KEY (`community_board_id`);
ALTER TABLE `COMMUNITY_BOARD_COMMENT` ADD CONSTRAINT `PK_COMMUNITY_BOARD_COMMENT` PRIMARY KEY (`community_board_comment_id`);
ALTER TABLE `ORDERS` ADD CONSTRAINT `FK_USERS_TO_ORDERS_1` FOREIGN KEY (`user_id`) REFERENCES `USERS` (`user_id`);
ALTER TABLE `ORDERS` ADD CONSTRAINT `FK_DELIVERY_TO_ORDERS_1` FOREIGN KEY (`delivery_id`) REFERENCES `DELIVERY` (`delivery_id`);
ALTER TABLE `ORDER_ITEM` ADD CONSTRAINT `FK_ORDERS_TO_ORDER_ITEM_1` FOREIGN KEY (`order_id`) REFERENCES `ORDERS` (`order_id`);
ALTER TABLE `ORDER_ITEM` ADD CONSTRAINT `FK_ITEM_TO_ORDER_ITEM_1` FOREIGN KEY (`item_id`) REFERENCES `ITEM` (`item_id`);
ALTER TABLE `ITEM` ADD CONSTRAINT `FK_TEAM_TO_ITEM_1` FOREIGN KEY (`team_id`) REFERENCES `TEAM` (`team_id`);
ALTER TABLE `REVIEW` ADD CONSTRAINT `FK_USERS_TO_REVIEW_1` FOREIGN KEY (`user_id`) REFERENCES `USERS` (`user_id`);
ALTER TABLE `REVIEW` ADD CONSTRAINT `FK_ITEM_TO_REVIEW_1` FOREIGN KEY (`item_id`) REFERENCES `ITEM` (`item_id`);
ALTER TABLE `REVIEW_COMMENT` ADD CONSTRAINT `FK_USERS_TO_REVIEW_COMMENT_1` FOREIGN KEY (`user_id`) REFERENCES `USERS` (`user_id`);
ALTER TABLE `REVIEW_COMMENT` ADD CONSTRAINT `FK_REVIEW_TO_REVIEW_COMMENT_1` FOREIGN KEY (`board_id`) REFERENCES `REVIEW` (`review_id`);
ALTER TABLE `INQUIRY` ADD CONSTRAINT `FK_USERS_TO_INQUIRY_1` FOREIGN KEY (`user_id`) REFERENCES `USERS` (`user_id`);
ALTER TABLE `INQUIRY` ADD CONSTRAINT `FK_ITEM_TO_INQUIRY_1` FOREIGN KEY (`item_id`) REFERENCES `ITEM` (`item_id`);
ALTER TABLE `INQUIRY_COMMENT` ADD CONSTRAINT `FK_USERS_TO_INQUIRY_COMMENT_1` FOREIGN KEY (`user_id`) REFERENCES `USERS` (`user_id`);
ALTER TABLE `INQUIRY_COMMENT` ADD CONSTRAINT `FK_INQUIRY_TO_INQUIRY_COMMENT_1` FOREIGN KEY (`board_id`) REFERENCES `INQUIRY` (`inquiry_id`);
ALTER TABLE `CART` ADD CONSTRAINT `FK_USERS_TO_CART_1` FOREIGN KEY (`user_id`) REFERENCES `USERS` (`user_id`);
ALTER TABLE `CART` ADD CONSTRAINT `FK_ITEM_TO_CART_1` FOREIGN KEY (`item_id`) REFERENCES `ITEM` (`item_id`);
ALTER TABLE `PLAYERS` ADD CONSTRAINT `FK_TEAM_TO_PLAYERS_1` FOREIGN KEY (`team_id`) REFERENCES `TEAM` (`team_id`);
ALTER TABLE `ZZIM` ADD CONSTRAINT `FK_USERS_TO_ZZIM_1` FOREIGN KEY (`user_id`) REFERENCES `USERS` (`user_id`);
ALTER TABLE `ZZIM` ADD CONSTRAINT `FK_ITEM_TO_ZZIM_1` FOREIGN KEY (`item_id`) REFERENCES `ITEM` (`item_id`);
ALTER TABLE `COMMUNITY` ADD CONSTRAINT `FK_TEAM_TO_COMMUNITY_1` FOREIGN KEY (`team_id`) REFERENCES `TEAM` (`team_id`);
ALTER TABLE `COMMUNITY_BOARD` ADD CONSTRAINT `FK_COMMUNITY_TO_COMMUNITY_BOARD_1` FOREIGN KEY (`community_id`) REFERENCES `COMMUNITY` (`community_id`);
ALTER TABLE `COMMUNITY_BOARD` ADD CONSTRAINT `FK_USERS_TO_COMMUNITY_BOARD_1` FOREIGN KEY (`user_id`) REFERENCES `USERS` (`user_id`);
ALTER TABLE `COMMUNITY_BOARD_COMMENT` ADD CONSTRAINT `FK_COMMUNITY_BOARD_TO_COMMUNITY_BOARD_COMMENT_1` FOREIGN KEY (`community_board_id`) REFERENCES `COMMUNITY_BOARD` (`community_board_id`);
ALTER TABLE `COMMUNITY_BOARD_COMMENT` ADD CONSTRAINT `FK_USERS_TO_COMMUNITY_BOARD_COMMENT_1` FOREIGN KEY (`user_id`) REFERENCES `USERS` (`user_id`);
