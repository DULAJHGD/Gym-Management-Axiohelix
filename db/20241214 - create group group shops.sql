CREATE TABLE `shop_group_shops`
(
    `id`       char(32) NOT NULL,
    `group_id` char(32) DEFAULT NULL,
    `shop_id`  char(32) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY        `group_id_idx` (`group_id`),
    KEY        `shop_id_idx` (`shop_id`),
    CONSTRAINT `group_id` FOREIGN KEY (`group_id`) REFERENCES `shop_group` (`id`),
    CONSTRAINT `shop_name` FOREIGN KEY (`shop_id`) REFERENCES `shop_users` (`id`)
)
