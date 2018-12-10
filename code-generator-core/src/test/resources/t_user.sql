/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : mapper

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 10/12/2018 18:58:43
*/

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
                         `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'PRIMARY ID',
                         `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0',
                         `user_gender` tinyint(1) DEFAULT NULL,
                         `user_salary` decimal(10, 0) DEFAULT NULL,
                         `user_birthday` datetime(0) DEFAULT NULL,
                         PRIMARY KEY (`id`) USING BTREE,
                         INDEX `idx_username`(`user_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表';
