/*
Navicat MySQL Data Transfer

Source Server         : mysqlLocal
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : firecontrol_db

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2020-02-04 21:07:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `dept_id` varchar(50) COLLATE utf8_bin NOT NULL,
  `dept_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `dept_address` varchar(150) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of dept
-- ----------------------------

-- ----------------------------
-- Table structure for dept_user
-- ----------------------------
DROP TABLE IF EXISTS `dept_user`;
CREATE TABLE `dept_user` (
  `user_id` varchar(50) COLLATE utf8_bin NOT NULL,
  `dept_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `login_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `login_pwd` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `dept_id` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `user_Phone` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FKD6AFC985755CB5A5` (`dept_id`),
  CONSTRAINT `FKD6AFC985755CB5A5` FOREIGN KEY (`dept_id`) REFERENCES `daoa_db`.`dept` (`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of dept_user
-- ----------------------------

-- ----------------------------
-- Table structure for facility
-- ----------------------------
DROP TABLE IF EXISTS `facility`;
CREATE TABLE `facility` (
  `fac_id` varchar(50) COLLATE utf8_bin NOT NULL,
  `fac_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `fac_time` datetime DEFAULT NULL,
  `fac_address` varchar(150) COLLATE utf8_bin DEFAULT NULL,
  `fac_statuc` int(11) DEFAULT NULL,
  `fac_bind` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `warn_id` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `ins_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`fac_id`),
  KEY `FK1DDE6EA3BB097170` (`warn_id`),
  KEY `FK1DDE6EA3C5B1ADD5` (`user_id`),
  KEY `FK1DDE6EA3455B9ECC` (`ins_id`),
  CONSTRAINT `FK1DDE6EA3455B9ECC` FOREIGN KEY (`ins_id`) REFERENCES `install_point` (`ins_id`),
  CONSTRAINT `FK1DDE6EA3BB097170` FOREIGN KEY (`warn_id`) REFERENCES `warn_user` (`user_id`),
  CONSTRAINT `FK1DDE6EA3C5B1ADD5` FOREIGN KEY (`user_id`) REFERENCES `sys_users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of facility
-- ----------------------------

-- ----------------------------
-- Table structure for facility_warn
-- ----------------------------
DROP TABLE IF EXISTS `facility_warn`;
CREATE TABLE `facility_warn` (
  `warn_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `warn_time` datetime DEFAULT NULL,
  `fac_id` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`warn_id`),
  KEY `FKAD8E8AA22BCEC0E0` (`fac_id`),
  CONSTRAINT `FKAD8E8AA22BCEC0E0` FOREIGN KEY (`fac_id`) REFERENCES `facility` (`fac_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of facility_warn
-- ----------------------------

-- ----------------------------
-- Table structure for install_point
-- ----------------------------
DROP TABLE IF EXISTS `install_point`;
CREATE TABLE `install_point` (
  `ins_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ins_code` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `ins_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `ins_address` varchar(150) COLLATE utf8_bin DEFAULT NULL,
  `as_id` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ins_id`),
  KEY `FKB09EFDECB060F079` (`as_id`),
  CONSTRAINT `FKB09EFDECB060F079` FOREIGN KEY (`as_id`) REFERENCES `manage_area_second` (`as_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of install_point
-- ----------------------------

-- ----------------------------
-- Table structure for manage_area_first
-- ----------------------------
DROP TABLE IF EXISTS `manage_area_first`;
CREATE TABLE `manage_area_first` (
  `af_id` varchar(50) COLLATE utf8_bin NOT NULL,
  `af_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `uf_id` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `dept_id` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`af_id`),
  KEY `FK72A5BF78755CB5A5` (`dept_id`),
  CONSTRAINT `FK72A5BF78755CB5A5` FOREIGN KEY (`dept_id`) REFERENCES `daoa_db`.`dept` (`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of manage_area_first
-- ----------------------------

-- ----------------------------
-- Table structure for manage_area_second
-- ----------------------------
DROP TABLE IF EXISTS `manage_area_second`;
CREATE TABLE `manage_area_second` (
  `as_id` varchar(50) COLLATE utf8_bin NOT NULL,
  `as_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `us_id` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `af_id` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`as_id`),
  KEY `FKF801F0ACAF7D92C8` (`af_id`),
  CONSTRAINT `FKF801F0ACAF7D92C8` FOREIGN KEY (`af_id`) REFERENCES `manage_area_first` (`af_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of manage_area_second
-- ----------------------------

-- ----------------------------
-- Table structure for manage_user_first
-- ----------------------------
DROP TABLE IF EXISTS `manage_user_first`;
CREATE TABLE `manage_user_first` (
  `uf_id` varchar(50) COLLATE utf8_bin NOT NULL,
  `uf_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `login_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `login_pwd` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `uf_phone` bigint(20) DEFAULT NULL,
  `uf_type` int(11) DEFAULT NULL,
  `user_id` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `af_id` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`uf_id`),
  KEY `FK9FF0F436AF7D92C8` (`af_id`),
  KEY `FK9FF0F4368A0C4A4A` (`user_id`),
  CONSTRAINT `FK9FF0F4368A0C4A4A` FOREIGN KEY (`user_id`) REFERENCES `dept_user` (`user_id`),
  CONSTRAINT `FK9FF0F436AF7D92C8` FOREIGN KEY (`af_id`) REFERENCES `manage_area_first` (`af_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of manage_user_first
-- ----------------------------

-- ----------------------------
-- Table structure for manage_user_second
-- ----------------------------
DROP TABLE IF EXISTS `manage_user_second`;
CREATE TABLE `manage_user_second` (
  `us_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `us_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `login_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `login_pwd` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `us_phone` bigint(20) DEFAULT NULL,
  `us_type` int(11) DEFAULT NULL,
  `as_id` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `uf_id` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`us_id`),
  KEY `FK741D53AEB060F079` (`as_id`),
  KEY `FK741D53AECAD3A45E` (`uf_id`),
  CONSTRAINT `FK741D53AECAD3A45E` FOREIGN KEY (`uf_id`) REFERENCES `manage_user_first` (`uf_id`),
  CONSTRAINT `FK741D53AEB060F079` FOREIGN KEY (`as_id`) REFERENCES `manage_area_second` (`as_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of manage_user_second
-- ----------------------------

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `fac_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `log_code` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `log_content` varchar(150) COLLATE utf8_bin DEFAULT NULL,
  `log_type` int(11) DEFAULT NULL,
  `log_time` datetime DEFAULT NULL,
  `entity_id` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`fac_id`),
  KEY `FKFBA0FAB2C5B1ADD5` (`user_id`),
  CONSTRAINT `FKFBA0FAB2C5B1ADD5` FOREIGN KEY (`user_id`) REFERENCES `sys_users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_users
-- ----------------------------
DROP TABLE IF EXISTS `sys_users`;
CREATE TABLE `sys_users` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `login_pwd` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `user_code` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `user_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `user_phone` bigint(20) DEFAULT NULL,
  `user_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sys_users
-- ----------------------------
INSERT INTO `sys_users` VALUES ('1', 'admin', 'admin', null, null, null, null);
INSERT INTO `sys_users` VALUES ('2', '2222', '2222', '20200204160133368466', '22', '12333333333', '6');
INSERT INTO `sys_users` VALUES ('3', '3333', '3333', '20200204160147247893', '33', '12333333333', '2');
INSERT INTO `sys_users` VALUES ('4', '4444', '4444', '20200204160155932271', '44', '12333333333', '3');
INSERT INTO `sys_users` VALUES ('5', '55555', '55555', '20200204160206262883', '55', '12333333333', '4');
INSERT INTO `sys_users` VALUES ('6', '6666', '6666', '20200204160258908615', '66', '12333333333', '5');
INSERT INTO `sys_users` VALUES ('7', '7777', '7777', '20200204160309654952', '77', '12333333333', '5');

-- ----------------------------
-- Table structure for warn_user
-- ----------------------------
DROP TABLE IF EXISTS `warn_user`;
CREATE TABLE `warn_user` (
  `user_id` varchar(50) COLLATE utf8_bin NOT NULL,
  `user_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `login_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `login_pwd` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `user_phone` bigint(20) DEFAULT NULL,
  `point` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FK97CEB5E492494950` (`point`),
  CONSTRAINT `FK97CEB5E492494950` FOREIGN KEY (`point`) REFERENCES `install_point` (`ins_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of warn_user
-- ----------------------------
