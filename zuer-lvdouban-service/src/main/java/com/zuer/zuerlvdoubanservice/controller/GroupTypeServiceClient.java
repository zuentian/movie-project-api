package com.zuer.zuerlvdoubanservice.controller;

import com.zuer.zuerlvdoubancommon.entity.Element;
import com.zuer.zuerlvdoubancommon.entity.GroupType;
import com.zuer.zuerlvdoubancommon.utils.RowBoundUtil;
import com.zuer.zuerlvdoubanservice.service.GroupTypeService;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping(value = "/GroupType")
public class GroupTypeServiceClient {
    @Autowired
    private GroupTypeService groupTypeService;


    @RequestMapping(value = "/insertGroupType",method = RequestMethod.POST)
    public int insertGroupType(@RequestBody GroupType groupType){
        return  groupTypeService.insertSelective(groupType);
    }

    @RequestMapping(value = "/queryGroupTypeByParam",method = RequestMethod.POST)
    public Map<String, Object> queryGroupTypeByParam(@RequestParam("pageSize") String pageSize,
                                              @RequestParam("page") String page,
                                              @RequestBody Map<String, Object> map){

        Map<String,Object> resultMap=new HashMap<>();
        Example example=new Example(Element.class);
        example.setOrderByClause("UPD_TIME DESC");//实现排序
        Example.Criteria criteria = example.createCriteria();
        String name=map.get("name")==null?null:(String)map.get("name");
        if(StringUtils.isNotBlank(name)){
            criteria.andLike("name","%"+name+"%");
        }

        RowBounds rowBounds = RowBoundUtil.getRowBounds(pageSize, page);

        List<GroupType> elementList=groupTypeService.selectByExampleAndRowBounds(example,rowBounds);
        resultMap.put("list",elementList);
        int count=groupTypeService.selectCountByExample(example);
        resultMap.put("count",count);
        return resultMap;

    }
    @RequestMapping(value = "/queryGroupTypeById",method = RequestMethod.GET)
    public GroupType queryGroupTypeById(@RequestParam("id") String id){
        return groupTypeService.selectByPrimaryKey(id);
    }

    @RequestMapping(value = "/updateGroupTypeById",method = RequestMethod.POST)
    public int updateGroupTypeById(@RequestBody GroupType groupType){
        return groupTypeService.updateByPrimaryKeySelective(groupType);
    }


    @RequestMapping(value = "/deleteGroupTypeById",method = RequestMethod.GET)
    public int deleteGroupTypeById(@RequestParam("id")  String id){
        return groupTypeService.deleteByPrimaryKey(id);
    }
}
