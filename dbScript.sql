-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema bootcamp
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bootcamp
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bootcamp` DEFAULT CHARACTER SET utf8 ;
USE `bootcamp` ;

-- -----------------------------------------------------
-- Table `bootcamp`.`atmosphere`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bootcamp`.`atmosphere` (
  `id_atmosphere` INT(11) NOT NULL AUTO_INCREMENT,
  `humidity` INT(11) NULL DEFAULT NULL,
  `pressure` FLOAT NULL DEFAULT NULL,
  `visibility` FLOAT NULL DEFAULT NULL,
  `rising` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id_atmosphere`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bootcamp`.`country`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bootcamp`.`country` (
  `country_name` VARCHAR(50) NULL DEFAULT NULL,
  `country_id2` VARCHAR(2) NULL DEFAULT NULL,
  `country_id3` VARCHAR(3) NOT NULL,
  PRIMARY KEY (`country_id3`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bootcamp`.`state`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bootcamp`.`state` (
  `id_state` INT(11) NOT NULL AUTO_INCREMENT,
  `country_id3` VARCHAR(3) NULL DEFAULT NULL,
  `state_name` VARCHAR(50) NULL DEFAULT NULL,
  `state_abbreviation` VARCHAR(2) NULL DEFAULT NULL,
  `area` MEDIUMTEXT NULL DEFAULT NULL,
  `capital` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`id_state`),
  INDEX `fk_country_state` (`country_id3` ASC),
  CONSTRAINT `fk_country_state`
    FOREIGN KEY (`country_id3`)
    REFERENCES `bootcamp`.`country` (`country_id3`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bootcamp`.`todayweather`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bootcamp`.`todayweather` (
  `id_today_weather` INT(11) NOT NULL AUTO_INCREMENT,
  `todayweather_date` DATETIME NULL DEFAULT NULL,
  `temperature` INT(11) NULL DEFAULT NULL,
  `description` VARCHAR(70) NULL DEFAULT NULL,
  PRIMARY KEY (`id_today_weather`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bootcamp`.`wind`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bootcamp`.`wind` (
  `id_wind` INT(11) NOT NULL AUTO_INCREMENT,
  `speed` INT(11) NULL DEFAULT NULL,
  `direction` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id_wind`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bootcamp`.`weather`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bootcamp`.`weather` (
  `id_weather` INT(11) NOT NULL AUTO_INCREMENT,
  `id_today_weather` INT(11) NULL DEFAULT NULL,
  `id_wind` INT(11) NULL DEFAULT NULL,
  `id_atmosphere` INT(11) NULL DEFAULT NULL,
  `id_state` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id_weather`),
  INDEX `fk_today_weather` (`id_today_weather` ASC),
  INDEX `fk_wind_weather` (`id_wind` ASC),
  INDEX `fk_atmosphere_weather` (`id_atmosphere` ASC),
  INDEX `fk_state_weather` (`id_state` ASC),
  CONSTRAINT `fk_atmosphere_weather`
    FOREIGN KEY (`id_atmosphere`)
    REFERENCES `bootcamp`.`atmosphere` (`id_atmosphere`),
  CONSTRAINT `fk_state_weather`
    FOREIGN KEY (`id_state`)
    REFERENCES `bootcamp`.`state` (`id_state`),
  CONSTRAINT `fk_today_weather`
    FOREIGN KEY (`id_today_weather`)
    REFERENCES `bootcamp`.`todayweather` (`id_today_weather`),
  CONSTRAINT `fk_wind_weather`
    FOREIGN KEY (`id_wind`)
    REFERENCES `bootcamp`.`wind` (`id_wind`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bootcamp`.`forecasts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bootcamp`.`forecasts` (
  `id_forecast` INT(11) NOT NULL AUTO_INCREMENT,
  `forecast_date` DATE NULL DEFAULT NULL,
  `forecast_day` VARCHAR(20) NULL DEFAULT NULL,
  `high` INT(11) NULL DEFAULT NULL,
  `low` INT(11) NULL DEFAULT NULL,
  `forecast_text` VARCHAR(70) NULL DEFAULT NULL,
  `id_weather` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id_forecast`),
  INDEX `fk_weather_forecast` (`id_weather` ASC),
  CONSTRAINT `fk_weather_forecast`
    FOREIGN KEY (`id_weather`)
    REFERENCES `bootcamp`.`weather` (`id_weather`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
