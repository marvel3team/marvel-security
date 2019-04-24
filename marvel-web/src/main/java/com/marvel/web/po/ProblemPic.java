package com.marvel.web.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * @Classname ProblemPic
 * @Description t_problem_pic
 * 问题图片信息
 * @Date 2019/4/23 下午11:22
 * @Author zhongjie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProblemPic implements Serializable {

    /**
     * 问题ID
     */
    private Long problemId;
    /**
     * 图片地址
     */
    private String url;

}
