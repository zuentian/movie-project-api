package com.zuer.movieprojectuser.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zuer.movieprojectcommon.entity.Dict;
import com.zuer.movieprojectcommon.entity.DictValue;
import com.zuer.movieprojectuser.feignConfig.DictFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EnableAutoConfiguration
@RequestMapping(value = "/DictController")
@RestController
public class DictController {


    @Autowired
    DictFeignClient dictFeignClient;


    @RequestMapping(value = "/queryDictByDictType",method = RequestMethod.POST)
    public Map<String,Object> queryDictByDictType(@RequestBody Map<String,Object> param) throws Exception{
        String dictType=param.get("dictType")==null?null:(String)param.get("dictType");
        List<DictValue> list=dictFeignClient.queryDictByDictType(dictType);
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("list",list);
        return resultMap;
    }

    @Transactional(rollbackFor = {Exception.class})
    @RequestMapping(value = "/queryDict",method = RequestMethod.POST)
    public PageInfo<Dict> queryDict(@RequestBody Map<String,Object> param) throws Exception{
        try {

            int pageSize=(Integer)param.get("pageSize");
            int currentPage=(Integer)param.get("currentPage");
            String dictType=(String)param.get("dictType");
            PageHelper.startPage(currentPage,pageSize);
            //PageHelper.orderBy("DICT_TYPE desc");//此处使用排序会让数据出现重复，暂时无法找到原因
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("dictType",dictType);
            List<Dict> dictInfoList = dictFeignClient.queryDict(map);
            PageInfo<Dict> pageInfo=new PageInfo<>(dictInfoList);
            return pageInfo;

        }catch (Exception e){
            throw new Exception("查询数据字典失败！");
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    @RequestMapping(value = "/getDictTypeName",method = RequestMethod.POST)
    public String getDictTypeName(@RequestBody Map<String,Object> param) throws Exception{

        String dictType=param.get("dictType")==null?null:(String)param.get("dictType");
        List<Dict> list=dictFeignClient.queryDictTypeNameByDictType(dictType);
        if(list!=null&&list.size()>0){
            return list.get(0).getDictTypeName();
        }else {
            return "";
        }
    }


}
