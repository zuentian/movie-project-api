package com.zuer.zuerlvdoubanauth.controller;

import com.zuer.zuerlvdoubanauth.feginService.ExceptionInfoFeignService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@EnableAutoConfiguration
@RequestMapping(value = "ExceptionInfoController")
@RestController
public class ExceptionInfoController {

    @Autowired
    ExceptionInfoFeignService exceptionInfoFeignService;

    @RequestMapping(value = "/queryExceptionInfoByParam",method = RequestMethod.POST)
    public Map<String,Object> queryExceptionInfoByParam(@RequestParam Map<String, Object> param)throws Exception{
        String time1=param.get("startTime")==null?null:((String)param.get("startTime"));
        String time2=param.get("endTime")==null?null:((String)param.get("endTime"));
        String pageSize=(String)param.get("pageSize");
        String currentPage=(String)param.get("currentPage");
        Date startTime=null;
        if(StringUtils.isNotBlank(time1)){
            startTime=new Date(Long.valueOf(time1));
        }
        Date endTime=null;
        if(StringUtils.isNotBlank(time2)){
            endTime=new Date(Long.valueOf(time2));
        }
        Map<String,Object> resultMap=exceptionInfoFeignService.queryExceptionInfoByParam(startTime,endTime,pageSize,currentPage);
        return resultMap;
    }
}
