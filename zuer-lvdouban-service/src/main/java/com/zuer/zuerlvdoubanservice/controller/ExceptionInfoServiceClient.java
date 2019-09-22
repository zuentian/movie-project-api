package com.zuer.zuerlvdoubanservice.controller;

import com.zuer.zuerlvdoubancommon.entity.ExceptionInfo;
import com.zuer.zuerlvdoubanservice.service.ExceptionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
