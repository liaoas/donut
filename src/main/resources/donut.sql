/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : donut

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 09/07/2020 09:39:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for d_bbs
-- ----------------------------
DROP TABLE IF EXISTS `d_bbs`;
CREATE TABLE `d_bbs`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '留言板ID',
  `bbs_userId` int(11) NULL DEFAULT NULL COMMENT '留言用户ID',
  `bbs_content` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '留言内容',
  `bbs_status` int(1) NULL DEFAULT NULL COMMENT '状态',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `bbs_userId`(`bbs_userId`) USING BTREE,
  CONSTRAINT `d_bbs_ibfk_1` FOREIGN KEY (`bbs_userId`) REFERENCES `d_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '留言表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of d_bbs
-- ----------------------------
INSERT INTO `d_bbs` VALUES (2, 9, '修改测试留言', 1, '2020-01-31 09:16:06');
INSERT INTO `d_bbs` VALUES (3, 9, '留言测试', 1, '2020-03-04 13:56:06');
INSERT INTO `d_bbs` VALUES (4, 9, '4', 1, '2020-03-04 13:56:07');
INSERT INTO `d_bbs` VALUES (5, 9, '留言测试', 1, '2020-03-04 13:56:08');
INSERT INTO `d_bbs` VALUES (6, 9, '留言测试', 1, '2020-03-04 13:56:08');
INSERT INTO `d_bbs` VALUES (7, 9, '留言内容', 1, '2020-03-04 13:56:09');
INSERT INTO `d_bbs` VALUES (8, 9, '李澳大人', 1, '2020-03-04 13:56:09');
INSERT INTO `d_bbs` VALUES (9, 9, '李澳大人', 1, '2020-03-04 13:56:10');
INSERT INTO `d_bbs` VALUES (10, 9, '李澳大人', 1, '2020-03-04 13:56:10');
INSERT INTO `d_bbs` VALUES (11, 9, '测试', 1, '2020-03-04 13:56:11');
INSERT INTO `d_bbs` VALUES (12, 1, 'http://localhost:8080/donut/html/home.html#', 1, '2020-03-08 08:03:27');
INSERT INTO `d_bbs` VALUES (13, 32, '让我告诉你', 1, '2020-03-15 01:10:50');
INSERT INTO `d_bbs` VALUES (14, 32, '让我告诉你啊！！！', 1, '2020-03-15 03:07:19');
INSERT INTO `d_bbs` VALUES (15, 33, '留言添加测试', 1, '2020-04-14 10:21:21');
INSERT INTO `d_bbs` VALUES (16, 33, 'asdasdasd', 1, '2020-04-26 02:36:37');
INSERT INTO `d_bbs` VALUES (17, 41, 'asdasd', 1, '2020-05-14 07:07:23');
INSERT INTO `d_bbs` VALUES (18, 41, 'admin52', 1, '2020-06-01 07:21:55');

-- ----------------------------
-- Table structure for d_bulletin
-- ----------------------------
DROP TABLE IF EXISTS `d_bulletin`;
CREATE TABLE `d_bulletin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `bulletin_userId` int(11) NULL DEFAULT NULL COMMENT '发布者ID',
  `bulletin_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '公告标题',
  `bulletin_content` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '公告内容',
  `bulletin_status` int(1) NULL DEFAULT NULL COMMENT '状态',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `bulletin_userId`(`bulletin_userId`) USING BTREE,
  CONSTRAINT `d_bulletin_ibfk_1` FOREIGN KEY (`bulletin_userId`) REFERENCES `d_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '公告表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of d_bulletin
-- ----------------------------
INSERT INTO `d_bulletin` VALUES (2, 1, '公告测试标题修改', '公告测试内容修改', 0, '2020-01-31 11:33:56');
INSERT INTO `d_bulletin` VALUES (3, 1, '公告测试标题', '公告测试内容', 1, '2020-03-04 14:33:25');
INSERT INTO `d_bulletin` VALUES (4, 1, '公告测试标题', '公告测试内容', 1, '2020-03-04 14:33:25');
INSERT INTO `d_bulletin` VALUES (5, 1, '公告测试标题修改测试', '公告测试内容', 1, '2020-03-04 14:33:26');
INSERT INTO `d_bulletin` VALUES (6, 1, '发布公告标题', '发布公告内容', 1, '2020-03-05 02:28:18');
INSERT INTO `d_bulletin` VALUES (7, 1, '发布公告', '发布公告', 1, '2020-03-05 02:28:26');
INSERT INTO `d_bulletin` VALUES (8, 1, '测试时间', '测试时间', 1, '2020-03-15 03:14:14');
INSERT INTO `d_bulletin` VALUES (9, 1, 'ce', 'ce', 1, '2020-05-08 13:11:05');
INSERT INTO `d_bulletin` VALUES (10, 1, 'dd', 'cc', 1, '2020-05-08 07:16:04');
INSERT INTO `d_bulletin` VALUES (12, 1, 'dasda', 'asdasd', 1, '2019-12-01 13:30:54');
INSERT INTO `d_bulletin` VALUES (13, 1, 'sqdasd', 'asdasdfafasfd', 0, '2020-05-08 15:25:28');

-- ----------------------------
-- Table structure for d_img
-- ----------------------------
DROP TABLE IF EXISTS `d_img`;
CREATE TABLE `d_img`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `product_id` int(11) NULL DEFAULT NULL COMMENT '产品图片ID',
  `img` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '图片',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `product_id`(`product_id`) USING BTREE,
  CONSTRAINT `d_img_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `d_product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 99 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '产品图片' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of d_img
-- ----------------------------
INSERT INTO `d_img` VALUES (32, 7, '1493105022031_586495.jpg', '2020-03-03 16:29:25');
INSERT INTO `d_img` VALUES (40, 1, '1491803633034_683819.jpg', '2020-03-03 17:21:20');
INSERT INTO `d_img` VALUES (41, 1, '1568950881751_702421.jpg', '2020-03-03 17:21:20');
INSERT INTO `d_img` VALUES (52, 8, '1488424654663_468827.jpg', '2020-03-04 03:20:04');
INSERT INTO `d_img` VALUES (53, 8, '1493105022031_586495.jpg', '2020-03-04 03:20:04');
INSERT INTO `d_img` VALUES (57, 4, '1504755963507_660305.jpg', '2020-03-04 03:21:08');
INSERT INTO `d_img` VALUES (58, 2, '1488424654663_468827.jpg', '2020-03-04 03:21:26');
INSERT INTO `d_img` VALUES (59, 9, '1503996204164_376156.jpg', '2020-03-04 03:21:41');
INSERT INTO `d_img` VALUES (60, 10, '1503996204164_376156.jpg', '2020-03-04 12:22:31');
INSERT INTO `d_img` VALUES (61, 11, '1503996204164_376156.jpg', '2020-03-04 12:22:39');
INSERT INTO `d_img` VALUES (62, 13, '1493105022031_586495.jpg', '2020-03-04 05:13:57');
INSERT INTO `d_img` VALUES (88, 17, '02.jpg', '2020-04-22 03:24:09');
INSERT INTO `d_img` VALUES (89, 16, '03.jpg', '2020-04-22 03:24:17');
INSERT INTO `d_img` VALUES (90, 15, '04.jpg', '2020-04-22 03:24:25');
INSERT INTO `d_img` VALUES (92, 18, '01.jpg', '2020-04-26 02:33:04');
INSERT INTO `d_img` VALUES (93, 18, '1554103835292_610234.jpg', '2020-04-26 02:33:04');
INSERT INTO `d_img` VALUES (94, 18, '1555484699771_582172.jpg', '2020-04-26 02:33:04');
INSERT INTO `d_img` VALUES (95, 18, '1554103835292_610234.jpg', '2020-04-26 02:33:04');
INSERT INTO `d_img` VALUES (99, 19, '1496903638807_884975.jpg', '2020-05-25 03:03:24');

-- ----------------------------
-- Table structure for d_product
-- ----------------------------
DROP TABLE IF EXISTS `d_product`;
CREATE TABLE `d_product`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '产品ID',
  `product_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '产品名称',
  `product_introduction` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '产品介绍',
  `product_category` int(11) NULL DEFAULT NULL COMMENT '产品ID',
  `product_status` int(1) NULL DEFAULT NULL COMMENT '产品状态',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '产品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of d_product
-- ----------------------------
INSERT INTO `d_product` VALUES (1, '测试产品名称修改', '测试产品介绍修改', NULL, 1, '2020-02-02 12:40:06');
INSERT INTO `d_product` VALUES (2, '祸水', '产品介绍', NULL, 1, '2020-03-02 18:15:07');
INSERT INTO `d_product` VALUES (4, '红颜', '产品介绍img', NULL, 1, '2020-03-02 22:18:59');
INSERT INTO `d_product` VALUES (7, '测试产品名称', '测试产品介绍', NULL, 1, '2020-03-02 14:19:16');
INSERT INTO `d_product` VALUES (8, '测试产品名称', '测试产品介绍', NULL, 1, '2020-03-02 14:19:17');
INSERT INTO `d_product` VALUES (9, '都想要啊', '测试产品介绍', NULL, 1, '2020-03-02 14:19:18');
INSERT INTO `d_product` VALUES (10, '测试产品名称', '测试产品介绍', NULL, 1, '2020-03-04 04:20:19');
INSERT INTO `d_product` VALUES (11, '测试产品名称', '测试产品介绍', NULL, 1, '2020-03-04 04:22:07');
INSERT INTO `d_product` VALUES (13, '产品添加图片上传测试', '产品添加图片上传测试', NULL, 1, '2020-03-04 05:13:57');
INSERT INTO `d_product` VALUES (15, '产品名称img', '产品介绍', NULL, 0, '2020-03-04 05:18:43');
INSERT INTO `d_product` VALUES (16, '产品名称添加测试', '产品名称添加测试', NULL, 1, '2020-03-04 13:16:27');
INSERT INTO `d_product` VALUES (17, '修改测试', '修改测试', NULL, 1, '2020-03-05 15:36:30');
INSERT INTO `d_product` VALUES (18, '产品添加图片上传测试', '产品添加图片上传测试', NULL, 1, '2020-04-14 13:40:40');
INSERT INTO `d_product` VALUES (19, '日志测试', '日志测试', NULL, 1, '2020-05-11 03:54:53');

-- ----------------------------
-- Table structure for d_user
-- ----------------------------
DROP TABLE IF EXISTS `d_user`;
CREATE TABLE `d_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_account` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '账号',
  `user_password` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '密码',
  `user_roles` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '角色名称',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '姓名',
  `user_sex` char(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '性别',
  `user_age` int(4) NULL DEFAULT NULL COMMENT '年龄',
  `user_email` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '邮箱',
  `head_img` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '头像',
  `user_status` int(1) NULL DEFAULT NULL COMMENT '状态',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_roles`(`user_roles`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of d_user
-- ----------------------------
INSERT INTO `d_user` VALUES (1, 'admin', 'admin', '管理员', '秋阳', '女', NULL, 'liao991224@gmail.com', '62aafd0af6e5230337b946160c777b7.jpg', 1, '2020-01-23 05:45:00');
INSERT INTO `d_user` VALUES (9, 'admin01', 'admin0102', '管理员', '冯秋阳', '女', NULL, 'liao991224@gmail.com', '1504679338743_764287.jpg', 1, '2020-01-23 06:08:45');
INSERT INTO `d_user` VALUES (23, 'admin01', 'admin01', '管理员', '孙宠', '男', NULL, 'liao991224@gmail.com', '1505895422601_499077.jpg', 1, '2020-02-10 01:57:02');
INSERT INTO `d_user` VALUES (24, 'admin02', 'admin02', '普通用户', 'Ao Li', '男', NULL, '227296324@qq.com', '1491803633034_683819.jpg', 1, '2020-02-10 02:11:56');
INSERT INTO `d_user` VALUES (25, 'admin03', 'admin03', '普通用户', 'aaaa', '女', NULL, '2272963013@gmail.com', '1501753501610_493895.jpg', 1, '2020-02-29 10:45:28');
INSERT INTO `d_user` VALUES (26, 'admin04', 'admin04', '普通用户', 'Ao Li', '女', NULL, '2272963013@gmail.com', '1497506292704_797348.jpg', 1, '2020-02-29 10:46:49');
INSERT INTO `d_user` VALUES (27, 'admin05', 'admin05', '普通用户', 'Li Ao', '男', NULL, '227296301@asdas.com', '1488424654663_468827.jpg', 1, '2020-02-29 11:18:43');
INSERT INTO `d_user` VALUES (28, 'admin01', 'admin01', '普通用户', 'null', 'null', NULL, '123456879@asda.com', '1522726105492_945820.jpg', 1, '2020-03-05 15:28:55');
INSERT INTO `d_user` VALUES (29, 'admin002', 'admin002', '管理员', 'null', 'null', NULL, '123456879@asda.com', '1506669629189_786198.jpg', 1, '2020-03-05 15:31:10');
INSERT INTO `d_user` VALUES (30, 'liao001', 'liao001', '普通用户', 'null', '男', NULL, 'asdasdasda123123@q.com', '1496903638807_884975.jpg', 1, '2020-03-05 15:38:09');
INSERT INTO `d_user` VALUES (31, 'liao0010', 'liao0010', '普通用户', 'null', '男', NULL, 'liao991224@asda.com', '1525418696194_736363.jpg', 0, '2020-03-05 15:41:27');
INSERT INTO `d_user` VALUES (32, 'asdasd12', 'asdasd12', '普通用户', '秋阳', '女', NULL, 'li991224asd@asda.com', '1488424654663_468827.jpg', 1, '2020-03-05 15:51:33');
INSERT INTO `d_user` VALUES (33, 'admin45', 'admin45', '普通用户', 'Ao Li', '女', NULL, 'asdasda@qq.com', '1500348341426_164057.jpg', 1, '2020-03-26 02:12:13');
INSERT INTO `d_user` VALUES (34, 'admin46', 'admin46', '普通用户', 'null', 'null', NULL, '1234566789@qq.com', '1554103827349_639223.jpg', 1, '2020-04-26 01:28:23');
INSERT INTO `d_user` VALUES (35, 'admin47', 'admin47', '普通用户', NULL, NULL, NULL, NULL, NULL, 1, '2020-04-26 01:31:47');
INSERT INTO `d_user` VALUES (36, 'admin48', 'admin48', '普通用户', NULL, NULL, NULL, NULL, NULL, 1, '2020-04-26 01:38:45');
INSERT INTO `d_user` VALUES (37, 'admin50', 'admin50', '普通用户', NULL, NULL, NULL, NULL, NULL, 1, '2020-04-26 01:41:32');
INSERT INTO `d_user` VALUES (38, 'admin60', 'admin60', '普通用户', NULL, NULL, NULL, NULL, NULL, 1, '2020-04-26 01:42:51');
INSERT INTO `d_user` VALUES (39, 'admin51', 'admin51', '普通用户', 'null', 'null', NULL, 'asdasda@gamil.com', '54EC40D7D9882745E35E264ACBA80691.jpg', 1, '2020-04-26 01:45:27');
INSERT INTO `d_user` VALUES (40, 'admin51', 'admin51', '普通用户', NULL, NULL, NULL, NULL, NULL, 1, '2020-04-26 01:45:27');
INSERT INTO `d_user` VALUES (41, 'admin52', 'admin52', '普通用户', 'null', 'null', NULL, 'dasdadawSD@gmail.com', '54EC40D7D9882745E35E264ACBA80691.jpg', 1, '2020-04-26 01:46:28');
INSERT INTO `d_user` VALUES (42, 'admin53', 'admin53', '管理员', NULL, NULL, NULL, NULL, NULL, 1, '2020-04-26 01:48:55');
INSERT INTO `d_user` VALUES (43, 'admin54', 'admin54', '管理员', NULL, NULL, NULL, NULL, NULL, 1, '2020-05-11 05:58:33');

SET FOREIGN_KEY_CHECKS = 1;
