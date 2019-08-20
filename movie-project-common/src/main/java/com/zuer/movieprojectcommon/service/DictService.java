package com.zuer.movieprojectcommon.service;

import com.zuer.movieprojectcommon.entity.DictValue;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DictService {

    List<DictValue> queryDictByDictType(@Param("dictType")String dictType);

}
