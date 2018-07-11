CREATE TABLE `article_info` (
  `article_id` varchar(30) NOT NULL COMMENT '文章id',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `content_md` longtext COMMENT '内容md',
  `content_html` longtext COMMENT '内容html',
  `author_id` varchar(30) DEFAULT NULL COMMENT '作者id',
  `post_time` datetime DEFAULT NULL COMMENT '发布时间',
  `categories` varchar(100) DEFAULT NULL COMMENT '分类目录',
  `tags` varchar(100) DEFAULT NULL COMMENT '标签',
  `status` varchar(2) DEFAULT NULL COMMENT '状态',
  `comment_ids` varchar(255) DEFAULT NULL COMMENT '评论id',
  `is_deleted` varchar(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(30) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `category_info` (
  `category_id` varchar(30) NOT NULL COMMENT '分类id',
  `category_name` varchar(50) DEFAULT NULL COMMENT '分类名称',
  `is_deleted` varchar(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(30) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tag_info` (
  `tag_id` varchar(30) NOT NULL COMMENT '分类id',
  `tag_name` varchar(50) DEFAULT NULL COMMENT '分类名称',
  `is_deleted` varchar(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(30) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`tag_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;