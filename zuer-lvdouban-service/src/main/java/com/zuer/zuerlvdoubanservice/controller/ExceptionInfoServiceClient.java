package com.zuer.zuerlvdoubanservice.controller;

import com.zuer.zuerlvdoubancommon.entity.ExceptionInfo;
import com.zuer.zuerlvdoubancommon.entity.User;
import com.zuer.zuerlvdoubancommon.utils.RowBoundUtil;
import com.zuer.zuerlvdoubanservice.service.ExceptionInfoService;
import net.sf.ehcache.transaction.xa.EhcacheXAException;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EnableFeignClients
@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/ExceptionInfo")
public class ExceptionInfoServiceClient {

    @Autowired
    private ExceptionInfoService exceptionInfoService;

    @RequestMapping(value = "/insertExceptionInfo",method = RequestMethod.POST)
    public void insertExceptionInfo(@RequestBody  ExceptionInfo exceptionInfo){

        exceptionInfoService.insertSelective(exceptionInfo);

    }


    @RequestMapping(value = "/queryExceptionInfoByParam",method = RequestMethod.POST)
    public Map<String, Object> queryExceptionInfoByParam(@RequestParam(value="startTime", required = false) Date startTime,
                                                         @RequestParam(value="endTime", required = false) Date endTime,
                                                         @RequestParam("pageSize") String pageSize,
                                                         @RequestParam("currentPage") String currentPage) throws Exception {

        Example example=new Example(ExceptionInfo.class);
        example.setOrderByClause("CRT_TIME DESC");//实现排序
        Example.Criteria criteria = example.createCriteria();
        if(startTime!=null){
            criteria.andGreaterThanOrEqualTo("crtTime",startTime);
        }
        if(endTime!=null){
            criteria.andLessThanOrEqualTo("crtTime",endTime);
        }
        RowBounds rowBounds = RowBoundUtil.getRowBounds(pageSize, currentPage);
        List<ExceptionInfo> lists = exceptionInfoService.selectByExampleAndRowBounds(example, rowBounds);
        Map<String,Object> resultMap=new HashMap<String, Object>();
        resultMap.put("list",lists);
        int count = exceptionInfoService.selectCountByExample(example);
        resultMap.put("count",count);

        return resultMap;
    }

}
