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

 Date: 05/10/2018 20:49:11 PM
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
  PRIMARY KEY (`doctor_account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `doctor_account`
-- ----------------------------
BEGIN;
INSERT INTO `doctor_account` VALUES ('1', '陈晓恒', '妇科');
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
INSERT INTO `medical_case` VALUES ('1', '1', '1', 'nofile');
COMMIT;

-- ----------------------------
--  Table structure for `model_node`
-- ----------------------------
DROP TABLE IF EXISTS `model_node`;
CREATE TABLE `model_node` (
  `model_id` varchar(5) NOT NULL,
  `node_id` int(11) NOT NULL,
  `question_content` varchar(255) NOT NULL,
  `question_type` varchar(10) NOT NULL,
  `answer_type` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`node_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `model_node`
-- ----------------------------
BEGIN;
INSERT INTO `model_node` VALUES ('1', '1', '白带量', 'normal', 'single_selection'), ('1', '2', '白带颜色', 'normal', 'single_selection'), ('1', '3', '白带形状', 'normal', 'single_selection'), ('1', '4', '白带异味', 'normal', 'single_selection'), ('1', '5', '外阴瘙痒', 'normal', 'single_selection'), ('1', '6', '外阴疼痛', 'normal', 'single_selection'), ('1', '7', '尿痛', 'normal', 'single_selection'), ('1', '8', '性交痛', 'normal', 'single_selection');
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

-- ----------------------------
--  Table structure for `question_answer`
-- ----------------------------
DROP TABLE IF EXISTS `question_answer`;
CREATE TABLE `question_answer` (
  `node_id` int(11) NOT NULL,
  `answer` varchar(100) NOT NULL,
  KEY `node_id` (`node_id`),
  CONSTRAINT `question_answer_ibfk_1` FOREIGN KEY (`node_id`) REFERENCES `model_node` (`node_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `question_answer`
-- ----------------------------
BEGIN;
INSERT INTO `question_answer` VALUES ('1', '多'), ('1', '中'), ('1', '少'), ('2', '灰黄色'), ('2', '白色'), ('2', '灰白色'), ('2', '透明'), ('2', '黄绿色'), ('2', '红色或者淡红色'), ('2', '淡黄色'), ('3', '泡沫状'), ('3', '凝乳状'), ('3', '匀质'), ('3', '稠厚'), ('3', '泔水样'), ('3', '稀薄'), ('4', '重'), ('4', '中'), ('4', '轻'), ('5', '重'), ('5', '中'), ('5', '轻'), ('6', '有'), ('6', '无'), ('7', '有'), ('7', '无'), ('8', '有'), ('8', '无');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
