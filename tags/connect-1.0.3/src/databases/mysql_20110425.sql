-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.49-1ubuntu8.1


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema connect
--

CREATE DATABASE IF NOT EXISTS connect;
USE connect;

--
-- Definition of table `connect`.`e2_activities`
--

DROP TABLE IF EXISTS `connect`.`e2_activities`;
CREATE TABLE  `connect`.`e2_activities` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_description` longtext,
  `_entered` datetime NOT NULL,
  `_entity` varchar(255) DEFAULT NULL,
  `_format` varchar(255) NOT NULL,
  `_linked_id` bigint(20) DEFAULT NULL,
  `_type` varchar(255) NOT NULL,
  `_entered_by_id` bigint(20) DEFAULT NULL,
  `_project_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`_id`),
  KEY `FK555D6EFFFA11FB90` (`_entered_by_id`),
  KEY `FK555D6EFFADE2AB90` (`_project_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`e2_activities`
--

/*!40000 ALTER TABLE `e2_activities` DISABLE KEYS */;
LOCK TABLES `e2_activities` WRITE;
INSERT INTO `connect`.`e2_activities` VALUES  (1,'<a href=\"http://localhost:8080/connect-web/haozhonghu-1/profile\">\n	Gavin Hu\n</a>\n 更新了 \n <a href=\"http://localhost:8080/connect-web/haozhonghu-1/profile\">\n 	Gavin Hu\n </a>','2011-04-25 18:35:19','Profile','text',1,'Profile',1,1),
 (2,'here is site chat...','2011-04-25 18:56:27',NULL,'text',NULL,'site-chat',1,NULL),
 (3,'this is gavin\'s micro blog...[face:laugh]','2011-04-25 19:08:23',NULL,'text',NULL,'user-input',1,1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `e2_activities` ENABLE KEYS */;


--
-- Definition of table `connect`.`e2_albums`
--

DROP TABLE IF EXISTS `connect`.`e2_albums`;
CREATE TABLE  `connect`.`e2_albums` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_entered` datetime DEFAULT NULL,
  `_modified` datetime DEFAULT NULL,
  `_name` varchar(255) DEFAULT NULL,
  `_entered_by_id` bigint(20) NOT NULL,
  `_modified_by_id` bigint(20) NOT NULL,
  `_project_id` bigint(20) NOT NULL,
  PRIMARY KEY (`_id`),
  KEY `FK1610DBF6FA11FB90` (`_entered_by_id`),
  KEY `FK1610DBF6ADE2AB90` (`_project_id`),
  KEY `FK1610DBF68DB1780` (`_modified_by_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`e2_albums`
--

/*!40000 ALTER TABLE `e2_albums` DISABLE KEYS */;
LOCK TABLES `e2_albums` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `e2_albums` ENABLE KEYS */;


--
-- Definition of table `connect`.`e2_answers`
--

DROP TABLE IF EXISTS `connect`.`e2_answers`;
CREATE TABLE  `connect`.`e2_answers` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_content` longtext NOT NULL,
  `_entered` datetime DEFAULT NULL,
  `_modified` datetime DEFAULT NULL,
  `_entered_by_id` bigint(20) NOT NULL,
  `_modified_by_id` bigint(20) NOT NULL,
  `_question_id` bigint(20) NOT NULL,
  PRIMARY KEY (`_id`),
  KEY `FKB064AEA3FA11FB90` (`_entered_by_id`),
  KEY `FKB064AEA3B7153A81` (`_question_id`),
  KEY `FKB064AEA38DB1780` (`_modified_by_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`e2_answers`
--

/*!40000 ALTER TABLE `e2_answers` DISABLE KEYS */;
LOCK TABLES `e2_answers` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `e2_answers` ENABLE KEYS */;


--
-- Definition of table `connect`.`e2_attachments`
--

DROP TABLE IF EXISTS `connect`.`e2_attachments`;
CREATE TABLE  `connect`.`e2_attachments` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_content_type` varchar(255) NOT NULL,
  `_entered` datetime DEFAULT NULL,
  `_file_name` varchar(255) NOT NULL,
  `_size` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`e2_attachments`
--

/*!40000 ALTER TABLE `e2_attachments` DISABLE KEYS */;
LOCK TABLES `e2_attachments` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `e2_attachments` ENABLE KEYS */;


--
-- Definition of table `connect`.`e2_blog_categories`
--

DROP TABLE IF EXISTS `connect`.`e2_blog_categories`;
CREATE TABLE  `connect`.`e2_blog_categories` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_code` varchar(255) DEFAULT NULL,
  `_enabled` bit(1) DEFAULT NULL,
  `_label` varchar(255) NOT NULL,
  `_level` int(11) DEFAULT NULL,
  `_project_id` bigint(20) NOT NULL,
  PRIMARY KEY (`_id`),
  KEY `FKC4DB9B07ADE2AB90` (`_project_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`e2_blog_categories`
--

/*!40000 ALTER TABLE `e2_blog_categories` DISABLE KEYS */;
LOCK TABLES `e2_blog_categories` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `e2_blog_categories` ENABLE KEYS */;


--
-- Definition of table `connect`.`e2_blog_posts`
--

DROP TABLE IF EXISTS `connect`.`e2_blog_posts`;
CREATE TABLE  `connect`.`e2_blog_posts` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_content` longtext NOT NULL,
  `_enabled` bit(1) DEFAULT NULL,
  `_entered` datetime DEFAULT NULL,
  `_keywords` varchar(255) DEFAULT NULL,
  `_modified` datetime DEFAULT NULL,
  `_title` varchar(255) NOT NULL,
  `_top` bit(1) DEFAULT NULL,
  `_type` varchar(255) NOT NULL,
  `_category_id` bigint(20) DEFAULT NULL,
  `_entered_by_id` bigint(20) NOT NULL,
  `_modified_by_id` bigint(20) NOT NULL,
  `_project_id` bigint(20) NOT NULL,
  PRIMARY KEY (`_id`),
  KEY `FKE7FD80084A86D073` (`_category_id`),
  KEY `FKE7FD8008FA11FB90` (`_entered_by_id`),
  KEY `FKE7FD8008ADE2AB90` (`_project_id`),
  KEY `FKE7FD80088DB1780` (`_modified_by_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`e2_blog_posts`
--

/*!40000 ALTER TABLE `e2_blog_posts` DISABLE KEYS */;
LOCK TABLES `e2_blog_posts` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `e2_blog_posts` ENABLE KEYS */;


--
-- Definition of table `connect`.`e2_comments`
--

DROP TABLE IF EXISTS `connect`.`e2_comments`;
CREATE TABLE  `connect`.`e2_comments` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_content` longtext NOT NULL,
  `_enabled` bit(1) DEFAULT NULL,
  `_entered` datetime NOT NULL,
  `_entity` varchar(255) DEFAULT NULL,
  `_linked_id` bigint(20) NOT NULL,
  `_modified` datetime NOT NULL,
  `_entered_by_id` bigint(20) NOT NULL,
  `_modified_by_id` bigint(20) NOT NULL,
  PRIMARY KEY (`_id`),
  KEY `FK560FAD86FA11FB90` (`_entered_by_id`),
  KEY `FK560FAD868DB1780` (`_modified_by_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`e2_comments`
--

/*!40000 ALTER TABLE `e2_comments` DISABLE KEYS */;
LOCK TABLES `e2_comments` WRITE;
INSERT INTO `connect`.`e2_comments` VALUES  (1,'this is a comment',0x01,'2011-04-25 18:56:56','Activity',2,'2011-04-25 18:56:56',1,1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `e2_comments` ENABLE KEYS */;


--
-- Definition of table `connect`.`e2_events`
--

DROP TABLE IF EXISTS `connect`.`e2_events`;
CREATE TABLE  `connect`.`e2_events` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_all_day` bit(1) DEFAULT NULL,
  `_description` longtext,
  `_enabled` bit(1) DEFAULT NULL,
  `_end_date` datetime NOT NULL,
  `_entered` datetime NOT NULL,
  `_modified` datetime NOT NULL,
  `_start_date` datetime NOT NULL,
  `_title` varchar(255) NOT NULL,
  `_type` varchar(255) NOT NULL,
  `_url` varchar(255) DEFAULT NULL,
  `_entered_by_id` bigint(20) NOT NULL,
  `_modified_by_id` bigint(20) NOT NULL,
  `_project_id` bigint(20) NOT NULL,
  PRIMARY KEY (`_id`),
  KEY `FK1D726D2BFA11FB90` (`_entered_by_id`),
  KEY `FK1D726D2BADE2AB90` (`_project_id`),
  KEY `FK1D726D2B8DB1780` (`_modified_by_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`e2_events`
--

/*!40000 ALTER TABLE `e2_events` DISABLE KEYS */;
LOCK TABLES `e2_events` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `e2_events` ENABLE KEYS */;


--
-- Definition of table `connect`.`e2_file_items`
--

DROP TABLE IF EXISTS `connect`.`e2_file_items`;
CREATE TABLE  `connect`.`e2_file_items` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_display_name` varchar(255) DEFAULT NULL,
  `_enabled` bit(1) DEFAULT NULL,
  `_entered` datetime DEFAULT NULL,
  `_modified` datetime DEFAULT NULL,
  `_entered_by_id` bigint(20) DEFAULT NULL,
  `_folder_id` bigint(20) DEFAULT NULL,
  `_modified_by_id` bigint(20) NOT NULL,
  `_real_file_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`_id`),
  KEY `FK5A85664F99FFD4D8` (`_folder_id`),
  KEY `FK5A85664F402E06BB` (`_real_file_id`),
  KEY `FK5A85664FFA11FB90` (`_entered_by_id`),
  KEY `FK5A85664F8DB1780` (`_modified_by_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`e2_file_items`
--

/*!40000 ALTER TABLE `e2_file_items` DISABLE KEYS */;
LOCK TABLES `e2_file_items` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `e2_file_items` ENABLE KEYS */;


--
-- Definition of table `connect`.`e2_folders`
--

DROP TABLE IF EXISTS `connect`.`e2_folders`;
CREATE TABLE  `connect`.`e2_folders` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_enabled` bit(1) DEFAULT NULL,
  `_entered` datetime NOT NULL,
  `_modified` datetime NOT NULL,
  `_name` varchar(255) NOT NULL,
  `_entered_by_id` bigint(20) NOT NULL,
  `_modified_by_id` bigint(20) NOT NULL,
  `_parent_id` bigint(20) DEFAULT NULL,
  `_project_id` bigint(20) NOT NULL,
  PRIMARY KEY (`_id`),
  KEY `FKBA2D67F3F5F5AF1C` (`_parent_id`),
  KEY `FKBA2D67F3FA11FB90` (`_entered_by_id`),
  KEY `FKBA2D67F3ADE2AB90` (`_project_id`),
  KEY `FKBA2D67F38DB1780` (`_modified_by_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`e2_folders`
--

/*!40000 ALTER TABLE `e2_folders` DISABLE KEYS */;
LOCK TABLES `e2_folders` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `e2_folders` ENABLE KEYS */;


--
-- Definition of table `connect`.`e2_forums`
--

DROP TABLE IF EXISTS `connect`.`e2_forums`;
CREATE TABLE  `connect`.`e2_forums` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_allow_attachment` bit(1) DEFAULT NULL,
  `_description` varchar(255) DEFAULT NULL,
  `_enabled` bit(1) DEFAULT NULL,
  `_entered` datetime NOT NULL,
  `_level` int(11) DEFAULT NULL,
  `_modified` datetime NOT NULL,
  `_name` varchar(255) NOT NULL,
  `_entered_by_id` bigint(20) NOT NULL,
  `_modified_by_id` bigint(20) NOT NULL,
  `_project_id` bigint(20) NOT NULL,
  PRIMARY KEY (`_id`),
  KEY `FK1ECAA384FA11FB90` (`_entered_by_id`),
  KEY `FK1ECAA384ADE2AB90` (`_project_id`),
  KEY `FK1ECAA3848DB1780` (`_modified_by_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`e2_forums`
--

/*!40000 ALTER TABLE `e2_forums` DISABLE KEYS */;
LOCK TABLES `e2_forums` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `e2_forums` ENABLE KEYS */;


--
-- Definition of table `connect`.`e2_keywords`
--

DROP TABLE IF EXISTS `connect`.`e2_keywords`;
CREATE TABLE  `connect`.`e2_keywords` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_entered` datetime DEFAULT NULL,
  `_keyword` varchar(255) NOT NULL,
  `_number` bigint(20) NOT NULL,
  `_type` varchar(255) NOT NULL,
  `_category_id` bigint(20) DEFAULT NULL,
  `_entered_by_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`_id`),
  KEY `FK99266F3C91C8ED4B` (`_category_id`),
  KEY `FK99266F3CFA11FB90` (`_entered_by_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`e2_keywords`
--

/*!40000 ALTER TABLE `e2_keywords` DISABLE KEYS */;
LOCK TABLES `e2_keywords` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `e2_keywords` ENABLE KEYS */;


--
-- Definition of table `connect`.`e2_links`
--

DROP TABLE IF EXISTS `connect`.`e2_links`;
CREATE TABLE  `connect`.`e2_links` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_enabled` bit(1) DEFAULT NULL,
  `_entity` varchar(255) NOT NULL,
  `_title` varchar(255) DEFAULT NULL,
  `_to_id` bigint(20) NOT NULL,
  `_from_id` bigint(20) NOT NULL,
  PRIMARY KEY (`_id`),
  KEY `FKF90DF9674B929A81` (`_from_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`e2_links`
--

/*!40000 ALTER TABLE `e2_links` DISABLE KEYS */;
LOCK TABLES `e2_links` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `e2_links` ENABLE KEYS */;


--
-- Definition of table `connect`.`e2_mail_settings`
--

DROP TABLE IF EXISTS `connect`.`e2_mail_settings`;
CREATE TABLE  `connect`.`e2_mail_settings` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_enabled` bit(1) DEFAULT NULL,
  `_host` varchar(255) DEFAULT NULL,
  `_password` varchar(255) DEFAULT NULL,
  `_port` int(11) DEFAULT NULL,
  `_username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`e2_mail_settings`
--

/*!40000 ALTER TABLE `e2_mail_settings` DISABLE KEYS */;
LOCK TABLES `e2_mail_settings` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `e2_mail_settings` ENABLE KEYS */;


--
-- Definition of table `connect`.`e2_maps`
--

DROP TABLE IF EXISTS `connect`.`e2_maps`;
CREATE TABLE  `connect`.`e2_maps` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_depth` int(11) DEFAULT NULL,
  `_latitude` varchar(255) DEFAULT NULL,
  `_longitude` varchar(255) DEFAULT NULL,
  `_scrollable` bit(1) DEFAULT NULL,
  `_show_search` bit(1) DEFAULT NULL,
  `_show_street` bit(1) DEFAULT NULL,
  `_show_traffic` bit(1) DEFAULT NULL,
  `_project_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`_id`),
  KEY `FK39956F29ADE2AB90` (`_project_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`e2_maps`
--

/*!40000 ALTER TABLE `e2_maps` DISABLE KEYS */;
LOCK TABLES `e2_maps` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `e2_maps` ENABLE KEYS */;


--
-- Definition of table `connect`.`e2_markers`
--

DROP TABLE IF EXISTS `connect`.`e2_markers`;
CREATE TABLE  `connect`.`e2_markers` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_description` varchar(255) DEFAULT NULL,
  `_draggable` bit(1) DEFAULT NULL,
  `_enabled` bit(1) DEFAULT NULL,
  `_latitude` varchar(255) DEFAULT NULL,
  `_longitude` varchar(255) DEFAULT NULL,
  `_icon_id` bigint(20) DEFAULT NULL,
  `_map_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`_id`),
  KEY `FK14ECE9A719C95EB1` (`_map_id`),
  KEY `FK14ECE9A7A29BD21` (`_icon_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`e2_markers`
--

/*!40000 ALTER TABLE `e2_markers` DISABLE KEYS */;
LOCK TABLES `e2_markers` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `e2_markers` ENABLE KEYS */;


--
-- Definition of table `connect`.`e2_messages`
--

DROP TABLE IF EXISTS `connect`.`e2_messages`;
CREATE TABLE  `connect`.`e2_messages` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_content` longtext NOT NULL,
  `_entered` datetime NOT NULL,
  `_read` bit(1) DEFAULT NULL,
  `_subject` varchar(255) NOT NULL,
  `_entered_by_id` bigint(20) NOT NULL,
  `_from_id` bigint(20) NOT NULL,
  `_read_by_id` bigint(20) DEFAULT NULL,
  `_to_id` bigint(20) NOT NULL,
  PRIMARY KEY (`_id`),
  KEY `FK5E6CD0DECAB7480D` (`_read_by_id`),
  KEY `FK5E6CD0DE8B22A250` (`_to_id`),
  KEY `FK5E6CD0DE4B929A81` (`_from_id`),
  KEY `FK5E6CD0DEFA11FB90` (`_entered_by_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`e2_messages`
--

/*!40000 ALTER TABLE `e2_messages` DISABLE KEYS */;
LOCK TABLES `e2_messages` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `e2_messages` ENABLE KEYS */;


--
-- Definition of table `connect`.`e2_permissions`
--

DROP TABLE IF EXISTS `connect`.`e2_permissions`;
CREATE TABLE  `connect`.`e2_permissions` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_enabled` bit(1) DEFAULT NULL,
  `_category_id` bigint(20) NOT NULL,
  `_resource_id` bigint(20) NOT NULL,
  `_role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`_id`),
  KEY `FKD09723B272C99D44` (`_role_id`),
  KEY `FKD09723B291C8ED4B` (`_category_id`),
  KEY `FKD09723B2B73B2144` (`_resource_id`)
) ENGINE=MyISAM AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`e2_permissions`
--

/*!40000 ALTER TABLE `e2_permissions` DISABLE KEYS */;
LOCK TABLES `e2_permissions` WRITE;
INSERT INTO `connect`.`e2_permissions` VALUES  (1,0x01,1,3,1),
 (2,0x01,1,6,1),
 (3,0x01,1,7,1),
 (4,0x01,1,8,3),
 (5,0x01,1,4,1),
 (6,0x01,1,5,2),
 (7,0x01,1,10,1),
 (8,0x01,1,9,1),
 (9,0x01,1,16,2),
 (10,0x01,1,11,3),
 (11,0x01,1,15,3),
 (12,0x01,1,17,3),
 (13,0x01,1,18,1),
 (14,0x01,1,12,3),
 (15,0x01,1,14,3),
 (16,0x01,1,13,2),
 (17,0x01,1,21,2),
 (18,0x01,1,19,1),
 (19,0x01,1,20,3),
 (20,0x01,1,22,1),
 (21,0x01,1,23,1),
 (22,0x01,1,34,1),
 (23,0x01,1,24,3),
 (24,0x01,1,36,2),
 (25,0x01,1,35,1),
 (26,0x01,1,37,1),
 (27,0x01,1,39,2),
 (28,0x01,1,42,3),
 (29,0x01,1,29,1),
 (30,0x01,1,40,3),
 (31,0x01,1,30,1),
 (32,0x01,1,38,1),
 (33,0x01,1,41,1),
 (34,0x01,1,28,1),
 (35,0x01,1,2,1),
 (36,0x01,1,1,3),
 (37,0x01,1,25,1),
 (38,0x01,1,27,2),
 (39,0x01,1,26,1),
 (40,0x01,1,31,2),
 (41,0x01,1,32,1),
 (42,0x01,1,33,3);
UNLOCK TABLES;
/*!40000 ALTER TABLE `e2_permissions` ENABLE KEYS */;


--
-- Definition of table `connect`.`e2_photos`
--

DROP TABLE IF EXISTS `connect`.`e2_photos`;
CREATE TABLE  `connect`.`e2_photos` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_entered` datetime DEFAULT NULL,
  `_modified` datetime DEFAULT NULL,
  `_name` varchar(255) DEFAULT NULL,
  `_album_id` bigint(20) NOT NULL,
  `_entered_by_id` bigint(20) NOT NULL,
  `_modified_by_id` bigint(20) NOT NULL,
  `_real_file_id` bigint(20) NOT NULL,
  PRIMARY KEY (`_id`),
  KEY `FK2F77149337D54C87` (`_album_id`),
  KEY `FK2F771493402E06BB` (`_real_file_id`),
  KEY `FK2F771493FA11FB90` (`_entered_by_id`),
  KEY `FK2F7714938DB1780` (`_modified_by_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`e2_photos`
--

/*!40000 ALTER TABLE `e2_photos` DISABLE KEYS */;
LOCK TABLES `e2_photos` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `e2_photos` ENABLE KEYS */;


--
-- Definition of table `connect`.`e2_profiles`
--

DROP TABLE IF EXISTS `connect`.`e2_profiles`;
CREATE TABLE  `connect`.`e2_profiles` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_attributes` longtext,
  `_description` longtext,
  `_entered` datetime DEFAULT NULL,
  `_modified` datetime DEFAULT NULL,
  `_short_description` longtext,
  `_title` varchar(255) DEFAULT NULL,
  `_entered_by_id` bigint(20) NOT NULL,
  `_logo_id` bigint(20) DEFAULT NULL,
  `_modified_by_id` bigint(20) NOT NULL,
  `_project_id` bigint(20) NOT NULL,
  PRIMARY KEY (`_id`),
  KEY `FK3E3A7B7CFA11FB90` (`_entered_by_id`),
  KEY `FK3E3A7B7CADE2AB90` (`_project_id`),
  KEY `FK3E3A7B7C8DB1780` (`_modified_by_id`),
  KEY `FK3E3A7B7CBCE64EAF` (`_logo_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`e2_profiles`
--

/*!40000 ALTER TABLE `e2_profiles` DISABLE KEYS */;
LOCK TABLES `e2_profiles` WRITE;
INSERT INTO `connect`.`e2_profiles` VALUES  (1,NULL,'',NULL,'2011-04-25 18:43:27','this is gavin\'s profile','Gavin Hu',1,NULL,1,1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `e2_profiles` ENABLE KEYS */;


--
-- Definition of table `connect`.`e2_project_categories`
--

DROP TABLE IF EXISTS `connect`.`e2_project_categories`;
CREATE TABLE  `connect`.`e2_project_categories` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_code` varchar(255) NOT NULL,
  `_enabled` bit(1) DEFAULT NULL,
  `_label` varchar(255) NOT NULL,
  `_level` int(11) DEFAULT NULL,
  `_site_id` bigint(20) NOT NULL,
  `_parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`_id`),
  KEY `FKDA9098147DDB8E5F` (`_parent_id`),
  KEY `FKDA9098149DE3DFA4` (`_site_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`e2_project_categories`
--

/*!40000 ALTER TABLE `e2_project_categories` DISABLE KEYS */;
LOCK TABLES `e2_project_categories` WRITE;
INSERT INTO `connect`.`e2_project_categories` VALUES  (1,'people',0x01,'成员',5,1,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `e2_project_categories` ENABLE KEYS */;


--
-- Definition of table `connect`.`e2_project_features`
--

DROP TABLE IF EXISTS `connect`.`e2_project_features`;
CREATE TABLE  `connect`.`e2_project_features` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_code` varchar(255) NOT NULL,
  `_label` varchar(255) DEFAULT NULL,
  `_level` int(11) DEFAULT NULL,
  `_show` bit(1) DEFAULT NULL,
  `_project_id` bigint(20) NOT NULL,
  `_role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`_id`),
  KEY `FK1F92543572C99D44` (`_role_id`),
  KEY `FK1F925435ADE2AB90` (`_project_id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`e2_project_features`
--

/*!40000 ALTER TABLE `e2_project_features` DISABLE KEYS */;
LOCK TABLES `e2_project_features` WRITE;
INSERT INTO `connect`.`e2_project_features` VALUES  (1,'profile','个人主页',NULL,0x01,1,3),
 (2,'knowledge','问答',NULL,0x01,1,3),
 (3,'calendar','日历',NULL,0x01,1,2),
 (4,'blog','博客',NULL,0x01,1,3),
 (5,'gallery','相册',NULL,0x01,1,3),
 (6,'discussion','讨论',NULL,0x01,1,3),
 (7,'document','文档',NULL,0x01,1,2),
 (8,'team','好友',NULL,0x01,1,3),
 (9,'message','消息',NULL,0x01,1,1),
 (10,'admin','管理',NULL,0x01,1,1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `e2_project_features` ENABLE KEYS */;


--
-- Definition of table `connect`.`e2_projects`
--

DROP TABLE IF EXISTS `connect`.`e2_projects`;
CREATE TABLE  `connect`.`e2_projects` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_enabled` bit(1) DEFAULT NULL,
  `_entered` datetime NOT NULL,
  `_modified` datetime NOT NULL,
  `_title` varchar(255) NOT NULL,
  `_unique_id` varchar(255) NOT NULL,
  `_category_id` bigint(20) NOT NULL,
  `_entered_by_id` bigint(20) DEFAULT NULL,
  `_modified_by_id` bigint(20) DEFAULT NULL,
  `_sub_category1_id` bigint(20) DEFAULT NULL,
  `_sub_category2_id` bigint(20) DEFAULT NULL,
  `_sub_category3_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`_id`),
  UNIQUE KEY `_unique_id` (`_unique_id`),
  KEY `FK3E70E80CC2E9DEF3` (`_sub_category1_id`),
  KEY `FK3E70E80C91C8ED4B` (`_category_id`),
  KEY `FK3E70E80CC2EA5352` (`_sub_category2_id`),
  KEY `FK3E70E80CFA11FB90` (`_entered_by_id`),
  KEY `FK3E70E80CC2EAC7B1` (`_sub_category3_id`),
  KEY `FK3E70E80C8DB1780` (`_modified_by_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`e2_projects`
--

/*!40000 ALTER TABLE `e2_projects` DISABLE KEYS */;
LOCK TABLES `e2_projects` WRITE;
INSERT INTO `connect`.`e2_projects` VALUES  (1,0x01,'2011-04-25 18:35:18','2011-04-25 18:35:18','Gavin Hu','haozhonghu-1',1,1,1,NULL,NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `e2_projects` ENABLE KEYS */;


--
-- Definition of table `connect`.`e2_questions`
--

DROP TABLE IF EXISTS `connect`.`e2_questions`;
CREATE TABLE  `connect`.`e2_questions` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_content` longtext NOT NULL,
  `_entered` datetime DEFAULT NULL,
  `_modified` datetime DEFAULT NULL,
  `_title` varchar(255) NOT NULL,
  `_answer_id` bigint(20) DEFAULT NULL,
  `_entered_by_id` bigint(20) NOT NULL,
  `_modified_by_id` bigint(20) NOT NULL,
  `_project_id` bigint(20) NOT NULL,
  PRIMARY KEY (`_id`),
  KEY `FK5AC763FBFA11FB90` (`_entered_by_id`),
  KEY `FK5AC763FBADE2AB90` (`_project_id`),
  KEY `FK5AC763FB8DB1780` (`_modified_by_id`),
  KEY `FK5AC763FBDA978141` (`_answer_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`e2_questions`
--

/*!40000 ALTER TABLE `e2_questions` DISABLE KEYS */;
LOCK TABLES `e2_questions` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `e2_questions` ENABLE KEYS */;


--
-- Definition of table `connect`.`e2_rating`
--

DROP TABLE IF EXISTS `connect`.`e2_rating`;
CREATE TABLE  `connect`.`e2_rating` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_entered` datetime NOT NULL,
  `_linked_id` bigint(20) NOT NULL,
  `_rating` int(11) NOT NULL,
  `_target` varchar(255) NOT NULL,
  `_entered_by_id` bigint(20) NOT NULL,
  PRIMARY KEY (`_id`),
  KEY `FK32803DAFFA11FB90` (`_entered_by_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`e2_rating`
--

/*!40000 ALTER TABLE `e2_rating` DISABLE KEYS */;
LOCK TABLES `e2_rating` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `e2_rating` ENABLE KEYS */;


--
-- Definition of table `connect`.`e2_replies`
--

DROP TABLE IF EXISTS `connect`.`e2_replies`;
CREATE TABLE  `connect`.`e2_replies` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_content` varchar(255) NOT NULL,
  `_enabled` bit(1) DEFAULT NULL,
  `_entered` datetime NOT NULL,
  `_modified` datetime DEFAULT NULL,
  `_subject` varchar(255) NOT NULL,
  `_entered_by_id` bigint(20) NOT NULL,
  `_modified_by_id` bigint(20) DEFAULT NULL,
  `_quote_id` bigint(20) DEFAULT NULL,
  `_topic_id` bigint(20) NOT NULL,
  PRIMARY KEY (`_id`),
  KEY `FK2423C736FA11FB90` (`_entered_by_id`),
  KEY `FK2423C7369CC52D89` (`_topic_id`),
  KEY `FK2423C7368DB1780` (`_modified_by_id`),
  KEY `FK2423C736A14D62D7` (`_quote_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`e2_replies`
--

/*!40000 ALTER TABLE `e2_replies` DISABLE KEYS */;
LOCK TABLES `e2_replies` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `e2_replies` ENABLE KEYS */;


--
-- Definition of table `connect`.`e2_resources`
--

DROP TABLE IF EXISTS `connect`.`e2_resources`;
CREATE TABLE  `connect`.`e2_resources` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_code` varchar(255) NOT NULL,
  `_description` varchar(255) DEFAULT NULL,
  `_enabled` bit(1) DEFAULT NULL,
  `_name` varchar(255) NOT NULL,
  PRIMARY KEY (`_id`),
  UNIQUE KEY `_code` (`_code`)
) ENGINE=MyISAM AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`e2_resources`
--

/*!40000 ALTER TABLE `e2_resources` DISABLE KEYS */;
LOCK TABLES `e2_resources` WRITE;
INSERT INTO `connect`.`e2_resources` VALUES  (1,'profile-profile-view','',0x01,'主页查看'),
 (2,'profile-profile-edit','',0x01,'主页编辑'),
 (3,'calendar-event-add','',0x01,'日历添加'),
 (4,'calendar-event-edit','',0x01,'日历编辑'),
 (5,'calendar-event-view','',0x01,'日历查看'),
 (6,'blog-post-add','',0x01,'博文添加'),
 (7,'blog-post-edit','',0x01,'博文编辑'),
 (8,'blog-post-view','',0x01,'博文查看'),
 (9,'discussion-forum-add','',0x01,'讨论版块添加'),
 (10,'discussion-forum-edit','',0x01,'讨论版块编辑'),
 (11,'discussion-forum-view','',0x01,'讨论版块查看'),
 (12,'discussion-topic-add','',0x01,'讨论话题添加'),
 (13,'discussion-topic-edit','',0x01,'讨论话题编辑'),
 (14,'discussion-topic-view','',0x01,'讨论话题查看'),
 (15,'discussion-reply-add','',0x01,'讨论回复添加'),
 (16,'discussion-reply-edit','',0x01,'讨论回复编辑'),
 (17,'discussion-reply-view','',0x01,'讨论回复查看'),
 (18,'document-file-add','',0x01,'文档文件添加'),
 (19,'document-file-edit','',0x01,'文档文件编辑'),
 (20,'document-file-view','',0x01,'文档文件查看'),
 (21,'document-file-download','',0x01,'文档文件下载'),
 (22,'document-folder-add','',0x01,'文档目录添加'),
 (23,'document-folder-edit','',0x01,'文档目录编辑'),
 (24,'document-folder-view','',0x01,'文档目录查看'),
 (25,'team-member-add','',0x01,'团队成员添加'),
 (26,'team-member-edit','',0x01,'团队成员编辑'),
 (27,'team-member-view','',0x01,'团队成员查看'),
 (28,'message-message-add','',0x01,'消息添加'),
 (29,'message-message-edit','',0x01,'消息编辑'),
 (30,'message-message-view','',0x01,'消息查看'),
 (31,'vblog-activity-add','',0x01,'微薄添加'),
 (32,'vblog-activity-edit','',0x01,'微薄编辑'),
 (33,'vblog-activity-view','',0x01,'微薄查看'),
 (34,'gallery-album-add','',0x01,'相册添加'),
 (35,'gallery-album-edit','',0x01,'相册编辑'),
 (36,'gallery-album-view','',0x01,'相册查看'),
 (37,'gallery-photo-add','',0x01,'相片上传'),
 (38,'gallery-photo-edit','',0x01,'相片编辑'),
 (39,'gallery-photo-view','',0x01,'相片查看'),
 (40,'knowledge-question-add','',0x01,'问题添加'),
 (41,'knowledge-question-edit','',0x01,'问题编辑'),
 (42,'knowledge-question-view','',0x01,'问题查看');
UNLOCK TABLES;
/*!40000 ALTER TABLE `e2_resources` ENABLE KEYS */;


--
-- Definition of table `connect`.`e2_roles`
--

DROP TABLE IF EXISTS `connect`.`e2_roles`;
CREATE TABLE  `connect`.`e2_roles` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_code` varchar(255) NOT NULL,
  `_description` varchar(255) DEFAULT NULL,
  `_enabled` bit(1) DEFAULT NULL,
  `_level` int(11) DEFAULT NULL,
  `_name` varchar(255) NOT NULL,
  `_category_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`_id`),
  KEY `FKF965386B91C8ED4B` (`_category_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`e2_roles`
--

/*!40000 ALTER TABLE `e2_roles` DISABLE KEYS */;
LOCK TABLES `e2_roles` WRITE;
INSERT INTO `connect`.`e2_roles` VALUES  (1,'admin','',0x01,5,'主人',1),
 (2,'friend','',0x01,50,'好友',1),
 (3,'guest','',0x01,100,'游客',1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `e2_roles` ENABLE KEYS */;


--
-- Definition of table `connect`.`e2_site_links`
--

DROP TABLE IF EXISTS `connect`.`e2_site_links`;
CREATE TABLE  `connect`.`e2_site_links` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_code` varchar(255) DEFAULT NULL,
  `_href` varchar(255) DEFAULT NULL,
  `_text` varchar(255) DEFAULT NULL,
  `_site_id` bigint(20) NOT NULL,
  PRIMARY KEY (`_id`),
  KEY `FK16EF3D39DE3DFA4` (`_site_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`e2_site_links`
--

/*!40000 ALTER TABLE `e2_site_links` DISABLE KEYS */;
LOCK TABLES `e2_site_links` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `e2_site_links` ENABLE KEYS */;


--
-- Definition of table `connect`.`e2_sites`
--

DROP TABLE IF EXISTS `connect`.`e2_sites`;
CREATE TABLE  `connect`.`e2_sites` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_context_path` varchar(255) DEFAULT NULL,
  `_copyright` varchar(255) DEFAULT NULL,
  `_description` longtext,
  `_domain` varchar(255) NOT NULL,
  `_enabled` bit(1) DEFAULT NULL,
  `_keywords` varchar(255) DEFAULT NULL,
  `_port` int(11) DEFAULT NULL,
  `_ssl` bit(1) DEFAULT NULL,
  `_title` varchar(255) NOT NULL,
  `_mail_settings_id` bigint(20) DEFAULT NULL,
  `_theme_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`_id`),
  UNIQUE KEY `_domain` (`_domain`),
  KEY `FKF970B3BAA290D111` (`_mail_settings_id`),
  KEY `FKF970B3BA387B1310` (`_theme_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`e2_sites`
--

/*!40000 ALTER TABLE `e2_sites` DISABLE KEYS */;
LOCK TABLES `e2_sites` WRITE;
INSERT INTO `connect`.`e2_sites` VALUES  (1,'connect-web','','','localhost',0x01,'',8080,0x00,'Demo',NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `e2_sites` ENABLE KEYS */;


--
-- Definition of table `connect`.`e2_statistics`
--

DROP TABLE IF EXISTS `connect`.`e2_statistics`;
CREATE TABLE  `connect`.`e2_statistics` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_count` bigint(20) DEFAULT NULL,
  `_entity` varchar(255) DEFAULT NULL,
  `_linked_id` bigint(20) NOT NULL,
  `_project_id` bigint(20) NOT NULL,
  PRIMARY KEY (`_id`),
  KEY `FKD59EE435ADE2AB90` (`_project_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`e2_statistics`
--

/*!40000 ALTER TABLE `e2_statistics` DISABLE KEYS */;
LOCK TABLES `e2_statistics` WRITE;
INSERT INTO `connect`.`e2_statistics` VALUES  (1,3,'Profile',1,1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `e2_statistics` ENABLE KEYS */;


--
-- Definition of table `connect`.`e2_tags`
--

DROP TABLE IF EXISTS `connect`.`e2_tags`;
CREATE TABLE  `connect`.`e2_tags` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_entity` varchar(255) NOT NULL,
  `_linked_id` bigint(20) NOT NULL,
  `_name` varchar(255) NOT NULL,
  PRIMARY KEY (`_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`e2_tags`
--

/*!40000 ALTER TABLE `e2_tags` DISABLE KEYS */;
LOCK TABLES `e2_tags` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `e2_tags` ENABLE KEYS */;


--
-- Definition of table `connect`.`e2_team_members`
--

DROP TABLE IF EXISTS `connect`.`e2_team_members`;
CREATE TABLE  `connect`.`e2_team_members` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_enabled` bit(1) DEFAULT NULL,
  `_status` varchar(255) DEFAULT NULL,
  `_project_id` bigint(20) NOT NULL,
  `_role_id` bigint(20) NOT NULL,
  `_user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`_id`),
  KEY `FKC450BBC972C99D44` (`_role_id`),
  KEY `FKC450BBC917F46124` (`_user_id`),
  KEY `FKC450BBC9ADE2AB90` (`_project_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`e2_team_members`
--

/*!40000 ALTER TABLE `e2_team_members` DISABLE KEYS */;
LOCK TABLES `e2_team_members` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `e2_team_members` ENABLE KEYS */;


--
-- Definition of table `connect`.`e2_templates`
--

DROP TABLE IF EXISTS `connect`.`e2_templates`;
CREATE TABLE  `connect`.`e2_templates` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_code` varchar(255) NOT NULL,
  `_content` longtext NOT NULL,
  `_enabled` bit(1) DEFAULT NULL,
  `_name` varchar(255) NOT NULL,
  `_category_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`_id`),
  KEY `FK3B20E5E791C8ED4B` (`_category_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`e2_templates`
--

/*!40000 ALTER TABLE `e2_templates` DISABLE KEYS */;
LOCK TABLES `e2_templates` WRITE;
INSERT INTO `connect`.`e2_templates` VALUES  (1,'people-features','[label=个人主页,code=profile,show=true,role=guest]\n[label=问答,code=knowledge,show=true,role=guest]\n[label=日历,code=calendar,show=true,role=friend]\n[label=博客,code=blog,show=true,role=guest]\n[label=相册,code=gallery,show=true,role=guest]\n[label=讨论,code=discussion,show=true,role=guest]\n[label=文档,code=document,show=true,role=friend]\n[label=好友,code=team,show=true,role=guest]\n[label=消息,code=message,show=true,role=admin]\n[label=管理,code=admin,show=true,role=admin]',0x01,'成员模块',1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `e2_templates` ENABLE KEYS */;


--
-- Definition of table `connect`.`e2_themes`
--

DROP TABLE IF EXISTS `connect`.`e2_themes`;
CREATE TABLE  `connect`.`e2_themes` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_enabled` bit(1) DEFAULT NULL,
  `_layout` varchar(255) DEFAULT NULL,
  `_layout_popup` varchar(255) DEFAULT NULL,
  `_name` varchar(255) NOT NULL,
  PRIMARY KEY (`_id`),
  UNIQUE KEY `_name` (`_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`e2_themes`
--

/*!40000 ALTER TABLE `e2_themes` DISABLE KEYS */;
LOCK TABLES `e2_themes` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `e2_themes` ENABLE KEYS */;


--
-- Definition of table `connect`.`e2_topic_categories`
--

DROP TABLE IF EXISTS `connect`.`e2_topic_categories`;
CREATE TABLE  `connect`.`e2_topic_categories` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_enabled` bit(1) DEFAULT NULL,
  `_label` varchar(255) NOT NULL,
  `_project_id` bigint(20) NOT NULL,
  PRIMARY KEY (`_id`),
  KEY `FKD09A28BEADE2AB90` (`_project_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`e2_topic_categories`
--

/*!40000 ALTER TABLE `e2_topic_categories` DISABLE KEYS */;
LOCK TABLES `e2_topic_categories` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `e2_topic_categories` ENABLE KEYS */;


--
-- Definition of table `connect`.`e2_topics`
--

DROP TABLE IF EXISTS `connect`.`e2_topics`;
CREATE TABLE  `connect`.`e2_topics` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_content` varchar(255) NOT NULL,
  `_enabled` bit(1) DEFAULT NULL,
  `_entered` datetime NOT NULL,
  `_modified` datetime NOT NULL,
  `_question` bit(1) DEFAULT NULL,
  `_subject` varchar(255) NOT NULL,
  `_answer_id` bigint(20) DEFAULT NULL,
  `_category_id` bigint(20) DEFAULT NULL,
  `_entered_by_id` bigint(20) NOT NULL,
  `_forum_id` bigint(20) NOT NULL,
  `_modified_by_id` bigint(20) NOT NULL,
  PRIMARY KEY (`_id`),
  KEY `FK36AD65368DE2D65A` (`_category_id`),
  KEY `FK36AD6536F1C6D049` (`_forum_id`),
  KEY `FK36AD6536FA11FB90` (`_entered_by_id`),
  KEY `FK36AD65368DB1780` (`_modified_by_id`),
  KEY `FK36AD6536DC808B77` (`_answer_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`e2_topics`
--

/*!40000 ALTER TABLE `e2_topics` DISABLE KEYS */;
LOCK TABLES `e2_topics` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `e2_topics` ENABLE KEYS */;


--
-- Definition of table `connect`.`e2_users`
--

DROP TABLE IF EXISTS `connect`.`e2_users`;
CREATE TABLE  `connect`.`e2_users` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_email` varchar(255) NOT NULL,
  `_enabled` bit(1) NOT NULL,
  `_entered` datetime NOT NULL,
  `_last_login` datetime DEFAULT NULL,
  `_locale` varchar(255) DEFAULT NULL,
  `_nickname` varchar(255) NOT NULL,
  `_password` varchar(255) NOT NULL,
  `_timezone` varchar(255) DEFAULT NULL,
  `_token` varchar(255) DEFAULT NULL,
  `_username` varchar(255) NOT NULL,
  `_project_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`_id`),
  UNIQUE KEY `_email` (`_email`),
  UNIQUE KEY `_username` (`_username`),
  KEY `FKF99137B6ADE2AB90` (`_project_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connect`.`e2_users`
--

/*!40000 ALTER TABLE `e2_users` DISABLE KEYS */;
LOCK TABLES `e2_users` WRITE;
INSERT INTO `connect`.`e2_users` VALUES  (1,'haozhonghu@hotmail.com',0x01,'2011-04-25 18:35:18','2011-04-25 18:55:40',NULL,'Gavin Hu','123456',NULL,'2eaa8aa7-3cdd-4625-b234-64241c729cea','haozhonghu@hotmail.com',1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `e2_users` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
