package com.zuer.zuerlvdoubanauth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zuer.zuerlvdoubanauth.FeginService.DictFeignService;
import com.zuer.zuerlvdoubancommon.entity.Dict;
import com.zuer.zuerlvdoubancommon.utils.DateUtil;
import com.zuer.zuerlvdoubancommon.vo.DictValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@EnableAutoConfiguration
@RequestMapping(value = "/DictController")
@RestController
public class DictController {


    @Autowired
    DictFeignService dictFeignService;



    @RequestMapping(value = "/queryDictByDictType",method = RequestMethod.POST)
    public List<DictValue>  queryDictByDictType(@RequestParam Map<String,Object> param) throws Exception{
        String dictType=param.get("dictType")==null?null:(String)param.get("dictType");
        List<DictValue> list=dictFeignService.queryDictByDictType(dictType);
        return list;
    }


    @Transactional(rollbackFor = {Exception.class})
    @RequestMapping(value = "/queryPageFromDict",method = RequestMethod.POST)
    @ResponseBody
    public  Map<String,Object> queryPageFromDict(@RequestParam Map<String,Object> param) throws Exception{
            try{
                Map<String,Object> map=new HashMap<>();
                String pageSize=(String)param.get("pageSize");
                String pageIndex=(String)param.get("currentPage");
                String dictType =param.get("dictType")==null?null:(String) param.get("dictType");
                if(dictType!=null&&!"".equals(dictType)){
                    map.put("dictType",dictType);
                }
                Map<String,Object> resultMap = dictFeignService.queryPageFromDict(map,pageSize,pageIndex);
                return resultMap;

            }catch (Exception e){
                throw new Exception("查询数据字典失败！");
            }

    }

    @Transactional(rollbackFor = {Exception.class})
    @RequestMapping(value = "/getDictTypeName",method = RequestMethod.POST)
    public String getDictTypeName(@RequestParam Map<String,Object> param) throws Exception{

        String dictType=param.get("dictType")==null?null:(String)param.get("dictType");
        Dict dict=dictFeignService.getDictTypeName(dictType);
        if(dict!=null){
            return dict.getDictTypeName();
        }else {
            return "";
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    @RequestMapping(value = "/addDict",method = RequestMethod.POST)
    public void addDict(@RequestParam Map<String,Object> param) throws Exception{
        try {
            ObjectMapper mapper = new ObjectMapper();
            Dict dict= mapper.readValue((String) param.get("dictInfoAdd"), Dict.class);
            String dictId = UUID.randomUUID().toString();
            dict.setDictId(dictId);
            dict.setCrtTime(DateUtil.getCurrentDateTime());
            dict.setAltTime(DateUtil.getCurrentDateTime());
            dictFeignService.addDict(dict);
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new Exception("增加数据字典失败！");
        }


    }

    @Transactional(rollbackFor = {Exception.class})
    @RequestMapping(value = "/queryDictByDictId",method = RequestMethod.POST)
    public Dict queryDictByDictId(@RequestParam Map<String ,Object> param) throws Exception{
        try {
            String dictId=(String)param.get("dictId");
            Dict dict=dictFeignService.queryDictByDictId(dictId);
            return dict;
        }catch (Exception e){
            throw new Exception("数字字典查询失败！");
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    @RequestMapping(value = "/editDictByDictId",method = RequestMethod.POST)
    public void editDictByDictId(@RequestParam Map<String,Object> param )throws Exception{
        try{
            ObjectMapper mapper=new ObjectMapper();
            Dict dict= mapper.readValue((String) param.get("dictInfoEdit"), Dict.class);
            dict.setAltTime(DateUtil.getCurrentDateTime());
            dictFeignService.editDictByDictId(dict);
        }catch (Exception e){
            throw new Exception("更新数据字典失败");
        }

    }

    @Transactional(rollbackFor = {Exception.class})
    @RequestMapping(value = "/deleteDictByDictId",method = RequestMethod.POST)
    public void deleteDictByDictId(@RequestParam Map<String,Object>param ) throws Exception{
        try{
            String dictId=(String)param.get("dictId");
            dictFeignService.deleteDictByDictId(dictId);

        }catch (Exception e){
            throw new Exception("删除数据字典失败！");
        }


    }

}
