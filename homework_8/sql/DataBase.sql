-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`complexity`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`complexity` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;

INSERT INTO `complexity` VALUES (1,'Easy'),(2,'Medium'),(3,'Hard');


-- -----------------------------------------------------
-- Table `mydb`.`subject`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`subject` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8;

INSERT INTO `subject` VALUES (1,'Programming'),(2,'Math'),(3,'English');

-- -----------------------------------------------------
-- Table `mydb`.`test`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`test` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `time` INT NOT NULL,
  `number_of_questions` INT NOT NULL,
  `id_subject` INT NOT NULL,
  `id_complexity` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `id_subject_idx` (`id_subject` ASC),
  INDEX `id_compl_idx` (`id_complexity` ASC),
  CONSTRAINT `id_compl`
    FOREIGN KEY (`id_complexity`)
    REFERENCES `mydb`.`complexity` (`id`),
  CONSTRAINT `id_sub`
    FOREIGN KEY (`id_subject`)
    REFERENCES `mydb`.`subject` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 141
DEFAULT CHARACTER SET = utf8;

INSERT INTO `test` VALUES (141,'Testing',10,10,1,2),(142,'Testing2',5,3,3,3),(143,'Testing3',10,4,2,3),(165,'Testing4',5,2,1,1),(166,'Testing5',5,3,1,1);

-- -----------------------------------------------------
-- Table `mydb`.`question`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`question` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(200) NOT NULL,
  `counter_question` INT NOT NULL,
  `number_of_answer` INT NOT NULL,
  `test_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `testId_idx` (`test_id` ASC),
  CONSTRAINT `test_q_id`
    FOREIGN KEY (`test_id`)
    REFERENCES `mydb`.`test` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 141
DEFAULT CHARACTER SET = utf8;

INSERT INTO `question` VALUES (141,'Вопрос первый',1,2,141),(142,'Вопрос второй',2,3,141),(143,'Вопрос третий',3,2,141),(144,'Вопрос четвертый',4,4,141),(145,'Вопрос пятый',5,2,141),(146,'Вопрос шестой',6,2,141),(147,'Вопрос седьмой',7,2,141),(148,'Вопрос восьмой',8,2,141),(149,'Вопрос девятый',9,2,141),(150,'Вопрос десятый',10,4,141),(151,'Вопрос первый',1,3,142),(152,'Вопрос второй',2,2,142),(153,'Вопрос третий',3,3,142),(154,'Вопрос первый',1,4,143),(155,'Вопрос второй',2,3,143),(156,'Вопрос третий',3,2,143),(157,'Вопрос четвертый',4,2,143),(169,'Вопрос первый',1,3,166),(170,'Вопрос второй',2,3,166),(171,'Вопрос третий',3,3,166);


-- -----------------------------------------------------
-- Table `mydb`.`answer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`answer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `variant` VARCHAR(450) NOT NULL,
  `counter_answer` INT NOT NULL,
  `correct_answer` TINYINT(1) NOT NULL,
  `question_id` INT NOT NULL,
  `test_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `question_id_idx` (`question_id` ASC),
  INDEX `test_id_idx` (`test_id` ASC),
  CONSTRAINT `question_id`
    FOREIGN KEY (`question_id`)
    REFERENCES `mydb`.`question` (`id`),
  CONSTRAINT `test_id_a`
    FOREIGN KEY (`test_id`)
    REFERENCES `mydb`.`test` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 181
DEFAULT CHARACTER SET = utf8;

INSERT INTO `answer` VALUES (181,'Ответ на первый вопрос один',1,0,141,141),(182,'Ответ на первый вопрос два',2,0,141,141),(183,'Ответ на второй вопрос один',1,1,142,141),(184,'Ответ на второй вопрос два',2,0,142,141),(185,'Ответ на второй вопрос три',3,0,142,141),(186,'Ответ на третий вопрос один',1,0,143,141),(187,'Ответ на третий вопрос два',2,0,143,141),(188,'Ответ на четвёртый вопрос один',1,0,144,141),(189,'Ответ на четвёртый вопрос два',2,1,144,141),(190,'Ответ на четвёртый вопрос три',3,0,144,141),(191,'Ответ на четвёртый вопрос четыре',4,1,144,141),(192,'Ответ на пятый вопрос один',1,1,145,141),(193,'Ответ на пятый вопрос два',2,0,145,141),(194,'Ответ на шестой вопрос один',1,0,146,141),(195,'Ответ на шестой вопрос два',2,1,146,141),(196,'Ответ на седьмой вопрос один',1,0,147,141),(197,'Ответ на седьмой вопрос два',2,1,147,141),(198,'Ответ на восьмой вопрос один',1,1,148,141),(199,'Ответ на восьмой вопрос два',2,0,148,141),(200,'Ответ на девятый вопрос один',1,0,149,141),(201,'Ответ на девятый вопрос два',2,1,149,141),(202,'Ответ на десятый вопрос один',1,1,150,141),(203,'Ответ на десятый вопрос два',2,0,150,141),(204,'Ответ на десятый вопрос три',3,0,150,141),(205,'Ответ на десятый вопрос четыре',4,1,150,141),(206,'Ответ на первый вопрос один',1,0,151,142),(207,'Ответ на первый вопрос два',2,1,151,142),(208,'Ответ на первый вопрос три',3,0,151,142),(209,'Ответ на второй вопрос один',1,1,152,142),(210,'Ответ на второй вопрос два',2,0,152,142),(211,'Ответ на третий вопрос один',1,0,153,142),(212,'Ответ на третий вопрос два',2,1,153,142),(213,'Ответ на третий вопрос три',3,0,153,142),(214,'Ответ на первый вопрос один',1,1,154,143),(215,'Ответ на первый вопрос два',2,1,154,143),(216,'Ответ на первый вопрос три',3,0,154,143),(217,'Ответ на первый вопрос четыре',4,0,154,143),(218,'Ответ на второй вопрос один',1,1,155,143),(219,'Ответ на второй вопрос два',2,0,155,143),(220,'Ответ на второй вопрос три',3,0,155,143),(221,'Ответ на третий вопрос один',1,1,156,143),(222,'Ответ на третий вопрос два',2,0,156,143),(223,'Ответ на четвёртый вопрос один',1,1,157,143),(224,'Ответ на четвёртый вопрос два',2,0,157,143),(238,'Ответ на первый вопрос один',1,1,169,166),(239,'Ответ на первый вопрос два',2,0,169,166),(240,'Ответ на первый вопрос три',3,0,169,166),(241,'Ответ на второй вопрос один',1,0,170,166),(242,'Ответ на второй вопрос два',2,1,170,166),(243,'Ответ на второй вопрос три',3,0,170,166),(244,'Ответ на третий вопрос один',1,1,171,166),(245,'Ответ на третий вопрос два',2,0,171,166),(246,'Ответ на третий вопрос три',3,1,171,166);


-- -----------------------------------------------------
-- Table `mydb`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`roles` (
  `id_roles` INT NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id_roles`),
  UNIQUE INDEX `name_UNIQUE` (`role` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;

INSERT INTO `roles` VALUES (1,'admin'),(2,'user');

-- -----------------------------------------------------
-- Table `mydb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(16) NOT NULL,
  `password` VARCHAR(32) NOT NULL,
  `name` VARCHAR(16) NOT NULL,
  `surname` VARCHAR(20) NOT NULL,
  `status` TINYINT(1) NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `id_roles_idx` (`role_id` ASC),
  CONSTRAINT `id_roles`
    FOREIGN KEY (`role_id`)
    REFERENCES `mydb`.`roles` (`id_roles`))
ENGINE = InnoDB
AUTO_INCREMENT = 52
DEFAULT CHARACTER SET = utf8;

INSERT INTO `user` VALUES (1,'admin','admin','Артём','Шарандо',1,1),(53,'user1','12345','Иван','Иванов',1,2),(54,'niwwspi','12345','Екатерина','Костына',1,2),(55,'user2','12345','Андрей','Шарандо',1,2),(56,'user3','12345','Даша','Шарандо',1,2),(59,'user10','12345','Иван','Иванов',1,2),(72,'user12','12345','User12','User12',1,2),(74,'user15','12345','User15','User15',1,2),(75,'user16','12345','User16','User16',1,2);


-- -----------------------------------------------------
-- Table `mydb`.`grade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`grade` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `result` VARCHAR(20) NOT NULL,
  `test_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `user_id_idx` (`user_id` ASC),
  INDEX `test_id_idx` (`test_id` ASC),
  CONSTRAINT `test_id`
    FOREIGN KEY (`test_id`)
    REFERENCES `mydb`.`test` (`id`),
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`user` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8;

INSERT INTO `grade` VALUES (12,'50',141,56),(13,'100',143,55),(26,'33,33',142,1),(27,'125',143,59);

-- -----------------------------------------------------
-- Table `mydb`.`subject_ru`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`subject_ru` (
  `id` INT UNSIGNED NOT NULL,
  `name_ru` VARCHAR(30) NOT NULL,
  `id_subject` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `id_subject_ru_idx` (`id_subject` ASC),
  CONSTRAINT `id_subject_ru`
    FOREIGN KEY (`id_subject`)
    REFERENCES `mydb`.`subject` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

INSERT INTO `subject_ru` VALUES (1,'Программирование',1),(2,'Математика',2),(3,'Английский',3);



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
