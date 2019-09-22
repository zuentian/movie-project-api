package com.zuer.zuerlvdoubanauth.FeginService;

import com.zuer.zuerlvdoubancommon.entity.ExceptionInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("zuer-lvdouban-service")
public interface ExceptionInfoFeignService {
    @RequestMapping(value = "/ExceptionInfo/insertExceptionInfo",method = RequestMethod.POST)
    void insertExceptionInfo(ExceptionInfo exceptionInfo);
}
