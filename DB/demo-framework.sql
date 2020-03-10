/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50644
 Source Host           : localhost:3306
 Source Schema         : demoframework

 Target Server Type    : MySQL
 Target Server Version : 50644
 File Encoding         : 65001

 Date: 10/03/2020 15:21:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins`  (
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `series` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `token` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `last_used` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`series`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for system_black_list_ip
-- ----------------------------
DROP TABLE IF EXISTS `system_black_list_ip`;
CREATE TABLE `system_black_list_ip`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `CREATE_ID` bigint(20) NULL DEFAULT NULL COMMENT '创建人编号',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建日期时间',
  `OPERATER_ID` bigint(20) NULL DEFAULT NULL COMMENT '操作人编号',
  `OPERATER_TIME` datetime(0) NULL DEFAULT NULL COMMENT '操作日期时间',
  `OPERATER_STATUS` int(11) NULL DEFAULT NULL COMMENT '操作状态 -1删除， 1新增, 2修改',
  `IS_ENABLE` int(11) NULL DEFAULT NULL COMMENT '是否启用1启用,2禁用',
  `IP` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP',
  `DESCRIPTION` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统黑名单-前端登录' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for system_button
-- ----------------------------
DROP TABLE IF EXISTS `system_button`;
CREATE TABLE `system_button`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `CREATE_ID` bigint(20) NULL DEFAULT NULL COMMENT '创建人编号',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建日期时间',
  `OPERATER_ID` bigint(20) NULL DEFAULT NULL COMMENT '操作人编号',
  `OPERATER_TIME` datetime(0) NULL DEFAULT NULL COMMENT '操作日期时间',
  `OPERATER_STATUS` int(11) NULL DEFAULT NULL COMMENT '操作状态 -1删除， 1新增, 2修改',
  `IS_ENABLE` int(11) NULL DEFAULT NULL COMMENT '是否启用-1禁用， 1启用，',
  `BUTTON_NAME` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '按钮名称',
  `BUTTON_CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '按钮唯一代码',
  `DESCRIPTION` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `BUTTON_CODE`(`BUTTON_CODE`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '按钮权限管理表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of system_button
-- ----------------------------
INSERT INTO `system_button` VALUES (1, 1, '2019-07-06 15:04:39', 1, '2019-07-06 15:04:39', 1, 1, '新增', 'add', '新增');
INSERT INTO `system_button` VALUES (2, 1, '2019-07-20 14:08:22', 1, '2019-07-20 14:08:22', 1, 1, '新增', 'insert', '新增');
INSERT INTO `system_button` VALUES (3, 1, '2019-07-20 14:09:20', 1, '2019-07-20 14:09:20', 1, 1, '新增', 'create', '新增');
INSERT INTO `system_button` VALUES (4, 1, '2019-07-20 14:00:32', 1, '2019-07-20 14:00:32', 1, 1, '新增', 'save', '保存');
INSERT INTO `system_button` VALUES (5, 1, '2019-07-20 14:14:10', 1, '2019-07-20 14:14:10', 1, 1, '批量新增', 'batchSave', '批量新增');
INSERT INTO `system_button` VALUES (6, 1, '2019-07-20 14:13:51', 1, '2019-07-20 14:13:51', 1, 1, '批量新增', 'batchCreate', '批量新增');
INSERT INTO `system_button` VALUES (7, 1, '2019-07-20 14:13:29', 1, '2019-07-20 14:13:29', 1, 1, '批量新增', 'batchInsert', '批量新增');
INSERT INTO `system_button` VALUES (8, 1, '2019-07-20 14:13:02', 1, '2019-07-20 14:13:02', 1, 1, '批量新增', 'batchAdd', '批量新增');
INSERT INTO `system_button` VALUES (9, 1, '2019-07-20 14:01:57', 1, '2019-07-20 14:01:57', 1, 1, '修改', 'upd', '修改');
INSERT INTO `system_button` VALUES (10, 1, '2019-07-06 15:04:52', 1, '2019-07-06 15:04:52', 1, 1, '修改', 'edit', '修改');
INSERT INTO `system_button` VALUES (11, 1, '2019-07-20 14:15:47', 1, '2019-07-20 14:15:47', 1, 1, '批量修改', 'batchUpd', '批量修改');
INSERT INTO `system_button` VALUES (12, 1, '2019-07-20 14:16:01', 1, '2019-07-20 14:16:01', 1, 1, '批量修改', 'batchEdit', '批量修改');
INSERT INTO `system_button` VALUES (13, 1, '2019-07-06 15:05:08', 1, '2019-07-06 15:05:08', 1, 1, '删除', 'del', '删除');
INSERT INTO `system_button` VALUES (14, 1, '2019-07-20 14:08:02', 1, '2019-07-20 14:08:02', 1, 1, '删除', 'remove', '删除');
INSERT INTO `system_button` VALUES (15, 1, '2019-07-06 15:05:08', 1, '2019-07-06 15:05:08', 1, 1, '批量删除', 'batchDel', '批量删除');
INSERT INTO `system_button` VALUES (16, 1, '2019-07-20 14:17:16', 1, '2019-07-20 14:17:16', 1, 1, '批量删除', 'batchRemove', '批量删除');
INSERT INTO `system_button` VALUES (17, 1, '2019-07-06 15:06:00', 1, '2019-07-20 11:12:58', 2, 1, '查询', 'view', '查看');
INSERT INTO `system_button` VALUES (18, 1, '2019-07-20 14:09:53', 1, '2019-07-20 14:09:53', 1, 1, '查询', 'list', '查询');
INSERT INTO `system_button` VALUES (19, 1, '2019-07-20 14:00:47', 1, '2019-07-20 14:00:47', 1, 1, '查询', 'find', '查询');
INSERT INTO `system_button` VALUES (20, 1, '2019-07-20 14:00:56', 1, '2019-07-20 14:01:04', 2, 1, '查询', 'get', '查询');
INSERT INTO `system_button` VALUES (21, 1, '2019-07-20 14:01:42', 1, '2019-07-20 14:01:42', 1, 1, '查询', 'query', '查询');
INSERT INTO `system_button` VALUES (22, 1, '2019-07-20 14:09:43', 1, '2019-07-20 14:09:43', 1, 1, '查询', 'select', '查询');
INSERT INTO `system_button` VALUES (23, 1, '2019-07-20 14:22:29', 1, '2019-07-20 14:22:29', 1, 1, '上传', 'upload', '上传');
INSERT INTO `system_button` VALUES (24, 1, '2019-07-20 14:22:48', 1, '2019-07-20 14:22:48', 1, 1, '批量上传', 'batchUpload', '批量上传');
INSERT INTO `system_button` VALUES (25, 1, '2019-07-20 14:23:04', 1, '2019-07-20 14:23:04', 1, 1, '下载', 'download', '下载');
INSERT INTO `system_button` VALUES (26, 1, '2019-07-20 14:23:05', 1, '2019-07-20 14:23:05', 1, 1, '验证是否存在', 'isExist', '验证是否存在');

-- ----------------------------
-- Table structure for system_dict
-- ----------------------------
DROP TABLE IF EXISTS `system_dict`;
CREATE TABLE `system_dict`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `CREATE_ID` bigint(20) NULL DEFAULT NULL COMMENT '创建人编号',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建日期时间',
  `OPERATER_ID` bigint(20) NULL DEFAULT NULL COMMENT '操作人编号',
  `OPERATER_TIME` datetime(0) NULL DEFAULT NULL COMMENT '操作日期时间',
  `OPERATER_STATUS` int(11) NULL DEFAULT NULL COMMENT '操作状态 -1删除， 1新增, 2修改',
  `DICT_KEY` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典键',
  `IS_ENABLE` int(11) NULL DEFAULT NULL COMMENT '是否启用1启用,2禁用',
  `DESCRIPTION` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `AK_UQ_DICT_KEY`(`DICT_KEY`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统字典表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for system_log
-- ----------------------------
DROP TABLE IF EXISTS `system_log`;
CREATE TABLE `system_log`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `OPERATER_ID` bigint(20) NULL DEFAULT NULL COMMENT '操作人编号',
  `OPERATER_TIME` datetime(0) NULL DEFAULT NULL COMMENT '操作日期时间',
  `LOGIN_NAME` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作账户名',
  `IP` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `OPERATER_TITLE` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作标题',
  `OPERATER_CONTENT` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作内容',
  `COMPANY_CODE` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司唯一代码,UUID32位',
  `COMPANY_ID` bigint(20) NULL DEFAULT NULL COMMENT '公司编号',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统管理操作日志表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for system_menu
-- ----------------------------
DROP TABLE IF EXISTS `system_menu`;
CREATE TABLE `system_menu`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `CREATE_ID` bigint(20) NULL DEFAULT NULL COMMENT '创建人编号',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建日期时间',
  `OPERATER_ID` bigint(20) NULL DEFAULT NULL COMMENT '操作人编号',
  `OPERATER_TIME` datetime(0) NULL DEFAULT NULL COMMENT '操作日期时间',
  `OPERATER_STATUS` int(11) NULL DEFAULT NULL COMMENT '操作状态 -1删除， 1新增, 2修改',
  `IS_ENABLE` int(11) NULL DEFAULT NULL COMMENT '是否启用-1禁用， 1启用，',
  `PARENT_ID` bigint(20) NULL DEFAULT NULL COMMENT '本表上级编号',
  `MENU_NAME` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `MENU_CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单唯一代码',
  `MENU_LEVEL` int(11) NULL DEFAULT NULL COMMENT '菜单层级,顶级菜单为0因为只有一个，模块菜单为1有多个(如：系统管理，XXX管理)\r\n            每级菜单根据上级菜单来自增1设置菜单级别',
  `URL_PATH` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求URL',
  `ICON` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `INDEX_SORT` int(11) NULL DEFAULT NULL COMMENT '排序',
  `ADMIN_TYPE` int(11) NULL DEFAULT NULL COMMENT '菜单类型，1管理员菜单 2普通菜单',
  `TARGET` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单打开方式，_self默认打开方式，_blank浏览器新窗口打开方式',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `AK_UQ_MENU_CODE`(`MENU_CODE`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of system_menu
-- ----------------------------
INSERT INTO `system_menu` VALUES (1, 1, '2019-02-13 13:05:00', 1, '2019-07-13 22:54:19', 2, 1, 0, '系统管理', 'SYSTEM_MANAGEMENT', 0, '', 'fa fa-gear', 1, 1, '_self');
INSERT INTO `system_menu` VALUES (2, 1, '2019-02-13 13:05:01', 1, '2019-02-13 13:05:01', 1, 1, 1, '用户管理', 'SYSTEM_USER_MANAGEMENT', 1, '/system/user/page/list', 'fa fa-user', 1, 1, '_self');
INSERT INTO `system_menu` VALUES (3, 1, '2019-02-13 13:05:02', 1, '2019-02-13 13:05:02', 1, 1, 1, '角色管理', 'SYSTEM_ROLE_MANAGEMENT', 1, '/system/role/page/list', 'fa fa-window-maximize', 2, 1, '_self');
INSERT INTO `system_menu` VALUES (4, 1, '2019-02-13 13:05:03', 1, '2019-12-17 09:38:57', 2, 1, 1, '菜单管理', 'SYSTEM_MENU_MANAGEMENT', 1, '/system/menu/page/list', 'fa fa-bars', 3, 1, '_self');
INSERT INTO `system_menu` VALUES (5, 1, '2019-02-13 13:15:04', 1, '2019-02-13 13:15:04', 1, 1, 1, '按钮管理', 'SYSTEM_BUTTON_MANAGEMENT', 1, '/system/button/page/list', 'fa fa-window-maximize', 4, 1, '_self');
INSERT INTO `system_menu` VALUES (6, 1, '2019-02-13 13:15:05', 1, '2019-12-17 09:39:40', 2, 1, 1, '用户角色关联管理', 'SYSTEM_USER_ROLE_MANAGEMENT', 1, '/system/user/role/page/list', 'fa fa-users', 5, 1, '_self');
INSERT INTO `system_menu` VALUES (7, 1, '2019-02-13 13:15:06', 1, '2019-07-20 11:22:18', 2, 1, 1, '角色菜单关联管理', 'SYSTEM_ROLE_MENU_MANAGEMENT', 1, '/system/role/menu/page/list', 'fa fa-compress', 6, 1, '_self');
INSERT INTO `system_menu` VALUES (8, 1, '2019-02-13 13:15:07', 1, '2019-12-17 09:39:23', 2, 1, 1, '角色菜单按钮关联管理', 'SYSTEM_ROLE_MENU_BUTTON_MANAGEMENT', 1, '/system/role/menu/button/page/list', 'fa fa-compress', 7, 1, '_self');
INSERT INTO `system_menu` VALUES (9, 1, '2019-07-24 16:40:06', 1, '2019-07-24 16:40:06', 1, 1, 1, '系统综合', 'SYSTEM_COMPOSITE_MANAGEMENT', 1, '', 'fa fa-slideshare', 8, 1, '_self');
INSERT INTO `system_menu` VALUES (10, 1, '2019-07-24 17:17:08', 1, '2019-07-24 17:17:08', 1, 1, 9, '操作日志管理', 'SYSTEM_LOG_MANAGEMENT', 2, '/system/log/page/list', 'fa fa-file-text', 9, 1, '_self');
INSERT INTO `system_menu` VALUES (11, 1, '2019-07-25 14:27:01', 1, '2019-07-25 14:27:01', 1, 1, 9, '类型管理', 'SYSTEM_TYPE_MANAGEMENT', 2, '/system/type/page/list', 'fa fa-file-text', 10, 1, '_self');
INSERT INTO `system_menu` VALUES (12, 1, '2019-07-25 14:27:44', 1, '2019-07-25 14:27:44', 1, 1, 9, '字典管理', 'SYSTEM_DICT_MANAGEMENT', 2, '/system/dict/page/list', 'fa fa-file-text', 11, 1, '_self');
INSERT INTO `system_menu` VALUES (13, 1, '2019-07-27 19:55:13', 1, '2019-07-27 19:55:13', 1, 1, 9, '前端IP黑名单管理', 'SYSTEM_BLACK_LIST_IP_MANAGEMENT', 2, '/system/black/list/ip/page/list', 'fa fa-file-text', 12, 1, '_self');
INSERT INTO `system_menu` VALUES (14, 1, '2019-07-27 19:56:01', 1, '2019-07-27 19:56:43', 2, 1, 9, '后台IP白名单管理', 'SYSTEM_WHITE_LIST_IP_MANAGEMENT', 2, '/system/white/list/ip/page/list', 'fa fa-file-text', 13, 1, '_self');

-- ----------------------------
-- Table structure for system_role
-- ----------------------------
DROP TABLE IF EXISTS `system_role`;
CREATE TABLE `system_role`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `CREATE_ID` bigint(20) NULL DEFAULT NULL COMMENT '创建人编号',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建日期时间',
  `OPERATER_ID` bigint(20) NULL DEFAULT NULL COMMENT '操作人编号',
  `OPERATER_TIME` datetime(0) NULL DEFAULT NULL COMMENT '操作日期时间',
  `OPERATER_STATUS` int(11) NULL DEFAULT NULL COMMENT '操作状态 -1删除， 1新增, 2修改',
  `ROLE_NAME` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `ROLE_CODE` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色唯一代码',
  `ROLE_LEVEL` int(11) NULL DEFAULT NULL COMMENT '角色层级，用于多个角色情况下优先显示数值最小级别给用户展示。',
  `DESCRIPTION` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `IS_ENABLE` int(11) NULL DEFAULT NULL COMMENT '是否启用-1禁用， 1启用，',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of system_role
-- ----------------------------
INSERT INTO `system_role` VALUES (1, 1, '2019-07-06 14:28:12', 1, '2019-07-20 10:32:50', 2, '超级管理员', 'system_ultra_admin', 0, '系统管理员', 1);

-- ----------------------------
-- Table structure for system_type
-- ----------------------------
DROP TABLE IF EXISTS `system_type`;
CREATE TABLE `system_type`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `CREATE_ID` bigint(20) NULL DEFAULT NULL COMMENT '创建人编号',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建日期时间',
  `OPERATER_ID` bigint(20) NULL DEFAULT NULL COMMENT '操作人编号',
  `OPERATER_TIME` datetime(0) NULL DEFAULT NULL COMMENT '操作日期时间',
  `OPERATER_STATUS` int(11) NULL DEFAULT NULL COMMENT '操作状态 -1删除， 1新增, 2修改',
  `IS_ENABLE` int(11) NULL DEFAULT NULL COMMENT '是否启用1启用,2禁用',
  `TYPE_NAME` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `TYPE_CODE` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型代码',
  `DESCRIPTION` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `AK_UQ_TYPE_CODE`(`TYPE_CODE`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统类型表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for system_user
-- ----------------------------
DROP TABLE IF EXISTS `system_user`;
CREATE TABLE `system_user`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `CREATE_ID` bigint(20) NULL DEFAULT NULL COMMENT '创建人编号',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建日期时间',
  `OPERATER_ID` bigint(20) NULL DEFAULT NULL COMMENT '操作人编号',
  `OPERATER_TIME` datetime(0) NULL DEFAULT NULL COMMENT '操作日期时间',
  `OPERATER_STATUS` int(11) NULL DEFAULT NULL COMMENT '操作状态 -1删除， 1新增, 2修改',
  `IS_ENABLE` int(11) NULL DEFAULT NULL COMMENT '是否启用-1禁用， 1启用，',
  `ACCOUNT_LOCKED` int(11) NULL DEFAULT NULL COMMENT '是否锁住 1=否, -1=是',
  `ACCOUNT_EXPIRED` int(11) NULL DEFAULT NULL COMMENT '帐号是否过期 1=否, -1=是',
  `CREDENTIALS_EXPIRED` int(11) NULL DEFAULT NULL COMMENT '凭证是否过期 1=否, -1=是',
  `LOGIN_NAME` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账户名',
  `PASSWORD` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `NAME` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `COMPANY_CODE` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司唯一代码,UUID32位',
  `COMPANY_ID` int(11) NULL DEFAULT NULL COMMENT '公司编号',
  `TELPHONE` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `EMAIL` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `USER_LEVEL` int(11) NULL DEFAULT NULL COMMENT '用户级别',
  `DESCRIPTION` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统管理员(代理商)描述',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `AK_UQ_LOGIN_NAME`(`LOGIN_NAME`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户管理表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of system_user
-- ----------------------------
INSERT INTO `system_user` VALUES (1, 1, '2019-07-05 16:41:49', 1, '2019-07-05 16:41:51', 1, 0, 1, 1, 1, 'admin', 'ee3b6236f0d2a83560b8d6e31f85bfee', '管理员', NULL, NULL, '13984687666', 'test@qq.com', 0, NULL);

-- ----------------------------
-- Table structure for system_white_list_ip
-- ----------------------------
DROP TABLE IF EXISTS `system_white_list_ip`;
CREATE TABLE `system_white_list_ip`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `CREATE_ID` bigint(20) NULL DEFAULT NULL COMMENT '创建人编号',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建日期时间',
  `OPERATER_ID` bigint(20) NULL DEFAULT NULL COMMENT '操作人编号',
  `OPERATER_TIME` datetime(0) NULL DEFAULT NULL COMMENT '操作日期时间',
  `OPERATER_STATUS` int(11) NULL DEFAULT NULL COMMENT '操作状态 -1删除， 1新增, 2修改',
  `IS_ENABLE` int(11) NULL DEFAULT NULL COMMENT '是否启用1启用,2禁用',
  `IP` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP',
  `DESCRIPTION` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统白名单-后台登录' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_file_info
-- ----------------------------
DROP TABLE IF EXISTS `t_file_info`;
CREATE TABLE `t_file_info`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `CREATE_ID` bigint(20) NULL DEFAULT NULL COMMENT '创建人编号',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建日期时间',
  `OPERATER_ID` bigint(20) NULL DEFAULT NULL COMMENT '操作人编号',
  `OPERATER_TIME` datetime(0) NULL DEFAULT NULL COMMENT '操作日期时间',
  `OPERATER_STATUS` int(11) NULL DEFAULT NULL COMMENT '操作状态 -1删除， 1新增, 2修改',
  `BUSINESS_ID` bigint(20) NULL DEFAULT NULL COMMENT '所属编号,对应哪个菜单下数据编号',
  `CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '唯一代码',
  `FILE_NAME` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件名',
  `FILE_TYPE` int(11) NULL DEFAULT NULL COMMENT '文件类型(1=txt,2=doc等。需要根据实际环境定义)',
  `FILE_PATH` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件路径,用了中间件的话，这个存储的就是对应的key',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文件信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_system_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_system_role_menu`;
CREATE TABLE `tb_system_role_menu`  (
  `ROLE_ID` bigint(20) NULL DEFAULT NULL COMMENT 'SYSTEM_ROLE表中主键ID',
  `MENU_ID` bigint(20) NULL DEFAULT NULL COMMENT 'SYSTEM_MENU表中主键ID'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色菜单关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_system_role_menu
-- ----------------------------
INSERT INTO `tb_system_role_menu` VALUES (1, 0);
INSERT INTO `tb_system_role_menu` VALUES (1, 1);
INSERT INTO `tb_system_role_menu` VALUES (1, 2);
INSERT INTO `tb_system_role_menu` VALUES (1, 3);
INSERT INTO `tb_system_role_menu` VALUES (1, 4);
INSERT INTO `tb_system_role_menu` VALUES (1, 6);
INSERT INTO `tb_system_role_menu` VALUES (1, 7);
INSERT INTO `tb_system_role_menu` VALUES (1, 9);
INSERT INTO `tb_system_role_menu` VALUES (1, 10);
INSERT INTO `tb_system_role_menu` VALUES (1, 11);
INSERT INTO `tb_system_role_menu` VALUES (1, 12);
INSERT INTO `tb_system_role_menu` VALUES (1, 13);
INSERT INTO `tb_system_role_menu` VALUES (1, 14);
INSERT INTO `tb_system_role_menu` VALUES (1, 5);
INSERT INTO `tb_system_role_menu` VALUES (1, 8);

-- ----------------------------
-- Table structure for tb_system_role_menu_button
-- ----------------------------
DROP TABLE IF EXISTS `tb_system_role_menu_button`;
CREATE TABLE `tb_system_role_menu_button`  (
  `ROLE_ID` bigint(20) NULL DEFAULT NULL COMMENT 'SYSTEM_ROLE表中的主键ID',
  `MENU_ID` bigint(20) NULL DEFAULT NULL COMMENT 'SYSTEM_MENU表中的主键ID',
  `BUTTON_ID` bigint(20) NULL DEFAULT NULL COMMENT 'SYSTEM_BUTTON表的主键ID'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单按钮中间表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_system_role_menu_button
-- ----------------------------
INSERT INTO `tb_system_role_menu_button` VALUES (1, 8, 4);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 8, 1);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 7, 1);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 7, 4);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 6, 4);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 6, 1);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 6, 17);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 7, 17);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 8, 17);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 6, 19);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 7, 19);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 8, 19);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 26);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 25);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 24);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 23);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 16);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 12);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 11);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 5);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 6);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 7);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 8);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 18);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 22);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 3);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 2);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 14);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 9);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 21);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 20);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 19);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 4);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 17);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 13);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 15);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 10);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 1);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 26);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 25);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 24);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 23);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 16);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 12);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 11);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 5);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 6);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 7);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 8);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 18);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 22);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 3);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 2);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 14);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 9);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 21);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 20);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 19);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 4);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 17);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 13);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 15);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 10);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 1);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 12, 26);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 12, 25);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 12, 24);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 12, 23);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 12, 16);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 12, 12);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 12, 11);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 12, 5);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 12, 6);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 12, 7);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 12, 8);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 12, 18);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 12, 22);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 12, 3);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 12, 2);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 12, 14);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 12, 9);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 12, 21);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 12, 20);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 12, 19);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 12, 4);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 12, 17);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 12, 13);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 12, 15);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 12, 10);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 12, 1);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 26);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 25);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 24);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 23);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 16);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 12);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 11);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 5);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 6);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 7);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 8);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 18);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 22);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 3);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 2);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 14);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 9);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 21);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 20);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 19);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 4);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 17);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 13);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 15);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 10);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 1);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 4, 26);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 4, 25);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 4, 24);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 4, 23);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 4, 16);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 4, 12);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 4, 11);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 4, 5);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 4, 6);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 4, 7);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 4, 8);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 4, 18);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 4, 22);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 4, 3);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 4, 2);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 4, 14);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 4, 9);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 4, 21);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 4, 20);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 4, 19);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 4, 4);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 4, 17);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 4, 13);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 4, 15);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 4, 10);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 4, 1);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 26);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 25);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 24);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 23);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 16);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 12);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 11);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 5);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 6);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 7);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 8);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 18);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 22);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 3);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 2);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 14);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 9);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 21);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 20);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 19);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 4);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 17);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 13);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 15);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 10);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 1);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 26);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 25);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 24);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 23);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 16);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 12);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 11);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 5);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 6);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 7);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 8);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 18);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 22);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 3);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 2);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 14);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 9);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 21);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 20);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 19);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 4);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 17);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 13);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 15);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 10);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 1);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 3, 1);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 3, 2);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 3, 3);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 3, 4);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 3, 5);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 3, 6);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 3, 7);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 3, 8);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 3, 9);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 3, 10);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 3, 11);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 3, 12);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 3, 13);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 3, 14);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 3, 15);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 3, 16);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 3, 17);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 3, 18);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 3, 19);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 3, 20);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 3, 21);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 3, 22);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 3, 23);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 3, 24);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 3, 25);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 3, 26);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 2, 1);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 2, 2);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 2, 3);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 2, 4);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 2, 5);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 2, 6);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 2, 7);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 2, 8);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 2, 9);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 2, 10);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 2, 11);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 2, 12);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 2, 13);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 2, 14);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 2, 15);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 2, 16);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 2, 17);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 2, 18);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 2, 19);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 2, 20);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 2, 21);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 2, 22);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 2, 23);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 2, 24);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 2, 25);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 2, 26);

-- ----------------------------
-- Table structure for tb_system_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_system_user_role`;
CREATE TABLE `tb_system_user_role`  (
  `USER_ID` bigint(20) NULL DEFAULT NULL COMMENT 'SYSTEM_USER表中主键ID',
  `ROLE_ID` bigint(20) NULL DEFAULT NULL COMMENT 'SYSTEM_ROLE表中主键ID'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_system_user_role
-- ----------------------------
INSERT INTO `tb_system_user_role` VALUES (1, 1);
INSERT INTO `tb_system_user_role` VALUES (4, 2);

SET FOREIGN_KEY_CHECKS = 1;
