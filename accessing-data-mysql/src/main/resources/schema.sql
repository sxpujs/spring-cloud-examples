drop table `user` if exists;

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email` varchar(40) DEFAULT NULL,
  `name` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;