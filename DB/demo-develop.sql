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
   ID                   BIGINT not null auto_increment comment '���',
   CREATE_ID            BIGINT comment '�����˱��',
   CREATE_TIME          DATETIME comment '��������ʱ��',
   OPERATER_ID          BIGINT comment '�����˱��',
   OPERATER_TIME        DATETIME comment '��������ʱ��',
   OPERATER_STATUS      INT comment '����״̬�� -1=ɾ���� 1=����, 2=�޸�',
   IS_ENABLE            INT comment '�Ƿ����ã�1=����,2=����',
   IP                   VARCHAR(128) comment 'IP',
   DESCRIPTION          VARCHAR(2000) comment '��ɫ����',
   primary key (ID)
);

alter table SYSTEM_BLACK_LIST_IP comment 'ϵͳ������-ǰ�˵�¼';

/*==============================================================*/
/* Table: SYSTEM_BUTTON                                         */
/*==============================================================*/
create table SYSTEM_BUTTON
(
   ID                   BIGINT not null auto_increment comment '���',
   CREATE_ID            BIGINT comment '�����˱��',
   CREATE_TIME          DATETIME comment '��������ʱ��',
   OPERATER_ID          BIGINT comment '�����˱��',
   OPERATER_TIME        DATETIME comment '��������ʱ��',
   OPERATER_STATUS      INT comment '����״̬��-1=ɾ����1=����, 2=�޸�',
   IS_ENABLE            INT comment '�Ƿ����ã�1=����,2=����',
   BUTTON_NAME          VARCHAR(256) comment '��ť����',
   BUTTON_CODE          VARCHAR(256) comment '��ťΨһ����',
   DESCRIPTION          VARCHAR(2000) comment '��ɫ����',
   primary key (ID),
   unique key AK_UQ_MENU_CODE (BUTTON_CODE)
);

alter table SYSTEM_BUTTON comment '��ťȨ�޹����';

/*==============================================================*/
/* Table: SYSTEM_DICT                                           */
/*==============================================================*/
create table SYSTEM_DICT
(
   ID                   BIGINT not null auto_increment comment '���',
   CREATE_ID            BIGINT comment '�����˱��',
   CREATE_TIME          DATETIME comment '��������ʱ��',
   OPERATER_ID          BIGINT comment '�����˱��',
   OPERATER_TIME        DATETIME comment '��������ʱ��',
   OPERATER_STATUS      INT comment '����״̬�� -1=ɾ���� 1=����, 2=�޸�',
   DICT_KEY             VARCHAR(128) comment '�ֵ��',
   IS_ENABLE            INT comment '�Ƿ����ã�1=����,2=����',
   DESCRIPTION          VARCHAR(2000) comment '����',
   primary key (ID),
   unique key AK_UQ_DICT_KEY (DICT_KEY)
);

alter table SYSTEM_DICT comment 'ϵͳ�ֵ��';

/*==============================================================*/
/* Table: SYSTEM_LOG                                            */
/*==============================================================*/
create table SYSTEM_LOG
(
   ID                   BIGINT not null auto_increment comment '���',
   OPERATER_ID          BIGINT comment '�����˱��',
   OPERATER_TIME        DATETIME comment '��������ʱ��',
   LOGIN_NAME           VARCHAR(256) comment '�����˻���',
   IP                   VARCHAR(256) comment 'IP��ַ',
   OPERATER_TITLE       VARCHAR(256) comment '��������',
   OPERATER_CONTENT     VARCHAR(2000) comment '��������',
   COMPANY_CODE         VARCHAR(512) comment '��˾Ψһ����,UUID32λ',
   COMPANY_ID           BIGINT comment '��˾���',
   primary key (ID)
);

alter table SYSTEM_LOG comment 'ϵͳ���������־��';

/*==============================================================*/
/* Table: SYSTEM_MENU                                           */
/*==============================================================*/
create table SYSTEM_MENU
(
   ID                   BIGINT not null auto_increment comment '���',
   CREATE_ID            BIGINT comment '�����˱��',
   CREATE_TIME          DATETIME comment '��������ʱ��',
   OPERATER_ID          BIGINT comment '�����˱��',
   OPERATER_TIME        DATETIME comment '��������ʱ��',
   OPERATER_STATUS      INT comment '����״̬��-1=ɾ����1=����, 2=�޸�',
   IS_ENABLE            INT comment '�Ƿ����ã�1=����,2=����',
   PARENT_ID            BIGINT comment '�����ϼ����',
   MENU_NAME            VARCHAR(512) comment '�˵�����',
   MENU_CODE            VARCHAR(255) comment '�˵�Ψһ����',
   MENU_LEVEL           INT comment '�˵��㼶,�����˵�Ϊ0��Ϊֻ��һ����ģ��˵�Ϊ1�ж��(�磺ϵͳ����XXX����)
            ÿ���˵������ϼ��˵�������1���ò˵�����',
   URL_PATH             VARCHAR(128) comment '����URL',
   ICON                 VARCHAR(128) comment 'ͼ��',
   INDEX_SORT           INT comment '����',
   ADMIN_TYPE           INT comment '�˵����ͣ�1����Ա�˵� 2��ͨ�˵�',
   TARGET               VARCHAR(64) comment '�˵��򿪷�ʽ��_selfĬ�ϴ򿪷�ʽ��_blank������´��ڴ򿪷�ʽ',
   primary key (ID),
   unique key AK_UQ_MENU_CODE (MENU_CODE)
);

alter table SYSTEM_MENU comment '�˵���';

/*==============================================================*/
/* Table: SYSTEM_ROLE                                           */
/*==============================================================*/
create table SYSTEM_ROLE
(
   ID                   BIGINT not null auto_increment comment '���',
   CREATE_ID            BIGINT comment '�����˱��',
   CREATE_TIME          DATETIME comment '��������ʱ��',
   OPERATER_ID          BIGINT comment '�����˱��',
   OPERATER_TIME        DATETIME comment '��������ʱ��',
   OPERATER_STATUS      INT comment '����״̬:-1=ɾ����1=����, 2=�޸�',
   IS_ENABLE            INT comment '�Ƿ����ã�1=����,2=����',
   ROLE_NAME            VARCHAR(128) comment '��ɫ����',
   ROLE_CODE            VARCHAR(256) comment '��ɫΨһ����',
   ROLE_LEVEL           INT comment '��ɫ�㼶�����ڶ����ɫ�����������ʾ��ֵ��С������û�չʾ��',
   DESCRIPTION          VARCHAR(2000) comment '��ɫ����',
   primary key (ID),
   unique key AK_UQ_ROLE_CODE (ROLE_CODE)
);

alter table SYSTEM_ROLE comment '��ɫ��';

/*==============================================================*/
/* Table: SYSTEM_TYPE                                           */
/*==============================================================*/
create table SYSTEM_TYPE
(
   ID                   BIGINT not null auto_increment comment '���',
   CREATE_ID            BIGINT comment '�����˱��',
   CREATE_TIME          DATETIME comment '��������ʱ��',
   OPERATER_ID          BIGINT comment '�����˱��',
   OPERATER_TIME        DATETIME comment '��������ʱ��',
   OPERATER_STATUS      INT comment '����״̬�� -1=ɾ���� 1=����, 2=�޸�',
   IS_ENABLE            INT comment '�Ƿ����ã�1=����,2=����',
   TYPE_NAME            VARCHAR(256) comment '����',
   TYPE_CODE            VARCHAR(128) comment '���ʹ���',
   DESCRIPTION          VARCHAR(2000) comment '����',
   primary key (ID),
   unique key AK_UQ_TYPE_CODE (TYPE_CODE)
);

alter table SYSTEM_TYPE comment 'ϵͳ���ͱ�';

/*==============================================================*/
/* Table: SYSTEM_USER                                           */
/*==============================================================*/
create table SYSTEM_USER
(
   ID                   BIGINT not null auto_increment comment '���',
   CREATE_ID            BIGINT comment '�����˱��',
   CREATE_TIME          DATETIME comment '��������ʱ��',
   OPERATER_ID          BIGINT comment '�����˱��',
   OPERATER_TIME        DATETIME comment '��������ʱ��',
   OPERATER_STATUS      INT comment '����״̬�� -1=ɾ���� 1=����, 2=�޸�',
   IS_ENABLE            INT comment '�Ƿ�����:1=����,2=����',
   ACCOUNT_LOCKED       INT comment '�Ƿ���ס:1=��, 2=��',
   ACCOUNT_EXPIRED      INT comment '�ʺ��Ƿ����:1=��, 2=��',
   CREDENTIALS_EXPIRED  INT comment 'ƾ֤�Ƿ����:1=��, 2=��',
   LOGIN_NAME           VARCHAR(64) comment '�˻���',
   PASSWORD             VARCHAR(64) comment '����',
   NAME                 VARCHAR(256) comment '����',
   COMPANY_CODE         VARCHAR(512) comment '��˾Ψһ����,UUID32λ',
   COMPANY_ID           INT comment '��˾���',
   TELPHONE             VARCHAR(32) comment '�绰',
   EMAIL                VARCHAR(128) comment '����',
   USER_LEVEL           INT comment '�û����𣬶����˻�0�������ݵ�ǰ�˻���������+1����',
   DESCRIPTION          VARCHAR(2000) comment 'ϵͳ����Ա(������)����',
   primary key (ID),
   unique key AK_UQ_LOGIN_NAME (LOGIN_NAME)
);

alter table SYSTEM_USER comment '�û������';

/*==============================================================*/
/* Table: SYSTEM_WHITE_LIST_IP                                  */
/*==============================================================*/
create table SYSTEM_WHITE_LIST_IP
(
   ID                   BIGINT not null auto_increment comment '���',
   CREATE_ID            BIGINT comment '�����˱��',
   CREATE_TIME          DATETIME comment '��������ʱ��',
   OPERATER_ID          BIGINT comment '�����˱��',
   OPERATER_TIME        DATETIME comment '��������ʱ��',
   OPERATER_STATUS      INT comment '����״̬�� -1=ɾ���� 1=����, 2=�޸�',
   IS_ENABLE            INT comment '�Ƿ����ã�1=����,2=����',
   IP                   VARCHAR(128) comment 'IP',
   DESCRIPTION          VARCHAR(2000) comment '��ɫ����',
   primary key (ID)
);

alter table SYSTEM_WHITE_LIST_IP comment 'ϵͳ������-��̨��¼';

/*==============================================================*/
/* Table: TB_SYSTEM_ROLE_MENU                                   */
/*==============================================================*/
create table TB_SYSTEM_ROLE_MENU
(
   ROLE_ID              BIGINT comment 'SYSTEM_ROLE��������ID',
   MENU_ID              BIGINT comment 'SYSTEM_MENU��������ID'
);

alter table TB_SYSTEM_ROLE_MENU comment '��ɫ�˵�������';

/*==============================================================*/
/* Table: TB_SYSTEM_ROLE_MENU_BUTTON                            */
/*==============================================================*/
create table TB_SYSTEM_ROLE_MENU_BUTTON
(
   ROLE_ID              BIGINT comment 'SYSTEM_ROLE���е�����ID',
   MENU_ID              BIGINT comment 'SYSTEM_MENU���е�����ID',
   BUTTON_ID            BIGINT comment 'SYSTEM_BUTTON�������ID'
);

alter table TB_SYSTEM_ROLE_MENU_BUTTON comment '��ɫ�˵���ť�м��';

/*==============================================================*/
/* Table: TB_SYSTEM_USER_ROLE                                   */
/*==============================================================*/
create table TB_SYSTEM_USER_ROLE
(
   USER_ID              BIGINT comment 'SYSTEM_USER��������ID',
   ROLE_ID              BIGINT comment 'SYSTEM_ROLE��������ID'
);

alter table TB_SYSTEM_USER_ROLE comment '�û���ɫ������';

/*==============================================================*/
/* Table: T_FILE_INFO                                           */
/*==============================================================*/
create table T_FILE_INFO
(
   ID                   BIGINT not null auto_increment comment '���',
   CREATE_ID            BIGINT comment '�����˱��',
   CREATE_TIME          DATETIME comment '��������ʱ��',
   OPERATER_ID          BIGINT comment '�����˱��',
   OPERATER_TIME        DATETIME comment '��������ʱ��',
   OPERATER_STATUS      INT comment '����״̬�� -1=ɾ���� 1=����, 2=�޸�',
   BUSINESS_ID          BIGINT comment '�������,��Ӧ�ĸ��˵������ݱ��',
   CODE                 VARCHAR(255) comment 'Ψһ����',
   FILE_NAME            VARCHAR(128) comment '�ļ���',
   FILE_TYPE            INT comment '�ļ�����(1=txt,2=doc�ȡ���Ҫ����ʵ�ʻ�������)',
   FILE_PATH            VARCHAR(256) comment '�ļ�·��,�����м���Ļ�������洢�ľ��Ƕ�Ӧ��key',
   primary key (ID)
);

alter table T_FILE_INFO comment '�ļ���Ϣ��';

