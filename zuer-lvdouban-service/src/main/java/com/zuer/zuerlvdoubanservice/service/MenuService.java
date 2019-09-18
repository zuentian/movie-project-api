package com.zuer.zuerlvdoubanservice.service;

import com.zuer.zuerlvdoubancommon.entity.Menu;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MenuService extends Mapper<Menu> {

    List<Menu> queryMenuGroupByGroupIdAndGroupType(@Param("groupId")String groupId, @Param("groupType")String groupType);

    List<Menu> getUserAuthorityMenuByUserId(@Param("userId")String userId);
}
