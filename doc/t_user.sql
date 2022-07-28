/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.27.203-开发
 Source Server Type    : MySQL
 Source Server Version : 50560
 Source Host           : 192.168.27.203:3306
 Source Schema         : vue3-server

 Target Server Type    : MySQL
 Target Server Version : 50560
 File Encoding         : 65001

 Date: 28/07/2022 18:11:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '账户',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '密码',
  `sex` int(1) NULL DEFAULT 1 COMMENT '性别：1-男；2-女；',
  `is_disabled` int(1) NULL DEFAULT 1 COMMENT '状态：1-启用；2-禁用；',
  `create_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (2, '张三', '1111', 1, 1, '2022-07-28 15:02:30', 'admin');
INSERT INTO `t_user` VALUES (3, '张三1', '1111', 1, 1, '2022-07-28 15:03:27', 'admin');
INSERT INTO `t_user` VALUES (4, '张三2', '1111', 1, 1, '2022-07-28 15:03:31', 'admin');
INSERT INTO `t_user` VALUES (5, '张三3', '1111', 1, 1, '2022-07-28 15:03:34', 'admin');
INSERT INTO `t_user` VALUES (6, '张三4', '1111', 1, 1, '2022-07-28 15:03:37', 'admin');
INSERT INTO `t_user` VALUES (7, '张三5', '1111', 1, 1, '2022-07-28 15:03:40', 'admin');
INSERT INTO `t_user` VALUES (8, '11', '11', 0, 2, '2022-07-28 15:03:43', 'admin');
INSERT INTO `t_user` VALUES (9, '张三7', '1111', 1, 1, '2022-07-28 15:03:46', 'admin');
INSERT INTO `t_user` VALUES (10, '张三8', '1111', 1, 1, '2022-07-28 15:03:50', 'admin');
INSERT INTO `t_user` VALUES (11, '张三9', '1111', 1, 1, '2022-07-28 15:03:53', 'admin');
INSERT INTO `t_user` VALUES (12, '张三10', '1111', 1, 1, '2022-07-28 15:03:57', 'admin');
INSERT INTO `t_user` VALUES (13, '张三11', '1111', 1, 1, '2022-07-28 15:04:01', 'admin');
INSERT INTO `t_user` VALUES (14, '张三12', '1111', 1, 1, '2022-07-28 15:04:04', 'admin');

SET FOREIGN_KEY_CHECKS = 1;
