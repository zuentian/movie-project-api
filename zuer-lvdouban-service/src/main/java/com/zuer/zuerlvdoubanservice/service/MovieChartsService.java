package com.zuer.zuerlvdoubanservice.service;

import com.zuer.zuerlvdoubancommon.charts.BarMovieShowCount;
import com.zuer.zuerlvdoubancommon.charts.CalendarMovieShowCount;
import com.zuer.zuerlvdoubancommon.charts.PieMovieShowCount;

import java.util.List;
import java.util.Map;

public interface MovieChartsService {
    List<CalendarMovieShowCount> queryCalendarMovieShowCount(Map<String, Object> map);

    List<BarMovieShowCount> queryBarMovieShowCount(String userId);

    List<PieMovieShowCount> queryPieShowMovieYearCount(String userId);
}
