package com.zuer.zuerlvdoubanservice.controller;

import com.zuer.zuerlvdoubancommon.entity.ThreadPropertiesInfo;
import com.zuer.zuerlvdoubanservice.service.ThreadPropertiesInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@EnableFeignClients
@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/ThreadPropertiesInfo")
public class ThreadPropertiesInfoServiceClient {
    @Autowired
    private ThreadPropertiesInfoService threadPropertiesInfoService;

    @RequestMapping(value = "/queryThreadPropertiesInfoByType",method = RequestMethod.GET)
    ThreadPropertiesInfo queryThreadPropertiesInfoByType(@RequestParam("type")String type){
        return threadPropertiesInfoService.selectByPrimaryKey(type);
    }
}
