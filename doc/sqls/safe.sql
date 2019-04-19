/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : safe

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 18/04/2019 00:08:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_company_base_info
-- ----------------------------
DROP TABLE IF EXISTS `t_company_base_info`;
CREATE TABLE `t_company_base_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `company_id` int(11) DEFAULT NULL COMMENT '企业id',
  `production_day` int(11) DEFAULT NULL COMMENT '年生产天数\n',
  `total_invest` int(11) DEFAULT NULL COMMENT '总投资金额（元）',
  `fixed_invest` int(11) DEFAULT NULL COMMENT '固定投资（元）',
  `last_year_income` int(11) DEFAULT NULL COMMENT '上一年营业收入（元）',
  `factory_area` int(11) DEFAULT NULL COMMENT '厂区占地面积（平米）',
  `covered_area` int(11) DEFAULT NULL COMMENT '建筑面积（平米）',
  `raw_stock_dosage` int(11) DEFAULT NULL COMMENT '原材料年用量',
  `accessories_dosage` int(11) DEFAULT NULL COMMENT '辅料年用量',
  `yearly_capacity` int(11) DEFAULT NULL COMMENT '年产量',
  `output_unit` varchar(50) DEFAULT NULL COMMENT '产量单位',
  `has_special_equipment` bit(1) DEFAULT NULL COMMENT '有无特种设备',
  `has_distribution_room` bit(1) DEFAULT NULL COMMENT '有无配电室',
  `has_transformer` bit(1) DEFAULT NULL COMMENT '有无变压器',
  `has_stdn_certificate` bit(1) DEFAULT NULL COMMENT '是否取得标准化证书',
  `stdn_certificate_type` varchar(255) DEFAULT NULL COMMENT '标准化证书类型',
  `stdn_certificate_id` int(11) DEFAULT NULL COMMENT '标准化证书编号',
  `has_declare_online` varchar(255) DEFAULT NULL COMMENT '是否网上申报',
  `has_safe_proof` varchar(255) DEFAULT NULL COMMENT '是否有安全部员证',
  `safe_proof_archive_no` int(11) DEFAULT NULL COMMENT '安全部员证存档编号',
  `work_system` smallint(6) DEFAULT NULL COMMENT '工作制度（1 一班倒 2 两班倒 3 三班倒）',
  `production_department_peploes` int(11) DEFAULT NULL COMMENT '生产车间人数',
  `office_peploes` int(11) DEFAULT NULL COMMENT '办公室及后勤人数',
  `work_days_yearly` int(11) DEFAULT NULL COMMENT '年工作天数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='企业基础数据信息';

-- ----------------------------
-- Table structure for t_company_device
-- ----------------------------
DROP TABLE IF EXISTS `t_company_device`;
CREATE TABLE `t_company_device` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company_id` int(11) NOT NULL COMMENT '公司编号',
  `name` varchar(50) NOT NULL COMMENT '设备名称',
  `no` varchar(100) NOT NULL COMMENT '设备编号',
  `number` int(11) NOT NULL COMMENT '设备数量',
  `norm_type` varchar(50) NOT NULL COMMENT '规格型号',
  `is_special_device` bit(1) NOT NULL COMMENT '是否特殊设备',
  `is_main_device` bit(1) NOT NULL COMMENT '是否主要设备',
  `is_enviroment_device` bit(1) NOT NULL COMMENT '是否环保设备',
  `is_occupational_health_device` bit(1) NOT NULL COMMENT '是否职业健康设备',
  `is_mobile_device` bit(1) NOT NULL COMMENT '是否移动设备',
  `is_safe_check` bit(1) NOT NULL COMMENT '是否安全检验',
  `safe_check_cycle` int(11) DEFAULT NULL COMMENT '安全检验周期（甜）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='公司设备表';

-- ----------------------------
-- Table structure for t_company_manager
-- ----------------------------
DROP TABLE IF EXISTS `t_company_manager`;
CREATE TABLE `t_company_manager` (
  `id` int(11) NOT NULL COMMENT '主键id',
  `company_id` int(11) DEFAULT NULL COMMENT '所属公司编号',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号',
  `id_card_no` int(11) DEFAULT NULL COMMENT '身份证号',
  `work_role` int(11) DEFAULT NULL COMMENT '职务类型\n1 法人\n2 总经理\n3 安全负责人\n4 安全管理\n5 综合办主任\n6 车间主任\n7 其他',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='企业管理人员表';

-- ----------------------------
-- Table structure for t_company_special
-- ----------------------------
DROP TABLE IF EXISTS `t_company_special`;
CREATE TABLE `t_company_special` (
  `id` int(11) NOT NULL COMMENT '自增主键',
  `name` varchar(50) NOT NULL COMMENT '姓名',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  `mobile` varchar(11) NOT NULL COMMENT '手机号',
  `id_card_no` varchar(18) DEFAULT NULL COMMENT '身份证号',
  `company_id` int(11) NOT NULL COMMENT '所属公司id',
  `work_type` smallint(6) DEFAULT NULL COMMENT '工种\n1 电工\n2 金属焊接工\n3 司炉工\n4 压力操作工\n5 机动车驾驶人员\n6 爆破作业工\n7 矿井瓦斯检验工\n8 建筑登高架设工\n9 机动船舶驾驶工\n。。。',
  `certificate_no` varchar(50) DEFAULT NULL COMMENT '证书号码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='企业特种人员信息表';

-- ----------------------------
-- Table structure for t_company_staff
-- ----------------------------
DROP TABLE IF EXISTS `t_company_staff`;
CREATE TABLE `t_company_staff` (
  `id` int(11) NOT NULL COMMENT '员工id',
  `company_id` int(11) NOT NULL COMMENT '所属公司id',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `mobile` varchar(11) NOT NULL COMMENT '手机号',
  `id_card_no` varchar(18) NOT NULL COMMENT '身份证号码',
  `department_id` int(11) NOT NULL COMMENT '部门id',
  `workshop_id` int(11) NOT NULL COMMENT '车间id',
  `team_id` int(11) NOT NULL COMMENT '班组id',
  `profession_id` int(11) NOT NULL COMMENT '工种id',
  `is_special_personnel` bit(1) NOT NULL COMMENT '是否特种作业人员',
  `special_personnel_id` int(11) DEFAULT NULL COMMENT '特种作业人员id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='公司员工信息表';

-- ----------------------------
-- Table structure for t_company_standard
-- ----------------------------
DROP TABLE IF EXISTS `t_company_standard`;
CREATE TABLE `t_company_standard` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `company_name` varchar(255) NOT NULL COMMENT '公司名称',
  `area_id` int(11) DEFAULT NULL COMMENT '地区id',
  `registed_capital` int(11) NOT NULL COMMENT '注册资本（元）',
  `legal_person` varchar(255) NOT NULL COMMENT '法人名称',
  `legal_person_mobile` varchar(20) NOT NULL COMMENT '法人联系方式',
  `bussiness_type_code` int(11) DEFAULT NULL COMMENT '经营类型代码',
  `email` varchar(50) DEFAULT NULL COMMENT '电子邮箱',
  `business_license_id` int(11) DEFAULT NULL COMMENT '营业执照id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='企业基本信息表';

-- ----------------------------
-- Table structure for t_expert_info
-- ----------------------------
DROP TABLE IF EXISTS `t_expert_info`;
CREATE TABLE `t_expert_info` (
  `id` int(11) NOT NULL COMMENT '专家编号',
  `name` varchar(50) NOT NULL COMMENT '专家姓名',
  `id_card_no` varchar(18) NOT NULL COMMENT '身份证号',
  `work_company` varchar(50) NOT NULL COMMENT '工作单位',
  `work_address` varchar(100) NOT NULL COMMENT '工作地址',
  `work_life` int(11) NOT NULL COMMENT '工作年限',
  `positional_title` varchar(50) DEFAULT NULL COMMENT '职称',
  `is_syndic` bit(1) NOT NULL COMMENT '是否评审员',
  `level` int(11) DEFAULT NULL COMMENT '等级',
  `evaluate_range` varchar(255) DEFAULT NULL COMMENT '评审范围',
  `collage` varchar(100) DEFAULT NULL COMMENT '专业',
  `home_address` varchar(100) DEFAULT NULL COMMENT '居住地址',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `photo_id` int(11) DEFAULT NULL COMMENT '本人签名图片地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='专家信息表';

-- ----------------------------
-- Table structure for t_user_login
-- ----------------------------
DROP TABLE IF EXISTS `t_user_login`;
CREATE TABLE `t_user_login` (
  `id` int(11) NOT NULL COMMENT '登陆人id',
  `account` varchar(20) NOT NULL COMMENT '账号',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `type` smallint(6) NOT NULL COMMENT '类型\n1 专家\n2 员工\n3 科局\n',
  `mobile` varchar(11) NOT NULL COMMENT '手机号',
  `login_time` datetime DEFAULT NULL COMMENT '登陆时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='登陆账号表';

SET FOREIGN_KEY_CHECKS = 1;
