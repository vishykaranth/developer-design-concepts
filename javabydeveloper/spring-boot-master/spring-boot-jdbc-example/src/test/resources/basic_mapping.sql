/* Mysql and H2 DB tables and Sample Data */ 
DROP TABLE IF EXISTS `USER`;

CREATE TABLE `USER` (
		`ID` bigint(11) NOT NULL AUTO_INCREMENT,
		`USERNAME` varchar(45) NOT NULL,
		`PASSWORD` varchar(45) NOT NULL,
		`CREATEDTIME` datetime NOT NULL,
		`UPDATEDTIME` datetime DEFAULT NULL,
		`USERTYPE` varchar(45) NOT NULL,
		`DATEOFBIRTH` date NOT NULL,
		PRIMARY KEY (`ID`),
		UNIQUE KEY `USER_NAME_UNIQUE` (`USERNAME`)
	);
	
		
INSERT INTO `USER` VALUES 
     (1,'PeterM','ABC123abc*','2020-03-17 07:13:30',NULL,'STUDENT','1986-03-17'),
     (2,'Mike','password','2020-03-18 14:59:35',NULL,'EMPLOYEE','1989-03-18'),
     (3,'KingPeter','password','2020-03-19 12:19:15',NULL,'EMPLOYEE','2012-03-18'),
     (4,'PeterH','ABC123abc*','2020-03-17 07:13:30',NULL,'STUDENT','1960-03-17'),
     (5,'Kelvin','password','2020-03-18 14:59:35',NULL,'EMPLOYEE','1988-03-18'),
     (6,'PeterLouise','password','2020-03-19 12:19:15',NULL,'EMPLOYEE','1992-03-18'),
     (7,'JustinB','ABC123abc*','2020-03-17 07:13:30',NULL,'STUDENT','2000-03-17'),
     (8,'AshjaA','password','2020-03-18 14:59:35',NULL,'EMPLOYEE','2018-03-18'),
     (9,'JenniferH','password','2020-03-19 12:19:15',NULL,'EMPLOYEE','2001-03-18'),
     (10,'DonaldT','ABC123abc*','2020-03-17 07:13:30',NULL,'STUDENT','1972-03-17'),
     (11,'HilloryK','password','2020-03-18 14:59:35',NULL,'EMPLOYEE','1992-03-18'),
     (12,'MartinKing','password','2020-03-19 12:19:15',NULL,'EMPLOYEE','1987-03-18');
     
     
     