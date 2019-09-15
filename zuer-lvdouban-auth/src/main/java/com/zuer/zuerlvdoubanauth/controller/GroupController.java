package com.zuer.zuerlvdoubanauth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zuer.zuerlvdoubanauth.FeginService.DictFeignService;
import com.zuer.zuerlvdoubanauth.FeginService.GroupFeignService;
import com.zuer.zuerlvdoubanauth.FeginService.UserFeginService;
import com.zuer.zuerlvdoubanauth.jwt.JWTUtil;
import com.zuer.zuerlvdoubancommon.entity.Group;
import com.zuer.zuerlvdoubancommon.entity.GroupType;
import com.zuer.zuerlvdoubancommon.entity.UserInfo;
import com.zuer.zuerlvdoubancommon.utils.ClientUtil;
import com.zuer.zuerlvdoubancommon.utils.EntityUtils;
import com.zuer.zuerlvdoubancommon.utils.ReflectionUtils;
import com.zuer.zuerlvdoubancommon.utils.TreeUtil;
import com.zuer.zuerlvdoubancommon.vo.DictValue;
import com.zuer.zuerlvdoubancommon.vo.GroupTree;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.*;

@EnableAutoConfiguration
@RequestMapping(value = "/GroupController")
@RestController
public class GroupController {

    @Autowired
    private UserFeginService userFeginService;
    @Autowired
    private GroupFeignService groupFeignService;
    @Autowired
    private DictFeignService dictFeignService;

    @RequestMapping(value = "/queryTree", method = RequestMethod.POST)
    @ResponseBody
    public List<GroupTree> queryTree(String groupTypeId) {
        if(StringUtils.isBlank(groupTypeId)) {
            return new ArrayList<GroupTree>();
        }
        List<Group> groupList=groupFeignService.queryGroupByGroupTypeId(groupTypeId);
        String root="";
        List<DictValue> dictValueList=dictFeignService.queryDictByDictType("GROUPROOT");
        if(dictValueList!=null&&dictValueList.size()>0){
            root=dictValueList.get(0).getLabel();
        }
        return createrGroupTree(groupList, root);
    }
    @RequestMapping(value = "/insertGroup",method = RequestMethod.POST)
    public int insertGroup(@RequestParam Map<String, Object> param) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Group group = mapper.readValue((String) param.get("group"), Group.class);

        String uuid= UUID.randomUUID().toString();
        group.setId(uuid);
        EntityUtils.setCreatAndUpdatInfo(group);
        return groupFeignService.insertGroup(group);
    }

    private List<GroupTree> createrGroupTree(List<Group> groups, String root) {
        List<GroupTree> trees = new ArrayList<GroupTree>();
        GroupTree node = null;
        for (Group group : groups) {
            node = new GroupTree();
            BeanUtils.copyProperties(group, node);
            node.setLabel(group.getName());
            trees.add(node);
        }
        return TreeUtil.bulid(trees, root);
    }

    @RequestMapping(value = "/queryGroupById/{id}",method = RequestMethod.GET)
    public Group queryGroupById(@PathVariable String id){
        return groupFeignService.queryGroupById(id);
    }

    @RequestMapping(value = "/updateGroupById",method = RequestMethod.POST)
    @ResponseBody
    public int updateGroupById(@RequestParam Map<String, Object> param) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        Group group = mapper.readValue((String) param.get("group"), Group.class);
        EntityUtils.setUpdatedInfo(group);
        return groupFeignService.updateGroupById(group);

    }
    @RequestMapping(value = "/queryGroupByParentIdCount/{parentId}",method = RequestMethod.GET)
    public int queryGroupByParentIdCount(@PathVariable String parentId) throws Exception{
        return groupFeignService.queryGroupByParentIdCount(parentId);
    }


    @RequestMapping(value = "/deleteGroupById/{id}",method = RequestMethod.GET)
    public int deleteGroupById(@PathVariable String id) throws Exception{
        return groupFeignService.deleteGroupById(id);
    }
}
