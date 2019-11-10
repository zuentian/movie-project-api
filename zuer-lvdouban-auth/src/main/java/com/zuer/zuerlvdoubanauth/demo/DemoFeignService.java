package com.zuer.zuerlvdoubanauth.demo;

import com.zuer.zuerlvdoubancommon.demo.Demo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Service
@FeignClient("zuer-lvdouban-service")
public interface DemoFeignService {
    @RequestMapping(value = "/Demo/query",method = RequestMethod.GET)
    List<Demo> query();

}
