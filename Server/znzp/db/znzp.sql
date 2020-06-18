/*
Navicat MySQL Data Transfer

Source Server         : db
Source Server Version : 50022
Source Host           : localhost:3306
Source Database       : znzp

Target Server Type    : MYSQL
Target Server Version : 50022
File Encoding         : 65001

*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
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

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('4', 'admin', '123456', '管理员', null, '2017-04-01 15:47:13');

-- ----------------------------
-- Table structure for `board`
-- ----------------------------
DROP TABLE IF EXISTS `board`;
CREATE TABLE `board` (
  `ID` int(11) NOT NULL auto_increment,
  `NEWSID` varchar(10) default NULL,
  `USERNAME` varchar(200) default NULL,
  `STATE` varchar(100) default NULL,
  `CREATETIME` timestamp NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `CONTENT` varchar(900) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of board
-- ----------------------------
INSERT INTO `board` VALUES ('18', '4', 'guanyu', '已审核', '2017-03-26 21:57:02', '粗粗哦哦，不错不错');

-- ----------------------------
-- Table structure for `news`
-- ----------------------------
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES ('6', '招程序猿', '1', null, null, 0xE585ACE58FB8E5BE85E98187E5A5BDEFBC8CE69C8935E4BAAC, '3', '2017-04-02 21:02:04');
INSERT INTO `news` VALUES ('11', '招金融财务', '3', null, null, 0xE5B7A5E8B584E99DA2E8AEAEEFBC8CE5BE85E98187E5A5BDEFBC8CE58C85E59083E4BD8F, '7', '2017-04-02 22:18:14');

-- ----------------------------
-- Table structure for `news1`
-- ----------------------------
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of news1
-- ----------------------------
INSERT INTO `news1` VALUES ('10', '212', '1', null, '12', null, 0x3132, '5', '2017-04-02 22:12:46');
INSERT INTO `news1` VALUES ('11', '我想养猪', '5', null, '6900', null, 0xE69DA8E8BF87E587A0E5B9B4E78CAA, '6', '2017-04-02 22:16:40');

-- ----------------------------
-- Table structure for `newstype`
-- ----------------------------
DROP TABLE IF EXISTS `newstype`;
CREATE TABLE `newstype` (
  `ID` int(11) NOT NULL auto_increment,
  `TYPENAME` varchar(100) default NULL,
  `CREATEUSER` varchar(100) default NULL,
  `CREATETIME` timestamp NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of newstype
-- ----------------------------
INSERT INTO `newstype` VALUES ('1', 'IT', null, '2017-04-02 05:49:28');
INSERT INTO `newstype` VALUES ('2', '服务', null, '2017-04-02 05:49:46');
INSERT INTO `newstype` VALUES ('3', '金融', null, '2017-04-02 05:49:43');
INSERT INTO `newstype` VALUES ('4', '运输', null, '2017-04-02 05:50:30');
INSERT INTO `newstype` VALUES ('5', '农业', null, '2017-04-02 05:50:53');

-- ----------------------------
-- Table structure for `shoucang`
-- ----------------------------
DROP TABLE IF EXISTS `shoucang`;
CREATE TABLE `shoucang` (
  `ID` int(11) NOT NULL auto_increment,
  `NEWSID` varchar(255) default NULL,
  `USERID` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shoucang
-- ----------------------------
INSERT INTO `shoucang` VALUES ('1', '1', '2');
INSERT INTO `shoucang` VALUES ('2', '1', '2');
INSERT INTO `shoucang` VALUES ('5', '6', '4');
INSERT INTO `shoucang` VALUES ('6', '6', '6');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('3', 'qiye', '123456', '阿里巴巴公司', '企业用户', null, null, null, null, null, null, 'ali@163.com', null, '12322312212', null, '2017-04-02 21:00:38');
INSERT INTO `user` VALUES ('4', 'geren', '123456', '张飞', '个人用户', null, null, null, null, null, null, '123456', null, '1232323', null, '2017-04-02 21:39:15');
INSERT INTO `user` VALUES ('5', 'caocao', '123456', '曹操', '个人用户', null, null, null, null, null, null, '123456@qq.com', null, '1233223121232', null, '2017-04-02 22:07:35');
INSERT INTO `user` VALUES ('6', 'liubei', '123456', '刘备', '个人用户', null, null, null, null, null, null, '123223@qq.com', null, '1233232312', null, '2017-04-02 22:15:08');
INSERT INTO `user` VALUES ('7', 'jindong', '123456', '京东公司', '企业用户', null, null, null, null, null, null, 'jd@163.com', null, '1232212323', null, '2017-04-02 22:16:43');
