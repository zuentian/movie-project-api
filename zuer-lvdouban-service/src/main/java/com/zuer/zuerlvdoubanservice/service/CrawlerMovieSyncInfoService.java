package com.zuer.zuerlvdoubanservice.service;

import com.zuer.zuerlvdoubancommon.entity.CrawlerMovieSyncInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface CrawlerMovieSyncInfoService extends Mapper<CrawlerMovieSyncInfo> {

    String getSyncFlagByIdFromCrawlerMovieSyncInfo(@Param("id") String id);

}
