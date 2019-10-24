package com.zuer.zuerlvdoubanmovie.feginservice;


import com.zuer.zuerlvdoubancommon.charts.BarMovieShowCount;
import com.zuer.zuerlvdoubancommon.charts.CalendarMovieShowCount;
import com.zuer.zuerlvdoubancommon.charts.PieMovieShowCount;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(value = "zuer-lvdouban-service")
public interface MovieChartsFeignService {


    @RequestMapping(value = "/MovieCharts/queryCalendarMovieShowCount",method = RequestMethod.POST)
    List<CalendarMovieShowCount> queryCalendarMovieShowCount(@RequestBody Map<String, Object> map);

    @RequestMapping(value = "/MovieCharts/queryBarMovieShowCount",method = RequestMethod.GET)
    List<BarMovieShowCount> queryBarMovieShowCount(@RequestParam("userId") String userId);

    @RequestMapping(value = "/MovieCharts/queryPieShowMovieYearCount",method = RequestMethod.GET)
    List<PieMovieShowCount> queryPieShowMovieYearCount(@RequestParam("userId") String userId);
}
