package com.zuer.zuerlvdoubanmovie.feginservice;

import com.zuer.zuerlvdoubancommon.entity.ThreadPropertiesInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "zuer-lvdouban-service")
public interface ThreadPropertiesInfoFeignService {
    @RequestMapping(value = "/ThreadPropertiesInfo/queryThreadPropertiesInfoByType",method = RequestMethod.GET)
    ThreadPropertiesInfo queryThreadPropertiesInfoByType(@RequestParam("type")String type);
}
