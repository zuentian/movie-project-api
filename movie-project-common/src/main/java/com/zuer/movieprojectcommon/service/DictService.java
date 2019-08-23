package com.zuer.movieprojectcommon.service;


import com.zuer.movieprojectcommon.entity.Dict;
import com.zuer.movieprojectcommon.entity.DictValue;
import com.zuer.movieprojectcommon.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Mapper
@EnableFeignClients
@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/Dict")
public interface DictService {

    @RequestMapping(value = "/queryDictByDictType",method = RequestMethod.GET)
    List<DictValue> queryDictByDictType(@RequestParam("dictType") String dictType);

    @RequestMapping(value = "queryDict",method = RequestMethod.POST)
    List<Dict> queryDict(@RequestBody Map<String,Object> map);

    @RequestMapping(value = "/queryDictTypeNameByDictType",method = RequestMethod.GET)
    List<Dict> queryDictTypeNameByDictType(@RequestParam("dictType") String dictType);

    @RequestMapping(value = "/addDict",method = RequestMethod.POST)
    int addDict(@RequestBody Dict dict);

    @RequestMapping(value = "/queryDictCount",method = RequestMethod.POST)
    int queryDictCount(@RequestBody Map<String,Object> map);

    @RequestMapping(value = "/queryDictByDictId",method = RequestMethod.GET)
    Dict queryDictByDictId(@RequestParam("dictType") String dictId);

    @RequestMapping(value = "/editDictByDictId",method = RequestMethod.POST)
    void editDictByDictId(@RequestBody Dict dict);

    @RequestMapping(value = "/deleteDictByDictId",method = RequestMethod.GET)
    void deleteDictByDictId(@RequestParam("dictType") String dictId);
}
