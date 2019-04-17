# marvel-photography
漫威-摄影，基于Spring boot框架，整合多种插件，适合于快速开发的参考Demo

#module说明
#marvel-common
1.定义异常类
2.通用的工具类
3.redis配置，如支持对象存储

#marvel-framework
1.处理了返回结果的统一封装、异常处理；
2.接口权限验证 @MarvelCheck
3.拦截器，如日志打印(需满足com.marvel.*.controller.*Controller.*(..))
4.配置类 @Configuration，跨域支持等；

#marvel-web
1.controller: api层；
    vo：POJO类
2.service：业务处理层；
3.mapper：处理接口方法和sql映射，相当于dao + mapper.xml；
4.enums：业务枚举类
5.po：POJO类
6.exception：业务异常类 + 业务异常常量定义

# mysql
测试库 + 测试表
create database mytest;
use mytest;
CREATE TABLE t_user(
  id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT comment '编号，自增ID',
  name VARCHAR(255) NOT NULL default '' comment '用户昵称',
  age int NOT NULL default '0' comment '年龄',
  address VARCHAR(255) NOT NULL default '' comment '住址'
) ENGINE=INNODB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;

#工程打包
mvn clean install -Dmaven.test.skip=true

#执行jar包
java -jar jar包

#docker运行步骤
1.在项目目录下，构建镜像
docker build -t scarecrows:v1 .
2.运行docker容器
docker run --name=scarecrows-web -d -P scarecrows:v1
或者
docker run --name=scarecrows-web -d -p 80:80 scarecrows:v1
两者区别在于，指定宿主机的映射端口，如果是用第一种方式启动，可以通过 docker ps 找到映射的宿主机的端口

#命令执行
可以将docker目录下的entrypoint.sh简单调整下，作为启动脚本