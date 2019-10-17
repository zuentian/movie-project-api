package com.zuer.zuerlvdoubanmovie.controller;

import com.zuer.zuerlvdoubancommon.charts.CalendarMovieShowCount;
import com.zuer.zuerlvdoubanmovie.feginservice.MovieChartsFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@EnableAutoConfiguration
@RequestMapping(value = "/MovieChartsController")
@RestController
@Transactional(rollbackFor = {Exception.class})
public class MovieChartsController {

    @Autowired
    private MovieChartsFeignService movieChartsFeignService;

    @RequestMapping(value = "/queryCalendarMovieShowCount",method = RequestMethod.POST)
    public List<CalendarMovieShowCount> queryCalendarMovieShowCount(@RequestParam Map<String,Object> param) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int nowYear=c.get(Calendar.YEAR);//当前年份

        String id =(String) param.get("id");
        int year=param.get("year")==null?nowYear:Integer.valueOf((String)param.get("year"));

        Map<String ,Object> map=new HashMap<String,Object>();
        map.put("userId",id);
        map.put("stateYear",year+"-01-01");
        map.put("endYear",(year+1)+"-01-01");

        List<CalendarMovieShowCount> calendarMovieShowCounts=movieChartsFeignService.queryCalendarMovieShowCount(map);
        System.out.println("calendarMovieShowCounts"+calendarMovieShowCounts);
        return null;
    }
}
