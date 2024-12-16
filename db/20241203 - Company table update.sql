ALTER TABLE `gym_management`.`company`
    ADD COLUMN `address` VARCHAR(255) NULL AFTER `name`,
ADD COLUMN `company_registation_no` VARCHAR(255) NULL AFTER `address`;

ALTER TABLE `gym_management`.`company`
    ADD COLUMN `phone_number` VARCHAR(255) NULL AFTER `address`;

ALTER TABLE `gym_management`.`company`
    ADD COLUMN `email` VARCHAR(255) NULL AFTER `company_registation_no`;

ALTER TABLE `gym_management`.`company`
    CHANGE COLUMN `state` `status` TINYINT(1) NULL DEFAULT NULL ;
