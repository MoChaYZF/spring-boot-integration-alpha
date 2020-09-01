-- 创建数据库
create database `integration` default character set utf8mb4 collate utf8mb4_general_ci;

-- 轮播图数据表
-- tinyint(4) unsigned 能够存储0~255 默认显示宽度为4
-- smallint(6) unsigned 能够存储0~‭65,535 默认显示宽度为6
-- 数据库表示是与否的值，使用 tinyint 类型，坚持 is_xxx 的命名方式
CREATE TABLE `tbl_carousel`  (
  `id` smallint unsigned NOT NULL AUTO_INCREMENT COMMENT '轮播图主键编号',
  `image` varchar(128) NOT NULL COMMENT '轮播图图片地址',
  `sort` tinyint unsigned NOT NULL COMMENT '轮播图排序',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY `pk_id` (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '轮播图数据表' ROW_FORMAT = Dynamic;

-- 轮播图数据表数据
INSERT INTO `tbl_carousel` VALUES ('1', 'https://wx2.sinaimg.cn/mw690/538d3eabgy1gccic17lijj20qo0zs1fj.jpg', '1', '2020-03-03 09:29:14', '2020-03-03 09:29:14');
INSERT INTO `tbl_carousel` VALUES ('2', 'https://wx1.sinaimg.cn/thumb180/ac56c7cbly1g3jgye6921j20zk0k0dll.jpg', '2', '2020-03-03 09:30:07', '2020-03-03 09:30:07');
