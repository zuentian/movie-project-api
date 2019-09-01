package com.zuer.zuerlvdoubanauth.FeginService;

import com.zuer.zuerlvdoubancommon.entity.Menu;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "zuer-lvdouban-service")
public interface MenuFeginService {

    @RequestMapping(value = "/Menu/getUserAuthorityMenuByUserId",method = RequestMethod.GET)
    List<Menu> getUserAuthorityMenuByUserId(@RequestParam("id") String id) ;
    @RequestMapping(value = "/Menu/queryMenu",method = RequestMethod.GET)
    List<Menu> queryMenu();
}
