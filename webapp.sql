# Host: localhost  (Version: 5.7.26)
# Date: 2022-12-30 20:49:26
# Generator: MySQL-Front 5.3  (Build 4.234)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "announcement"
#

DROP TABLE IF EXISTS `announcement`;
CREATE TABLE `announcement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `a_create` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `a_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

#
# Data for table "announcement"
#

/*!40000 ALTER TABLE `announcement` DISABLE KEYS */;
INSERT INTO `announcement` VALUES (2,'测试3','测试4','2022-12-12 16:02:44','2022-12-28 21:16:44'),(3,'放假通知','今天放假了','2022-12-21 16:11:54','2022-12-21 16:11:54');
/*!40000 ALTER TABLE `announcement` ENABLE KEYS */;

#
# Structure for table "attendance"
#

DROP TABLE IF EXISTS `attendance`;
CREATE TABLE `attendance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `sid` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

#
# Data for table "attendance"
#

/*!40000 ALTER TABLE `attendance` DISABLE KEYS */;
INSERT INTO `attendance` VALUES (45,'小红','1902','早班打卡','2022-12-28 20:24'),(48,'测试员工2','0000','早班打卡','2022-12-28 21:12'),(49,'测试员工2','0000','加班打卡','2022-12-28 21:12');
/*!40000 ALTER TABLE `attendance` ENABLE KEYS */;

#
# Structure for table "department"
#

DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `department` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

#
# Data for table "department"
#

/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'技术部'),(2,'销售部'),(3,'设计部'),(4,'财务部'),(5,'客服部'),(7,'测试部门1');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;

#
# Structure for table "duser"
#

DROP TABLE IF EXISTS `duser`;
CREATE TABLE `duser` (
  `pk_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT '',
  `pwd` varchar(255) DEFAULT '',
  `email` varchar(255) DEFAULT '',
  `d_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `d_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`pk_id`)
) ENGINE=MyISAM AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

#
# Data for table "duser"
#

/*!40000 ALTER TABLE `duser` DISABLE KEYS */;
INSERT INTO `duser` VALUES (21,'123','202cb962ac59075b964b07152d234b70','17@qq.com','2022-12-01 12:20:04','2022-12-01 16:48:28'),(22,'1234','fcea920f7412b5da7be0cf42b8c93759','17@qq.com','2022-12-01 12:21:59','2022-12-01 16:48:30'),(23,'12345','fcea920f7412b5da7be0cf42b8c93759','17@qq.com','2022-12-01 12:22:05','2022-12-01 16:48:31'),(24,'123456','fcea920f7412b5da7be0cf42b8c93759','17@qq.com','2022-12-01 12:22:11','2022-12-01 16:48:33'),(25,'1234568','fcea920f7412b5da7be0cf42b8c93759','17@qq.com','2022-12-01 12:22:16','2022-12-01 16:48:47'),(27,'0000','fcea920f7412b5da7be0cf42b8c93759','228146@qq.com','2022-12-06 21:03:29','2022-12-06 21:03:29');
/*!40000 ALTER TABLE `duser` ENABLE KEYS */;

#
# Structure for table "employee"
#

DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `u_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT '',
  `age` int(11) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `sid` varchar(11) DEFAULT NULL,
  `spwd` varchar(255) DEFAULT NULL,
  `position` varchar(255) DEFAULT '员工',
  `department` varchar(255) DEFAULT NULL,
  `salary` varchar(255) DEFAULT NULL,
  `s_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `s_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`u_id`)
) ENGINE=MyISAM AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

#
# Data for table "employee"
#

/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (16,'测试员工2',21,'先生','15','0000','fcea920f7412b5da7be0cf42b8c93759','经理','技术部','8000','2022-12-06 23:34:29','2022-12-28 22:47:00'),(18,'小红',19,'女士','113','1902','fcea920f7412b5da7be0cf42b8c93759','员工','设计部','5500','2022-12-08 16:04:43','2022-12-28 20:18:56'),(20,'张三',22,'先生','13','1903','fcea920f7412b5da7be0cf42b8c93759','员工','销售部','4000','2022-12-08 16:04:23','2022-12-28 20:18:49'),(21,'李四',61,'先生','11','1904','fcea920f7412b5da7be0cf42b8c93759','员工','设计部','4000','2022-12-08 16:08:36','2022-12-21 17:07:29'),(22,'张五',31,'先生','11','1905','fcea920f7412b5da7be0cf42b8c93759','客服专员','客服部','5600','2022-12-08 16:12:18','2022-12-21 17:07:23'),(23,'aaa',19,'先生','11','ceshi6','fcea920f7412b5da7be0cf42b8c93759','员工',NULL,NULL,'2022-12-08 16:12:48','2022-12-08 16:12:48'),(30,'13',39,'先生','12','ceshi13','fcea920f7412b5da7be0cf42b8c93759','销售',NULL,'6000','2022-12-08 16:27:34','2022-12-08 16:27:34'),(36,'测试9',19,'先生','12','ceshi9','fcea920f7412b5da7be0cf42b8c93759','员工',NULL,'3000','2022-12-12 14:10:47','2022-12-12 14:10:47'),(37,'测试20',19,'先生','','ceshi20','fcea920f7412b5da7be0cf42b8c93759','员工',NULL,'3000','2022-12-12 19:57:08','2022-12-12 19:57:08'),(38,'测试21',1234567,'女士','123','ceshi21','fcea920f7412b5da7be0cf42b8c93759','员工','技术部','5000','2022-12-12 20:00:59','2022-12-27 22:16:29'),(39,'测试22',19,'先生','12','ceshi22','fcea920f7412b5da7be0cf42b8c93759','员工',NULL,'3000','2022-12-12 23:46:10','2022-12-12 23:46:10'),(40,'测试23',19,'先生','','ceshi23','fcea920f7412b5da7be0cf42b8c93759','员工',NULL,'','2022-12-12 23:47:50','2022-12-12 23:47:50'),(42,'测试24',19,'先生','123','ceshi24','fcea920f7412b5da7be0cf42b8c93759','技术员',' ','5000','2022-12-21 15:55:56','2022-12-21 15:55:56'),(43,'测试25',20,'先生','12','ceshi25','fcea920f7412b5da7be0cf42b8c93759','销售员','销售部','6000','2022-12-21 15:59:57','2022-12-21 15:59:57'),(46,'测试27',19,'先生','123','ceshi27','fcea920f7412b5da7be0cf42b8c93759','员工','技术部','3000','2022-12-21 16:25:14','2022-12-27 22:16:29'),(56,'赵六',19,'先生','11','1906','fcea920f7412b5da7be0cf42b8c93759','员工','测试部门1','3000','2022-12-28 22:20:19','2022-12-28 22:20:47');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;

#
# Structure for table "leave1"
#

DROP TABLE IF EXISTS `leave1`;
CREATE TABLE `leave1` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `sid` varchar(255) DEFAULT NULL,
  `cause` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

#
# Data for table "leave1"
#

/*!40000 ALTER TABLE `leave1` DISABLE KEYS */;
INSERT INTO `leave1` VALUES (46,'测试员工2','0000','事假','2022-12-28 20:39到2022-12-29 20:39','驳回'),(47,'测试员工2','0000','病假','2022-12-29 20:40到2022-12-30 20:40','批准');
/*!40000 ALTER TABLE `leave1` ENABLE KEYS */;

#
# Structure for table "salary"
#

DROP TABLE IF EXISTS `salary`;
CREATE TABLE `salary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `sid` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `base` varchar(255) DEFAULT NULL,
  `late` varchar(255) DEFAULT NULL,
  `leave1` varchar(255) DEFAULT NULL,
  `prize` varchar(255) DEFAULT NULL,
  `tax` varchar(255) DEFAULT NULL,
  `benefits` varchar(255) DEFAULT NULL,
  `total` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

#
# Data for table "salary"
#

/*!40000 ALTER TABLE `salary` DISABLE KEYS */;
INSERT INTO `salary` VALUES (1,'测试员工2','0000','2022-12-25','8000','200','200','300','100','400','7400.0'),(3,'小红','1902','2022-12-30','4000','100','200','300','400','500','3100.0'),(4,'测试员工2','0000','2022-12-26','8000','20','61','123','754','52','7236.0'),(5,'张五','1905','2022-12-24','5600','100','30','200','50','100','5520.0'),(6,'测试20','ceshi20','2022-12-24','3000','200','200','500','20','100','2980.0'),(7,'张三','1903','2022-12-24','5500','100','10','20','30','40','5340.0'),(10,'李四','1904','2022-12-26','4000','100','52','123.5','21','25','3925.5'),(15,'李四','1904','2022-12-26','4000','0.0','0.0','0.0','0.0','0.0','4000.0'),(18,'测试员工2','0000','2022-12-28','8000','50','100','500','30','60','8260.0'),(19,'李四','1904','2022-12-29','4000','50.0','100.0','300.0','30.0','50.0','4070.0');
/*!40000 ALTER TABLE `salary` ENABLE KEYS */;
