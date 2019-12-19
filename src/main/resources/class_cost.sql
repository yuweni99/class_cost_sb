/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50612
Source Host           : localhost:3306
Source Database       : class_cost

Target Server Type    : MYSQL
Target Server Version : 50612
File Encoding         : 65001

Date: 2019-12-19 19:02:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for charging
-- ----------------------------
DROP TABLE IF EXISTS `charging`;
CREATE TABLE `charging` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `money` double DEFAULT NULL COMMENT '金额',
  `class_id` int(11) DEFAULT NULL COMMENT '班级id',
  `charging_desc` varchar(255) DEFAULT NULL COMMENT '收费说明',
  `user_id` int(11) DEFAULT NULL COMMENT '班级管理员关联',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of charging
-- ----------------------------
INSERT INTO `charging` VALUES ('4', '200', '3', '收缴班费', '4', '2019-12-19 00:11:24');

-- ----------------------------
-- Table structure for charging_history
-- ----------------------------
DROP TABLE IF EXISTS `charging_history`;
CREATE TABLE `charging_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `charging_id` int(11) DEFAULT NULL COMMENT '缴费id',
  `user_id` int(255) DEFAULT NULL COMMENT '学生id',
  `status` int(11) DEFAULT NULL COMMENT '缴费状态 0 未缴费，1缴费',
  `update_time` datetime DEFAULT NULL COMMENT '缴费时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of charging_history
-- ----------------------------
INSERT INTO `charging_history` VALUES ('1', '4', '5', '1', '2019-12-19 00:56:12', '2019-12-19 00:11:40');
INSERT INTO `charging_history` VALUES ('2', '4', '6', '0', null, '2019-12-19 00:11:41');

-- ----------------------------
-- Table structure for classes
-- ----------------------------
DROP TABLE IF EXISTS `classes`;
CREATE TABLE `classes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `class_name` varchar(255) DEFAULT NULL COMMENT '班级名称',
  `user_id` int(11) DEFAULT NULL COMMENT '班费管理员id',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of classes
-- ----------------------------
INSERT INTO `classes` VALUES ('1', '计软1班', '4', '2019-12-17 22:28:09');
INSERT INTO `classes` VALUES ('3', '计软2班', '4', '2019-12-18 15:35:23');
INSERT INTO `classes` VALUES ('4', '计软3班', '6', '2019-12-18 15:35:23');

-- ----------------------------
-- Table structure for expend
-- ----------------------------
DROP TABLE IF EXISTS `expend`;
CREATE TABLE `expend` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `money` double(255,0) DEFAULT NULL,
  `class_id` int(11) DEFAULT NULL,
  `use_details` varchar(1000) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL COMMENT '班级管理员关联，表示是谁添加的记录',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of expend
-- ----------------------------
INSERT INTO `expend` VALUES ('2', '21', '3', '活动花费', '4', '2019-12-18 22:14:40');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `class_id` int(11) DEFAULT NULL COMMENT '班级id',
  `password` varchar(255) DEFAULT NULL,
  `role_type` int(11) DEFAULT '3' COMMENT ' 1：系统管理员 2：班费管理员,3: 学生',
  `status` int(1) DEFAULT '0' COMMENT '0未激活，1激活',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', null, 'yy5yl2s59l52s6e6095222955b96b969', '1', null, null);
INSERT INTO `user` VALUES ('4', 'ad', null, 's92s6sys026296s5ebblq2sy6b5b65q0', '2', '1', '2019-12-17 23:11:59');
INSERT INTO `user` VALUES ('5', 'lisi', '3', 's92s6sys026296s5ebblq2sy6b5b65q0', '3', '1', '2019-12-18 16:31:28');
INSERT INTO `user` VALUES ('6', 'adc', '3', 'yy5yl2s59l52s6e6095222955b96b969', '3', '1', '2019-12-17 23:11:59');
