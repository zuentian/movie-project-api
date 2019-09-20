package com.zuer.zuerlvdoubanservice.service;

import com.zuer.zuerlvdoubancommon.entity.Element;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ElementService extends Mapper<Element> {
    List<Element> getUserAuthorityElementByUserId(@Param("userId") String userId);

    List<Element> getUserElementAllByUserId(@Param("userId") String userId);
}
