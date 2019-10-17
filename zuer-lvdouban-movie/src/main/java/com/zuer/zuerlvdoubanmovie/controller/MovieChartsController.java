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
    public List<Object[]>  queryCalendarMovieShowCount(@RequestParam Map<String,Object> param) {
        Calendar car = Calendar.getInstance();
        car.setTime(new Date());
        int nowYear=car.get(Calendar.YEAR);//当前年份

        String id =(String) param.get("id");
        String year=param.get("year")==null?nowYear+"":(String)param.get("year");

        Map<String ,Object> map=new HashMap<String,Object>();
        map.put("userId",id);
        map.put("year",year);
        List<CalendarMovieShowCount> calendarMovieShowCounts=movieChartsFeignService.queryCalendarMovieShowCount(map);
        List<Object[]> list=new ArrayList<Object[]>();
        for(CalendarMovieShowCount c:calendarMovieShowCounts){
            Object [] obj=new Object[2];
            obj[0]=c.getDay();
            obj[1]=c.getCount();
            list.add(obj);
        }
        return list;
    }
}
