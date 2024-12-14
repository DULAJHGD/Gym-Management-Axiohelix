ALTER TABLE `gym_management`.`shop_group`
    ADD COLUMN `company_id` CHAR(32) NULL AFTER `shop_id`,
ADD COLUMN `remark` VARCHAR(255) NULL AFTER `company_id`,
ADD COLUMN `status` TINYINT(1) NULL AFTER `remark`,
ADD COLUMN `created_by` VARCHAR(255) NULL AFTER `status`,
ADD COLUMN `created_on` VARCHAR(255) NULL AFTER `created_by`,
ADD COLUMN `last_modified_by` VARCHAR(255) NULL AFTER `created_on`,
ADD COLUMN `last_modified_on` VARCHAR(255) NULL AFTER `last_modified_by`;
