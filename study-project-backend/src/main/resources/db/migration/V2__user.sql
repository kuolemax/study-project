create table "account"
(
    id       bigserial primary key,
    name     varchar(10) not null unique ,
    password varchar(64) not null,
    email    varchar(64) not null unique
);
comment on table "account" is '用户表';
comment on column "account".name is '用户名';
comment on column "account".password is '密码';
comment on column "account".email is '邮箱';
