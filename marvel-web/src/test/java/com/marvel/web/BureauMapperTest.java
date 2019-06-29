package com.marvel.web;

import com.alibaba.fastjson.JSON;
import com.marvel.web.mapper.BureauMapper;
import com.marvel.web.po.Bureau;
import org.assertj.core.util.Lists;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Classname BureauMapperTest
 * @Description BureauMapperTest
 * @Date 2019/6/29 下午7:02
 * @Author zhongjie
 */
public class BureauMapperTest extends BaseTestUtils{

    @Resource
    private BureauMapper bureauMapper;

    @Test
    public void testGetBureauByIds() {
        List<Long> ids = Lists.newArrayList();
        ids.add(576704710900187136L);
        Map<Long, Bureau> bureauMap = new HashMap<>();
        List<Bureau> bureauList = bureauMapper.getBureauByIds(ids);
        if (null != bureauList && bureauList.size() > 0) {
            bureauMap = bureauList.stream().collect(Collectors.toMap(Bureau::getId, temp -> temp, (e1, e2) -> e1));
        }
        System.out.println(JSON.toJSONString(bureauMap));

    }
}
