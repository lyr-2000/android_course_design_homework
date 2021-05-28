/*
 Navicat Premium Data Transfer

 Source Server         : 腾讯云_spider_APP
 Source Server Type    : MySQL
 Source Server Version : 50733
 
 Source Schema         : spider_app

 Target Server Type    : MySQL
 Target Server Version : 50733
 File Encoding         : 65001

 Date: 21/05/2021 12:18:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_book_info
-- ----------------------------
DROP TABLE IF EXISTS `t_book_info`;
CREATE TABLE `t_book_info`  (
  `book_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '小说ID',
  `website_id` bigint(20) NULL DEFAULT NULL COMMENT '网页ID',
  `book_name` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '书籍名字',
  `book_introduction` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '内容介绍',
  `from_website_id` bigint(20) NULL DEFAULT NULL COMMENT '下载网页的Id',
  `delete_status` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  `author` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '作者名字',
  `book_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '小说下载地址',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '网页图片',
  `already_finished` tinyint(1) NULL DEFAULT 0 COMMENT '是否下载完成',
  PRIMARY KEY (`book_id`) USING BTREE,
  UNIQUE INDEX `unique_book_url`(`book_url`) USING BTREE COMMENT 'unique唯一索引'
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_index_page
-- ----------------------------
DROP TABLE IF EXISTS `t_index_page`;
CREATE TABLE `t_index_page`  (
  `download_url` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `download_title` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `index_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `book_id` bigint(20) NULL DEFAULT NULL,
  `delete_status` tinyint(1) NULL DEFAULT 0,
  `gmt_create` datetime(0) NULL DEFAULT NULL,
  `gmt_modified` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`index_id`) USING BTREE,
  UNIQUE INDEX `unique_download_url`(`download_url`) USING BTREE COMMENT '下载链接索引'
) ENGINE = InnoDB AUTO_INCREMENT = 23163 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_novel_detail_page
-- ----------------------------
DROP TABLE IF EXISTS `t_novel_detail_page`;
CREATE TABLE `t_novel_detail_page`  (
  `detail_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `index_id` bigint(20) NULL DEFAULT NULL,
  `book_detail` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `delete_status` tinyint(1) NULL DEFAULT NULL,
  `gmt_create` datetime(0) NULL DEFAULT NULL,
  `gmt_modified` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`detail_id`) USING BTREE,
  UNIQUE INDEX `index_unique`(`index_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 510 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_website_rule
-- ----------------------------
DROP TABLE IF EXISTS `t_website_rule`;
CREATE TABLE `t_website_rule`  (
  `website_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `website_name` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '网站名字',
  `website_url` varchar(600) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '网站地址',
  `delete_status` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `search_url` varchar(600) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '搜索 url',
  `detail_selector` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '内容选择器',
  `book_name_selector` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '小说名字选择器',
  `book_title_selector` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '小说标题选择器',
  PRIMARY KEY (`website_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
