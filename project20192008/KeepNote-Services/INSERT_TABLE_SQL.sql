CREATE database IF NOT EXISTS `notedb`;
USE `notedb`;
DROP TABLE IF EXISTS `Note`;
DROP TABLE IF EXISTS `User`;
CREATE TABLE `User` (
  `user_id` varchar(50) NOT NULL,
  `firstName` varchar(50) NOT NULL,
  `lastName` varchar(50) NOT NULL,
  `userPassword` varchar(50) NOT NULL,
  `userRole` varchar(50) NOT NULL,
  `user_added_date` DATETIME
);
CREATE TABLE `Note` (
  `noteId` int(11) NOT NULL AUTO_INCREMENT,
  `noteTitle` varchar(100) NOT NULL,
  `noteContent` varchar(300) NOT NULL,
  `noteStatus` varchar(50) NOT NULL,
  `createdAt` DATETIME,
  `createdBy` varchar(50) NOT NULL,
  PRIMARY KEY (`noteId`)
);
LOCK TABLES `User` WRITE;
INSERT INTO `User` VALUES ('admin','rameshkumar', 'C', 'password', 'admin', '2018-10-05 10:10:00');
UNLOCK TABLES;
LOCK TABLES `Note` WRITE;
INSERT INTO `Note` VALUES (1,'Read Angular6 blog', 'Shall do at 6 pm', 'not-started', '2018-10-05 10:10:00', 'admin');
INSERT INTO `Note` VALUES (2,'Read Spring blog', 'Shall do at 7 pm', 'started', '2018-10-05 10:10:00', 'admin');
INSERT INTO `Note` VALUES (3,'Wake Up', 'Shall wake on 6 am', 'started', '2018-10-05 10:10:00', 'admin');
INSERT INTO `Note` VALUES (4,'Call Ravi', 'Shall do at 12 pm', 'not-started', '2018-10-05 10:10:00', 'admin');
INSERT INTO `Note` VALUES (5,'EB Bill', 'Shall do at 10 am', 'completed', '2018-10-05 10:10:00', 'admin');
UNLOCK TABLES;
