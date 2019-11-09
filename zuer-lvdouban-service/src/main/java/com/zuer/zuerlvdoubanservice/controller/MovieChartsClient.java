package com.zuer.zuerlvdoubanservice.controller;

import com.zuer.zuerlvdoubancommon.charts.BarMovieShowCount;
import com.zuer.zuerlvdoubancommon.charts.CalendarMovieShowCount;
import com.zuer.zuerlvdoubancommon.charts.PieMovieShowCount;
import com.zuer.zuerlvdoubanservice.service.MovieChartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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


    @RequestMapping(value = "/queryBarMovieShowCount",method = RequestMethod.GET)
    public List<BarMovieShowCount> queryBarMovieShowCount(@RequestParam("userId") String userId){
        return movieChartsService.queryBarMovieShowCount(userId);
    }

    @RequestMapping(value = "/queryPieShowMovieYearCount",method = RequestMethod.GET)
    public List<PieMovieShowCount> queryPieShowMovieYearCount(@RequestParam("userId") String userId){
        return movieChartsService.queryPieShowMovieYearCount(userId);
    }
}
