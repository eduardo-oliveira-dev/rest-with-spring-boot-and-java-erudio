CREATE TABLE IF NOT EXISTS `person` (
                          `first_name` varchar(80) NOT NULL,
                          `last_name` varchar(80) NOT NULL,
                          `id` bigint NOT NULL AUTO_INCREMENT,
                          `address` varchar(100) NOT NULL,
                          `gender` varchar(6) NOT NULL,
                          PRIMARY KEY (`id`)
);

