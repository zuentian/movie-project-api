package com.zuer.zuerlvdoubanauth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zuer.zuerlvdoubanauth.FeginService.ElementFeignService;
import com.zuer.zuerlvdoubanauth.FeginService.UserFeginService;
import com.zuer.zuerlvdoubancommon.entity.Element;
import com.zuer.zuerlvdoubancommon.utils.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
        EntityUtils.setUpdatedInfo(element);
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



}
