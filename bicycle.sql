# Host: 127.0.0.1  (Version: 5.1.32-community)
# Date: 2016-01-28 22:42:32
# Generator: MySQL-Front 5.3  (Build 4.156)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "user"
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(40) DEFAULT NULL,
  `password` varchar(80) DEFAULT NULL,
  `head` varchar(120) DEFAULT NULL,
  `name` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

#
# Data for table "user"
#

INSERT INTO `user` VALUES (1,'admin','admin',NULL,'Mr.W'),(2,'admin2','admin2','','王浩明2'),(3,'admin3','admin3',NULL,'王浩明3'),(4,'admin4','admin4',NULL,'王浩明4'),(5,'admin5','admin5',NULL,'王浩明5'),(6,'admin6','admin6',NULL,'王浩明6');

#
# Structure for table "locat"
#

DROP TABLE IF EXISTS `locat`;
CREATE TABLE `locat` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `geo` varchar(20) DEFAULT NULL,
  `time` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `userid` (`userid`),
  CONSTRAINT `locat_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

#
# Data for table "locat"
#

INSERT INTO `locat` VALUES (1,2,'ws1y10rd232x','2016.1.1'),(5,3,'ws4wpbq18gf5','2016.1.1'),(6,1,'ws4wpbrbwgmm','2016.1.1'),(7,5,'ws4wpbyurs71','2016.1.1'),(8,6,'ws4wpbxr6en1','2015.1.1');
