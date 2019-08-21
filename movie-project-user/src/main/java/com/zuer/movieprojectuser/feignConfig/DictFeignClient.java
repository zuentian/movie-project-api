package com.zuer.movieprojectuser.feignConfig;


import com.zuer.movieprojectcommon.entity.DictValue;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("movie-project-common")
public interface DictFeignClient {

    @RequestMapping(value = "/Dict/queryDictByDictType",method = RequestMethod.GET)
    //此处调用的时候@RequestParam需要加value不然会报错
    List<DictValue> queryDictByDictType(@RequestParam("dictType") String dictType);

    //@PostMapping(value = "/queryDict")
    //public List<Dict> queryDict();
}
