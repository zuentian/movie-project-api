package com.zuer.zuerlvdoubanservice.service;

import com.zuer.zuerlvdoubancommon.entity.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface UserService extends Mapper<User> {

    List<User> queryUserByQueryParam(Map<String, Object> param);
}
