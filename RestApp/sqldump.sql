CREATE TABLE `reservation_db`.`table_details` (
  `TABLE_NO` INT NOT NULL AUTO_INCREMENT,
  `TABLE_SIZE` INT NOT NULL,
  `TABLE_STATUS` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`TABLE_NO`));
  
insert into `reservation_db`.`table_details`(TABLE_SIZE,TABLE_STATUS) values(5,0);
insert into `reservation_db`.`table_details`(TABLE_SIZE,TABLE_STATUS) values(5,0);
insert into `reservation_db`.`table_details`(TABLE_SIZE,TABLE_STATUS) values(2,0);
insert into `reservation_db`.`table_details`(TABLE_SIZE,TABLE_STATUS) values(5,0);
insert into `reservation_db`.`table_details`(TABLE_SIZE,TABLE_STATUS) values(5,0);
insert into `reservation_db`.`table_details`(TABLE_SIZE,TABLE_STATUS) values(2,0);
insert into `reservation_db`.`table_details`(TABLE_SIZE,TABLE_STATUS) values(10,0);
insert into `reservation_db`.`table_details`(TABLE_SIZE,TABLE_STATUS) values(10,0);
--------------------------------------------------------------------------------------------
CREATE TABLE `reservation_db`.`customer_details` (
  `CUSTOMER_NAME` VARCHAR(45) NOT NULL,
  `CUSTOMER_EMAILID` VARCHAR(45) NOT NULL,
  `CUSTOMER_PHONE` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`CUSTOMER_EMAILID`));
  
insert into `reservation_db`.`customer_details`(CUSTOMER_NAME,CUSTOMER_EMAILID,CUSTOMER_PHONE) values('Tracy','abc@gmail.com',217-546-7899);
insert into `reservation_db`.`customer_details`(CUSTOMER_NAME,CUSTOMER_EMAILID,CUSTOMER_PHONE) values('Smith','smith@gmail.com',217-546-7898);

------------------------------------------------------------------------------------------------------
CREATE TABLE `reservation_db`.`reservation_details` (
  `CONFIRMATION_NO` INT NOT NULL AUTO_INCREMENT,
  `TABLE_NO` INT NULL DEFAULT 0,
  `CUSTOMER_EMAILID` VARCHAR(45) NOT NULL,
  `RESERVATION_DATE` DATE NOT NULL,
  `RESERVATION_TIME` TIME NOT NULL,
  `PARTY_SIZE` INT NOT NULL,
  PRIMARY KEY (`CONFIRMATION_NO`),
  UNIQUE INDEX `CONFIRMATION_NO_UNIQUE` (`CONFIRMATION_NO` ASC),
  INDEX `TABLE_NO_idx` (`TABLE_NO` ASC),
  INDEX `CUSTOMER_EMAILID_idx` (`CUSTOMER_EMAILID` ASC),
  CONSTRAINT `TABLE_NO`
    FOREIGN KEY (`TABLE_NO`)
    REFERENCES `reservation_db`.`table_details` (`TABLE_NO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `CUSTOMER_EMAILID`
    FOREIGN KEY (`CUSTOMER_EMAILID`)
    REFERENCES `reservation_db`.`customer_details` (`CUSTOMER_EMAILID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
-------------------------------------------------------------------------------------------------------
    
CREATE TABLE `reservation_db`.`user_settings` (
  `owner_emailid` VARCHAR(45) NOT NULL,
  `owner_password` VARCHAR(45) NOT NULL,
  `owner_firstname` VARCHAR(45) NOT NULL,
  `owner_lastname` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`owner_emailid`));
  
insert into reservation_db.user_settings values (1,'owner@gmail.com','owner','Larry','Hudson');
-------------------------------------------------------------------------------------------------------
 
CREATE TABLE `reservation_db`.`settings` (
  `auto_table_assign` INT NOT NULL DEFAULT 0);

insert into reservation_db.settings values(0);
