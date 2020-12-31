CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_code` varchar(32) DEFAULT NULL,
  `account_name` varchar(255) DEFAULT NULL,
  `amount` decimal(10,2) DEFAULT '0.00',
  `updated_at` int(10) unsigned DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_account_code` (`account_code`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('1', 'javadaily', 'test', '10000', '0');

-- ----------------------------
-- Table structure for `account_amount_log`
-- ----------------------------
CREATE TABLE `account_amount_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `change_type` tinyint(3) unsigned DEFAULT '0' COMMENT '金额变动类型',
  `account_id` int(10) unsigned NOT NULL DEFAULT '0',
  `amount` decimal(10,0) DEFAULT '0' COMMENT '金额',
  `created_at` int(10) unsigned DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_sn` varchar(50) DEFAULT NULL COMMENT '编码',
  `goods_name` varchar(255) DEFAULT NULL COMMENT '名称',
  `count` int(11) DEFAULT '0' COMMENT '库存数量',
  `price` decimal(10,2) DEFAULT '0.00' COMMENT '单价',
  `num` int(10) unsigned DEFAULT '0',
  `updated_at` int(10) unsigned DEFAULT '0',
  `created_at` int(10) unsigned DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_goods_code` (`goods_sn`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `undo_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `branch_id` bigint(20) NOT NULL,
  `xid` varchar(100) NOT NULL,
  `context` varchar(128) NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int(11) NOT NULL,
  `log_created` datetime NOT NULL,
  `log_modified` datetime NOT NULL,
  `ext` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;