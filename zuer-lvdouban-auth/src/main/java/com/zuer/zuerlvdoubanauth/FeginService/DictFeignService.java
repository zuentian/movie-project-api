package com.zuer.zuerlvdoubanauth.FeginService;


import com.zuer.zuerlvdoubancommon.entity.Dict;
import com.zuer.zuerlvdoubancommon.vo.page.PageResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient("zuer-lvdouban-service")
public interface DictFeignService {

    //@RequestMapping(value = "/Dict/queryDictByDictType",method = RequestMethod.GET)
    //此处调用的时候@RequestParam需要加value不然会报错
    //List<DictValue> queryDictByDictType(@RequestParam("dictType") String dictType)throws Exception;

    @RequestMapping(value = "/Dict/queryDict",method = RequestMethod.POST)
    PageResult<List<Dict>> queryDict(@RequestBody Map<String, Object> map,
                                     @RequestParam("pageSize") String pageSize,
                                     @RequestParam("pageIndex") String pageIndex) throws Exception;


    @RequestMapping(value = "/Dict/queryDictCount",method = RequestMethod.POST)
    int queryDictCount(@RequestBody Map<String, Object> map);
/*

    @RequestMapping(value = "/Dict/queryDictTypeNameByDictType",method = RequestMethod.GET)
    List<Dict> queryDictTypeNameByDictType(@RequestParam("dictType") String dictType) ;

    @RequestMapping(value = "/Dict/addDict",method = RequestMethod.POST)
    int addDict(@RequestBody Dict dict) throws Exception;;


    @RequestMapping(value = "/Dict/queryDictByDictId",method = RequestMethod.GET)
    Dict queryDictByDictId(@RequestParam("dictType") String dictId);

    @RequestMapping(value = "/Dict/editDictByDictId",method = RequestMethod.POST)
    void editDictByDictId(@RequestBody Dict dict);
    @RequestMapping(value = "/Dict/deleteDictByDictId",method = RequestMethod.GET)
    void deleteDictByDictId(@RequestParam("dictType") String dictId);*/
}
