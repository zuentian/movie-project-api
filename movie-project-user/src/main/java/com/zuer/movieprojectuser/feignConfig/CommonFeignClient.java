package com.zuer.movieprojectuser.feignConfig;


import com.zuer.movieprojectcommon.entity.Dict;
import com.zuer.movieprojectcommon.entity.DictValue;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient("movie-project-common")
public interface CommonFeignClient {

    @RequestMapping(value = "/queryDictByDictType/{dictType}",method = RequestMethod.GET)
    public List<DictValue> queryDictByDictType(@PathVariable("dictType")String dictType);

    @PostMapping(value = "/queryDict")
    public List<Dict> queryDict();
}
