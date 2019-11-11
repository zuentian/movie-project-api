package com.zuer.movieprojectuser.feignConfig;


import com.zuer.movieprojectcommon.entity.Dict;
import com.zuer.movieprojectcommon.entity.DictValue;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient("movie-project-common")
public interface DictFeignClient {

    @RequestMapping(value = "/Dict/queryDictByDictType",method = RequestMethod.GET)
    //此处调用的时候@RequestParam需要加value不然会报错
    List<DictValue> queryDictByDictType(@RequestParam("dictType") String dictType)throws Exception;

    @RequestMapping(value = "/Dict/queryDict",method = RequestMethod.POST)
    List<Dict> queryDict(@RequestBody Map<String, Object> map) throws Exception;


    @RequestMapping(value = "/Dict/queryDictTypeNameByDictType",method = RequestMethod.GET)
    List<Dict> queryDictTypeNameByDictType(@RequestParam("dictType") String dictType) ;

    @RequestMapping(value = "/Dict/addDict",method = RequestMethod.POST)
    int addDict(@RequestBody Dict dict) throws Exception;;

    @RequestMapping(value = "/Dict/queryDictCount",method = RequestMethod.POST)
    int queryDictCount(@RequestBody Map<String,Object> map);

    @RequestMapping(value = "/Dict/queryDictByDictId",method = RequestMethod.GET)
    Dict queryDictByDictId(@RequestParam("dictType") String dictId);

    @RequestMapping(value = "/Dict/editDictByDictId",method = RequestMethod.POST)
    void editDictByDictId(@RequestBody Dict dict);
    @RequestMapping(value = "/Dict/deleteDictByDictId",method = RequestMethod.GET)
    void deleteDictByDictId(@RequestParam("dictType") String dictId);
}
