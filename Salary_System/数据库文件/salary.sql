/*
SQLyog v10.2 
MySQL - 5.7.35 : Database - smanage
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`smanage` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `smanage`;

/*Table structure for table `reimburse` */

DROP TABLE IF EXISTS `reimburse`;

CREATE TABLE `reimburse` (
  `rid` int(11) NOT NULL AUTO_INCREMENT COMMENT '报销单id',
  `office` int(11) DEFAULT '0' COMMENT '办公用品费',
  `room` int(11) DEFAULT '0' COMMENT '住宿费',
  `eat` int(11) DEFAULT '0' COMMENT '餐补费',
  `gasoline` int(11) DEFAULT '0' COMMENT '加油费',
  `state_pass` tinyint(1) NOT NULL DEFAULT '0' COMMENT '通过状态',
  `r_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `sid` int(11) NOT NULL,
  PRIMARY KEY (`rid`),
  KEY `reimburse_salarys_sid_fk` (`sid`),
  CONSTRAINT `reimburse_salarys_sid_fk` FOREIGN KEY (`sid`) REFERENCES `salarys` (`sid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Table structure for table `salarys` */

DROP TABLE IF EXISTS `salarys`;

CREATE TABLE `salarys` (
  `sid` int(11) NOT NULL AUTO_INCREMENT COMMENT '工资条id',
  `wage` int(11) NOT NULL DEFAULT '0' COMMENT '基本工资',
  `welfare` int(11) NOT NULL DEFAULT '0' COMMENT '奖金',
  `reimburse` int(11) NOT NULL DEFAULT '0' COMMENT '报销',
  `s_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `u_id` int(11) NOT NULL,
  PRIMARY KEY (`sid`),
  UNIQUE KEY `salarys_sid_uindex` (`sid`),
  KEY `salarys_users_u_id_fk` (`u_id`),
  CONSTRAINT `salarys_users_u_id_fk` FOREIGN KEY (`u_id`) REFERENCES `users` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `u_id` int(11) NOT NULL COMMENT '员工id',
  `u_name` varchar(20) NOT NULL COMMENT '员工姓名',
  `u_pwd` varchar(16) NOT NULL COMMENT '员工密码',
  `u_sex` varchar(4) NOT NULL COMMENT '员工性别',
  `u_salary` int(11) DEFAULT NULL COMMENT '总收入',
  `u_position` varchar(50) NOT NULL COMMENT '员工岗位',
  `u_depart` varchar(50) NOT NULL COMMENT '员工所属部门',
  `u_state` tinyint(1) NOT NULL DEFAULT '0',
  `u_role` int(11) NOT NULL COMMENT '用户权限',
  PRIMARY KEY (`u_id`),
  UNIQUE KEY `users_u_id_uindex` (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
