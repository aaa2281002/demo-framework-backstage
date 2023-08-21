/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2023/6/28 9:43:06                            */
/*==============================================================*/


drop table if exists SYSTEM_BACKSTAGE_BLACK_LIST_IP;

drop table if exists SYSTEM_BACKSTAGE_WHITE_LIST_IP;

drop table if exists SYSTEM_DICT;

drop table if exists SYSTEM_FRONT_BLACK_LIST_IP;

drop table if exists SYSTEM_FRONT_WHITE_LIST_IP;

drop table if exists SYSTEM_LOG;

drop table if exists SYSTEM_MENU;

drop table if exists SYSTEM_ROLE;

drop table if exists SYSTEM_TYPE;

drop table if exists SYSTEM_USER;

drop table if exists TB_SYSTEM_ROLE_MENU;

drop table if exists TB_SYSTEM_USER_ROLE;

drop table if exists T_FILE_INFO;

/*==============================================================*/
/* Table: SYSTEM_BACKSTAGE_BLACK_LIST_IP                        */
/*==============================================================*/
create table SYSTEM_BACKSTAGE_BLACK_LIST_IP
(
    ID                   BIGINT not null auto_increment comment '编号',
    CREATE_ID            BIGINT default 0 comment '创建人编号',
    CREATE_TIME          DATETIME comment '创建日期时间',
    OPERATER_ID          BIGINT default 0 comment '操作人编号',
    OPERATER_TIME        DATETIME comment '操作日期时间',
    OPERATER_STATUS      INT default 0 comment '操作状态',
    IS_ENABLE            INT default 0 comment '是否启用',
    IP                   VARCHAR(128) default '' comment 'IP',
    DESCRIPTION          VARCHAR(2000) default '' comment '描述',
    primary key (ID)
);

alter table SYSTEM_BACKSTAGE_BLACK_LIST_IP comment '系统后台黑名单IP';

/*==============================================================*/
/* Table: SYSTEM_BACKSTAGE_WHITE_LIST_IP                        */
/*==============================================================*/
create table SYSTEM_BACKSTAGE_WHITE_LIST_IP
(
    ID                   BIGINT not null auto_increment comment '编号',
    CREATE_ID            BIGINT default 0 comment '创建人编号',
    CREATE_TIME          DATETIME comment '创建日期时间',
    OPERATER_ID          BIGINT default 0 comment '操作人编号',
    OPERATER_TIME        DATETIME comment '操作日期时间',
    OPERATER_STATUS      INT default 0 comment '操作状态',
    IS_ENABLE            INT default 0 comment '是否启用',
    IP                   VARCHAR(128) default '' comment 'IP',
    DESCRIPTION          VARCHAR(2000) default '' comment '描述',
    primary key (ID)
);

alter table SYSTEM_BACKSTAGE_WHITE_LIST_IP comment '系统后台白名单IP';

/*==============================================================*/
/* Table: SYSTEM_DICT                                           */
/*==============================================================*/
create table SYSTEM_DICT
(
    ID                   BIGINT not null auto_increment comment '编号',
    CREATE_ID            BIGINT default 0 comment '创建人编号',
    CREATE_TIME          DATETIME comment '创建日期时间',
    OPERATER_ID          BIGINT default 0 comment '操作人编号',
    OPERATER_TIME        DATETIME comment '操作日期时间',
    OPERATER_STATUS      INT default 0 comment '操作状态',
    IS_ENABLE            INT default 0 comment '是否启用',
    DICT_KEY             VARCHAR(128) default '' comment '字典键',
    DESCRIPTION          VARCHAR(2000) default '' comment '描述',
    primary key (ID),
    unique key AK_UQ_DICT_KEY (DICT_KEY)
);

alter table SYSTEM_DICT comment '系统字典表';

/*==============================================================*/
/* Table: SYSTEM_FRONT_BLACK_LIST_IP                            */
/*==============================================================*/
create table SYSTEM_FRONT_BLACK_LIST_IP
(
    ID                   BIGINT not null auto_increment comment '编号',
    CREATE_ID            BIGINT default 0 comment '创建人编号',
    CREATE_TIME          DATETIME comment '创建日期时间',
    OPERATER_ID          BIGINT default 0 comment '操作人编号',
    OPERATER_TIME        DATETIME comment '操作日期时间',
    OPERATER_STATUS      INT default 0 comment '操作状态',
    IS_ENABLE            INT default 0 comment '是否启用',
    IP                   VARCHAR(128) default '' comment 'IP',
    DESCRIPTION          VARCHAR(2000) default '' comment '描述',
    primary key (ID)
);

alter table SYSTEM_FRONT_BLACK_LIST_IP comment '系统前端黑名单IP';

/*==============================================================*/
/* Table: SYSTEM_FRONT_WHITE_LIST_IP                            */
/*==============================================================*/
create table SYSTEM_FRONT_WHITE_LIST_IP
(
    ID                   BIGINT not null auto_increment comment '编号',
    CREATE_ID            BIGINT default 0 comment '创建人编号',
    CREATE_TIME          DATETIME comment '创建日期时间',
    OPERATER_ID          BIGINT default 0 comment '操作人编号',
    OPERATER_TIME        DATETIME comment '操作日期时间',
    OPERATER_STATUS      INT default 0 comment '操作状态',
    IS_ENABLE            INT default 0 comment '是否启用',
    IP                   VARCHAR(128) default '' comment 'IP',
    DESCRIPTION          VARCHAR(2000) default '' comment '描述',
    primary key (ID)
);

alter table SYSTEM_FRONT_WHITE_LIST_IP comment '系统前端白名单IP';

/*==============================================================*/
/* Table: SYSTEM_LOG                                            */
/*==============================================================*/
create table SYSTEM_LOG
(
    ID                   BIGINT not null auto_increment comment '编号',
    OPERATER_ID          BIGINT default 0 comment '操作人编号',
    OPERATER_TIME        DATETIME comment '操作日期时间',
    LOGIN_NAME           VARCHAR(256) default '' comment '操作账户名',
    IP                   VARCHAR(256) default '' comment 'IP地址',
    OPERATER_TITLE       VARCHAR(256) default '' comment '操作标题',
    OPERATER_CONTENT     VARCHAR(2000) default '' comment '操作内容',
    COMPANY_CODE         VARCHAR(512) default '' comment '公司唯一代码',
    COMPANY_ID           BIGINT default 0 comment '公司编号',
    primary key (ID)
);

alter table SYSTEM_LOG comment '系统管理操作日志表';

/*==============================================================*/
/* Table: SYSTEM_MENU                                           */
/*==============================================================*/
create table SYSTEM_MENU
(
    ID                   BIGINT not null auto_increment comment '编号',
    CREATE_ID            BIGINT default 0 comment '创建人编号',
    CREATE_TIME          DATETIME comment '创建日期时间',
    OPERATER_ID          BIGINT default 0 comment '操作人编号',
    OPERATER_TIME        DATETIME comment '操作日期时间',
    OPERATER_STATUS      INT default 0 comment '操作状态',
    IS_ENABLE            INT default 0 comment '是否启用',
    PARENT_ID            BIGINT default 0 comment '本表上级编号',
    NAME                 VARCHAR(512) default '' comment '菜单名称',
    CODE                 VARCHAR(255) default '' comment '菜单唯一代码',
    LEVEL                INT default 0 comment '菜单层级',
    URL                  VARCHAR(512) default '' comment '请求URL',
    ICON                 VARCHAR(128) default '' comment '图标',
    INDEX_SORT           INT default 0 comment '排序',
    CATEGORY             INT default 0 comment '类别',
    TYPE                 INT default 0 comment '类型',
    TARGET               VARCHAR(64) default '' comment '菜单打开方式',
    primary key (ID),
    unique key AK_UQ_PARENT_ID_CODE (PARENT_ID, CODE)
);

alter table SYSTEM_MENU comment '菜单表';

/*==============================================================*/
/* Table: SYSTEM_ROLE                                           */
/*==============================================================*/
create table SYSTEM_ROLE
(
    ID                   BIGINT not null auto_increment comment '编号',
    CREATE_ID            BIGINT default 0 comment '创建人编号',
    CREATE_TIME          DATETIME comment '创建日期时间',
    OPERATER_ID          BIGINT default 0 comment '操作人编号',
    OPERATER_TIME        DATETIME comment '操作日期时间',
    OPERATER_STATUS      INT default 0 comment '操作状态',
    IS_ENABLE            INT default 0 comment '是否启用',
    ROLE_NAME            VARCHAR(128) default '' comment '角色名称',
    ROLE_CODE            VARCHAR(255) default '' comment '角色唯一代码',
    ROLE_LEVEL           INT default 0 comment '角色层级',
    DESCRIPTION          VARCHAR(2000) default '' comment '描述',
    primary key (ID),
    unique key AK_UQ_ROLE_CODE (ROLE_CODE)
);

alter table SYSTEM_ROLE comment '角色表';

/*==============================================================*/
/* Table: SYSTEM_TYPE                                           */
/*==============================================================*/
create table SYSTEM_TYPE
(
    ID                   BIGINT not null auto_increment comment '编号',
    CREATE_ID            BIGINT default 0 comment '创建人编号',
    CREATE_TIME          DATETIME comment '创建日期时间',
    OPERATER_ID          BIGINT default 0 comment '操作人编号',
    OPERATER_TIME        DATETIME comment '操作日期时间',
    OPERATER_STATUS      INT default 0 comment '操作状态',
    IS_ENABLE            INT default 0 comment '是否启用',
    PARENT_ID            BIGINT default 0 comment '上级编号',
    TYPE_NAME            VARCHAR(256) default '' comment '名称',
    TYPE_CODE            VARCHAR(128) default '' comment '类型代码',
    TYPE_VALUE           INT default 0 comment '类型数值',
    DESCRIPTION          VARCHAR(2000) default '' comment '描述',
    primary key (ID)
);

alter table SYSTEM_TYPE comment '系统类型表';

/*==============================================================*/
/* Table: SYSTEM_USER                                           */
/*==============================================================*/
create table SYSTEM_USER
(
    ID                   BIGINT not null auto_increment comment '编号',
    CREATE_ID            BIGINT default 0 comment '创建人编号',
    CREATE_TIME          DATETIME comment '创建日期时间',
    OPERATER_ID          BIGINT default 0 comment '操作人编号',
    OPERATER_TIME        DATETIME comment '操作日期时间',
    OPERATER_STATUS      INT default 0 comment '操作状态',
    IS_ENABLE            INT default 0 comment '是否启用',
    ACCOUNT_LOCKED       INT default 0 comment '是否锁住',
    ACCOUNT_EXPIRED      INT default 0 comment '帐号是否过期',
    CREDENTIALS_EXPIRED  INT default 0 comment '凭证是否过期',
    LOGIN_NAME           VARCHAR(64) default '' comment '账户名',
    PASSWORD             VARCHAR(64) default '' comment '密码',
    NAME                 VARCHAR(256) default '' comment '姓名',
    COMPANY_CODE         VARCHAR(512) default '' comment '公司唯一代码',
    COMPANY_ID           INT default 0 comment '公司编号',
    TELPHONE             VARCHAR(32) default '' comment '电话',
    EMAIL                VARCHAR(128) default '' comment '邮箱',
    USER_LEVEL           INT default 0 comment '用户级别',
    DESCRIPTION          VARCHAR(2000) default '' comment '描述',
    primary key (ID),
    unique key AK_UQ_LOGIN_NAME (LOGIN_NAME)
);

alter table SYSTEM_USER comment '用户管理表';

/*==============================================================*/
/* Table: TB_SYSTEM_ROLE_MENU                                   */
/*==============================================================*/
create table TB_SYSTEM_ROLE_MENU
(
    ROLE_ID              BIGINT comment 'SYSTEM_ROLE表中主键ID',
    MENU_ID              BIGINT comment 'SYSTEM_MENU表中主键ID'
);

alter table TB_SYSTEM_ROLE_MENU comment '角色菜单关联表';

/*==============================================================*/
/* Table: TB_SYSTEM_USER_ROLE                                   */
/*==============================================================*/
create table TB_SYSTEM_USER_ROLE
(
    USER_ID              BIGINT comment 'SYSTEM_USER表中主键ID',
    ROLE_ID              BIGINT comment 'SYSTEM_ROLE表中主键ID'
);

alter table TB_SYSTEM_USER_ROLE comment '用户角色关联表';

/*==============================================================*/
/* Table: T_FILE_INFO                                           */
/*==============================================================*/
create table T_FILE_INFO
(
    ID                   BIGINT not null auto_increment comment '编号',
    CREATE_ID            BIGINT default 0 comment '创建人编号',
    CREATE_TIME          DATETIME comment '创建日期时间',
    OPERATER_ID          BIGINT default 0 comment '操作人编号',
    OPERATER_TIME        DATETIME comment '操作日期时间',
    OPERATER_STATUS      INT default 0 comment '操作状态',
    BUSINESS_ID          VARCHAR(32) default '' comment '所属编号',
    CODE                 VARCHAR(255) default '' comment '唯一代码',
    CLASSIFICATION       INT default 0 comment '分类',
    OLD_NAME             VARCHAR(128) default '' comment '原始文件名',
    NEW_NAME             VARCHAR(128) default '' comment '新文件名',
    FILE_TYPE            INT default 0 comment '文件类型',
    FILE_PATH            VARCHAR(256) default '' comment '文件路径',
    primary key (ID)
);

alter table T_FILE_INFO comment '文件信息表';

