CREATE TABLE `scm_dtx` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `lock_id` bigint(20) NOT NULL,
  `lock_name` varchar(128) NOT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modify` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_lock_id` (`lock_id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8;