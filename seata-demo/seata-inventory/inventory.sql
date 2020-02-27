CREATE TABLE `inventory` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`PRODUCT_ID`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`AMOUNT`  bigint(20) NULL DEFAULT NULL ,
`CREATED_AT`  datetime NULL DEFAULT NULL ,
`UPDATED_AT`  datetime NULL DEFAULT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=2
ROW_FORMAT=DYNAMIC
;
