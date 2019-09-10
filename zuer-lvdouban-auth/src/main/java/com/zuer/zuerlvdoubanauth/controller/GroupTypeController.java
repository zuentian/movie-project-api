package com.zuer.zuerlvdoubanauth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zuer.zuerlvdoubanauth.FeginService.GroupTypeFeignService;
import com.zuer.zuerlvdoubanauth.FeginService.UserFeginService;
import com.zuer.zuerlvdoubanauth.jwt.JWTUtil;
import com.zuer.zuerlvdoubancommon.entity.GroupType;
import com.zuer.zuerlvdoubancommon.entity.UserInfo;
import com.zuer.zuerlvdoubancommon.utils.ClientUtil;
import com.zuer.zuerlvdoubancommon.utils.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
        groupType=setGroupTypeCrt(groupType);
        groupType=setGroupTypeUpd(groupType);
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
        groupType=setGroupTypeUpd(groupType);
        return groupTypeFeignService.updateGroupTypeById(groupType);

    }

    @RequestMapping(value = "/deleteGroupTypeById/{id}",method = RequestMethod.GET)
    public int deleteGroupTypeById(@PathVariable String id){
        return groupTypeFeignService.deleteGroupTypeById(id);
    }


    private GroupType setGroupTypeCrt(GroupType groupType){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String hostIp = ClientUtil.getClientIp(request);
        String token=request.getHeader("Authorization");//获取token值，取得登陆的账号
        UserInfo userInfo=userFeginService.queryUserInfoByUserName(JWTUtil.getUsername(token));
        // 默认属性
        String[] fields = {"crtHost","crtTime","crtUser","crtName"};
        Field field = ReflectionUtils.getAccessibleField(groupType, "crtTime");
        // 默认值
        Object [] value = null;
        if(field!=null&&field.getType().equals(Date.class)){
            value = new Object []{hostIp,new Date(),userInfo.getUsername(),userInfo.getName()};
        }
        // 填充默认属性值
        setDefaultValues(groupType, fields, value);
        return groupType;
    }


    private GroupType setGroupTypeUpd(GroupType groupType){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String hostIp = ClientUtil.getClientIp(request);
        String token=request.getHeader("Authorization");//获取token值，取得登陆的账号
        UserInfo userInfo=userFeginService.queryUserInfoByUserName(JWTUtil.getUsername(token));
        // 默认属性
        String[] fields = {"updHost","updTime","updUser","updName"};
        Field field = ReflectionUtils.getAccessibleField(groupType, "updTime");
        // 默认值
        Object [] value = null;
        if(field!=null&&field.getType().equals(Date.class)){
            value = new Object []{hostIp,new Date(),userInfo.getUsername(),userInfo.getName()};
        }
        // 填充默认属性值
        setDefaultValues(groupType, fields, value);
        return groupType;
    }
    /**
     * 依据对象的属性数组和值数组对对象的属性进行赋值
     *
     * @param entity 对象
     * @param fields 属性数组
     * @param value 值数组
     * @author 王浩彬
     */
    private static <T> void setDefaultValues(T entity, String[] fields, Object[] value) {
        for(int i=0;i<fields.length;i++){
            String field = fields[i];
            if(ReflectionUtils.hasField(entity, field)){
                ReflectionUtils.invokeSetter(entity, field, value[i]);
            }
        }
    }
}
