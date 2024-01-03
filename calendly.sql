-- MySQL dump 10.13  Distrib 8.2.0, for macos13 (arm64)
--
-- Host: localhost    Database: calendly
-- ------------------------------------------------------
-- Server version	8.2.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `user_event`
--

create database if not exists calendly;
use calendly;

DROP TABLE IF EXISTS `user_event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_event` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `date_num` int DEFAULT NULL,
  `from_hour` int DEFAULT NULL,
  `event_desc` varchar(100) DEFAULT NULL,
  `from_minutes` int DEFAULT NULL,
  `to_hour` int DEFAULT NULL,
  `to_minutes` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=303 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_event`
--

LOCK TABLES `user_event` WRITE;
/*!40000 ALTER TABLE `user_event` DISABLE KEYS */;
INSERT INTO `user_event` VALUES (1,1,1704133800,11,'Interview with XYZ',30,12,0),(2,1,1704133800,13,'Lunch Break',0,14,0),(3,1,1704133800,14,'Focus mode working, DND',0,17,30),(4,2,1704133800,11,'Interview with ABC',30,12,0),(5,2,1704133800,15,'Code review',0,16,0),(6,1,1704133800,17,'Tea Break',30,18,0),(7,1,1704133800,9,'Morning Tea Break',0,9,30),(52,2,1704133800,10,'Doctor visit',0,10,30),(102,2,1704133800,16,'Meeting with Boss',0,16,30),(252,4,1704133800,16,'Meeting with Boss',0,16,30),(302,10,1704133800,16,'Meeting with Boss',0,16,30);
/*!40000 ALTER TABLE `user_event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_event_seq`
--

DROP TABLE IF EXISTS `user_event_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_event_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_event_seq`
--

LOCK TABLES `user_event_seq` WRITE;
/*!40000 ALTER TABLE `user_event_seq` DISABLE KEYS */;
INSERT INTO `user_event_seq` VALUES (401);
/*!40000 ALTER TABLE `user_event_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_schedule`
--

DROP TABLE IF EXISTS `user_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_schedule` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `day_name` tinyint DEFAULT NULL,
  `from_hour` int DEFAULT NULL,
  `from_minutes` int DEFAULT NULL,
  `to_hour` int DEFAULT NULL,
  `to_minutes` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_index` (`user_id`,`day_name`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_schedule`
--

LOCK TABLES `user_schedule` WRITE;
/*!40000 ALTER TABLE `user_schedule` DISABLE KEYS */;
INSERT INTO `user_schedule` VALUES (1,1,1,9,0,18,0),(2,1,2,9,0,18,0),(3,1,3,10,0,18,0),(4,1,4,10,0,18,0),(5,1,5,10,0,18,0),(6,2,1,9,0,18,0),(7,2,2,9,0,18,0),(8,2,3,10,0,18,0),(9,2,4,10,0,18,0),(10,2,5,10,0,18,0),(11,4,1,9,0,18,0),(12,4,2,9,0,18,0),(13,4,3,10,0,18,0),(14,4,4,10,0,18,0),(15,4,5,10,0,18,0),(18,5,1,9,0,18,0),(19,5,2,9,0,18,0),(20,5,3,10,0,18,0),(21,5,4,10,0,18,0),(22,5,5,10,0,18,0),(102,10,1,10,30,15,30),(103,10,2,10,30,18,30),(104,10,3,10,30,18,30);
/*!40000 ALTER TABLE `user_schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_schedule_seq`
--

DROP TABLE IF EXISTS `user_schedule_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_schedule_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_schedule_seq`
--

LOCK TABLES `user_schedule_seq` WRITE;
/*!40000 ALTER TABLE `user_schedule_seq` DISABLE KEYS */;
INSERT INTO `user_schedule_seq` VALUES (201);
/*!40000 ALTER TABLE `user_schedule_seq` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-03 18:54:14
