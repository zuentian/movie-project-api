package com.zuer.zuerlvdoubanservice.service;

import com.zuer.zuerlvdoubancommon.entity.Dict;
import com.zuer.zuerlvdoubancommon.vo.DictValue;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface DictService extends Mapper<Dict> {

    List<DictValue> queryDictByDictType(String dictType);
}
