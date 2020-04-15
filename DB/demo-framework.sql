/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50644
 Source Host           : localhost:3306
 Source Schema         : testdemo

 Target Server Type    : MySQL
 Target Server Version : 50644
 File Encoding         : 65001

 Date: 15/04/2020 15:51:25
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
  `CREATE_ID` bigint(20) NULL DEFAULT 0 COMMENT '创建人编号',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建日期时间',
  `OPERATER_ID` bigint(20) NULL DEFAULT 0 COMMENT '操作人编号',
  `OPERATER_TIME` datetime(0) NULL DEFAULT NULL COMMENT '操作日期时间',
  `OPERATER_STATUS` int(11) NULL DEFAULT 0 COMMENT '操作状态： -1=删除， 1=新增, 2=修改',
  `IS_ENABLE` int(11) NULL DEFAULT 0 COMMENT '是否启用：1=启用,2=禁用',
  `IP` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'IP',
  `DESCRIPTION` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '角色描述',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统黑名单-前端登录' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for system_button
-- ----------------------------
DROP TABLE IF EXISTS `system_button`;
CREATE TABLE `system_button`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `CREATE_ID` bigint(20) NULL DEFAULT 0 COMMENT '创建人编号',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建日期时间',
  `OPERATER_ID` bigint(20) NULL DEFAULT 0 COMMENT '操作人编号',
  `OPERATER_TIME` datetime(0) NULL DEFAULT NULL COMMENT '操作日期时间',
  `OPERATER_STATUS` int(11) NULL DEFAULT 0 COMMENT '操作状态：-1=删除，1=新增, 2=修改',
  `IS_ENABLE` int(11) NULL DEFAULT 0 COMMENT '是否启用：1=启用,2=禁用',
  `BUTTON_NAME` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '按钮名称',
  `BUTTON_CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '按钮唯一代码',
  `DESCRIPTION` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '角色描述',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `AK_UQ_MENU_CODE`(`BUTTON_CODE`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '按钮权限管理表' ROW_FORMAT = Compact;

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
INSERT INTO `system_button` VALUES (27, 1, '2020-03-11 17:25:50', 1, '2020-03-11 17:25:50', 1, 1, '刷新', 'refresh', '刷新');
INSERT INTO `system_button` VALUES (28, 1, '2020-03-11 18:26:50', 1, '2020-03-11 18:26:50', 1, 1, '批量刷新', 'batchRefresh', '批量刷新');
INSERT INTO `system_button` VALUES (29, 1, '2020-03-12 09:27:18', 1, '2020-03-12 09:27:18', 1, 1, '上锁', 'lock', '上锁');
INSERT INTO `system_button` VALUES (30, 1, '2020-03-26 09:37:32', 1, '2020-03-26 09:37:36', 1, 1, '解锁', 'unlock', '解锁');
INSERT INTO `system_button` VALUES (31, 1, '2020-03-26 09:37:36', 1, '2020-03-26 09:37:36', 1, 1, '初始化或登陆', 'init', '初始化或登陆');
INSERT INTO `system_button` VALUES (32, 1, '2020-03-26 09:37:36', 1, '2020-03-26 09:37:36', 1, 1, '金额转移', 'moneyTrans', '金额转移');
INSERT INTO `system_button` VALUES (33, 1, '2020-04-04 18:35:47', 1, '2020-04-04 18:35:44', 1, 1, '重新查询', 'reQuery', '重新查询');
INSERT INTO `system_button` VALUES (34, 1, '2020-04-04 18:35:52', 1, '2020-04-04 18:35:55', 1, 1, '重新回调', 'reCallback', '重新回调');

-- ----------------------------
-- Table structure for system_dict
-- ----------------------------
DROP TABLE IF EXISTS `system_dict`;
CREATE TABLE `system_dict`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `CREATE_ID` bigint(20) NULL DEFAULT 0 COMMENT '创建人编号',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建日期时间',
  `OPERATER_ID` bigint(20) NULL DEFAULT 0 COMMENT '操作人编号',
  `OPERATER_TIME` datetime(0) NULL DEFAULT NULL COMMENT '操作日期时间',
  `OPERATER_STATUS` int(11) NULL DEFAULT 0 COMMENT '操作状态： -1=删除， 1=新增, 2=修改',
  `DICT_KEY` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典键',
  `IS_ENABLE` int(11) NULL DEFAULT 0 COMMENT '是否启用：1=启用,2=禁用',
  `DESCRIPTION` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '描述',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `AK_UQ_DICT_KEY`(`DICT_KEY`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统字典表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for system_log
-- ----------------------------
DROP TABLE IF EXISTS `system_log`;
CREATE TABLE `system_log`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `OPERATER_ID` bigint(20) NULL DEFAULT 0 COMMENT '操作人编号',
  `OPERATER_TIME` datetime(0) NULL DEFAULT NULL COMMENT '操作日期时间',
  `LOGIN_NAME` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '操作账户名',
  `IP` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'IP地址',
  `OPERATER_TITLE` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '操作标题',
  `OPERATER_CONTENT` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '操作内容',
  `COMPANY_CODE` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '公司唯一代码,UUID32位',
  `COMPANY_ID` bigint(20) NULL DEFAULT 0 COMMENT '公司编号',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统管理操作日志表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of system_log
-- ----------------------------
INSERT INTO `system_log` VALUES (1, 1, '2020-04-15 15:46:45', 'admin', '192.168.2.205', 'user_login', 'user_login', NULL, NULL);
INSERT INTO `system_log` VALUES (2, 1, '2020-04-15 15:48:04', 'admin', '192.168.2.205', 'user_logout', 'user_logout', NULL, NULL);
INSERT INTO `system_log` VALUES (3, 1, '2020-04-15 15:48:10', 'admin', '192.168.2.205', 'user_login', 'user_login', NULL, NULL);

-- ----------------------------
-- Table structure for system_menu
-- ----------------------------
DROP TABLE IF EXISTS `system_menu`;
CREATE TABLE `system_menu`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `CREATE_ID` bigint(20) NULL DEFAULT 0 COMMENT '创建人编号',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建日期时间',
  `OPERATER_ID` bigint(20) NULL DEFAULT 0 COMMENT '操作人编号',
  `OPERATER_TIME` datetime(0) NULL DEFAULT NULL COMMENT '操作日期时间',
  `OPERATER_STATUS` int(11) NULL DEFAULT 0 COMMENT '操作状态：-1=删除，1=新增, 2=修改',
  `IS_ENABLE` int(11) NULL DEFAULT 0 COMMENT '是否启用：1=启用,2=禁用',
  `PARENT_ID` bigint(20) NULL DEFAULT 0 COMMENT '本表上级编号',
  `MENU_NAME` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '菜单名称',
  `MENU_CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '菜单唯一代码',
  `MENU_LEVEL` int(11) NULL DEFAULT 0 COMMENT '菜单层级,顶级菜单为0因为只有一个，模块菜单为1有多个(如：系统管理，XXX管理)\r\n            每级菜单根据上级菜单来自增1设置菜单级别',
  `URL_PATH` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '请求URL',
  `ICON` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '图标',
  `INDEX_SORT` int(11) NULL DEFAULT NULL COMMENT '排序',
  `ADMIN_TYPE` int(11) NULL DEFAULT 0 COMMENT '菜单类型，1管理员菜单 2普通菜单',
  `TARGET` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '菜单打开方式，_self默认打开方式，_blank浏览器新窗口打开方式',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `AK_UQ_MENU_CODE`(`MENU_CODE`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of system_menu
-- ----------------------------
INSERT INTO `system_menu` VALUES (1, 1, '2019-02-13 13:05:00', 1, '2019-07-13 22:54:19', 1, 1, 0, '系统管理', 'SYSTEM_MANAGEMENT', 0, '', 'fa fa-gear', 1, 1, '_self');
INSERT INTO `system_menu` VALUES (2, 1, '2019-02-13 13:05:01', 1, '2019-02-13 13:05:01', 1, 1, 1, '用户管理', 'SYSTEM_USER_MANAGEMENT', 1, '', 'fa fa-user', 1, 1, '_self');
INSERT INTO `system_menu` VALUES (3, 1, '2019-02-13 13:05:01', 1, '2019-02-13 13:05:01', 1, 1, 1, '权限管理', 'SYSTEM_PERMISSION_MANAGEMENT', 1, '', 'fa fa-bars', 2, 1, '_self');
INSERT INTO `system_menu` VALUES (4, 1, '2019-02-13 13:05:01', 1, '2019-02-13 13:05:01', 1, 1, 1, '权限关联管理', 'SYSTEM_PERMISSION_ASSOCIATION_MANAGEMENT', 1, '', 'fa fa-bars', 3, 1, '_self');
INSERT INTO `system_menu` VALUES (5, 1, '2019-02-13 13:05:01', 1, '2019-02-13 13:05:01', 1, 1, 2, '用户列表', 'SYSTEM_USER_LIST_MANAGEMENT', 1, 'system/user/page/list', 'fa fa-user', 1, 1, '_self');
INSERT INTO `system_menu` VALUES (6, 1, '2019-02-13 13:05:02', 1, '2019-02-13 13:05:02', 1, 1, 3, '角色列表', 'SYSTEM_ROLE_LIST_MANAGEMENT', 2, 'system/role/page/list', 'fa fa-window-maximize', 1, 1, '_self');
INSERT INTO `system_menu` VALUES (7, 1, '2019-02-13 13:05:03', 1, '2019-12-17 09:38:57', 1, 1, 3, '菜单列表', 'SYSTEM_MENU_LIST_MANAGEMENT', 2, 'system/menu/page/list', 'fa fa-bars', 2, 1, '_self');
INSERT INTO `system_menu` VALUES (8, 1, '2019-02-13 13:15:04', 1, '2019-02-13 13:15:04', 1, 1, 3, '按钮列表', 'SYSTEM_BUTTON_LIST_MANAGEMENT', 2, 'system/button/page/list', 'fa fa-window-maximize', 3, 1, '_self');
INSERT INTO `system_menu` VALUES (9, 1, '2019-02-13 13:15:05', 1, '2019-12-17 09:39:40', 1, 1, 4, '用户角色关联列表', 'SYSTEM_USER_ROLE_LIST_MANAGEMENT', 2, 'system/user/role/page/list', 'fa fa-users', 1, 1, '_self');
INSERT INTO `system_menu` VALUES (10, 1, '2019-02-13 13:15:06', 1, '2019-07-20 11:22:18', 1, 1, 4, '角色菜单关联列表', 'SYSTEM_ROLE_MENU_LIST_MANAGEMENT', 2, 'system/role/menu/page/list', 'fa fa-compress', 2, 1, '_self');
INSERT INTO `system_menu` VALUES (11, 1, '2019-02-13 13:15:07', 1, '2019-12-17 09:39:23', 1, 1, 4, '角色菜单按钮关联列表', 'SYSTEM_ROLE_MENU_BUTTON_LIST_MANAGEMENT', 2, 'system/role/menu/button/page/list', 'fa fa-compress', 3, 1, '_self');
INSERT INTO `system_menu` VALUES (12, 1, '2019-07-24 16:40:06', 1, '2019-07-24 16:40:06', 1, 1, 1, '系统综合管理', 'SYSTEM_COMPOSITE_MANAGEMENT', 1, '', 'fa fa-slideshare', 4, 1, '_self');
INSERT INTO `system_menu` VALUES (13, 1, '2019-07-24 17:17:08', 1, '2019-07-24 17:17:08', 1, 1, 12, '操作日志列表', 'SYSTEM_LOG_LIST_MANAGEMENT', 2, 'system/log/page/list', 'fa fa-file-text', 1, 1, '_self');
INSERT INTO `system_menu` VALUES (14, 1, '2019-07-25 14:27:01', 1, '2019-07-25 14:27:01', 1, 1, 12, '类型列表', 'SYSTEM_TYPE_LIST_MANAGEMENT', 2, 'system/type/page/list', 'fa fa-file-text', 2, 1, '_self');
INSERT INTO `system_menu` VALUES (15, 1, '2019-07-25 14:27:44', 1, '2019-07-25 14:27:44', 1, 1, 12, '字典列表', 'SYSTEM_DICT_LIST_MANAGEMENT', 2, 'system/dict/page/list', 'fa fa-file-text', 3, 1, '_self');
INSERT INTO `system_menu` VALUES (16, 1, '2019-07-27 19:55:13', 1, '2019-07-27 19:55:13', 1, 1, 12, '接口IP白名单列表', 'SYSTEM_BLACK_LIST_IP_MANAGEMENT', 2, 'system/black/list/ip/page/list', 'fa fa-file-text', 4, 1, '_self');
INSERT INTO `system_menu` VALUES (17, 1, '2019-07-27 19:56:01', 1, '2019-07-27 19:56:43', 1, 1, 12, '后台IP白名单列表', 'SYSTEM_WHITE_LIST_IP_MANAGEMENT', 2, 'system/white/list/ip/page/list', 'fa fa-file-text', 5, 1, '_self');
INSERT INTO `system_menu` VALUES (18, 1, '2020-03-25 10:06:16', 1, '2020-03-25 10:06:46', 1, 1, 1, '数据权限管理', 'DATA_PERMISSION_MANAGEMENT', 1, '', 'fa fa-table', 5, 1, '_self');

-- ----------------------------
-- Table structure for system_role
-- ----------------------------
DROP TABLE IF EXISTS `system_role`;
CREATE TABLE `system_role`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `CREATE_ID` bigint(20) NULL DEFAULT 0 COMMENT '创建人编号',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建日期时间',
  `OPERATER_ID` bigint(20) NULL DEFAULT 0 COMMENT '操作人编号',
  `OPERATER_TIME` datetime(0) NULL DEFAULT NULL COMMENT '操作日期时间',
  `OPERATER_STATUS` int(11) NULL DEFAULT 0 COMMENT '操作状态:-1=删除，1=新增, 2=修改',
  `IS_ENABLE` int(11) NULL DEFAULT 0 COMMENT '是否启用：1=启用,2=禁用',
  `ROLE_NAME` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '角色名称',
  `ROLE_CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '角色唯一代码',
  `ROLE_LEVEL` int(11) NULL DEFAULT NULL COMMENT '角色层级，用于多个角色情况下优先显示数值最小级别给用户展示。',
  `DESCRIPTION` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '角色描述',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `AK_UQ_ROLE_CODE`(`ROLE_CODE`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of system_role
-- ----------------------------
INSERT INTO `system_role` VALUES (1, 1, '2019-07-06 14:28:12', 1, '2019-07-20 10:32:50', 2, 1, '系统超级管理员', 'system_ultra_admin', 0, '系统超级管理员');
INSERT INTO `system_role` VALUES (2, 1, '2020-03-25 16:42:51', 1, '2020-03-25 16:42:51', 1, 1, '系统管理员', 'system_admin', 1, '系统管理员');

-- ----------------------------
-- Table structure for system_type
-- ----------------------------
DROP TABLE IF EXISTS `system_type`;
CREATE TABLE `system_type`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `CREATE_ID` bigint(20) NULL DEFAULT 0 COMMENT '创建人编号',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建日期时间',
  `OPERATER_ID` bigint(20) NULL DEFAULT 0 COMMENT '操作人编号',
  `OPERATER_TIME` datetime(0) NULL DEFAULT NULL COMMENT '操作日期时间',
  `OPERATER_STATUS` int(11) NULL DEFAULT 0 COMMENT '操作状态： -1=删除， 1=新增, 2=修改',
  `IS_ENABLE` int(11) NULL DEFAULT 0 COMMENT '是否启用：1=启用,2=禁用',
  `TYPE_NAME` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '名称',
  `TYPE_CODE` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '类型代码',
  `DESCRIPTION` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '描述',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `AK_UQ_TYPE_CODE`(`TYPE_CODE`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统类型表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for system_user
-- ----------------------------
DROP TABLE IF EXISTS `system_user`;
CREATE TABLE `system_user`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `CREATE_ID` bigint(20) NULL DEFAULT 0 COMMENT '创建人编号',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建日期时间',
  `OPERATER_ID` bigint(20) NULL DEFAULT 0 COMMENT '操作人编号',
  `OPERATER_TIME` datetime(0) NULL DEFAULT NULL COMMENT '操作日期时间',
  `OPERATER_STATUS` int(11) NULL DEFAULT 0 COMMENT '操作状态： -1=删除， 1=新增, 2=修改',
  `IS_ENABLE` int(11) NULL DEFAULT 0 COMMENT '是否启用:1=启用,2=禁用',
  `ACCOUNT_LOCKED` int(11) NULL DEFAULT 0 COMMENT '是否锁住:1=否, 2=是',
  `ACCOUNT_EXPIRED` int(11) NULL DEFAULT 0 COMMENT '帐号是否过期:1=否, 2=是',
  `CREDENTIALS_EXPIRED` int(11) NULL DEFAULT 0 COMMENT '凭证是否过期:1=否, 2=是',
  `LOGIN_NAME` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '账户名',
  `PASSWORD` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '密码',
  `NAME` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '密码',
  `COMPANY_CODE` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '公司唯一代码,UUID32位',
  `COMPANY_ID` int(11) NULL DEFAULT 0 COMMENT '公司编号',
  `TELPHONE` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '电话',
  `EMAIL` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '邮箱',
  `USER_LEVEL` int(11) NULL DEFAULT 0 COMMENT '用户级别，顶级账户0级，根据当前账户创建进行+1递增',
  `DESCRIPTION` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '系统管理员(代理商)描述',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `AK_UQ_LOGIN_NAME`(`LOGIN_NAME`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户管理表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of system_user
-- ----------------------------
INSERT INTO `system_user` VALUES (1, 1, '2019-07-05 16:41:49', 1, '2019-07-05 16:41:51', 1, 0, 1, 1, 1, 'admin', 'ee3b6236f0d2a83560b8d6e31f85bfee', '管理员', NULL, NULL, '13984687666', 'test@qq.com', 0, NULL);
INSERT INTO `system_user` VALUES (2, 1, '2020-03-25 10:21:02', 1, '2020-03-25 10:21:02', 1, 1, 1, 1, 1, 'ceshi001', 'ee3b6236f0d2a83560b8d6e31f85bfee', '测试001', NULL, NULL, '13984687888', '123qwe@qq.com', 1, '测试');

-- ----------------------------
-- Table structure for system_white_list_ip
-- ----------------------------
DROP TABLE IF EXISTS `system_white_list_ip`;
CREATE TABLE `system_white_list_ip`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `CREATE_ID` bigint(20) NULL DEFAULT 0 COMMENT '创建人编号',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建日期时间',
  `OPERATER_ID` bigint(20) NULL DEFAULT 0 COMMENT '操作人编号',
  `OPERATER_TIME` datetime(0) NULL DEFAULT NULL COMMENT '操作日期时间',
  `OPERATER_STATUS` int(11) NULL DEFAULT 0 COMMENT '操作状态： -1=删除， 1=新增, 2=修改',
  `IS_ENABLE` int(11) NULL DEFAULT 0 COMMENT '是否启用：1=启用,2=禁用',
  `IP` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'IP',
  `DESCRIPTION` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '角色描述',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统白名单-后台登录' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_file_info
-- ----------------------------
DROP TABLE IF EXISTS `t_file_info`;
CREATE TABLE `t_file_info`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `CREATE_ID` bigint(20) NULL DEFAULT 0 COMMENT '创建人编号',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建日期时间',
  `OPERATER_ID` bigint(20) NULL DEFAULT 0 COMMENT '操作人编号',
  `OPERATER_TIME` datetime(0) NULL DEFAULT NULL COMMENT '操作日期时间',
  `OPERATER_STATUS` int(11) NULL DEFAULT 0 COMMENT '操作状态： -1=删除， 1=新增, 2=修改',
  `BUSINESS_ID` bigint(20) NULL DEFAULT 0 COMMENT '所属编号,对应哪个菜单下数据编号',
  `CODE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '唯一代码',
  `FILE_NAME` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '文件名',
  `FILE_TYPE` int(11) NULL DEFAULT 0 COMMENT '文件类型(1=txt,2=doc等。需要根据实际环境定义)',
  `FILE_PATH` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '文件路径,用了中间件的话，这个存储的就是对应的key',
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
INSERT INTO `tb_system_role_menu` VALUES (1, 1);
INSERT INTO `tb_system_role_menu` VALUES (1, 2);
INSERT INTO `tb_system_role_menu` VALUES (1, 5);
INSERT INTO `tb_system_role_menu` VALUES (1, 3);
INSERT INTO `tb_system_role_menu` VALUES (1, 6);
INSERT INTO `tb_system_role_menu` VALUES (1, 7);
INSERT INTO `tb_system_role_menu` VALUES (1, 8);
INSERT INTO `tb_system_role_menu` VALUES (1, 4);
INSERT INTO `tb_system_role_menu` VALUES (1, 9);
INSERT INTO `tb_system_role_menu` VALUES (1, 10);
INSERT INTO `tb_system_role_menu` VALUES (1, 11);
INSERT INTO `tb_system_role_menu` VALUES (1, 12);
INSERT INTO `tb_system_role_menu` VALUES (1, 13);
INSERT INTO `tb_system_role_menu` VALUES (1, 14);
INSERT INTO `tb_system_role_menu` VALUES (1, 15);
INSERT INTO `tb_system_role_menu` VALUES (1, 16);
INSERT INTO `tb_system_role_menu` VALUES (1, 17);
INSERT INTO `tb_system_role_menu` VALUES (1, 18);

-- ----------------------------
-- Table structure for tb_system_role_menu_button
-- ----------------------------
DROP TABLE IF EXISTS `tb_system_role_menu_button`;
CREATE TABLE `tb_system_role_menu_button`  (
  `ROLE_ID` bigint(20) NULL DEFAULT NULL COMMENT 'SYSTEM_ROLE表中的主键ID',
  `MENU_ID` bigint(20) NULL DEFAULT NULL COMMENT 'SYSTEM_MENU表中的主键ID',
  `BUTTON_ID` bigint(20) NULL DEFAULT NULL COMMENT 'SYSTEM_BUTTON表的主键ID'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色菜单按钮中间表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_system_role_menu_button
-- ----------------------------
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 1);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 2);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 3);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 4);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 5);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 6);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 7);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 8);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 9);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 10);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 11);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 12);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 13);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 14);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 15);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 16);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 17);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 18);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 19);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 20);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 21);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 22);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 23);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 24);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 25);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 26);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 27);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 28);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 29);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 30);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 31);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 32);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 33);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 5, 34);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 6, 1);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 6, 2);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 6, 3);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 6, 4);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 6, 5);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 6, 6);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 6, 7);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 6, 8);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 6, 9);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 6, 10);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 6, 11);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 6, 12);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 6, 13);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 6, 14);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 6, 15);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 6, 16);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 6, 17);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 6, 18);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 6, 19);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 6, 20);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 6, 21);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 6, 22);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 6, 23);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 6, 24);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 6, 25);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 6, 26);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 6, 27);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 6, 28);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 6, 29);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 6, 30);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 6, 31);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 6, 32);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 6, 33);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 6, 34);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 7, 1);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 7, 2);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 7, 3);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 7, 4);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 7, 5);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 7, 6);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 7, 7);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 7, 8);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 7, 9);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 7, 10);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 7, 11);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 7, 12);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 7, 13);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 7, 14);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 7, 15);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 7, 16);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 7, 17);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 7, 18);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 7, 19);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 7, 20);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 7, 21);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 7, 22);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 7, 23);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 7, 24);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 7, 25);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 7, 26);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 7, 27);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 7, 28);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 7, 29);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 7, 30);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 7, 31);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 7, 32);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 7, 33);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 7, 34);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 8, 1);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 8, 2);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 8, 3);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 8, 4);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 8, 5);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 8, 6);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 8, 7);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 8, 8);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 8, 9);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 8, 10);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 8, 11);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 8, 12);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 8, 13);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 8, 14);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 8, 15);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 8, 16);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 8, 17);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 8, 18);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 8, 19);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 8, 20);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 8, 21);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 8, 22);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 8, 23);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 8, 24);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 8, 25);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 8, 26);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 8, 27);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 8, 28);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 8, 29);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 8, 30);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 8, 31);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 8, 32);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 8, 33);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 8, 34);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 9, 1);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 9, 2);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 9, 3);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 9, 4);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 9, 5);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 9, 6);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 9, 7);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 9, 8);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 9, 9);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 9, 10);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 9, 11);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 9, 12);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 9, 13);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 9, 14);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 9, 15);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 9, 16);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 9, 17);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 9, 18);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 9, 19);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 9, 20);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 9, 21);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 9, 22);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 9, 23);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 9, 24);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 9, 25);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 9, 26);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 9, 27);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 9, 28);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 9, 29);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 9, 30);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 9, 31);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 9, 32);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 9, 33);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 9, 34);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 1);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 2);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 3);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 4);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 5);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 6);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 7);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 8);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 9);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 10);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 11);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 12);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 13);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 14);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 15);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 16);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 17);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 18);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 19);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 20);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 21);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 22);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 23);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 24);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 25);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 26);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 27);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 28);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 29);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 30);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 31);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 32);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 33);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 10, 34);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 1);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 2);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 3);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 4);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 5);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 6);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 7);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 8);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 9);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 10);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 11);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 12);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 13);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 14);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 15);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 16);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 17);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 18);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 19);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 20);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 21);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 22);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 23);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 24);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 25);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 26);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 27);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 28);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 29);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 30);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 31);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 32);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 33);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 11, 34);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 1);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 2);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 3);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 4);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 5);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 6);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 7);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 8);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 9);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 10);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 11);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 12);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 13);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 14);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 15);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 16);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 17);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 18);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 19);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 20);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 21);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 22);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 23);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 24);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 25);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 26);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 27);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 28);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 29);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 30);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 31);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 32);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 33);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 13, 34);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 1);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 2);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 3);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 4);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 5);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 6);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 7);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 8);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 9);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 10);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 11);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 12);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 13);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 14);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 15);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 16);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 17);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 18);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 19);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 20);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 21);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 22);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 23);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 24);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 25);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 26);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 27);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 28);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 29);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 30);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 31);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 32);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 33);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 14, 34);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 15, 1);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 15, 2);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 15, 3);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 15, 4);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 15, 5);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 15, 6);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 15, 7);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 15, 8);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 15, 9);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 15, 10);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 15, 11);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 15, 12);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 15, 13);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 15, 14);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 15, 15);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 15, 16);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 15, 17);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 15, 18);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 15, 19);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 15, 20);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 15, 21);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 15, 22);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 15, 23);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 15, 24);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 15, 25);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 15, 26);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 15, 27);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 15, 28);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 15, 29);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 15, 30);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 15, 31);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 15, 32);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 15, 33);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 15, 34);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 16, 1);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 16, 2);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 16, 3);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 16, 4);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 16, 5);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 16, 6);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 16, 7);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 16, 8);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 16, 9);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 16, 10);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 16, 11);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 16, 12);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 16, 13);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 16, 14);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 16, 15);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 16, 16);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 16, 17);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 16, 18);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 16, 19);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 16, 20);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 16, 21);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 16, 22);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 16, 23);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 16, 24);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 16, 25);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 16, 26);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 16, 27);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 16, 28);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 16, 29);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 16, 30);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 16, 31);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 16, 32);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 16, 33);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 16, 34);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 17, 1);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 17, 2);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 17, 3);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 17, 4);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 17, 5);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 17, 6);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 17, 7);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 17, 8);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 17, 9);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 17, 10);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 17, 11);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 17, 12);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 17, 13);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 17, 14);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 17, 15);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 17, 16);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 17, 17);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 17, 18);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 17, 19);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 17, 20);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 17, 21);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 17, 22);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 17, 23);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 17, 24);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 17, 25);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 17, 26);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 17, 27);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 17, 28);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 17, 29);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 17, 30);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 17, 31);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 17, 32);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 17, 33);
INSERT INTO `tb_system_role_menu_button` VALUES (1, 17, 34);

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
INSERT INTO `tb_system_user_role` VALUES (2, 2);

SET FOREIGN_KEY_CHECKS = 1;
