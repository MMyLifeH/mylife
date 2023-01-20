/*
SQLyog v10.2 
MySQL - 5.5.20 : Database - Phone
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`Phone` /*!40100 DEFAULT CHARACTER SET gbk */;

USE `Phone`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `adminname` varchar(20) NOT NULL,
  `adminpass` char(50) NOT NULL,
  `role` int(11) DEFAULT '0',
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=gbk;

/*Data for the table `admin` */

insert  into `admin`(`aid`,`adminname`,`adminpass`,`role`) values (1,'王家宁','25d55ad283aa400af464c76d713c07ad',0),(2,'焉佳馨','123456',0),(3,'马豪','14e1b600b1fd579f47433b88e8d85291',0);

/*Table structure for table `human` */

DROP TABLE IF EXISTS `human`;

CREATE TABLE `human` (
  `phid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `sex` char(2) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `number` char(11) DEFAULT NULL,
  `familyaddress` varchar(50) DEFAULT NULL,
  `birthday` varchar(20) DEFAULT NULL,
  `pid` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`phid`),
  KEY `FK_Relationship_1` (`pid`),
  CONSTRAINT `FK_Relationship_1` FOREIGN KEY (`pid`) REFERENCES `phone` (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=gbk;

/*Data for the table `human` */

insert  into `human`(`phid`,`name`,`sex`,`email`,`number`,`familyaddress`,`birthday`,`pid`) values (1,'妈妈','女','123132121@qq.com','131546432','更换手机卡打开几万块','12.12','1'),(2,'爸爸','男','131546212@163.com','1324654613','挖回答我看见大家看我','13.14','2'),(3,'王家宁','男','132154654@qq.com','1314654665','撒娇的骄傲快点玩','11.11','3'),(4,'马豪','男','134564@131.com','13451344654','贵州省安居客我的骄傲看外界','12.12','1'),(5,'焉佳馨','男','132154654@qq.com','1314654665','撒娇的骄傲快点玩','11.11','5'),(6,'张广学','男','1345611@qq.com','12345678910','大家玩的吉奥文科扩大','11.11','5'),(7,'王老板','男','1345611@qq.com','12345678910','撒大苏打阿三大苏打','11.11','4'),(8,'刘经理','男','1345611@qq.com','12345678910','萨达萨达萨达是','11.11','4');

/*Table structure for table `phone` */

DROP TABLE IF EXISTS `phone`;

CREATE TABLE `phone` (
  `pid` varchar(20) NOT NULL,
  `phonename` varchar(100) NOT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `phone` */

insert  into `phone`(`pid`,`phonename`) values ('1','家人'),('2','朋友'),('3','亲戚'),('4','同事'),('5','同学'),('6','兄弟');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
