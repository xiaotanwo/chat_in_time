用Navicat软件建表

建表语句：

user表

CREATE TABLE `user` (

  `id` int(11) NOT NULL AUTO_INCREMENT,
  
  `name` varchar(80) NOT NULL,
  
  `password` varchar(80) NOT NULL,
  
  `image` varchar(80) DEFAULT NULL,
  
  PRIMARY KEY (`id`),
  
  UNIQUE KEY `name` (`name`) USING BTREE
  
) ENGINE=InnoDB DEFAULT CHARSET=utf8

chat表

CREATE TABLE `chat` (

  `id` int(11) NOT NULL AUTO_INCREMENT,

  `userId` int(11) NOT NULL,

  `content` varchar(1000) DEFAULT NULL,

  PRIMARY KEY (`id`),

  KEY `userId` (`userId`),

  CONSTRAINT `userId` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8