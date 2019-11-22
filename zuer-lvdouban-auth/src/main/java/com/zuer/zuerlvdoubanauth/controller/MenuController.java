package com.zuer.zuerlvdoubanauth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zuer.zuerlvdoubanauth.feginService.DictFeignService;
import com.zuer.zuerlvdoubanauth.feginService.MenuFeignService;
import com.zuer.zuerlvdoubanauth.feginService.MenuGroupFeignService;
import com.zuer.zuerlvdoubancommon.entity.Menu;
import com.zuer.zuerlvdoubancommon.entity.MenuGroup;
import com.zuer.zuerlvdoubancommon.utils.EntityUtils;
import com.zuer.zuerlvdoubancommon.utils.TreeUtil;
import com.zuer.zuerlvdoubancommon.vo.DictValue;
import com.zuer.zuerlvdoubancommon.vo.MenuTree;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/*
菜单
 */
@EnableAutoConfiguration
@RequestMapping(value = "/MenuController")
@RestController
public class MenuController {
    @Autowired
    private MenuFeignService menuFeignService;
    @Autowired
    private DictFeignService dictFeignService;

    private List<MenuTree> createMenuTree(List<Menu> menus, String root) {
        List<MenuTree> trees = new ArrayList<MenuTree>();
        MenuTree node = null;
        for (Menu menu : menus) {
            node = new MenuTree();
            BeanUtils.copyProperties(menu, node);
            node.setLabel(menu.getTitle());
            trees.add(node);
        }
        return TreeUtil.bulid(trees, root);
    }


    @RequestMapping(value = "/queryMenuTree",method = RequestMethod.POST)
    public List<MenuTree> queryMenuTree(@RequestParam Map<String, Object> param) {
        String title = param.get("title") == null ? null : (String) param.get("title");
        List<Menu> menuList = menuFeignService.queryMenuByTitle(title);
        String root="";
        List<DictValue> dictValueList=dictFeignService.queryDictByDictType("MENUROOT");
        if(dictValueList!=null&&dictValueList.size()>0){
            root=dictValueList.get(0).getLabel();
        }
        return createMenuTree(menuList, root);
    }

    @RequestMapping(value = "/queryMenuById/{id}",method = RequestMethod.GET)
    public Menu queryMenuById(@PathVariable String id){
        return menuFeignService.queryMenuById(id);
    }

    @RequestMapping(value = "/addMenu",method = RequestMethod.POST)
    @ResponseBody
    public int addMenu(@RequestParam Map<String, Object> param) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Menu menu = mapper.readValue((String) param.get("menu"), Menu.class);

        String uuid= UUID.randomUUID().toString();
        menu.setId(uuid);
        EntityUtils.setCreatAndUpdatInfo(menu);
        return menuFeignService.insertMenu(menu);
    }

    @RequestMapping(value = "/updateMenu",method = RequestMethod.POST)
    @ResponseBody
    public int updateMenu(@RequestParam Map<String, Object> param) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Menu menu = mapper.readValue((String) param.get("menu"), Menu.class);
        EntityUtils.setUpdatedInfo(menu);
        return menuFeignService.updateMenuById(menu);
    }

    @RequestMapping(value = "/deleteMenuById/{id}",method = RequestMethod.GET)
    public int deleteMenuById(@PathVariable String id) throws Exception{
        return menuFeignService.deleteMenuById(id);
    }
    @RequestMapping(value = "/queryMenuByParentIdCount/{parentId}",method = RequestMethod.GET)
    public int queryMenuByParentIdCount(@PathVariable String parentId) throws Exception{
        return menuFeignService.queryMenuByParentIdCount(parentId);
    }

    @Autowired
    private MenuGroupFeignService menuGroupFeignService;


    @RequestMapping(value = "/updateMenuGroupByGroupId",method = RequestMethod.POST)
    @ResponseBody
    public void updateMenuGroupByGroupId(@RequestParam Map<String, Object> param) throws Exception {

        String groupId=param.get("groupId")==null?null:(String)param.get("groupId");

        String menuTrees=param.get("menuTrees")==null?null:(String)param.get("menuTrees");
        String[] menus=null;
        if(StringUtils.isNotBlank(menuTrees)){
            menus=menuTrees.split(",");
        }

        menuGroupFeignService.deleteMenuGroupByGroupId(groupId);

        List<Menu> menuList = menuFeignService.queryMenu();
        Map<String, String> map = new HashMap<String, String>();
        for (Menu menu : menuList) {
            map.put(menu.getId(), menu.getParentId());
        }
        Set<String> relationMenus = new HashSet<String>();
        relationMenus.addAll(Arrays.asList(menus));

        String root="";
        List<DictValue> dictValueList=dictFeignService.queryDictByDictType("MENUROOT");
        if(dictValueList!=null&&dictValueList.size()>0){
            root=dictValueList.get(0).getLabel();
        }
        MenuGroup menuGroup = null;
        for (String menuId : menus) {
            findParentID(map, relationMenus, menuId,root);//需要父节点的id
        }
        for(String menuId:relationMenus){
            menuGroup=new MenuGroup();
            menuGroup.setId(UUID.randomUUID().toString());
            menuGroup.setGroupId(groupId);
            menuGroup.setMenuId(menuId);
            EntityUtils.setCreateInfo(menuGroup);
            menuGroupFeignService.insertMenuGroup(menuGroup);
        }
    }

    private void findParentID(Map<String, String> map, Set<String> relationMenus, String id,String root) {
        String parentId = map.get(id);
        if (root.equals(id)) {
            return;
        }
        relationMenus.add(parentId);
        findParentID(map, relationMenus, parentId,root);
    }
    @RequestMapping(value = "/queryMenuGroupByGroupId/{groupId}",method = RequestMethod.GET)
    public List<Menu> queryMenuGroupByGroupId(@PathVariable String groupId) throws Exception{
        List<Menu> menuList=menuFeignService.queryMenuGroupByGroupId(groupId);
        return menuList ;
    }


}
