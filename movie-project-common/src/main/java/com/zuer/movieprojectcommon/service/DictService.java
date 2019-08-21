package com.zuer.movieprojectcommon.service;


import com.zuer.movieprojectcommon.entity.DictValue;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Mapper
@EnableFeignClients
@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/Dict")
public interface DictService {

    @RequestMapping(value = "/queryDictByDictType",method = RequestMethod.GET)
    List<DictValue> queryDictByDictType(@RequestParam("dictType") String dictType);
}
