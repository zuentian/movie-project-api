package com.zuer.zuerlvdoubanservice.controller;

import com.zuer.zuerlvdoubancommon.charts.CalendarMovieShowCount;
import com.zuer.zuerlvdoubanservice.service.MovieChartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@EnableFeignClients
@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/MovieCharts")
@Transactional(rollbackFor = Exception.class)
public class MovieChartsClient {

    @Autowired
    private MovieChartsService movieChartsService;

    @RequestMapping(value = "/queryCalendarMovieShowCount",method = RequestMethod.POST)
    public List<CalendarMovieShowCount> queryCalendarMovieShowCount(@RequestBody Map<String, Object> map){

        return movieChartsService.queryCalendarMovieShowCount(map);

    }
}
