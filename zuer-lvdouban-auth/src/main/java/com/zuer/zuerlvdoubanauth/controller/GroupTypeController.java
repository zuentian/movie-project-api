package com.zuer.zuerlvdoubanauth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zuer.zuerlvdoubanauth.feginService.GroupTypeFeignService;
import com.zuer.zuerlvdoubancommon.entity.GroupType;
import com.zuer.zuerlvdoubancommon.utils.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@EnableAutoConfiguration
@RequestMapping(value = "/GroupTypeController")
@RestController
public class GroupTypeController {

    @Autowired
    private GroupTypeFeignService groupTypeFeignService;


    @RequestMapping(value = "/addGroupType",method = RequestMethod.POST)
    public int addElement(@RequestParam Map<String, Object> param) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        GroupType groupType = mapper.readValue((String) param.get("groupType"), GroupType.class);

        String uuid= UUID.randomUUID().toString();
        groupType.setId(uuid);
        EntityUtils.setCreatAndUpdatInfo(groupType);
        return groupTypeFeignService.insertGroupType(groupType);
    }

    @RequestMapping(value = "/queryGroupTypeByParam",method = RequestMethod.POST)
    public Map<String,Object> queryGroupTypeByParam(@RequestParam(defaultValue = "10") String limit,
                                                @RequestParam(defaultValue = "1") String page,
                                                String name){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("name",name);
        return groupTypeFeignService.queryGroupTypeByParam(limit,page,map);
    }

    @RequestMapping(value = "/queryGroupTypeById/{id}",method = RequestMethod.GET)
    public GroupType queryGroupTypeById(@PathVariable String id){
        return groupTypeFeignService.queryGroupTypeById(id);
    }

    @RequestMapping(value = "/updateGroupTypeById",method = RequestMethod.POST)
    @ResponseBody
    public int updateGroupTypeById(@RequestParam Map<String, Object> param) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        GroupType groupType = mapper.readValue((String) param.get("groupType"), GroupType.class);
        EntityUtils.setUpdatedInfo(groupType);
        return groupTypeFeignService.updateGroupTypeById(groupType);

    }

    @RequestMapping(value = "/deleteGroupTypeById/{id}",method = RequestMethod.GET)
    public int deleteGroupTypeById(@PathVariable String id){
        return groupTypeFeignService.deleteGroupTypeById(id);
    }


    @RequestMapping(value = "/getAllGroupTypes",method = RequestMethod.POST)
    public List<GroupType> getAllGroupTypes() throws Exception{
        return groupTypeFeignService.queryGroupType();
    }
}
