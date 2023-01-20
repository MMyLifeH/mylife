/*
SQLyog v10.2 
MySQL - 5.5.20 : Database - om
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`om` /*!40100 DEFAULT CHARACTER SET gbk */;

USE `om`;

/*Table structure for table `auth` */

DROP TABLE IF EXISTS `auth`;

CREATE TABLE `auth` (
  `auth_token` varchar(50) NOT NULL,
  `auth_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `auth_ip` varchar(50) NOT NULL,
  `auth_address` varchar(50) NOT NULL,
  `auth_state` tinyint(4) NOT NULL DEFAULT '0',
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `auth` */

insert  into `auth`(`auth_token`,`auth_time`,`auth_ip`,`auth_address`,`auth_state`,`user_id`) values ('b8b51069f3cc43c1b53f82969e818b89','0000-00-00 00:00:00','0:0:0:0:0:0:0:1','山东省淄博市',0,NULL),('1d3f02976615481b948c68184d5903ba','0000-00-00 00:00:00','0:0:0:0:0:0:0:1','山东省淄博市',0,NULL),('1000134b921e4a8299b0097ae38b2394','0000-00-00 00:00:00','0:0:0:0:0:0:0:1','山东省淄博市',0,NULL),('3bb70840b5f74c4890a938026ca2c28c','0000-00-00 00:00:00','0:0:0:0:0:0:0:1','山东省淄博市',0,NULL),('edcc52a80c04498da23783d2908087f2','0000-00-00 00:00:00','0:0:0:0:0:0:0:1','山东省淄博市',0,NULL),('046bea8463b347998ffe47dcd5c9bad4','0000-00-00 00:00:00','0:0:0:0:0:0:0:1','山东省淄博市',0,NULL),('02c8a6e373ff4db080a78853f7f4d31f','0000-00-00 00:00:00','0:0:0:0:0:0:0:1','山东省淄博市',0,NULL),('243977adf34547cb8e298be82ce6989c','0000-00-00 00:00:00','0:0:0:0:0:0:0:1','山东省淄博市',0,NULL),('347a7ffadf3c4ccbbf110363f8123e2e','0000-00-00 00:00:00','0:0:0:0:0:0:0:1','山东省淄博市',0,NULL),('e473162324064c299824cfbb261462d4','0000-00-00 00:00:00','0:0:0:0:0:0:0:1','山东省淄博市',0,NULL),('b01901cf73e044eea60367987a0f21de','0000-00-00 00:00:00','0:0:0:0:0:0:0:1','山东省淄博市',0,NULL),('14fe61ee4bd9419587b182aef416a5af','0000-00-00 00:00:00','0:0:0:0:0:0:0:1','山东省淄博市',0,NULL),('f5e83a99611945e7aa09f54c712e2263','0000-00-00 00:00:00','0:0:0:0:0:0:0:1','山东省淄博市',0,NULL),('d0c7b255cb4947dba7d42a3467f76d39','0000-00-00 00:00:00','0:0:0:0:0:0:0:1','山东省淄博市',0,NULL),('9504572a61c34e35a8fc24fd19bfdda3','0000-00-00 00:00:00','0:0:0:0:0:0:0:1','山东省淄博市',0,NULL),('9394b0f0b4b54a29a0283ad5c5d20640','0000-00-00 00:00:00','0:0:0:0:0:0:0:1','山东省淄博市',0,NULL),('f90d6b2173dd48f49bc91dbde5281d5a','0000-00-00 00:00:00','0:0:0:0:0:0:0:1','山东省淄博市',0,NULL);

/*Table structure for table `reviews` */

DROP TABLE IF EXISTS `reviews`;

CREATE TABLE `reviews` (
  `rv_id` int(17) NOT NULL AUTO_INCREMENT COMMENT '自动增长',
  `rv_content` varchar(200) DEFAULT NULL COMMENT '车辆评论',
  `re_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`rv_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*Data for the table `reviews` */

insert  into `reviews`(`rv_id`,`rv_content`,`re_date`) values (1,'非常认可','2022-01-22 15:00:00'),(4,'价格合理','2022-02-22 04:18:52'),(5,'车盘受损','2022-02-25 08:18:52'),(6,'平台透明','2022-03-20 15:18:52'),(7,'实时交易','2022-04-12 18:18:52'),(8,'可以线下沟通','2022-04-25 10:18:52'),(9,'交易方式安全','2022-04-27 17:18:52'),(11,'每次都会参考价格','2022-04-29 12:19:48'),(12,'很好的平台','2022-05-06 12:20:13'),(14,'购买多次','2022-05-15 14:19:50'),(15,'没有中间商赚取差价','2022-05-21 21:19:50'),(16,'单批出售','2022-05-28 14:19:50'),(18,'出售价格合理','2022-05-28 18:08:12'),(19,'推荐朋友购买','2022-06-10 16:41:25'),(20,'平台资源丰富','2022-06-16 09:54:37'),(22,'地区定位准确','2022-06-19 11:18:01'),(23,'品牌全','2022-06-21 16:32:43'),(24,'是完全的','2022-06-21 05:48:00'),(25,'个人出售','2022-06-23 18:53:48'),(26,'应有尽有','2022-06-23 14:01:20'),(27,'各式各样','2022-06-25 12:50:11'),(28,'进口车辆完好','2022-06-24 12:06:45'),(29,'很喜欢的的','2022-06-24 12:17:13'),(31,'多数喜欢','2022-06-24 17:15:18'),(32,'架构多级','2022-06-24 20:15:40'),(33,'客服人性化','2022-06-24 22:21:33'),(34,'处理及时','2022-06-24 22:22:07'),(35,'没有特殊情况','2022-06-24 23:23:27');

/*Table structure for table `tb_orders` */

DROP TABLE IF EXISTS `tb_orders`;

CREATE TABLE `tb_orders` (
  `or_id` int(17) NOT NULL AUTO_INCREMENT COMMENT '订单编号',
  `tradetime` date NOT NULL COMMENT '交易时间',
  `ordermoney` float DEFAULT NULL COMMENT '订单金额',
  `ordernote` varchar(200) DEFAULT NULL COMMENT '订单备注',
  `Vehicleid` int(17) NOT NULL,
  PRIMARY KEY (`or_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*Data for the table `tb_orders` */

insert  into `tb_orders`(`or_id`,`tradetime`,`ordermoney`,`ordernote`,`Vehicleid`) values (1,'2022-06-20',11.25,'',0),(3,'2022-06-20',11.88,'线下详谈',0),(4,'2022-06-22',15.55,'线上沟通',0),(5,'2022-06-22',58.36,'合同缺少',0),(8,'2022-06-26',28.88,'成功交易',0),(13,'2022-01-13',88.2,'',0),(14,'2022-02-17',77,'平台介绍',0),(15,'2022-02-10',56,'平台人员',0),(16,'2022-03-09',56.75,'车主',0),(17,'2022-03-09',65.75,'货运',0),(18,'2022-03-22',75.67,'海运',0),(19,'2022-06-25',66.66,'定价',0),(20,'2022-04-14',45.45,'合同价格',0),(21,'2022-04-05',31,'单批',0),(22,'2022-04-07',21,'单批',0),(23,'2022-04-19',32,'单批',0),(25,'2022-06-24',54,'单批',0),(26,'2022-05-12',23.21,'私主',0),(28,'2022-06-23',33.2,'雇主',6),(29,'2022-06-23',11.1,'个人',0),(30,'2022-06-23',12,'公家',1),(31,'2022-06-24',29.69,'',6),(32,'2022-06-24',221,'',17),(33,'2022-06-24',46.35,'12',17),(37,'2022-06-16',85.35,'空运',0),(38,'2022-06-24',22.22,'协商',0),(39,'2022-06-23',5.4,'mini',0);

/*Table structure for table `tb_user` */

DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user` (
  `u_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '自动增长',
  `u_name` varchar(20) NOT NULL COMMENT '用户名',
  `u_pwd` varchar(20) NOT NULL COMMENT '密码',
  `u_phone` varchar(20) NOT NULL COMMENT '电话',
  `u_email` varchar(20) NOT NULL COMMENT '注册邮箱',
  `u_address` varchar(20) NOT NULL COMMENT '地址',
  `u_avatar` varchar(20) DEFAULT NULL COMMENT '登录状态',
  `u_role` int(20) DEFAULT NULL COMMENT '角色权限',
  PRIMARY KEY (`u_id`),
  UNIQUE KEY `tb_user_u_name_uindex` (`u_name`),
  UNIQUE KEY `tb_user_u_pwd_uindex` (`u_pwd`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=gbk;

/*Data for the table `tb_user` */

insert  into `tb_user`(`u_id`,`u_name`,`u_pwd`,`u_phone`,`u_email`,`u_address`,`u_avatar`,`u_role`) values (1,'1','1','1','1','1','1',1),(2,'2','2','2','2','2','2',0);

/*Table structure for table `vehicle` */

DROP TABLE IF EXISTS `vehicle`;

CREATE TABLE `vehicle` (
  `Vehicleid` int(17) NOT NULL AUTO_INCREMENT,
  `Vehiclename` varchar(20) NOT NULL,
  `Vehicleprice` float NOT NULL,
  `Vehiclecon` varchar(200) NOT NULL,
  `Shoppingstate` tinyint(1) NOT NULL DEFAULT '0',
  `Collectstate` tinyint(1) DEFAULT '0',
  `pic` varchar(225) DEFAULT NULL,
  PRIMARY KEY (`Vehicleid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*Data for the table `vehicle` */

insert  into `vehicle`(`Vehicleid`,`Vehiclename`,`Vehicleprice`,`Vehiclecon`,`Shoppingstate`,`Collectstate`,`pic`) values (19,'斯柯达 昕锐 2013款',4.14,'上牌时间 ：2014-06  表显里程：15.2万公里  排量 ：1.6L   变速箱：自动',1,0,'/pics/cdc88d171a8142419a8504b6f8b80d42.jpg'),(20,'吉利汽车 帝豪EV 2016款',4.9,'上牌时间：2016-07 表显里程：5.9万公里',1,1,'/pics/713788ed101c4117a6901d1a54137a2c.jpg'),(21,'比亚迪 秦PLUS 2021款',10.88,'上牌时间：2021-04 表显里程：0.9万公里   排量 ：1.5L 变速箱：自动',1,1,'/pics/3e0eaf87aabf481f9c144682aefd9032.jpg'),(22,'奥迪A6L 2016款',26.66,'上牌时间 ：2016-09 表显里程： 7.6万公里 排量 ：3.0L 变速箱：自动',0,0,'/pics/474e78b68522460c972e123c885513a2.jpg'),(23,'特斯拉 Model 3 2021款',25.96,'上牌时间 ：2021-10 表显里程： 0.1万公里 ',0,1,'/pics/fd147342c886493892b35ca1666e08e6.jpg'),(24,'路虎 神行者2 2015款',14.68,'上牌时间 ：2014-10 表显里程： 7.3万公里 排量 ：2.0T 变速箱：自动',1,0,'/pics/6d7bd4322e3f4ebeb327b2ee5756ae2e.jpg'),(25,'奔驰CLA级 2015款',18.88,'上牌时间 ：2015-08  表显里程： 7.1万公里  排量 ：1.6T 变速箱：自动',0,0,'/pics/8f4df7cd08df4d5ea64d9806a64aae34.jpg'),(26,'五菱汽车 宏光MINI EV 2021款',3.98,'上牌时间 ：2021-06 表显里程： 1.4万公里 ',0,0,'/pics/52a2b1c638ae422f815299ee5a577e10.jpg'),(27,'保时捷Macan 2020款',55.6,'上牌时间 ：2020-10 表显里程： 1.7万公里 排量 ：2.0T 变速箱：自动',1,0,'/pics/0998c5c1e2d346869317bee5418759ac.jpg'),(28,'丰田 卡罗拉 2021款',10.38,'上牌时间 ：2021-04 表显里程：0.5万公里 排量 ：1.2T 变速箱：自动',0,1,'/pics/5f820a2763f84105a12ea2352db123c5.jpg'),(29,'林肯MKZ 2018款',15.56,'上牌时间 ：2019-04 表显里程： 5.0万公里 排量 ：2.0T 变速箱：自动',1,1,'/pics/ac090679045d435c973c09f89442312d.jpg'),(30,'宝马X1新能源 2021款',26.08,'上牌时间 ：2021-05 表显里程： 0.8万公里 排量 ：1.5T 变速箱：自动',1,0,'/pics/d0f2602e57be48b997321692dde1fc42.jpg');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
