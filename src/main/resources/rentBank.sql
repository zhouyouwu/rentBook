CREATE TABLE `account_zhoucy` (
                                  `userid` char(16) NOT NULL,
                                  `username` varchar(20) NOT NULL,
                                  `password` char(6) NOT NULL,
                                  `phone_no` char(11) NOT NULL,
                                  `id_card_no` char(18) NOT NULL,
                                  `register_time` varchar(20) NOT NULL,
                                  `account_balance` decimal(12,4) NOT NULL,
                                  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `transfer_zhoucy` (
                                   `userid` char(16) NOT NULL,
                                   `operation_time` varchar(20) NOT NULL,
                                   `amount` decimal(12,4) NOT NULL,
                                   `link_account` char(16) NOT NULL,
                                   `link_username` varchar(20) DEFAULT NULL,
                                   `op_balance` decimal(12,4) NOT NULL,
                                   `op_type` int(11) DEFAULT NULL,
                                   `op_desc` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

