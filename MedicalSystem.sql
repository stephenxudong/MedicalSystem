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

 Date: 05/08/2018 09:59:51 AM
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
--  Table structure for `medical_case`
-- ----------------------------
DROP TABLE IF EXISTS `medical_case`;
CREATE TABLE `medical_case` (
  `patient_account_id` varchar(20) NOT NULL,
  `doctor_account_id` varchar(20) NOT NULL,
  `file_path` varchar(50) NOT NULL,
  `case_id` varchar(10) NOT NULL,
  PRIMARY KEY (`case_id`),
  KEY `patient account_id` (`patient_account_id`),
  KEY `doctor_account_id` (`doctor_account_id`),
  CONSTRAINT `medical_case_ibfk_1` FOREIGN KEY (`patient_account_id`) REFERENCES `patient_account` (`patient_account_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `medical_case_ibfk_2` FOREIGN KEY (`doctor_account_id`) REFERENCES `doctor_account` (`doctor_account_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `model_node`
-- ----------------------------
DROP TABLE IF EXISTS `model_node`;
CREATE TABLE `model_node` (
  `model_id` varchar(5) NOT NULL,
  `node_id` int(11) NOT NULL,
  `question_content` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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

SET FOREIGN_KEY_CHECKS = 1;
