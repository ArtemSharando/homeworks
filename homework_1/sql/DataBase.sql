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


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
