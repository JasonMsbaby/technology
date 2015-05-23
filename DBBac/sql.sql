/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.1.49-community : Database - technology
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`technology` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `technology`;

/*Table structure for table `technology_competition` */

DROP TABLE IF EXISTS `technology_competition`;

CREATE TABLE `technology_competition` (
  `cID` int(11) NOT NULL AUTO_INCREMENT,
  `cName` varchar(255) DEFAULT NULL,
  `cLevel` varchar(255) DEFAULT NULL,
  `cOrganize` varchar(255) DEFAULT NULL,
  `cOrganization` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `technology_competition` */

/*Table structure for table `technology_major` */

DROP TABLE IF EXISTS `technology_major`;

CREATE TABLE `technology_major` (
  `mId` int(11) NOT NULL AUTO_INCREMENT,
  `mName` varchar(255) DEFAULT NULL,
  `mSchool` int(11) DEFAULT NULL,
  PRIMARY KEY (`mId`),
  KEY `FK90C1B4A6D5A6E093` (`mSchool`),
  CONSTRAINT `FK90C1B4A6D5A6E093` FOREIGN KEY (`mSchool`) REFERENCES `technology_school` (`sId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `technology_major` */

/*Table structure for table `technology_permission` */

DROP TABLE IF EXISTS `technology_permission`;

CREATE TABLE `technology_permission` (
  `pId` varchar(255) NOT NULL,
  `pContent` varchar(255) DEFAULT NULL,
  `pType` varchar(255) DEFAULT NULL,
  `pLink` varchar(255) DEFAULT NULL,
  `pAllow` varchar(255) DEFAULT NULL,
  `pShow` int(11) DEFAULT NULL,
  `pOrder` int(11) DEFAULT NULL,
  `pIcon` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `technology_permission` */

insert  into `technology_permission`(`pId`,`pContent`,`pType`,`pLink`,`pAllow`,`pShow`,`pOrder`,`pIcon`) values ('01','项目管理','0','','',1,1,'icon-cube'),('02','成果管理','0','','',1,2,'icon-book'),('03','成果审核','0','','',1,3,'icon-unlock-alt'),('05','院系管理','0','','',1,5,'icon-building'),('06','系统设置','0','','',1,6,'icon-gear (alias)'),('11','项目管理','01','competitionManager','competitionManager',1,1,''),('14','录入成果','02','RecordsManger_add','RecordsManger_add,RecordsManger_add_submit,getRewardByCompetition',1,1,''),('15','成果管理','02','RecordsManger','RecordsManger',1,2,''),('17','待审核','03','RecordsCheck','RecordsCheck,RecordsCheck_deal,getWaitCheckNum,RecordsCheck_deal_defeat,RecordsCheck_deal_pass',1,1,''),('22','添加院系','05','schoolManger_add','schoolManger_add,schoolManger_add_submit',1,1,''),('23','院系管理','05','schoolManger','schoolManger',1,2,''),('24','用户管理','06','userManger','userManger',1,1,''),('25','角色管理','06','roleManger','roleManger',1,2,''),('26','权限管理','06','permissionManger','permissionManger',0,3,''),('27','数据库管理','06','dbbacList','dbbacList,DB_delete',1,3,''),('28','项目奖励设置','01','rewardSetting','rewardSetting',1,4,''),('51','编辑','11','competitionManager_edit','competitionManager_edit,competitionManager_edit_submit,rewardSetting_getrLevelDistinct',0,2,''),('52','删除','11','competitionManager_delete','competitionManager_delete',0,3,''),('53','编辑','15','RecordsManger_edit','RecordsManger_edit,RecordsManger_edit_submit,RecordsManger_detail_getStudentInfoById,RecordsManger_detail_getTeacherInfoById,getRewardByCompetition',0,2,''),('54','删除','15','RecordsManger_delete','RecordsManger_delete',0,3,''),('55','新增','28','rewardSetting_add','rewardSetting_add,rewardSetting_add_submit',0,1,''),('56','删除','28','rewardSetting_delete','rewardSetting_delete',0,2,''),('57','编辑','23','','',0,1,''),('58','删除','23','','',0,2,''),('59','编辑','24','userManger_edit','userManger_edit,userManger_edit_submit',0,2,''),('60','删除','24','userManger_delete','userManger_delete',0,3,''),('61','添加','25','roleManger_add','roleManger_add,roleManger_add_submit',0,1,''),('62','编辑','25','roleManger_edit','roleManger_edit,roleManger_edit_submit',0,2,''),('63','删除','25','roleManger_delete','roleManger_delete',0,3,''),('64','编辑','26',NULL,'',0,1,''),('65','初始化','26','permissionManger_init','permissionManger_init',1,2,''),('66','备份','27','dbbacup','dbbacup',0,1,''),('67','还原','27','DB_load','DB_load',0,2,''),('68','添加','24','userManger_add','userManger_add,userManger_add_submit',0,1,''),('69','编辑','28','rewardSetting_edit','rewardSetting_edit,rewardSetting_edit_submit',0,3,''),('70','查看详情','15','RecordsManger_detail','RecordsManger_detail',0,1,''),('71','导出报表','0','','',1,5,'icon-download'),('72','导出报表','71','exportExcel','exportExcel',1,1,''),('73','导出竞赛级别认定、承办单位汇总表','72','exportExcel1','exportExcel1',0,2,''),('74','导出竞赛类别等级、奖励规定一览表','72','exportExcel2','exportExcel2',0,2,''),('75','导出学生奖励发放表(学生)','72','exportExcel3','exportExcel3',0,2,''),('76','导出竞赛国家级和省级奖励统计表(教师)','72','exportExcel4','exportExcel4',0,2,'');

/*Table structure for table `technology_records` */

DROP TABLE IF EXISTS `technology_records`;

CREATE TABLE `technology_records` (
  `reId` int(11) NOT NULL AUTO_INCREMENT,
  `reGrade` varchar(255) DEFAULT NULL,
  `reJoinTime` varchar(255) DEFAULT NULL,
  `reWriteTime` varchar(255) DEFAULT NULL,
  `reWritePerson` varchar(255) DEFAULT NULL,
  `reSchool` int(11) DEFAULT NULL,
  `reCheckPerson` varchar(255) DEFAULT NULL,
  `reCheckPersonAdmin` varchar(255) DEFAULT NULL,
  `reGiveStatus` int(11) DEFAULT NULL,
  `reCheckStatus` int(11) DEFAULT NULL,
  `recheckSuggestion` varchar(255) DEFAULT NULL,
  `reCheckStatusAdmin` int(11) DEFAULT NULL,
  `recheckSuggestionAdmin` varchar(255) DEFAULT NULL,
  `reStudentMoneny` varchar(255) DEFAULT NULL,
  `reTeacherMoney` varchar(255) DEFAULT NULL,
  `reProjectName` varchar(255) DEFAULT NULL,
  `reCompetition` int(11) DEFAULT NULL,
  PRIMARY KEY (`reId`),
  KEY `FK761715EFDD51072D` (`reCompetition`),
  CONSTRAINT `FK761715EFDD51072D` FOREIGN KEY (`reCompetition`) REFERENCES `technology_competition` (`cID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `technology_records` */

/*Table structure for table `technology_reward` */

DROP TABLE IF EXISTS `technology_reward`;

CREATE TABLE `technology_reward` (
  `rId` int(11) NOT NULL AUTO_INCREMENT,
  `rLevel` varchar(255) DEFAULT NULL,
  `rGrade` varchar(255) DEFAULT NULL,
  `rTeacher` varchar(255) DEFAULT NULL,
  `rStudent` varchar(255) DEFAULT NULL,
  `rRemark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`rId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `technology_reward` */

/*Table structure for table `technology_role` */

DROP TABLE IF EXISTS `technology_role`;

CREATE TABLE `technology_role` (
  `rId` int(11) NOT NULL AUTO_INCREMENT,
  `rName` varchar(255) DEFAULT NULL,
  `rPermission` varchar(255) DEFAULT NULL,
  `rLevel` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`rId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `technology_role` */

insert  into `technology_role`(`rId`,`rName`,`rPermission`,`rLevel`) values (1,'教务处管理员',NULL,'校级');

/*Table structure for table `technology_school` */

DROP TABLE IF EXISTS `technology_school`;

CREATE TABLE `technology_school` (
  `sId` int(11) NOT NULL AUTO_INCREMENT,
  `sName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `technology_school` */

/*Table structure for table `technology_studentinfo` */

DROP TABLE IF EXISTS `technology_studentinfo`;

CREATE TABLE `technology_studentinfo` (
  `sId` varchar(255) NOT NULL,
  `sName` varchar(255) DEFAULT NULL,
  `sSex` varchar(255) DEFAULT NULL,
  `sGrade` varchar(255) DEFAULT NULL,
  `sIDCard` varchar(255) DEFAULT NULL,
  `sIDBank` varchar(255) DEFAULT NULL,
  `sPhone` varchar(255) DEFAULT NULL,
  `sMajor` int(11) DEFAULT NULL,
  `sSchool` int(11) DEFAULT NULL,
  PRIMARY KEY (`sId`),
  KEY `FK4921EB6130C4419` (`sSchool`),
  KEY `FK4921EB64202F421` (`sMajor`),
  CONSTRAINT `FK4921EB64202F421` FOREIGN KEY (`sMajor`) REFERENCES `technology_major` (`mId`),
  CONSTRAINT `FK4921EB6130C4419` FOREIGN KEY (`sSchool`) REFERENCES `technology_school` (`sId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `technology_studentinfo` */

/*Table structure for table `technology_studentinfo_record` */

DROP TABLE IF EXISTS `technology_studentinfo_record`;

CREATE TABLE `technology_studentinfo_record` (
  `reId` int(11) NOT NULL,
  `sId` varchar(255) NOT NULL,
  `StudentInfo_Record` int(11) NOT NULL,
  PRIMARY KEY (`reId`,`StudentInfo_Record`),
  KEY `FK83A15E1AACC84219` (`sId`),
  KEY `FK83A15E1A7D6992` (`reId`),
  CONSTRAINT `FK83A15E1A7D6992` FOREIGN KEY (`reId`) REFERENCES `technology_records` (`reId`),
  CONSTRAINT `FK83A15E1AACC84219` FOREIGN KEY (`sId`) REFERENCES `technology_studentinfo` (`sId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `technology_studentinfo_record` */

/*Table structure for table `technology_teacherinfo` */

DROP TABLE IF EXISTS `technology_teacherinfo`;

CREATE TABLE `technology_teacherinfo` (
  `tId` varchar(255) NOT NULL,
  `tName` varchar(255) DEFAULT NULL,
  `tSex` varchar(255) DEFAULT NULL,
  `tIdcard` varchar(255) DEFAULT NULL,
  `tBankNum` varchar(255) DEFAULT NULL,
  `tPhone` varchar(255) DEFAULT NULL,
  `tSchool` int(11) DEFAULT NULL,
  PRIMARY KEY (`tId`),
  KEY `FKDB2A3CDD47F27F5A` (`tSchool`),
  CONSTRAINT `FKDB2A3CDD47F27F5A` FOREIGN KEY (`tSchool`) REFERENCES `technology_school` (`sId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `technology_teacherinfo` */

/*Table structure for table `technology_teacherinfo_record` */

DROP TABLE IF EXISTS `technology_teacherinfo_record`;

CREATE TABLE `technology_teacherinfo_record` (
  `reId` int(11) NOT NULL,
  `sId` varchar(255) NOT NULL,
  `TeacherInfo_Record` int(11) NOT NULL,
  PRIMARY KEY (`reId`,`TeacherInfo_Record`),
  KEY `FKC899561383606040` (`sId`),
  KEY `FKC89956137D6992` (`reId`),
  CONSTRAINT `FKC89956137D6992` FOREIGN KEY (`reId`) REFERENCES `technology_records` (`reId`),
  CONSTRAINT `FKC899561383606040` FOREIGN KEY (`sId`) REFERENCES `technology_teacherinfo` (`tId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `technology_teacherinfo_record` */

/*Table structure for table `technology_user` */

DROP TABLE IF EXISTS `technology_user`;

CREATE TABLE `technology_user` (
  `uId` int(11) NOT NULL AUTO_INCREMENT,
  `uName` varchar(255) DEFAULT NULL,
  `uPwd` varchar(255) DEFAULT NULL,
  `uRole` int(11) DEFAULT NULL,
  `uSchool` int(11) DEFAULT NULL,
  PRIMARY KEY (`uId`),
  KEY `FKCAE0DB3EA84555F` (`uRole`),
  KEY `FKCAE0DB3E7CD8BA9B` (`uSchool`),
  CONSTRAINT `FKCAE0DB3E7CD8BA9B` FOREIGN KEY (`uSchool`) REFERENCES `technology_school` (`sId`),
  CONSTRAINT `FKCAE0DB3EA84555F` FOREIGN KEY (`uRole`) REFERENCES `technology_role` (`rId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `technology_user` */

insert  into `technology_user`(`uId`,`uName`,`uPwd`,`uRole`,`uSchool`) values (1,'admin','123',1,NULL);

/* Trigger structure for table `technology_competition` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `technology_delete_Competition` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `technology_delete_Competition` BEFORE DELETE ON `technology_competition` FOR EACH ROW BEGIN
	delete from technology_records where reCompetition=old.cID;
    END */$$


DELIMITER ;

/* Trigger structure for table `technology_major` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `technology_delete_Major` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `technology_delete_Major` BEFORE DELETE ON `technology_major` FOR EACH ROW BEGIN
	delete from technology_studentinfo where sMajor=old.mId;
    END */$$


DELIMITER ;

/* Trigger structure for table `technology_records` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `technology_delete_records` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `technology_delete_records` BEFORE DELETE ON `technology_records` FOR EACH ROW BEGIN
	delete from technology_studentinfo_record where reId=old.reId;
	delete from technology_teacherinfo_record where reId=old.reId;
    END */$$


DELIMITER ;

/* Trigger structure for table `technology_role` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `technology_delete_Role` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `technology_delete_Role` BEFORE DELETE ON `technology_role` FOR EACH ROW BEGIN
	delete from technology_user where uRole=old.rId;
    END */$$


DELIMITER ;

/* Trigger structure for table `technology_school` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `technology_delete_School` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `technology_delete_School` BEFORE DELETE ON `technology_school` FOR EACH ROW BEGIN
	delete from technology_user where uSchool=old.sId;
    END */$$


DELIMITER ;

/* Trigger structure for table `technology_studentinfo` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `technology_delete_Student` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `technology_delete_Student` BEFORE DELETE ON `technology_studentinfo` FOR EACH ROW BEGIN
	delete from technology_studentinfo_record where sId=old.sId;
    END */$$


DELIMITER ;

/* Trigger structure for table `technology_teacherinfo` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `technology_delete_Teacher` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `technology_delete_Teacher` BEFORE DELETE ON `technology_teacherinfo` FOR EACH ROW BEGIN
	delete from technology_teacherinfo_record where sId=old.tId;
    END */$$


DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
