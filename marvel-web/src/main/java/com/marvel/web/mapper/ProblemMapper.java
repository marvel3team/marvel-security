package com.marvel.web.mapper;

import com.marvel.web.po.Problem;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Classname ProblemMapper
 * @Description 问题信息mapper操作类
 * @Date 2019/4/23 下午11:15
 * @Author zhongjie
 */
@Mapper
public interface ProblemMapper {

    int save(Problem problem);
}
