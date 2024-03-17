CREATE DATABASE `Equation_new`;

CREATE TABLE `Equation_new`.`Expression` (
  `id` int NOT NULL DEFAULT '0',
  `value` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Expression_UNIQUE` (`value`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `Equation_new`.`Root` (
  `id` int NOT NULL,
  `value` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Root_UNIQUE` (`value`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `Equation_new`.`Expression-Root` (
  `expression` int DEFAULT NULL,
  `root` int DEFAULT NULL,
  UNIQUE KEY `Expression_Root_UNIQUE` (`expression`,`root`),
  KEY `Expression_Root_Root_FK` (`root`),
  CONSTRAINT `Expression_Root_Expression_FK` FOREIGN KEY (`expression`) REFERENCES `Expression` (`id`),
  CONSTRAINT `Expression_Root_Root_FK` FOREIGN KEY (`root`) REFERENCES `Root` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;