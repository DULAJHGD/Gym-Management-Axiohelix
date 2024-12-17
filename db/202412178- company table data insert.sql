INSERT INTO `gym_management`.`company`
(`id`,
 `name`,
 `address`,
 `phone_number`,
 `company_registation_no`,
 `email`,
 `remark`,
 `status`,
 `sort_order`,
 `created_by`,
 `created_on`,
 `last_update_by`
)
VALUES
    (101153800055685120 ,
     'Company1',
     'No10 abc road bcd',
     '+958-154-7852',
     'regB1457852',
     'company@gmail.com',
     'test',
     1,
     1,
     'admin',
     '2024-12-15',
     ''
    );

INSERT INTO `gym_management`.`company`
(`id`,
 `name`,
 `address`,
 `phone_number`,
 `company_registation_no`,
 `email`,
 `remark`,
 `status`,
 `sort_order`,
 `created_by`,
 `created_on`,
 `last_update_by`
)
VALUES
    (105153800055685120 ,
     'Company2',
     'No45 evc road acd',
     '+958-137-1234',
     'regB1457852',
     'company1@gmail.com',
     'test1',
     1,
     2,
     'admin',
     '2024-12-14',
     ''
    );
UPDATE `gym_management`.`company` SET `id` = '105153800055685121' WHERE (`id` = '101153800055685120');