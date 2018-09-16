-- MySQL dump 10.13  Distrib 5.7.19, for osx10.12 (x86_64)
--
-- Host: localhost    Database: calendar
-- ------------------------------------------------------
-- Server version	5.7.19

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
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `end` datetime DEFAULT NULL,
  `start` datetime DEFAULT NULL,
  `title` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `completed` tinyint(1) DEFAULT NULL,
  `class` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `repetition` int(11) DEFAULT NULL,
  `user_id` int(11) NOT NULL DEFAULT '1',
  `original_start` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `event_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` VALUES (1,NULL,'2018-04-18 20:11:51','english lesson',NULL,NULL,NULL,1,NULL),(2,NULL,'2018-04-18 20:12:59','java lesson',NULL,NULL,NULL,1,NULL),(3,NULL,NULL,'test 1522713600000',NULL,NULL,NULL,1,NULL),(4,NULL,NULL,'test 1523318400000',NULL,NULL,NULL,1,NULL),(5,NULL,NULL,'test 1524441600000',NULL,NULL,NULL,1,NULL),(6,NULL,NULL,'test 1523318400000',NULL,NULL,NULL,1,NULL),(7,'2018-04-11 02:00:00','2018-04-10 02:00:00','test 1523318400000',NULL,NULL,NULL,1,NULL),(8,'2018-04-12 02:00:00','2018-04-11 02:00:00','test 1523404800000',NULL,NULL,NULL,1,NULL),(9,'2018-04-04 02:00:00','2018-04-03 02:00:00','test 1522713600000',NULL,NULL,NULL,1,NULL),(10,'2018-04-21 02:00:00','2018-04-20 02:00:00','test 1524182400000',1,'fc-event-completed',NULL,1,NULL),(11,'2018-04-10 02:00:00','2018-04-09 02:00:00','test 1523232000000',NULL,NULL,NULL,1,NULL),(12,'2018-04-13 02:00:00','2018-04-12 02:00:00','test 1523491200000',1,'fc-event-completed',NULL,1,NULL),(13,'2018-04-14 02:00:00','2018-04-13 02:00:00','test 1523577600000',1,'fc-event-completed',NULL,1,NULL),(14,'2018-04-18 02:00:00','2018-04-17 02:00:00','test 1523923200000',NULL,NULL,NULL,1,NULL),(15,'2018-04-18 02:00:00','2018-04-17 02:00:00','test 1523923200000',NULL,NULL,NULL,1,NULL),(16,'2018-04-18 02:00:00','2018-04-17 02:00:00','test 1523923200000',NULL,NULL,NULL,1,NULL),(17,'2018-04-18 02:00:00','2018-04-17 02:00:00','test 1523923200000',NULL,NULL,NULL,1,NULL),(21,'2018-04-16 08:30:00','2018-04-16 03:30:00','test 1523842200000',NULL,NULL,NULL,1,NULL),(22,'2018-03-30 02:00:00','2018-03-29 02:00:00','test 1522281600000',NULL,NULL,NULL,1,NULL),(23,'2018-03-31 02:00:00','2018-03-30 02:00:00','test 1522368000000',NULL,NULL,NULL,1,NULL),(24,'2018-03-30 02:00:00','2018-03-26 02:00:00','test 1522022400000',NULL,NULL,NULL,1,NULL),(25,'2018-04-06 02:00:00','2018-04-02 02:00:00','test 1522627200000',NULL,NULL,NULL,1,NULL),(26,'2018-04-27 02:00:00','2018-04-26 02:00:00','test 1524700800000',NULL,NULL,NULL,1,NULL),(27,'2018-04-28 02:00:00','2018-04-27 02:00:00','test 1524787200000',1,'fc-event-completed',NULL,1,NULL),(28,'2018-04-06 02:00:00','2018-04-05 02:00:00','test 1522886400000',NULL,NULL,NULL,1,NULL),(29,'2018-04-05 02:00:00','2018-04-04 02:00:00','test 1522800000000',NULL,NULL,NULL,1,NULL),(30,'2018-03-29 02:00:00','2018-03-28 02:00:00','test 1522195200000',NULL,NULL,NULL,1,NULL),(31,'2018-03-26 03:00:00','2018-03-26 02:00:00','Полить цветы',NULL,NULL,NULL,1,NULL),(32,'2018-03-27 03:00:00','2018-03-27 02:00:00','Постельное белье',NULL,NULL,NULL,1,NULL),(33,'2018-04-24 05:30:00','2018-04-24 04:30:00','Полить цветы',NULL,NULL,NULL,1,NULL),(34,'2018-04-25 12:00:00','2018-04-25 11:00:00','Постельное белье',NULL,NULL,NULL,1,NULL),(35,'2018-03-28 03:00:00','2018-03-28 02:00:00','Полить цветы',NULL,NULL,NULL,1,NULL),(36,'2018-03-27 02:00:00','2018-03-26 02:00:00','Кошка1522022400000',NULL,NULL,NULL,1,NULL),(37,'2018-03-30 02:00:00','2018-03-29 02:00:00','огород',NULL,NULL,NULL,1,NULL),(38,'2018-04-08 02:00:00','2018-04-07 02:00:00',NULL,NULL,NULL,NULL,1,NULL),(39,'2018-04-07 02:00:00','2018-04-06 02:00:00','Привет :)',NULL,NULL,NULL,1,NULL),(40,'2018-05-10 14:30:00','2018-05-10 10:00:00','Прррроверка!',1,'fc-event-completed',NULL,1,NULL),(41,'2018-04-21 03:00:00','2018-04-21 02:00:00','Полить цветы',NULL,NULL,NULL,1,NULL),(42,'2018-03-30 03:00:00','2018-03-30 02:00:00','Полить цветы',NULL,NULL,NULL,1,NULL),(43,'2018-09-09 06:30:00','2018-09-09 03:00:00','Постельное белье',0,'fc-event-overdue',NULL,1,NULL),(44,'2018-09-09 17:00:00','2018-09-09 16:00:00','Горшок кота',0,'fc-event-overdue',NULL,1,NULL),(45,'2018-09-09 10:30:00','2018-09-09 09:30:00','test2',0,'fc-event-overdue',NULL,1,NULL),(46,'2018-09-09 02:30:00','2018-09-09 02:00:00','Полить цветы',0,'fc-event-overdue',NULL,1,NULL),(47,'2018-09-09 11:30:00','2018-09-09 11:00:00','test7',0,'fc-event-overdue',NULL,1,NULL),(48,'2018-09-09 03:00:00','2018-09-09 02:00:00','Полить цветы',0,'fc-event-overdue',NULL,1,NULL),(49,'2018-09-09 09:30:00','2018-09-09 07:00:00','Test whole day',0,'fc-event-overdue',NULL,1,NULL),(50,'2018-09-09 09:00:00','2018-09-09 06:30:00','Test whole day 2',0,'fc-event-overdue',NULL,1,NULL),(51,'2018-09-09 13:30:00','2018-09-09 13:00:00','Test 5',0,'fc-event-overdue',NULL,1,NULL),(52,'2018-09-09 02:00:00','2018-09-09 01:30:00','Test8',0,'fc-event-overdue',NULL,1,NULL),(53,'2018-09-09 02:00:00','2018-09-09 02:00:00','User2Test',0,'fc-event-overdue',NULL,2,NULL),(54,'2018-09-16 02:00:00','2018-09-15 02:00:00','User2 1 day delay',0,'',NULL,2,NULL),(55,'2018-09-09 00:30:00','2018-09-09 00:00:00','User2Test3',0,'fc-event-overdue',NULL,2,NULL);
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `task_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` VALUES (1,'Горшок кота',1),(2,'Полотенца',1),(3,'Постельное белье',1),(4,'Полить цветы',1);
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  UNIQUE KEY `password_UNIQUE` (`password`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'test','$2a$10$jEE7ir5SZKj5HZszt0Rvu.U.wuSpjvFOh.WbS3S9KeLGH.0NIyr6u'),(2,'test2','$2a$10$Jn88fJLz72/wyy6E/V6v2et6Tp7ODd3NCxEXpcDoZnbCej3gL9XV6');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-09-16 13:29:04
