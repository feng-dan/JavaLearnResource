```sql
-- 注：自己没有做过电商类项目，根据自己理解做的简单表设计，谢谢老师指导！
-- 用户信息表
create table t_user
(
    u_id         bigint unsigned auto_increment comment '用户ID',
    user_name    varchar(15) default ''                not null comment '用户名',
    phone        varchar(11) default ''                not null comment '用户电话',
    flag         tinyint     default 0                 not null comment '逻辑删除',
    f_created_at timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    f_updated_at timestamp   default CURRENT_TIMESTAMP not null comment '更新时间',
    constraint uniq_u_id
        unique (u_id)
) comment '用户信息表' charset = utf8mb4;
alter table t_user
    add primary key (u_id);

-- 商品信息表
create table t_product
(
    p_id         bigint unsigned auto_increment comment '商品编号',
    product_name varchar(30)             default ''                not null comment '商品名称',
    price        decimal(10, 3) unsigned default 0                 not null comment '商品价格',
    categories   tinyint                 default 0                 not null comment '商品分类',
    flag         tinyint                 default 0                 not null comment '逻辑删除',
    f_created_at timestamp               default CURRENT_TIMESTAMP not null comment '创建时间',
    f_updated_at timestamp               default CURRENT_TIMESTAMP not null comment '更新时间',
    constraint uniq_p_id
        unique (p_id)
) comment '商品信息表' charset = utf8mb4;

alter table t_product
    add primary key (p_id);

-- 订单信息表
create table t_order
(
    o_id         bigint unsigned auto_increment comment '订单编号',
    order_number varchar(30)  default ''                not null comment '订单号',
    user_id      bigint       default 0                 not null comment '用户编号',
    recipients   varchar(15)  default ''                not null comment '收件人',
    phone        varchar(11)  default ''                not null comment '收件人电话',
    address      varchar(100) default ''                not null comment '收件地址',
    flag         tinyint      default 0                 not null comment '逻辑删除',
    order_status tinyint      default 0                 not null comment '订单状态',
    f_created_at timestamp    default CURRENT_TIMESTAMP not null comment '创建时间',
    f_updated_at timestamp    default CURRENT_TIMESTAMP not null comment '更新时间',
    constraint uniq_p_id
        unique (o_id)
) comment '订单信息表' charset = utf8mb4;

alter table t_order
    add primary key (o_id);
```