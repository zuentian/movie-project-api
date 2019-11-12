package com.zuer.zuerlvdoubanservice.demo;

import com.zuer.zuerlvdoubancommon.demo.Demo;
import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;
@CacheNamespace//开启二级缓存
@Transactional
public interface DemoService extends Mapper<Demo> {
}
