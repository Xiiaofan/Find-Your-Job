/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.0.51b-community-nt-log : Database - znzp
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`znzp` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `znzp`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `ID` int(11) NOT NULL,
  `LOGINNAME` varchar(50) collate utf8_bin default NULL,
  `LOGINPSW` varchar(50) collate utf8_bin default NULL,
  `USERNAME` varchar(50) collate utf8_bin default NULL,
  `USERTYPE` varchar(10) collate utf8_bin default NULL,
  `CREATETIME` varchar(50) collate utf8_bin default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `admin` */

insert  into `admin`(`ID`,`LOGINNAME`,`LOGINPSW`,`USERNAME`,`USERTYPE`,`CREATETIME`) values (6,'admin','123456','XIAOFAN LIU',NULL,'2020-06-17 21:23:08');

/*Table structure for table `board` */

DROP TABLE IF EXISTS `board`;

CREATE TABLE `board` (
  `ID` int(11) NOT NULL auto_increment,
  `NEWSID` varchar(10) default NULL,
  `USERNAME` varchar(200) default NULL,
  `STATE` varchar(100) default NULL,
  `CREATETIME` timestamp NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `CONTENT` varchar(900) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Data for the table `board` */

insert  into `board`(`ID`,`NEWSID`,`USERNAME`,`STATE`,`CREATETIME`,`CONTENT`) values (18,'4','guanyu','已审核','2017-03-26 21:57:02','粗粗哦哦，不错不错');

/*Table structure for table `news` */

DROP TABLE IF EXISTS `news`;

CREATE TABLE `news` (
  `ID` int(11) NOT NULL auto_increment,
  `TITLE` varchar(200) collate utf8_bin default NULL,
  `TYPEID` varchar(100) collate utf8_bin default NULL,
  `TYPENAME` varchar(200) collate utf8_bin default NULL,
  `IMGPATH` varchar(200) collate utf8_bin default NULL,
  `CONTENT` text collate utf8_bin,
  `CREATEUSER` varchar(100) collate utf8_bin default NULL,
  `CREATETIME` varchar(100) collate utf8_bin default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `news` */

insert  into `news`(`ID`,`TITLE`,`TYPEID`,`TYPENAME`,`IMGPATH`,`CONTENT`,`CREATEUSER`,`CREATETIME`) values (13,'jfldksjf','',NULL,NULL,'fnjsdjkfjdlkg','15','2020-06-16 19:07:26'),(14,'fdgfg','',NULL,NULL,'fdgfh','15','2020-06-16 19:08:06'),(15,'Junior/Student QA Tester','1',NULL,NULL,'JDR Software are seeking final year Undergraduate Computer Science, IT & Software Engineering students to join their team. These roles are paid, flexible, and the right candidate has a great chance of securing a full time role once their studies are complete! ','1','2020-06-19 18:55:21'),(16,'Junior Test Analyst','1',NULL,NULL,'JDR Software are seeking final year Undergraduate Computer Science, IT & Software Engineering students to join their team. These roles are paid, flexible, and the right candidate has a great chance of securing a full time role once their studies are complete! As a Junior Test Analyst, you will be responsible for Functional and Technical testing of the JDR Software product suite including Allocate Plus (known as STAR at Deakin).','1','2020-06-19 18:58:35'),(17,'WorkXtra Corporate','2',NULL,NULL,'Xtra AgedCare & Xtra HomeCare are part of the mobile health division of Zenitas Healthcare. We are a national business with a unique approach, focused on comprehensive assessments, which has been pivotal in achieving optimal outcomes for our clients. Working across both the aged care & community industries, we are able to offer our employees clinical variety & diverse experiences. We invest in our staff by providing comprehensive training, on-going PD, travel & genuine genuine career advancement','2','2020-06-19 19:03:13'),(18,'Back-End IT Intern','1',NULL,NULL,'AgedCare are a company seeking a Back-End Intern with experience in .NET (essential) and an understanding of algorithms and matching technology. You will work closely with Roundly\'s co-founder who has a domain expertise in recruitment and hiring.','2','2020-06-19 19:04:52'),(19,'Knowledge Translation Specialist','4',NULL,NULL,'Are you skilled at facilitating the translation of research into policy and practice? Are you passionate about building capacity and capability in evidence-informed decision making for the benefit of Australian families?\n\nThe Knowledge Translation & Impact team are looking for a Knowledge Translation Specialist to lead and support researchers in knowledge translation activities across a range of research projects. The successful candidate will play a key role in engaging researchers in knowledge translation to achieve better child and family wellbeing.','3','2020-06-19 19:10:26'),(20,'Translator\n','2',NULL,NULL,'Translator required for instuition tours','3','2020-06-19 19:13:45'),(21,'Business Services','3',NULL,NULL,'You will take responsibility for:\n\nPreparation of financial statements and tax returns for various entities including companies, trusts and superannuation funds\nWorking directly with the Partners and Managers on various strategic matters for SME\'s and high net worth individuals\nTax and cash flow planning including regular monitoring and reviewing client\'s income tax, GST and BAS requirements\nResearch and preparation of advice in respect of technical tax issues, commercial issues associated with SME\'s\nClient contact via shadowing programs with Partners and Managers\nTo be successful\n','4','2020-06-19 19:18:19'),(22,'Assistant Accountant','3',NULL,NULL,'We are seeking a passionate and driven individual who has a keen interest for sport to join their team as an Assistant Accountant. The Assistant Accountant will be an essential asset to the finance team working closely with the Chief Financial Officer and other Finance team members on a daily basis.','4','2020-06-19 19:19:38'),(23,'Graduate Program: Science and Planning','5',NULL,NULL,'Science and Planning Graduate Program - Victorian Government\nThe program is a partnership between the Department of Environment, Land, Water & Planning (DELWP) and the Department of Jobs, Precincts & Regions (DJPR)','5','2020-06-19 19:24:44'),(24,'Volunteers','5',NULL,NULL,'These programs involve more than 55,000 people who regularly make an effort to improve Victoria\'s land and water systems, biodiversity, marine and coastal environments and ecosystems.','5','2020-06-19 19:25:42'),(25,'ooo','1',NULL,NULL,'jfdksjfl','8','2020-06-19 20:01:28');

/*Table structure for table `news1` */

DROP TABLE IF EXISTS `news1`;

CREATE TABLE `news1` (
  `ID` int(11) NOT NULL auto_increment,
  `TITLE` varchar(200) collate utf8_bin default NULL,
  `TYPEID` varchar(100) collate utf8_bin default NULL,
  `TYPENAME` varchar(200) collate utf8_bin default NULL,
  `XINCHOU` varchar(200) collate utf8_bin default NULL,
  `IMGPATH` varchar(200) collate utf8_bin default NULL,
  `CONTENT` text collate utf8_bin,
  `CREATEUSER` varchar(100) collate utf8_bin default NULL,
  `CREATETIME` varchar(100) collate utf8_bin default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `news1` */

insert  into `news1`(`ID`,`TITLE`,`TYPEID`,`TYPENAME`,`XINCHOU`,`IMGPATH`,`CONTENT`,`CREATEUSER`,`CREATETIME`) values (12,'12423','',NULL,'124',NULL,'213124','16','2020-06-16 19:09:41'),(13,'Planter','5',NULL,'20000',NULL,'I have cultivated many excellent varieties and carried them out. I have been praised by the school for my planting skills. The total output has greatly improved','6','2020-06-19 19:37:02'),(14,'programming','1',NULL,'10000',NULL,'I like computer','7','2020-06-19 19:57:28'),(15,'programming','1',NULL,'1000',NULL,'I like computer','7','2020-06-19 20:00:12'),(16,'jlkdsjf','1',NULL,'111',NULL,'bdksf','7','2020-06-19 20:04:57');

/*Table structure for table `newstype` */

DROP TABLE IF EXISTS `newstype`;

CREATE TABLE `newstype` (
  `ID` int(11) NOT NULL auto_increment,
  `TYPENAME` varchar(100) default NULL,
  `CREATEUSER` varchar(100) default NULL,
  `CREATETIME` timestamp NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `newstype` */

insert  into `newstype`(`ID`,`TYPENAME`,`CREATEUSER`,`CREATETIME`) values (1,'IT',NULL,'2020-06-17 11:55:56'),(2,'Service',NULL,'2020-06-17 12:01:11'),(3,'Finance',NULL,'2020-06-17 12:01:25'),(4,'Transportation',NULL,'2020-06-17 12:01:41'),(5,'Agriculture',NULL,'2020-06-17 12:01:57');

/*Table structure for table `shoucang` */

DROP TABLE IF EXISTS `shoucang`;

CREATE TABLE `shoucang` (
  `ID` int(11) NOT NULL auto_increment,
  `NEWSID` varchar(255) default NULL,
  `USERID` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `shoucang` */

insert  into `shoucang`(`ID`,`NEWSID`,`USERID`) values (1,'1','2'),(2,'1','2'),(5,'6','4'),(6,'6','6'),(7,'12','10'),(8,'12','16'),(9,'15','16'),(10,'15','6'),(11,'18','7'),(12,'15','7'),(13,'15','7');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `ID` int(11) NOT NULL auto_increment,
  `LOGINNAME` varchar(50) collate utf8_bin default NULL,
  `LOGINPSW` varchar(50) collate utf8_bin default NULL,
  `USERNAME` varchar(50) collate utf8_bin default NULL,
  `USERTYPE` varchar(50) collate utf8_bin default NULL,
  `IMGPATH` varchar(200) collate utf8_bin default NULL,
  `INTERESTS` varchar(900) collate utf8_bin default NULL,
  `JOB` varchar(100) collate utf8_bin default NULL,
  `CONCERN` varchar(100) collate utf8_bin default NULL,
  `SEX` varchar(10) collate utf8_bin default NULL,
  `QQ` varchar(50) collate utf8_bin default NULL,
  `EMAIL` varchar(50) collate utf8_bin default NULL,
  `ADDRESS` varchar(100) collate utf8_bin default NULL,
  `TEL` varchar(50) collate utf8_bin default NULL,
  `EXAMSTATE` varchar(10) collate utf8_bin default NULL,
  `CREATETIME` timestamp NULL default CURRENT_TIMESTAMP,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `user` */

insert  into `user`(`ID`,`LOGINNAME`,`LOGINPSW`,`USERNAME`,`USERTYPE`,`IMGPATH`,`INTERESTS`,`JOB`,`CONCERN`,`SEX`,`QQ`,`EMAIL`,`ADDRESS`,`TEL`,`EXAMSTATE`,`CREATETIME`) values (1,'1','123456','JDR Software','Company',NULL,NULL,NULL,NULL,NULL,NULL,'info@jdrsoftware.com.au',NULL,'1300 142 032',NULL,'2020-06-19 18:51:35'),(2,'AgedCareee','12345678','AgedCare','Company',NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,'(08) 8321 9010',NULL,'2020-06-19 19:02:15'),(3,'AIFS','1234566','Australian Institute of Family Studies','Company',NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,'1800 55 1800',NULL,'2020-06-19 19:09:33'),(4,'WA','123456','Wellingtons Accountants Pty Ltd','Company',NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,'04589378',NULL,'2020-06-19 19:17:09'),(5,'VIC','123123','VICTORIA State Government','Company',NULL,NULL,NULL,NULL,NULL,NULL,'VLRSbushfiresupport@victorianlrs.com.au',NULL,'(03) 9102 0402 ',NULL,'2020-06-19 19:23:32'),(6,'SUN','123456','MANQI','Individual',NULL,NULL,NULL,NULL,NULL,NULL,'51838899@gmail.com',NULL,'04510909',NULL,'2020-06-19 19:34:01'),(7,'LIU','123456','XIAOFAN','Individual',NULL,NULL,NULL,NULL,NULL,NULL,'liuxiiaof@gmail.com',NULL,'04510902',NULL,'2020-06-19 19:56:11'),(8,'11','11','Deakin','Company',NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,'',NULL,'2020-06-19 20:01:08');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
