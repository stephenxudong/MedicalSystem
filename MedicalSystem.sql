/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost
 Source Database       : MedicalSystem

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : utf-8

 Date: 05/13/2018 21:42:52 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `doctor_account`
-- ----------------------------
DROP TABLE IF EXISTS `doctor_account`;
CREATE TABLE `doctor_account` (
  `doctor_account_id` varchar(20) NOT NULL,
  `doctor_name` varchar(10) NOT NULL,
  `department` varchar(10) NOT NULL,
  `status` varchar(5) DEFAULT '0',
  PRIMARY KEY (`doctor_account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `doctor_account`
-- ----------------------------
BEGIN;
INSERT INTO `doctor_account` VALUES ('1', '陈晓恒', '妇科', '0');
COMMIT;

-- ----------------------------
--  Table structure for `medical_case`
-- ----------------------------
DROP TABLE IF EXISTS `medical_case`;
CREATE TABLE `medical_case` (
  `case_id` varchar(10) NOT NULL,
  `patient_account_id` varchar(20) NOT NULL,
  `doctor_account_id` varchar(20) NOT NULL,
  `file_path` varchar(50) NOT NULL,
  `status` varchar(10) NOT NULL DEFAULT '0' COMMENT '当服务器接收到返回的病历单号，将此项设置为true',
  PRIMARY KEY (`case_id`),
  KEY `patient account_id` (`patient_account_id`),
  KEY `doctor_account_id` (`doctor_account_id`),
  CONSTRAINT `medical_case_ibfk_1` FOREIGN KEY (`patient_account_id`) REFERENCES `patient_account` (`patient_account_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `medical_case_ibfk_2` FOREIGN KEY (`doctor_account_id`) REFERENCES `doctor_account` (`doctor_account_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `medical_case`
-- ----------------------------
BEGIN;
INSERT INTO `medical_case` VALUES ('1', '1', '1', 'nofile', '0');
COMMIT;

-- ----------------------------
--  Table structure for `node`
-- ----------------------------
DROP TABLE IF EXISTS `node`;
CREATE TABLE `node` (
  `node_id` int(5) NOT NULL,
  `model_id` varchar(5) CHARACTER SET latin1 NOT NULL,
  `node_type` varchar(10) CHARACTER SET latin1 NOT NULL,
  `content` varchar(50) NOT NULL,
  PRIMARY KEY (`node_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `node`
-- ----------------------------
BEGIN;
INSERT INTO `node` VALUES ('0', '1', 'question', '月经周期'), ('1', '1', 'answer', '长'), ('2', '1', 'answer', '中'), ('3', '1', 'answer', '短'), ('4', '1', 'question', '白带量'), ('5', '1', 'answer', '一般'), ('6', '1', 'answer', '很多'), ('7', '1', 'question', '白带颜色'), ('8', '1', 'answer', '灰黄色'), ('9', '1', 'answer', '白色'), ('10', '1', 'answer', '黄绿色'), ('11', '1', 'answer', '红色或淡红色');
COMMIT;

-- ----------------------------
--  Table structure for `patient_account`
-- ----------------------------
DROP TABLE IF EXISTS `patient_account`;
CREATE TABLE `patient_account` (
  `patient_account_id` varchar(20) NOT NULL,
  `patient_name` varchar(10) NOT NULL,
  `patient_phone_number` varchar(11) NOT NULL,
  `patient_identification_id` varchar(18) NOT NULL,
  PRIMARY KEY (`patient_account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `patient_account`
-- ----------------------------
BEGIN;
INSERT INTO `patient_account` VALUES ('1', '张红汗', '111222333', '123456789012345678');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
