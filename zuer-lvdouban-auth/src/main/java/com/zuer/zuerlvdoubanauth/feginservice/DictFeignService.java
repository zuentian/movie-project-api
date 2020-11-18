package com.zuer.zuerlvdoubanauth.feginservice;


import com.zuer.zuerlvdoubancommon.entity.Dict;
import com.zuer.zuerlvdoubancommon.vo.DictValue;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient("zuer-lvdouban-service")
public interface DictFeignService {

    @RequestMapping(value = "/Dict/queryPageFromDict",method = RequestMethod.POST)
    Map<String,Object> queryPageFromDict(@RequestBody Map<String, Object> map,
                                     @RequestParam("pageSize") String pageSize,
                                     @RequestParam("pageIndex") String pageIndex) throws Exception;


    @RequestMapping(value = "/Dict/getDictTypeName",method = RequestMethod.POST)
    Dict getDictTypeName(@RequestParam("dictType") String dictType);

    @RequestMapping(value = "/Dict/addDict",method = RequestMethod.POST)
    int addDict(@RequestBody Dict dict);


    @RequestMapping(value = "/Dict/queryDictByDictId",method = RequestMethod.GET)
    Dict queryDictByDictId(@RequestParam("dictId") String dictId);

    @RequestMapping(value = "/Dict/editDictByDictId",method = RequestMethod.POST)
    int editDictByDictId(@RequestBody Dict dict);

    @RequestMapping(value = "/Dict/deleteDictByDictId",method = RequestMethod.GET)
    int deleteDictByDictId(@RequestParam("dictId") String dictId);

    @RequestMapping(value = "/Dict/queryDictByDictType",method = RequestMethod.GET)
    //此处调用的时候@RequestParam需要加value不然会报错
    List<DictValue> queryDictByDictType(@RequestParam("dictType") String dictType);
}
