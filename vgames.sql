-- MySQL dump 10.13  Distrib 5.7.23, for Linux (x86_64)
--
-- Host: vgames.mysql.pythonanywhere-services.com    Database: vgames$vgames
-- ------------------------------------------------------
-- Server version	5.7.21-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `auth_group`
--

DROP TABLE IF EXISTS `auth_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_group`
--

LOCK TABLES `auth_group` WRITE;
/*!40000 ALTER TABLE `auth_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `auth_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_group_permissions`
--

DROP TABLE IF EXISTS `auth_group_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_group_permissions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_group_permissions_group_id_permission_id_0cd325b0_uniq` (`group_id`,`permission_id`),
  KEY `auth_group_permissio_permission_id_84c5c92e_fk_auth_perm` (`permission_id`),
  CONSTRAINT `auth_group_permissio_permission_id_84c5c92e_fk_auth_perm` FOREIGN KEY (`permission_id`) REFERENCES `auth_permission` (`id`),
  CONSTRAINT `auth_group_permissions_group_id_b120cbf9_fk_auth_group_id` FOREIGN KEY (`group_id`) REFERENCES `auth_group` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_group_permissions`
--

LOCK TABLES `auth_group_permissions` WRITE;
/*!40000 ALTER TABLE `auth_group_permissions` DISABLE KEYS */;
/*!40000 ALTER TABLE `auth_group_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_permission`
--

DROP TABLE IF EXISTS `auth_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `content_type_id` int(11) NOT NULL,
  `codename` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_permission_content_type_id_codename_01ab375a_uniq` (`content_type_id`,`codename`),
  CONSTRAINT `auth_permission_content_type_id_2f476e4b_fk_django_co` FOREIGN KEY (`content_type_id`) REFERENCES `django_content_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_permission`
--

LOCK TABLES `auth_permission` WRITE;
/*!40000 ALTER TABLE `auth_permission` DISABLE KEYS */;
INSERT INTO `auth_permission` VALUES (1,'Can add log entry',1,'add_logentry'),(2,'Can change log entry',1,'change_logentry'),(3,'Can delete log entry',1,'delete_logentry'),(4,'Can view log entry',1,'view_logentry'),(5,'Can add permission',2,'add_permission'),(6,'Can change permission',2,'change_permission'),(7,'Can delete permission',2,'delete_permission'),(8,'Can view permission',2,'view_permission'),(9,'Can add group',3,'add_group'),(10,'Can change group',3,'change_group'),(11,'Can delete group',3,'delete_group'),(12,'Can view group',3,'view_group'),(13,'Can add user',4,'add_user'),(14,'Can change user',4,'change_user'),(15,'Can delete user',4,'delete_user'),(16,'Can view user',4,'view_user'),(17,'Can add content type',5,'add_contenttype'),(18,'Can change content type',5,'change_contenttype'),(19,'Can delete content type',5,'delete_contenttype'),(20,'Can view content type',5,'view_contenttype'),(21,'Can add session',6,'add_session'),(22,'Can change session',6,'change_session'),(23,'Can delete session',6,'delete_session'),(24,'Can view session',6,'view_session'),(25,'Can add Εταιρία',7,'add_company'),(26,'Can change Εταιρία',7,'change_company'),(27,'Can delete Εταιρία',7,'delete_company'),(28,'Can view Εταιρία',7,'view_company'),(29,'Can add Χαρακτηριστικό',8,'add_feature'),(30,'Can change Χαρακτηριστικό',8,'change_feature'),(31,'Can delete Χαρακτηριστικό',8,'delete_feature'),(32,'Can view Χαρακτηριστικό',8,'view_feature'),(33,'Can add Γλώσσα',9,'add_language'),(34,'Can change Γλώσσα',9,'change_language'),(35,'Can delete Γλώσσα',9,'delete_language'),(36,'Can view Γλώσσα',9,'view_language'),(37,'Can add Πλατφόρμα',10,'add_platform'),(38,'Can change Πλατφόρμα',10,'change_platform'),(39,'Can delete Πλατφόρμα',10,'delete_platform'),(40,'Can view Πλατφόρμα',10,'view_platform'),(41,'Can add Κατηγορία',11,'add_genre'),(42,'Can change Κατηγορία',11,'change_genre'),(43,'Can delete Κατηγορία',11,'delete_genre'),(44,'Can view Κατηγορία',11,'view_genre'),(45,'Can add Παιχνίδι',12,'add_game'),(46,'Can change Παιχνίδι',12,'change_game'),(47,'Can delete Παιχνίδι',12,'delete_game'),(48,'Can view Παιχνίδι',12,'view_game'),(49,'Can add Εικόνα',13,'add_gallery'),(50,'Can change Εικόνα',13,'change_gallery'),(51,'Can delete Εικόνα',13,'delete_gallery'),(52,'Can view Εικόνα',13,'view_gallery');
/*!40000 ALTER TABLE `auth_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_user`
--

DROP TABLE IF EXISTS `auth_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(128) NOT NULL,
  `last_login` datetime(6) DEFAULT NULL,
  `is_superuser` tinyint(1) NOT NULL,
  `username` varchar(150) NOT NULL,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(150) NOT NULL,
  `email` varchar(254) NOT NULL,
  `is_staff` tinyint(1) NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  `date_joined` datetime(6) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_user`
--

LOCK TABLES `auth_user` WRITE;
/*!40000 ALTER TABLE `auth_user` DISABLE KEYS */;
INSERT INTO `auth_user` VALUES (1,'pbkdf2_sha256$150000$sf6Ozhqv3yrB$AGrnyFFqCYkw8t79qK/CxpBSQxpRNUzuvBugg6ys0lY=','2019-05-29 15:00:07.797572',1,'sotiris','','','sotiris@serres.gr',1,1,'2019-04-16 10:52:14.310047'),(2,'pbkdf2_sha256$150000$qgJemmHfChZA$NlY2Jw6d56jN7dBg+MfvWinqd5JZ/3U7wKNbE74H2Q4=','2019-05-20 08:46:50.864302',0,'sakis','ΑΘΑΝΑΣΙΟΣ','ΤΑΟΥΣΑΝΗΣ','athan.taousanis@gmail.com',0,1,'2019-05-16 16:38:26.000000');
/*!40000 ALTER TABLE `auth_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_user_groups`
--

DROP TABLE IF EXISTS `auth_user_groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_user_groups` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_user_groups_user_id_group_id_94350c0c_uniq` (`user_id`,`group_id`),
  KEY `auth_user_groups_group_id_97559544_fk_auth_group_id` (`group_id`),
  CONSTRAINT `auth_user_groups_group_id_97559544_fk_auth_group_id` FOREIGN KEY (`group_id`) REFERENCES `auth_group` (`id`),
  CONSTRAINT `auth_user_groups_user_id_6a12ed8b_fk_auth_user_id` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_user_groups`
--

LOCK TABLES `auth_user_groups` WRITE;
/*!40000 ALTER TABLE `auth_user_groups` DISABLE KEYS */;
/*!40000 ALTER TABLE `auth_user_groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_user_user_permissions`
--

DROP TABLE IF EXISTS `auth_user_user_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_user_user_permissions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_user_user_permissions_user_id_permission_id_14a6b632_uniq` (`user_id`,`permission_id`),
  KEY `auth_user_user_permi_permission_id_1fbb5f2c_fk_auth_perm` (`permission_id`),
  CONSTRAINT `auth_user_user_permi_permission_id_1fbb5f2c_fk_auth_perm` FOREIGN KEY (`permission_id`) REFERENCES `auth_permission` (`id`),
  CONSTRAINT `auth_user_user_permissions_user_id_a95ead1b_fk_auth_user_id` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_user_user_permissions`
--

LOCK TABLES `auth_user_user_permissions` WRITE;
/*!40000 ALTER TABLE `auth_user_user_permissions` DISABLE KEYS */;
/*!40000 ALTER TABLE `auth_user_user_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `desc` varchar(100) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (1,'Nintendo','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000'),(2,'Valve Corporation','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000'),(3,'Rockstar Games','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000'),(4,'Electronic Arts','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000'),(5,'Activision Blizzard','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000'),(6,'Sony Computer Entertainment','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000'),(7,'Ubisoft','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000'),(8,'Sega Games Co. Ltd','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000'),(9,'BioWare','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000'),(10,'Microsoft Corporation','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000'),(11,'Epic Games','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000'),(12,'id Software','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000'),(13,'Lucas Arts','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000'),(14,'Sierra','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000'),(15,'Bizzard Entertainment, Inc','2019-04-16 11:19:21.636786','2019-04-16 11:19:21.636786'),(16,'Crate Entertainment','2019-04-16 11:23:18.022659','2019-04-16 11:23:18.022659'),(17,'Cyan Inc','2019-04-16 11:38:05.348139','2019-04-16 11:38:05.348139'),(18,'Ghost Town Games Ltd','2019-04-22 08:35:52.893576','2019-04-22 08:35:52.893635'),(19,'Crytek Studios','2019-04-22 08:45:29.581128','2019-04-22 08:45:29.581177'),(20,'Bugbear Entertainment','2019-05-02 17:25:36.036444','2019-05-02 17:25:36.036483'),(21,'Gremlin Interactive Ltd.','2019-05-02 17:37:27.297586','2019-05-02 17:37:27.297629');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `django_admin_log`
--

DROP TABLE IF EXISTS `django_admin_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `django_admin_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `action_time` datetime(6) NOT NULL,
  `object_id` longtext,
  `object_repr` varchar(200) NOT NULL,
  `action_flag` smallint(5) unsigned NOT NULL,
  `change_message` longtext NOT NULL,
  `content_type_id` int(11) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `django_admin_log_content_type_id_c4bce8eb_fk_django_co` (`content_type_id`),
  KEY `django_admin_log_user_id_c564eba6_fk_auth_user_id` (`user_id`),
  CONSTRAINT `django_admin_log_content_type_id_c4bce8eb_fk_django_co` FOREIGN KEY (`content_type_id`) REFERENCES `django_content_type` (`id`),
  CONSTRAINT `django_admin_log_user_id_c564eba6_fk_auth_user_id` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `django_admin_log`
--

LOCK TABLES `django_admin_log` WRITE;
/*!40000 ALTER TABLE `django_admin_log` DISABLE KEYS */;
INSERT INTO `django_admin_log` VALUES (1,'2019-04-16 11:13:48.881859','1','Grim Dawn - Forgotten Gods',1,'[{\"added\": {}}]',12,1),(2,'2019-04-16 11:18:37.453354','10','Fantasy',1,'[{\"added\": {}}]',11,1),(3,'2019-04-16 11:19:21.637783','15','Bizzard Entertainment, Inc',1,'[{\"added\": {}}]',7,1),(4,'2019-04-16 11:21:27.261647','2','Diablo',1,'[{\"added\": {}}]',12,1),(5,'2019-04-16 11:23:18.022659','16','Crate Entertainment',1,'[{\"added\": {}}]',7,1),(6,'2019-04-16 11:28:14.584842','2','Diablo',2,'[{\"changed\": {\"fields\": [\"image\"]}}]',12,1),(7,'2019-04-16 11:28:51.599138','1','Grim Dawn - Forgotten Gods',2,'[{\"changed\": {\"fields\": [\"image\"]}}]',12,1),(8,'2019-04-16 11:33:46.963089','21','Ελληνικά',1,'[{\"added\": {}}]',9,1),(9,'2019-04-16 11:34:03.935300','1','Grim Dawn - Forgotten Gods',2,'[{\"changed\": {\"fields\": [\"company\", \"desc\", \"rating\", \"url\", \"features\", \"platforms\", \"languages\", \"genres\"]}}]',12,1),(10,'2019-04-16 11:35:24.348713','1','Grim Dawn - Forgotten Gods',2,'[{\"changed\": {\"fields\": [\"image\"]}}]',12,1),(11,'2019-04-16 11:38:05.348139','17','Cyan Inc',1,'[{\"added\": {}}]',7,1),(12,'2019-04-16 11:44:06.899902','11','FPP',1,'[{\"added\": {}}]',11,1),(13,'2019-04-16 11:44:13.945111','12','Puzzle',1,'[{\"added\": {}}]',11,1),(14,'2019-04-16 11:44:26.876996','3','Odbuction',1,'[{\"added\": {}}]',12,1),(15,'2019-04-16 11:48:22.690610','1','Gallery object (1)',1,'[{\"added\": {}}]',13,1),(16,'2019-04-16 11:48:30.905879','2','Gallery object (2)',1,'[{\"added\": {}}]',13,1),(17,'2019-04-16 11:48:37.898666','3','Gallery object (3)',1,'[{\"added\": {}}]',13,1),(18,'2019-04-16 11:55:33.625575','4','Grim Dawn - Forgotten Gods (4)',1,'[{\"added\": {}}]',13,1),(19,'2019-04-16 11:55:39.289072','5','Grim Dawn - Forgotten Gods (5)',1,'[{\"added\": {}}]',13,1),(20,'2019-04-16 11:55:44.114392','6','Grim Dawn - Forgotten Gods (6)',1,'[{\"added\": {}}]',13,1),(21,'2019-04-16 11:55:49.353492','7','Grim Dawn - Forgotten Gods (7)',1,'[{\"added\": {}}]',13,1),(22,'2019-04-16 11:55:54.374632','8','Grim Dawn - Forgotten Gods (8)',1,'[{\"added\": {}}]',13,1),(23,'2019-04-21 11:20:56.225911','9','Diablo (9)',1,'[{\"added\": {}}]',13,1),(24,'2019-04-21 11:21:11.974602','10','Diablo (10)',1,'[{\"added\": {}}]',13,1),(25,'2019-04-21 11:21:28.447057','11','Diablo (11)',1,'[{\"added\": {}}]',13,1),(26,'2019-04-21 11:21:47.662833','12','Diablo (12)',1,'[{\"added\": {}}]',13,1),(27,'2019-04-21 11:22:15.642843','13','Diablo (13)',1,'[{\"added\": {}}]',13,1),(28,'2019-04-21 11:29:44.806686','13','Point-and-click',1,'[{\"added\": {}}]',11,1),(29,'2019-04-21 11:29:52.066746','4','The Curse of Monkey Island',1,'[{\"added\": {}}]',12,1),(30,'2019-04-21 11:31:06.131772','4','The Curse of Monkey Island',2,'[{\"changed\": {\"fields\": [\"image\"]}}]',12,1),(31,'2019-04-21 11:34:21.099595','14','The Curse of Monkey Island (14)',1,'[{\"added\": {}}]',13,1),(32,'2019-04-21 11:34:36.874919','15','The Curse of Monkey Island (15)',1,'[{\"added\": {}}]',13,1),(33,'2019-04-21 11:34:47.154274','16','The Curse of Monkey Island (16)',1,'[{\"added\": {}}]',13,1),(34,'2019-04-21 11:34:55.493884','17','The Curse of Monkey Island (17)',1,'[{\"added\": {}}]',13,1),(35,'2019-04-21 11:35:04.939559','18','The Curse of Monkey Island (18)',1,'[{\"added\": {}}]',13,1),(36,'2019-04-22 08:35:52.896655','18','Ghost Town Games Ltd',1,'[{\"added\": {}}]',7,1),(37,'2019-04-22 08:40:26.468590','5','Riven: The Sequel to Myst',1,'[{\"added\": {}}]',12,1),(38,'2019-04-22 08:42:34.581070','19','Riven: The Sequel to Myst (19)',1,'[{\"added\": {}}]',13,1),(39,'2019-04-22 08:42:42.124818','20','Riven: The Sequel to Myst (20)',1,'[{\"added\": {}}]',13,1),(40,'2019-04-22 08:42:50.426890','21','Riven: The Sequel to Myst (21)',1,'[{\"added\": {}}]',13,1),(41,'2019-04-22 08:42:59.060441','22','Riven: The Sequel to Myst (22)',1,'[{\"added\": {}}]',13,1),(42,'2019-04-22 08:43:10.448665','23','Riven: The Sequel to Myst (23)',1,'[{\"added\": {}}]',13,1),(43,'2019-04-22 08:43:16.580711','24','Riven: The Sequel to Myst (24)',1,'[{\"added\": {}}]',13,1),(44,'2019-04-22 08:43:24.756439','25','Riven: The Sequel to Myst (25)',1,'[{\"added\": {}}]',13,1),(45,'2019-04-22 08:45:29.584510','19','Crytek Studios',1,'[{\"added\": {}}]',7,1),(46,'2019-04-22 08:48:44.252339','14','Sci-fi',1,'[{\"added\": {}}]',11,1),(47,'2019-04-22 08:48:53.660671','6','Crysis',1,'[{\"added\": {}}]',12,1),(48,'2019-04-22 08:52:09.192885','26','Crysis (26)',1,'[{\"added\": {}}]',13,1),(49,'2019-04-22 08:52:15.464644','27','Crysis (27)',1,'[{\"added\": {}}]',13,1),(50,'2019-04-22 08:52:22.244480','28','Crysis (28)',1,'[{\"added\": {}}]',13,1),(51,'2019-04-22 08:52:28.852475','29','Crysis (29)',1,'[{\"added\": {}}]',13,1),(52,'2019-04-22 08:52:35.750227','30','Crysis (30)',1,'[{\"added\": {}}]',13,1),(53,'2019-04-22 08:52:42.772772','31','Crysis (31)',1,'[{\"added\": {}}]',13,1),(54,'2019-04-22 08:54:13.150174','6','Crysis',2,'[]',12,1),(55,'2019-05-02 17:18:03.481413','7','The Dig',1,'[{\"added\": {}}]',12,1),(56,'2019-05-02 17:18:26.279317','32','The Dig (32)',1,'[{\"added\": {}}]',13,1),(57,'2019-05-02 17:18:36.695469','33','The Dig (33)',1,'[{\"added\": {}}]',13,1),(58,'2019-05-02 17:18:43.818909','34','The Dig (34)',1,'[{\"added\": {}}]',13,1),(59,'2019-05-02 17:18:53.711599','35','The Dig (35)',1,'[{\"added\": {}}]',13,1),(60,'2019-05-02 17:19:00.276293','36','The Dig (36)',1,'[{\"added\": {}}]',13,1),(61,'2019-05-02 17:19:07.896271','37','The Dig (37)',1,'[{\"added\": {}}]',13,1),(62,'2019-05-02 17:25:36.040345','20','Bugbear Entertainment',1,'[{\"added\": {}}]',7,1),(63,'2019-05-02 17:27:01.347805','15','Arcade',1,'[{\"added\": {}}]',11,1),(64,'2019-05-02 17:27:09.401566','16','Off-road',1,'[{\"added\": {}}]',11,1),(65,'2019-05-02 17:27:54.924843','8','FlatOut 2',1,'[{\"added\": {}}]',12,1),(66,'2019-05-02 17:28:15.814540','38','FlatOut 2 (38)',1,'[{\"added\": {}}]',13,1),(67,'2019-05-02 17:28:24.606863','39','FlatOut 2 (39)',1,'[{\"added\": {}}]',13,1),(68,'2019-05-02 17:28:33.232562','40','FlatOut 2 (40)',1,'[{\"added\": {}}]',13,1),(69,'2019-05-02 17:28:44.323729','41','FlatOut 2 (41)',1,'[{\"added\": {}}]',13,1),(70,'2019-05-02 17:28:53.666558','42','FlatOut 2 (42)',1,'[{\"added\": {}}]',13,1),(71,'2019-05-02 17:37:27.321176','21','Gremlin Interactive Ltd.',1,'[{\"added\": {}}]',7,1),(72,'2019-05-02 17:39:02.606184','9','VR Soccer \'96',1,'[{\"added\": {}}]',12,1),(73,'2019-05-02 17:39:23.171247','43','VR Soccer \'96 (43)',1,'[{\"added\": {}}]',13,1),(74,'2019-05-02 17:39:31.189437','44','VR Soccer \'96 (44)',1,'[{\"added\": {}}]',13,1),(75,'2019-05-02 17:39:50.709737','45','VR Soccer \'96 (45)',1,'[{\"added\": {}}]',13,1),(76,'2019-05-02 17:39:59.797761','46','VR Soccer \'96 (46)',1,'[{\"added\": {}}]',13,1),(77,'2019-05-02 17:40:09.269726','47','VR Soccer \'96 (47)',1,'[{\"added\": {}}]',13,1),(78,'2019-05-16 16:38:26.450350','2','sakis',1,'[{\"added\": {}}]',4,1),(79,'2019-05-16 16:39:29.835250','2','sakis',2,'[{\"changed\": {\"fields\": [\"first_name\", \"last_name\", \"email\", \"is_staff\"]}}]',4,1),(80,'2019-05-16 16:39:44.108712','2','sakis',2,'[{\"changed\": {\"fields\": [\"is_staff\"]}}]',4,1),(81,'2019-05-16 16:40:06.870935','6','Crysis',2,'[{\"changed\": {\"fields\": [\"user\"]}}]',12,1),(82,'2019-05-16 16:40:15.920959','2','Diablo',2,'[{\"changed\": {\"fields\": [\"user\"]}}]',12,1),(83,'2019-05-16 16:40:24.871719','8','FlatOut 2',2,'[{\"changed\": {\"fields\": [\"user\"]}}]',12,1),(84,'2019-05-16 16:40:34.057863','1','Grim Dawn - Forgotten Gods',2,'[{\"changed\": {\"fields\": [\"user\"]}}]',12,1),(85,'2019-05-16 16:40:42.230272','3','Odbuction',2,'[{\"changed\": {\"fields\": [\"user\"]}}]',12,1),(86,'2019-05-16 16:40:51.404564','5','Riven: The Sequel to Myst',2,'[{\"changed\": {\"fields\": [\"user\"]}}]',12,1),(87,'2019-05-16 16:40:58.389743','4','The Curse of Monkey Island',2,'[{\"changed\": {\"fields\": [\"user\"]}}]',12,1),(88,'2019-05-16 16:41:05.839871','7','The Dig',2,'[{\"changed\": {\"fields\": [\"user\"]}}]',12,1),(89,'2019-05-16 16:41:14.174895','9','VR Soccer \'96',2,'[{\"changed\": {\"fields\": [\"user\"]}}]',12,1);
/*!40000 ALTER TABLE `django_admin_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `django_content_type`
--

DROP TABLE IF EXISTS `django_content_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `django_content_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_label` varchar(100) NOT NULL,
  `model` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `django_content_type_app_label_model_76bd3d3b_uniq` (`app_label`,`model`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `django_content_type`
--

LOCK TABLES `django_content_type` WRITE;
/*!40000 ALTER TABLE `django_content_type` DISABLE KEYS */;
INSERT INTO `django_content_type` VALUES (1,'admin','logentry'),(3,'auth','group'),(2,'auth','permission'),(4,'auth','user'),(5,'contenttypes','contenttype'),(7,'games','company'),(8,'games','feature'),(13,'games','gallery'),(12,'games','game'),(11,'games','genre'),(9,'games','language'),(10,'games','platform'),(6,'sessions','session');
/*!40000 ALTER TABLE `django_content_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `django_migrations`
--

DROP TABLE IF EXISTS `django_migrations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `django_migrations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `applied` datetime(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `django_migrations`
--

LOCK TABLES `django_migrations` WRITE;
/*!40000 ALTER TABLE `django_migrations` DISABLE KEYS */;
INSERT INTO `django_migrations` VALUES (1,'contenttypes','0001_initial','2019-04-16 06:10:51.694675'),(2,'auth','0001_initial','2019-04-16 06:10:53.258592'),(3,'admin','0001_initial','2019-04-16 06:10:57.321230'),(4,'admin','0002_logentry_remove_auto_add','2019-04-16 06:10:58.271066'),(5,'admin','0003_logentry_add_action_flag_choices','2019-04-16 06:10:58.298989'),(6,'contenttypes','0002_remove_content_type_name','2019-04-16 06:10:59.064535'),(7,'auth','0002_alter_permission_name_max_length','2019-04-16 06:10:59.502777'),(8,'auth','0003_alter_user_email_max_length','2019-04-16 06:10:59.962201'),(9,'auth','0004_alter_user_username_opts','2019-04-16 06:11:00.025172'),(10,'auth','0005_alter_user_last_login_null','2019-04-16 06:11:00.380907'),(11,'auth','0006_require_contenttypes_0002','2019-04-16 06:11:00.422437'),(12,'auth','0007_alter_validators_add_error_messages','2019-04-16 06:11:00.448354'),(13,'auth','0008_alter_user_username_max_length','2019-04-16 06:11:00.896224'),(14,'auth','0009_alter_user_last_name_max_length','2019-04-16 06:11:01.479485'),(15,'auth','0010_alter_group_name_max_length','2019-04-16 06:11:01.979130'),(16,'auth','0011_update_proxy_permissions','2019-04-16 06:11:02.013040'),(17,'sessions','0001_initial','2019-04-16 06:11:02.204220'),(18,'games','0001_initial','2019-04-16 06:54:04.248248'),(19,'games','0002_genre','2019-04-16 06:54:04.527140'),(20,'games','0003_game','2019-04-16 08:01:45.097430'),(21,'games','0004_gallery','2019-04-16 08:34:40.033131'),(22,'games','0005_auto_20190416_1135','2019-04-16 08:35:37.465585'),(23,'games','0006_auto_20190416_1403','2019-04-16 11:03:07.914078'),(24,'games','0007_auto_20190416_1411','2019-04-16 11:11:15.037674'),(25,'games','0008_auto_20190420_1126','2019-05-16 16:36:28.075954'),(26,'games','0009_game_user','2019-05-16 16:36:35.305480');
/*!40000 ALTER TABLE `django_migrations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `django_session`
--

DROP TABLE IF EXISTS `django_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `django_session` (
  `session_key` varchar(40) NOT NULL,
  `session_data` longtext NOT NULL,
  `expire_date` datetime(6) NOT NULL,
  PRIMARY KEY (`session_key`),
  KEY `django_session_expire_date_a5c62663` (`expire_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `django_session`
--

LOCK TABLES `django_session` WRITE;
/*!40000 ALTER TABLE `django_session` DISABLE KEYS */;
INSERT INTO `django_session` VALUES ('381rdknmlkmntaelhdo2ayj69htfns9k','MjJmMmMzODIxNGQ2MjhhZTQ3Mjc0YTFhOTlmNTA5NDA5YzAzZTkxMzp7Il9hdXRoX3VzZXJfaWQiOiIxIiwiX2F1dGhfdXNlcl9iYWNrZW5kIjoiZGphbmdvLmNvbnRyaWIuYXV0aC5iYWNrZW5kcy5Nb2RlbEJhY2tlbmQiLCJfYXV0aF91c2VyX2hhc2giOiI2YjcyZTBiNGQ2MGJkNzEwNzA2NWY3ZDFhMDEwODQ5NDEyNTA3ZWFmIn0=','2019-06-12 13:22:57.826060'),('6g1bigqo9yjodimau0afaqybaz95w04k','MjJmMmMzODIxNGQ2MjhhZTQ3Mjc0YTFhOTlmNTA5NDA5YzAzZTkxMzp7Il9hdXRoX3VzZXJfaWQiOiIxIiwiX2F1dGhfdXNlcl9iYWNrZW5kIjoiZGphbmdvLmNvbnRyaWIuYXV0aC5iYWNrZW5kcy5Nb2RlbEJhY2tlbmQiLCJfYXV0aF91c2VyX2hhc2giOiI2YjcyZTBiNGQ2MGJkNzEwNzA2NWY3ZDFhMDEwODQ5NDEyNTA3ZWFmIn0=','2019-04-30 11:01:20.153604'),('g4ppf6a5mrnf4b0cpypxqjrjq7djr995','YzAwYTI1MTAyZDNjNzEyZjRmY2UxYThmOGMwYTAwNjU0ODVlZDg4Mjp7Il9hdXRoX3VzZXJfaWQiOiIyIiwiX2F1dGhfdXNlcl9iYWNrZW5kIjoiZGphbmdvLmNvbnRyaWIuYXV0aC5iYWNrZW5kcy5Nb2RlbEJhY2tlbmQiLCJfYXV0aF91c2VyX2hhc2giOiIxMDE1NjBhOTgzMmVlZmY4MmRkZDZjODkwMDUyYTBlZWI1NzcwNWVjIn0=','2019-05-30 19:20:53.164638'),('i2nn5diecs5yyrbg1cgwapxl02y2su3n','MjJmMmMzODIxNGQ2MjhhZTQ3Mjc0YTFhOTlmNTA5NDA5YzAzZTkxMzp7Il9hdXRoX3VzZXJfaWQiOiIxIiwiX2F1dGhfdXNlcl9iYWNrZW5kIjoiZGphbmdvLmNvbnRyaWIuYXV0aC5iYWNrZW5kcy5Nb2RlbEJhY2tlbmQiLCJfYXV0aF91c2VyX2hhc2giOiI2YjcyZTBiNGQ2MGJkNzEwNzA2NWY3ZDFhMDEwODQ5NDEyNTA3ZWFmIn0=','2019-05-06 08:35:12.020543'),('kmhpnt3545i1b2ectrcz1erqtd99smpq','MjJmMmMzODIxNGQ2MjhhZTQ3Mjc0YTFhOTlmNTA5NDA5YzAzZTkxMzp7Il9hdXRoX3VzZXJfaWQiOiIxIiwiX2F1dGhfdXNlcl9iYWNrZW5kIjoiZGphbmdvLmNvbnRyaWIuYXV0aC5iYWNrZW5kcy5Nb2RlbEJhY2tlbmQiLCJfYXV0aF91c2VyX2hhc2giOiI2YjcyZTBiNGQ2MGJkNzEwNzA2NWY3ZDFhMDEwODQ5NDEyNTA3ZWFmIn0=','2019-05-05 11:11:56.713035'),('mh9uiuyq3zfkcgc9bpq4lkfss7beh0wm','MjJmMmMzODIxNGQ2MjhhZTQ3Mjc0YTFhOTlmNTA5NDA5YzAzZTkxMzp7Il9hdXRoX3VzZXJfaWQiOiIxIiwiX2F1dGhfdXNlcl9iYWNrZW5kIjoiZGphbmdvLmNvbnRyaWIuYXV0aC5iYWNrZW5kcy5Nb2RlbEJhY2tlbmQiLCJfYXV0aF91c2VyX2hhc2giOiI2YjcyZTBiNGQ2MGJkNzEwNzA2NWY3ZDFhMDEwODQ5NDEyNTA3ZWFmIn0=','2019-05-03 05:50:21.242951'),('rjp2888poy67crgbm42cfnw7slbdjk6l','MjJmMmMzODIxNGQ2MjhhZTQ3Mjc0YTFhOTlmNTA5NDA5YzAzZTkxMzp7Il9hdXRoX3VzZXJfaWQiOiIxIiwiX2F1dGhfdXNlcl9iYWNrZW5kIjoiZGphbmdvLmNvbnRyaWIuYXV0aC5iYWNrZW5kcy5Nb2RlbEJhY2tlbmQiLCJfYXV0aF91c2VyX2hhc2giOiI2YjcyZTBiNGQ2MGJkNzEwNzA2NWY3ZDFhMDEwODQ5NDEyNTA3ZWFmIn0=','2019-05-03 10:58:06.419246');
/*!40000 ALTER TABLE `django_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feature`
--

DROP TABLE IF EXISTS `feature`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feature` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `desc` varchar(100) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feature`
--

LOCK TABLES `feature` WRITE;
/*!40000 ALTER TABLE `feature` DISABLE KEYS */;
INSERT INTO `feature` VALUES (1,'Single-player','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000'),(2,'Multi-player','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000'),(3,'Co-op','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000'),(4,'Achievements','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000'),(5,'Leaderboards','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000'),(6,'Controller support','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000'),(7,'In development','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000'),(8,'Cloud saves','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000'),(9,'Overlay','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000');
/*!40000 ALTER TABLE `feature` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gallery`
--

DROP TABLE IF EXISTS `gallery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gallery` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `image` varchar(100) NOT NULL,
  `game_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `gallery_game_id_32f6fa51_fk_game_id` (`game_id`),
  CONSTRAINT `gallery_game_id_32f6fa51_fk_game_id` FOREIGN KEY (`game_id`) REFERENCES `game` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gallery`
--

LOCK TABLES `gallery` WRITE;
/*!40000 ALTER TABLE `gallery` DISABLE KEYS */;
INSERT INTO `gallery` VALUES (1,'game_images/obduction1.jpg',3),(2,'game_images/obduction2.jpg',3),(3,'game_images/obduction3.jpg',3),(4,'game_images/grim_dawn_fg_1.jpg',1),(5,'game_images/grim_dawn_fg_2.jpg',1),(6,'game_images/grim_dawn_fg_3.jpg',1),(7,'game_images/grim_dawn_fg_4.jpg',1),(8,'game_images/grim_dawn_fg_5.jpg',1),(9,'game_images/diablo1.jpg',2),(10,'game_images/diablo2.jpg',2),(11,'game_images/diablo3.jpg',2),(12,'game_images/diablo4.jpg',2),(13,'game_images/diablo5.jpg',2),(14,'game_images/monkey3_1.jpg',4),(15,'game_images/monkey3_2.jpg',4),(16,'game_images/monkey3_3.jpg',4),(17,'game_images/monkey3_4.jpg',4),(18,'game_images/monkey3_5.jpg',4),(19,'game_images/riven1.jpg',5),(20,'game_images/riven2.jpg',5),(21,'game_images/riven3.jpg',5),(22,'game_images/riven4.jpg',5),(23,'game_images/riven5.jpg',5),(24,'game_images/riven6.jpg',5),(25,'game_images/riven7.jpg',5),(26,'game_images/crysis1.jpg',6),(27,'game_images/crysis2.jpg',6),(28,'game_images/crysis3.jpg',6),(29,'game_images/crysis4.jpg',6),(30,'game_images/crysis5.jpg',6),(31,'game_images/crysis6.jpg',6),(32,'game_images/dig1.jpg',7),(33,'game_images/dig2.jpg',7),(34,'game_images/dig3.jpg',7),(35,'game_images/dig4.jpg',7),(36,'game_images/dig5.jpg',7),(37,'game_images/dig6.jpg',7),(38,'game_images/flatout1.jpg',8),(39,'game_images/flatout2.jpg',8),(40,'game_images/flatout3.jpg',8),(41,'game_images/flatout4.jpg',8),(42,'game_images/flatout5.jpg',8),(43,'game_images/vrsoccer1.jpg',9),(44,'game_images/vrsoccer2.jpg',9),(45,'game_images/vrsoccer3.jpg',9),(46,'game_images/vrsoccer4.jpg',9),(47,'game_images/vrsoccer5.jpg',9);
/*!40000 ALTER TABLE `gallery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `game`
--

DROP TABLE IF EXISTS `game`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `game` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `desc` longtext NOT NULL,
  `year` smallint(5) unsigned NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `rating` decimal(3,2) NOT NULL,
  `url` varchar(255) NOT NULL,
  `image` varchar(100) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `company_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `game_company_id_06477f78_fk_company_id` (`company_id`),
  KEY `game_user_id_50018049_fk_auth_user_id` (`user_id`),
  CONSTRAINT `game_company_id_06477f78_fk_company_id` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`),
  CONSTRAINT `game_user_id_50018049_fk_auth_user_id` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game`
--

LOCK TABLES `game` WRITE;
/*!40000 ALTER TABLE `game` DISABLE KEYS */;
INSERT INTO `game` VALUES (1,'Grim Dawn - Forgotten Gods','Journey beyond the bounds of the Erulan Empire, traversing burning sands, lush oases and volcanic wastes to reach the sun beaten ruins of a city with secrets that should never have been disturbed. The flames of a forgotten god have been rekindled, sending ripples through the Eldritch realm and sowing terror even among the Witch Gods themselves. Discover hundreds of new unique items, unlock all-new mobility powers and venture into a brand-new game mode with endless replayability.',2019,14.99,4.60,'https://www.gog.com/game/grim_dawn_forgotten_gods','game_images/grim_dawn_fg.jpg','2019-04-16 11:13:48.641372','2019-05-16 16:40:34.047259',16,2),(2,'Diablo','Darkness stirs beneath Tristram. An ancient evil sweeps across the land, plunging it into civil war and terrorizing the populace. A mad king, his missing son, and a mysterious archbishop are all pieces of the puzzle you need to hack through. You have journeyed to the source of this evil. The town of Tristram - now inhabited only by a handful of survivors, broken and twisted by the madness that has befallen them. A cathedral stands there, built over the ruins of an ancient monastery. Eerie lights and ungodly sounds are heard echoing through its abandoned halls, and that is where you shall venture.',1996,8.89,4.50,'https://www.gog.com/game/diablo','game_images/diablo.jpg','2019-04-16 11:21:27.137146','2019-05-16 16:40:15.906387',15,2),(3,'Odbuction','From Cyan, the indie studio that brought you Myst, comes a new sci-fi adventure. \r\n\r\nAs you walk beside the lake on a cloudy night, a curious, organic artifact falls from the starry sky and inexplicably, without asking permission, transports you across the universe. You’ve been abducted from your cozy existence and added into an alien landscape with pieces of Earth from unexpected times and places. \r\n\r\nThe strange worlds of Obduction reveal their secrets only as you explore, discover, coax, and consider their clues. As you bask in the otherworldly beauty and explore the enigmatic landscapes, remember that the choices you make will have substantial consequences. This is your story now. \r\n\r\nMake it home.',2016,29.99,4.30,'https://www.gog.com/game/obduction','game_images/obduction.jpg','2019-04-16 11:44:26.662940','2019-05-16 16:40:42.218932',17,1),(4,'The Curse of Monkey Island','Description\r\nI\'ve sailed the seas from Trinidad to Tortuga and I\'ve never seen anything like it! The engagement ring I gave Elaine has a terrible pirate curse on it. LeChuck is behind it, I\'m sure. I should have known that nothing good could come out of that evil zombie\'s treaures. And if that\'s not bad enough, the clairvoyant I met in the mangrove swamp told me that if I am to break the curse and save Elaine, I will have to die!',1997,6.19,4.80,'https://www.gog.com/game/the_curse_of_monkey_island','game_images/monkey3.jpg','2019-04-21 11:29:51.978465','2019-05-16 16:40:58.371358',13,1),(5,'Riven: The Sequel to Myst','Enter a deceptively beautiful world torn asunder by age-old conflicts... Where secrets lie hidden at every turn... And nothing is as it seems. A world of incomparable beauty, intrigue, and betrayal. Riven\'s story continues where Myst - and its companion novel, Myst: The Book of Atrus - left off. Your friend Atrus sends you to rescue his beloved wife Catherine, trapped on the slowly collapsing Age of Riven by Gehn. If you enjoyed Myst, you\'re bound to enjoy Riven!\r\n- One of the most beautiful and acclaimed adventure games of all time\r\n- Immerse yourself in the one of the most beautiful adventures ever created   \r\n- Challenging riddles and an immersive story',1997,5.29,4.60,'https://www.gog.com/game/riven_the_sequel_to_myst','game_images/riven.jpg','2019-04-22 08:40:26.324906','2019-05-16 16:40:51.388596',17,1),(6,'Crysis','Crysis, the first game of a well-received trilogy, is a first-person shooter set in the year 2019 and the spiritual successor to Far Cry.\r\n\r\nDownload it now! and take part in a unique virtual combat experience. \r\n\r\nWhat begins as a simple rescue mission becomes the battleground of a new war, as alien invaders swarm over a North Korean island chain. Armed with a powerful Nanosuit, you must achieve your objectives by any means necessary. Become invisible to stalk enemy patrols, or boost your strength to lay waste to vehicles. An advanced AI controls your enemies, so play smart. One wrong move out here could be your last.',2007,18.19,4.30,'https://www.gog.com/game/crysis','game_images/crysis.jpg','2019-04-22 08:48:53.597235','2019-05-16 16:40:06.854137',19,2),(7,'The Dig','An asteroid the size of a small moon is on a crash course toward Earth, and only NASA veteran Boston Low has the expertise to stop it. Along for the ride are award-winning journalist Maggie Robbins and internationally renowned geologist Ludger Brink. \r\n\r\nOnce the wayward asteroid is nuked into a safe orbit, the trio conducts a routine examination of the rocky surface. \r\n\r\nWhat they uncover is anything but routine. \r\n\r\nLow, Brink and Robbins unwittingly trigger a mechanism that transforms the asteroid into a crystal-like spacecraft. The team is hurtled across the galaxy to a planet so desolate, Brink is moved to name it Cocytus, after the 9th circle of Hell in Dante’s inferno. The bleak landscape was obviously once home to a highly evolved civilization, with remnants of sophisticated architecture, advanced technology and an intricate network of underground tunnels. \r\n\r\nBut no Cocytans. \r\n\r\nWho were the original inhabitants of this once rich empire-turned-wasteland? What are those apparitions that mysteriously appear from time to time? Why have Low, Robbins, and Brink been brought to this place? And how can Low keep his team from unraveling in the face of such uncertainty? To return to Earth, they must dig for answers, both on the planet’s surface and deep within themselves.',1995,5.29,4.50,'https://www.gog.com/game/the_dig','game_images/dig.jpg','2019-05-02 17:18:03.400993','2019-05-16 16:41:05.830187',13,2),(8,'FlatOut 2','Experience the drive of your life as you throw yourself around on and off the track causing fences to shatter, tyre walls explode, water tanks and barrels fly across the track into other cars. And if anyone, including you, gets caught up in a big smash sit back and watch as the driver gets catapulted through the windscreen in spectacular effect. With over 5000 destructible objects on each track and 40 deformable pieces on every car sparks are guaranteed to fly increasing the mayhem with every lap!    \r\n\r\nFeaturing an enhanced version of the original\'s lauded physics engine and even faster driving track designs, FlatOut 2 also boasts a plethora of improvements, enhancements and additions to make this the definitive FlatOut experience. Twice as many vehicles, a more sophisticated career mode, additional race environments, double the number of tracks; twice as many mini-games along with many multiplayer modes (via LAN only) are just some of the exhaustive features that are included in FlatOut 2.',2006,8.89,4.60,'https://www.gog.com/game/flatout_2','game_images/flatout.jpg','2019-05-02 17:27:54.843833','2019-05-16 16:40:24.849489',20,1),(9,'VR Soccer \'96','I\'m sure you remember this game. It was the first soccer game that made you smell the grass and feel on your leg after every foul. You were playing from virtually any perspective with smooth and fast camera action that got you in the game. Its interactive technology didn\'t look better, but it played better. You do remember, don\'t you? \r\n\r\nChoose from 44 international teams and select the position you want to play. Try head to head or alternating play modes for one or two players and even more multiplayer fun? \r\n\r\nFeel the soccer once again.\r\n- The first football game to utilize 3D graphics; also known as Actua Soccer\r\n- Create your own team and customize it to suit your preferences\r\n- Play with up to 15 of your friends!',1996,8.89,3.20,'https://www.gog.com/game/vr_soccer_96','game_images/vrsoccer.jpg','2019-05-02 17:39:02.492159','2019-05-16 16:41:14.158028',21,2);
/*!40000 ALTER TABLE `game` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `game_feature`
--

DROP TABLE IF EXISTS `game_feature`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `game_feature` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `game_id` int(11) NOT NULL,
  `feature_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `game_feature_game_id_feature_id_0afa0210_uniq` (`game_id`,`feature_id`),
  KEY `game_feature_feature_id_3ae38c93_fk_feature_id` (`feature_id`),
  CONSTRAINT `game_feature_feature_id_3ae38c93_fk_feature_id` FOREIGN KEY (`feature_id`) REFERENCES `feature` (`id`),
  CONSTRAINT `game_feature_game_id_a9316bf0_fk_game_id` FOREIGN KEY (`game_id`) REFERENCES `game` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game_feature`
--

LOCK TABLES `game_feature` WRITE;
/*!40000 ALTER TABLE `game_feature` DISABLE KEYS */;
INSERT INTO `game_feature` VALUES (2,1,1),(3,1,2),(8,1,3),(9,1,4),(10,1,6),(1,1,8),(7,1,9),(6,2,1),(5,2,2),(4,2,9),(12,3,1),(11,3,9),(15,4,1),(13,4,8),(14,4,9),(16,5,1),(18,6,1),(17,6,9),(20,7,1),(19,7,8),(21,8,1),(22,8,2),(24,9,1),(25,9,2),(26,9,3),(23,9,8);
/*!40000 ALTER TABLE `game_feature` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `game_genre`
--

DROP TABLE IF EXISTS `game_genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `game_genre` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `game_id` int(11) NOT NULL,
  `genre_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `game_genre_game_id_genre_id_7313ccbe_uniq` (`game_id`,`genre_id`),
  KEY `game_genre_genre_id_ae5119cc_fk_genre_id` (`genre_id`),
  CONSTRAINT `game_genre_game_id_07744918_fk_game_id` FOREIGN KEY (`game_id`) REFERENCES `game` (`id`),
  CONSTRAINT `game_genre_genre_id_ae5119cc_fk_genre_id` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game_genre`
--

LOCK TABLES `game_genre` WRITE;
/*!40000 ALTER TABLE `game_genre` DISABLE KEYS */;
INSERT INTO `game_genre` VALUES (1,1,1),(8,1,6),(7,1,10),(4,2,1),(6,2,6),(5,2,10),(9,3,9),(10,3,11),(11,3,12),(12,4,9),(13,4,10),(14,4,13),(15,5,9),(16,5,11),(17,5,12),(19,6,6),(18,6,8),(20,6,14),(21,7,9),(22,7,13),(23,7,14),(25,8,4),(26,8,15),(24,8,16),(27,9,5);
/*!40000 ALTER TABLE `game_genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `game_language`
--

DROP TABLE IF EXISTS `game_language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `game_language` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `game_id` int(11) NOT NULL,
  `language_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `game_language_game_id_language_id_375a1e36_uniq` (`game_id`,`language_id`),
  KEY `game_language_language_id_c179533b_fk_language_id` (`language_id`),
  CONSTRAINT `game_language_game_id_5e5aa26f_fk_game_id` FOREIGN KEY (`game_id`) REFERENCES `game` (`id`),
  CONSTRAINT `game_language_language_id_c179533b_fk_language_id` FOREIGN KEY (`language_id`) REFERENCES `language` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game_language`
--

LOCK TABLES `game_language` WRITE;
/*!40000 ALTER TABLE `game_language` DISABLE KEYS */;
INSERT INTO `game_language` VALUES (1,1,1),(2,1,2),(3,1,3),(5,1,4),(6,1,5),(7,1,6),(8,1,8),(9,1,10),(10,1,11),(11,1,12),(12,1,13),(13,1,21),(4,2,1),(14,3,1),(15,3,2),(16,3,3),(17,3,4),(18,3,5),(19,3,7),(20,3,8),(21,3,9),(22,3,12),(23,3,13),(24,4,1),(25,4,2),(26,4,3),(27,4,4),(28,4,5),(29,5,1),(30,5,2),(31,5,3),(32,5,4),(33,5,5),(34,5,8),(35,5,9),(36,5,10),(37,6,1),(38,6,2),(39,6,3),(40,6,4),(41,6,5),(42,6,8),(43,6,9),(44,6,10),(45,6,11),(46,6,15),(47,6,16),(48,7,1),(49,7,2),(50,7,3),(51,7,4),(52,7,5),(53,8,1),(54,8,2),(55,8,3),(56,8,4),(57,8,5),(58,9,1);
/*!40000 ALTER TABLE `game_language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `game_platform`
--

DROP TABLE IF EXISTS `game_platform`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `game_platform` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `game_id` int(11) NOT NULL,
  `platform_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `game_platform_game_id_platform_id_e322814b_uniq` (`game_id`,`platform_id`),
  KEY `game_platform_platform_id_1c6f1fa5_fk_platform_id` (`platform_id`),
  CONSTRAINT `game_platform_game_id_7c73d9fd_fk_game_id` FOREIGN KEY (`game_id`) REFERENCES `game` (`id`),
  CONSTRAINT `game_platform_platform_id_1c6f1fa5_fk_platform_id` FOREIGN KEY (`platform_id`) REFERENCES `platform` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game_platform`
--

LOCK TABLES `game_platform` WRITE;
/*!40000 ALTER TABLE `game_platform` DISABLE KEYS */;
INSERT INTO `game_platform` VALUES (1,1,1),(4,2,1),(5,3,1),(6,3,2),(7,4,1),(8,4,2),(9,5,1),(10,5,2),(11,6,1),(12,7,1),(13,7,2),(14,7,3),(15,8,1),(16,8,3),(17,9,1),(18,9,2);
/*!40000 ALTER TABLE `game_platform` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genre`
--

DROP TABLE IF EXISTS `genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `genre` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `desc` varchar(100) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genre`
--

LOCK TABLES `genre` WRITE;
/*!40000 ALTER TABLE `genre` DISABLE KEYS */;
INSERT INTO `genre` VALUES (1,'Role-playing','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000'),(2,'Simulation','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000'),(3,'Indie','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000'),(4,'Racing','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000'),(5,'Sports','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000'),(6,'Action','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000'),(7,'Strategy','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000'),(8,'Shooter','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000'),(9,'Adventure','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000'),(10,'Fantasy','2019-04-16 11:18:37.453354','2019-04-16 11:18:37.453354'),(11,'FPP','2019-04-16 11:44:06.898906','2019-04-16 11:44:06.898906'),(12,'Puzzle','2019-04-16 11:44:13.945111','2019-04-16 11:44:13.945111'),(13,'Point-and-click','2019-04-21 11:29:44.803593','2019-04-21 11:29:44.803634'),(14,'Sci-fi','2019-04-22 08:48:44.241204','2019-04-22 08:48:44.241244'),(15,'Arcade','2019-05-02 17:27:01.340607','2019-05-02 17:27:01.340665'),(16,'Off-road','2019-05-02 17:27:09.400597','2019-05-02 17:27:09.400639'),(17,'Sports','2019-05-20 08:50:17.925986','2019-05-20 08:50:17.926023');
/*!40000 ALTER TABLE `genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `language`
--

DROP TABLE IF EXISTS `language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `language` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `desc` varchar(100) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `language`
--

LOCK TABLES `language` WRITE;
/*!40000 ALTER TABLE `language` DISABLE KEYS */;
INSERT INTO `language` VALUES (1,'English','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000'),(2,'Deutsch','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000'),(3,'français','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000'),(4,'español','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000'),(5,'italiano','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000'),(6,'Português do Brasil','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000'),(7,'Português','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000'),(8,'русский','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000'),(9,'polski','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000'),(10,'日本語','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000'),(11,'český','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000'),(12,'nederlands','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000'),(13,'中文(简体)','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000'),(14,'한국어','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000'),(15,'Türkçe','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000'),(16,'magyar','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000'),(17,'svenska','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000'),(18,'Suomi','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000'),(19,'norsk','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000'),(20,'Dansk','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000'),(21,'Ελληνικά','2019-04-16 11:33:46.962329','2019-04-16 11:33:46.962329');
/*!40000 ALTER TABLE `language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `platform`
--

DROP TABLE IF EXISTS `platform`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `platform` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `desc` varchar(100) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `platform`
--

LOCK TABLES `platform` WRITE;
/*!40000 ALTER TABLE `platform` DISABLE KEYS */;
INSERT INTO `platform` VALUES (1,'Windows','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000'),(2,'OSX','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000'),(3,'Linux','2019-04-15 00:00:00.000000','2019-04-16 00:00:00.000000');
/*!40000 ALTER TABLE `platform` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-01  7:04:08
