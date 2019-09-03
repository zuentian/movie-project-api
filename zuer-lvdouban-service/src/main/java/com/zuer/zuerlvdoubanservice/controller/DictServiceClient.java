package com.zuer.zuerlvdoubanservice.controller;


import com.zuer.zuerlvdoubancommon.entity.Dict;
import com.zuer.zuerlvdoubancommon.utils.RowBoundUtil;
import com.zuer.zuerlvdoubancommon.vo.page.PageResult;
import com.zuer.zuerlvdoubanservice.service.DictService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@EnableFeignClients
@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/Dict")
public class DictServiceClient {


    @Autowired
    DictService dictService;

    @RequestMapping(value = "/queryDict",method = RequestMethod.POST)
    @ResponseBody
    public PageResult<List<Dict>> queryDict(@RequestBody Map<String, Object> map, @RequestParam("pageSize") String pageSize, @RequestParam("pageIndex") String pageIndex) throws Exception{
        System.out.println("--------------------------"+map+"==="+pageSize+","+pageIndex);
        PageResult list=new PageResult();
        Example example=new Example(Dict.class);
        Example.Criteria criteria = example.createCriteria();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            criteria.andEqualTo(entry.getKey(),entry.getValue());
        }
        RowBounds rowBounds = RowBoundUtil.getRowBounds(pageSize, pageIndex);
        List<Dict> gdNoRepairDos = dictService.selectByExampleAndRowBounds(example, rowBounds);
        System.out.println("--------------------------"+gdNoRepairDos);
        list.setData(gdNoRepairDos);
        int count = dictService.selectCountByExample(example);
        System.out.println("--------------------------"+count);
        list.setTotal(count);
        System.out.println(list);
        return list;
    }

}
