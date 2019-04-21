package com.marvel.common.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Classname PageBean
 * @Description 翻页对象
 * @Date 2019/4/21 下午11:13
 * @Author zhongjie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean<T> implements Serializable {

    /**
     * 当前页码
     */
    private Integer page;
    /**
     * 每页数据量
     */
    private Integer count;
    /**
     * 总记录数
     */
    private Integer total;
    /**
     * 当前游标值
     */
    private Long cursor;
    /**
     * 下一页游标值
     */
    private Long nextCursor;
    /**
     * 结果集
     */
    private List<T> list;

}
