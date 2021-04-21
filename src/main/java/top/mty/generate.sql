create table base_data.person
(
    id int auto_increment
        primary key,
    name varchar(32) null comment '姓名',
    age int null comment '年龄',
    addr varchar(50) null comment '住址',
    mobile varchar(11) null comment '电话',
    create_date timestamp null comment 'create',
    update_date timestamp null comment 'update'
)
    comment 'person';

create table base_data.search_param_config
(
    id int auto_increment comment 'id'
        primary key,
    index_name varchar(50) null comment '索引名称',
    url varchar(100) null comment '接口',
    field_name varchar(128) null comment 'field_name',
    query_type varchar(32) null comment 'query_type',
    is_required tinyint(1) default 0 null comment '是否为必须的参数',
    create_date timestamp default CURRENT_TIMESTAMP not null,
    update_date timestamp default CURRENT_TIMESTAMP not null
)
    comment '查询参数配置';

