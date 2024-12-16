ALTER TABLE `gym_management`.`shop_group`
    CHANGE COLUMN `last_modified_by` `last_updated_by` VARCHAR(255) NULL DEFAULT NULL ,
    CHANGE COLUMN `last_modified_on` `last_updated_on` VARCHAR(255) NULL DEFAULT NULL ;