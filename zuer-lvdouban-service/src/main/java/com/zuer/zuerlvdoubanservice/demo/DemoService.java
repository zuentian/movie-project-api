package com.zuer.zuerlvdoubanservice.demo;

import com.zuer.zuerlvdoubancommon.demo.Demo;
import org.apache.ibatis.annotations.CacheNamespace;
import tk.mybatis.mapper.common.Mapper;
@CacheNamespace
public interface DemoService extends Mapper<Demo> {
}
