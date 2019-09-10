package com.zuer.zuerlvdoubanauth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zuer.zuerlvdoubanauth.FeginService.ElementFeignService;
import com.zuer.zuerlvdoubanauth.FeginService.UserFeginService;
import com.zuer.zuerlvdoubanauth.jwt.JWTUtil;
import com.zuer.zuerlvdoubancommon.entity.Element;
import com.zuer.zuerlvdoubancommon.entity.Menu;
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
@RequestMapping(value = "/ElementController")
@RestController
public class ElementController {
    @Autowired
    private UserFeginService userFeginService;
    @Autowired
    private ElementFeignService elementFeignService;
    @RequestMapping(value = "/queryElementParam",method = RequestMethod.POST)
    public Map<String,Object> queryElementParam(@RequestParam(defaultValue = "10") String limit,
                                                @RequestParam(defaultValue = "1") String page,
                                                String name,
                                                String menuId){
        Map<String,Object> map=new HashMap<>();
        map.put("name",name);
        map.put("menuId",menuId);
        return elementFeignService.queryElementParam(limit,page,map);
    }

    @RequestMapping(value = "/addElement",method = RequestMethod.POST)
    public int addElement(@RequestParam Map<String, Object> param) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Element element = mapper.readValue((String) param.get("element"), Element.class);

        String uuid= UUID.randomUUID().toString();
        element.setId(uuid);
        element=setMenuCrt(element);
        return elementFeignService.insertElement(element);
    }


    @RequestMapping(value = "/queryElementById/{id}",method = RequestMethod.GET)
    public Element queryElementById(@PathVariable String id){
        return elementFeignService.queryElementById(id);
    }

    @RequestMapping(value = "/updateElementById",method = RequestMethod.POST)
    @ResponseBody
    public int updateElementById(@RequestParam Map<String, Object> param) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        Element element = mapper.readValue((String) param.get("element"), Element.class);
        return elementFeignService.updateElementById(element);

    }

    @RequestMapping(value = "/deleteElementById/{id}",method = RequestMethod.GET)
    public int deleteElementById(@PathVariable String id){
        return elementFeignService.deleteElementById(id);
    }

    private Element setMenuCrt(Element element){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String hostIp = ClientUtil.getClientIp(request);
        String token=request.getHeader("Authorization");//获取token值，取得登陆的账号
        UserInfo userInfo=userFeginService.queryUserInfoByUserName(JWTUtil.getUsername(token));
        // 默认属性
        String[] fields = {"crtHost","crtTime","crtUser","crtName"};
        Field field = ReflectionUtils.getAccessibleField(element, "crtTime");
        // 默认值
        Object [] value = null;
        if(field!=null&&field.getType().equals(Date.class)){
            value = new Object []{hostIp,new Date(),userInfo.getUsername(),userInfo.getName()};
        }
        // 填充默认属性值
        setDefaultValues(element, fields, value);
        return element;
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
