package com.zuer.movieprojectuser.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.zuer.movieprojectcommon.entity.Dict;
import com.zuer.movieprojectcommon.entity.DictValue;
import com.zuer.movieprojectcommon.entity.User;
import com.zuer.movieprojectcommon.entity.UserType;
import com.zuer.movieprojectuser.feignConfig.DictFeignClient;
import com.zuer.movieprojectuser.feignConfig.UserFeignClient;
import com.zuer.movieprojectuser.utils.DateUtils;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@EnableAutoConfiguration
@RequestMapping(value = "/UserInfoController")
@RestController
public class UserInfoController {


    @Autowired
    UserFeignClient userFeignClient;
    @Autowired
    DictFeignClient dictFeignClient;


    @Transactional(rollbackFor = {Exception.class})
    @RequestMapping(value = "/search")
    public Map<String,Object> search(@RequestBody Map<String,Object> param) throws Exception{

        try {
            String nameOrMobile=param.get("nameOrMobile")==null?null:(String)param.get("nameOrMobile");
            int pageNum = (Integer)param.get("pageNum");
            int pageSize = (Integer)param.get("pageSize");
            String status = param.get("status")==null?null:(String)param.get("status");

            int start=(pageNum-1)*pageSize+1;
            int end=pageNum*pageSize;

            Map<String,Object> map=new HashMap<String,Object>();
            map.put("nameOrMobile",nameOrMobile);
            map.put("start",start);
            map.put("end",end);
            map.put("status",status);

            int count=userFeignClient.queryUserCount(map);
            List<User> userList=null;
            if(count>0){
                userList=userFeignClient.queryUser(map);
                if(userList==null){
                    throw new Exception("查无数据");
                }
            }
            Map<String,Object> resultMap =new HashMap<String,Object>();
            resultMap.put("list",userList);
            resultMap.put("count",count);
            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage()) ;
        }
    }


    @Transactional(rollbackFor = {Exception.class})
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public void create(@RequestBody Map<String ,Object> param)throws Exception{

        try {

            String userCode=param.get("userCode")==null?null:(String)param.get("userCode");

            List<User> userOld=userFeignClient.queryUserByUserCode(userCode);
            if(userOld!=null&&userOld.size()>0){
                throw new Exception("该用户已被创建!");
            }

            String userName=param.get("userName")==null?null:(String)param.get("userName");

            String userNameBak=param.get("userNameBak")==null?null:(String)param.get("userNameBak");

            String mobile=param.get("mobile")==null?null:(String)param.get("mobile");

            String password=param.get("password")==null?null:(String)param.get("password");
            DefaultPasswordService defaultPasswordService=new DefaultPasswordService();
            String passwordEncrypt = defaultPasswordService.encryptPassword(password);

            String status=param.get("status")==null?null:(String)param.get("status");
            String sex=param.get("sex")==null?null:(String)param.get("sex");

            User user =new User();

            String uuid=UUID.randomUUID().toString();
            user.setUserId(uuid);
            user.setUserCode(userCode);
            user.setUserName(userName);
            user.setUserNameBak(userNameBak);
            user.setSex(sex);
            user.setPassword(passwordEncrypt);
            user.setStatus(status);
            user.setMobile(mobile);

            boolean isEmail=isEmail(userCode);//检验是否是邮箱

            if(userCode.equals(mobile)){
                user.setUserType(UserType.MOBILE.getCode());
            }else if(isEmail){
                user.setUserType(UserType.EMAIL.getCode());
            }else if(userCode.indexOf("@")>0){
                user.setUserType(UserType.OTHEREAMIL.getCode());
            }else{
                user.setUserType(UserType.OTHER.getCode());
            }

            String userPhotoUrl=getSysPhotoUrl(sex);//获取系统头像URL
            user.setUserPhotoUrl(userPhotoUrl);

            user.setCrtTime(DateUtils.getCurrentDateTime());
            user.setAltTime(DateUtils.getCurrentDateTime());
            userFeignClient.insertUser(user);


        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new Exception("创建用户失败！");
        }

    }

    @Transactional(rollbackFor = {Exception.class})
    @RequestMapping(value = "/queryUserByUserId",method = RequestMethod.POST)
    public User queryUserByUserId(@RequestBody Map<String,Object> param){
        String userId=(String)param.get("userId");

        return userFeignClient.queryUserByUserId(userId);
    }

    @Transactional(rollbackFor = {Exception.class})
    @RequestMapping(value = "/updateUserByUserId",method = RequestMethod.POST)
    public void updateUserByUserId(@RequestBody Map<String ,Object>param) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        User user= mapper.convertValue(param.get("user"), User.class);

        user.setAltTime(DateUtils.getCurrentDateTime());
        String password= user.getPassword();
        DefaultPasswordService defaultPasswordService=new DefaultPasswordService();
        String passwordEncrypt = defaultPasswordService.encryptPassword(password);
        user.setPassword(passwordEncrypt);
        System.out.println(user);
        userFeignClient.updateUserByUserId(user);
    }

    @Transactional(rollbackFor = {Exception.class})
    @RequestMapping(value = "/deleteUserByUserId",method = RequestMethod.POST)
    public void deleteUserByUserId(@RequestBody Map<String ,Object> param)throws Exception{
        String userId=(String)param.get("userId");
        userFeignClient.deleteUserByUserId(userId);
    }

    @Transactional(rollbackFor = {Exception.class})
    @RequestMapping(value = "/updateUserToStatusByUserId",method = RequestMethod.POST)
    public void updateUserToStatusByUserId(@RequestBody Map<String,Object> param) throws Exception{

        String userId=(String)param.get("userId");
        String status=(String)param.get("status");
        Map<String,String> map=new HashMap<>();
        map.put("userId",userId);
        map.put("status",status);
        map.put("altTime",DateUtils.getCurrentDateTime());
        userFeignClient.updateUserToStatusByUserId(map);


    }

    private String getSysPhotoUrl(String sex) throws Exception{
        String url="";
        List<DictValue> dictValueList=dictFeignClient.queryDictByDictType("SYSPHOTOURL");
        if(dictValueList!=null&&dictValueList.size()>0){
            for(DictValue dictValue:dictValueList){
                if(sex!=null&&sex.equals(dictValue.getValue())){
                    url = dictValue.getLabel();
                }
            }
            if("".equals(url)){
                Random rd=new Random();
                int index=rd.nextInt(dictValueList.size());
                url=dictValueList.get(index).getLabel();
            }
        }
        return url;
    }

    private boolean isEmail(String userCode) throws Exception{

        List<DictValue> dictValueList=dictFeignClient.queryDictByDictType("EMAILSUBSTRING");
        if(dictValueList!=null){
            for(DictValue dictValue:dictValueList) {
                if(userCode.toLowerCase().indexOf(dictValue.getValue())>0){
                    return true;
                }
            }
        }
        return false;
    }

}
