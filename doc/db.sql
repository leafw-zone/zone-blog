create table zone_blog.article_info
(
	article_id varchar(30) not null comment '文章id'
		primary key,
	title varchar(100) null comment '标题',
	content_md longtext null comment '内容md',
	content_html longtext null comment '内容html',
	summary varchar(255) null,
	author_id varchar(30) null comment '作者id',
	post_time datetime null comment '发布时间',
	categories varchar(100) null comment '分类目录',
	tags varchar(100) null comment '标签',
	status varchar(2) null comment '状态',
	comment_ids varchar(255) null comment '评论id',
	is_open varchar(1) not null comment '是否公开',
	is_deleted varchar(1) null,
	create_time datetime null,
	create_by varchar(30) null,
	update_time datetime null,
	update_by varchar(30) null
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

create table zone_blog.category_info
(
	category_id varchar(30) not null comment '分类id'
		primary key,
	category_name varchar(50) null comment '分类名称',
	author_id varchar(30) not null,
	is_deleted varchar(1) null,
	create_time datetime null,
	create_by varchar(30) null,
	update_time datetime null,
	update_by varchar(30) null
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

create table zone_blog.tag_info
(
	tag_id varchar(30) not null comment '分类id'
		primary key,
	tag_name varchar(50) null comment '分类名称',
	author_id varchar(30) not null,
	is_deleted varchar(1) null,
	create_time datetime null,
	create_by varchar(30) null,
	update_time datetime null,
	update_by varchar(30) null
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;