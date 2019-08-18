package com.zuer.movieprojectuser.service;

import com.zuer.movieprojectuser.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserService {
    List<User> queryUser(Map<String,Object> map);

    List<User> queryUserByUserCode(String userCode);
}
