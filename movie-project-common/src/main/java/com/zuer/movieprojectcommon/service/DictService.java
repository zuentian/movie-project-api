package com.zuer.movieprojectcommon.service;

import com.zuer.movieprojectcommon.entity.Dict;
import com.zuer.movieprojectcommon.entity.DictValue;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Mapper
public interface DictService {


    List<DictValue> queryDictByDictType(@Param("dictType")String dictType);
    @PostMapping("/queryDict")
    List<Dict> queryDict();
}
