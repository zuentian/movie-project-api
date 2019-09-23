package com.zuer.zuerlvdoubanauth.FeginService;

import com.zuer.zuerlvdoubancommon.entity.ExceptionInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Map;

@FeignClient("zuer-lvdouban-service")
public interface ExceptionInfoFeignService {
    @RequestMapping(value = "/ExceptionInfo/insertExceptionInfo",method = RequestMethod.POST)
    void insertExceptionInfo(ExceptionInfo exceptionInfo);

    @RequestMapping(value = "/ExceptionInfo/queryExceptionInfoByParam",method = RequestMethod.POST)
    Map<String, Object> queryExceptionInfoByParam(@RequestParam(value="startTime", required = false) Date startTime,
                                                  @RequestParam(value="endTime", required = false) Date endTime,
                                                  @RequestParam("pageSize") String pageSize,
                                                  @RequestParam("currentPage") String currentPage);


}
