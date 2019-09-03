package com.zuer.zuerlvdoubanauth.controller;

import com.zuer.zuerlvdoubanauth.FeginService.DictFeignService;
import com.zuer.zuerlvdoubancommon.entity.Dict;
import com.zuer.zuerlvdoubancommon.vo.page.PageResult;
import org.bouncycastle.crypto.tls.MACAlgorithm;
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
    DictFeignService dictFeignService;

/*

    @RequestMapping(value = "/queryDictByDictType",method = RequestMethod.POST)
    public Map<String,Object> queryDictByDictType(@RequestBody Map<String,Object> param) throws Exception{
        String dictType=param.get("dictType")==null?null:(String)param.get("dictType");
        List<DictValue> list=dictFeignClient.queryDictByDictType(dictType);
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("list",list);
        return resultMap;
    }
*/

    @Transactional(rollbackFor = {Exception.class})
    @RequestMapping(value = "/queryDict",method = RequestMethod.POST)
    @ResponseBody
    public PageResult<List<Dict>> queryDict(@RequestParam Map<String,Object> param) throws Exception{

            System.out.println("111111111111111111111");
            Map<String,Object> map=new HashMap<>();
            //String pageSize=(String)param.get("pageSize");
            //String pageIndex=(String)param.get("currentPage");
            String dictType =param.get("dictType")==null?null:(String) param.get("dictType");

            if(dictType!=null&&!"".equals(dictType)){
                map.put("dictType",dictType);
            }
            String pageSize="10";
            String pageIndex="1";
            System.out.println(map);

            PageResult<List<Dict>> list = dictFeignService.queryDict(map,pageSize,pageIndex);
            System.out.println(list);
            return list;

    }

  /*  @Transactional(rollbackFor = {Exception.class})
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

    @Transactional(rollbackFor = {Exception.class})
    @RequestMapping(value = "/addDict",method = RequestMethod.POST)
    public void addDict(@RequestBody Map<String,Object> param) throws Exception{
        try {
            ObjectMapper mapper = new ObjectMapper();
            Dict dict= mapper.convertValue(param.get("dictInfoAdd"), Dict.class);

            String dictId = UUID.randomUUID().toString();
            dict.setDictId(dictId);
            dict.setCrtTime(DateUtils.getCurrentDateTime());
            dict.setAltTime(DateUtils.getCurrentDateTime());
            int i=dictFeignClient.addDict(dict);
        }catch (Exception e){
            throw new Exception("增加数据字典失败！");
        }


    }

    @Transactional(rollbackFor = {Exception.class})
    @RequestMapping(value = "/queryDictByDictId",method = RequestMethod.POST)
    public Dict queryDictByDictId(@RequestBody Map<String ,Object> param) throws Exception{
        try {
            String dictId=(String)param.get("dictId");
            Dict dict=dictFeignClient.queryDictByDictId(dictId);
            return dict;
        }catch (Exception e){
            throw new Exception("数字字典查询失败！");
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    @RequestMapping(value = "/editDictByDictId",method = RequestMethod.POST)
    public void editDictByDictId(@RequestBody Map<String,Object> param )throws Exception{
        try{
            ObjectMapper obj=new ObjectMapper();
            Dict dict = obj.convertValue(param.get("dictInfoEdit"),Dict.class);
            dict.setAltTime(DateUtils.getCurrentDateTime());
            dictFeignClient.editDictByDictId(dict);
        }catch (Exception e){
            throw new Exception("更新数据字典失败");
        }

    }

    @Transactional(rollbackFor = {Exception.class})
    @RequestMapping(value = "/deleteDictByDictId",method = RequestMethod.POST)
    public void deleteDictByDictId(@RequestBody Map<String,Object>param ) throws Exception{

        try{

            String dictId=(String)param.get("dictId");
            dictFeignClient.deleteDictByDictId(dictId);

        }catch (Exception e){
            throw new Exception("删除数据字典失败！");
        }


    }*/

}
