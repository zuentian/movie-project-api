package com.zuer.zuerlvdoubanservice.service;

import com.zuer.zuerlvdoubancommon.entity.Group;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface GroupService extends Mapper<Group> {


    List<Group> queryGroupByUserId(String userId);
}
