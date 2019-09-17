package com.zuer.zuerlvdoubanauth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zuer.zuerlvdoubanauth.FeginService.ElementFeignService;
import com.zuer.zuerlvdoubanauth.FeginService.ElementGroupFeignService;
import com.zuer.zuerlvdoubancommon.entity.Element;
import com.zuer.zuerlvdoubancommon.entity.ElementGroup;
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
@RequestMapping(value = "/ElementController")
@RestController
public class ElementController {
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
        EntityUtils.setCreateInfo(element);
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


    @RequestMapping(value = "/queryElementByMenuId/{menuId}",method = RequestMethod.GET)
    public List<Element> queryElementByMenuId(@PathVariable String menuId){
        return elementFeignService.queryElementByMenuId(menuId);
    }

    public final static String GROUP_TYPE = "group";
    @Autowired
    private ElementGroupFeignService elementGroupFeignService;

    @RequestMapping(value = "/addElementGroupByGroupIdAndElementId",method = RequestMethod.POST)
    @ResponseBody
    public int addElementGroupByGroupIdAndElementId(@RequestParam Map<String, Object> param) throws Exception {

        String groupId=param.get("groupId")==null?null:(String)param.get("groupId");
        String elementId=param.get("elementId")==null?null:(String)param.get("elementId");

        ElementGroup elementGroup=new ElementGroup();
        elementGroup.setId(UUID.randomUUID().toString());
        elementGroup.setGroupId(groupId);
        elementGroup.setElementId(elementId);
        elementGroup.setGroupType(GROUP_TYPE);
        EntityUtils.setCreateInfo(elementGroup);
        return elementGroupFeignService.insertElementGroup(elementGroup);

    }
    @RequestMapping(value = "/deleteElementGroupByGroupIdAndElementId",method = RequestMethod.POST)
    @ResponseBody
    public int deleteElementGroupByGroupIdAndElementId(@RequestParam Map<String, Object> param) throws Exception {
        String groupId=param.get("groupId")==null?null:(String)param.get("groupId");
        String elementId=param.get("elementId")==null?null:(String)param.get("elementId");
        ElementGroup elementGroup=new ElementGroup();
        elementGroup.setGroupId(groupId);
        elementGroup.setElementId(elementId);
        return elementGroupFeignService.deleteElementGroupByGroupIdAndElementId(elementGroup);

    }

    @RequestMapping(value = "/queryElementGroupByGroupId/{groupId}",method = RequestMethod.GET)
    public List<ElementGroup> queryElementGroupByGroupId(@PathVariable String groupId){
        return elementGroupFeignService.queryElementGroupByGroupId(groupId);
    }

    @RequestMapping(value = "/addElementGroupByGroupIdAndElementIds",method = RequestMethod.POST)
    @ResponseBody
    public void addElementGroupByGroupIdAndElementIds(@RequestParam Map<String, Object> param) throws Exception {
        String groupId=param.get("groupId")==null?null:(String)param.get("groupId");
        String elementIds=param.get("elementIds")==null?null:(String)param.get("elementIds");
        String menuId=param.get("menuId")==null?null:(String)param.get("menuId");

        List<Element> elementList = elementFeignService.queryElementByMenuId(menuId);

        for(Element element:elementList){
            ElementGroup elementGroup=new ElementGroup();
            elementGroup.setGroupId(groupId);
            elementGroup.setElementId(element.getId());
            //全选时删除操作不能把其他的菜单对于功能关联信息删除，因此需要得到菜单ID
            elementGroupFeignService.deleteElementGroupByGroupIdAndElementId(elementGroup);
        }
        String[] elementId=elementIds.split(",");
        for(String id:elementId){
            ElementGroup elementGroup=new ElementGroup();
            elementGroup.setId(UUID.randomUUID().toString());
            elementGroup.setGroupId(groupId);
            elementGroup.setElementId(id);
            elementGroup.setGroupType(GROUP_TYPE);
            EntityUtils.setCreateInfo(elementGroup);
            elementGroupFeignService.insertElementGroup(elementGroup);
        }
    }
}
