DROP TABLE IF EXISTS `rooms`;

CREATE TABLE `rooms` (
  `room_id` int NOT NULL,
  `room_title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `personnel` int NOT NULL,
  `private` tinyint NOT NULL,
  `state` tinyint NOT NULL,
  `map` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`room_id`),
  UNIQUE KEY `nickname_UNIQUE` (`nickname`),
  CONSTRAINT `rooms_chk_1` CHECK (((`personnel` >= 1) and (`personnel` <= 6)))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
