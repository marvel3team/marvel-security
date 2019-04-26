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

 Date: 19/04/2019 00:44:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_big_recreation_facility_info
-- ----------------------------
DROP TABLE IF EXISTS `t_big_recreation_facility_info`;
CREATE TABLE `t_big_recreation_facility_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `company_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '企业id',
  `admin_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '管理人员id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='大型娱乐设施信息表';

-- ----------------------------
-- Table structure for t_bolier_info
-- ----------------------------
DROP TABLE IF EXISTS `t_bolier_info`;
CREATE TABLE `t_bolier_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `company_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '企业id',
  `device_no` varchar(256) NOT NULL DEFAULT '' COMMENT '设备编号或厂家编号',
  `volume` int(11) NOT NULL DEFAULT '0' COMMENT '容积',
  `pressure` int(11) NOT NULL DEFAULT '0' COMMENT '压力',
  `filing_no` varchar(256) NOT NULL DEFAULT '' COMMENT '备案证存档编号',
  `admin_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '管理员id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='锅炉信息表';

-- ----------------------------
-- Table structure for t_bureau_info
-- ----------------------------
DROP TABLE IF EXISTS `t_bureau_info`;
CREATE TABLE `t_bureau_info` (
  `id` bigint(20) NOT NULL COMMENT '科局人员id',
  `ares_id` int(11) NOT NULL DEFAULT '0' COMMENT '地区id',
  `company_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '所属公司id',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '姓名',
  `mobile` varchar(11) NOT NULL DEFAULT '' COMMENT '手机号',
  `remark` varchar(512) NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='科局人员信息表';

-- ----------------------------
-- Table structure for t_company_appraise_info
-- ----------------------------
DROP TABLE IF EXISTS `t_company_appraise_info`;
CREATE TABLE `t_company_appraise_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `company_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '公司id',
  `appraise_method` tinyint(3) NOT NULL COMMENT '鉴定方式 1 年检 2 三年检 3 半年检 4 月检',
  `last_appraise_time` bigint(20) NOT NULL DEFAULT '-1' COMMENT '上次鉴定日期',
  `last_plan_execute_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '上次计划执行id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='企业鉴定信息表';

-- ----------------------------
-- Table structure for t_company_base_info
-- ----------------------------
DROP TABLE IF EXISTS `t_company_base_info`;
CREATE TABLE `t_company_base_info` (
  `id` bigint(20) NOT NULL COMMENT '企业id',
  `production_day` int(11) NOT NULL DEFAULT '0' COMMENT '年生产天数',
  `total_invest` bigint(20) NOT NULL DEFAULT '-1' COMMENT '总投资金额（分）',
  `fixed_invest` bigint(20) NOT NULL DEFAULT '-1' COMMENT '固定投资（分）',
  `last_year_income` bigint(20) NOT NULL DEFAULT '-1' COMMENT '上一年营业收入（分）',
  `factory_area` int(11) NOT NULL DEFAULT '0' COMMENT '厂区占地面积（平米 * 100）',
  `covered_area` int(11) NOT NULL DEFAULT '0' COMMENT '建筑面积（平米 * 100）',
  `raw_stock_dosage` int(11) NOT NULL DEFAULT '0' COMMENT '原材料年用量',
  `accessories_dosage` int(11) NOT NULL DEFAULT '0' COMMENT '辅料年用量',
  `yearly_capacity` int(11) NOT NULL DEFAULT '0' COMMENT '年产量',
  `output_unit` tinyint(3) NOT NULL DEFAULT '1' COMMENT '产量单位 1吨 ....',
  `is_special_equipment` tinyint(1) NOT NULL DEFAULT '1' COMMENT '有无特种设备 1有',
  `is_distribution_room` tinyint(1) NOT NULL DEFAULT '1' COMMENT '有无配电室 1有',
  `is_transformer` tinyint(1) NOT NULL DEFAULT '1' COMMENT '有无变压器 1有',
  `is_stan_certificate` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否取得标准化证书 1是',
  `stan_certificate_type` varchar(255) NOT NULL DEFAULT '' COMMENT '标准化证书类型（是否采用枚举更合适？）',
  `stan_certificate_id` varchar(255) NOT NULL DEFAULT '' COMMENT '标准化证书编号',
  `is_declare_online` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否网上申报 1是',
  `is_safe_proof` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否有安全部员证 1是',
  `safe_proof_archive_no` varchar(255) NOT NULL DEFAULT '' COMMENT '安全部员证存档编号',
  `work_system` tinyint(3) NOT NULL DEFAULT '1' COMMENT '工作制度（1 一班倒 2 两班倒 3 三班倒）',
  `production_department_peoples` int(11) NOT NULL DEFAULT '0' COMMENT '生产车间人数',
  `office_peoples` int(11) NOT NULL DEFAULT '0' COMMENT '办公室及后勤人数',
  `work_days_yearly` int(11) NOT NULL DEFAULT '0' COMMENT '年工作天数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='公司基础数据表';

-- ----------------------------
-- Table structure for t_company_device
-- ----------------------------
DROP TABLE IF EXISTS `t_company_device`;
CREATE TABLE `t_company_device` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `company_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '公司ID',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '设备名称',
  `device_no` varchar(255) NOT NULL DEFAULT '' COMMENT '设备编号',
  `number` int(11) NOT NULL DEFAULT '0' COMMENT '设备数量',
  `norm_type` varchar(255) NOT NULL DEFAULT '' COMMENT '规格型号(是否是确认的几种？用枚举类型？)',
  `is_special_device` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否特殊设备 1是',
  `is_main_device` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否主要设备 1是',
  `is_environment_device` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否环保设备 1是',
  `is_occupational_health_device` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否职业健康设备 1是',
  `is_mobile_device` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否移动设备 1是',
  `is_safe_check` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否安全检验 1是',
  `safe_check_cycle` int(11) NOT NULL DEFAULT '0' COMMENT '安全检验周期（天）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='公司设备表';

-- ----------------------------
-- Table structure for t_company_industry
-- ----------------------------
DROP TABLE IF EXISTS `t_company_industry`;
CREATE TABLE `t_company_industry` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `type` tinyint(3) NOT NULL DEFAULT '1' COMMENT '行业分类type，枚举类型',
  `content` varchar(512) NOT NULL DEFAULT '' COMMENT '行业信息',
  `service_cycle` int(11) NOT NULL DEFAULT '0' COMMENT '服务周期',
  `cost` bigint(20) NOT NULL DEFAULT '0' COMMENT '费用（精确到分）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='企业行业信息';

-- ----------------------------
-- Table structure for t_company_manager
-- ----------------------------
DROP TABLE IF EXISTS `t_company_manager`;
CREATE TABLE `t_company_manager` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `company_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '所属公司编号',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '管理人员姓名，是否有ID关联？？',
  `mobile` varchar(11) NOT NULL DEFAULT '' COMMENT '手机号',
  `id_card_no` varchar(40) NOT NULL DEFAULT '' COMMENT '身份证号',
  `work_role` tinyint(3) NOT NULL DEFAULT '1' COMMENT '职务类型\n1 法人\n2 总经理\n3 安全负责人\n4 安全管理\n5 综合办主任\n6 车间主任\n7 其他',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='企业管理人员表';

-- ----------------------------
-- Table structure for t_company_special
-- ----------------------------
DROP TABLE IF EXISTS `t_company_special`;
CREATE TABLE `t_company_special` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '姓名',
  `age` int(11) NOT NULL DEFAULT '-1' COMMENT '年龄',
  `address` varchar(200) NOT NULL DEFAULT '' COMMENT '地址',
  `mobile` varchar(11) NOT NULL DEFAULT '' COMMENT '手机号',
  `id_card_no` varchar(40) NOT NULL DEFAULT '' COMMENT '身份证号',
  `company_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '所属公司id',
  `work_type` tinyint(3) NOT NULL DEFAULT '1' COMMENT '工种\n1 电工\n2 金属焊接工\n3 司炉工\n4 压力操作工\n5 机动车驾驶人员\n6 爆破作业工\n7 矿井瓦斯检验工\n8 建筑登高架设工\n9 机动船舶驾驶工\n。。。',
  `certificate_no` varchar(256) NOT NULL DEFAULT '' COMMENT '证书号码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='企业特种人员信息表';

-- ----------------------------
-- Table structure for t_company_staff
-- ----------------------------
DROP TABLE IF EXISTS `t_company_staff`;
CREATE TABLE `t_company_staff` (
  `id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '员工id',
  `company_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '所属公司id',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '姓名',
  `mobile` varchar(11) NOT NULL DEFAULT '' COMMENT '手机号',
  `id_card_no` varchar(40) NOT NULL DEFAULT '' COMMENT '身份证号码',
  `department_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '部门id',
  `workshop_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '车间id',
  `team_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '班组id',
  `profession_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '工种id（枚举？可确定的？）',
  `is_special_personnel` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否特种作业人员 1是',
  `special_personnel_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '特种作业人员id',
  `remark` varchar(512) NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='公司员工信息表';

-- ----------------------------
-- Table structure for t_company_standard
-- ----------------------------
DROP TABLE IF EXISTS `t_company_standard`;
CREATE TABLE `t_company_standard` (
  `id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '公司id',
  `name` varchar(255) NOT NULL COMMENT '公司名称',
  `area_id` int(11) NOT NULL DEFAULT '-1' COMMENT '地区id',
  `industry_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '行业id',
  `registed_capital` bigint(20) NOT NULL DEFAULT '0' COMMENT '注册资本（分）',
  `legal_person` varchar(255) NOT NULL DEFAULT '' COMMENT '法人名称',
  `legal_person_mobile` varchar(20) NOT NULL DEFAULT '' COMMENT '法人联系方式',
  `bussiness_type_code` varchar (20) NOT NULL DEFAULT '-1' COMMENT '经营类型代码（类型是字符串？）',
  `email` varchar(100) NOT NULL DEFAULT '' COMMENT '电子邮箱',
  `business_license_id` varchar (100) NOT NULL DEFAULT '-1' COMMENT '营业执照id（营业执照编号是否用字符串？）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='企业基本信息表';

-- ----------------------------
-- Table structure for t_company_user
-- ----------------------------
DROP TABLE IF EXISTS `t_company_user`;
CREATE TABLE `t_company_user` (
  `id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '员工id',
  `department_type` tinyint(3) NOT NULL DEFAULT '1' COMMENT '部门类型（都有哪些...）',
  `task_area_id` int(11) NOT NULL DEFAULT '-1' COMMENT '任务区域id',
  `company_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '公司id',
  `mobile` varchar(11) NOT NULL DEFAULT '-1' COMMENT '手机号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='公司员工表';

-- ----------------------------
-- Table structure for t_elevator_info
-- ----------------------------
DROP TABLE IF EXISTS `t_elevator_info`;
CREATE TABLE `t_elevator_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `company_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '企业id',
  `certificate_no` varchar(200) NOT NULL DEFAULT '' COMMENT '合格证编号',
  `production_date` bigint(20) NOT NULL DEFAULT '-1' COMMENT '出厂日期',
  `factory` varchar(255) NOT NULL DEFAULT '' COMMENT '生产厂家',
  `admin_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '管理员id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='电梯信息记录表';

-- ----------------------------
-- Table structure for t_engineering_plan_info
-- ----------------------------
DROP TABLE IF EXISTS `t_engineering_plan_info`;
CREATE TABLE `t_engineering_plan_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `company_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '企业id',
  `file_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '存档id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='工程平面图存档表（平面图拍照或者扫描存档）';

-- ----------------------------
-- Table structure for t_expert_info
-- ----------------------------
DROP TABLE IF EXISTS `t_expert_info`;
CREATE TABLE `t_expert_info` (
  `id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '专家编号',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '专家姓名',
  `id_card_no` varchar(40) NOT NULL DEFAULT '' COMMENT '身份证号',
  `company_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '专家公司id',
  `work_company` varchar(100) NOT NULL DEFAULT '' COMMENT '工作单位',
  `work_address` varchar(255) NOT NULL DEFAULT '' COMMENT '工作地址',
  `work_life` int(11) NOT NULL DEFAULT '0' COMMENT '工作年限',
  `positional_title` varchar(255) NOT NULL DEFAULT '' COMMENT '职称',
  `is_syndic` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否评审员 1是',
  `level` tinyint(3) NOT NULL DEFAULT '0' COMMENT '等级',
  `evaluate_range` varchar(255) NOT NULL DEFAULT '' COMMENT '评审范围',
  `collage` varchar(100) NOT NULL DEFAULT '' COMMENT '专业',
  `home_address` varchar(255) NOT NULL DEFAULT '' COMMENT '居住地址',
  `mobile` varchar(11) NOT NULL DEFAULT '' COMMENT '手机号',
  `remark` varchar(512) NOT NULL DEFAULT '' COMMENT '备注',
  `sign_url` varchar(512) NOT NULL DEFAULT '' COMMENT '本人签名图片地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='专家信息表';

-- ----------------------------
-- Table structure for t_expert_plan
-- ----------------------------
DROP TABLE IF EXISTS `t_expert_plan`;
CREATE TABLE `t_expert_plan` (
  `plan_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '计划id',
  `expert_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '专家id',
  PRIMARY KEY (`plan_id`,`expert_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='专家计划映射表';

-- ----------------------------
-- Table structure for t_expert_respond_plan
-- ----------------------------
DROP TABLE IF EXISTS `t_expert_respond_plan`;
CREATE TABLE `t_expert_respond_plan` (
  `plan_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '计划id',
  `expert_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '专家id',
  PRIMARY KEY (`plan_id`,`expert_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='专家响应计划映射表';

-- ----------------------------
-- Table structure for t_expert_time
-- ----------------------------
DROP TABLE IF EXISTS `t_expert_time`;
CREATE TABLE `t_expert_time` (
  `time_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '时间id',
  `expert_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '专家id',
  `expert_type` tinyint(3) NOT NULL DEFAULT '1' COMMENT '专家类型 1 空闲专家 2 待定专家',
  PRIMARY KEY (`time_id`,`expert_id`,`expert_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='专家时间映射表';

-- ----------------------------
-- Table structure for t_fire_equipment
-- ----------------------------
DROP TABLE IF EXISTS `t_fire_equipment`;
CREATE TABLE `t_fire_equipment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `company_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '企业id',
  `equipment_type` tinyint(3) NOT NULL DEFAULT '1' COMMENT '器材种类',
  `placement` varchar(255) NOT NULL DEFAULT '' COMMENT '摆放位置',
  `number` int(11) NOT NULL DEFAULT '0' COMMENT '数量',
  `buy_time` bigint(20) NOT NULL DEFAULT '-1' COMMENT '购买时间',
  `due_time` bigint(20) NOT NULL DEFAULT '-1' COMMENT '到期时间',
  `inspection_time` bigint(20) NOT NULL DEFAULT '-1' COMMENT '检查时间',
  `remark` varchar(512) NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消防器材收集表';

-- ----------------------------
-- Table structure for t_hoisting_machine_info
-- ----------------------------
DROP TABLE IF EXISTS `t_hoisting_machine_info`;
CREATE TABLE `t_hoisting_machine_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `company_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '企业id',
  `certificate_file_no` varchar(255) NOT NULL DEFAULT '' COMMENT '合格证存档编号',
  `tonnage` int(11) NOT NULL DEFAULT '0' COMMENT '吨位',
  `factory` varchar(255) NOT NULL DEFAULT '' COMMENT '生产厂家',
  `admin_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '管理员id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='起重设备信息表';

-- ----------------------------
-- Table structure for t_load_bearing
-- ----------------------------
DROP TABLE IF EXISTS `t_load_bearing`;
CREATE TABLE `t_load_bearing` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `company_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '企业id',
  `volume` int(11) NOT NULL DEFAULT '0' COMMENT '容积(单位是啥？)',
  `pressure` int(11) NOT NULL DEFAULT '0' COMMENT '压力(单位是啥？)',
  `admin_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '管理员id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='承重设备信息记录表';

-- ----------------------------
-- Table structure for t_passenger_ropeway_info
-- ----------------------------
DROP TABLE IF EXISTS `t_passenger_ropeway_info`;
CREATE TABLE `t_passenger_ropeway_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `company_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '企业id',
  `length` int(11) NOT NULL DEFAULT '0' COMMENT '长度(单位是啥？)',
  `admin_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '管理人员id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客运索道信息表';

-- ----------------------------
-- Table structure for t_plan
-- ----------------------------
DROP TABLE IF EXISTS `t_plan`;
CREATE TABLE `t_plan` (
  `id` bigint(20) NOT NULL COMMENT '计划ID',
  `company_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '企业id',
  `bureau_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '科局id',
  `plan_time` bigint(20) NOT NULL DEFAULT '-1' COMMENT '计划时间',
  `time_slot` int(11) NOT NULL DEFAULT '0' COMMENT '时间段（天）',
  `staff_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '企业工作人员id',
  `superversion_level` tinyint(3) NOT NULL DEFAULT '0' COMMENT '监管等级',
  `plan_subject` varchar(255) NOT NULL DEFAULT '' COMMENT '计划科目',
  `non_conformance_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '不符合项id',
  `rectification_result_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '整改结果id',
  `finish_time` bigint(20) NOT NULL DEFAULT '-1' COMMENT '完成时间',
  `status` tinyint(3) NOT NULL DEFAULT '10' COMMENT '10：科局发起 20：企业工作人员认领任务 30： 专家确认时间 40.准备执行 50. 执行中 60. 开具不符合项 70. 整改完毕  80：科局取消 90：未确定时间计划取消 100：执行前异常取消 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='计划表';

-- ----------------------------
-- Table structure for t_pressure_piping_info
-- ----------------------------
DROP TABLE IF EXISTS `t_pressure_piping_info`;
CREATE TABLE `t_pressure_piping_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `company_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '企业id',
  `length` int(11) NOT NULL DEFAULT '0' COMMENT '长度(单位是啥？)',
  `pressure` int(11) NOT NULL DEFAULT '0' COMMENT '压力(单位是啥？)',
  `admin_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '管理员id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='压力管道信息记录表';

-- ----------------------------
-- Table structure for t_pressure_vessel_info
-- ----------------------------
DROP TABLE IF EXISTS `t_pressure_vessel_info`;
CREATE TABLE `t_pressure_vessel_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `company_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '公司id',
  `device_no` bigint(20) NOT NULL DEFAULT '-1'  COMMENT '设备编号或者厂家编号',
  `pressure` int(11) NOT NULL DEFAULT '0' COMMENT '压力(单位是啥？)',
  `admin_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '管理员id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='压力容器信息表';

-- ----------------------------
-- Table structure for t_raw_stock
-- ----------------------------
DROP TABLE IF EXISTS `t_raw_stock`;
CREATE TABLE `t_raw_stock` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `company_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '企业id',
  `product_name` varchar(255) NOT NULL DEFAULT '' COMMENT '产品名称',
  `stock_name` varchar(255) NOT NULL DEFAULT '' COMMENT '材料名称',
  `standards` varchar(255) NOT NULL DEFAULT '' COMMENT '规格（是否可用枚举？）',
  `annual_consumption` int(11) NOT NULL DEFAULT '0' COMMENT '年产量（单位转换成整）',
  `storage_mode` varchar(255) NOT NULL DEFAULT '' COMMENT '存储方式（可用枚举？）',
  `is_dangerous_goods` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否危险品 1 是',
  `is_xx_goods` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否xx品 1 是',
  `is_raw` tinyint(1) NOT NULL DEFAULT '1' COMMENT '原料/辅料 1原料',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='原材料记录表';

-- ----------------------------
-- Table structure for t_rescue_drag_info
-- ----------------------------
DROP TABLE IF EXISTS `t_rescue_drag_info`;
CREATE TABLE `t_rescue_drag_info` (
  `id` bigint(20) NOT NULL COMMENT '模板ID',
  `drag_name` varchar(255) NOT NULL DEFAULT '' COMMENT '药品名称',
  `average_person` int(11) NOT NULL DEFAULT '0' COMMENT '平均数量（单位/人）',
  `average_square_metre` int(11) NOT NULL DEFAULT '0' COMMENT '单位/平米',
  `remark` varchar(512) NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='应急救援物资及药品清单模板';

-- ----------------------------
-- Table structure for t_respond_plan
-- ----------------------------
DROP TABLE IF EXISTS `t_respond_plan`;
CREATE TABLE `t_respond_plan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `plan_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '计划id',
  `plan_time` bigint(20) NOT NULL DEFAULT '-1' COMMENT '计划时间',
  `type` tinyint(3) NOT NULL DEFAULT '1' COMMENT '方式：1 远程 2 现场',
  `status` tinyint(3) NOT NULL DEFAULT '1' COMMENT '状态 1 未完成 2 已经完成 3 超时取消',
  `remark` varchar(512) NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='响应计划表';

-- ----------------------------
-- Table structure for t_service_info
-- ----------------------------
DROP TABLE IF EXISTS `t_service_info`;
CREATE TABLE `t_service_info` (
  `id` bigint(20) NOT NULL COMMENT '服务内容ID',
  `service_type` tinyint(3) NOT NULL DEFAULT '1' COMMENT '服务类型\n1 安全隐患排查\n2 安全生产培训\n3 双控培训\n4 职业健康培训\n5 应急救援培训\n6 应急救援\n7 事故调查',
  `service_name` varchar(255) NOT NULL DEFAULT '' COMMENT '对应的服务类型名称',
  `service_cycle` int(11) NOT NULL DEFAULT '0' COMMENT '服务周期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='服务内容表';

-- ----------------------------
-- Table structure for t_technological_process_info
-- ----------------------------
DROP TABLE IF EXISTS `t_technological_process_info`;
CREATE TABLE `t_technological_process_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `company_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '企业id',
  `product_name` varchar(255) NOT NULL DEFAULT '' COMMENT '产品名称',
  `process_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '工艺流程id',
  `photo_url` varchar(512) NOT NULL DEFAULT '' COMMENT '环评拍照图图片地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='工艺流程存档表';

-- ----------------------------
-- Table structure for t_time_config
-- ----------------------------
DROP TABLE IF EXISTS `t_time_config`;
CREATE TABLE `t_time_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `s_date` bigint(20) NOT NULL DEFAULT '-1' COMMENT '日期',
  `ares_id` int(11) NOT NULL DEFAULT '-1' COMMENT '地区id',
  `plan_not_execute` varchar(255) DEFAULT NULL COMMENT '计划中未执行？不知道是啥',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='时间设置表';

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL COMMENT '用户id',
  `mobile` varchar(11) NOT NULL DEFAULT '' COMMENT '手机号',
  `username` varchar(30) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(50) NOT NULL DEFAULT '' COMMENT '密码',
  `type` tinyint(3) NOT NULL DEFAULT '1' COMMENT '类型 1企业 2政府 3专家 4公司业务员',
  `create_time` bigint(20) NOT NULL DEFAULT '-1' COMMENT '创建时间',
  `status` tinyint(3) NOT NULL DEFAULT '1' COMMENT '状态 1 正常 2 无效',
  `update_time` bigint(20) NOT NULL DEFAULT '-1' COMMENT '更新时间',
  `remark` varchar(512) NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Table structure for t_vehicle_info
-- ----------------------------
DROP TABLE IF EXISTS `t_vehicle_info`;
CREATE TABLE `t_vehicle_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `company_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '企业id',
  `type` tinyint(3) NOT NULL DEFAULT '1' COMMENT '类型 1 客车 2货车',
  `factory` varchar(255) NOT NULL DEFAULT '' COMMENT '生产厂家',
  `car_numer` varchar(50) NOT NULL DEFAULT '' COMMENT '车牌号',
  `frame_number` varchar(50) NOT NULL DEFAULT '' COMMENT '车架号',
  `engine_number` varchar(50) NOT NULL DEFAULT '' COMMENT '发动机号',
  `driving_permit_no` varchar(50) NOT NULL DEFAULT '' COMMENT '行驶证编号',
  `admin_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '管理员id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='机动车信息表';


DROP TABLE IF EXISTS `t_problem_project`;
CREATE TABLE `t_problem_project` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `plan_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '计划id',
  `expert_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '专家id',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '项目名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='现场问题项目表';


DROP TABLE IF EXISTS `t_problem_project_rule`;
CREATE TABLE `t_problem_project_rule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `project_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '项目id',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '项目细则名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目细则表';


DROP TABLE IF EXISTS `t_problem_info`;
CREATE TABLE `t_problem_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `plan_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '计划ID',
  `rule_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '项目细则id',
  `expert_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '专家id',
  `project_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '项目id',
  `project_name` varchar(100) NOT NULL DEFAULT '' COMMENT '项目细则名称',
  `problem_content` varchar(255) NOT NULL DEFAULT '-1' COMMENT '问题',
  `corrective_action` varchar(255) NOT NULL DEFAULT '' COMMENT '整改措施',
  `term` int(11) NOT NULL DEFAULT '0' COMMENT '期限（天）',
  `status` tinyint(3) NOT NULL DEFAULT '1' COMMENT '整改状态 1 待整改 2 已整改 3 整改不合格 4 整改完成',
  `update_time` bigint(20) NOT NULL DEFAULT '-1' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='现场问题表';

DROP TABLE IF EXISTS `t_problem_pic`;
CREATE TABLE `t_problem_pic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `problem_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '问题id',
  `url` varchar(512) NOT NULL DEFAULT '-1' COMMENT '图片地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='问题图片表';


SET FOREIGN_KEY_CHECKS = 1;
