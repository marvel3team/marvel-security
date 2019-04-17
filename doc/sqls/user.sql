create database scarecrows;
use scarecrows;
CREATE TABLE `t_user` (
  `uid` bigint(20) NOT NULL COMMENT '用户id',
  `username` varchar(30) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(40) NOT NULL DEFAULT '' COMMENT '密码',
  `type` tinyint(3) NOT NULL DEFAULT '50' COMMENT '用户类型 默认50游客登录，10应急管理部门，20应急专家，30企业客户，40员工',
  `status` tinyint(3) NOT NULL DEFAULT '2' COMMENT '登录状态 1登录 2注销',
  `create_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '注册时间',
  `update_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `INX_USERNAME` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;