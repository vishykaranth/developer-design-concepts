
DROP TABLE IF EXISTS `USER`;

CREATE TABLE `USER` (
		`ID` int(11) NOT NULL AUTO_INCREMENT,
		`USER_NAME` varchar(45) NOT NULL,
		`PASSWORD` varchar(45) NOT NULL,
		`EMAIL` varchar(100) DEFAULT NULL,
		`CREATED_TIME` datetime NOT NULL,
		`UPDATED_TIME` datetime DEFAULT NULL,
		`USER_TYPE` varchar(45) NOT NULL,
		`DOB` date NOT NULL,
		PRIMARY KEY (`ID`),
		UNIQUE KEY `USER_NAME_UNIQUE` (`USER_NAME`)
	);
	
		
INSERT INTO `USER` VALUES 
     (1,'PeterM','ABC123abc*','peter@email.com','2020-03-17 07:13:30',NULL,'STUDENT','2020-03-17'),
     (2,'Mike','password','mike@email.com','2020-03-18 14:59:35',NULL,'EMPLOYEE','2020-03-18'),
     (3,'KingPeter','password','kingpeter@email.com','2020-03-19 12:19:15',NULL,'EMPLOYEE','2020-03-18'),
     (4,'PeterH','ABC123abc*','peterh@email.com','2020-03-17 07:13:30',NULL,'STUDENT','2020-03-17'),
     (5,'Kelvin','password','kelvin@email.com','2020-03-18 14:59:35',NULL,'EMPLOYEE','2020-03-18'),
     (6,'PeterLouise','password','peterl@email.com','2020-03-19 12:19:15',NULL,'EMPLOYEE','2020-03-18'),
     (7,'JustinB','ABC123abc*','justin@email.com','2020-03-17 07:13:30',NULL,'STUDENT','2020-03-17'),
     (8,'AshjaA','password','ashja@email.com','2020-03-18 14:59:35',NULL,'EMPLOYEE','2020-03-18'),
     (9,'JenniferH','password','jennifer@email.com','2020-03-19 12:19:15',NULL,'EMPLOYEE','2020-03-18'),
     (10,'DonaldT','ABC123abc*','donald@email.com','2020-03-17 07:13:30',NULL,'STUDENT','2020-03-17'),
     (11,'HilloryK','password','hillory@email.com','2020-03-18 14:59:35',NULL,'EMPLOYEE','2020-03-18'),
     (12,'MartinKing','password','martin@email.com','2020-03-19 12:19:15',NULL,'EMPLOYEE','2020-03-18');
     
     