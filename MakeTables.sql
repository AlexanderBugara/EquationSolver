CREATE DATABASE IF NOT EXISTS `Equation_new`;
USE `Equation_new`;

DROP TABLE IF EXISTS `Equation_new`.`Expression-Root`;
DROP TABLE IF EXISTS `Equation_new`.`Expression`;
DROP TABLE IF EXISTS `Equation_new`.`Root`;

CREATE TABLE `Equation_new`.`Expression` (
  `id` INT AUTO_INCREMENT primary key NOT NULL,
  `value` varchar(100) DEFAULT NULL,
  UNIQUE KEY `Expression_UNIQUE` (`value`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `Equation_new`.`Root` (
  `id` INT AUTO_INCREMENT primary key NOT NULL,
  `value` double DEFAULT NULL,
  UNIQUE KEY `Root_UNIQUE` (`value`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `Equation_new`.`Expression-Root` (
  `expression` int DEFAULT '0',
  `root` int DEFAULT '0',
  UNIQUE KEY `Expression_Root_UNIQUE` (`expression`,`root`),
  KEY `Expression_Root_Root_FK` (`root`),
  CONSTRAINT `Expression_Root_Expression_FK` FOREIGN KEY (`expression`) REFERENCES `Expression` (`id`),
  CONSTRAINT `Expression_Root_Root_FK` FOREIGN KEY (`root`) REFERENCES `Root` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;