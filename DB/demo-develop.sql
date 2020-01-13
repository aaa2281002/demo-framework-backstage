/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/1/6 11:11:09                            */
/*==============================================================*/


drop table if exists SYSTEM_BLACK_LIST_IP;

drop table if exists SYSTEM_BUTTON;

drop table if exists SYSTEM_DICT;

drop table if exists SYSTEM_LOG;

drop table if exists SYSTEM_MENU;

drop table if exists SYSTEM_ROLE;

drop table if exists SYSTEM_TYPE;

drop table if exists SYSTEM_USER;

drop table if exists SYSTEM_WHITE_LIST_IP;

drop table if exists TB_SYSTEM_ROLE_MENU;

drop table if exists TB_SYSTEM_ROLE_MENU_BUTTON;

drop table if exists TB_SYSTEM_USER_ROLE;

drop table if exists T_FILE_INFO;

/*==============================================================*/
/* Table: SYSTEM_BLACK_LIST_IP                                  */
/*==============================================================*/
create table SYSTEM_BLACK_LIST_IP
(
   ID                   BIGINT not null auto_increment comment '编号',
   CREATE_ID            BIGINT comment '创建人编号',
   CREATE_TIME          DATETIME comment '创建日期时间',
   OPERATER_ID          BIGINT comment '操作人编号',
   OPERATER_TIME        DATETIME comment '操作日期时间',
   OPERATER_STATUS      INT comment '操作状态： -1=删除， 1=新增, 2=修改',
   IS_ENABLE            INT comment '是否启用：1=启用,2=禁用',
   IP                   VARCHAR(128) comment 'IP',
   DESCRIPTION          VARCHAR(2000) comment '角色描述',
   primary key (ID)
);

alter table SYSTEM_BLACK_LIST_IP comment '系统黑名单-前端登录';

/*==============================================================*/
/* Table: SYSTEM_BUTTON                                         */
/*==============================================================*/
create table SYSTEM_BUTTON
(
   ID                   BIGINT not null auto_increment comment '编号',
   CREATE_ID            BIGINT comment '创建人编号',
   CREATE_TIME          DATETIME comment '创建日期时间',
   OPERATER_ID          BIGINT comment '操作人编号',
   OPERATER_TIME        DATETIME comment '操作日期时间',
   OPERATER_STATUS      INT comment '操作状态：-1=删除，1=新增, 2=修改',
   IS_ENABLE            INT comment '是否启用：1=启用,2=禁用',
   BUTTON_NAME          VARCHAR(256) comment '按钮名称',
   BUTTON_CODE          VARCHAR(256) comment '按钮唯一代码',
   DESCRIPTION          VARCHAR(2000) comment '角色描述',
   primary key (ID),
   unique key AK_UQ_MENU_CODE (BUTTON_CODE)
);

alter table SYSTEM_BUTTON comment '按钮权限管理表';

/*==============================================================*/
/* Table: SYSTEM_DICT                                           */
/*==============================================================*/
create table SYSTEM_DICT
(
   ID                   BIGINT not null auto_increment comment '编号',
   CREATE_ID            BIGINT comment '创建人编号',
   CREATE_TIME          DATETIME comment '创建日期时间',
   OPERATER_ID          BIGINT comment '操作人编号',
   OPERATER_TIME        DATETIME comment '操作日期时间',
   OPERATER_STATUS      INT comment '操作状态： -1=删除， 1=新增, 2=修改',
   DICT_KEY             VARCHAR(128) comment '字典键',
   IS_ENABLE            INT comment '是否启用：1=启用,2=禁用',
   DESCRIPTION          VARCHAR(2000) comment '描述',
   primary key (ID),
   unique key AK_UQ_DICT_KEY (DICT_KEY)
);

alter table SYSTEM_DICT comment '系统字典表';

/*==============================================================*/
/* Table: SYSTEM_LOG                                            */
/*==============================================================*/
create table SYSTEM_LOG
(
   ID                   BIGINT not null auto_increment comment '编号',
   OPERATER_ID          BIGINT comment '操作人编号',
   OPERATER_TIME        DATETIME comment '操作日期时间',
   LOGIN_NAME           VARCHAR(256) comment '操作账户名',
   IP                   VARCHAR(256) comment 'IP地址',
   OPERATER_TITLE       VARCHAR(256) comment '操作标题',
   OPERATER_CONTENT     VARCHAR(2000) comment '操作内容',
   COMPANY_CODE         VARCHAR(512) comment '公司唯一代码,UUID32位',
   COMPANY_ID           BIGINT comment '公司编号',
   primary key (ID)
);

alter table SYSTEM_LOG comment '系统管理操作日志表';

/*==============================================================*/
/* Table: SYSTEM_MENU                                           */
/*==============================================================*/
create table SYSTEM_MENU
(
   ID                   BIGINT not null auto_increment comment '编号',
   CREATE_ID            BIGINT comment '创建人编号',
   CREATE_TIME          DATETIME comment '创建日期时间',
   OPERATER_ID          BIGINT comment '操作人编号',
   OPERATER_TIME        DATETIME comment '操作日期时间',
   OPERATER_STATUS      INT comment '操作状态：-1=删除，1=新增, 2=修改',
   IS_ENABLE            INT comment '是否启用：1=启用,2=禁用',
   PARENT_ID            BIGINT comment '本表上级编号',
   MENU_NAME            VARCHAR(512) comment '菜单名称',
   MENU_CODE            VARCHAR(255) comment '菜单唯一代码',
   MENU_LEVEL           INT comment '菜单层级,顶级菜单为0因为只有一个，模块菜单为1有多个(如：系统管理，XXX管理)
            每级菜单根据上级菜单来自增1设置菜单级别',
   URL_PATH             VARCHAR(128) comment '请求URL',
   ICON                 VARCHAR(128) comment '图标',
   INDEX_SORT           INT comment '排序',
   ADMIN_TYPE           INT comment '菜单类型，1管理员菜单 2普通菜单',
   TARGET               VARCHAR(64) comment '菜单打开方式，_self默认打开方式，_blank浏览器新窗口打开方式',
   primary key (ID),
   unique key AK_UQ_MENU_CODE (MENU_CODE)
);

alter table SYSTEM_MENU comment '菜单表';

/*==============================================================*/
/* Table: SYSTEM_ROLE                                           */
/*==============================================================*/
create table SYSTEM_ROLE
(
   ID                   BIGINT not null auto_increment comment '编号',
   CREATE_ID            BIGINT comment '创建人编号',
   CREATE_TIME          DATETIME comment '创建日期时间',
   OPERATER_ID          BIGINT comment '操作人编号',
   OPERATER_TIME        DATETIME comment '操作日期时间',
   OPERATER_STATUS      INT comment '操作状态:-1=删除，1=新增, 2=修改',
   IS_ENABLE            INT comment '是否启用：1=启用,2=禁用',
   ROLE_NAME            VARCHAR(128) comment '角色名称',
   ROLE_CODE            VARCHAR(256) comment '角色唯一代码',
   ROLE_LEVEL           INT comment '角色层级，用于多个角色情况下优先显示数值最小级别给用户展示。',
   DESCRIPTION          VARCHAR(2000) comment '角色描述',
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
   CREATE_ID            BIGINT comment '创建人编号',
   CREATE_TIME          DATETIME comment '创建日期时间',
   OPERATER_ID          BIGINT comment '操作人编号',
   OPERATER_TIME        DATETIME comment '操作日期时间',
   OPERATER_STATUS      INT comment '操作状态： -1=删除， 1=新增, 2=修改',
   IS_ENABLE            INT comment '是否启用：1=启用,2=禁用',
   TYPE_NAME            VARCHAR(256) comment '名称',
   TYPE_CODE            VARCHAR(128) comment '类型代码',
   DESCRIPTION          VARCHAR(2000) comment '描述',
   primary key (ID),
   unique key AK_UQ_TYPE_CODE (TYPE_CODE)
);

alter table SYSTEM_TYPE comment '系统类型表';

/*==============================================================*/
/* Table: SYSTEM_USER                                           */
/*==============================================================*/
create table SYSTEM_USER
(
   ID                   BIGINT not null auto_increment comment '编号',
   CREATE_ID            BIGINT comment '创建人编号',
   CREATE_TIME          DATETIME comment '创建日期时间',
   OPERATER_ID          BIGINT comment '操作人编号',
   OPERATER_TIME        DATETIME comment '操作日期时间',
   OPERATER_STATUS      INT comment '操作状态： -1=删除， 1=新增, 2=修改',
   IS_ENABLE            INT comment '是否启用:1=启用,2=禁用',
   ACCOUNT_LOCKED       INT comment '是否锁住:1=否, 2=是',
   ACCOUNT_EXPIRED      INT comment '帐号是否过期:1=否, 2=是',
   CREDENTIALS_EXPIRED  INT comment '凭证是否过期:1=否, 2=是',
   LOGIN_NAME           VARCHAR(64) comment '账户名',
   PASSWORD             VARCHAR(64) comment '密码',
   NAME                 VARCHAR(256) comment '密码',
   COMPANY_CODE         VARCHAR(512) comment '公司唯一代码,UUID32位',
   COMPANY_ID           INT comment '公司编号',
   TELPHONE             VARCHAR(32) comment '电话',
   EMAIL                VARCHAR(128) comment '邮箱',
   USER_LEVEL           INT comment '用户级别，顶级账户0级，根据当前账户创建进行+1递增',
   DESCRIPTION          VARCHAR(2000) comment '系统管理员(代理商)描述',
   primary key (ID),
   unique key AK_UQ_LOGIN_NAME (LOGIN_NAME)
);

alter table SYSTEM_USER comment '用户管理表';

/*==============================================================*/
/* Table: SYSTEM_WHITE_LIST_IP                                  */
/*==============================================================*/
create table SYSTEM_WHITE_LIST_IP
(
   ID                   BIGINT not null auto_increment comment '编号',
   CREATE_ID            BIGINT comment '创建人编号',
   CREATE_TIME          DATETIME comment '创建日期时间',
   OPERATER_ID          BIGINT comment '操作人编号',
   OPERATER_TIME        DATETIME comment '操作日期时间',
   OPERATER_STATUS      INT comment '操作状态： -1=删除， 1=新增, 2=修改',
   IS_ENABLE            INT comment '是否启用：1=启用,2=禁用',
   IP                   VARCHAR(128) comment 'IP',
   DESCRIPTION          VARCHAR(2000) comment '角色描述',
   primary key (ID)
);

alter table SYSTEM_WHITE_LIST_IP comment '系统白名单-后台登录';

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
/* Table: TB_SYSTEM_ROLE_MENU_BUTTON                            */
/*==============================================================*/
create table TB_SYSTEM_ROLE_MENU_BUTTON
(
   ROLE_ID              BIGINT comment 'SYSTEM_ROLE表中的主键ID',
   MENU_ID              BIGINT comment 'SYSTEM_MENU表中的主键ID',
   BUTTON_ID            BIGINT comment 'SYSTEM_BUTTON表的主键ID'
);

alter table TB_SYSTEM_ROLE_MENU_BUTTON comment '角色菜单按钮中间表';

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
   CREATE_ID            BIGINT comment '创建人编号',
   CREATE_TIME          DATETIME comment '创建日期时间',
   OPERATER_ID          BIGINT comment '操作人编号',
   OPERATER_TIME        DATETIME comment '操作日期时间',
   OPERATER_STATUS      INT comment '操作状态： -1=删除， 1=新增, 2=修改',
   BUSINESS_ID          BIGINT comment '所属编号,对应哪个菜单下数据编号',
   CODE                 VARCHAR(255) comment '唯一代码',
   FILE_NAME            VARCHAR(128) comment '文件名',
   FILE_TYPE            INT comment '文件类型(1=txt,2=doc等。需要根据实际环境定义)',
   FILE_PATH            VARCHAR(256) comment '文件路径,用了中间件的话，这个存储的就是对应的key',
   primary key (ID)
);

alter table T_FILE_INFO comment '文件信息表';

