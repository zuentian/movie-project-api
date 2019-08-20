package com.zuer.movieprojectuser.feignConfig;


import com.zuer.movieprojectcommon.entity.Dict;
import com.zuer.movieprojectcommon.entity.DictValue;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@FeignClient("movie-project-common")
public interface CommonFeignClient {

    @RequestMapping(value = "/queryDictByDictType/{dictType}",method = RequestMethod.GET)
    public List<DictValue> queryDictByDictType(@PathVariable("dictType")String dictType);

    @RequestMapping(value = "/queryDict",method = RequestMethod.POST)
    public List<Dict> queryDict();
}
