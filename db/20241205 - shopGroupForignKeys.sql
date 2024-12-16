ALTER TABLE `gym_management`.`shop_group`
    ADD CONSTRAINT `shop_id`
        FOREIGN KEY (`id`)
            REFERENCES `gym_management`.`shop_users` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
ADD CONSTRAINT `company_id`
  FOREIGN KEY (`id`)
  REFERENCES `gym_management`.`company` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
