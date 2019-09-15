package com.zuer.zuerlvdoubanauth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zuer.zuerlvdoubanauth.FeginService.GroupTypeFeignService;
import com.zuer.zuerlvdoubanauth.FeginService.UserFeginService;
import com.zuer.zuerlvdoubanauth.jwt.JWTUtil;
import com.zuer.zuerlvdoubancommon.entity.GroupType;
import com.zuer.zuerlvdoubancommon.entity.UserInfo;
import com.zuer.zuerlvdoubancommon.utils.ClientUtil;
import com.zuer.zuerlvdoubancommon.utils.EntityUtils;
import com.zuer.zuerlvdoubancommon.utils.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

@EnableAutoConfiguration
@RequestMapping(value = "/GroupTypeController")
@RestController
public class GroupTypeController {

    @Autowired
    private GroupTypeFeignService groupTypeFeignService;

    @Autowired
    private UserFeginService userFeginService;

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
        Map<String,Object> map=new HashMap<>();
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
