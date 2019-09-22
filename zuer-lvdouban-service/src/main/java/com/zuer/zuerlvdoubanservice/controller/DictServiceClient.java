package com.zuer.zuerlvdoubanservice.controller;


import com.zuer.zuerlvdoubancommon.entity.Dict;
import com.zuer.zuerlvdoubancommon.entity.User;
import com.zuer.zuerlvdoubancommon.utils.RowBoundUtil;
import com.zuer.zuerlvdoubancommon.vo.DictValue;
import com.zuer.zuerlvdoubanservice.service.DictService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EnableFeignClients
@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/Dict")
public class DictServiceClient {

    @Autowired
    DictService dictService;

    @RequestMapping(value = "/queryPageFromDict",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> queryPageFromDict(@RequestBody Map<String, Object> map,
                                                @RequestParam("pageSize") String pageSize,
                                                @RequestParam("pageIndex") String pageIndex) throws Exception{

        Example example=new Example(Dict.class);
        example.setOrderByClause("DICT_TYPE");//实现排序
        Example.Criteria criteria = example.createCriteria();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            criteria.andEqualTo(entry.getKey(),entry.getValue());
        }
        RowBounds rowBounds = RowBoundUtil.getRowBounds(pageSize, pageIndex);
        List<Dict> lists = dictService.selectByExampleAndRowBounds(example, rowBounds);
        Map<String,Object> resultMap=new HashMap<String, Object>();
        resultMap.put("list",lists);
        int count = dictService.selectCountByExample(example);
        resultMap.put("count",count);
        return resultMap;
    }



    @RequestMapping(value = "/getDictTypeName",method = RequestMethod.POST)
    public Dict getDictTypeName(@RequestParam("dictType") String dictType){
        Example example=new Example(Dict.class);
        example.createCriteria().andEqualTo("dictType",dictType);
        List<Dict> dictList=dictService.selectByExample(example);
        Dict dict=null;
        if(dictList!=null&&dictList.size()>0){
            dict=dictList.get(0);
        }
        return dict;
    }


    @RequestMapping(value = "/addDict",method = RequestMethod.POST)
    public int addDict(@RequestBody Dict dict){
        return dictService.insert(dict);
    }


    @RequestMapping(value = "/queryDictByDictId",method = RequestMethod.GET)
    public Dict queryDictByDictId(@RequestParam("dictId") String dictId){
        Dict dict=new Dict();
        dict.setDictId(dictId);
        return dictService.selectOne(dict);
    }


    @RequestMapping(value = "/editDictByDictId",method = RequestMethod.POST)
    public int editDictByDictId(@RequestBody Dict dict){
        return dictService.updateByPrimaryKey(dict);
    }


    @RequestMapping(value = "/deleteDictByDictId",method = RequestMethod.GET)
    public int deleteDictByDictId(@RequestParam("dictId") String dictId){
        Dict dict=new Dict();
        dict.setDictId(dictId);
        return dictService.delete(dict);
    }

    @RequestMapping(value = "/queryDictByDictType",method = RequestMethod.GET)
        //此处调用的时候@RequestParam需要加value不然会报错
    public List<DictValue> queryDictByDictType(@RequestParam("dictType") String dictType){

        return dictService.queryDictByDictType(dictType);

    }

}
